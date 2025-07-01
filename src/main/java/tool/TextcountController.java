package tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/textCount.do")
public class TextcountController {

	@GetMapping
	String CountingLetters() {
		return "tool/textcount";
	}

}
