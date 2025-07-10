package kr.or.ddit.company.recruitment.process.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.process.dto.ProcessWrapper;
import kr.or.ddit.company.recruitment.process.service.RecruitProcessService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitProcessAjaxController {
	
	private final RecruitProcessService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping("/{recruitmentNo}/process")
	public ResponseEntity<?> insertRecruitProcess(
		@PathVariable String recruitmentNo
		, @Valid @RequestBody ProcessWrapper wrapper
		, BindingResult bindingResult
	){
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		service.createRecruitProcess(recruitmentNo, wrapper.getEntries());
		return ResponseEntity.ok("ok");
	}
}
