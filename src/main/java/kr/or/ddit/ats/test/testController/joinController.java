package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class joinController {

	// 개인회원가입
	@GetMapping("/signUp")
	public String getSignUp() {
		return "join/joinForm";
	}
	
	// 기업회원가입
	@GetMapping("/signUp/corp")
	public String getSingCorp() {
		return "join/joinFormCorp";
	}
}
