package kr.or.ddit.recruitment.realtime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.recruitment.realtime.service.RecruitmentNoticeService;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Controller
@RequestMapping
public class RecruitmentNoticeController {
	
	@Autowired
	private RecruitmentNoticeService service;

	@GetMapping("/realTimeRecruitment.do")
	public String ListRecruitmentNotice(Model model) {
		List<RecruitmentNoticeVO> recruitmentList = service.readRecruitmentNoticeList();
		model.addAttribute("recruitmentList", recruitmentList);
//		model.addAttribute("boardCss",true);
		return "recruitment/newempolyee/realTimeRecruitment";
	}
	
	@GetMapping("/newEmployee.do")
	public String ss(Model model) {
		List<RecruitmentNoticeVO> salaryList = service.salaryRecruitment();
		model.addAttribute("boardCss", true);
		model.addAttribute("salaryList", salaryList);
		return "recruitment/newempolyee/NewEmpolyee";
	}
	
}
