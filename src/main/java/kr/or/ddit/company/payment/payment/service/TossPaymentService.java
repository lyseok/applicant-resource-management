package kr.or.ddit.company.payment.payment.service;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class TossPaymentService {
	
	@Value("${toss.secretKey}")
	private String secretKey;
	
	public String confirmPayment(String paymentKey, String orderId, int amount) {
		String url = "http://api.tosspayments.com/v1/payments/" + paymentKey;
		
		HttpHeaders headers = new HttpHeaders();
		String auth = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
		 headers.set("Authorization", "Basic" + auth);
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 
		 Map<String, Object> body = new HashMap<>();
		 body.put("orderId", orderId);
		 body.put("amount", amount);
		 
		 
		 HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			return "결제 승인 실패 : " + e.getResponseBodyAsString();
		}
		
	}
	
}
