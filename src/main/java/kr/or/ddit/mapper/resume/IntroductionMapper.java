package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.IntroductionVO;

@Mapper
public interface IntroductionMapper {
	// 리스트 조회
	public List<IntroductionVO> selectIntroductionList(String userId);
	// 단건 조회
	public IntroductionVO selectIntroductionDetail(IntroductionVO introductionVO);
	// 등록 조회
	public int insertIntroduction(IntroductionVO introductionVO);
	// 수정 조회
	public int updateIntroduction(IntroductionVO introductionVO);
	// 삭제 조회
	public int deleteIntroduction(IntroductionVO introductionVO);
	
}