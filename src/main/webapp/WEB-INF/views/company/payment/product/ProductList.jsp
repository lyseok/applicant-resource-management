<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<script src="https://js.tosspayments.com/v1"></script>
<script src="/js/company/payment/TossPayment.js"></script>
<style>
.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}


.subtitle {
	color: #666;
	font-size: 14px;
}

/* 상단 액션 */
.header-actions {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-bottom: 25px;
}

/* 카드 리스트 */
.packages-section {
	display: flex;
	gap: 20px;
}

.package-card {
	background: white;
	border-radius: 8px;
	width: 100%;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border: 1px solid var(--gray60);
	transition: transform .2s ease, box-shadow .2s ease;
	position: relative;
}


.package-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 5px;
}

.package-header h5 {
	font-size: 1.3rem;
	font-weight: bold;
	color: #212529;
}

.package-tags {
	margin-bottom: 15px;
}

.package-tags p {
	font-size: 1rem;
	font-weight: 500;
}

/* 가격 영역 */
.package-pricing {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: 10px;
}

.package-pricing strong {
	margin-right: 5px;
}

.btn-violet {
	background-color: #8e44ad;
	color: white;
	border: none;
}

.btn-violet:hover {
	background-color: #732d91;
}

/* 상품혜택 hover */
.package-info {
	display: flex;
	align-items: center;
	gap: 5px;
	color: #666;
	font-size: 14px;
	position: relative;
	cursor:pointer;
}

.hover-detail {
	position: absolute;
	top: 30px;
	left: 0;
	width: 250px;
	background: rgba(126, 87, 194, 0.95);
	color: #fff;
	padding: 10px;
	font-size: 0.9rem;
	line-height: 1.4;
	border-radius: 8px;
	opacity: 0;
	transform: translateY(10px);
	transition: all 0.3s ease;
	z-index: 10;
	white-space: normal;
	max-height:0;
	z-index:-1;
}

.hover-detail::after {
	content: '';
	position: absolute;
	bottom: -8px;
	left: 20px;
	border-width: 8px;
	border-style: solid;
	border-color: rgba(126, 87, 194, 0.95) transparent transparent
		transparent;
}

.package-info:hover .hover-detail {
	opacity: 1;
	transform: translateY(0);
	max-height:500px;
	z-index:1;
}



/* 페이징 */
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
		<!-- 타이틀 -->
		<div class="d-flex justify-content-between mb-3 align-items-end">
			<div class="">
				<h1 class="h2 lh1 fw-bold">등록된 상품 목록</h1>
				<p class="subtitle">대표 채용공고 지면에서 다수 구직자에게 효과적으로 공고를 홍보하는 상품</p>
			</div>
			
			<!-- 필터 + 등록 버튼 -->
			<form class="d-flex" method="get" action="">
				<select class="form-select w140 h48" name="filterType" onchange="this.form.submit()">
					<option value="" ${empty param.filterType ? "selected" : ""}>전체</option>
					<option value="BUSINESS"
						${param.filterType == 'BUSINESS' ? "selected" : ""}>Business</option>
					<option value="PREMIUM"
						${param.filterType == 'PREMIUM' ? "selected" : ""}>Premium</option>
				</select>
			</form>
		</div>

		<!-- 상품 카드 리스트 -->
		<div class="packages-section">
			<c:forEach var="product" items="${productList}">
				<div class="package-card" data-product-no="${product.productNo}">
					<div class="package-header">
						<h5>${product.productName}</h5>
						
						<div class="package-info">
							<span class="material-symbols-outlined">info</span><span>상품혜택</span>
							<div class="hover-detail">${product.productDetail}</div>
						</div>
					</div>
					<div class="package-tags">
						<c:choose>
							<c:when test="${product.productType == 'PREMIUM'}">
								<p class="text-violet110">프리미엄의 품격이 어울리는 기업에게 추천합니다.</p>
							</c:when>
							<c:when test="${product.productType == 'BUSINESS'}">
								<p class="text-violet110">비즈니스 파트너를 찾으세요? 바로 여기 있습니다.</p>
							</c:when>
						</c:choose>
					</div>
					<div class="package-pricing">
						<p>
							<b><fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></b>원 / 월
						</p>
					</div>
					
					<button class="btn btn_violet mt-3" data-product-no="${product.productNo}" onclick="goToDetail(this)">상품 구매</button>
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
</body>