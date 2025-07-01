package tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class Ch_SpellingController {

	@GetMapping
	String Ch_splling() {
		return "tool/ch_spelling";
	}
}
