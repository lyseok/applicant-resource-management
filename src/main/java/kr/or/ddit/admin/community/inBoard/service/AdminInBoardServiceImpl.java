package kr.or.ddit.admin.community.inBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.InBoardMapper;
import kr.or.ddit.vo.community.InBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminInBoardServiceImpl implements AdminInBoardService {

	private final InBoardMapper mapper;
	
	@Override
	public Optional<InBoardVO> readInBoardByPk(String commuPostNo, String avatarId) {
		return Optional.ofNullable(mapper.selectInBoardByPk(commuPostNo, avatarId));
	}

	@Override
	public List<InBoardVO> searchInBoardCommuPostList(String avatarId) {
		return mapper.searchInBoardCommuPostList(avatarId);
	}

	@Override
	public List<InBoardVO> searchInBoardAvatarList(String commuPostNo) {
		return mapper.searchInBoardAvatarList(commuPostNo);
	}

	@Override
	public List<InBoardVO> readInBoardList() {
		return mapper.selectInBoardList();
	}

	@Override
	public void createInBoard(InBoardVO board) {
		mapper.insertInBoard(board);
	}

	@Override
	public void removeInBoard(String commuPostNo, String avatarId) {
		mapper.deleteInBoard(commuPostNo, avatarId);
	}

}
