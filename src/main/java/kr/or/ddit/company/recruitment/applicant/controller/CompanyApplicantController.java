package kr.or.ddit.company.recruitment.applicant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.recruitment.applicant.service.CompanyApplicantService;
import kr.or.ddit.mapper.recruitment.ApplicantMapper;
import kr.or.ddit.vo.recruitment.ApplicantVO;

@RequestMapping("company")
@Controller
public class CompanyApplicantController {

	@Autowired
	private CompanyApplicantService Alservice;
	
	@GetMapping("/companyTest")
	public String applicantList(Model model) {
		List<ApplicantVO> applicantList = Alservice.selectApplicantList();
		model.addAttribute("boardCss",true);
		model.addAttribute("applicantList", applicantList);
		return "/company/person/find";
	}
}	
	

