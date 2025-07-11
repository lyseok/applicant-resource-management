package kr.or.ddit.member.recruitment.videointerview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.recruitment.videointerview.service.MemberVideoInterviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/member/video_interview")
public class MemberVideoInterviewAjaxController {
	private final MemberVideoInterviewService service;
	
	@GetMapping("/{no}")
	public String joinVideoInterviewURL(@PathVariable String no) {
		return service.memberVideoInterviewURL(no);
	}
}
