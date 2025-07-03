package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CommuBoardVO;

@Mapper
public interface CommuBoardMapper {

	public CommuBoardVO selectCommuBoard(String commuPostNo);
	public List<CommuBoardVO> selectCommuBoardList(String categoryCode);
	public int insertCommuBoard(CommuBoardVO board);
	public int updateCommuBoard(CommuBoardVO board);
	public int deleteCommuBoard(String commuPostNo);
}
