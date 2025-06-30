package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin/adminHome";
	}
}
