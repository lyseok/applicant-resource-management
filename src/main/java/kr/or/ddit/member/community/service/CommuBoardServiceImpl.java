package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CommuBoardMapper;
import kr.or.ddit.vo.community.CommuBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommuBoardServiceImpl implements CommuBoardService {
	
	private final CommuBoardMapper mapper;

	@Override
	public Optional<CommuBoardVO> readCommuBoard(String commuPostNo) {
		return Optional.ofNullable(mapper.selectCommuBoard(commuPostNo));
	}

	@Override
	public List<CommuBoardVO> readCommuBoardList(String categoryCode) {
		return mapper.selectCommuBoardList(categoryCode);
	}

	@Override
	public void createCommuBoard(CommuBoardVO board) {
		mapper.insertCommuBoard(board);
	}

	@Override
	public void modifyCommuBoard(CommuBoardVO board) {
		mapper.updateCommuBoard(board);
	}

	@Override
	public void removeCommuBoard(String commuPostNo) {
		mapper.deleteCommuBoard(commuPostNo);
	}

}
