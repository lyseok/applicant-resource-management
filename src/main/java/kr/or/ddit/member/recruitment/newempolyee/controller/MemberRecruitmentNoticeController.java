package kr.or.ddit.member.recruitment.newempolyee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.recruitment.newempolyee.service.MemberRecruitmentNoticeService;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Controller
@RequestMapping("/member")
public class MemberRecruitmentNoticeController {

	@Autowired
	private MemberRecruitmentNoticeService service;

	@GetMapping("/realTimeRecruitment")
	public String ListRecruitmentNotice(Model model) {
		List<RecruitmentNoticeVO> recruitmentList = service.readRecruitmentNoticeList();
		model.addAttribute("recruitmentList", recruitmentList);
//		model.addAttribute("boardCss",true);
		return "member/recruitment/realTimeRecruitment";
	}

	@GetMapping("/newEmployee")
	public String SalaryList(Model model) {
		List<RecruitmentNoticeVO> salaryList = service.salaryRecruitment();
		model.addAttribute("boardCss", true);
		model.addAttribute("salaryList", salaryList);
		return "member/recruitment/NewEmpolyee";
	}

}
