package kr.or.ddit.member.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CommuTagMapper;
import kr.or.ddit.vo.community.CommuTagVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommuTagServiceImpl implements CommuTagService {

	private final CommuTagMapper mapper;

	@Override
	public List<CommuTagVO> readCommuTagSearchList(String boardNo) {
		return mapper.searchCommuTagList(boardNo);
	}

	@Override
	public List<CommuTagVO> readCommuTagList(String boardNo) {
		return mapper.selectCommuTagList(boardNo);
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
