package kr.or.ddit.member.common.companyDetailView.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.dto.CompanyOpProfitDTO;
import kr.or.ddit.dto.CompanySalaryDTO;
import kr.or.ddit.dto.CompanySalesDTO;
import kr.or.ddit.member.common.companyDetailView.service.CompanyDetailViewService;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
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
	
	@GetMapping("/sales/{userId}")
	public List<CompanySalesDTO> companyGetSalesInfo(@PathVariable("userId") String userId){
		List<CompanySalesDTO> sales = companyDetailViewService.readCompanySalesInfoById(userId);
		log.info("sales----{}", sales);
		return sales;
	}
	
	@GetMapping("/profit/{userId}")
	public List<CompanyOpProfitDTO> companyGetProfitInfo(@PathVariable("userId") String userId){
		List<CompanyOpProfitDTO> profit = companyDetailViewService.readCompanyProfitInfoById(userId);
		log.info("profit----{}", profit);
		return profit;
	}
	

	@GetMapping("/notice/{userId}")
	public List<RecruitmentNoticeVO> companyGetNoticeInfo(@PathVariable("userId") String userId){
		List<RecruitmentNoticeVO> notice = companyDetailViewService.readRecruitmentNoticeByUserId(userId);
		log.info("notice----{}", notice);
		return notice;
	}
	
	@GetMapping("/salary/{userId}")
	public List<CompanySalaryDTO> companyGetSalaryInfo(@PathVariable("userId") String userId){
		List<CompanySalaryDTO> salaries = companyDetailViewService.readSalaryStatisticsById(userId);
		log.info("salary----{}", salaries);
		return salaries;
	}
	
	@GetMapping("/top_notice/{userId}")
	public List<Map<String, Object>> companyTopNotice(@PathVariable("userId") String userId){
		return companyDetailViewService.readTopFiveJobNotice(userId);
	}
	
}
