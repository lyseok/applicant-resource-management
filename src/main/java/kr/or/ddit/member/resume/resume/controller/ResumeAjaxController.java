package kr.or.ddit.member.resume.resume.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.resume.resume.service.ResumeService;
import kr.or.ddit.vo.project.PrjAplcntVO;
import kr.or.ddit.vo.recruitment.ApplicantVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/resume")
@RequiredArgsConstructor
public class ResumeAjaxController {
	private final ResumeService service;
	
	@GetMapping
	public List<ResumeVO> getResumeList(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		return service.readResumeList(userId);
	}
	
	@PostMapping
	public ResponseEntity<?> applicantRequestLogic(@RequestBody PrjAplcntVO prjAplcnt) {
		service.applicantCopyLogic(prjAplcnt);
		
		
		return ResponseEntity.ok("ok");
	}
	
	
	@PostMapping("/recruit")
	public ResponseEntity<?> recruitApplicate(@RequestBody ApplicantVO applicant) {
		service.recruitApplicate(applicant);
			
			
		return ResponseEntity.ok("ok");
	}
}
