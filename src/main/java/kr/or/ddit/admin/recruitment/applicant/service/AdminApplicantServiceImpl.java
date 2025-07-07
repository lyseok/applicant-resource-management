package kr.or.ddit.admin.recruitment.applicant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminApplicantServiceImpl implements AdminApplicantService {

	private final ApplicantMapper AlMapper;
	private final RecruitmentNoticeMapper RnMapper ;
	private final ResumeMapper RMapper;
	private final MemberMapper MMapper;
	
	
	@Override
	public List<ApplicantMapper> selectApplicantList() {
		
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
		
		return AlMapper.updateApplicant(vo);
	}

	@Override
	public int deleteApplicant(String applicantId) {
		
		return AlMapper.deleteApplicant(applicantId);
	}

}
