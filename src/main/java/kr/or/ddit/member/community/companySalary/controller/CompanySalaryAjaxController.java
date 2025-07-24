package kr.or.ddit.member.community.companySalary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.community.companySalary.service.CompanySalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/company_salary")
public class CompanySalaryAjaxController {
	
	private final CompanySalaryService salaryService;
	
	@GetMapping
	public List<Map<String, Object>> getCompanyAllSalary(){
		return salaryService.readSalaryListAllCompany();
	}
	
	

}
