package kr.or.ddit.company.common.companyManagement.service;

import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.vo.common.CompanyVO;

public interface CompanyManagementService {
	public CompanyVO readCompanyManagementById(String userId);
	public int editCompanyInfo(CompanyInfoDTO companyInfoDTO);
	
}
