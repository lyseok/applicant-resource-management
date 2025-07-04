package kr.or.ddit.ajax.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.AdminBoardMapper;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminBoardServiceImpl implements AdminBoardService {
	
	private final AdminBoardMapper mapper;

	@Override
	public Optional<AdminBoardVO> readAdminBoard(String boardNo) {
		return Optional.ofNullable(mapper.selectAdminBoard(boardNo));
	}

	@Override
	public List<AdminBoardVO> readAdminBoardList(String boardTypeCode) {
		return mapper.selectAdminBoardList(boardTypeCode);
	}

	@Override
	public void createAdminBoard(AdminBoardVO board) {
		mapper.insertAdminBoard(board);		
	}

	@Override
	public void modifyAdminBoard(AdminBoardVO board) {
		mapper.updateAdminBoard(board);

	}


	@Override
	public void removeAdminBoard(String boardNo) {
		mapper.deleteAdminBoard(boardNo);
	}
	
}
