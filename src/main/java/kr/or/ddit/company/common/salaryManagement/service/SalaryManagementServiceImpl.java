package kr.or.ddit.company.common.salaryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public int createSalary(List<SalaryVO> salaryList, String userId) {
		int count = 0;
		for (SalaryVO salary : salaryList) {
			salary.setUserId(userId);
			
			SalaryVO existing = salaryMapper.selectSalaryByUserAndRank(userId, salary.getCodeDetailNo());
			
			if(existing != null) {
				salary.setSalaryId(existing.getSalaryId());
				count += salaryMapper.updateSalary(salary);
			}else {
				count += salaryMapper.insertSalary(salary);
			}
		}
		return count;
	}
	
	

	@Override
	public int editSalary(SalaryVO salary) {
		return 0;
	
	}


	@Override
	public List<SalaryVO> readSalaryListById(String userId) {
		List<SalaryVO> salaryList = salaryMapper.selectSalaryListbyId(userId);
		
		for (SalaryVO salary : salaryList) {
			salary.setCodeName(codeMapProvider.getCodeName(salary.getCodeDetailNo()));
		}
		return salaryList;
	}

	
	
}
