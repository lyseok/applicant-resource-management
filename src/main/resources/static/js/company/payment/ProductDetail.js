
function subscribe() {
	alert("상품번호" , ProductNo);
	console.log("커스터머키" ,customerKey);
	console.log("상품명", orderName);
	alert("빌링키", BillingKey);
   	const productNo = ProductNo;
   	
   	console.log("상품번호 : ", productNo);
   	 console.log("빌링키",BillingKey);
   	
   
     fetch("/company/toss/check/billing",{
		method : "GET" ,
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
                // ✅ billingKey가 있으면 결제 페이지 이동
                console.log("cmProductNo");
               
                /*window.location.href  = "/company/toss/buyproduct";*/
                window.location.href  = "/company/toss/buyproduct?productNo=" + productNo;
            } else {
                // ❌ 없으면 카드 등록 모달
                showNoCardModal();
            }
        })
        .catch(err => {
			
            console.error("Billing check error:", err);
        });
}

function showNoCardModal() {
    const modal = new bootstrap.Modal(document.getElementById("noCardModal"));
    modal.show();
}

const billingKey = document.body.dataset.billingKey;
if(billingKey) {
	console.log("받은 billingKey", billingKey);
}

function requestbillingPayment(){
	const productNo = ProductNo;
	const billingKey = BillingKey;
	const amount = Amount;
	const customerKey = CustomerKey;
	
	if(!billingKey) {
		alert("등록된 카드가 없다");
		return ;
	}
	
	const url = `/company/toss/executebilling?productNo=${productNo}&billingKey=${billingKey}&customerKey=${customerKey}&amount=${amount}`;		
	window.location.href = url;
	
}

function changesubscribe(button) {
	const productNo = button.dataset.productNo;
	const billingKey = button.dataset.billingKey;
	const customerKey = button.dataset.customerKey;
	const amount = button.dataset.amount;
	const orderName = button.dataset.orderName;

	alert("상품번호: " + productNo);
	console.log("커스터머키:", customerKey);
	console.log("상품명:", orderName);
	alert("빌링키: " + billingKey);

	// 결제 가능한지 먼저 확인
	fetch("/company/toss/check/billing", {
		method: "GET",
		credentials: "include"
	})
	.then(response => {
		console.log("응답 status:", response);
		return response.json();
	})
	.then(data => {
		console.log("받은 데이터:", data);
		if (data.hasBillingKey) {
			// ✅ 결제 페이지 이동
			window.location.href = `/company/toss/buyproduct?productNo=${productNo}`;
		} else {
			showNoCardModal();
		}
	})
	.catch(err => {
		console.error("Billing check error:", err);
	});
}
