package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.AdminBoardVO;

@Mapper
public interface AdminBoardMapper {
	
	public AdminBoardVO selectAdminBoardByPk(String boardNo);  //게시글 단건조회
	public List<AdminBoardVO> selectAdminBoardListByType(String boardTypeCode);	 //유형별 게시글 목록조회
	public List<AdminBoardVO> selectAdminBoardList();  //전체 게시글 목록조회
	public int insertAdminBoard(AdminBoardVO board);
	public int updateAdminBoard(AdminBoardVO board);
	public int deleteAdminBoard(String boardNo);
}
