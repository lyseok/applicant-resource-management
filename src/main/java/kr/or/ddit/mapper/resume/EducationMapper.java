package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.EducationVO;

@Mapper
public interface EducationMapper {
	// 목록 조회
	public List<EducationVO> selectEducationList(String no);
	// 단건 조회
	public EducationVO selectEducationDetail(EducationVO vo);
	// 등록
	public int insertEducation(EducationVO vo);
	// 수정
	public int updateEducation(EducationVO vo);
	// 삭제
	public int deleteEducation(EducationVO vo);
	
}
