package kr.or.ddit.company.email.talentpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.company.email.talentpool.service.JoboffSendService;
@RequestMapping("/talentpool")
@Controller
public class JoboffSendController {
	
	@Autowired
	private JoboffSendService mailService;

	@PostMapping("/sendOfferMail")
	@ResponseBody
	public String sendOffer(@RequestParam String email,
	                        @RequestParam String position,
	                        @RequestParam String job,
	                        @RequestParam String content) {

	    String subject = "[이직 제안] " + position + " - " + job;
	    String body = "안녕하세요!\n\n" + content + "\n\n감사합니다.";

	    mailService.sendOfferMail(email, subject, body);

	    return "success";
	}
}
