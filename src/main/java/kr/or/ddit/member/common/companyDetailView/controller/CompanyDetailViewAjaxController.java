package kr.or.ddit.member.common.companyDetailView.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.member.common.companyDetailView.service.CompanyDetailViewService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/member/company_view")
public class CompanyDetailViewAjaxController {
	private final CompanyDetailViewService companyDetailViewService;
	
	@GetMapping
	public ResponseEntity<CompanyInfoDTO> companyGetInfo(){
		
		
		return null;
		
	}
}
