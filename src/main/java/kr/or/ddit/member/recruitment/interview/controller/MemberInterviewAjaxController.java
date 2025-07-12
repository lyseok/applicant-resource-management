package kr.or.ddit.member.recruitment.interview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.recruitment.interview.service.MemberInterviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/member/mypage/interview")
public class MemberInterviewAjaxController {
	private final MemberInterviewService service;
	
	@GetMapping("/list")
	public List<Map<String, Object>> getInterviewList(){
		return service.readMyInterviewList();
	}
	
	@GetMapping("/{no}")
	public List<Map<String, Object>> getInterviewDetail(@PathVariable String no){
		return service.readInterviewDetail(no);
	} 
}
