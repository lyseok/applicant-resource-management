package kr.or.ddit.member.common.mypage.interviewReview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/interview/review")
public class InterviewReviewController {
	
	@GetMapping("/write")
	public String formUI() {
		return "member/community/interviewReview/interviewReviewForm";
	}
}
