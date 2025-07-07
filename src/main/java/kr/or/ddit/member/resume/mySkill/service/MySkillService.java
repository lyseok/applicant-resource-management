package kr.or.ddit.member.resume.mySkill.service;

import java.util.List;

import kr.or.ddit.vo.resume.MySkillVO;

public interface MySkillService {
	// 목록 조회
	public List<MySkillVO> readMySkillList();
	// 단건 조회
	public MySkillVO readMySkillDetail(String no);
	// 등록
	public void createMySkill(MySkillVO vo);
	// 수정
	public void editMySkill(MySkillVO vo);
	// 삭제
	public void removeMySkill(String no);
}
