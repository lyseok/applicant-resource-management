package kr.or.ddit.mapper.common;

import java.util.List;

import kr.or.ddit.vo.common.SalaryVO;

public interface SalaryMapper {
	public List<SalaryVO> selectSalaryList();
	public SalaryVO selectSalary(String comId);
	public int updateSalary(SalaryVO salary);
	public int insertSalary(SalaryVO salary);
	public int deleteSalary(String salaryId);

}
