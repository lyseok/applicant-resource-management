package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.TopJobVO;
@Mapper
public interface TopJobMapper {
	public List<TopJobVO> selectTopJobList();

	public TopJobVO selectTopJobByPk(TopJobVO vo);
	
	public int insertTopJob(TopJobVO vo);
	
	public int updateTopJob(TopJobVO vo);
	
	public int deleteTopJob(TopJobVO vo);
}


