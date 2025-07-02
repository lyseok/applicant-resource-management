package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CompanyVO;
@Mapper
public interface CompanyMapper {
	public List<CompanyVO> selectCompanyList();
	
	public CompanyVO selectCompanyById(String userId);
	
	public int updateCompany();
	
	public int deleteCompany();
	
	public int insertCompany();
}
