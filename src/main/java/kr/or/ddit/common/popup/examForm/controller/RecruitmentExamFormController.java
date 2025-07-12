package kr.or.ddit.common.popup.examForm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/popup")
public class RecruitmentExamFormController {
	
	@GetMapping("/exam_form")
	public String recruitExamPage() {
		return "popup/recruitmentExamForm";
	}
}
