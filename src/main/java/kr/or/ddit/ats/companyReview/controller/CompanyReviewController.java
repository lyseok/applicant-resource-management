package kr.or.ddit.ats.companyReview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companyReview")
public class CompanyReviewController {
	@GetMapping
	public String review() {
		return "companyReview/companyReviewList";
	}
}
