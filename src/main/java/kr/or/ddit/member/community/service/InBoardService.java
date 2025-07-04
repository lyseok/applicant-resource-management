package kr.or.ddit.member.community.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.InBoardVO;

public interface InBoardService {

	public Optional<InBoardVO> readInBoardByCommuPostNoAvatarId(String commuPostNo, String avatarId);
	public List<InBoardVO> searchInBoardCommuPostList(String avatarId);  //해당 아바타가 좋아한 댓글들
	public List<InBoardVO> searchInBoardAvatarList(String commuPostNo);  //해당 댓글을 좋아한 아바타들
	public List<InBoardVO> readInBoardList();  //댓글과 아바타 전체
	public void createInBoard(InBoardVO board);
	public void removeInBoard(String commuPostNo, String avatarId);
}
