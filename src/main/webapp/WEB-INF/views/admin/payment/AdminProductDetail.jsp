<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productName}상세보기</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://js.tosspayments.com/v2/standard"></script>


<script>
	const ProductNo = "${product.productNo}";
	const BillingKey = "${param.billingKey}";
	const Amount = "${product.productPrice}";
	const CustomerKey = "h5hXSJ-WPK8sZQpXQUJUA";
</script>
</head>
<body class="container my-5" data-billing-key="${param.billingKey}"
	data-product-no="${product.productNo}">
	<div class="card shadow">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="/upload/${product.productImg}"
					class="img-fluid rounded-start" alt="상품 이미지" />
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h3 class="card-title">${product.productName}</h3>
					<p class="card-text">${product.productDetail}</p>
					<p>
						<strong>가격:</strong> ₩
						<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" />
					</p>
					<p>
						<strong>유형:</strong>
						<c:choose>
							<c:when test="${product.productType == 'Premium'}">Premium</c:when>
							<c:when test="${product.productType == 'Business'}">Business</c:when>
							<c:otherwise>${product.productType}</c:otherwise>
						</c:choose>
					</p>
					<p>
						<strong>이용기간:</strong> ${product.productPeriod}
					</p>
					<p>productNo: ${product.productNo}</p>

					<a href="/admin/update/product?productNo=${product.productNo}">
					<button>수정</button></a>
					<button id="deleteBtn" data-product-no="${product.productNo}" onclick="deleteProduct()">삭제</button>
				</div>
				<button onclick="history.back()">뒤로가기</button>
			</div>
		</div>
	</div>
	<!-- 카드 등록 필요 모달 -->
<script src="/js/admin/payment/AdminProduct.js"></script>
</body>
</html>