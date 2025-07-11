package kr.or.ddit.company.recruitment.interviewscore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.interviewscore.service.InterviewScoreService;
import kr.or.ddit.dto.InterviewScoreListDTO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.InterviewScoreVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/interviewscore")
public class InterviewScoreAjaxController {
	private final InterviewScoreService service;
	private final ErrorsUtils errorsUtils;
	
	@PostMapping
	public ResponseEntity<?> settingInterviewScore(@Valid @RequestBody InterviewScoreListDTO dto, BindingResult bindingResult) {
		log.info("==========>{}", dto);
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		List<InterviewScoreVO> list = dto.getInterviewScoreList();
		service.createInterviewScoreList(list);
		return ResponseEntity.ok(list);
	}
}
