function doBilling() {
 	
 	const body = document.body;
	const billingKey = PbillingKey;
	const customerKey = PcustomerKey;
	const amount = Pamount;
	const orderName = PproductName;
	const productNo = PproductNo;;

    console.log("billingKey", billingKey);
    console.log("customerKey", customerKey);
    console.log("amount", amount);
    console.log("orderName", orderName);
    fetch("/company/toss/api/billing/execute", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            billingKey: billingKey,
            customerKey: customerKey,
            amount: Number(amount),
            orderName: orderName
        })
        
    })
    .then(res => {
		console.log("res :",res);
		alert("로그확인시간");
		return res.json();
		})
    .then(data => {
        alert("결제 성공!");
        console.log(data);
        // 성공 후 리디렉션 등 처리 가능
        window.location.href="/company/payment/success/executebilling";
    })
    .catch(err => {
        alert("결제 실패!");
        console.error("Billing error:", err);
    });
}
