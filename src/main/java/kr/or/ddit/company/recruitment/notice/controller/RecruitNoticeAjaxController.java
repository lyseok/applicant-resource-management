package kr.or.ddit.company.recruitment.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.notice.service.RecruitService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/recruit")
public class RecruitNoticeAjaxController {

	private final RecruitService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping("/notice")
	public ResponseEntity<?> insertNotice(
		@Valid @RequestBody RecruitmentNoticeVO notice
		, BindingResult bindingResult
	){
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		service.createRecruitment(notice);
		return ResponseEntity.ok(notice.getRecruitmentNo());
	}
	
	
	@GetMapping("/list") // 로그인 정보 안쓰고 일단 pathvariable
	public List<Map<String, Object>> myNotice(){
		List<Map<String, Object>> data = service.readMyNotice();
		return data;
	}
	
	@PostMapping("/{recruitmentNo}")
	public ResponseEntity<?> updateDeadLine(@PathVariable String recruitmentNo){
		service.setDeadLine(recruitmentNo);
		return ResponseEntity.ok("ok");
	}
}
