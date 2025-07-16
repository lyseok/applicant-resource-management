 // ------  SDK 초기화 ------
      // @docs https://docs.tosspayments.com/sdk/v2/js#토스페이먼츠-초기화
      const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
      const customerKey = "test_sk_vZnjEJeQVxG2jBWev45D3PmOoBN0";
      const tossPayments = TossPayments(clientKey);
      /*const payment = tossPayments.payment({ customerKey });*/
      // 회원 결제
      // @docs https://docs.tosspayments.com/sdk/v2/js#tosspaymentspayment
      // 비회원 결제
      // const payment = tossPayments.payment({customerKey: TossPayments.ANONYMOUS})
      // ------ '카드 등록하기' 버튼 누르면 결제창 띄우기 ------
      // @docs https://docs.tosspayments.com/sdk/v2/js#paymentrequestpayment
      async function requestBillingAuth() {
        await payment.requestBillingAuth({
          method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
          successUrl: window.location.origin + "/success", // 요청이 성공하면 리다이렉트되는 URL
          failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
          customerEmail: "dhfjdhfj@naver.com",
          customerName: "김철민",
        });
      }
      
      fetch("/api/toss/billing/execute", {
		method : "POST",
		headers : {
			"Content_Type" : "application/json"
		},
		body : JSON.stringify({
			billingKey : "빌링키",
			customerKey : "user_123",
			amount : 10000
		})
	  })
	  .then(res => res.json())
	  .then(date => {
		console.log("결제 성공 ! ", data);
	  })
	  .catch(err=> {
		consoler.error("결제 실패 !", err);
	  })
	  
	  
	  /*
	  
	  @PostMapping("/execute-billing")
public String executeBilling(
        @RequestParam String billingKey,
        @RequestParam String customerKey,
        @RequestParam int amount,
        Model model) {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> body = new HashMap<>();
    body.put("customerKey", customerKey);
    body.put("amount", amount);
    body.put("orderId", "ORDER_" + System.currentTimeMillis());
    body.put("orderName", "정기 구독");
    body.put("customerEmail", "customer@email.com");
    body.put("customerName", "홍길동");
    body.put("taxFreeAmount", 0);

    String requestBody = "";
    try {
        requestBody = mapper.writeValueAsString(body);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
        model.addAttribute("error", "요청 바디 생성 실패");
        return "billing/fail";
    }

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
        .header("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4RzJqQldldjQ1RDNQbU9vQk4wOg==")
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build();

    HttpResponse<String> response = null;
    try {
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        model.addAttribute("result", response.body());
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "결제 실패");
    }

    return "billing/success";
}

	  
	  
	  */