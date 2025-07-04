package kr.or.ddit.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.community.CommuTagVO;

@Mapper
public interface CommuTagMapper {

	public CommuTagVO selectCommuTag(String tagNo, String boardNo);  //해당 게시글의 태그 하나
	public List<CommuTagVO> selectCommuTagList(String boardNo);  //해당 게시글의 태그들
	public int insertCommuTag(CommuTagVO tag);
	public int updateCommuTag(CommuTagVO tag);
	public int deleteCommuTag(String tagNo, String boardNo);
}
