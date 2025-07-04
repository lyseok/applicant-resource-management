package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CommuTagVO;

@Mapper
public interface CommuTagMapper {

	public CommuTagVO selectCommuTagByTagBoardNo(String tagNo, String boardNo);  //해당 게시글의 태그 하나
	public List<CommuTagVO> searchCommuTagTagList(String boardNo);  //해당 게시글의 태그들
	public List<CommuTagVO> searchCommuTagBoardList(String tagNo);  //해당 태그가 걸린 게시글들
	public List<CommuTagVO> selectCommuTagList();  //게시글 태그 전부
	public int insertCommuTag(CommuTagVO tag);
	public int deleteCommuTag(String tagNo, String boardNo);
}
