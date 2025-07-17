function doBilling() {
    const billingKey = document.body.dataset.billingKey;
    const customerKey = document.body.dataset.customerKey;
    const amount = document.body.dataset.amount;
    const orderName = document.body.dataset.orderName;

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
    })
    .catch(err => {
        alert("결제 실패!");
        console.error("Billing error:", err);
    });
}
