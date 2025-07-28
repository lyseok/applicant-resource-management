package kr.or.ddit.member.community.companySalary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member/company_salary")
public class CompanySalaryController {
	
	@GetMapping
	public String salaryPage() {
		return "member/community/companySalary/companySalaryView";
	}

	
	@GetMapping("/detail")
	public String salaryDetail() {
		return "member/community/companySalary/companySalaryDetail";
	}
}
