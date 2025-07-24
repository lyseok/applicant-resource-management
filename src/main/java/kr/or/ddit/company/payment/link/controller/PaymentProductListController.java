package kr.or.ddit.company.payment.link.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.company.payment.link.service.PaymentProductLinkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/link")
public class PaymentProductListController {

	PaymentProductLinkService service;
	
	@GetMapping("/list")
	public String check(
		Model model
		
			) {
		return "";
	}
	
}
