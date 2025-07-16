package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CompanyVO;
@Mapper
public interface CompanyMapper {
	public List<CompanyVO> selectCompanyList();
	
	public CompanyVO selectCompanyById(String userId);
	
	public int updateCompany(CompanyVO company);
	
	public int deleteCompany(String userId);
	
	public int insertCompany(CompanyVO company);
	
	public CompanyVO selectCompanyManagementById(String userId);
	
	public int updateCompanyInfoById(CompanyVO company);
	
	public CompanyVO selectCompanyWithResumeCareer();
}
