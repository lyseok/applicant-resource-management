package kr.or.ddit.ats.companyReview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ats.companyReview.service.CompanyReviewService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		return "ats/companyReview/companyReviewList";
	}
	
	
	/*
	 * @GetMapping("/detail/{id)") public String review(Model model, String id) {
	 * 
	 * //model.addAttribute(); //return "ats/companyReview/companyReviewDetail"; }
	 */
}
