package kr.or.ddit.company.recruitment.companyExam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.recruitment.companyExam.service.CompanyExamService;
import kr.or.ddit.validate.utils.ErrorsUtils;
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
	private final ErrorsUtils errorsUtils;
	

	
	@GetMapping("/list")
	public List<CompanyExamVO> examMyList(){
		List<CompanyExamVO>	list =  companyExamService.readCompanyExamListById(getUserId());
		log.info("list : {}", list);
		return list;
	}
	
	@GetMapping("/delete/{examNo}")
	public ResponseEntity<?> examDelete(@PathVariable("examNo") String examNo) {
		log.info("examNo : {}", examNo);
		boolean success = companyExamService.editExamDeleteDate(examNo);
		if (success) {
			return ResponseEntity.ok("삭제 성공");
		}else {
			return ResponseEntity.ok("삭제 실패");
		}
	}
	
	
	@GetMapping("/detail/{examNo}")
	public CompanyExamVO examDetail(@PathVariable("examNo") String examNo){
		log.info("examNo:{}", examNo);
		CompanyExamVO exam = companyExamService.readCompanyExamWithQuestionAndOption(examNo);
		log.info("exam : {}", exam);
		
		return exam;
	}
	
	
	@PostMapping("/create")
    public ResponseEntity<?> createExam(
            @Valid @RequestBody CompanyExamVO exam,
            BindingResult br
    ) {
        if (br.hasErrors()) {
            MultiValueMap<String, String> errors = errorsUtils.errorsToMap(br);
            return ResponseEntity.badRequest().body(errors);
        }

        exam.setUserId(getUserId());
        companyExamService.createCompanyExam(exam);
        return ResponseEntity.ok("등록 성공");
    }
	
	 @PutMapping("/edit/{examNo}")
	    public ResponseEntity<?> editExam(
	            @PathVariable String examNo,
	            @Valid @RequestBody CompanyExamVO exam,
	            BindingResult br
	    ) {
	        if (br.hasErrors()) {
	            MultiValueMap<String, String> errors = errorsUtils.errorsToMap(br);
	            return ResponseEntity.badRequest().body(errors);
	        }

	        exam.setComExamNo(examNo);
	        boolean success = companyExamService.editCompanyExamInfo(exam);
	        if (!success) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok("수정 성공");
	    }
	
	
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}
