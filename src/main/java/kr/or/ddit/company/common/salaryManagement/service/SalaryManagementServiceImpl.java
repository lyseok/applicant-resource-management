package kr.or.ddit.company.common.salaryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.SalaryMapper;
import kr.or.ddit.vo.common.SalaryVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryManagementServiceImpl implements SalaryManagementService{
	private final CodeMapProvider codeMapProvider;
	private final SalaryMapper salaryMapper;
	
	@Override
	public int createSalary(SalaryVO salary) {
		return 0;
		
	}

	@Override
	public int editSalary(SalaryVO salary) {
		return 0;
	
	}

	@Override
	public List<SalaryVO> readSalaryById(String userId) {
		return null;
		
	}
	
}
