package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.CommuCommentVO;

public interface CommuCommentService {

	public Optional<CommuCommentVO> readCommuComment(String commuCommentNo);
	public List<CommuCommentVO> readCommuCommentList(String commuPostNo);
	public void createCommuComment(CommuCommentVO comment);
	public void modifyCommuComment(CommuCommentVO comment);
	public void removeCommuComment(String commuCommentNo);
}
