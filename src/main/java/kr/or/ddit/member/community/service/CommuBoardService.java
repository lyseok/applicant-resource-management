package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.CommuBoardVO;

public interface CommuBoardService {

	public Optional<CommuBoardVO> readCommuBoard(String commuPostNo);
	public List<CommuBoardVO> readCommuBoardList(String categoryCode);
	public void createCommuBoard(CommuBoardVO board);
	public void modifyCommuBoard(CommuBoardVO board);
	public void removeCommuBoard(String commuPostNo);
}