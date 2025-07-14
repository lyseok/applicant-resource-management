package kr.or.ddit.member.common.tool.spelling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.common.tool.spelling.service.SpellingTest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SpellingTestController {
	
	private final SpellingTest spellingService;

	@GetMapping("/spelling")
	public String showPage() {
		return "member/tool/spelling/SpellingCheck"; // JSP 경로
	}

	@PostMapping("/spell/check")
	public String check(@RequestParam("sentence") String sentence, Model model) {
		 System.out.println(">>> Controller 도착, 입력 문장: " + sentence);
		try {
			String correctedHtml = spellingService.checkSpelling(sentence);
			model.addAttribute("original", sentence);
			model.addAttribute("corrected", correctedHtml);
		} catch (Exception e) {
			 e.printStackTrace(); // 꼭 출력
			model.addAttribute("error", "맞춤법 검사 중 오류가 발생했습니다.");
		}
		return "member/tool/spelling/SpellingCheck";
	}
}

