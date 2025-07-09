package kr.or.ddit.company.recruitment.position.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.position.service.RecruitPositionService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/recruit")
@RequiredArgsConstructor
public class RecruitPositionAjaxController {

	private final RecruitPositionService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping("/{recruitmentNo}/positions")
	public ResponseEntity<?> insertPosition(
		@PathVariable String recruitmentNo
		, @Valid @RequestBody List<RecruitmentPositionVO> positions
		, BindingResult bindingResult
	){
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		for(RecruitmentPositionVO position : positions) {
			position.setRecruitmentNo(recruitmentNo);
			service.createRecruitPosition(position);
		}
		
		return ResponseEntity.ok("position ok");
	}
}
