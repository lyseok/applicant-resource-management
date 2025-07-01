package kr.or.ddit.recruitment.hot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HotRecruitment {

	@GetMapping("/hotrecruitment.do")
	String HotRecruitment() {
		return "recruitment/hot/recruitmentlist";
	}
	
}
