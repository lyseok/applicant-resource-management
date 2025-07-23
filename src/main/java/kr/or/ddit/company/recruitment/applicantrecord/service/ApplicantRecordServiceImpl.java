package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import kr.or.ddit.admin.common.email.utils.EmailUtil;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.PasserMapper;
import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.member.resume.resume.service.ResumeService;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantRecordServiceImpl implements ApplicantRecordService {
	
	private final ApplicantRecordMapper applMapper;
	private final ApplicantMapper applicantMapper;
	private final RecruitProcessMapper processMapper;
	private final PasserMapper passMapper;
	private final ResumeService resumeService;
	
	private final EmailUtil emailUtils;

	@Override
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo) {
		
		return applMapper.getApplicantByRecruitment(recruitmentNo);
	}

	@Transactional
	@Override
	public void updateNextStep(ApplicantRecordVO vo) {
		int cnt = applMapper.updateApplicantPass(vo);
		
		if(cnt == 0) {
			throw new DataUpdateException("데이터 수정에 실패했습니다");
		}else {
			RecruitProcessVO processVo = new RecruitProcessVO();
			processVo.setRecruitmentNo(vo.getRecruitmentNo());
			int step = Integer.parseInt(vo.getRecruitProcessStep())+1;
			String formatStep = Integer.toString(step);
			processVo.setRecruitProcessStep(formatStep);
			processVo = processMapper.selectNextStep(processVo);
			
			String email = applicantMapper.selectApplicantMail(vo.getApplicantId());
			
			if(vo.getRecruitProcessFinal().equals("N")) {				
				ApplicantRecordVO applVo = new ApplicantRecordVO();
				applVo.setRecruitProcessNo(processVo.getRecruitProcessNo());
				applVo.setApplicantId(vo.getApplicantId());
				applVo.setApplicantName(vo.getApplicantName());
				if(applMapper.selectDuplicateRecord(applVo)==null) {					
					applMapper.insertApplicantRecord(applVo);
				}
				
			try {
				String subject = String.format("[%s차 전형] 합격을 축하드립니다", vo.getRecruitProcessStep());
				String body = vo.getApplicantName() + "님, " + subject + "!\n다음 단계도 잘 준비해 주세요";
				emailUtils.sendEmail(email, subject, body);
			} catch(MessagingException e) {
				log.warn("중간 합격자 메일 전송 실패 : {}", email, e);
			}
				
			}else {
				PasserVO pass = new PasserVO();
				pass.setApplicantId(vo.getApplicantId());
				pass.setRecruitmentNo(vo.getRecruitmentNo());
				
				if(passMapper.selectDuplicatePasser(pass)==null) {					
					passMapper.insertPasser(pass);
				}
				
				try {
					String subject = "최종 합격을 진심으로 축하드립니다!";
					String body = vo.getApplicantName() + "님, 최종 합격하셨습니다.\n입사 관련 내용을 확인해 주세요";
					emailUtils.sendEmail(email, subject, body);
					
					passMapper.updateAlarm(pass.getPasserNo());
				} catch (MessagingException e) {
					log.warn("최종 합격자 메일 전송 실패: {}", email, e);
				}
			}
			
		}
	}

	@Override
	public List<PasserVO> selectPasserByRecruitment(String recruitmentNo) {
		return passMapper.selectpasserByRecruit(recruitmentNo);
	}

	@Override
	@Transactional
	public List<ResumeVO> getResumeByApplicantId(List<String> applicantIds) {
		return applicantIds.stream()
			.map(id ->{
				ApplicantVO applicant = applicantMapper.selectApplicant(id);
				String resumeNo = applicant.getResumeNo();
				String userId = applicant.getUserId();
				ResumeVO inputResume = new ResumeVO();
				inputResume.setResumeNo(resumeNo);
				inputResume.setUserId(userId);
				ResumeVO detailResume = resumeService.readResumeDetail(inputResume);
				detailResume.setApplicantId(id);
				return detailResume;
			})
			.collect(Collectors.toList());
	}

	@Override
	public void updateResumeView(String applicantId) {
		applicantMapper.updateApplicant(applicantId);
	}

	@Override
	public void updateHireDate(PasserVO vo) {
		passMapper.updatePasser(vo);
		
	}

}
