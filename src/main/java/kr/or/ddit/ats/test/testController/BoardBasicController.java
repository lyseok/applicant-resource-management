package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardBasicController {

	@GetMapping("/board/basic")
	public String getBoardBasicList(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "board/basic/boardBasicList";
	}
	@GetMapping("/board/basic/view")
	public String getBoardBasicView(Model model) {
		model.addAttribute("boardCss", true);
		return "board/basic/basicView";
	}
}
