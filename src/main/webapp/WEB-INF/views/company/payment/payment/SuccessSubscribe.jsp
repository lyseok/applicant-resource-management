<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 결과</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<div class="card shadow-lg p-4">
			<h3 class="card-title text-center mb-4">결제 완료</h3>

			<table class="table table-bordered">
				<tbody>
					<tr>
						<th scope="row" class="w-25">결제수단</th>
						<td>${result.paymentMethod}</td>
					</tr>
					<tr>
						<th scope="row">상품명</th>
						<td>
							<c:forEach var="product" items="${product.paymentProductList}">
       						     ${product.productName}<br />
							</c:forEach>
						</td>
					</tr>

					<tr>
						<th scope="row">구매금액</th>
						<td>${result.paymentPay}원</td>
					</tr>
					<tr>
						<th scope="row">결제일시</th>
						<td>${result.paymentDate}</td>
					</tr>
					<tr>
						<th scope="row">상품 이용기간</th>
						<td>구매일로 부터 30일</td>
					</tr>
				</tbody>
			</table>

			<div class="text-center mt-4">
				<a href="/company/payment/main" class="btn btn-primary">대시보드로 이동</a>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS (optional for interactions) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>