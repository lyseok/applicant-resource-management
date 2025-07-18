function doBilling() {
 
    const billingKey = body.dataset.billingKey;
    const customerKey = body.dataset.customerKey;
    const amount = body.dataset.amount;
    const orderName = body.dataset.orderName;
    const productNo = body.dataset.productNo;

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
    .then(res => res.json())
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
