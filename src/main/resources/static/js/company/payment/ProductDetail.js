
function subscribe() {
	alert("상품번호" , ProductNo);
	/*console.log("커스터머키" ,customerKey);*/
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


async function requestBillingAuth() {
        await payment.requestBillingAuth({
          method: "CARD", // 자동결제(빌링)는 카드만 지원합니다
          successUrl , // 현재 페이지로 돌아오게 함
          failUrl: window.location.origin + "/fail", // 요청이 실패하면 리다이렉트되는 URL
          customerEmail: "dhfjdhfj@naver.com",
          customerName: "김철민",
        })
      };


function showNoCardModal() {
    const modal = new bootstrap.Modal(document.getElementById("noCardModal"));
    modal.show();
}

const billingKey = document.body.dataset.billingKey;
if(billingKey) {
	console.log("받은 billingKey", billingKey);
}
