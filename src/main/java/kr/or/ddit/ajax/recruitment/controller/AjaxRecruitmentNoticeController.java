package kr.or.ddit.ajax.recruitment.controller;

import java.util.HashMap;
import java.util.List;
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
	
	@GetMapping("/search")
	public ResponseEntity<?> searchRecruitmentNotice(
		@RequestParam int page,
	    @RequestParam int pageSize,
	    @RequestParam String sort,
	    @RequestParam(required = false) List<String> districtCode,   
	    @RequestParam(required = false) List<String> jobCode,
	    @RequestParam(required = false) String yearCode,
	    @RequestParam(required = false) String keyword
    ){
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("startRow", (page - 1) * pageSize);
		params.put("endRow", page * pageSize);
		params.put("sort", sort);
		params.put("districtCode", districtCode);
		params.put("jobCode", jobCode);
		params.put("yearCode", yearCode);
		params.put("keyword", keyword);
		
		Map<String, Object> resp = service.searchRecruitmentNoticeList(params);
		
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/main_p")
	public ResponseEntity<?> mainPRecruitmentNotice(){
		Map<String, Object> resp = service.selectMainPRecruitmentNoticeList();
		
		return ResponseEntity.ok(resp);
	}
	@GetMapping("/main_middle")
	public ResponseEntity<?> mainMiddleRecruitmentNotice(){
		Map<String, Object> resp = service.selectMainMiddleRecruitmentNoticeList();
		
		return ResponseEntity.ok(resp);
	}
	@GetMapping("/main_bottom")
	public ResponseEntity<?> mainBottomRecruitmentNotice(){
		Map<String, Object> resp = service.selectMainBottomRecruitmentNoticeList();
		
		return ResponseEntity.ok(resp);
	}
	
}
