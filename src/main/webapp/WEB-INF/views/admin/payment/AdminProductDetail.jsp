<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productName} 상세보기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://js.tosspayments.com/v2/standard"></script>

<script>
	const ProductNo = "${product.productNo}";
	const BillingKey = "${param.billingKey}";
	const Amount = "${product.productPrice}";
	const CustomerKey = "h5hXSJ-WPK8sZQpXQUJUA";
</script>
</head>
<body class="container my-5" data-billing-key="${param.billingKey}" data-product-no="${product.productNo}">
	<div class="card shadow">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="${product.productImg}" alt="상품 이미지" class="rounded" style="width:100%; object-fit:cover;">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h3 class="card-title">${product.productName}</h3>
					<p class="card-text">${product.productDetail}</p>

					<p><strong>가격:</strong> ₩ <fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>
					<p>
						<strong>유형:</strong>
						<c:choose>
							<c:when test="${product.productType == 'Premium'}">Premium</c:when>
							<c:when test="${product.productType == 'Business'}">Business</c:when>
							<c:otherwise>${product.productType}</c:otherwise>
						</c:choose>
					</p>
					<p><strong>이용기간:</strong> ${product.productPeriod}</p>
					<p><strong>상품번호:</strong> ${product.productNo}</p>

					<!-- 버튼들 -->
					<div class="mt-3 d-flex gap-2">
						<a href="/admin/update/product?productNo=${product.productNo}" class="btn btn-primary">수정</a>
						<button id="deleteBtn" data-product-no="${product.productNo}" class="btn btn-danger" onclick="deleteProduct()">삭제</button>
						<button onclick="history.back()" class="btn btn-secondary">뒤로가기</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="/js/admin/payment/AdminProduct.js"></script>
</body>
</html>
