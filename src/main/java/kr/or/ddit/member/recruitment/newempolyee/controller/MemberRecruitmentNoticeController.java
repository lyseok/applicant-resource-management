package kr.or.ddit.member.recruitment.newempolyee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberRecruitmentNoticeController {
	
	@GetMapping("/realTimeRecruitment")
	public String ListRecruitmentNotice(Model model) {
		model.addAttribute("boardCss", true);
		return "recruitNotice/realTimeNotice";
	}

	@GetMapping("/newEmployee")
	public String SalaryList(Model model) {
		model.addAttribute("boardCss", true);
		return "recruitNotice/newbieSalary";
	}

}
