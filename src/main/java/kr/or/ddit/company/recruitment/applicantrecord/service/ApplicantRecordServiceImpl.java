package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@RequiredArgsConstructor
public class ApplicantRecordServiceImpl implements ApplicantRecordService {
	
	private final ApplicantRecordMapper applMapper;
	private final ApplicantMapper applicantMapper;
	private final RecruitProcessMapper processMapper;
	private final PasserMapper passMapper;
	private final ResumeService resumeService;

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
			
			if(vo.getRecruitProcessFinal().equals("N")) {				
				ApplicantRecordVO applVo = new ApplicantRecordVO();
				applVo.setRecruitProcessNo(processVo.getRecruitProcessNo());
				applVo.setApplicantId(vo.getApplicantId());
				applVo.setApplicantName(vo.getApplicantName());
				if(applMapper.selectDuplicateRecord(applVo)==null) {					
					applMapper.insertApplicantRecord(applVo);
				}else {
					return;
				}
			}else {
				PasserVO pass = new PasserVO();
				pass.setApplicantId(vo.getApplicantId());
				pass.setRecruitmentNo(vo.getRecruitmentNo());
				if(passMapper.selectDuplicatePasser(pass)==null) {					
					passMapper.insertPasser(pass);
				}else {
					return;
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
//				detailResume.setApplicantId(id);
				return detailResume;
			})
			.collect(Collectors.toList());
	}

}
