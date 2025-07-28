package kr.or.ddit.member.community.companyReview.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Controller
@RequestMapping("/member/company_review")
@Slf4j
@RequiredArgsConstructor
public class MemberCompanyReviewController {
	
	@GetMapping
	public String review() {
		return "member/community/companyReview/companyReviewList";
	}
	
	@GetMapping("/my_review")
	public String myReview(Model model) {
		return "member/community/companyReview/myCompanyReviewList";
	}
	
	
	
	 @GetMapping("/detail") 
	 public String reviewDetail() {
		return "member/community/companyReview/companyReviewDetail";
	 }
	 
	  
	 @GetMapping("/form")
	 public String reviewForm() {
	    	return "member/community/companyReview/companyReviewForm";
	 }
	 
	 
	 
	 
}
