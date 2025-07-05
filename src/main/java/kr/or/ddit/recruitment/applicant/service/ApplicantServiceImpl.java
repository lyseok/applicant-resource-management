package kr.or.ddit.recruitment.applicant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.resume.ResumeMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

	private final ResumeMapper resMapper;
	private final ApplicantMapper appMapper;

	
	@Override
	public List<ApplicantVO> selectApplicantList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicantVO readApplicant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyApplicant() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeApplicant() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerApplicant(ResumeVO rvo, RecruitmentNoticeVO rnv) {
		// TODO Auto-generated method stub

	}

}
