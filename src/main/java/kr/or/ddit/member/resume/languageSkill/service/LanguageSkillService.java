package kr.or.ddit.member.resume.languageSkill.service;

import java.util.List;

import kr.or.ddit.vo.resume.LanguageSkillVO;

public interface LanguageSkillService {
	// 목록 조회
	public List<LanguageSkillVO> readLanguageSkillList();
	// 단건 조회
	public LanguageSkillVO readLanguageSkillDetail(String no);
	// 등록
	public void createLanguageSkill(LanguageSkillVO vo);
	// 수정
	public void editLanguageSkill(LanguageSkillVO vo);
	// 삭제
	public void removeLanguageSkill(String no);
}
