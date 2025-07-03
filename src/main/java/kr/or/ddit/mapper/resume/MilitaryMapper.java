package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MilitaryVO;

@Mapper
public interface MilitaryMapper {
	// 목록 조회
	public List<MilitaryVO> seletMilitaryList();
	// 단건 조회
	public MilitaryVO seletMilitaryDetail(String militaryNo);
	// 등록
	public int insertMilitary(MilitaryVO militaryVO);
	// 수정
	public int updateMilitary(MilitaryVO militaryVO);
	// 삭제
	public int deleteMilitary(String militaryNo);
}
