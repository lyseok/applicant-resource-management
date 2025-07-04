package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.ComReviewTagVO;

@Mapper
public interface ComReviewTagMapper {
	public ComReviewTagVO selectComReviewTagByNo(ComReviewTagVO comReviewTagVO);
	public List<ComReviewTagVO> selectComReviewTagList();
	public List<ComReviewTagVO> searchComReviewTagList(String comReviewNO);
	public int insertComReviewTag(ComReviewTagVO comReviewTag);
	public int updateComReviewTag(ComReviewTagVO comReviewTag);	
}
