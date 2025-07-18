package kr.or.ddit.member.project.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/project/my_applicant")
public class MemberProjectMyApplicantController {
	@GetMapping
	public String myApplicantListPage() {
		return "member/project/applicant/myApplicantList";
	}
}
