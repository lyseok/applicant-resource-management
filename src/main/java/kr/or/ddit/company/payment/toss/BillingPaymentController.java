package kr.or.ddit.company.payment.toss;

import java.io.IOException;
import java.net.URI;
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
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6spy.engine.common.Loggable;

import jakarta.servlet.http.HttpSession;
import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.company.payment.product.service.PaymentProductServiceImpl;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/toss")
public class BillingPaymentController {
	
	@Value("${toss.secretKey}")
	private String tossSecretKey;
	
	@Autowired
	PaymentProductServiceImpl service;
	
	@Autowired
	PaymentServiceImpl Pservice;

	@GetMapping("/change/buyproduct")
	public String buyProduct(
		@RequestParam String oldPaymentNo,
	    @RequestParam String newproductNo,
	    @RequestParam String billingKey,
	    HttpSession session,
	    Model model
	) {
	    PaymentProductVO product = service.selectPaymentProductByPk(oldPaymentNo);
	    log.info("product: {}", product);
	    model.addAttribute("product", product);
	    model.addAttribute("oldPaymentNo", oldPaymentNo);
	    model.addAttribute("billingKey", billingKey);
	    session.setAttribute("customerKey", "고객 고유키"); // 실 사용 환경에서는 사용자 기준으로 발급
	    log.info("session : {}", session);
	    return "company/payment/product/ChangeProduct";
	}

	
	@PostMapping("/check/billing")
	@ResponseBody
	public Map<String, Object> checkBillingKey(HttpSession session){
		Map<String, Object> map = new HashMap<>();
		log.info("session id: {}", session.getId());
		String billingKey = (String) session.getAttribute("billingKey");
		String customerKey = (String) session.getAttribute("customerKey");
		if(billingKey == null || billingKey == "") {
			billingKey = Pservice.checkbilling(Pservice.getUserId());		
			log.info("여기서 빌링키가 나온다고요 ?? : {}", billingKey);
			map.put("billingKey", billingKey);
		}
		log.info("==> sessionId: {}" ,session.getId());
		map.put("hasBillingKey", billingKey != null && !billingKey.isEmpty());
		map.put("customerKey", customerKey);
		log.info("빌링키빌링키빌링키빌링키빌링키", billingKey);
		log.info("map 반환값 : {}", map);
		return map;
	}
	
	@GetMapping("/buyproduct")
	String productFormUi(
		@RequestParam String productNo
		,@RequestParam(required = false) String billingKey
		,@RequestParam(required = false) String customerKey
		,HttpSession session
		,Model model
			) {
		log.info("productNo가 씨이이이 이게 뭔데 로고 이렇게쓰면 잘 보이겠지 : {}", productNo);
		
		if(session.getAttribute("billingKey") == null && billingKey != null) {
			session.setAttribute("billingKey", billingKey);
		}
		
		if(session.getAttribute("customerKey") == null && customerKey != null) {
			session.setAttribute("customerKey", customerKey);
		}
		
		String sessionBillingKey = (String) session.getAttribute("billingKey");
		String sessionCustomerKey = (String) session.getAttribute("customerKey");
		log.info("==>> sessionBillingKey가 아닌 그냥 파람으로 받은 BillingKey : {}", billingKey);
		log.info("최종 sessionBillingKey: {}", sessionBillingKey);
		log.info("최종 sessionCustomerKey: {}", sessionCustomerKey);
		
		log.info("==> sessionId: {}" ,session.getId());
		PaymentProductVO product = service.selectPaymentProductByPk(productNo);
		model.addAttribute("product",product);
		model.addAttribute("productNo",productNo);
		model.addAttribute("customerKey", sessionCustomerKey);
		model.addAttribute("billingKey",sessionBillingKey);
//		return "/company/payment/payment/buyproduct?productNo=" + productNo;
		return "company/payment/payment/BuyProduct";
	}
	
	@GetMapping("/billing")
	String BillingFormUI(
			@RequestParam String productNo			
			, Model model
			) {
		model.addAttribute("productNo", productNo);
		return "company/payment/payment/BillingPayment";
	}

	@GetMapping("/success") // 결제 키 ㅋㅋ 부르긴 했는데 안나오네 집가서 하자

	String Success(@RequestParam String authKey
				, @RequestParam String customerKey
				, @RequestParam String productNo
				, HttpSession session
				, Model model) throws IOException, InterruptedException {
		String billingKey = (String) session.getAttribute("billingKey");
		log.info("productNo : {}", productNo);
		log.info("customerKey : {}", customerKey);
		log.info("authKey : {}", authKey);
		log.info("==>sessionID : {}", session.getId());
		// JSOn 본문 생성
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> requestBody = new HashMap<>();

		requestBody.put("customerKey", customerKey);
		requestBody.put("authKey", authKey);
		String jsonBody = "";

		try {
			jsonBody = objectMapper.writeValueAsString(requestBody);
		} catch (IOException e) {
			log.error("JSOn변환 실패 ", e);
			model.addAttribute("error", "요청 본문 생성 실패");
			return "company/payment/payment/billingResult";
		}

		// Toss Api 요청 생성

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
				.header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==") // Base64 인코딩된																					// secretKey:
				.header("Content-Type", "application/json")
				.method("POST",HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
		
		
		// 요청 실행
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		
		if (response != null && response.body() != null) {
			
			log.info("결제응답 : " + response.body());

			try {
				JsonNode jsonNode = objectMapper.readTree(response.body());
				/* String billingKey = jsonNode.get("billingKey").asText(); */
				log.info("jsonBody : {}",jsonBody);
				// billingKey를 저장
				billingKey = jsonNode.get("billingKey").asText();
				model.addAttribute("billingKey",billingKey);
				model.addAttribute("result",jsonNode.toPrettyString());
				session.setAttribute("authKey", authKey);
				session.setAttribute("customerKey", customerKey);
				session.setAttribute("billingKey", billingKey);
				log.info("customerKey",customerKey);
				log.info("빌링빌링빌링키 = billingKey : {}", billingKey);
				log.info("session id: {}", session.getId());
				return "redirect:/company/toss/buyproduct?productNo=" + productNo;
				
				
			} catch (IOException e) {
				log.error("JSON파싱 실패 ", e);
				model.addAttribute("error", "응답처리 실패");
			}
			

		} else {
			log.warn("응답 실패 !! ");
			model.addAttribute("error", "응답 없음");
		}
		
		log.info("responseBody : {}", response.body());
		/* return "dummy"; */
		return "redirect:/company/toss/buyproduct?productNo=" + productNo;
	}

	@PostMapping("/api/toss/billing/issue")
	public ResponseEntity<String> inssueBillingKey(@RequestBody Map<String, String> requestMap) {
		log.info("><<><><><><><><><>requestMap : {}", requestMap);
		String authKey = requestMap.get("authKey");
		String customerKey = requestMap.get("customerKey");
		String billingKey = requestMap.get("billingKey");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("test_sk_xxxxx");

		Map<String, String> body = new HashMap<>();
		body.put("billingKey",billingKey);
		body.put("authKey", authKey);
		body.put("customerKey", customerKey);

		log.info("body 의 값 :{}",body);
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

	@PostMapping("/api/billing/execute")	// 결제하기 눌렀을때 여기로
	public ResponseEntity<String> executeBilling(@RequestBody Map<String, Object> payload) {
		String billingKey = (String) payload.get("billingKey");
		String customerKey = (String) payload.get("customerKey");
		String orderName = (String) payload.get("orderName");
//		int amount = (int) payload.get("amount");
		int amount = Integer.parseInt(String.valueOf(payload.get("amount")));
		String orderId = "ORDER_" + System.currentTimeMillis();
		log.info("payloaad : {}", payload);

		String encodedKey = Base64.getEncoder().encodeToString((tossSecretKey + ":").getBytes(StandardCharsets.UTF_8));
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + encodedKey); // Secret Key
		headers.setContentType(MediaType.APPLICATION_JSON);
		log.info("customerKey : {} ", customerKey);
		log.info("headers값 : {}",headers);
		log.info("tossSecretKey : {}",tossSecretKey);
		Map<String, Object> body = new HashMap<>();
		body.put("amount", amount);
		body.put("orderId", orderId);
		body.put("customerKey", customerKey);
		body.put("orderName", orderName);
		body.put("customerEmail", "test");
		body.put("customerName", Pservice.getUserId());
		body.put("billingKey", billingKey);
		
		log.info("body값 : {}", body);
		
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
		
		log.info("request 값 : {}" , request);
		String url = "https://api.tosspayments.com/v1/billing/" + billingKey;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			log.info("이젠 진짜 그만하자 : {}",response.getBody());
			
			return ResponseEntity.ok(response.getBody());
			
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}

	}

}
