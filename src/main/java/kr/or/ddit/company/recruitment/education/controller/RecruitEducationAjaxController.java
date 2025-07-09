package kr.or.ddit.company.recruitment.education.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.education.service.RecruitEducationService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/recruit")
@RequiredArgsConstructor
public class RecruitEducationAjaxController {

	private final RecruitEducationService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping("/{recruitmentNo}/education")
	public ResponseEntity<?> insertEducation(
		@PathVariable String recruitmentNo
		, @Valid @RequestBody RecruitmentEducationVO education
		, BindingResult bindingResult
	){
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		service.createRecruitEducation(education);
		return ResponseEntity.ok("education ok");
	}
}
