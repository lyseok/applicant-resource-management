const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
const customerKey = "h5hXSJ-WPK8sZQpXQUJUA"; // 세션 기반이면 이 값은 서버에서 세팅한 걸 써야 함

const productNo = window.PRODUCT_NO
const tossPayments = TossPayments(clientKey);
const successUrl = `${window.location.origin}/company/toss/success?productNo=${productNo}`;
const failUrl = `${window.location.origin}/fail`;

const payment = tossPayments.payment({ customerKey });

async function requestBillingAuth() {
	console.log("productNo", productNo);
	console.log("successUrl", successUrl);
  await payment.requestBillingAuth({
    method: "CARD",
    successUrl,
    failUrl,
    customerEmail: "dhfjdhfj@naver.com",
    customerName: "김철민",
  });
}
