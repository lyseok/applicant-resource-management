package kr.or.ddit.member.project.applicant.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.project.applicant.service.PrjAplcntService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ajax/project/applicant")
@RequiredArgsConstructor
public class MemberProjectApplicantAjaxController {
	private final PrjAplcntService service;
	
	@PutMapping("/join")
	public ResponseEntity<?> joinProjectRequest(@RequestBody Map<String, Object> reqData) {
		service.modifyApplicantStatusCode(reqData);
		
		return ResponseEntity.ok("ok");
	}
}
