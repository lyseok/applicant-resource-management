package kr.or.ddit.admin.community.adminBoard.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kr.or.ddit.vo.community.AdminBoardVO;

public interface AdminAdminBoardAjaxService {

	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo);
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode);
	public List<AdminBoardVO> readAFaqListByCgn(String groupPrefix);	 //FAQ 회원별 게시글 목록조회
	public List<AdminBoardVO> readAFaqListByUcn(String upperCodeNo);	 //FAQ 전체 게시글 목록조회
	public List<AdminBoardVO> readAdminBoardList();
	public void createAdminBoard(AdminBoardVO board);  //selectKey로 만들어진 boardNo가 반환돼야 뷰에서 사용
	public void modifyAdminBoard(AdminBoardVO board);
	public void removeAdminBoard(String boardNo);
}
