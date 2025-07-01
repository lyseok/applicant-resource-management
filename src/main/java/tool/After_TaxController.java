package tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("AfterTax.do")
public class After_TaxController {

	@GetMapping
	String AfterTax() {
		return "tool/after_tax";
	}
}
