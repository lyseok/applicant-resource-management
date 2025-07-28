package kr.or.ddit.company.recruitment.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		@RequestPart("notice") @Valid RecruitmentNoticeVO notice
		, @RequestPart(value = "recruitThumbnail", required = false) MultipartFile recruitThumbnail
	){
		    notice.setRecruitThumbnail(recruitThumbnail);
		    service.createRecruitment(notice);
		    return ResponseEntity.ok(notice.getRecruitmentNo());
	}
	
	@ControllerAdvice
	public class GlobalErrorHandler {

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<MultiValueMap<String, String>> handleValidation(
	            MethodArgumentNotValidException ex) {

	        MultiValueMap<String, String> errors = new LinkedMultiValueMap<>();
	        ex.getBindingResult().getFieldErrors()
	          .forEach(err -> errors.add(err.getField(), err.getDefaultMessage()));

	        return ResponseEntity.badRequest().body(errors);
	    }
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
