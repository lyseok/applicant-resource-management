package kr.or.ddit.member.recruitment.recruitmentExam.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/recruitment_exam")
public class RecruitmentExamController {

	@GetMapping
	public String recruitmentExamView() {
		return "member/recruitment/recruitmentExam/recruitmentExamList";
	}
	
	@GetMapping("form")
	public String recruitmentExamFromUI() {
		return "member/recruitment/recruitmentExam/recruitmentExamForm";
	}
	
	
	
}
