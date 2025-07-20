<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역 List</title>
</head>
<body>
	이 페이지는 기간이 지난 상품들만 나타내기위함
	<div class="container my-5">
		<h2 class="mb-4">🧾 내가 구매한 상품</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품명</th>
					<th>결제금액</th>
					<th>결제기간</th>

					<th>결제수단</th>
					<th>누적사용액</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${purchaseList}">
					<tr>
						<td><c:forEach var="product"
								items="${item.paymentProductList}">
                        ${product.productName}<br />
							</c:forEach></td>
						<td>${item.paymentPay}원</td>
						<td>${item.startDate}~ ${item.endDate }</td>
						<%-- <td>
   					 <c:forEach var="product" items="${item.paymentProductList}">
					${product.productName} 의 남은기간은 ${product.daysRemaining}일 입니다<br/>
						</c:forEach>
						</td> --%>
						<td>${item.paymentMethod}</td>
						<td><c:forEach var="product"
								items="${item.paymentProductList }">
								<c:set var="count"
									value="${productCountMap[product.productName]}" />
								<c:set var="total" value="${item.paymentPay * count }" />
							누적 사용액 : <fmt:formatNumber value="${total }" type="number" /> 원 <br />
							</c:forEach></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right;"><strong>총
							누적 사용액 :</strong></td>
					<td><strong><fmt:formatNumber
								value="${totalUsedAmount}" type="number" /> 원</strong></td>
				</tr>
			</tbody>
			</div>
</body>
</html>