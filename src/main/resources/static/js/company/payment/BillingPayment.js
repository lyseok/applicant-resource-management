 // ------  SDK 초기화 ------
      // @docs https://docs.tosspayments.com/sdk/v2/js#토스페이먼츠-초기화
      const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
      const customerKey = "test_sk_vZnjEJeQVxG2jBWev45D3PmOoBN0";
      const tossPayments = TossPayments(clientKey);
      const payment = tossPayments.payment({ customerKey });
      // 회원 결제
      // @docs https://docs.tosspayments.com/sdk/v2/js#tosspaymentspayment
      // 비회원 결제
      // const payment = tossPayments.payment({customerKey: TossPayments.ANONYMOUS})
      // ------ '카드 등록하기' 버튼 누르면 결제창 띄우기 ------
      // @docs https://docs.tosspayments.com/sdk/v2/js#paymentrequestpayment
      const currentUrl = window.location.href; 
      async function requestBillingAuth() {
        await payment.requestBillingAuth({
          method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
          successUrl: currentUrl, // 요청이 성공하면 리다이렉트되는 URL
          failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
          customerEmail: "dhfjdhfj@naver.com",
          customerName: "김철민",
        });
      }
      
      fetch("/api/toss/billing/execute", {
		method : "POST",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify({
			billingKey : "${sessionScope.billingKey}",
			customerKey : customerKey,
			amount : "${product.productPrice}"
		})
	  })
	  .then(res => res.json())
	  .then(date => {
		console.log("결제 성공 ! ", data);
	  })
	  .catch(err=> {
		consoler.error("결제 실패 !", err);
	  })
	  
	  
	

	  
	  
