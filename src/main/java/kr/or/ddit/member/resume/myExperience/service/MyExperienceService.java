package kr.or.ddit.member.resume.myExperience.service;

import java.util.List;

import kr.or.ddit.vo.resume.MyExperienceVO;

public interface MyExperienceService {
	// 목록 조회
	public List<MyExperienceVO> readMyExperienceList();
	// 단건 조회
	public MyExperienceVO readMyExperienceDetail(String no);
	// 등록
	public void createMyExperience(MyExperienceVO vo);
	// 수정
	public void editMyExperience(MyExperienceVO vo);
	// 삭제
	public void removeMyExperience(String no);
}
