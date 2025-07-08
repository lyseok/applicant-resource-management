package kr.or.ddit.member.resume.resume.service;

import java.util.List;

import kr.or.ddit.vo.resume.ResumeVO;

public interface ResumeService {
	// 목록 조회
	public List<ResumeVO> readResumeList(ResumeVO vo);
	// 단건 조회
	public ResumeVO readResumeDetail(String no);
	// 등록
	public void createResume(ResumeVO vo);
	// 수정
	public void editResume(ResumeVO vo);
	// 삭제
	public void removeResume(String no);
	
	// 테스트 조회
	public List<ResumeVO> readMemberList(String userId);
}
