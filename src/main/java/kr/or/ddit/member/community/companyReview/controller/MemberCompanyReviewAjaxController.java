package kr.or.ddit.member.community.companyReview.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.community.companyReview.service.MemberCompanyReviewService;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.community.CompanyReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/companyReview")
public class MemberCompanyReviewAjaxController {
	private final MemberCompanyReviewService companyReviewService;

	
	   @GetMapping("/reviewDetail/{id}")
	    public List<CompanyReviewVO> reviewDetail(@PathVariable String id){
	    	List<CompanyReviewVO> reviewInfo = companyReviewService.readReivewQAList(id);
	    	log.info("{}", reviewInfo);
	    	return reviewInfo;
	    }
	
	  @GetMapping("/reviewQuestion")
	    public List<CmnCodeVO> reviewQ(){
	    	String no = "REVU";
	    	List<CmnCodeVO> qList = companyReviewService.readCmnCodeGroupQuestionList(no);
	    	log.info("{}",qList);
			return qList;
	    }
}
