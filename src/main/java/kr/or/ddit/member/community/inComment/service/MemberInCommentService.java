package kr.or.ddit.member.community.inComment.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.InCommentVO;

public interface MemberInCommentService {

	public Optional<InCommentVO> readInCommentByPk(String commentNo, String avatarId);
	public List<InCommentVO> searchInCommentCommentList(String avatarId);  //해당 아바타가 좋아한 댓글들
	public List<InCommentVO> searchInCommentAvatarList(String commentNo);  //해당 댓글을 좋아한 아바타들
	public List<InCommentVO> readInCommentList();  //댓글과 아바타 전체
	public void createInComment(InCommentVO comment);
	public void removeInComment(String commentNo, String avatarId);
}
