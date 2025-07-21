package kr.or.ddit.company.common.salaryManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping
	public List<SalaryVO> salaryList(){
		List<SalaryVO> list = salaryManagementService.readSalaryListById(getUserId());
		return list;
	}
	
	@PostMapping
	public ResponseEntity<?> createSalary(@RequestBody List<SalaryVO> salaryList){
		int count = salaryManagementService.createSalary(salaryList, getUserId());
		
		return ResponseEntity.ok("성공 ");
	}
	
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
	
	
}
