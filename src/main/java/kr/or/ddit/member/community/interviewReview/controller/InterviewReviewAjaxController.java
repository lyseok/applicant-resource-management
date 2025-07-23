package kr.or.ddit.member.community.interviewReview.controller;

import java.util.List;
import java.util.Map;

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
import kr.or.ddit.dto.InterviewReviewDTO;
import kr.or.ddit.member.community.interviewReview.service.InterviewReviewService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.community.InterviewReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/interview/review")
public class InterviewReviewAjaxController {
	private final InterviewReviewService interviewReviewService;
	private final ErrorsUtils errorsUtils;
	
	
	@GetMapping()
	public List<InterviewReviewVO> reviewList(){
		List<InterviewReviewVO> reviewList = interviewReviewService.readInterviewReviewAllList();
		return reviewList;
	}

	@GetMapping("/{interviewNo}")
	public Map<String, Object> getInterviewInfo(@PathVariable String interviewNo){
		return interviewReviewService.readInterviewWithCompanyNameByNo(interviewNo);
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> writeInterviewReview(
		@Valid @RequestBody InterviewReviewDTO interviewReviewDTO
		, BindingResult bindingResult
	){
		log.info("interviewReviewDTO------{}", interviewReviewDTO);
		
		if(bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			log.info("bindingResult.hasErrors(): {}", bindingResult.hasErrors());
			return ResponseEntity.badRequest().body(errors);
		}
		interviewReviewService.registerInterviewReview(interviewReviewDTO);
		return ResponseEntity.ok("ok");
	}
}
