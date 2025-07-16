function selectType(type) {
	// 폼 보이기
	document.getElementById("formCard").classList.remove("d-none");

	// 유형 저장
	document.getElementById("productType").value = type;

	// 카드 선택 스타일 적용
	document.getElementById("card-regular").classList.remove("selected");
	document.getElementById("card-single").classList.remove("selected");

	if (type === "정기권") {
		document.getElementById("card-regular").classList.add("selected");
	} else {
		document.getElementById("card-single").classList.add("selected");
	}

	// 제목에 선택된 유형 표시
	document.getElementById("selectedType").textContent = `(${type})`;
}

document.addEventListener('DOMContentLoaded', function() {
	const form = document.getElementById('insertForm');
	form.setAttribute('action', '/company/payment/product');
	form.setAttribute('method', 'post');

})



/*const form = document.getElementById('insertForm');
form.addEventListener('submit', function(e) {
	e.preventDefault();

	// FormData 객체로 폼 데이터 수집
	const formData = new FormData(form);

	// applicantId와 time을 추출해서 배열로 만듦
	const applicantTimes = [];
	for (let [key, value] of formData.entries()) {
		if (key.startsWith("applicantTime_")) {
			const applicantId = key.replace("applicantTime_", "");
			applicantTimes.push({
				applicantId: applicantId,
				evaluationStartTime: value
			});
		}
	}

	const data = {
		productName: formData.get('productName'),
		productDetail: formData.get('productDetail'),
		productPrice: formData.get('productPrice'),
		productImg: formData.get('productImg'),
		applicantTimes: applicantTimes // 배열!
	};
	// axios POST로 전송 (예시 URL)
	axios.post('/ajax/admin/payment/addproduct', data)
		.then(res => {
			alert('저장 성공!');
			// 필요시 location.href = ... 등 처리
		})*/