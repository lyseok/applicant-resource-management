package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.SalaryVO;

@Mapper
public interface SalaryMapper {
	public SalaryVO selectSalaryByPk(String salaryId);
	public SalaryVO selectSalaryByCompany(String comId);
	public SalaryVO selectSalaryByUserAndRank(String userId, String codeDetailNo);
	
	public List<SalaryVO> selectSalaryList();
	public List<SalaryVO> searchSalaryRangeList(Map<String, Object> params);
	public List<SalaryVO> searchSalaryComNameList(String comName);
	public List<SalaryVO> selectSalaryListbyId(String userId);
	
	public int updateSalary(SalaryVO salary);
	public int insertSalary(SalaryVO salary);
	public int deleteSalary(String salaryId);
}
