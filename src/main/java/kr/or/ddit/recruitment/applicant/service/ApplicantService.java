package kr.or.ddit.recruitment.applicant.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.resume.ResumeVO;

public interface ApplicantService {

	public List<ApplicantVO> selectApplicantList();
	
	public ApplicantVO readApplicant();
	
	public void modifyApplicant();
	
	public void removeApplicant();
	
	public void registerApplicant(ResumeVO rvo, RecruitmentNoticeVO rnv);
	
}
