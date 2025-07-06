package kr.or.ddit.recruitment.applicant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentNoticeMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

	@Override
	public List<ApplicantMapper> selectApplicantList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicantVO selectApplicant(String applicantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertApplicant(ApplicantVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateApplicant(ApplicantVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteApplicant(String applicantId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
