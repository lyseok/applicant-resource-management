package kr.or.ddit.member.community.companySalary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.dto.CompanySalaryDTO;
import kr.or.ddit.member.community.companySalary.service.CompanySalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/company_salary")
public class CompanySalaryAjaxController {
	
	private final CompanySalaryService salaryService;
	
	/*
	 * @GetMapping public List<Map<String, Object>> getCompanyAllSalary(){ return
	 * salaryService.readSalaryListAllCompany(); }
	 */
	
	@GetMapping
	public ResponseEntity<?> getCompaanyAllSalary(
			@RequestParam int page,
		    @RequestParam int pageSize,
	        @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Integer minSalary,
	        @RequestParam(required = false) Integer maxSalary,
	        @RequestParam String sort
	) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("page", page);
	    params.put("pageSize", pageSize);
	    params.put("keyword", keyword);
	    params.put("minSalary", minSalary);
	    params.put("maxSalary", maxSalary);
	    params.put("sort", sort);
	    return ResponseEntity.ok(salaryService.readSalaryListAllCompanyPaged(params));
	}

	
	@GetMapping("/detail/{userId}")
	public List<CompanySalaryDTO> getSalaryDetail(@PathVariable String userId) {
		List<CompanySalaryDTO> salary = salaryService.readSalaryStatisticsById(userId);
		return salary;
	}
	
	@GetMapping("/similar/{industryType}")
	public List<Map<String, Object>> getSimilarSalary(@PathVariable String industryType){
		return salaryService.readSimilarCompanySalariesList(industryType);
	}

}
