package kr.or.ddit.company.statistics.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.statistics.service.CompanyStatisticsService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/company/statistics")
@RequiredArgsConstructor

public class CompanyStatisticsAjaxController {
	private final CompanyStatisticsService companyStatisticsService;
	
	@GetMapping
	public CompanyVO getCompanyInfo() {
		return companyStatisticsService.readCompanyById();
	}
	@GetMapping("/recruitment_status")
	public Map<String, Object> getRecruitmentStatus(){
		return companyStatisticsService.readRecruitmentStatusById();
	}
}
