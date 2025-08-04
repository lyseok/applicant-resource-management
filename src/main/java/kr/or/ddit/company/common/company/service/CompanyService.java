package kr.or.ddit.company.common.company.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.UsersVO;

public interface CompanyService {
	public List<CompanyVO> readCompanyList();
	
	public List<Map<String, Object>> readInduCodeAndClassCode();
	
	public int duplicatedBrNo(String brNumber);
	
	public CompanyVO readCompany();
	
	public void modifyCompany();
	
	public void removeCompany();
	
	public void registerCompany(CompanyVO company);
	
	public CompanyVO selectCompanyById(String userId);

}
