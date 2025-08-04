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
	// 게시글 상세 단건조회
	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo);
	
	// 목록 조회
	public List<AdminBoardVO> readDelAboardList();
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode);
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode, String userRole);
	public List<AdminBoardVO> readAdminBoardList();
	
	public Map<String, Object> readAboardWithComments(Map<String, Object> params);  //답글여부 조회
	public Map<String, Object> readAboardPage(Map<String, Object> params);  //페이징 처리
	public Map<String, Object> readAboardByFilter(Map<String, Object> params);  //검색
	
	// 공지사항용 페이징 처리
	public List<AdminBoardVO> readNoticeList(String boardTypeCode);
	public Map<String, Object> readNotice(Map<String, Object> params);
	
	// 공통 코드 이용한 목록 조회
	public List<CmnCodeVO> readCmnList(String codeGroupNo);
	public List<CmnCodeGroupVO> readCmnGroupList(String upperCodeNo);
	public List<AdminBoardVO> readAFaqListByCgn(String groupPrefix);	 //FAQ 회원별 게시글 목록조회
	public List<AdminBoardVO> readAFaqListByUcn(String upperCodeNo);	 //FAQ 전체 게시글 목록조회
	public String readBoardTypeName(String boardTypeCode);

	// 작성자 이름 가져오기
	public MemberVO readMemName(String userId);
	public CompanyVO readComName(String userId);
	
	// 등록, 수정, 삭제
	public void createAdminBoard(AdminBoardVO board);
	public void modifyAdminBoard(AdminBoardVO board);
	public void addABoardPostHit(AdminBoardVO board);  //조회수 증가
	public void hiddenAdminBoard(AdminBoardVO board);  //삭제 대체 쿼리
	public void removeAdminBoard(String boardNo);
}
