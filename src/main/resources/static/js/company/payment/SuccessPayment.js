document.addEventListener("DOMContentLoaded" , ()=> {
	const orderName = localStorage.getItem("orderName");
	const amount = localStorage.getItem("amount");
	
	const orderId = document.querySelector("#orderId").value;
	
	fetch("/company/payment/update",{
		method : "POST",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify({
			orderId
			,amount
			,additionalMemo : "추가정보 입력가능"
		})
	});
	
});