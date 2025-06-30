/* 페이지에 n_inner 클래스가 있을경우, inner 값 없앰. */
document.addEventListener("DOMContentLoaded", function(){
	const el = document.querySelector('.n_inner');
	if (el) {
		 const parent = el.closest('.inner');
		 parent.style.maxWidth = "100%";
	}
})