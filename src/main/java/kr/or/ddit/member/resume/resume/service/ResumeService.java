package kr.or.ddit.member.resume.resume.service;

import java.util.List;

import kr.or.ddit.vo.project.PrjAplcntVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.resume.ResumeVO;

public interface ResumeService {
	// 목록 조회
	public List<ResumeVO> readResumeList(String no);
	// 단건 조회
	public ResumeVO readResumeDetail(ResumeVO vo);
	// 등록
	public int createResume(ResumeVO vo);
	// 수정
	public int editResume(ResumeVO vo);
	// 논리 삭제
	public int editResumeRemove(ResumeVO vo);
	// 삭제
	public int removeResume(String no);
	
	// 지원 (이력서 복사)
	public void applicantCopyLogic(PrjAplcntVO prjAplcnt);
	
	// 입사 지원(이력서 복사)
	public void recruitApplicate(ApplicantVO applicant);
	
}
