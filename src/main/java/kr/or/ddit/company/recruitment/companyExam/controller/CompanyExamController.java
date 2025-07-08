package kr.or.ddit.company.recruitment.companyExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company/companyExam")
public class CompanyExamController {
	
	@GetMapping
	public String makeExam() {
		return "";
	}
}
