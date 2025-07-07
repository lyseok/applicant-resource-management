package kr.or.ddit.member.community.commuComment.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.CommuCommentVO;

public interface MemberCommuCommentService {

	public Optional<CommuCommentVO> readCommuCommentbyPk(String commuCommentNo);
	public List<CommuCommentVO> searchCommuCommentPostList(String commuPostNo);
	public List<CommuCommentVO> searchCommuCommentList();
	public void createCommuComment(CommuCommentVO comment);
	public void modifyCommuComment(CommuCommentVO comment);
	public void removeCommuComment(String commuCommentNo);
}
