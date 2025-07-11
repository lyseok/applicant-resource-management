package kr.or.ddit.common.popup.envaluate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/popup")
public class InterviewEvaluateController {	
	@GetMapping("/evaluate")
	public String evaluetePage() {
		return "popup/interviewEvaluate";
	}
}
