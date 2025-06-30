package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class companyController {
	
	@GetMapping("/company/view")
	public String getviewUI() {
		return "company/companyView";
	}
}
