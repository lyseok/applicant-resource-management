package kr.or.ddit.company.recruitment.applicant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.recruitment.notice.service.RecruitService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/company/recruit_applicant")
@RequiredArgsConstructor
public class CompanyApplPasserController {

	private RecruitService recruitService;
	
	@GetMapping("/list")
	public String noticeList() {
		return "company/recruitment/applmanage/manageList";
	}
}
