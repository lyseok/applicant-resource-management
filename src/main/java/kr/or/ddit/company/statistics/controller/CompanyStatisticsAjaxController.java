package kr.or.ddit.company.statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.statistics.service.CompanyStatisticsService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;

@RequestMapping
@RequiredArgsConstructor
@RestController("/ajax/company/statistics")
public class CompanyStatisticsAjaxController {
	private final CompanyStatisticsService companyStatisticsService;
	
	@GetMapping
	public CompanyVO getCompanyInfo() {
		return companyStatisticsService.readCompanyById();
	}
}
