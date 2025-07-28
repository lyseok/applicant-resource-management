<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://js.tosspayments.com/v1"></script>
<script src="/js/company/payment/TossPayment.js"></script>
<style>
/* 전체 배경 및 기본 폰트 설정 */
body {
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #f8f9fa;
}

/* 제목 스타일 */
h2.fw-bold {
  color: #343a40;
}

/* 상품 카드 스타일 */
.list-group-item {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.list-group-item:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

/* 이미지 테두리 강조 */
.list-group-item img {
  border: 2px solid #dee2e6;
}

/* 상품 설명 텍스트 */
.list-group-item p {
  margin-bottom: 0.25rem;
  font-size: 0.95rem;
}

.list-group-item h5 {
  font-size: 1.2rem;
  color: #212529;
}

/* 필터 섹션 드롭다운 */
.form-select {
  min-width: 140px;
}

/* 페이징 버튼 */
.pagination .page-item.active .page-link {
  background-color: #7e57c2;
  border-color: #7e57c2;
}

.pagination .page-link {
  color: #7e57c2;
}

.pagination .page-link:hover {
  color: #5e3fa3;
}

/* 버튼 공통 정렬 */
.d-flex.flex-column.gap-2 .btn {
  width: 100px;
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<!-- 헤더 -->
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h2 class="fw-bold mb-0">📦 등록된 상품 목록</h2>
			<a href="/admin/add" class="btn btn_violet h50">+ 상품 등록하기</a>
			<%-- 버튼 색상 변경 (btn -> btn_violet) --%>
		</div>

		<!-- 필터 폼 -->
		<form class="row g-2 mb-4" method="get" action="">
			<div class="col-auto">
				<select class="form-select" name="filterType">
					<option value="" ${empty param.filterType ? "selected" : ""}>전체</option>
					<option value="S"
						${param.filterType == 'BUSINESS' ? "selected" : ""}>Business</option>
					<option value="L"
						${param.filterType == 'PREMIUM' ? "selected" : ""}>Premium</option>
				</select>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn_violet h50">검색</button>
			</div>
		</form>

		<!-- 상품 목록 -->
		<div class="list-group">
			<c:forEach var="product" items="${productList}">
				<div
					class="list-group-item p-4 mb-3 shadow-sm rounded border d-flex justify-content-between align-items-center"
					data-product-no="${product.productNo}" onclick="AdminDetail(this)">
					<div class="d-flex align-items-center gap-3">
						<img src="${product.productImg}" alt="상품 이미지" class="rounded"
							style="width: 120px; height: 120px; object-fit: cover;">
						<div>
							<h5 class="fw-bold mb-2">${product.productName}</h5>
							<p class="text-muted mb-1">${product.productDetail}</p>
							<p class="mb-1">
								<strong>가격:</strong> ₩
								<fmt:formatNumber value="${product.productPrice}"
									pattern="#,##0" />
							</p>
							<p class="mb-1">
								<strong>유형:</strong>
								<c:choose>
									<c:when test="${product.productType == 'PREMIUM'}">PREMIUM</c:when>
									<c:when test="${product.productType == 'BUSINESS'}">BUSINESS</c:when>
									<c:otherwise>${product.productType}</c:otherwise>
								</c:choose>
							</p>
							<p class="text-secondary">
								<small>이용기간: ${product.productPeriod}</small>
							</p>
						</div>
					</div>
					<!-- 버튼 -->
					<div class="d-flex flex-column gap-2">
						<a href="/admin/edit?productNo=${product.productNo}"
							class="btn btn_gray_line">수정</a>
						<button type="button" class="btn btn-outline-danger"
							onclick="event.stopPropagation(); deleteProduct(${product.productNo})">삭제</button>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- 페이징 -->
		<c:if test="${totalPages > 1}">
			<nav class="mt-5">
				<ul class="pagination justify-content-center">
					<c:forEach begin="1" end="${totalPages}" var="i">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link"
							href="?page=${i}&filterType=${param.filterType}">${i}</a></li>
					</c:forEach>
				</ul>
			</nav>
		</c:if>
	</div>

	<script src="/js/admin/payment/AdminProduct.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
