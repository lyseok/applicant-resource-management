package kr.or.ddit.admin.community.adminComment.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.AdminBoardVO;
import kr.or.ddit.vo.community.AdminCommentVO;


public interface AdminAdminCommentAjaxService {
	
	public Optional<AdminCommentVO> readAdminCommentbyPk(String commentNo);
	public List<AdminCommentVO> searchAdminCommentCommentList(String boardNo);  //해당 문의게시글에 대한 관리자답글 모두
	public List<AdminCommentVO> searchAdminCommentList();  //문의게시판 관리자답글 모두
	public void createAdminComment(AdminCommentVO comment);
	public void modifyAdminComment(AdminCommentVO comment);
	public void hiddenAdminComment(AdminCommentVO comment);
	public void removeAdminComment(String commentNo);
}
