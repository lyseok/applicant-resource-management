package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MySkillVO;

@Mapper
public interface MySkillMapper {
	// 목록 조회
	public List<MySkillVO> selectMySkillList();
	// 단건 조회
	public MySkillVO selectMySkillDetail(String no);
	// 등록
	public int insertMySkill(MySkillVO vo);
	// 수정
	public int updateMySkill(MySkillVO vo);
	// 삭제
	public int deleteMySkill(String no);
}
