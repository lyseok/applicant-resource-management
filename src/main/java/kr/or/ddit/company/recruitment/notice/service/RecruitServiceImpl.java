package kr.or.ddit.company.recruitment.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.company.recruitment.exam.service.RecruitExamService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentEducationMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentPositionMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentSkillmapper;
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
	private final CodeMapProvider codeMapProvider;

	@Override
	@Transactional
	public void createRecruitment(RecruitmentNoticeVO recruit) {
		recruit.setUserId(getUserId());
		noticeMapper.insertRecruitmentNotice(recruit);
		
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
						examService.copyCompanyExamToRecruit(processNo, recruit.getComExamNo(), exam);
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
	}

	@Override
	public List<Map<String, Object>> readMyNotice(String userId) {
		List<Map<String, Object>> notices = (List<Map<String, Object>>) noticeMapper.selectMyRecruitNotice(userId);
		for(Map<String, Object> notice : notices) {			
			notice.put("jobCodeName", codeMapProvider.getJobName((String) notice.get("JOBCODE")));
			notice.put("cityCodeName", codeMapProvider.getCityName((String) notice.get("CITYCODE")));
			notice.put("yearCodeName", codeMapProvider.getCodeName((String) notice.get("YEARCODE")));
			notice.put("districtCodeName", codeMapProvider.getDistrictName((String) notice.get("DISTRICTCODE")));
		}
		return notices;
		
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}

}
