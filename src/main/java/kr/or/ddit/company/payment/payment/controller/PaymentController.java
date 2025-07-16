package kr.or.ddit.company.payment.payment.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.Response;

import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.company.payment.payment.service.TossPaymentService;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/company/payment")
public class PaymentController {

	@Autowired
	private TossPaymentService tossPaymentSerice;
	
	@Autowired
	private PaymentServiceImpl service;
	
//	@GetMapping
//	public String productlist() {
//		
//		return "company/payment/payment/TestView";
//	}
//	
	@GetMapping("/main")
	String mainForm(
			Model model , Authentication auth
			) {
		
		List<PaymentVO> purchaseList = service.selectMyPaymentList(service.getUserId());
		model.addAttribute("purchaseList",purchaseList);
		return "company/payment/payment/PaymentMain";
		}
	
	
	@GetMapping("/success")
	String Test(
			@RequestParam String paymentKey,
			@RequestParam String orderId,
			@RequestParam int amount,
			@RequestParam String productNo,
			Model model
			) {
		String bodyJson = String.format(
				"{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
				paymentKey, orderId, amount
				);
		
		String pamount = (amount + "");
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
			    .header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString(bodyJson))
			    .build();
		
			HttpResponse<String> response = null;
			JsonNode jsonNode = null;
			PaymentVO vo = new PaymentVO();
			// 결제상품 번호를 등록하려면 먼저 샘플을 만들어야 하기에 insert대기
			int totalAmount =0;
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			if(response != null) {				
				log.info("결제 응답 : " + response.body());
				model.addAttribute("paymentResult",response.body());			
			
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				jsonNode = objectMapper.readTree(response.body());
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			if(jsonNode != null) {
				String orderName = jsonNode.get("orderName").asText();
				String method = jsonNode.get("method").asText();
				int RtotalAmount = amount;
				
				model.addAttribute("orderName",orderName);
				model.addAttribute("method",method);
				model.addAttribute("RtotalAmount",RtotalAmount);
				log.info("model 의 값들 : {}" ,model);
				String paymentMethod = jsonNode.get("method").asText();
				vo.setProductNo(productNo);	
				vo.setPaymentPay(pamount);
				vo.setPaymentMethod(paymentMethod);
				vo.setTossPaymentKey(paymentKey);
				service.insertPayment(vo);
				log.info("insert에 들어갈 값 : {}", vo);
			}
			}
			else {
				log.info("겔제응답이 없ㅇ름");
			}
	
		return "company/payment/payment/SuccessPayment";
	}
	
	@GetMapping("/executeBilling")		// 정기결제
	public String executeBilling(
			@RequestParam String billingKey,
			@RequestParam String customerKey,
			@RequestParam int amount ,
			@RequestParam String productNo,
			Model model
			){
		ObjectMapper mapper = new ObjectMapper();
		String orderId = "SUBSCRIPTION_" + System.currentTimeMillis();
		
		// JSON 바디 생성
		String requestBody ="";
		
		try {
			requestBody = mapper.writeValueAsString(Map.of(
					"customerKey" , customerKey,
					"amount" , amount,
					"orderId", orderId,
					"orderName", "이건 상품명을 넣을건데 방법생각좀",
					"customerEmail" , "test용@이메일.com",
					"customerName" , service.getUserId()
					));
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "요청 본문 생성 실패");
			return "company/payment/payment/BillingResult";	
		}
		
	
	HttpRequest request = HttpRequest.newBuilder()
			 .uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
	            .header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==") // secretKey base64
	            .header("Content-Type", "application/json")
	            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
	            .build();
	
	// 실행 및 응답처리
	HttpResponse<String> response = null ;
	
	try {
		response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	}catch(IOException | InterruptedException e) {
		e.printStackTrace();
		model.addAttribute("error", "정기결제 요청 실패");
		return "";
		
	}
	
	if(response != null && response.body() != null) {
		log.info("Billing 결제 응답 : {} ", response.body());
		
		try {
			JsonNode jsonNode = mapper.readTree(response.body());
			
			String method = jsonNode.get("method").asText();
			String orderName = jsonNode.get("orderName").asText();
			String payKey = jsonNode.get("paymentKey").asText();
			int paidAmount = jsonNode.get("totalAmount").asInt();
			
			model.addAttribute("method",method);
			model.addAttribute("orderName",orderName);
			model.addAttribute("paidAmount",paidAmount);
			
			// 결제내역 저장
			PaymentVO vo = new PaymentVO();
			vo.setProductNo(productNo);
			vo.setPaymentPay(String.valueOf(paidAmount));
			vo.setPaymentMethod(method);
			vo.setTossPaymentKey(payKey);
			service.insertPayment(vo);
			
			log.info("정기결제 저장완료 : {}" ,vo);
		}catch(IOException e) {
			e.printStackTrace();
			model.addAttribute("error","응답 파싱 실패");
		}
		
	}else {
		model.addAttribute("error", "응답없음");
		log.warn("응답이 null 또는 body가 없습니다.");
	}
	return "company/payment/payment/BillinbResult";
	}
}
