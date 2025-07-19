package kr.or.ddit.member.common.companyDetailView.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.member.common.companyDetailView.service.CompanyDetailViewService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/member/company_view")
public class CompanyDetailViewAjaxController {
	private final CompanyDetailViewService companyDetailViewService;
	
	@GetMapping("{userId}")
	public ResponseEntity<CompanyVO> companyGetInfo(@PathVariable("userId") String userId){
		
		CompanyVO company = companyDetailViewService.readCompanyInfoById(userId);
		log.info("company----{}", company);
		
		return ResponseEntity.ok(company);
		
	
	}
}
