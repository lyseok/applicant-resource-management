
function subscribe(event ,btn) {
	
   	const productNo = btn.dataset.productNo;
   	const billingKey = btn.dataset.billingKey;
	/*console.log("커스터머키" ,customerKey);*/
	console.log("상품명", orderName);
   	
   	console.log("상품번호 : ", productNo);
   	 console.log("빌링키",billingKey);
   	
   
     fetch("/company/toss/check/billing",{
		method : "POST" ,
		credentials : "include"
	 }) // <-- 여기 경로 중요
        .then(response => {
			console.log("응답 status:" ,response);
			return response.json();
			})
        .then(data => {
			console.log("받은 데이터 : " ,data);
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

function showNoCardModal() {
    const modal = new bootstrap.Modal(document.getElementById("noCardModal"));
    modal.show();
}

const billingKey = btn.dataset.billingKey;
if(billingKey) {
	console.log("받은 billingKey", billingKey);
}
