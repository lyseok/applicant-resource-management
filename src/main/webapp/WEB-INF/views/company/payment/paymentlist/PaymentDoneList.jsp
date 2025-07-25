<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역 List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container my-5">
		<h2 class="mb-4">🧾 내가 구매한 상품</h2>

		<!-- ✅ 필터 폼 -->

		<form method="get" class="mb-3 d-flex">
			<select name="filter" class="form-select w-auto me-2">
				<option value="" ${empty selectedFilter ? 'selected' : ''}>전체
					보기</option>
				<option value="A" ${selectedFilter == 'A' ? 'selected' : ''}>현재
					사용 중</option>
				<option value="E" ${selectedFilter == 'E' ? 'selected' : ''}>사용기간
					만료</option>
				<option value="S" ${selectedFilter == 'S' ? 'selected' : ''}>다음결제일부터
					사용가능</option>
				<option value="C" ${selectedFilter == 'C' ? 'selected' : ''}>다음결제일부터
					사용불가</option>
			</select>

			<button type="submit" class="btn btn-primary">필터 적용</button>
		</form>

		<!-- ✅ 결제 내역 테이블 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품명</th>
					<th>결제금액</th>
					<th>사용기간</th>
					<th>결제수단</th>
					<th>현재상태</th>
					<th>순차 누적 사용액</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${purchaseList}" varStatus="status">
					<tr>
						<td><c:forEach var="product"
								items="${item.paymentProductList}">
                            ${product.productName}<br />
								<small class="text-muted"> <c:choose>
										<c:when test="${product.daysRemaining <= 0}">🔴 만료됨</c:when>
										<c:otherwise>🟢 ${product.daysRemaining}일 남음</c:otherwise>
									</c:choose>
								</small>
								<br />
							</c:forEach></td>
						<td><fmt:formatNumber value="${item.paymentPay}"
								type="number" /> 원</td>
						<td>${item.startDate}~${item.endDate}</td>
						<td>${item.paymentMethod}</td>

						<td><c:choose>
								<c:when test="${item.status == 'A'}">현재 사용 중</c:when>
								<c:when test="${item.status == 'E'}">사용기간 만료</c:when>
								<c:when test="${item.status == 'S'}">다음결제일부터 사용가능</c:when>
								<c:when test="${item.status == 'C'}">다음결제일부터 사용불가</c:when>
								<c:otherwise>알 수 없음</c:otherwise>
							</c:choose></td>
						<td><fmt:formatNumber value="${cumulativeList[status.index]}"
								type="number" /> 원</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="4" class="text-end"><strong>총 누적 사용액 :</strong></td>
					<td><strong><fmt:formatNumber
								value="${totalUsedAmount}" type="number" /> 원</strong></td>
				</tr>
			</tbody>
		</table>

		<!-- ✅ 페이징 -->
		<c:if test="${totalPages > 1}">
			<nav class="mt-4">
				<ul class="pagination justify-content-center">
					<c:forEach begin="1" end="${totalPages}" var="i">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link" href="?page=${i}&filter=${selectedFilter}">
								${i} </a></li>
					</c:forEach>
				</ul>
			</nav>
		</c:if>
	</div>
</body>
</html>
