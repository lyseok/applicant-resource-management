package kr.or.ddit.company.common.companyManagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.common.companyManagement.service.CompanyManagementService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/company_management")
public class CompanyManagementAjaxController {
	private final CompanyManagementService companyManagementService;
	
	@GetMapping
	public CompanyVO companyDetail() {
		CompanyVO company = companyManagementService.readCompanyById(getUserId());
		return company;
	}
	

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}
