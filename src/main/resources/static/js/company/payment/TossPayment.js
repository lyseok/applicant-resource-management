/**
 *
 */
const tossPayments = TossPayments('test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy');

function requestPayment(card) {
  /*const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";*/
  const productNo = card.dataset.productNo;
  const orderName = document.getElementById('orderName').innerText.trim();
  const amountText = document.getElementById('amount').innerText.trim();
  const amount = parseInt(amountText.replace(/[^0-9]/g, ''));
  const orderId = 'ORDER' + new Date().getTime();
  const customerName = '김철민';
  const method = 'CARD';
  const successUrl =
    'http://localhost/company/payment/success' + `?productNo=${productNo}`;
  const failUrl = 'http://localhost:8080/payment/fail';

  tossPayments.requestPayment(method, {
    /*customerKey,*/
    amount,
    orderId /*"ORDER-20250714-0001",*/ /*"ORDER-" + new Date().getTime(),*/,
    orderName /*"프리미엄 이용권"*/,
    customerName,
    successUrl,
    failUrl,
  });
  console.log('amount', amount);
  console.log('orderId', orderId);
  console.log('orderName', orderName);
  console.log('customerName', customerName);
  console.log('successUrl', successUrl);
  console.log('failUrl', failUrl);
  console.log('customerKey', customerKey);
}

function goToDetail(card) {
  const productNo = card.dataset.productNo;
  console.log('productNo', productNo);
  const orderName = card.dataset.productName;
  // 상세 페이지로 이동 (GET 방식, productNo만 넘김)
  window.location.href =
    '/company/payment/product/detail?productNo=' +
    encodeURIComponent(productNo);
}
