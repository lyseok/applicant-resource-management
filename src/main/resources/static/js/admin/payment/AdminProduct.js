function AdminDetail(cardElement) {
	const productNo = cardElement.getAttribute("data-product-no");
	if(productNo){
		window.location.href=`/admin/detail?productNo=${productNo}`;
	}
}

function deleteProduct() {
	const button = document.getElementById("deleteBtn");
	 const productNo = button.dataset.productNo;
	console.log("productNo", productNo);
	if(confirm("정말 삭제하시겠습니까?")){
		console.log("삭제할 productNo :", productNo);
		window.location.href=`/admin/delete/product?productNo=${productNo}`;
	}
}