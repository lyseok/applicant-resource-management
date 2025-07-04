package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.LanguageSkillVO;

@Mapper
public interface LanguageSkillMapper {
	// 목록 조회
	public List<LanguageSkillVO> selectLanguageSkillList();
	// 단건 조회
	public LanguageSkillVO selectLanguageSkillDetail(String skillNo);
	// 등록
	public int insertLanguageSkill(LanguageSkillVO skillVO);
	// 수정
	public int updateLanguageSkill(LanguageSkillVO skillVO);
	// 삭제
	public int deleteLanguageSkill(String skillNo);
}
