package kr.or.ddit.member.common.tool.spelling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmploymentToolController {

	@GetMapping("/countText")
	public String countTextForm() {
		return "member/tool/counttext/CountText";
	}
	
	@GetMapping("/countyear")
	public String countYearForm() {
		return "member/tool/countyear/CountYear";
	}
}
