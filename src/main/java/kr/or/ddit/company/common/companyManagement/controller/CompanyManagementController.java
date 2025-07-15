package kr.or.ddit.company.common.companyManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company/company_management")
public class CompanyManagementController {
	@GetMapping
	public String managementView() {
		return "company/common/companyManagement/companyManagementDetail";
	}
	
	
	@GetMapping("/edit")
	public String managementForm() {
		return "company/common/companyManagement/companyManagementForm";
	}
	
	
}
