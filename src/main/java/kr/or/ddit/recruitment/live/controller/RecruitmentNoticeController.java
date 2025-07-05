package kr.or.ddit.recruitment.live.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.recruitment.live.service.RecruitmentNoticeService;

@Controller
@RequestMapping
public class RecruitmentNoticeController {
	
	@Autowired
	private RecruitmentNoticeService service;

	@GetMapping("/liverecruitment.do")
	String liveRecruitment(Model model) {
		model.addAttribute("boardCss",true);
		return "recruitment/live/recruitmentupload";
	}
}
