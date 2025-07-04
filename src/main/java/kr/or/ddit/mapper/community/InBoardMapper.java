package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.InBoardVO;

@Mapper
public interface InBoardMapper {
	
	public InBoardVO selectInBoardByCommuPostNoAvatarId(String commuPostNo, String avatarId);  //해당 아바타가 좋아한 해당 게시글 하나
	public List<InBoardVO> searchInBoardCommuPostList(String avatarId);  //해당 아바타가 좋아한 게시글들
	public List<InBoardVO> searchInBoardAvatarList(String commuPostNo);  //해당 게시글을 좋아한 아바타들
	public List<InBoardVO> selectInBoardList();  //게시글과 아바타 전부
	public int insertInBoard(InBoardVO board);
	public int deleteInBoard(String commuPostNo, String avatarId);
}
