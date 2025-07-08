package kr.or.ddit.company.recruitment.companyExam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/list")
	public List<CompanyExamVO> examMyList(){
		List<CompanyExamVO>	list =  companyExamService.readCompanyExamListById("testCompany");
		log.info("list : {}", list);
		return list;
	}
	
	@DeleteMapping("/delete{examNo}")
	public ResponseEntity<Void> examDelete(@PathVariable("examNo") String examNo) {
		boolean success = companyExamService.removeCompanyExam(examNo);
		if (success) {
			 return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}
