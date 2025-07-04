package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.TagVO;


@Mapper
public interface TagMapper {
	public List<TagVO> selectTagList();

	public TagVO selectTagByPk(TagVO vo);
	
	public int insertTag(TagVO vo);
	
	public int updateTag(TagVO vo);
	
	public int deleteTag(TagVO vo);
}
