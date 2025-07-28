package kr.or.ddit.member.common.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@GetMapping
	public String memberMypage() {
		return "member/index";
	}
	@GetMapping("/test")
	public String testmyPage() {
		return "member/testIndex";
	}
}
