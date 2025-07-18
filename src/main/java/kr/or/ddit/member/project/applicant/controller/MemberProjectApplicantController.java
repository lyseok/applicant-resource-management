package kr.or.ddit.member.project.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/project/applicant")
public class MemberProjectApplicantController {
	@GetMapping
	public String applicantListPage() {
		return "member/project/applicant/applicantList";
	}
	@GetMapping("/detail")
	public String projectApplicantPage() {
		return "member/project/applicant/projectApplicant";
	}
	
}
