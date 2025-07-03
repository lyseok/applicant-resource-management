package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CommuCommentMapper;
import kr.or.ddit.vo.community.CommuCommentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommuCommentServiceImpl implements CommuCommentService {
	
	private final CommuCommentMapper mapper;

	@Override
	public Optional<CommuCommentVO> readCommuComment(String commuCommentNo) {
		return Optional.ofNullable(mapper.selectCommuComment(commuCommentNo));
	}

	@Override
	public List<CommuCommentVO> readCommuCommentList(String commuPostNo) {
		return mapper.selectCommuCommentList(commuPostNo);
	}

	@Override
	public void createCommuComment(CommuCommentVO comment) {
		mapper.insertCommuComment(comment);
	}

	@Override
	public void modifyCommuComment(CommuCommentVO comment) {
		mapper.updateCommuComment(comment);
	}

	@Override
	public void removeCommuComment(String commuCommentNo) {
		mapper.deleteCommuComment(commuCommentNo);
	}

}
