package kr.or.ddit.member.project.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/project")
public class MemberProjectController {
	@GetMapping
	public String projectListPage() {
		return "member/project/projectList";
	}
}
