package kr.or.ddit.company.payment.paymentlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.payment.paymentlist.service.PaymentListService;
import kr.or.ddit.vo.common.PaymentListVO;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
@RequestMapping("/company/paymentList")
public class PaymentListController {

	@Autowired
	PaymentListService service;
	
	@GetMapping
	String ListForm(Model model) {
		
		List<PaymentVO> comPayList = service.selectPaymentCompany("corp03");
		
		model.addAttribute("comPayList" ,comPayList);
		log.info("리스트 정보 :" , comPayList);
		return "company/payment/paymentlist/PaymentList";
	}
	
	@GetMapping("/paymentDetail")
	String detail() {
		return "company/payment/paymentlist/PaymentDetail";
	}
}
