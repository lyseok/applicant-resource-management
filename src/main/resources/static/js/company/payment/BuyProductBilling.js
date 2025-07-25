function doBilling() {

	const body = document.body;
	const billingKey = PbillingKey;
	const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
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
			console.log("res :", res);
			console.log("orderName",orderName)
			alert("로그확인시간");
			return res.json();
		})
		.then(data => {
			console.log("data 전체", data);

			/*const { billingKey, orderName, amount, paymentKey } = data;*/

			const paymentKey = data.paymentKey;
			const amount = Pamount;
			const orderName = PproductName;
			const billingKey = PbillingKey;

			console.log("billingKey:", billingKey);
			console.log("amount:", amount);
			console.log("orderName:", orderName);
			console.log("paymentKey:", paymentKey);
			alert("결제 성공!");

			window.location.href =
				`/company/payment/success/executebilling?billingKey=${billingKey}` +
				`&amount=${amount}&orderName=${encodeURIComponent(orderName)}` +
				`&paymentKey=${paymentKey}`;
		})
		.catch(err => {
			alert("결제 실패!");
			console.error("Billing error:", err);
		});
}
