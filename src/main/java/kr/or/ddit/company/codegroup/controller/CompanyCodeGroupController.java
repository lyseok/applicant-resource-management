package kr.or.ddit.company.codegroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company/codegroup")
public class CompanyCodeGroupController {
	@GetMapping
	public String codeGroupPage() {
		return "";
	}
}
