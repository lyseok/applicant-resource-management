package kr.or.ddit.member.community.service;

import java.util.List;

import kr.or.ddit.vo.community.CommuTagVO;

public interface CommuTagService {

	public List<CommuTagVO> readCommuTagSearchList(String boardNo);
	public List<CommuTagVO> readCommuTagList(String boardNo);
	public void createCommuTag(CommuTagVO tag);
	public void removeCommuTag(String tagNo, String boardNo);
}
