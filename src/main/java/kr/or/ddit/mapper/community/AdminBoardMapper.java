package kr.or.ddit.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.community.AdminBoardVO;
import kr.or.ddit.vo.resume.ResumeVO;

@Mapper
public interface AdminBoardMapper {
	
	public AdminBoardVO selectAdminBoardByPk(String boardNo);  //게시글 단건조회
	public List<AdminBoardVO> selectDelAboardList();	 //삭제된 게시글 목록조회
	public List<AdminBoardVO> selectAdminBoardListByType(String boardTypeCode);	 //유형별 게시글 목록조회, FAQ는 문항별 게시글 목록조회
	public List<AdminBoardVO> selectAdminBoardListByTypeAndUserRole(
		    @Param("boardTypeCode") String boardTypeCode,
		    @Param("userRole") String userRole
		);
	
	public List<AdminBoardVO> selectAdminBoardWithComments(Map<String, Object> params);
	public List<AdminBoardVO> selectAboardByFilter(Map<String, Object> params);
	public int selectAboardCountByFilter(Map<String, Object> params);
	
	// 공지사항용 페이징 처리
	public List<AdminBoardVO> selectNoticeList(String boardTypeCode);
	public List<AdminBoardVO> selectNotice(Map<String, Object> params);
	public int selectCountNotice(Map<String, Object> params);
	
	// 기본 페이징 처리
	public List<AdminBoardVO> selectAdminBoard(Map<String, Object> params);
	public int selectCountAdminBoard(Map<String, Object> params);
	
	public List<CmnCodeVO> selectCmnList(String codeGroupNo);
	public List<CmnCodeGroupVO> selectCmnGroupList(String upperCodeNo);
	
	public List<AdminBoardVO> selectAFaqListByCgn(Map<String, Object> paramMap);	 //FAQ 회원별 게시글 목록조회
	public List<AdminBoardVO> selectAFaqListByUcn(String upperCodeNo);	 //FAQ 전체 게시글 목록조회
	public List<AdminBoardVO> selectAdminBoardList();  //전체 게시글 목록조회
	
	public String selectBoardTypeName(String boardTypeCode);  //유형이름 출력시 매핑용
	
	public int insertAdminBoard(AdminBoardVO board);
	public int updateAdminBoard(AdminBoardVO board);
	public int updateABoardPostHit(AdminBoardVO board);
	public int upDeleteAdminBoard(AdminBoardVO board);  //상태를 삭제로 바꿈
	
	public int deleteAdminBoard(String boardNo);
}
