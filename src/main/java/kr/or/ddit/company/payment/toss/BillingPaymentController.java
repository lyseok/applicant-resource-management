package kr.or.ddit.company.payment.toss;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/Toss")
public class BillingPaymentController {

	@GetMapping("/billing")
	String BillingFormUI() {
		return "company/payment/payment/BillingPayment";
	}
	
	@GetMapping("/success")			// 결제 빌씨링발키 ㅋㅋ 부르긴 했는데 안나오네 집가서 하자
	String Success(
		@RequestParam String authKey,
		@RequestParam String customerKey,
		Model model
			) {
		log.info("customerKey : {}",customerKey);
		log.info("authKey : {}",authKey);
		
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
			.header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==")
			.header("Content-Type", "application/json")
			.method("POST", HttpRequest.BodyPublishers.ofString("{\"authKey\":\"LPBPxzkmek6flXrr5oOfh\",\"customerKey\":\"skHcEFrq-91RMhWAiB1tQ\"}"))
			.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("responseBody : {}" , response.body());
		return "company/payment/payment/BillingResult";
	}
	
	@PostMapping("/api/toss/billing/issue")
	public ResponseEntity<String> inssueBillingKey(
			@RequestBody Map<String, String> requestMap){
		String authKey = requestMap.get("authKey");
		String customerKey = requestMap.get("customerKey");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("test_sk_xxxxx");
		
		Map<String, String> body = new HashMap<>();
		body.put("authKey", authKey);
		body.put("customerKey", customerKey);
		
		HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://api.tosspayments.com/v1/billing/authorizations/issue";	
	
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			return ResponseEntity.ok(response.getBody());
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString()); 
		}
		
	}
	
	@PostMapping("/api/toss/billing/execute")
	public ResponseEntity<String> executeBilling(
			@RequestBody Map<String, Object> payload
			){
		String billingKey = (String) payload.get("billingKey");
		String customerKey = (String) payload.get("customerKey");
		int amount = (int) payload.get("amount");
		String orderId = "ORDER_" + System.currentTimeMillis();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("");		//Secret Key
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, Object> body = new HashMap<>();
		body.put("amount", amount);
		body.put("orderId",orderId);
		body.put("customerKey", customerKey);
		body.put("orderName","정기구독요금제");
		body.put("customerEmail","test");
		body.put("customerName","김철민");
		
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body,headers);
		String url = "https://api.tosspayments.com/v1/billing/" + billingKey;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			return ResponseEntity.ok(response.getBody());
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
		
	}
	
	
	
	
	
	
}
