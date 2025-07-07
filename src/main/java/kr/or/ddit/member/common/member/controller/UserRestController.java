package kr.or.ddit.member.common.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/user")
@RequiredArgsConstructor
public class UserRestController {
	
	private final MemberService service;
	
	@GetMapping("{userId}")
	public String idCheck(@PathVariable String userId) {
		return "";
	}
}
