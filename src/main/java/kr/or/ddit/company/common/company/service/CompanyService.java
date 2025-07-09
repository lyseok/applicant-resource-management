package kr.or.ddit.company.common.company.service;

import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.UsersVO;

public interface CompanyService {
	public List<CompanyVO> readCompanyList();
	
	public CompanyVO readCompany();
	
	public void modifyCompany();
	
	public void removeCompany();
	
	public void registerCompany(CompanyVO company);

}
