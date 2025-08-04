function doBilling() {

	const body = document.body;
	const billingKey = PbillingKey;
	const customerKey = "h5hXSJ-WPK8sZQpXQUJUA";
	const amount = Pamount;
	const orderName = PproductName;
	const productNo = PproductNo;;

	/*console.log("billingKey", billingKey);
	console.log("customerKey", customerKey);
	console.log("amount", amount);
	console.log("orderName", orderName);*/

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
			console.log("orderName", orderName)
			return res.json();
		})
		.then(data => {
			console.log("data 전체", data);

			/*const { billingKey, orderName, amount, paymentKey } = data;*/

			const paymentKey = data.paymentKey;
			const amount = Pamount;
			const orderName = PproductName;
			const billingKey = PbillingKey;
			const orderId = data.orderId;
			/*console.log("paymentKey :", paymentKey);
			console.log("billingKey:", billingKey);
			console.log("amount:", amount);
			console.log("orderName:", orderName);
			console.log("paymentKey:", paymentKey);*/
			
			alert("결제 성공!");
			
			instantUnlockMenu();

			window.location.href =
				`/company/payment/success/executebilling?billingKey=${billingKey}` +
				`&amount=${amount}` +
				`&orderName=${encodeURIComponent(orderName)}` +
				`&paymentKey=${paymentKey}` +
				`&orderId=${orderId}` +
				`&paymentKey=${paymentKey}`;
		})
		.catch(err => {
			alert("결제 실패!");
			console.error("Billing error:", err);
		});
}

function instantUnlockMenu() {
  sessionStorage.setItem("menuUnlocked", "true");
  const menuEls = document.querySelectorAll('#layout-menu .off');
  menuEls.forEach(el => {
    el.classList.remove('off');
    el.classList.add('unlock-anim');
    setTimeout(() => el.classList.remove('unlock-anim'), 2000);
  });
  console.log("[DEBUG] Menu unlocked instantly after payment");
}
