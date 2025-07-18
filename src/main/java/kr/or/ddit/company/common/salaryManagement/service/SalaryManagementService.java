package kr.or.ddit.company.common.salaryManagement.service;

import java.util.List;

import kr.or.ddit.vo.common.SalaryVO;

public interface SalaryManagementService {
	public int createSalary(List<SalaryVO> salary, String userId);
	public int editSalary(SalaryVO salary);
	
	public List<SalaryVO> readSalaryListById(String userId);
	
}
