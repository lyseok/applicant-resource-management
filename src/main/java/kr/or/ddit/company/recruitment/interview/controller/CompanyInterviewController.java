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
}
