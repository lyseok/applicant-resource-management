package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.AdminBoardVO;

@Mapper
public interface AdminBoardMapper {
	
	public AdminBoardVO selectAdminBoard(String boardNo);
	public List<AdminBoardVO> selectAdminBoardList(String boardTypeCode);
	public int insertAdminBoard(AdminBoardVO board);
	public int updateAdminBoard(AdminBoardVO board);
	public int deleteAdminBoard(String boardNo);
}
