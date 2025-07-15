/**
 * 
 */
 const tossPayments = TossPayments("test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy");

  function requestPayment() {
    tossPayments.requestPayment('카드', {
      amount: 15000,
      orderId: /*"ORDER-20250714-0001",*/ "ORDER-" + new Date().getTime(),
      orderName: "프리미엄 이용권",
      customerName: "홍길동",
      successUrl: "http://localhost/payment/test",
      failUrl: "http://localhost:8080/payment/fail"
    });
    /*console.log("amount", amount);
    console.log("orderId", orderId);
    console.log("orderName", orderName);
    console.log("customerName", customerName);
    console.log("successUrl", successUrl);
    console.log("failUrl", failUrl);*/
  }