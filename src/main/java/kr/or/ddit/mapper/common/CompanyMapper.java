package kr.or.ddit.mapper.common;

import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;

public interface CompanyMapper {
	public List<CompanyVO> selectCompanyList();
	
	public CompanyVO selectCompany();
	
	public int updateCompany();
	
	public int deleteCompany();
	
	public int insertCompany();
}
