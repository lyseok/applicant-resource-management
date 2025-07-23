package kr.or.ddit.member.recruitment.applicant.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface MemberApplicantService {
	public List<ApplicantVO> selectApplicantList();
	public List<Map<String, Object>> readApplicatedList();
	public List<Map<String, Object>> readMyApplicatedStep();
	public ApplicantVO selectApplicant(String applicantId);
	public int insertApplicant(ApplicantVO vo);
	public int updateApplicant(ApplicantVO vo);
	public int updateAccept(PasserVO vo);
	public int deleteApplicant(String applicantId);
}
