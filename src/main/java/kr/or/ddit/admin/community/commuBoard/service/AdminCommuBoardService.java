package kr.or.ddit.admin.community.commuBoard.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.CommuBoardVO;

public interface AdminCommuBoardService {

	public Optional<CommuBoardVO> readCommuBoard(String commuPostNo);
	public List<CommuBoardVO> readCommuBoardList(String categoryCode);
	public void createCommuBoard(CommuBoardVO board);
	public void modifyCommuBoard(CommuBoardVO board);
	public void removeCommuBoard(String commuPostNo);
}