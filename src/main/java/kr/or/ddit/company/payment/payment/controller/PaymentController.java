package kr.or.ddit.company.payment.payment.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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

import kr.or.ddit.company.payment.link.service.PaymentProductLinkService;
import kr.or.ddit.company.payment.payment.service.PaymentServiceImpl;
import kr.or.ddit.company.payment.payment.service.TossPaymentService;
import kr.or.ddit.company.payment.product.service.PaymentProductServiceImpl;
import kr.or.ddit.vo.common.ChangeProductResponseVO;
import kr.or.ddit.vo.common.PaymentListResponseVO;
import kr.or.ddit.vo.common.PaymentProductVO;
import kr.or.ddit.vo.common.PaymentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company/payment")
public class PaymentController {

	@Autowired
	private TossPaymentService tossPaymentService;

	@Autowired
	private PaymentServiceImpl service;

//	@Autowired
//	private PaymentProductServiceImpl pservice;

//	@Autowired
//	private PaymentProductLinkService lservice;

	
	// 메인화면
		@GetMapping("/main")
		public String mainForm(Model model, Authentication auth) {
		    PaymentVO ppvo = service.getUserCurrentPaymentStatus();
		    model.addAttribute("ppvo", ppvo);
		    return "company/payment/payment/PaymentMain";
		}
		
		// 구매상품보기(controller <-> service 분리완, 작동 이상무)
		@GetMapping("/buydetail")
		public String buyDetail(Model model, @RequestParam String paymentNo, @RequestParam String productNo) {
		    PaymentVO paymentDetail = service.BuyDetail(paymentNo, productNo);
		    model.addAttribute("payment", paymentDetail);
		    return "company/payment/payment/BuyProductDetail";
		}
		
		// 결제완료 상품(controller <-> service 분리완, 작동 이상무)
		@GetMapping("/done/products")
		public String getDoneProducts(
		        @RequestParam(value = "filter", required = false, defaultValue = "") String filter,
		        @RequestParam(value = "page", defaultValue = "1") int page,
		        Model model) {

			PaymentListResponseVO response = service.getFilteredPayments(service.getUserId(), filter, page);

		    model.addAttribute("purchaseList", response.getPayments());
		    model.addAttribute("cumulativeList", response.getCumulativeAmounts());
		    model.addAttribute("productCountMap", response.getProductCountMap());
		    model.addAttribute("totalUsedAmount", response.getTotalUsedAmount());
		    model.addAttribute("selectedFilter", filter);
		    model.addAttribute("totalPages", response.getTotalPages());
		    model.addAttribute("currentPage", page);

		    return "company/payment/paymentlist/PaymentDoneList";
		}

		// 결제성공(controller <-> service 분리완, 작동 이상무)
		@GetMapping("/success/executebilling")
		public String successBuy(
		        @RequestParam("billingKey") String billingKey,
		        @RequestParam("amount") String amount,
		        @RequestParam("orderName") String orderName,
		        @RequestParam("paymentKey") String paymentKey,
		        @RequestParam("orderId") String orderId,
		        Model model
		) {
		    PaymentVO result = service.processSuccessfulBilling(billingKey, amount, orderName, paymentKey, orderId);
		    model.addAttribute("result", result);
		    return "company/payment/payment/SuccessSubscribe";
		}

		// 요금제 변경 화면으로 가기(controller <-> service 분리완, 작동 이상무)
		 @GetMapping("/change/product")
		 public String changeProduct(Model model, @RequestParam String productNo, @RequestParam String paymentNo) {
		     ChangeProductResponseVO response = service.getChangeableProductInfo(paymentNo, productNo);
		     model.addAttribute("payment", response.getPayment());
		     model.addAttribute("changeableProducts", response.getChangeableProducts());
		     return "company/payment/product/ComparisonProduct";
		 }
		 
		// 요금제 변경(controller <-> service 분리완, 작동 이상무)
		 @PostMapping("/product/change/confirm")
		 @ResponseBody
		 public ResponseEntity<?> changeProduct(@RequestBody Map<String, String> payload) {
		     service.changeProduct(payload);
		     return ResponseEntity.ok("요금제 변경 완료");
		 }
	
	// 취소하기
	@PostMapping("/cancel/subscription")
	@ResponseBody
	public String cancelSubscription(@RequestParam String paymentNo) throws Exception {
	    return service.cancelUserSubscription(paymentNo);
	}
	
//	 @PostMapping("/cancel/subscription")
//	 @ResponseBody
//	    public String cancelSubscription(
//	    		@RequestParam String paymentNo
//	    		, Model model) throws Exception {
//		 			
//		 
//	        // 1. DB에서 billingKey 가져오기
//		 	String PN = service.getPaymentNo(service.getUserId());
//		 	PaymentVO vo = service.selectPaymentByPk(PN);
//		 
//		 	log.info("취소시 vo 값 : {}" , vo);
//	             
//	        if (vo == null) {
//	            throw new RuntimeException("정보 를 찾을 수 없습니다.");
//	        }
//	        
//	        String billingKey = vo.getPaymentBillingKey();
//	        String paymentKey = vo.getPaymentKey();
//	        String orderId = vo.getPaymentOrderId();
//	        String customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
//	        
//	        log.info("취소시 billinbKey의 값 : {}",vo );
//	        // 2. Toss로 해지 요청
//	        String result = tossPaymentService.deactivateBillingKey(billingKey ,orderId, paymentKey , customerKey);
//	        log.info("resutl : {}", result);
//	        // 3. DB 상태 업데이트 (선택)
//	        service.cancelPayment(paymentNo);
//
//	        return result; // Toss 응답 그대로 반환
//	    }
	
	 


//	@PostMapping("/product/change/confirm")
//	@ResponseBody
//	public ResponseEntity<?> changeProduct(@RequestBody Map<String, String> payload) {
//		String orderId = payload.get("orderId");
//		String oldPaymentNo = payload.get("oldPaymentNo");
//		String newProductNo = payload.get("newProductNo");
//		String billingKey = payload.get("billingKey");
//		String paymentKey = payload.get("paymentKey");
//		log.info("/product/change/confirm 의 oldPaymentNo : {}", oldPaymentNo);
//		log.info("/product/change/confirm 의 payload :{} ", payload);
//		log.info("newProductNo의 값 : {}", newProductNo);
//
//		PaymentVO scheduled = service.selectScheduledByUserId(service.getUserId());
//		log.info("scheduled : {}", scheduled);
//		if(scheduled != null) {
//			throw new IllegalStateException("이미 구매한 상품이 있습니다. 관리자에게 문의하세요");
//		}
//
//		PaymentVO oldPayment = service.selectPaymentByPk(oldPaymentNo);
//		LocalDate nextStart = LocalDate.parse(oldPayment.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
//				.plusDays(1);
//		log.info("nextStart : {}", nextStart);
//		LocalDate nextEnd = nextStart.plusMonths(1);
//
//		log.info("/product/change/confirm 의oldPayment :{}", oldPayment);
//		service.cancelPayment(oldPaymentNo);
//
//		PaymentProductVO newProduct = pservice.selectPaymentProductByPk(newProductNo);
//		log.info("newProduct : {}", newProduct);
//		PaymentVO newPayment = new PaymentVO();
//		newPayment.setUserId(service.getUserId());
//		newPayment.setProductNo(newProduct.getProductNo());
//		newPayment.setPaymentPay(newProduct.getProductPrice());
//		newPayment.setPaymentMethod("카드");
//		newPayment.setPaymentDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//		newPayment.setPaymentBillingKey(billingKey);
//		newPayment.setUsageAllowed(newProduct.getProductLimit());
//		newPayment.setUsageRemaining(newProduct.getProductLimit());
//		newPayment.setStartDate(nextStart.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
//		newPayment.setEndDate(nextEnd.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
//		newPayment.setStatus("S");
//		newPayment.setPaymentOrderId(orderId);
//		log.info("newPayment: {}", newPayment);
//		service.comePayment(newPayment);
//
//		// 성공 응답
//		return ResponseEntity.ok().body("요금제 변경 완료");
//	}

	 // 요금제 변경 선택화면
//	@GetMapping("/change/product")
//	public String changeProduct(Model model, @RequestParam String productNo, @RequestParam String paymentNo) {
//		log.info("받은 paymentNo값 : {}", paymentNo);
//		log.info("받은 productNo값 : {}", productNo);
//		PaymentVO payment = service.selectPaymentByPk(paymentNo);
//		List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
//		log.info("productList의 값 : {}", productList);
//		payment.setPaymentProductList(productList);
//		log.debug("payment의 값 : {}", payment);
//		
//		model.addAttribute("payment", payment);
//		List<PaymentProductVO> allProducts = pservice.selectPaymentProductList();
//		List<PaymentProductVO> changeableProducts = allProducts.stream()
//				.filter(p -> !p.getProductNo().equals(productNo)).collect(Collectors.toList());
//
//		model.addAttribute("changeableProducts", changeableProducts);
//		log.info("Comparison 으로 넘어가는 payment 값 : {}", payment);
//		return "company/payment/product/ComparisonProduct";
//	}
	 
	

	 
	
	

	
//	@GetMapping("/done/products")
//	public String doun(@RequestParam(value = "filter", required = false, defaultValue = "") String filter,
//			@RequestParam(value = "page", defaultValue = "1") int page, Model model, Authentication auth) {
//
//		// 구매 목록 가져오기
//		List<PaymentVO> purchaseList = service.selectMyPaymentList(service.getUserId());
//		Map<String, Integer> productCountMap = new HashMap<>();
//		List<Long> cumulativeAmounts = new ArrayList<>();
//		long totalUsedAmount = 0;
//		LocalDate now = LocalDate.now();
//
//		log.info("filter의 값 : {}", filter);
//
//		// 결제 정보 설정
//		for (PaymentVO payment : purchaseList) {
//
//			LocalDate paymentDate = LocalDate.parse(payment.getPaymentDate(),
//					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//			long daysLeft = 30 - ChronoUnit.DAYS.between(paymentDate, now);
//
//			List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(payment.getProductNo());
//
//			if (productList == null) {
//				productList = new ArrayList<>();
//			}
//
//			for (PaymentProductVO product : productList) {
//				product.setDaysRemaining((int) daysLeft);
//			}
//
//			payment.setPaymentProductList(productList);
//			payment.setStartDate(paymentDate.toString());
//			payment.setEndDate(paymentDate.plusDays(30).toString());
//			log.info("payment 는 잘나오나 : {}", payment);
//		}
//
//		// 필터링된 리스트
//		List<PaymentVO> filteredList = purchaseList;
//
//		// "expired" 필터링: 사용 기간 만료
//		if ("expired".equals(filter)) {
//			filteredList = purchaseList.stream()
//					.filter(p -> p.getPaymentProductList().stream().allMatch(prod -> prod.getDaysRemaining() <= 0))
//					.collect(Collectors.toList());
//			log.info("Filtered expired products, filteredList size : {}", filteredList.size());
//
//			// "active" 필터링: 사용 기간이 남은 상품
//		} else if ("active".equals(filter)) {
//			filteredList = purchaseList.stream()
//					.filter(p -> p.getPaymentProductList().stream().anyMatch(prod -> prod.getDaysRemaining() > 0))
//					.collect(Collectors.toList());
//			log.info("Filtered active products, filteredList size: {}", filteredList.size());
//
//			// "A", "S", "E", "C" 필터링: status 값에 따라
//		} else if (List.of("A", "S", "E", "C").contains(filter)) {
//			filteredList = purchaseList.stream().filter(p -> filter.equals(p.getStatus())).collect(Collectors.toList());
//			log.info("Filtered by status '{}', filteredList size: {}", filter, filteredList.size());
//		}
//
//		// startDate 기준 빠른순서부터
//		filteredList.sort(Comparator.comparing(PaymentVO::getStartDate));
//
//		// 필터링 후 누적 금액과 상품 수 계산
//		for (PaymentVO payment : filteredList) {
//			for (PaymentProductVO product : payment.getPaymentProductList()) {
//				if (product.getProductName() != null) {
//					productCountMap.merge(product.getProductName(), 1, Integer::sum);
//				}
//			}
//			totalUsedAmount += Long.parseLong(payment.getPaymentPay());
//			cumulativeAmounts.add(totalUsedAmount);
//		}
//
//		// 페이징 처리
//		int pageSize = 10;
//		int totalItems = filteredList.size();
//		int totalPages = (int) Math.ceil((double) totalItems / pageSize);
//		int offset = (page - 1) * pageSize;
//
//		List<PaymentVO> pageList = filteredList.stream().skip(offset).limit(pageSize).collect(Collectors.toList());
//		List<Long> pageCumulativeAmounts = cumulativeAmounts.subList(offset,
//				Math.min(offset + pageSize, cumulativeAmounts.size()));
//
//		model.addAttribute("purchaseList", pageList); // ⬅️ 페이징된 리스트
//		model.addAttribute("cumulativeList", pageCumulativeAmounts); // ⬅️ 순차 누적 금액
//		model.addAttribute("productCountMap", productCountMap); // ⬅️ 상품별 수량
//		model.addAttribute("totalUsedAmount", totalUsedAmount); // ⬅️ 총 사용 금액
//		model.addAttribute("selectedFilter", filter); // ⬅️ 선택된 필터 값
//		model.addAttribute("totalPages", totalPages); // ⬅️ 전체 페이지 수
//		model.addAttribute("currentPage", page); // ⬅️ 현재 페이지
//
//		log.info("결제 내역(필터 : {}) : {}", filter, filteredList);
//
//		return "company/payment/paymentlist/PaymentDoneList";
//	}

//	@GetMapping("/buydetail")
//	public String buyDetail(Model model, @RequestParam String paymentNo, @RequestParam String productNo) {
//		log.info("너 뭘로 나오나 보자 : {}", paymentNo);
//		PaymentVO payment = service.selectPaymentByPk(paymentNo);
//
//		List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(productNo);
//		payment.setPaymentProductList(productList);
//
//		model.addAttribute("payment", payment);
//		log.info("detail로 가는 값 : {}", model);
//		return "company/payment/payment/BuyProductDetail";
//	}
	
	

	

//	@GetMapping("/main")
//	String mainForm(Model model, Authentication auth) {
//
//		String userId = service.getUserId();
//
//		PaymentVO ppvo = service.selectStauts(userId);
//
//		if (ppvo != null) {
//			List<PaymentProductVO> productList = pservice.selectPaymentProductListByPk(ppvo.getProductNo());
//			ppvo.setPaymentProductList(productList);
//
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//			LocalDate today = LocalDate.now();
//			LocalDate end = LocalDate.parse(ppvo.getEndDate(), formatter);
//			long daysRemaining = ChronoUnit.DAYS.between(today, end);
//
//			// ❗daysRemaining을 VO에 담기
//			ppvo.setDaysRemaining((int) daysRemaining);
//		}
//
//		log.info("ppvo : {}", ppvo);
//		model.addAttribute("ppvo", ppvo);
//
//		return "company/payment/payment/PaymentMain";
//	}

	
	
	
//	@GetMapping("/success/executebilling")
//	String successBuy(@RequestParam("billingKey") String billingKey
//			, @RequestParam("amount") String amount
//			,@RequestParam("orderName") String orderName
//			,@RequestParam("paymentKey") String paymentKey
//			,@RequestParam("orderId") String orderId
//			, Model model
//					) {
//		log.info("orderId : {}", orderId);
//		log.info("billingKey: {}", billingKey);
//		log.info("amount: {}", amount);
//		log.info("orderName: {}", orderName);
//		log.info("paymentKey: {}", paymentKey);
//		PaymentVO vo = new PaymentVO();
//		PaymentProductVO product = pservice.selectPaymentProductByName(orderName);
//		vo.setPaymentKey(paymentKey);
//		vo.setUserId(service.getUserId());
//		vo.setProductNo(product.getProductNo());
//		vo.setPaymentMethod("카드");
//		vo.setPaymentBillingKey(billingKey);
//		vo.setPaymentPay(amount);
//		vo.setUsageAllowed(product.getProductLimit());
//		vo.setUsageRemaining(product.getProductLimit());
//		List<PaymentProductVO> productList = new ArrayList<>();
//		productList.add(product);
//		vo.setPaymentProductList(productList);
//		vo.setPaymentOrderId(orderId);
//		log.info("어 이게 뭐지 : {}", vo);
//		service.insertPayment(vo);
//		service.updateComPaymentStatus(vo.getUserId());
//		PaymentVO result = service.selectPaymentByPk(vo.getPaymentNo());
//		model.addAttribute("result", result);
//
//		return "company/payment/payment/SuccessSubscribe";
//	}
	
	

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
				log.info("update 한 후 : {}", vo);
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
