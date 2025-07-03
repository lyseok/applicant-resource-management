package kr.or.ddit.ajax.common.signup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ajax.common.signup.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/user")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService service;
	
	@GetMapping("{userId}")
	public String idCheck(@PathVariable String userId) {
		return "";
	}
}
