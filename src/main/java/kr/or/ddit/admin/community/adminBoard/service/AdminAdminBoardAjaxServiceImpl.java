package kr.or.ddit.admin.community.adminBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.AdminBoardMapper;
import kr.or.ddit.vo.community.AdminBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAdminBoardAjaxServiceImpl implements AdminAdminBoardAjaxService{

	private final AdminBoardMapper mapper;  //UserMapper를 넣어야 하나?

	@Override
	public Optional<AdminBoardVO> readAdminBoardByPk(String boardNo) {
		return Optional.ofNullable(mapper.selectAdminBoardByPk(boardNo));
	}

	@Override
	public List<AdminBoardVO> readAdminBoardListByType(String boardTypeCode) {
		return mapper.selectAdminBoardListByType(boardTypeCode);
	}
		
	@Override
	public List<AdminBoardVO> readAdminBoardList() {
		return mapper.selectAdminBoardList();
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
