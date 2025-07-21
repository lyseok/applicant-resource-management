package kr.or.ddit.member.common.mypage.interviewReview.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.mypage.interviewReview.service.InterviewReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/interview/review")
public class InterviewReviewAjaxController {
	private final InterviewReviewService interviewReviewService;
	
	@GetMapping("/{interviewNo}")
	public Map<String, Object> getInterviewInfo(@PathVariable String interviewNo){
		return interviewReviewService.readInterviewWithCompanyNameByNo(interviewNo);
	}
}
