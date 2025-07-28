package kr.or.ddit.member.common.companyDetailView.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/company_view")
public class CompanyDetailViewController {
	
	@GetMapping
	public String companyView() {
		return "member/common/companyDetailView/companyView";
	}
	
}
