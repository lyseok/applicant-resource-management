package kr.or.ddit.member.common.codegroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/codegroup")
public class MemberCodeGroupController {
	@GetMapping
	public String codeGroupPage() {
		return "";
	}
}
