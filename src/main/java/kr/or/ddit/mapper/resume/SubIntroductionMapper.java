package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.SubIntroductionVO;

@Mapper
public interface SubIntroductionMapper {
	// 전체 리스트 조회
	public List<SubIntroductionVO> selectSubIntroductionList();
	// 단건 조회
	public SubIntroductionVO selectSubIntroductionDetail(String subIntroductionNo);
	// 등록
	public int insertSubIntroduction(SubIntroductionVO subIntroductionVO);
	// 수정
	public int updateSubIntroduction(SubIntroductionVO subIntroductionVO);
	// 삭제
	public int deleteSubIntroduction(String subIntroductionNo);
}
