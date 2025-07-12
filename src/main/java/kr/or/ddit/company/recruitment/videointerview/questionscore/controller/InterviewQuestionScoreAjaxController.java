package kr.or.ddit.company.recruitment.videointerview.questionscore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.videointerview.questionscore.service.InterviewQuestionScoreService;
import kr.or.ddit.dto.InterviewQuestionScoreListDTO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;

@RequestMapping("ajax/interview/score")
@RestController
@RequiredArgsConstructor
public class InterviewQuestionScoreAjaxController {
	private final InterviewQuestionScoreService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping
	public ResponseEntity<?> saveInterviewScore(@Valid @RequestBody InterviewQuestionScoreListDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		service.createInterviewQuestionScoreList(dto);
		
		return ResponseEntity.ok("ok");
	}
}
