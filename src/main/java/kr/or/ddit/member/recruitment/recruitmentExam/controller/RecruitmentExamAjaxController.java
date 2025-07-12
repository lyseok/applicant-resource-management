package kr.or.ddit.member.recruitment.recruitmentExam.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.recruitment.recruitmentExam.service.RecruitmentExamService;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ajax/member/recruitment_exam")
public class RecruitmentExamAjaxController {
	private final RecruitmentExamService recruitmentExamService;
	
	@GetMapping
	public List<RecruitmentExamVO> recruitmentExamList(){
		List<RecruitmentExamVO> list = recruitmentExamService.readMyRecruitExams(getUserId());
		log.info("list------{}", list);
		return list;
	}
	
	@GetMapping("exam-view/{recruitExamNo}")
	public List<RecruitmentExamVO> recruitExamQuestionWithOption(@PathVariable("recruitExamNo") String recruitExamNo){
		log.info("시험 번호 --------------{}",recruitExamNo);
		List<RecruitmentExamVO> list = recruitmentExamService.readRecruitExamQuestionWithOptionByNo(recruitExamNo);
		log.info("시험 문제제-------{}", list);
		return list;
	}
	
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
}


