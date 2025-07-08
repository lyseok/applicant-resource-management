package kr.or.ddit.company.recruitment.companyExam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.common.company.service.CompanyService;
import kr.or.ddit.company.recruitment.companyExam.service.CompanyExamService;
import kr.or.ddit.vo.common.CompanyVO;

@Controller
@RequestMapping("/company/companyExam")
public class CompanyExamController {
	
	@Autowired
	private CompanyExamService sevice;
	
	
	@GetMapping
	public String examList(Model model) {
		
		model.addAttribute("user", sevice.readCompanyById(getUserId()));
		return "company/recruitment/companyExam/companyExamList";
	}
	
	
	@GetMapping("/form")
	public String makeExam() {
		return "company/recruitment/companyExam/companyExamForm";
	}
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}

}
