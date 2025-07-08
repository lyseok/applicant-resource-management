package kr.or.ddit.member.recruitment.applicant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.admin.recruitment.applicant.service.AdminApplicantService;
import kr.or.ddit.member.recruitment.applicant.service.MemberApplicantService;
import kr.or.ddit.vo.recruitment.ApplicantVO;

@RequestMapping("/member")
@Controller
public class MemberApplicantController {

	@Autowired
	private MemberApplicantService Alservice;
	
	@GetMapping("/adminTest")
	public String applicantList(Model model) {
		List<ApplicantVO> applicantList = Alservice.selectApplicantList();
		model.addAttribute("boardCss",true);
		model.addAttribute("applicantList", applicantList);
		return "/member/person/find";
	}
	
	
}
