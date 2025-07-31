package kr.or.ddit.member.community.companyReview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.dto.CompanyReviewDTO;
import kr.or.ddit.dto.CompanyReviewStatsDTO;
import kr.or.ddit.member.community.companyReview.service.MemberCompanyReviewService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import kr.or.ddit.vo.resume.CareerVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/company_review")
public class MemberCompanyReviewAjaxController {
	private final MemberCompanyReviewService companyReviewService;
	private final ErrorsUtils errorsUtils;
	
	   @GetMapping
	   public Map<String, Object> reviewPage(
				@RequestParam int page,
				@RequestParam int pageSize,
				@RequestParam(required = false) String sort,
				@RequestParam(required = false) String keyword,
				@RequestParam(required = false) String industry
			){
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("startRow", (page - 1) * pageSize);
				paramMap.put("endRow", page * pageSize);
				paramMap.put("sort", sort);
				paramMap.put("keyword", keyword);
				paramMap.put("industry", industry);
		   
		  return companyReviewService.readCompanyInfoWithReviewInfoPage(paramMap);
		  
	   }
	   
	   
	   @GetMapping("/my_career")
	   public List<ResumeVO> myCareer(){
		   List<ResumeVO> careers = companyReviewService.readResumeWithCareers();
		   return careers;
	   }
	   
	   @GetMapping("/career/{careerNo}")
	   public CareerVO myCareerDetail(@PathVariable String careerNo) {
		   CareerVO career = companyReviewService.readCareerDetail(careerNo);
		   return career;
	   }
	
	   @GetMapping("/detail/{company}")
	    public CompanyReviewStatsDTO reviewChart(@PathVariable String company) {
		   CompanyReviewStatsDTO dto = companyReviewService.readCompanyReviewStats(company);
		   return dto;
	   }
	   
	   @GetMapping("/info/{company}")
	   public Map<String, Object> companyReviewInfo(@PathVariable String company){
		   Map<String, Object> info = companyReviewService.readCompanyWithReviewInfo(company);
		   return info;
	   }
	
	
	  @GetMapping("/my_review/delete/{reviewNo}")
	  public ResponseEntity<String> deleteMyReview(@PathVariable("reviewNo") String reviewNO) {
		  CompanyReviewVO companyReview = new CompanyReviewVO();
		  companyReview.setCompanyReviewNo(reviewNO);
		  boolean success = companyReviewService.updateRemoveStatusMyCompanyReview(companyReview);
		  
		  if (success) {
			  return ResponseEntity.ok("success");
		  }else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		  }
		  
		  
	  }
	  
	  
	  @PostMapping("/submit")
	  public ResponseEntity<?> saveVideoInterview(
	  	@Valid @RequestBody CompanyReviewDTO reviewDTO
	  	, BindingResult bindingResult
	  ) {
	  	log.info("{}", reviewDTO);
	  	
	  	if(bindingResult.hasErrors()) {
	  		MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
	  		return ResponseEntity.badRequest().body(errors);
	  	}
	  	companyReviewService.createCompanyReview(reviewDTO);
	      return ResponseEntity.ok("ok");
	  }
	  
	 
}
