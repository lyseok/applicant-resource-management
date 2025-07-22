<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 결제상품</title>
</head>
<body>
	현재 살아있는 유료상품 내용 : (구현예정)
	<a href="/company/payment/product/list"><button>상품 보러가기</button></a>

	<div class="container my-5">
		<h2 class="mb-4">🧾 내가 구매한 상품</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품명</th>
					<th>결제금액</th>
					<th>결제일시</th>
					<th>남은기간</th>
					<th>결제수단</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${purchaseList}">
					<tr>
						<td><c:forEach var="product"
								items="${item.paymentProductList}"
								>
                        ${product.productName}<br />
							</c:forEach></td>
						<td>${item.paymentPay}원</td>
						<td>${item.paymentDate}</td>
						<td>${item.paymentMethod}</td>
					</tr>
				</c:forEach>
			</tbody>
	</div>

</body>
</html>