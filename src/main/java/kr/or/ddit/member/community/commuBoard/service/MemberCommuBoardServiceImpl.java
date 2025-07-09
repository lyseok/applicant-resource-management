package kr.or.ddit.member.community.commuBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.community.CommuBoardMapper;
import kr.or.ddit.vo.community.CommuBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCommuBoardServiceImpl implements MemberCommuBoardService {
	
	private final CommuBoardMapper mapper;
	private final CodeMapProvider codeMapProvider;

	@Override
	public Optional<CommuBoardVO> readCommuBoard(String commuPostNo) {
		Optional<CommuBoardVO> cboard = Optional.ofNullable(mapper.selectCommuBoardByPk(commuPostNo));
		cboard.ifPresent(this::setCodeName); // Optional의 값이 존재할 때만 setCodeName 호출
		return cboard;
	}

	private void setCodeName(CommuBoardVO cboard) {
		String categoryCode = codeMapProvider.getCodeName(cboard.getCategoryCode());
		cboard.setCodeName(categoryCode);
	}

	@Override
	public List<CommuBoardVO> readCommuBoardList(String categoryCode) {
		List<CommuBoardVO> cboardList = mapper.selectCommuBoardListByCate(categoryCode);
		for(CommuBoardVO cboard : cboardList) {
			setCodeName(cboard);
		}
		return cboardList;
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
