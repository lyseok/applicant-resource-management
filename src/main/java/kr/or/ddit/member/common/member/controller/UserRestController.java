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
		int cnt = service.idDuplicateCheck(userId);
		String msg = null;
		
		if(cnt>0) {
			msg = "아이디가 중복 되었습니다.";
		}else {
			msg = "사용 가능한 아이디입니다!";
		}
		return msg;
	}
}
