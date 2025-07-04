package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CommuCommentVO;

@Mapper
public interface CommuCommentMapper {

	public CommuCommentVO selectCommuComment(String commuCommentNo);
	public List<CommuCommentVO> selectCommuCommentList(String commuPostNo);
	public int insertCommuComment(CommuCommentVO comment);
	public int updateCommuComment(CommuCommentVO comment);
	public int deleteCommuComment(String commuCommentNo);
}
