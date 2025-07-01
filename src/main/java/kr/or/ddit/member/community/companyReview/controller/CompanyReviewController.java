package kr.or.ddit.member.community.companyReview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.community.companyReview.service.CompanyReviewService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
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
	  List<CompanyReviewQuestionVO> list = service.readCompanyReviewAnswerList(id);
	  model.addAttribute("list",list);
	  return "member/community/companyReview/companyReviewDetail";
	  
	 }
	 
	 
	 
	 @GetMapping("/insert")
	 public String reviewFormUI(Model model) {
		 String code = "REVU";
		 List<CmnCodeGroupVO> questionList = service.readCompanyReviewQuestionList(code);
		 model.addAttribute("questionList", questionList);
		 
		return  "member/community/companyReview/companyReviewForm";
	 }
	 
	 
	 //여기부터 수정
	@PostMapping()
	public String formProcess(
			@Validated(InsertGroup.class)@ModelAttribute CompanyReviewQuestionVO question
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			) {
		
		return null;
		
	}
	 
}
