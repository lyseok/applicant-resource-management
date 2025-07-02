package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeGroupVO;

@Mapper
public interface CmnCodeGroupMapper {
	public List<CmnCodeGroupVO> selectCmnCodeGroupList();
}
