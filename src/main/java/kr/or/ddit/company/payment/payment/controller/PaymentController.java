package kr.or.ddit.company.payment.payment.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.company.payment.payment.service.PaymentService;
import kr.or.ddit.company.payment.payment.service.TossPaymentService;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/company/payment")
public class PaymentController {

	@Autowired
	private TossPaymentService tossPaymentSerice;
	private PaymentService service;
	
	@GetMapping
	public String productlist() {
		
		return "company/payment/payment/TestView";
	}
	
	
//	@GetMapping("/success")
//	public String paymentSuccess() {
//		return "successUrl";
//	}
//	
	
	
	@GetMapping("/success")
	String Test(
			@RequestParam String paymentKey,
			@RequestParam String orderId,
			@RequestParam int amount,
			Model model
			) {
		String bodyJson = String.format(
				"{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
				paymentKey, orderId, amount
				);
		
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
			    .header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString(bodyJson))
			    .build();
		
			HttpResponse<String> response = null;
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			
			PaymentVO vo = new PaymentVO();
			// 결제상품 번호를 등록하려면 먼저 샘플을 만들어야 하기에 insert대기
			
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			if(response != null) {				
				log.info("결 제 응답 : " + response.body());
				model.addAttribute("paymentResult",response.body());
								
				service.insertPayment(null);
			}
			else {
				log.info("겔제응답이 없ㅇ름");
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
//			JsonNode jsonNode = objectMapper.readTree(response.body());
			
//			String paymentNo= jonNode.get("")asText();
//			String productNo= jonNode.get("")asText();
//			String paymentMethod= jonNode.get("간편결제")asText();
//			String paymentDate= jonNode.get("")asText();
//			String paymentPay= jonNode.get("")asText();
//			String payment= jonNode.get("")asText();
			
		
		return "company/payment/payment/SuccessPayment";
	}
	
}
