package kr.or.ddit.member.community.commuComment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.CommuCommentMapper;
import kr.or.ddit.vo.community.CommuCommentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCommuCommentServiceImpl implements MemberCommuCommentService {
	
	private final CommuCommentMapper mapper;
	
	@Override
	public Optional<CommuCommentVO> readCommuCommentbyPk(String commuCommentNo) {
		return Optional.ofNullable(mapper.selectCommuCommentbyPk(commuCommentNo));
	}

	@Override
	public List<CommuCommentVO> searchCommuCommentPostList(String commuPostNo) {
		return mapper.searchCommuCommentPostList(commuPostNo);
	}

	@Override
	public List<CommuCommentVO> searchCommuCommentList() {
		return mapper.searchCommuCommentList();
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
