package kr.or.ddit.admin.community.commuTag.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.community.CommuTagVO;

public interface AdminCommuTagService {
	
	public Optional<CommuTagVO> readCommuTagByPk(String tagNo, String boardNo);
	public List<CommuTagVO> searchCommuTagTagList(String boardNo);
	public List<CommuTagVO> searchCommuTagBoardList(String tagNo);
	public List<CommuTagVO> readCommuTagList();
	public void createCommuTag(CommuTagVO tag);
	public void removeCommuTag(String tagNo, String boardNo);
}
