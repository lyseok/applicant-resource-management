package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.SpecialtyVO;

@Mapper
public interface SpecialtyMapper {
	// 목록 조회
	public List<SpecialtyVO> selectSpecialtyList();
	// 단건 조회
	public SpecialtyVO selectSpecialtyDetail(SpecialtyVO vo);
	// 등록
	public int insertSpecialty(SpecialtyVO vo);
	// 수정
	public int updateSpecialty(SpecialtyVO vo);
	// 삭제
	public int deleteSpecialty(SpecialtyVO vo);	
}
