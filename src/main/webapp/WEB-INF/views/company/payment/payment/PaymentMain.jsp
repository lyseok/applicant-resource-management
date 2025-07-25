<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>나의 결제상품</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container my-5">
		<h2 class="mb-4">🧾 나의 결제 상품</h2>

		<c:if test="${empty ppvo}">
			<div class="alert alert-info">활성화된 상품이 없습니다.</div>
		</c:if>
		<a href="/company/payment/product/list"><button>상품목록 보러가기</button></a>
		<c:if test="${not empty ppvo}">
			<table class="table table-bordered">
				<thead class="table-secondary">
					<tr>
						<th>상품명</th>
						<th>결제금액</th>
						<th>결제일시</th>
						<th>남은기간</th>
						<th>결제수단</th>
						<th>잔여메일발송횟수</th>
						<th>메일발송초기화일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<!-- 상품명: paymentProductList 에서 productName 반복 출력 -->
						<td><c:forEach var="prod" items="${ppvo.paymentProductList}">
								<a
									href="/company/payment/buydetail?paymentNo=${ppvo.paymentNo}&productNo=${prod.productNo}">
									${prod.productName} </a>
								<br />
							</c:forEach></td>

						<!-- 결제금액 -->
						<td><fmt:formatNumber value="${ppvo.paymentPay}"
								type="currency" currencySymbol="₩" /></td>

						<!-- 결제일시 -->
						<td>${ppvo.paymentDate}</td>

						<!-- 남은기간 -->
						<td><c:choose>
							<c:when test="${ppvo.daysRemaining >= 0}">
     					 다음결제일까지 ${ppvo.daysRemaining}일 남았습니다.
    						</c:when>
								<c:otherwise>
   							   기간 만료
    							</c:otherwise>
							</c:choose></td>

						<!-- 결제수단 -->
						<td>${ppvo.paymentMethod}</td>

						<!-- 잔여메일발송횟수 -->
						<c:choose>
							<c:when test="${ppvo.usageRemaining == -1}">
								<td>무제한 이용가능</td>
							</c:when>
							<c:otherwise>
								<td>${ppvo.usageRemaining}회남음</td>
							</c:otherwise>
						</c:choose>

						<!-- 메일발송초기화일 -->
						<td>${ppvo.endDate}</td>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
