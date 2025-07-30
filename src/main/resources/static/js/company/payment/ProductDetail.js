
function subscribe(event ,btn) {
	
   	const productNo = btn.dataset.productNo;
   	const billingKey = btn.dataset.billingKey;
	alert("상품번호" , productNo);
	/*console.log("커스터머키" ,customerKey);*/
	console.log("상품명", orderName);
	alert("빌링키", billingKey);
   	
   	console.log("상품번호 : ", productNo);
   	 console.log("빌링키",billingKey);
   	
   
     fetch("/company/toss/check/billing",{
		method : "POST" ,
		credentials : "include"
	 }) // <-- 여기 경로 중요
        .then(response => {
			console.log("응답 status:" ,response);
			alert("잠깐 로그 확인시간");
			return response.json();
			})
        .then(data => {
			console.log("받은 데이터 : " ,data);
			alert("잠깐 로그 확인시간");
            if (data.hasBillingKey) {
				const go = confirm("등록된 카드로 결제 하시겠습니까?");
				if(go){
                window.location.href  = "/company/toss/buyproduct?productNo=" + productNo;					
				} else {
				// ❌ 카드 등록 화면으로 이동
				requestBillingAuth();
			}
		} else {
			const goToBilling = confirm("등록된 카드가 없습니다. 카드를 등록하시겠습니까?");
			if (goToBilling) {
				requestBillingAuth();
			}
		}
	})
        .catch(err => {
			
            console.error("Billing check error:", err);
        });
}


/*async function requestBillingAuth() {
        await payment.requestBillingAuth({
          method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
          successUrl , // 현재 페이지로 돌아오게 함
          failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
          customerEmail: "dhfjdhfj@naver.com",
          customerName: "김철민",
        })
      };*/

const clientKey = "test_ck_Gv6LjeKD8a9wXO9o7lLw8wYxAdXy";
const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
const tossPayments = TossPayments(clientKey);
const payment = tossPayments.payment({ customerKey });
const successUrl = `${window.location.origin}/company/toss/success?productNo=${productNo}`;

async function requestBillingAuth() {
	const authResult = await tossPayments.billing.requestBillingAuth({
		method: "CARD",
		customerKey: "h5hXSJ-WPK8sZQpXQUJUA",
		customerEmail: "test@test.com",
		customerName: "홍길동"
	});
	const authKey = authResult.authKey;

	const response = await fetch("/company/toss/api/toss/billing/issue", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ authKey: authKey, customerKey: "h5hXSJ-WPK8sZQpXQUJUA" })
	});

	const data = await response.json();
	console.log("billingKey:", data.billingKey);
}

fetch("/api/toss/billing/execute", {
	method: "POST",
	headers: {
		"Content-Type": "application/json"
	},
	body: JSON.stringify({
		billingKey: "${sessionScope.billingKey}",
		customerKey: customerKey,
		amount: "${product.productPrice}"
	})
})
	.then(res => res.json())
	.then(date => {
		console.log("결제 성공 ! ", data);
	})
	.catch(err => {
		consoler.error("결제 실패 !", err);
	})


function showNoCardModal() {
    const modal = new bootstrap.Modal(document.getElementById("noCardModal"));
    modal.show();
}

const billingKey = btn.dataset.billingKey;
if(billingKey) {
	console.log("받은 billingKey", billingKey);
}
