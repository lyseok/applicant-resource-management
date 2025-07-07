package kr.or.ddit.company.recruitment.talentpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.recruitment.talentpool.service.CompanyTalentService;
import kr.or.ddit.vo.resume.ResumeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class CompanyTalentPoolDetailController {

	@Autowired
	private CompanyTalentService CTservice; 
	
	@GetMapping("/TalentPool/detail/{no}")
	public String detail(@PathVariable String no, Model model) {
		ResumeVO detail = CTservice.selectResumeDetail(no);
		log.info("{}", detail);
		
	    model.addAttribute("detail", detail); // 이 이름에 주의
	    log.info("디테일: {}",detail);
	    return "company/recruitment/TalentPoolDetail";
	}
}