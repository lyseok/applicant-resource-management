package kr.or.ddit.mapper.common;

import java.util.List;

import kr.or.ddit.dto.CompanyOpProfitDTO;

public interface CompanyOpProfitMapper {
	public List<CompanyOpProfitDTO> selectCompanyOpProfitInfoById(String userId);
}
