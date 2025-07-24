function selectType(type) {
	// 폼 보이기
	document.getElementById("formCard").classList.remove("d-none");

	// 유형 저장
	document.getElementById("productType").value = type === "정기권" ? 'L' : 'S';

	// 카드 선택 스타일 적용
	document.getElementById("card-regular").classList.remove("selected");
	document.getElementById("card-single").classList.remove("selected");

	if (type === '정기권') {
		document.getElementById("card-regular").classList.add("selected");
		document.getElementById('productPeriodDiv').style.display = 'block'; // 기간 설정 div 표시
		localStorage.setItem("selectedType", "정기권");
	} else {
		document.getElementById("card-single").classList.add("selected");
		document.getElementById('productPeriodDiv').style.display = 'none'; // 기간 설정 div 숨기기
		localStorage.setItem("selectedType" , "단건");
	}

	// 공통 사항인 'PRODUCT_LIMIT' 설정을 표시
	document.getElementById('productLimitDiv').style.display = 'block'; // 한도 설정 div 표시

	

	// 제목에 선택된 유형 표시
	document.getElementById("selectedType").textContent = `(${type})`;
}

document.addEventListener('DOMContentLoaded', function() {
	const form = document.getElementById('insertForm');
	form.setAttribute('action', '/admin/insert');
	form.setAttribute('method', 'post');

})



