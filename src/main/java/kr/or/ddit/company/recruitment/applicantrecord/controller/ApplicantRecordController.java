package kr.or.ddit.company.recruitment.applicantrecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/company/applicant_record")
public class ApplicantRecordController {
	@GetMapping
	public String applicantRecordPage() {
		return "company/recruitment/applicantRecode/applicantRecordList";
	}
}
