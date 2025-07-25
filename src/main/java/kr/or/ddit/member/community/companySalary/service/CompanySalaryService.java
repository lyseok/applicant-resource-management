package kr.or.ddit.member.community.companySalary.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CompanySalaryDTO;

public interface CompanySalaryService {
	public List<Map<String, Object>> readSalaryListAllCompany();
	
	public List<CompanySalaryDTO> readSalaryStatisticsById(String userId);
	
	public List<Map<String, Object>> readSimilarCompanySalariesList(String industryType);
}
