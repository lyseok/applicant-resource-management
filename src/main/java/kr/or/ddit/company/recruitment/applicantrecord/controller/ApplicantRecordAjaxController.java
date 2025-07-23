package kr.or.ddit.company.recruitment.applicantrecord.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.applicantrecord.service.ApplicantRecordService;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/applicant/record")
@RequiredArgsConstructor
@Slf4j
public class ApplicantRecordAjaxController {
	
	private final ApplicantRecordService service;

	@GetMapping("/{recruitmentNo}")
	public List<Map<String, Object>> getApplicantData(@PathVariable String recruitmentNo){
		return service.getApplicantsByRecruitment(recruitmentNo);
	}
	
	@PostMapping("/pass")
	public ResponseEntity<?> passStep(@RequestBody List<ApplicantRecordVO> applList) {
		try {
			for(ApplicantRecordVO appl : applList) {
				log.info("{}", appl);
				service.updateNextStep(appl);
			}
			return ResponseEntity.ok("Success");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail");
		}
	}
	
	@GetMapping("/passer/{recruitmentNo}")
	public List<PasserVO> getPasser(@PathVariable String recruitmentNo){
		return service.selectPasserByRecruitment(recruitmentNo);
	}
	
	@PostMapping("/resume")
	public List<ResumeVO> getResumeByApplicantIds(@RequestBody List<String> applicantIds){
		return service.getResumeByApplicantId(applicantIds);
	}
	
	@PostMapping("/{applicantId}")
	public ResponseEntity<?> updateResumeView(@PathVariable String applicantId){
		service.updateResumeView(applicantId);
		return ResponseEntity.ok("Success");
	}
	
	@PostMapping("/hiredate")
	public ResponseEntity<?> updateHireDate(@RequestBody List<PasserVO> list){
		try {
			list.forEach(p-> service.updateHireDate(p));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
		}
	}
}
