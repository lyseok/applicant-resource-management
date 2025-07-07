package kr.or.ddit.member.community.adminBoard.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.AdminBoardVO;

public interface MemberAdminBoardAjaxService {

	public Optional<AdminBoardVO> readAdminBoard(String boardNo);
	public List<AdminBoardVO> readAdminBoardList(String boardTypeCode);
	public void createAdminBoard(AdminBoardVO board);
	public void modifyAdminBoard(AdminBoardVO board);
	public void removeAdminBoard(String boardNo);
}
