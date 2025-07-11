package kr.or.ddit.member.recruitment.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/interview")
public class MemberInterviewController {
	@GetMapping
	public String memberMyInterviewListPage() {
		return "member/recruitment/interview/interviewList";
	}
}
