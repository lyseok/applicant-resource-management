package kr.or.ddit.member.recruitment.applicant.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.PassIntroductionMapper;
import kr.or.ddit.mapper.recruitment.PasserMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MemberApplicantServiceImpl implements MemberApplicantService {

	private final ApplicantMapper AlMapper;
	private final RecruitmentNoticeMapper RnMapper ;
	private final ResumeMapper RMapper;
	private final MemberMapper MMapper;
	private final ApplicantRecordMapper applRecMapper;
	private final CodeMapProvider codeMapProvider;
	private final PasserMapper passMapper;
	private final PassIntroductionMapper passIntroductionMapper;
	
	
	@Override
	public List<ApplicantVO> selectApplicantList() {
		
		return AlMapper.selectApplicantList();
	}

	@Override
	public ApplicantVO selectApplicant(String applicantId) {
		
		return AlMapper.selectApplicant(applicantId);
	}

	@Override
	public int insertApplicant(ApplicantVO vo) {
		
		return AlMapper.insertApplicant(vo);
	}

	@Override
	public int updateApplicant(ApplicantVO vo) {
		
//		return AlMapper.updateApplicant(vo);
		return 0;
	}

	@Override
	public int deleteApplicant(String applicantId) {
		
		return AlMapper.deleteApplicant(applicantId);
	}

	@Override
	public List<Map<String, Object>> readApplicatedList() {
		
		List<Map<String, Object>> notices = RnMapper.selectApplicatedNotice(getUserId());
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

	@Override
	public List<Map<String, Object>> readMyApplicatedStep() {
		
		return applRecMapper.selectMyApplicatedStep(getUserId());
	}
	@Transactional
	@Override
	public int updateAccept(PasserVO vo) {
		int updated = passMapper.updateAccept(vo);
		
		if(updated > 0) {
			passIntroductionMapper.insertPassIntroductionForAcceptedPasser(vo.getApplicantId(), vo.getRecruitmentNo());
		}
		return updated;
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

}
