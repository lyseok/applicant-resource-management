package kr.or.ddit.member.community.companyReview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.community.companyReview.service.CompanyReviewService;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/companyReview")
@Slf4j
@RequiredArgsConstructor
public class CompanyReviewController {
	private final CompanyReviewService service;
	
	@GetMapping
	public String review(Model model) {
		List<CompanyVO> companyList = service.readCompanyList();
		log.info("회사 리스트 : {}", service.readCompanyList());
		model.addAttribute("companyList", companyList);
		return "member/community/companyReview/companyReviewList";
	}
	
	
	
	 @GetMapping("/detail/{id}") 
	 public String review(Model model, @PathVariable("id") String id) {
	 
	 log.info("리뷰 리스트 : {}", service.readCompanyReviewQuestionList(id));
	  List<CompanyReviewQuestionVO> list = service.readCompanyReviewQuestionList(id);
	  model.addAttribute("list",list);
	  return "member/community/companyReview/companyReviewDetail";
	  
	 }
	 
	 
	 
	 @GetMapping("/insert")
	 public String reviewFormUI() {
		return  "member/community/companyReview/companyReviewForm";
	 }
	 
	 
	 
//	@PostMapping()
//	public String formProcess() {
//		
//	}
	 
}
