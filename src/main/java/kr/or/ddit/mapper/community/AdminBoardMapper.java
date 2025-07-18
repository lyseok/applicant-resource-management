package kr.or.ddit.mapper.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.community.AdminBoardVO;

@Mapper
public interface AdminBoardMapper {
	
	public AdminBoardVO selectAdminBoardByPk(String boardNo);  //게시글 단건조회
	public List<AdminBoardVO> selectAdminBoardListByType(String boardTypeCode);	 //유형별 게시글 목록조회, FAQ는 문항별 게시글 목록조회
	public List<CmnCodeVO> selectCmnList(String codeGroupNo);
	public List<CmnCodeGroupVO> selectCmnGroupList(String upperCodeNo);
	public List<AdminBoardVO> selectAFaqListByCgn(Map<String, Object> paramMap);	 //FAQ 회원별 게시글 목록조회
	public List<AdminBoardVO> selectAFaqListByUcn(String upperCodeNo);	 //FAQ 전체 게시글 목록조회
	public List<AdminBoardVO> selectAdminBoardList();  //전체 게시글 목록조회
	public int insertAdminBoard(AdminBoardVO board);
	public int updateAdminBoard(AdminBoardVO board);
	public int upDeleteAdminBoard(AdminBoardVO board);  //상태를 삭제로 바꿈
	public int deleteAdminBoard(String boardNo);
}
