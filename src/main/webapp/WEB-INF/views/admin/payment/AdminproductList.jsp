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

/* 헤더 영역 */
.header {
	margin-bottom: 20px;
}

.header h1 {
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 5px;
}

.subtitle {
	color: #666;
	font-size: 14px;
}

/* 헤더 상단 우측 정렬 */
.header-actions {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-bottom: 30px;
}

/* 패키지 카드 */
.packages-section {
	display: flex;
	flex-direction: column;
	gap: 20px;
	width: 100%;
}

.package-card {
	background: white;
	border-radius: 8px;
	width: 100%;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border: 1px solid #7e57c2;
	cursor: pointer;
	transition: transform .2s ease, box-shadow .2s ease;
	position: relative;
	overflow: hidden;
}

.package-card:hover {
	transform: translateY(-4px);
	box-shadow: 0 6px 20px rgba(0, 0, 0, .1);
}

/* 상품혜택 hover */
.package-info {
	display: flex;
	align-items: center;
	gap: 5px;
	color: #666;
	font-size: 14px;
	position: relative;
}

.hover-detail {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	background: rgba(126, 87, 194, 0.9);
	color: #fff;
	padding: 15px;
	font-size: 0.95rem;
	line-height: 1.4;
	opacity: 0;
	transform: translateY(100%);
	transition: all 0.3s ease;
	border-top-left-radius: 8px;
	border-top-right-radius: 8px;
}

.package-info:hover .hover-detail {
	opacity: 1;
	transform: translateY(0);
}
.search_wrap {
	width:260px;
}
.search_wrap select{
	width:180px;
}
.search_btn{
	width:calc(100% - 180px);
	
}
</style>
</head>
<body>
	<div class="container">
		<!-- 상단 타이틀 -->
		<div class="header">
			<h1>📦 등록된 상품 목록</h1>
			<p class="subtitle">대표 채용공고 지면에서 다수 구직자에게 효과적으로 공고를 홍보하는 상품</p>
		</div>

		<!-- 우측 정렬된 필터 + 등록 버튼 -->
		<div class="header-actions">
			<form class="d-flex search_wrap" method="get" action="">
				<select class="form-select me-2" name="filterType">
					<option value="" ${empty param.filterType ? "selected" : ""}>전체</option>
					<option value="BUSINESS"
						${param.filterType == 'BUSINESS' ? "selected" : ""}>Business</option>
					<option value="PREMIUM"
						${param.filterType == 'PREMIUM' ? "selected" : ""}>Premium</option>
				</select>
				<button type="submit" class="btn btn_violet search_btn">검색</button>
			</form>
			<a href="/admin/add" class="btn btn_violet">+ 상품 등록하기</a>
		</div>

		<!-- 상품 리스트 -->
		<div class="packages-section">
			<c:forEach var="product" items="${productList}">
				<div class="package-card" data-product-no="${product.productNo}"
					onclick="AdminDetail(this)">
					<div class="package-header">
						<h5>${product.productName}</h5>
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
							<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" />
						</p>
					</div>
					<div class="package-info">
						<span class="info-icon">🏢</span> <span>상품혜택</span>
						<div class="hover-detail">${product.productDetail}</div>
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
