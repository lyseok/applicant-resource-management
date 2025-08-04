package kr.or.ddit.company.common.company.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.common.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ajax")
@Slf4j
@RequiredArgsConstructor
public class CompanyAjaxController {

	private final CompanyService service;
	
	@GetMapping("/industry")
	public List<Map<String, Object>> getInduCode(){
		
		return service.readInduCodeAndClassCode();
	}
	
	@GetMapping("{brNumber}")
	public String brNumberCheck(@PathVariable String brNumber) {
		int cnt = service.duplicatedBrNo(brNumber);
		String msg = null;
		
		if(cnt>0) {
			msg = "이미 가입한 사업자입니다";
		}else {
			msg = "사용 가능한 사업자 등록 번호입니다!";
		}
		return msg;
	}
}
