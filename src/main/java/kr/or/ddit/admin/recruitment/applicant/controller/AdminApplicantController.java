package kr.or.ddit.admin.recruitment.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminApplicantController {

	@GetMapping("/check")
	public String chick() {
		return "";
	}
	
	
}
