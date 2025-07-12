package kr.or.ddit.admin.community.inComment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.admin.community.inComment.service.AdminInCommentService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin/board/in_comment")
@RequiredArgsConstructor
public class AdminInCommentController { // 동기 컨트롤러는 페이지 이동용

	private final AdminInCommentService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);    // 게시판 전용 css 이 한줄만 추가해주면 됩니다.
		model.addAttribute("searchBar", true);   // 서치바 전용 css
		return "admin/community/inComment/icommentForm";
	}
	
}