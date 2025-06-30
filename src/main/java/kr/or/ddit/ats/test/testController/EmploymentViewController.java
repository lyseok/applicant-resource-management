package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employment")
public class EmploymentViewController {
	
	@GetMapping("list")
	public String viewEmployment() {
		return "ats/employment/employmentList";
	}
}
