package kr.or.ddit.company.recruitment.companyExam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.company.recruitment.companyExam.service.CompanyExamService;
import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import kr.or.ddit.vo.recruitment.CompanyExamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/company/companyExam")
public class CompanyExamAjaxController {
	private final CompanyExamService companyExamService;
	
	@PostMapping("/exam")
	public ResponseEntity<String> createExam(@RequestBody CompanyExamVO exam) {
		//exam.setUserId(getLoginId());
		
		exam.setUserId("testCompany");
		
		companyExamService.createCompanyExam(exam);
		log.info("exam : {}", exam);
		return ResponseEntity.ok(exam.getComExamNo());
		
	}
	
	
	@PostMapping("/question")
	public ResponseEntity<String> createExamQuestion(@RequestBody ComExamQuestionsVO examQuestion){
		companyExamService.createCompanyExamQuestions(examQuestion);
		log.info("examQuestion: {}", examQuestion);
		return ResponseEntity.ok(examQuestion.getComQuestionsNo());
	}
	
	@PostMapping("/option")
	public ResponseEntity<String> createExamOption(@RequestBody ComExamOptionVO examOption){
		companyExamService.createCompanyExamOptions(examOption);
		log.info("examOption : {}", examOption);
		return ResponseEntity.ok("시험 등록 성공");
	}
	
	
	   private String getLoginId() {
	        return SecurityContextHolder.getContext().getAuthentication().getName();
	    }
}
