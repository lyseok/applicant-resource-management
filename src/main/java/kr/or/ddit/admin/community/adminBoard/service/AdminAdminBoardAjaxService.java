package kr.or.ddit.admin.community.adminBoard.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.community.AdminBoardVO;

public interface AdminAdminBoardAjaxService {

	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo);
	
	public List<AdminBoardVO> readDelAboardList();
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode);
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode, String userRole);
	public List<AdminBoardVO> readAdminBoardList();
	
	public List<CmnCodeVO> readCmnList(String codeGroupNo);
	public List<CmnCodeGroupVO> readCmnGroupList(String upperCodeNo);
	public List<AdminBoardVO> readAFaqListByCgn(String groupPrefix);	 //FAQ 회원별 게시글 목록조회
	public List<AdminBoardVO> readAFaqListByUcn(String upperCodeNo);	 //FAQ 전체 게시글 목록조회
	public String readBoardTypeName(String boardTypeCode);

	public MemberVO readMemName(String userId);
	public CompanyVO readComName(String userId);
	
	public void createAdminBoard(AdminBoardVO board);
	public void modifyAdminBoard(AdminBoardVO board);
	public void addABoardPostHit(AdminBoardVO board);  //조회수 증가
	public void hiddenAdminBoard(AdminBoardVO board);  //삭제 대체 쿼리
	public void removeAdminBoard(String boardNo);
}
