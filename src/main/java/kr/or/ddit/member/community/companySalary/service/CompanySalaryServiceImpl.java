package kr.or.ddit.member.community.companySalary.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.common.SalaryMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanySalaryServiceImpl implements CompanySalaryService{
	private final SalaryMapper salaryMapper;
	private final CodeMapProvider codeMapProvider;

	@Override
	public List<Map<String, Object>> readSalaryListAllCompany() {
		List<Map<String, Object>> salaryList = salaryMapper.selectSalaryListAllCompany();
		for (Map<String, Object> salary : salaryList) {
			String induCode = (String) salary.get("INDUSTRY_TYPE");
			String induName = codeMapProvider.getInduName(induCode);
			salary.put("INDU_NAME", induName);
		}
		
		return salaryList;
	}

}
