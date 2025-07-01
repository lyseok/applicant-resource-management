package kr.or.ddit.recruitment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SampleTest {

	@GetMapping("/sample00.do")
	String sample00() {
		return "recruitment/main/sample00";
	}
	
	@GetMapping("/sample01.do")
	String sample01() {
		return "recruitment/main/sample01";
	}
	
	@GetMapping("/sample02.do")
	String sample02() {
		return "recruitment/main/sample02";
	}
}
