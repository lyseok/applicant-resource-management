package kr.or.ddit.company.email.talentpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.company.email.talentpool.service.JoboffSendService;
import kr.or.ddit.company.payment.link.service.PaymentProductLinkService;
import kr.or.ddit.company.payment.log.service.PaymentLogService;
import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.common.PaymentLogVO;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;
@RequestMapping("/talentpool")
@Controller
@Slf4j
public class JoboffSendController {
	
	@Autowired
	private JoboffSendService mailService;

	@Autowired
	private PaymentServiceImpl service;
	
	@Autowired
	private PaymentProductLinkService pservice;
	
	@Autowired
	private PaymentLogService lservice;
	
	@PostMapping("/sendOfferMail")
	@ResponseBody
	public String sendOffer(
			@RequestParam String email,
	          @RequestParam String position,
	          @RequestParam String job,
	          @RequestParam String content
	           ) {

	    String subject = "[이직 제안] " + position + " - " + job;
	    String body = "안녕하세요!\n\n" + content + "\n\n감사합니다.";

	    
	    log.info("로그인된 아이디 : {}", service.getUserId());
	    String paymentNo = service.getPaymentNo(service.getUserId());
	    PaymentVO pvo = service.selectPaymentByPk(paymentNo);
		
	    log.info("pvo : {}",pvo);
		int result = pservice.minusRemaining(pvo.getPaymentNo(), pvo.getProductNo());
		if (result == 0) {
		    log.warn("사용 가능 횟수가 0이어서 차감되지 않았습니다.");
		    return "잔여 메일발송 횟수가 없습니다.";
		}
		PaymentLogVO lvo = new PaymentLogVO();
		lvo.setEmailAddress(email);
		lvo.setMessageBody(body);
		lvo.setSubject(subject);
		lvo.setProductNo(pvo.getProductNo());
		lvo.setPaymentNo(pvo.getPaymentNo());
		lvo.setUsedCount(1);
		log.info("lvo: {}" , lvo);
		lservice.insertLog(lvo);
		mailService.sendOfferMail(email, subject, body);
		pservice.minusRemaining(pvo.getPaymentNo(), pvo.getProductNo());
		return "success";
	}
}
