package kr.or.ddit.company.recruitment.applicantrecord.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.company.recruitment.applicantrecord.service.ApplicantRecordService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applicant/record")
@RequiredArgsConstructor
public class ApplicantRecordAjaxController {
	
	private final ApplicantRecordService service;

	@GetMapping("/{recruitmentNo}")
	public List<Map<String, Object>> getApplicantData(@PathVariable String recruitmentNo){
		return service.getApplicantsByRecruitment(recruitmentNo);
	}
}
