package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.MyExperienceVO;

@Mapper
public interface MyExperienceMapper {
	// 목록 조회
	public List<MyExperienceVO> seletMyExperienceList();
	// 단건 조회
	public MyExperienceVO seletMyExperienceDetail(String expNo);
	// 등록
	public int insertMyExperience(MyExperienceVO expVo);
	// 수정
	public int updateMyExperience(MyExperienceVO expVo);
	// 삭제
	public int deleteMyExperience(String expNo);
}
