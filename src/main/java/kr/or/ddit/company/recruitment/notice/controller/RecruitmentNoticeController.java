package kr.or.ddit.company.recruitment.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.recruitment.notice.service.RecruitService;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/company/recruit_notice")
@RequiredArgsConstructor
public class RecruitmentNoticeController {
	
	private final RecruitService service;
	
	@GetMapping("/{recruitmentNo}")
	public String selectRecruitDetail(
		@PathVariable String recruitmentNo
		, Model model
	) {
		RecruitmentNoticeVO notice = service.readRecruitNotice(recruitmentNo);
		model.addAttribute("recruitmentNotice", notice) ;
		return "member/recruitment/recruitmentNotice";
	}
	
	@GetMapping("/list")
	public String selectMyRecruitment() {
		return "company/recruitment/recruitList";
	}
	
	@GetMapping("/notice_form")
	public String noticeForm() {
		
		return "company/recruitment/recruitForm";
	}
	
}
