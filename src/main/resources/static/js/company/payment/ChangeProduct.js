const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
const tossPayments = TossPayments(clientKey);
const payment = tossPayments.payment({ customerKey });

// JSP에서 받은 값
const productName = PproductName;
const amount = Pamount;

// 데이터셋에서 받는 값
const productNo = PproductNo;
const oldPaymentNo = PoldPaymentNo;
let newProductNo = document.body.dataset.productNo;
let billingKey = PbillingKey; // 초기에 JSP에서 넘어온 값
console.log("oldPaymentNo", oldPaymentNo);
console.log(document.body.dataset.oldPaymentNo);
// 만약 billingKey가 쿼리스트링으로 전달된 경우라면 갱신
window.addEventListener("DOMContentLoaded", () => {
	const params = new URLSearchParams(window.location.search);
	const keyFromQuery = params.get("billingKey");
	if (keyFromQuery) {
		billingKey = keyFromQuery;
		document.body.dataset.billingKey = keyFromQuery;
		doChangeBilling(); // 등록 직후 바로 실행
	}
});
/*document.querySelector("#change-btn").addEventListener('click', e => {
	console.log("e.target",e.target.dataset.productNo);
})*/
async function doChangeBilling(btn) {
	const newProductNo = btn.dataset.productNo;
	console.log("newProductno", newProductNo);
	if (!billingKey) {
		const confirmed = confirm("등록된 카드가 없습니다. 등록하시겠습니까?");
		if (confirmed) {
			await requestBillingAuth(); // 등록 후 현재 페이지로 리다이렉트
		}
		return;
	}

	const res = await fetch("/company/toss/api/billing/execute", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({
			billingKey,
			customerKey,
			amount:Number(amount),
			orderName: productName
		})
	});

	const data = await res.json();

	if (!data.paymentKey) {
		alert("결제 실패 또는 응답 오류");
		return;
	}
	console.log("billingKey", billingKey);
	console.log("customerKey", customerKey);
	console.log("amount", amount);
	console.log("orderName", productName);

	const confirmRes = await fetch("/company/payment/product/change/confirm",
		{
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({
				oldPaymentNo,
				newProductNo: productNo,
				billingKey,
				paymentKey: data.paymentKey
			})

		});

	if (confirmRes.ok) {
		alert("요금제 변경 완료!");
		window.location.href = "/company/payment/main";
	} else {
		alert("요금제 변경 실패");
	}
}

async function requestBillingAuth() {
	await payment.requestBillingAuth({
		method: "CARD",
		successUrl: window.location.href, // billingKey 포함되어 리턴됨
		failUrl: window.location.origin + "/fail",
		customerEmail: "dhfjdhfj@naver.com",
		customerName: "김철민"
	});
}
