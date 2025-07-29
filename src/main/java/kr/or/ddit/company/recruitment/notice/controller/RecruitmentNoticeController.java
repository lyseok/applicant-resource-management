package kr.or.ddit.company.recruitment.notice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.recruitment.notice.service.RecruitService;
import kr.or.ddit.vo.common.UsersVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RecruitmentNoticeController {
	
	
	@GetMapping("/recruit_notice/{recruitmentNo}")
	public String selectRecruitDetail(@PathVariable String recruitmentNo, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		model.addAttribute("recruitmentNo", recruitmentNo);
		model.addAttribute("username", username);
		return "member/recruitment/recruitmentNotice";
	}
	
	@GetMapping("/company/recruit_notice/{recruitmentNo}")
	public String selectRecruitDetailInCompany(@PathVariable String recruitmentNo, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		model.addAttribute("recruitmentNo", recruitmentNo);
		model.addAttribute("username", username);
		return "member/recruitment/recruitmentNotice";
	}
	
	@GetMapping("/company/recruit_notice/list")
	public String selectMyRecruitment() {
		return "company/recruitment/recruitList";
	}
	
	@GetMapping("/company/recruit_notice/notice_form")
	public String noticeForm() {
		
		return "company/recruitment/recruitForm";
	}
	
}
