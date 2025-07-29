package kr.or.ddit.member.community.companySalary.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.CompanySalaryDTO;
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

	@Override
	public List<CompanySalaryDTO> readSalaryStatisticsById(String userId) {
		
		List<CompanySalaryDTO> sList =  salaryMapper.selectSalaryStatisticsById(userId);
			
		
		for (CompanySalaryDTO s : sList) {
			 String codeDetailNo = s.getCodeDetailNo();
			if ("RANK-007".equals(codeDetailNo) || "RANK-008".equals(codeDetailNo) ||
            "RANK-009".equals(codeDetailNo) || "RANK-010".equals(codeDetailNo) ||
            "RANK-011".equals(codeDetailNo)) {
				s.setCodeName("임원");
			}else {
				String codeName = codeMapProvider.getCodeName(s.getCodeDetailNo());
				s.setCodeName(codeName);
			}
			
		}
		return sList;
	}

	@Override
	public List<Map<String, Object>> readSimilarCompanySalariesList(String industryType) {
		return salaryMapper.selectSimilarCompanySalariesList(industryType);
	}

	@Override
	public Map<String, Object> readSalaryListAllCompanyPaged(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
	    int pageSize = Integer.parseInt(params.get("pageSize").toString());
		params.put("startRow", (page-1) * pageSize);
		params.put("endRow", page * pageSize);
		if (params.get("sort") == null || params.get("sort").toString().isEmpty()) {
		        params.put("sort", "default"); // 회사명순
		}
		
		List<Map<String, Object>> salaryList = salaryMapper.selectSalaryListAllCompanyPaged(params);
		
		for (Map<String, Object> salary : salaryList) {
			  String induCode = (String) salary.get("INDUSTRY_TYPE");
		      salary.put("INDU_NAME", codeMapProvider.getInduName(induCode));
		}
		
		int totalCount = salaryMapper.countSalaryListAllCompany(params);
		
		
		Map<String, Object> result = new HashMap<>();
	    result.put("data", salaryList);
		result.put("totalCount", totalCount);

		return result;
				
	}

	@Override
	public Map<String, Object> readCompanySalaryRankByIndu(String userId, String industryType) {
		
		Map<String, Object> rank = salaryMapper.selectCompanySalaryRankByIndu(userId, industryType);
		String induCode = (String)rank.get("INDUSTRY_TYPE");
		String induName = codeMapProvider.getInduName(induCode);
		rank.put("INDU_NAME", induName);
		return rank;
		
	}

	


	

}
