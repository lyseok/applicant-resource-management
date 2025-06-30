package kr.or.ddit.ats.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class commnityController {
	
	@GetMapping("/board/commnity")
	public String getCommnity(Model model) {
		model.addAttribute("searchBar", true);		
		return "board/commnity/commnityList";
	}
	
	@GetMapping("/board/commnity/view")
	public String getCommnityView() {
		return "board/commnity/commnityView";
	}
}
