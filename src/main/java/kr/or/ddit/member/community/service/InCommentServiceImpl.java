package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.community.InCommentMapper;
import kr.or.ddit.vo.community.InCommentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InCommentServiceImpl implements InCommentService {

	private final InCommentMapper mapper;
	
	@Override
	public Optional<InCommentVO> readInCommentByCommentNoAvatarId(String commentNo, String avatarId) {
		return Optional.ofNullable(mapper.selectInCommentbyCommentNoAvatarId(commentNo, avatarId));
	}

	@Override
	public List<InCommentVO> searchInCommentCommentList(String avatarId) {
		return mapper.searchInCommentCommentList(avatarId);
	}

	@Override
	public List<InCommentVO> searchInCommentAvatarList(String commentNo) {
		return mapper.searchInCommentAvatarList(commentNo);
	}

	@Override
	public List<InCommentVO> readInCommentList() {
		return mapper.selectInCommentList();
	}

	@Override
	public void createInComment(InCommentVO comment) {
		mapper.insertInComment(comment);
	}

	@Override
	public void removeInComment(String commentNo, String avatarId) {
		mapper.deleteInComment(commentNo, avatarId);
	}

}
