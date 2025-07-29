package kr.or.ddit.common.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@GetMapping("/recruit")
	public String recruitSearch(Model model) {
		model.addAttribute("boardCss", true);
		return "common/search";
	}
	
	@GetMapping("/company")
	public String companySearch() {
		return "";
	}

}
