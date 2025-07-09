package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MilitaryVO;

@Mapper
public interface MilitaryMapper {
	// 목록 조회
	public List<MilitaryVO> selectMilitaryList(String no);
	// 단건 조회
	public MilitaryVO selectMilitaryDetail(MilitaryVO vo);
	// 등록
	public int insertMilitary(MilitaryVO vo);
	// 수정
	public int updateMilitary(MilitaryVO vo);
	// 삭제
	public int deleteMilitary(String vo);
}
