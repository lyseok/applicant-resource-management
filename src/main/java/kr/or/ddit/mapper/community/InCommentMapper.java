package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.InCommentVO;

@Mapper
public interface InCommentMapper {

	public InCommentVO selectInComment(String commentNo, String avatarId);  //해당 아바타가 좋아한 해당 댓글 하나
	public List<InCommentVO> selectInCommentList(String avatarId);  //해당 아바타가 좋아한 댓글들
	public List<InCommentVO> selectInCommentAvatarList(String commentNo);  //해당 댓글을 좋아한 아바타들
	public int insertInComment(InCommentVO Comment);
	public int deleteInComment(String commentNo, String avatarId);
}
