package kr.or.ddit.mapper.resume;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.resume.IntroductionVO;

@Mapper
public interface IntroductionMapper {
	// 리스트 조회
	public List<IntroductionVO> selectIntroductionList(String userId);
	// 단건 조회
	public IntroductionVO selectIntroductionDetail(String no);
	// 등록
	public int insertIntroduction(IntroductionVO introductionVO);
	// 수정
	public int updateIntroduction(IntroductionVO introductionVO);
	// 삭제
	public int deleteIntroduction(IntroductionVO introductionVO);
	// 검색 조회
	public List<IntroductionVO> selectIntroductionSearch(String name);
    
	// 삭제 조회
	// public int deleteIntroduction(IntroductionVO introductionVO);
	
	
	
	// 페이징
	// 전체 카운트 조회
	public int selectIntroductionCount(String userId);
	// 페이징 처리된 리스트 조회
	public List<IntroductionVO> selectIntroductionPagingList(
	    @Param("userId") String userId,
	    @Param("offset") int offset,
	    @Param("limit") int limit
	);
	
}