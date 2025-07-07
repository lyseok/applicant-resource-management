package kr.or.ddit.admin.community.commuTag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CommuTagMapper;
import kr.or.ddit.vo.community.CommuTagVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCommuTagServiceImpl implements AdminCommuTagService {

	private final CommuTagMapper mapper;
	
	@Override
	public Optional<CommuTagVO> readCommuTagByPk(String tagNo, String boardNo) {
		return Optional.ofNullable(mapper.selectCommuTagByPk(tagNo, boardNo));
	}
	

	@Override
	public List<CommuTagVO> searchCommuTagTagList(String boardNo) {
		return mapper.searchCommuTagTagList(boardNo);
	}

	@Override
	public List<CommuTagVO> searchCommuTagBoardList(String tagNo) {
		return mapper.searchCommuTagBoardList(tagNo);
	}
	
	@Override
	public List<CommuTagVO> readCommuTagList() {
		return mapper.selectCommuTagList();
	}

	@Override
	public void createCommuTag(CommuTagVO tag) {
		mapper.insertCommuTag(tag);
	}

	@Override
	public void removeCommuTag(String tagNo, String boardNo) {
		mapper.deleteCommuTag(tagNo, boardNo);
	}



}
