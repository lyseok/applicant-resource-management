package kr.or.ddit.company.payment.payment.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TossPaymentService {

	@Autowired
	PaymentServiceImpl service;
	
	@Value("${toss.secretKey}")
	private String secretKey;

	public String confirmPayment(
			String billingKey
			, String paymentKey
			, String orderId
			, int amount
			) {
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
	
	public String deactivateBillingKey(String billingKey, String orderId, String paymentKey, String customerKey) throws Exception {
	    String encodedBillingKey = URLEncoder.encode(billingKey, StandardCharsets.UTF_8);
	    String uri = "https://api.tosspayments.com/v1/billing/" + encodedBillingKey;

	    String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

	    String json = """
	        {
	            "cancelReason": "사용자 요청: 정기결제 해지",
	            "orderId": "%s",
	            "paymentKey": "%s",
	            "customerKey": "%s"
	        }
	        """.formatted(orderId, paymentKey, customerKey);

	    HttpRequest request = HttpRequest.newBuilder()
	        .uri(URI.create(uri))
	        .header("Authorization", authHeader)
	        .header("Content-Type", "application/json")
	        .POST(HttpRequest.BodyPublishers.ofString(json))
	        .build();

	    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

	    System.out.println("🚀 Toss 응답: " + response.body());
	    return response.body();
	}

	public String cancelPayment(String paymentKey, String cancelReason) throws Exception {
		String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

		String requestBody = String.format("""
				    {
				        "cancelReason": "%s"
				    }
				""", cancelReason);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel"))
				.header("Authorization", authHeader).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

}
