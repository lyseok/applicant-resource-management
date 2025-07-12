package kr.or.ddit.member.community.inComment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.community.inComment.service.MemberInCommentService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/member/board/in_comment")
@RequiredArgsConstructor
public class MemberInCommentController { // 동기 컨트롤러는 페이지 이동용

	private final MemberInCommentService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);    // 게시판 전용 css 이 한줄만 추가해주면 됩니다.
		model.addAttribute("searchBar", true);   // 서치바 전용 css
		return "member/community/inComment/icommentForm";
	}
	
}