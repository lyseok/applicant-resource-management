package kr.or.ddit.ajax.community.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.AdminCommentVO;


public interface AdminCommentService {
	
	public Optional<AdminCommentVO> readAdminComment(String commentNo);
	public List<AdminCommentVO> readAdminCommentList(String boardNo);
	public void createAdminComment(AdminCommentVO comment);
	public void modifyAdminComment(AdminCommentVO comment);
	public void removeAdminComment(String commentNo);
}
