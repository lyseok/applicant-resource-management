
function subscribe() {
	alert(ProductNo);
	
   	const productNo = ProductNo;
   	console.log("상품번호 : ", productNo);
     fetch("/company/toss/check/billing") // <-- 여기 경로 중요
        .then(response => response.json())
        .then(data => {
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
	
	const url = `/company/toss/executeBilling?productNo=${productNo}&billingKey=${billingKey}&customerKey=${customerKey}&amount=${amount}`;		
	window.location.href = url;

	
}