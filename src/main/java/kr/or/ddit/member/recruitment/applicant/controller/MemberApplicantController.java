package kr.or.ddit.member.recruitment.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberApplicantController {

	@GetMapping("/check")
	public String chick() {
		return "/company/person/find";
	}
	
	
}
