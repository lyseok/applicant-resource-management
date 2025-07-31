package kr.or.ddit.member.community.companySalary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member/company_salary")
public class CompanySalaryController {
	
	@GetMapping
	public String salaryPage(Model model) {
		model.addAttribute("boardCss", true);
		return "member/community/companySalary/companySalaryView";
	}

	
	@GetMapping("/detail")
	public String salaryDetail() {
		return "member/community/companySalary/companySalaryDetail";
	}
}
