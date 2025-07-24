package kr.or.ddit.ajax.recruitment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ajax.recruitment.service.AjaxRecruitmentNoticeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/recruit")
public class AjaxRecruitmentNoticeController {
	private final AjaxRecruitmentNoticeService service;
	
	@GetMapping("/realtime")
	public ResponseEntity<?> getAllRecruitmentNotice(@RequestParam int page, @RequestParam int pageSize, @RequestParam String sort){
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("startRow", (page - 1) * pageSize);
		params.put("endRow", page * pageSize);
		params.put("sort", sort);
		
		Map<String, Object> resp = service.readRecruitmentNoticeDtoList(params);
		
		return ResponseEntity.ok(resp);
	}
}
