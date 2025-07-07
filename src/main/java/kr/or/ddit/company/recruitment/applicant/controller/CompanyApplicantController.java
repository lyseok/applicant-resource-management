package kr.or.ddit.company.recruitment.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("company")
@Controller
public class CompanyApplicantController {

	@GetMapping("/companyTest")
	public String chick() {
		return "/company/person/find";
	}
	
	
}
