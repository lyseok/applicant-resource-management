package kr.or.ddit.member.recruitment.applicant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.recruitment.applicant.service.MemberApplicantService;
import kr.or.ddit.vo.recruitment.PasserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/ajax/applicant")
@Slf4j
public class MemberApplicantAjaxController {
	
	private final MemberApplicantService service;

	@GetMapping("/list")
	public List<Map<String, Object>> applicatedPage(){
		List<Map<String, Object>> data = service.readApplicatedList();
		return data;
	}
	
	@GetMapping("/step")
	public List<Map<String, Object>> applicatedStep(){
		List<Map<String, Object>> data = service.readMyApplicatedStep();
		return data;
	}
	
	@PostMapping("/accept")
	public ResponseEntity<?> updateAccept(@RequestBody PasserVO vo){
		try {
			service.updateAccept(vo);
			return ResponseEntity.ok("Success");
		} catch (Exception e) {
			log.error("채용 수락 처리 실패",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
		}
	}
}
