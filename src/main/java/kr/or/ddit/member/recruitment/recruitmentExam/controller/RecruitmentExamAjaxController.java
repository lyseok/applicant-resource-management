package kr.or.ddit.member.recruitment.recruitmentExam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import kr.or.ddit.common.exception.AlreadyTakenExamException;
import kr.or.ddit.dto.MyRecruitExamDTO;
import kr.or.ddit.dto.RecruitmentExamAnswerDTO;
import kr.or.ddit.member.recruitment.recruitmentExam.service.RecruitmentExamService;
import kr.or.ddit.vo.recruitment.RecruitmentExamScoreResultVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/mypage/recruitment_exam")
public class RecruitmentExamAjaxController {
	private final RecruitmentExamService recruitmentExamService;
	
	@GetMapping
	public List<MyRecruitExamDTO> recruitmentExamList(){
		List<MyRecruitExamDTO> list = recruitmentExamService.readMyRecruitExams(getUserId());
		log.info("list------{}", list);
		return list;
	}
	
	@GetMapping("/questions/{recruitExamNo}")
	public RecruitmentExamVO recruitExamQuestionWithOption(@PathVariable("recruitExamNo") String recruitExamNo){
		log.info("시험 번호 --------------{}",recruitExamNo);
		RecruitmentExamVO exam = recruitmentExamService.readRecruitExamQuestionWithOptionByNo(recruitExamNo);
		log.info("시험 문제제-------{}", exam);
		return exam;
	}
	
	@PostMapping(value = "/submit", consumes = "application/json")
	public RecruitmentExamScoreResultVO submit(
			@RequestBody List<RecruitmentExamAnswerDTO> answers) {
		 RecruitmentExamScoreResultVO result = recruitmentExamService.gradeAndSave(getUserId(), answers);
		 return result;
	}
	
	
	@GetMapping("/result/{recruitExamNo}/{applicantId}")
	public ResponseEntity<RecruitmentExamScoreResultVO> result(
			@PathVariable("recruitExamNo") String recruitExamExamNo
			,@PathVariable("applicantId") String applicantId
	){
		RecruitmentExamScoreResultVO result = 
		recruitmentExamService.readResultByExamAndUser(applicantId, recruitExamExamNo);
		return ResponseEntity.ok(result);
	}
	

	
	
	@ExceptionHandler(AlreadyTakenExamException.class)
	public ResponseEntity<String> examTaken(AlreadyTakenExamException ex){
		return ResponseEntity
			   .status(HttpStatus.CONFLICT)
			   .body(ex.getMessage());
				
	}
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}



//@GetMapping("/step/{recruitExamNo}/{applicantId}")
//public ResponseEntity<?> updateStep(
//		@PathVariable("recruitExamNo") String recruitExamExamNo
//		,@PathVariable("applicantId") String applicantId
//){
//	boolean stepUpdated = recruitmentExamService.editStepApplicationYN(recruitExamExamNo, applicantId);
//
//	if (stepUpdated) {
//		return ResponseEntity.ok("응시여부 업데이트 성공");
//	}else {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("응시 여부 업데이트 실패");
//	}
//}

