function AdminDetail(cardElement) {
	const productNo = cardElement.getAttribute("data-product-no");
	if(productNo){
		window.location.href=`/admin/detail?productNo=${productNo}`;
	}
}