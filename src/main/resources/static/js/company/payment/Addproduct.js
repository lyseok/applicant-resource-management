function selectType(type) {
	// 폼 보이기
	document.getElementById("formCard").classList.remove("d-none");

	// 유형 저장 (상품 유형: 'L' = 정기권(Premium), 'S' = 일반(Business))
	document.getElementById("productType").value = type === "Premium" ? 'L' : 'S';

	// 카드 선택 스타일 적용
	document.getElementById("card-regular").classList.remove("selected");
	document.getElementById("card-single").classList.remove("selected");

	// 조건별 처리
	if (type === 'Premium') {
		document.getElementById("card-regular").classList.add("selected");
		document.getElementById('productPeriodDiv').style.display = 'none';  // 기간 보이기
		document.getElementById('productLimitDiv').style.display = 'none';    // 한도 숨기기
		localStorage.setItem("selectedType", "Premium");
	} else {
		document.getElementById("card-single").classList.add("selected");
		document.getElementById('productPeriodDiv').style.display = 'none';   // 기간 숨기기
		document.getElementById('productLimitDiv').style.display = 'block';   // 한도 보이기
		localStorage.setItem("selectedType", "Business");
	}

	// 제목에 선택된 유형 표시
	document.getElementById("selectedType").textContent = `(${type})`;
	
	document.addEventListener('DOMContentLoaded', function() {
	const form = document.getElementById('insertForm');
	form.setAttribute('action', '/admin/insert');
	form.setAttribute('method', 'post');
});
	
}
