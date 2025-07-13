package kr.or.ddit.company.recruitment.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/company/interview")
public class CompanyInterviewController {
	@GetMapping
	public String companyInterviewListPage() {
		return "company/recruitment/interview/interviewList";
	}
	@GetMapping("/detail")
	public String getMethodName() {
		return "company/recruitment/interview/interviewDetail";
	}
	@GetMapping("/create")
	public String companyVideoInterviewFormPage() {
		return "company/recruitment/interview/videoInterviewFrom";
	}
	@GetMapping("/edit")
	public String companyInterviewFormPage() {
		return "company/recruitment/interview/interviewForm";
	}
	
}
