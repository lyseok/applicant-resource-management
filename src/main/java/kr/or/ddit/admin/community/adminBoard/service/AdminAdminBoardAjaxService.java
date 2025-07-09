package kr.or.ddit.admin.community.adminBoard.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.community.AdminBoardVO;

public interface AdminAdminBoardAjaxService {

	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo);
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode);
	public List<AdminBoardVO> readAdminBoardList();
	public List<CmnCodeVO> matchBoardTypeCode(String boardTypeCode);
	public void createAdminBoard(AdminBoardVO board);
	public void modifyAdminBoard(AdminBoardVO board);
	public void removeAdminBoard(String boardNo);
}
