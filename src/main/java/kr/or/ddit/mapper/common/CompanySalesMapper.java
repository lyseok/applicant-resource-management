package kr.or.ddit.mapper.common;

import java.util.List;

import kr.or.ddit.dto.CompanySalesDTO;

public interface CompanySalesMapper {
	public List<CompanySalesDTO> selectCompanySalesInfoById(String userId);
}
