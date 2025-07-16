/**
 * 
 */
 const tossPayments = TossPayments("test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy");

  function requestPayment(card) {

 const productNo = card.dataset.productNo;
 const orderName = document.getElementById("orderName").innerText.trim();
 const amountText = document.getElementById("amount").innerText.trim();
 const amount = parseInt(amountText.replace(/[^0-9]/g,""));
 const orderId = "ORDER" + new Date().getTime();
 const customerName = "홍길동";
 const method = '카드';
 const successUrl = "http://localhost/company/payment/success"
 						+ `?productNo=${productNo}`;
 const failUrl = "http://localhost:8080/payment/fail";
 
    tossPayments.requestPayment(method, {
      amount,
      orderId, /*"ORDER-20250714-0001",*/ /*"ORDER-" + new Date().getTime(),*/
      orderName /*"프리미엄 이용권"*/,
      customerName,
      successUrl,
      failUrl
    });
    console.log("amount", amount);
    console.log("orderId", orderId);
    console.log("orderName", orderName);
    console.log("customerName", customerName);
    console.log("successUrl", successUrl);
    console.log("failUrl", failUrl);
    
    
  }