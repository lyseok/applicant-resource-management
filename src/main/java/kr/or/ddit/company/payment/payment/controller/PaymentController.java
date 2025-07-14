package kr.or.ddit.company.payment.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.payment.payment.service.PaymentService;
import kr.or.ddit.company.payment.payment.service.TossPaymentService;
import kr.or.ddit.vo.common.PaymentVO;

@Controller
@RequestMapping("/company/payment")
public class PaymentController {

	@Autowired
	private TossPaymentService tossPaymentService;
	private PaymentService service;
	
	@GetMapping
	public String test() {
		return "company/payment/payment/TestView";
	}
	
	
	@GetMapping("/success")
	public String paymentSuccess(
		@RequestParam String paymentKey,
		@RequestParam String orderId,
		@RequestParam int amount,
		Model model
			) {
		String paymentResult = tossPaymentService.confirmPayment(paymentKey, orderId, amount);
		
		model.addAttribute("paymentResult" ,paymentResult);
		return "successUrl";
	}
	
	
	
	@GetMapping("/test")
	String Test() {
		return "company/payment/payment/SuccessPayment";
	}
	
}
