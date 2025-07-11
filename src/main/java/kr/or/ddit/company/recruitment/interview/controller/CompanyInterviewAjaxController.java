package kr.or.ddit.company.recruitment.interview.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.interview.service.CompanyInterviewService;
import kr.or.ddit.dto.VideoInterviewSaveDTO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.VideoInterviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/interview")
public class CompanyInterviewAjaxController {
	private final CompanyInterviewService service;
	private final ErrorsUtils errorsUtils;
	
	@GetMapping
	public List<InterviewVO> interviewList() {
		return service.readInterviewList();
	}
	
	@GetMapping("/{no}")
	public InterviewVO interviewDetail(@PathVariable String no) {
		InterviewVO data = service.readInterview(no);
		log.info("{}", data);
		return data;
	}
	
	@PostMapping("/video")
	public ResponseEntity<?> saveVideoInterview(@Valid @RequestBody VideoInterviewSaveDTO dto, BindingResult bindingResult) {
		log.info("{}", dto);
		
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		service.createInterviewLogic(dto);
		
	    return ResponseEntity.ok("ok");
	}
}
