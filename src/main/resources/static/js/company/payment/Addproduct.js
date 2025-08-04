function selectType(type) {
	
	document.getElementById("formCard").classList.remove("d-none");

	document.getElementById("productType").value = type === "Premium" ? 'Premium' : 'Business';

	document.getElementById("card-regular").classList.remove("selected");
	document.getElementById("card-single").classList.remove("selected");

	
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
	console.log("");
	// 제목에 선택된 유형 표시
	document.getElementById("selectedType").textContent = `(${type})`;

}



document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('insertForm');
    form.addEventListener("submit", async function (e) {
        e.preventDefault();
        const formData = new FormData(form);

        try {
            const response = await fetch("/admin/insert", {
                method: "POST",
                body: formData
            });

            const data = await response.json();

            if (!response.ok || !data.success) {
                alert(data.error || "등록 실패");
                return;
            }

            location.href = data.redirectUrl;

        } catch (err) {
            alert("서버 오류: " + err.message);
        }
    });
});



/*document.addEventListener('DOMContentLoaded', function() {
	const form = document.getElementById('insertForm');
	form.addEventListener("submit", function (e){
		
		 const formData = new FormData(form);
		 fetch("/admin/insert", {
			method: "POST",
			body: formData
		 }).then(r => r.text()).then(console.log);
	});
	form.setAttribute('action', '/admin/insert');
	form.setAttribute('method', 'post');
	form.setAttribute('enctype', 'multipart/form-data');
});*/