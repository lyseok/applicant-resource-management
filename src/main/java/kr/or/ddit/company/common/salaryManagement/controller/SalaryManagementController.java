package kr.or.ddit.company.common.salaryManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company/salary_management")
public class SalaryManagementController {
	
	@GetMapping
	public String salaryView() {
		return "company/common/salaryManagement/salaryManagementDetail";
	}
	
	@GetMapping("/edit")
	public String salaryEdit() {
		return "company/common/salaryManagement/salaryManagementForm";
				
	}
}
