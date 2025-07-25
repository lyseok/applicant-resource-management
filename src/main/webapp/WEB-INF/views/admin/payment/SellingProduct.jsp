<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품별 매출 내역</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container my-5">
		<h2 class="mb-4">💰 상품별 매출 현황</h2>

		<table class="table table-bordered table-hover">
			<thead class="table-light">
				<tr>
					<th>상품명</th>
					<th>상품타입</th>
					<th>총 매출액</th>
					<th>판매 건수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${vo}">
					<c:set var="totalAmount" value="0" />
					<c:set var="saleCount" value="0" />

					<!-- 결제내역과 매칭 -->
					<c:forEach var="pay" items="${pvo}">
						<c:if test="${product.productNo == pay.productNo}">
							<c:set var="totalAmount" value="${totalAmount + pay.paymentPay}" />
							<c:set var="saleCount" value="${saleCount + 1}" />
						</c:if>
					</c:forEach>

					<tr>
						<td>${product.productName}</td>
						<td>${product.productType}</td>
						<td><fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="₩" /></td>
						<td>${saleCount} 건</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
