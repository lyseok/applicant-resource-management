package kr.or.ddit.company.recruitment.notice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.common.file.S3Uploader;
import kr.or.ddit.common.file.service.FileService;
import kr.or.ddit.company.recruitment.exam.service.RecruitExamService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.FileMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentEducationMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentPositionMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentSkillmapper;
import kr.or.ddit.vo.common.FilesVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

	private final RecruitmentNoticeMapper noticeMapper;
	private final RecruitmentPositionMapper positionMapper;
	private final RecruitmentEducationMapper eduMapper;
	private final RecruitmentSkillmapper skillMapper;
	private final RecruitProcessMapper processMapper;
	private final InterviewMapper interviewMapper;
	private final RecruitExamService examService;
	private final CompanyMapper comMapper;
	private final CodeMapProvider codeMapProvider;
	private final FileService fileService;
	private final S3Uploader s3Uploader;
	
	private final UserMapper userMapper;
	private final MemberMapper memMapper;
	private final ApplicantMapper applMapper;
	private final ApplicantRecordMapper applRecordMapper;

	@Override
	@Transactional
	public void createRecruitment(RecruitmentNoticeVO recruit) {
		recruit.setUserId(getUserId());
		recruit.setCompany(comMapper.selectCompanyById(getUserId())); 
		MultipartFile file = recruit.getRecruitThumbnail();
		if (file != null && !file.isEmpty()) {
			try {
				String thumbnail = s3Uploader.upload(file);
				recruit.setRecruitmentImg(thumbnail);
			} catch (IOException e) {
				throw new RuntimeException("썸네일 업로드 실패", e); // ❗️ TODO 대신 안전한 예외처리
			}
		}
		noticeMapper.insertRecruitmentNotice(recruit);
		
		
		if (recruit.getFileList() != null && !recruit.getFileList().isEmpty()) {
		    List<String> filePaths = recruit.getFileList().stream()
		        .map(FilesVO::getFilePath)
		        .collect(Collectors.toList());

		    fileService.updateFilesWithOrder(
		        String.valueOf(recruit.getRecruitmentNo()),
		        filePaths
		    );
		}
		
		if(recruit.getPositionList() != null) {
			for(RecruitmentPositionVO position : recruit.getPositionList()) {
				position.setRecruitmentNo(recruit.getRecruitmentNo());
				positionMapper.insertRecruitmentPositon(position);
			}
		}
		
		if(recruit.getEducation() != null) {
			recruit.getEducation().setRecruitmentNo(recruit.getRecruitmentNo());
			eduMapper.insertRecruitmentEducation(recruit.getEducation());
		}
		
		if(recruit.getSkillList() != null) {
			for(RecruitmentSkillVO skill : recruit.getSkillList()) {
				skill.setRecruitmentNo(recruit.getRecruitmentNo());
				skillMapper.insertRecruitmetnSkill(skill);
			}
		}
		
		if(recruit.getProcessList() != null) {
			for(RecruitProcessVO process : recruit.getProcessList()) {
				process.setRecruitmentNo(recruit.getRecruitmentNo());
				process.setCompanyName(recruit.getCompany().getComName());
				processMapper.insertRecruitProcess(process);
				String processNo = process.getRecruitProcessNo();
				
				if(process.getInterviewList() != null) {
					for(InterviewVO interview : process.getInterviewList()) {
						interview.setProcessNo(processNo);
						interviewMapper.insertInterview(interview);
					}
				}
				if(process.getRecruitmentExamList() != null) {
					for(RecruitmentExamVO exam : process.getRecruitmentExamList()) {
						examService.copyCompanyExamToRecruit(processNo, exam);
					}
				}
				
			}
		}
		
	}

	@Override
	public void modifyRecruitment(RecruitmentNoticeVO recruit) {
		noticeMapper.updateRecruitmentNotice(recruit);

	}

	@Override
	public void deleteRecruitment(String recruimentNo) {
		noticeMapper.deleteRecruitmentNotice(recruimentNo);

	}

	@Override
	public List<RecruitmentNoticeVO> readRecruitList() {
		List<RecruitmentNoticeVO> notiList = noticeMapper.readRecruitmentNoticeList();
		for(RecruitmentNoticeVO notiVo : notiList) {
			setCodeName(notiVo);
		}
		return notiList;
	}

	@Override
	public RecruitmentNoticeVO readRecruitNotice(String recruitNo) {
		RecruitmentNoticeVO notiVo = noticeMapper.selectliveRecruitmentDetail(recruitNo);
		setCodeName(notiVo);
		return notiVo;
	}
	
	private void setCodeName(RecruitmentNoticeVO notiVo) {
		List<RecruitmentPositionVO> positionList = notiVo.getPositionList();
		if(notiVo.getPositionList() != null) {
			for(RecruitmentPositionVO position : positionList) {
				String cmnCode = codeMapProvider.getCodeName(position.getCodeDetailNo()); 
				position.setCodeDetailName(cmnCode);
			}			
		}
		RecruitmentEducationVO education = notiVo.getEducation();
		if(notiVo.getEducation() != null) {			
			String cmnCode = codeMapProvider.getCodeName(education.getCodeDetailNo());
			education.setCodeDetailName(cmnCode);
		}
		
		String district = codeMapProvider.getDistrictName(notiVo.getDistrictCode());
		notiVo.setDistrictCodeName(district);
		
		String job = codeMapProvider.getJobName(notiVo.getJobCode());
		notiVo.setJobCodeName(job);
		
		String city = codeMapProvider.getCityName(notiVo.getCityCode());
		notiVo.setCityCodeName(city);
		
		String year = codeMapProvider.getCodeName(notiVo.getYearCode());
		notiVo.setYearCodeName(year);
		
		String indu = codeMapProvider.getInduName(notiVo.getCompany().getIndustryType());
		notiVo.getCompany().setInduName(indu);		
	}

	@Override
	public List<Map<String, Object>> readMyNotice() {
		List<Map<String, Object>> notices = (List<Map<String, Object>>) noticeMapper.selectMyRecruitNotice(getUserId());
		for(Map<String, Object> notice : notices) {			
			notice.put("jobCodeName", codeMapProvider.getJobName((String) notice.get("JOBCODE")));
			notice.put("cityCodeName", codeMapProvider.getCityName((String) notice.get("CITYCODE")));
			notice.put("yearCodeName", codeMapProvider.getCodeName((String) notice.get("YEARCODE")));
			notice.put("districtCodeName", codeMapProvider.getDistrictName((String) notice.get("DISTRICTCODE")));
		}
		return notices;
		
	}
	
	@Override
	public UsersVO searchUser() {
		return userMapper.selectUserById(getUserId());
	}

	@Transactional
	@Override
	public void setDeadLine(String recruitmentNo) {
		int cnt = noticeMapper.updateRecruitDeadLine(recruitmentNo);
		if(cnt == 0) {
			throw new DataUpdateException("마감 업데이트에 실패했습니다.");
		}else {
			List<ApplicantVO> list = applMapper.selectApplicantListByNo(recruitmentNo);
			RecruitProcessVO process= processMapper.selectProcessByRecruit(recruitmentNo);
			
			for(ApplicantVO appl : list) {
				MemberVO member = memMapper.selectMemberById(appl.getUserId());
				ApplicantRecordVO rec = new ApplicantRecordVO();
				rec.setApplicantId(appl.getApplicantId());
				rec.setRecruitProcessNo(process.getRecruitProcessNo());
				rec.setApplicantName(member.getMemName());
				applRecordMapper.insertApplicantRecord(rec);
			}
		}
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}


}
