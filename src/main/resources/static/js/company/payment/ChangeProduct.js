const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
const tossPayments = TossPayments(clientKey);
const payment = tossPayments.payment({ customerKey });

// JSP에서 받은 값
const productName = PproductName;


// 데이터셋에서 받는 값

let newProductNo = btn.dataset.productNo;
/*let billingKey = PbillingKey; // 초기에 JSP에서 넘어온 값*/
console.log(document.body.dataset.oldPaymentNo);
// 만약 billingKey가 쿼리스트링으로 전달된 경우라면 갱신
window.addEventListener("DOMContentLoaded", () => {
	console.log("커스터키값 :", customerKey);
	const params = new URLSearchParams(window.location.search);
	const keyFromQuery = params.get("billingKey");
	console.log("keyFromQuery 값 : ", keyFromQuery);
	if (keyFromQuery) {
		billingKey = keyFromQuery;
		document.body.dataset.billingKey = keyFromQuery;
		doChangeBilling(); // 등록 직후 바로 실행

	}
});
/*document.querySelector("#change-btn").addEventListener('click', e => {
	console.log("e.target",e.target.dataset.productNo);
})*/
async function doChangeBilling(event, btn) {
	event.preventDefault();
	const oldproduct = document.getElementById("oldProduct");
	const oldProductNo = oldproduct.dataset.productNo;      // 현재 사용 중인 상품 번호
	const newProductNo = btn.dataset.productNo;                // 새로 선택한 상품 번호
	
	/*console.log("현재 상품 번호 (oldProductNo):", oldProductNo );
	console.log("바꿀 상품 번호 (newProductNo):", newProductNo);*/
	const productName = btn.dataset.productName;
	const billingKey = btn.dataset.billingKey;
	const orderId = btn.dataset.orderId;
	const amount = Number(btn.dataset.amount);
	const oldPaymentNo = oldproduct.dataset.paymentNo;
	const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
	
	/*console.log("빌링키 값 :",billingKey);
	console.log("oldPaymentNo값 :",oldPaymentNo);
	console.log("newProductno", newProductNo);
	console.log("커스터키값 : {}", customerKey);
	console.log("orderName",productName);*/
	if (!billingKey) {
		const confirmed = confirm("등록된 카드가 없습니다. 등록하시겠습니까?");
		if (confirmed) {
			alert("여기까진 문제없음4");
			await requestBillingAuth(); // 등록 후 현재 페이지로 리다이렉트
		}
		return;
	}
	console.log("billingKey",billingKey);
	console.log("customerKey",customerKey);
	console.log("amount",Number(amount));
	console.log("orderName",productName);
	 const billingRes = await fetch("/company/toss/api/billing/execute", {
	method: "POST",
	headers: { "Content-Type": "application/json" },
	body: JSON.stringify({
		billingKey,
		customerKey,
		amount,
		orderName: productName,
		orderId
	})
});

const rawText = await billingRes.text(); // MIME type이 text/plain이기 때문
let billingData;
try {
	billingData = JSON.parse(rawText); // text → JSON 객체로 변환
} catch (err) {
	console.error("JSON 파싱 오류:", err);
	alert("결제 응답이 잘못되었습니다.");
	return;
}

	/*console.log("결제 요청 응답 :",res);
	alert("res", res);
	const data = await res.json();
	console.log("응답 받은데이터 :",data);
	alert("여기까진 문제없음6");
	if (!data.paymentKey) {
		alert("결제 실패 또는 응답 오류");
		return;
	}*/
	
	alert("여기까진 오니?");
	const confirmRes = await fetch("/company/payment/product/change/confirm", {
	method: "POST",
	headers: { "Content-Type": "application/json" },
	body: JSON.stringify({
		oldProductNo,
		oldPaymentNo,
		newProductNo,
		billingKey,
		paymentKey: billingData.paymentKey,
		orderId
	})
});
	alert("돌아왔니 ?! 2");

if (confirmRes.ok) {
	alert("요금제 변경 완료!");
	window.location.href = "/company/payment/main";
} else {
	const errText = await confirmRes.text();
	alert("요금제 변경 실패\n" + errText);
}

}

async function requestBillingAuth() {
	await payment.requestBillingAuth({
		method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
		successUrl, // 현재 페이지로 돌아오게 함
		failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
		customerEmail: "dhfjdhfj@naver.com",
		customerName: "김철민",
	})
};
