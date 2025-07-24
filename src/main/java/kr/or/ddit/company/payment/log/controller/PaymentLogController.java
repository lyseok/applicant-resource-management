package kr.or.ddit.company.payment.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/company/log")
public class PaymentLogController {

	@GetMapping("/list")
	public String listFormUI() {
		return "";
	}
}
