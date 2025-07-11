package kr.or.ddit.member.community.companyReview.controller;



import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.community.companyReview.service.MemberCompanyReviewService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.community.CompanyReviewQuestionVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/member/companyReview")
@Slf4j
@RequiredArgsConstructor
public class MemberCompanyReviewController {
	private final MemberCompanyReviewService companyReviewService;

	
	@GetMapping
	public String review(Model model) {
		List<CompanyVO> companyList = companyReviewService.readCompanyList();
		log.info("회사 리스트 : {}",companyReviewService.readCompanyList());
		model.addAttribute("companyList", companyList);
		
    	List<ResumeVO> resumes = companyReviewService.readResumeWithCareers(getLoginId());
    	log.info("resumes------------------------{}", resumes);
    	model.addAttribute("resumes", resumes);
		return "member/community/companyReview/companyReviewList";
	}
	
	@GetMapping("/myReview")
	public String myReview(Model model) {
	    log.info("🔐 요청자: {}", getLoginId());
    	List<CompanyReviewVO> myReviewList = companyReviewService.readMyCompanyReviewList(getLoginId());
    	MemberVO member =  companyReviewService.readMemberById(getLoginId());
    	log.info("{}", myReviewList);
    	log.info("{}", member);
    	
    	model.addAttribute("myReviewList", myReviewList);
    	model.addAttribute("member", member);
		return "member/community/companyReview/myCompanyReviewList";
	}
	
	
	
	 @GetMapping("/detail/{id}") 
	 public String review(Model model, @PathVariable("id") String id) {
		String no = "REVU";
    	List<CmnCodeVO> questionList = companyReviewService.readCmnCodeGroupQuestionList(no);
    	log.info("{}",questionList);
    	model.addAttribute("questionList",questionList);
		CompanyVO company = companyReviewService.readCompany(id);
		model.addAttribute("company", company);
		model.addAttribute("id", id);
		return "member/community/companyReview/companyReviewDetail";
	 }
	 
	  
	 
	 
	 
	 
	 @GetMapping("/form/{careerNo}")
	 public String reviewFormUI(@PathVariable String careerNo, Model model) {
		 	CareerVO career = companyReviewService.readCareerDetail(careerNo);
		 	log.info("career------------------------{}", career);
		 	model.addAttribute("career", career);
		 	String no = "REVU";
	    	List<CmnCodeVO> questionList = companyReviewService.readCmnCodeGroupQuestionList(no);
	    	model.addAttribute("questionList",questionList);
	    	return "member/community/companyReview/companyReviewForm";
	 }
	 
	 
	 
	 
	
	@PostMapping()
	public String formProcess(
			@Validated(InsertGroup.class) @ModelAttribute CompanyReviewVO companyReview
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			) {
		 log.debug(">>> formProcess: submitted CompanyReviewVO={}", companyReview);
			if(errors.hasErrors()) {
			    log.debug(">>> formProcess: validation errors, returning to form");
				return "member/community/companyReview/companyReviewForm";
			}
			companyReview.setUserId(getLoginId());
			companyReviewService.createCompanyReview(companyReview);
			redirectAttributes.addFlashAttribute("msg", "리뷰가 등록되었습니다.");
	        return "redirect:/member/companyReview";
		
	}
	
	


    private String getLoginId() {
    	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           String userId = auth.getName();
           log.debug(">>> getLoginId: {}", userId);
           return userId;
    }
	 
}
