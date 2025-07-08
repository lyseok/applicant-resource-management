package kr.or.ddit.company.recruitment.applicant.service;

import java.util.List;

import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;

public interface CompanyApplicantService {
	public List<ApplicantVO> selectApplicantList();
	public ApplicantVO selectApplicant(String applicantId);
	public int insertApplicant(ApplicantVO vo);
	public int updateApplicant(ApplicantVO vo);
	public int deleteApplicant(String applicantId);
}
