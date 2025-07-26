package kr.or.ddit.company.recruitment.talentpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/company/talentpool")
@Controller
public class CompanyTalentPoolController {
	@GetMapping
	public String talentpoolPage() {
		return "company/recruitment/talentpool/talentPoolList";
	}	
	@GetMapping("/scrab")
	public String myTalentpoolPage() {
		return "company/recruitment/talentpool/myTalentPoolList";
	}	
	
}