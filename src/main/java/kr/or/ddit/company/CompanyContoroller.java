package kr.or.ddit.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyContoroller {

	@GetMapping
	public String indexPage() {
		return "company/index";
	}
}
