package kr.or.ddit.member.recruitment.applicant.service;

import java.util.List;

import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;

public interface MemberApplicantService {
	public List<ApplicantMapper> selectApplicantList();
	public ApplicantVO selectApplicant(String applicantId);
	public int insertApplicant(ApplicantVO vo);
	public int updateApplicant(ApplicantVO vo);
	public int deleteApplicant(String applicantId);
}
