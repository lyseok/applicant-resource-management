package kr.or.ddit.company.common.salaryManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.common.salaryManagement.service.SalaryManagementService;
import kr.or.ddit.vo.common.SalaryVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/salary_management")
public class SalaryManagementAjaxController {
	private final SalaryManagementService salaryManagementService;
	
	
}
