package kr.or.ddit.member.community.companySalary.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.CompanySalaryDTO;

public interface CompanySalaryService {
	public List<Map<String, Object>> readSalaryListAllCompany();
	
	public List<CompanySalaryDTO> readSalaryStatisticsById(String userId);
	
	public List<Map<String, Object>> readSimilarCompanySalariesList(String industryType);
	
	public Map<String, Object> readSalaryListAllCompanyPaged(Map<String, Object> params);
	
	// feature/#012_기업연봉 추가 메서드
	public Map<String, Object> readCompanySalaryRankByIndu(String userId, String industryType);
}
