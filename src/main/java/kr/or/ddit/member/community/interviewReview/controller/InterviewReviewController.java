package kr.or.ddit.member.community.interviewReview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/mypage/interview/review", "/member/interview/review"})
public class InterviewReviewController {
	
	@GetMapping("/view")
	public String reviewList() {
		return "member/community/interviewReview/interviewReviewList";
	}
	
	
	@GetMapping("/write")
	public String formUI() {
		return "member/community/interviewReview/interviewReviewForm";
	}
	
	
}
