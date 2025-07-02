package kr.or.ddit.member.common.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.common.member.service.MemberService;

@Controller
public class MemberRegisterController {
	
	@Autowired
	private MemberService service;
	private static final String MODELNAME = "member";
	
	
}
