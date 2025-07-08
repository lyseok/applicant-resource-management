package kr.or.ddit.company.recruitment.interview.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.interview.service.CompanyInterviewService;
import kr.or.ddit.vo.recruitment.InterviewVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/interview")
public class CompanyInterviewAjaxController {
	private final CompanyInterviewService service;
	
	@GetMapping
	public List<InterviewVO> interviewList() {
		return service.readInterviewList();
	}
	
	@GetMapping("/{no}")
	public InterviewVO interviewDetail(@PathVariable String no) {
		return service.readInterview(no);
	}
}
