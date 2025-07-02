package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.SalaryVO;

@Mapper
public interface SalaryMapper {
	public List<SalaryVO> selectSalaryList();
	public SalaryVO selectSalary(String comId);
	public List<SalaryVO> selectSalaryRangeList(Map<String, Object> params);
	public List<SalaryVO> selectSalaryComNameList(String comName);
	public int updateSalary(SalaryVO salary);
	public int insertSalary(SalaryVO salary);
	public int deleteSalary(String salaryId);
}
