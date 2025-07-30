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
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f8f9fa;
	color: #333;
}

.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

.header {
	text-align: center;
	margin-bottom: 30px;
}

.header h1 {
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 10px;
}

.subtitle {
	color: #666;
	font-size: 14px;
}

.content-wrapper {
	display: flex;
	flex-direction: row;
	gap: 30px;
}

/* .preview-section {
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	width: 668px;
	height: 900px;
} */

.packages-section {
	display: flex;
	flex-direction: column;
	gap: 20px;
	width: calc100%;
}

.package-card {
	background: white;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border: 1px solid #e0e0e0;
	cursor: pointer;
	transition: transform .2s ease, box-shadow .2s ease;
}

.package-card:hover {
	transform: translateY(-4px);
	box-shadow: 0 6px 20px rgba(0, 0, 0, .1);
}

.package-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 15px;
}

.package-header h5 {
	font-size: 1.2rem;
	font-weight: bold;
	color: #212529;
}

.package-icons {
	display: flex;
	gap: 10px;
}

.icon {
	width: 30px;
	height: 30px;
	background: #f0f0f0;
	border-radius: 4px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
}

.package-tags {
	margin-bottom: 15px;
}

.package-options {
	display: flex;
	align-items: center;
	gap: 15px;
	margin-bottom: 20px;
	padding: 15px;
	background: #f8f9fa;
	border-radius: 4px;
}

.package-pricing {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 15px;
}

.package-pricing strong {
	margin-right: 5px;
}

.purchase-btn {
	padding: 12px 24px;
	background: #ff4444;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: bold;
	font-size: 14px;
}

.purchase-btn:hover {
	background: #cc3333;
}

.package-info {
	display: flex;
	align-items: center;
	gap: 5px;
	color: #666;
	font-size: 14px;
}

.info-icon {
	font-size: 16px;
}

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
</style>
</head>
<body>
	<div class="container">
		<header class="header">
			<h1>📦 등록된 상품 목록</h1>
			<p class="subtitle">대표 채용공고 지면에서 다수 구직자에게 효과적으로 공고를 홍보하는 상품</p>
			<a href="/admin/add" class="btn btn_violet h50 mt-3">+ 상품 등록하기</a>
		</header>

		<!-- 필터 폼 -->
		<form class="row g-2 mb-4" method="get" action="">
			<div class="col-auto">
				<select class="form-select" name="filterType">
					<option value="" ${empty param.filterType ? "selected" : ""}>전체</option>
					<option value="BUSINESS"
						${param.filterType == 'BUSINESS' ? "selected" : ""}>Business</option>
					<option value="PREMIUM"
						${param.filterType == 'PREMIUM' ? "selected" : ""}>Premium</option>
				</select>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn_violet h50">검색</button>
			</div>
		</form>

		<div class="content-wrapper">
			<!-- 왼쪽 미리보기 영역 -->
			<!-- <div id="preview-container" class="preview-section">
				<p>상품을 선택하면 상세 정보가 표시됩니다.</p>
			</div> -->

			<!-- 오른쪽 상품 카드 리스트 -->
			<div class="packages-section">
				<c:forEach var="product" items="${productList}">
					<div class="package-card" data-product-no="${product.productNo}"
						onclick="goToDetail(this)">
						<div class="package-header">
							<h5>${product.productName}</h5>
							<div class="package-icons">
								<span class="icon">📱</span> <span class="icon">💼</span> <span
									class="icon">📊</span>
							</div>
						</div>
						<div class="package-tags">
							<p class="card-text">${product.productDetail}</p>
						</div>
						<div class="package-options">
							<p class="card-text mb-1">
								<strong>유형:</strong>
								<c:choose>
									<c:when test="${product.productType == 'PREMIUM'}">PREMIUM</c:when>
									<c:when test="${product.productType == 'BUSINESS'}">BUSINESS</c:when>
									<c:otherwise>${product.productType}</c:otherwise>
								</c:choose>
							</p>
						</div>
						<div class="package-pricing">
							<p class="card-text mb-1">
								<strong>가격:</strong> ₩
								<fmt:formatNumber value="${product.productPrice}"
									pattern="#,##0" />
							</p>
							<button class="purchase-btn">상품 구매</button>
						</div>
						<div class="package-info">
							<span class="info-icon">🏢</span> <span>상품혜택</span>
						</div>
					</div>
				</c:forEach>
			</div>
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
