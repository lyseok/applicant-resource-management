package kr.or.ddit.ajax.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ajax.company.service.AjaxCompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company_name")
public class AjaxCompanyController {
	private final AjaxCompanyService service;
	
	@GetMapping
	public ResponseEntity<?> getCompanyNameList(){
		List<Map<String, Object>> resp = service.readCompanyNameList();
		
		return ResponseEntity.ok(resp);
	}
}
