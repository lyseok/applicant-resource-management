package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.ComReviewTagVO;

@Mapper
public interface ComReviewTagMapper {
	public ComReviewTagVO selectComReviewTagByNo(String tagNo);
	public List<ComReviewTagVO> selectComReviewTagList();
	public int insertComReviewTag(ComReviewTagVO comReviewTag);
	public int updateComReviewTag(ComReviewTagVO comReviewTag);
	public int deleteComReviewTag(ComReviewTagVO comReviewTag);
}
