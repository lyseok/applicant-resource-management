package kr.or.ddit.company.payment.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentListController {

	@GetMapping("/product_list")
	String productForm() {
		return "company/payment/product/PaymentList";
	}
}
