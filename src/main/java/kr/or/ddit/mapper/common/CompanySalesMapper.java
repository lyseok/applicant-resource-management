package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.CompanySalesDTO;

@Mapper
public interface CompanySalesMapper {
	public List<CompanySalesDTO> selectCompanySalesInfoById(String userId);
	
}
