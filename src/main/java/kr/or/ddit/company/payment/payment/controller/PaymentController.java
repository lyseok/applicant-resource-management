package kr.or.ddit.company.payment.payment.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.company.payment.payment.service.TossPaymentService;
import kr.or.ddit.company.payment.product.service.PaymentProductServiceImpl;
import kr.or.ddit.vo.common.PaymentProductVO;
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

	@Autowired
	private PaymentProductServiceImpl pservice;
	
	@PostMapping("/product/change/confirm")
	@ResponseBody
	public ResponseEntity<?> changeProduct(@RequestBody Map<String, String> payload) {
	    String oldPaymentNo = payload.get("oldPaymentNo");
	    String newProductNo = payload.get("newProductNo");
	    String billingKey = payload.get("billingKey");
	    String paymentKey = payload.get("paymentKey"); // 추가로 받은 값
	    log.info(" /product/change/confirm 의 oldPaymentNo : {}", oldPaymentNo);
	    log.info("/product/change/confirm 의 payload :{} ", payload);
	    // 나머지 기존 로직 동일
	    PaymentVO oldPayment = service.selectPaymentByPk(oldPaymentNo);
	    LocalDate nextStart = LocalDate.parse(oldPayment.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
	    LocalDate nextEnd = nextStart.plusMonths(1);
	    log.info("/product/change/confirm 의oldPayment :{}",oldPayment);
	    service.cancelPayment(oldPaymentNo);

	    PaymentProductVO newProduct = pservice.selectPaymentProductByPk(newProductNo);
	    PaymentVO newPayment = new PaymentVO();
	    newPayment.setUserId(service.getUserId());
	    newPayment.setProductNo(newProduct.getProductNo());
	    newPayment.setPaymentPay(newProduct.getProductPrice());
	    newPayment.setPaymentMethod("카드");
	    newPayment.setPaymentDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	    newPayment.setPaymentBillingKey(billingKey);
	    newPayment.setStartDate(nextStart.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	    newPayment.setEndDate(nextEnd.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	    newPayment.setStatus("T");

	    log.info("newPayment: {}", newPayment);
	    service.insertPayment(newPayment);

	    // 성공 응답
	    return ResponseEntity.ok().body("요금제 변경 완료");
	}


	@GetMapping("/change/product")
	public String changeProduct(
			Model model
			, @RequestParam String productNo
			, @RequestParam String paymentNo	
			) {
		log.info("받은 paymentNo값 : {}", paymentNo);
		log.info("받은 productNo값 : {}", productNo);
		PaymentVO payment = service.selectPaymentByPk(paymentNo);
		List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
		log.info("productList의 값 : {}", productList);
		payment.setPaymentProductList(productList);
		log.debug("payment의 값 : {}", payment);
		
		model.addAttribute("payment", payment);
		List<PaymentProductVO> allProducts = pservice.selectPaymentProductList();
		List<PaymentProductVO> changeableProducts = allProducts.stream()
										.filter(p -> !p.getProductNo().equals(productNo))
										.collect(Collectors.toList());
		
		
		model.addAttribute("changeableProducts",changeableProducts);
		log.info("Comparison 으로 넘어가는 payment 값 : {}", payment);
		return "company/payment/product/ComparisonProduct";
	}
	
	@GetMapping("/done/products")
	public String done(
			Model model
			, Authentication auth
			) {

		List<PaymentVO> purchaseList = service.selectMyPaymentList(service.getUserId());
		List<PaymentVO> expiredPayments = new ArrayList<>();
		Map<String, Integer> productCountMap = new HashMap<>();
		long totalUsedAmount = 0;

		for (PaymentVO payment : purchaseList) {
			LocalDate paymentDate = LocalDate.parse(payment.getPaymentDate(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LocalDate now = LocalDate.now();
			long daysLeft = 30 - ChronoUnit.DAYS.between(paymentDate, now);

			List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(payment.getProductNo());

			// ✅ 만료된 상품만 추가
			List<PaymentProductVO> expiredProducts = new ArrayList<>();
			for (PaymentProductVO product : productList) {
				product.setDaysRemaining((int) daysLeft);
				if (daysLeft <= 0) {
					expiredProducts.add(product);
					
					productCountMap.merge(product.getProductName(), 1, Integer::sum);
				}
			}
			
			// 누적금액

			if (!expiredProducts.isEmpty()) {
				payment.setPaymentProductList(expiredProducts);
				payment.setStartDate(paymentDate.toString());
				payment.setEndDate(paymentDate.plusDays(30).toString());
				expiredPayments.add(payment);			
			}
					
		}
		
		// 총 결제금액 확인
		for(PaymentVO payment : expiredPayments) {
			totalUsedAmount += Long.parseLong(payment.getPaymentPay());
		}
		
		
		model.addAttribute("totalUsedAmount",totalUsedAmount);
		model.addAttribute("purchaseList", purchaseList);
		model.addAttribute("productCountMap", productCountMap);
		model.addAttribute("purchaseList", expiredPayments);
		log.info("만료된 상품 리스트 : {}", expiredPayments);
		log.debug("누적 사용량 Map :  {}" ,productCountMap);
		return "company/payment/paymentlist/PaymentDoneList";
	}

	@GetMapping("/buydetail")
	public String buyDetail(Model model
			, @RequestParam String paymentNo
			, @RequestParam String productNo
					) {
		log.info("너 뭘로 나오나 보자 : {}", paymentNo);
		PaymentVO payment = service.selectPaymentByPk(paymentNo);
		
		List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
		payment.setPaymentProductList(productList);
		
		model.addAttribute("payment", payment);
		log.info("detail로 가는 값 : {}", model);
		return "company/payment/payment/BuyProductDetail";
	}

	@GetMapping("/main")
	String mainForm(Model model, Authentication auth) {

		List<PaymentVO> purchaseList = service.selectMyPaymentList(service.getUserId());

		for (PaymentVO payment : purchaseList) {
			LocalDate paymentDate = LocalDate.parse(payment.getPaymentDate(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LocalDate now = LocalDate.now();
			long daysLeft = 30 - ChronoUnit.DAYS.between(paymentDate, now);

			List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(payment.getProductNo());

			for (PaymentProductVO product : productList) {
				product.setDaysRemaining((int) daysLeft);
			}
			payment.setPaymentProductList(productList);
		}

		model.addAttribute("purchaseList", purchaseList);
		log.info("purchaseList : {}", purchaseList);
		return "company/payment/payment/PaymentMain";
	}

	@GetMapping("/success/executebilling")
	String successBuy(@RequestParam("billingKey") String billingKey, @RequestParam("amount") String amount,
			@RequestParam("orderName") String orderName, @RequestParam("paymentKey") String paymentKey, Model model) {
		log.info("billingKey: {}", billingKey);
		log.info("amount: {}", amount);
		log.info("orderName: {}", orderName);
		log.info("paymentKey: {}", paymentKey);
		PaymentVO vo = new PaymentVO();
		PaymentProductVO product = pservice.selectPaymentProductByName(orderName);

		vo.setUserId(service.getUserId());
		vo.setProductNo(product.getProductNo());
		vo.setPaymentMethod("카드");
		vo.setPaymentBillingKey(billingKey);
		vo.setPaymentPay(amount);

		List<PaymentProductVO> productList = new ArrayList<>();
		productList.add(product);
		vo.setPaymentProductList(productList);

		log.info("어 이게 뭐지 : {}", vo);
		service.insertPayment(vo);
		PaymentVO result = service.selectPaymentByPk(vo.getPaymentNo());
		model.addAttribute("result", result);
		return "company/payment/payment/SuccessSubscribe";
	}

	@GetMapping("/success")
	String Test(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam int amount,
			@RequestParam String productNo, @RequestParam String billingKey, Model model) {
		String bodyJson = String.format("{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}", paymentKey, orderId,
				amount);

		String pamount = (amount + "");

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
				.header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString(bodyJson)).build();

		HttpResponse<String> response = null;
		JsonNode jsonNode = null;
		PaymentVO vo = new PaymentVO();
		// 결제상품 번호를 등록하려면 먼저 샘플을 만들어야 하기에 insert대기
		int totalAmount = 0;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (response != null) {
			log.info("결제 응답 : " + response.body());
			model.addAttribute("paymentResult", response.body());

			try {
				ObjectMapper objectMapper = new ObjectMapper();
				jsonNode = objectMapper.readTree(response.body());
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jsonNode != null) {
				String orderName = jsonNode.get("orderName").asText();
				String method = jsonNode.get("method").asText();
				int RtotalAmount = amount;
				model.addAttribute("billingKey", billingKey);
				model.addAttribute("orderName", orderName);
				model.addAttribute("method", method);
				model.addAttribute("RtotalAmount", RtotalAmount);
				log.info("model 의 값들 : {}", model);
				String paymentMethod = jsonNode.get("method").asText();
				vo.setProductNo(productNo);
				vo.setPaymentPay(pamount);
				vo.setPaymentMethod(paymentMethod);

				service.insertPayment(vo);
				log.info("insert에 들어갈 값 : {}", vo);
			}
		} else {
			log.info("겔제응답이 없ㅇ름");
		}

		return "company/payment/payment/SuccessPayment";
	}

	@GetMapping("/executeBilling") // 정기결제
	public String executeBilling(@RequestParam String billingKey, @RequestParam String customerKey,
			@RequestParam int amount, @RequestParam String productNo, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		String orderId = "SUBSCRIPTION_" + System.currentTimeMillis();

		// JSON 바디 생성
		String requestBody = "";

		try {
			requestBody = mapper.writeValueAsString(Map.of("customerKey", customerKey, "amount", amount, "orderId",
					orderId, "orderName", "이건 상품명을 넣을건데 방법생각좀", "customerEmail", "test용@이메일.com", "customerName",
					service.getUserId()));
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "요청 본문 생성 실패");
			return "company/payment/payment/BillingResult";
		}

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
				.header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==") // secretKey
																										// base64
				.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();

		// 실행 및 응답처리
		HttpResponse<String> response = null;

		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			model.addAttribute("error", "정기결제 요청 실패");
			return "";

		}

		if (response != null && response.body() != null) {
			log.info("Billing 결제 응답 : {} ", response.body());

			try {
				JsonNode jsonNode = mapper.readTree(response.body());

				String method = jsonNode.get("method").asText();
				String orderName = jsonNode.get("orderName").asText();
				String payKey = jsonNode.get("paymentKey").asText();
				int paidAmount = jsonNode.get("totalAmount").asInt();

				model.addAttribute("method", method);
				model.addAttribute("orderName", orderName);
				model.addAttribute("paidAmount", paidAmount);

				// 결제내역 저장
				PaymentVO vo = new PaymentVO();
				vo.setProductNo(productNo);
				vo.setPaymentPay(String.valueOf(paidAmount));
				vo.setPaymentMethod(method);
				service.insertPayment(vo);

				log.info("정기결제 저장완료 : {}", vo);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("error", "응답 파싱 실패");
			}

		} else {
			model.addAttribute("error", "응답없음");
			log.warn("응답이 null 또는 body가 없습니다.");
		}
		return "company/payment/payment/BillinbResult";
	}
}
