package kr.or.ddit.company.recruitment.mailtemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company/mail/template")
public class ComMailTemComtroller {
	@GetMapping
	public String templateList() {
		return "company/recruitment/mailTemplate/mailTemplateList";
	}
}
