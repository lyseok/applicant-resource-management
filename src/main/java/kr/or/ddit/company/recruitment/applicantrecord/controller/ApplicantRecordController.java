package kr.or.ddit.company.recruitment.applicantrecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/company/applicant_record")
public class ApplicantRecordController {
	
	@GetMapping("/{recruitmentNo}")
	public String applicantRecordPage(@PathVariable String recruitmentNo, Model model) {
		model.addAttribute("recruitmentNo", recruitmentNo);
		return "company/recruitment/applicantRecode/applicantRecordList";
	}
}
