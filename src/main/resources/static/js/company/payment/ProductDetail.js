
function subscribe() {
	alert(cmProductNo);
	
   	const productNo = cmProductNo;
   	console.log("상품번호 : ", productNo);
     fetch("/company/toss/check/billing") // <-- 여기 경로 중요
        .then(response => response.json())
        .then(data => {
            if (data.hasBillingKey) {
                // ✅ billingKey가 있으면 결제 페이지 이동
                window.location.href  = "/company/toss/buyproduct";
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