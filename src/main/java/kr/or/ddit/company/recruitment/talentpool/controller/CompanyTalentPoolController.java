package kr.or.ddit.company.recruitment.talentpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.company.recruitment.talentpool.service.TalentPoolServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/company/talentpool")
@Controller
public class CompanyTalentPoolController {
	
	@Autowired
	TalentPoolServiceImpl service;
	
	@GetMapping
	public String talentpoolPage(
			RedirectAttributes redirectAttributes
			) {
		if(service.NonPayment() != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "구독중인 기업만 이용가능합니다");
	        return "redirect:/company/payment/main";
		}
		return "company/recruitment/talentpool/talentPoolList";
	}	
	@GetMapping("/scrab")
	public String myTalentpoolPage(
			RedirectAttributes redirectAttributes
			) {
		if(service.NonPayment() != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "구독중인 기업만 이용가능합니다");
	        return "redirect:/company/payment/main";
		}
		return "company/recruitment/talentpool/myTalentPoolList";
	}	
	
} 