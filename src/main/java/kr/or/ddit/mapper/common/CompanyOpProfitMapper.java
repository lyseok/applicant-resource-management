package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.CompanyOpProfitDTO;
@Mapper
public interface CompanyOpProfitMapper {
	public List<CompanyOpProfitDTO> selectCompanyOpProfitInfoById(String userId);
	
}
