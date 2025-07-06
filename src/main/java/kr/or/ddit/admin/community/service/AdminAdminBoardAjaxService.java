package kr.or.ddit.admin.community.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.AdminBoardVO;

public interface AdminAdminBoardAjaxService {

	public Optional<AdminBoardVO> readAdminBoard(String boardNo);
	public List<AdminBoardVO> readAdminBoardList(String boardTypeCode);
	public void createAdminBoard(AdminBoardVO board);
	public void modifyAdminBoard(AdminBoardVO board);
	public void removeAdminBoard(String boardNo);
}
