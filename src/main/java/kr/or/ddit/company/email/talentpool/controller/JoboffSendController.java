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
import kr.or.ddit.company.payment.product.service.PaymentProductService;
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
	private PaymentProductService pservice;
	
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

	    
//	    log.info("로그인된 아이디 : {}", service.getUserId());
//	    String paymentNo = service.getPaymentNo(service.getUserId());
//	    PaymentVO pvo = service.selectPaymentByPk(paymentNo);
//		
//	    log.info("pvo : {}",pvo);
//		int result = pservice.minusRemaining(pvo.getPaymentNo(), pvo.getProductNo());
//		if (result == 0) {
//		    log.warn("사용 가능 횟수가 0이어서 차감되지 않았습니다.");
//		    return "잔여 메일발송 횟수가 없습니다.";
//		}
		
	    PaymentLogVO lvo = new PaymentLogVO();
		PaymentVO pvo = service.selectStauts(service.getUserId());
//		if(pvo.getUsageAllowed() == -1) {
//			return "success";
//		}
		log.info("수정된 pvo 의 값 : {}", pvo);
		if(pvo.getUsageRemaining() == -1) {
			lvo.setEmailAddress(email);
			lvo.setMessageBody(body);
			lvo.setSubject(subject);
			lvo.setProductNo(pvo.getProductNo());
			lvo.setPaymentNo(pvo.getPaymentNo());
			lvo.setUsedCount(1);
			lservice.insertLog(lvo);		
			mailService.sendOfferMail(email, subject, body);
			return "success";
		}else {
			
		int result = service.minuseaining(pvo.getPaymentNo());
		if(result == 0) {
			log.warn("사용가능 횟수가 0이여서 발송이 되지 않습니다");
			return "잔여 메일발송 횟수가 없습니다.";
		}
		log.info("result 의 값 : {}", result);
		
		}
		lvo.setEmailAddress(email);
		lvo.setMessageBody(body);
		lvo.setSubject(subject);
		lvo.setProductNo(pvo.getProductNo());
		lvo.setPaymentNo(pvo.getPaymentNo());
		lvo.setUsedCount(1);
		lservice.insertLog(lvo);
		log.info("lvo: {}" , lvo);
		mailService.sendOfferMail(email, subject, body);
		service.minuseaining(pvo.getPaymentNo());
		return "success";
	}
}
