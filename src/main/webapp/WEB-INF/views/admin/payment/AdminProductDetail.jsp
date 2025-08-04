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
<style>
    body {
        background-color: #f8f9fa;
    }
    .product-detail-container {
        max-width: 900px;
        margin: 40px auto;
        border: 2px solid #7e57c2; /* 보라색 테두리 */
        border-radius: 12px;
        padding: 40px;
        background: #fff;
        box-shadow: 0 4px 12px rgba(126, 87, 194, 0.2);
        transition: box-shadow 0.3s ease, transform 0.3s ease;
    }
    .product-detail-container:hover {
        box-shadow: 0 6px 18px rgba(126, 87, 194, 0.35);
        transform: translateY(-3px);
    }
    .product-detail-container img {
        width: 100%;
        max-height: 500px;
        object-fit: cover;
        border-radius: 8px;
        margin-bottom: 25px;
        border: 1px solid #ddd;
    }
    .product-detail-container h3 {
        font-weight: bold;
        margin-bottom: 20px;
        font-size: 2rem;
    }
    .product-detail-container p {
        font-size: 1.2rem;
        margin-bottom: 15px;
    }
    .price-text {
        font-size: 1.5rem;
        font-weight: bold;
        color: #6a1b9a;
        margin-bottom: 20px;
    }
    .btn-primary {
        background-color: #7e57c2;
        border-color: #7e57c2;
    }
    .btn-primary:hover {
        background-color: #5e3fa3;
        border-color: #5e3fa3;
    }
</style>
<script>
    const ProductNo = "${product.productNo}";
    const BillingKey = "${param.billingKey}";
    const Amount = "${product.productPrice}";
    const CustomerKey = "h5hXSJ-WPK8sZQpXQUJUA";
</script>
</head>
<body class="container my-5" data-billing-key="${param.billingKey}" data-product-no="${product.productNo}">
    <div class="card product-detail-container">
        <!-- 이미지 -->
        표시될 이미지 : <br/>
        <img src="${product.productImg}" alt="상품 이미지">

        <!-- 제목 -->
        <h3>제목 : ${product.productName}</h3>

        <!-- 설명 -->
        <p>설명 : ${product.productDetail}</p>

        <!-- 가격 -->
        <p class="price-text">₩ <fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>

        <!-- 유형 -->
        <p>
            <strong>유형:</strong>
            <c:choose>
                <c:when test="${product.productType == 'Premium'}">Premium</c:when>
                <c:when test="${product.productType == 'Business'}">Business</c:when>
                <c:otherwise>${product.productType}</c:otherwise>
            </c:choose>
        </p>

        <!-- 이용기간 -->
        <p><strong>이용기간:</strong> ${product.productPeriod}</p>

        <!-- 상품번호 -->
        <p><strong>상품번호:</strong> ${product.productNo}</p>

        <!-- 버튼들 -->
        <div class="mt-4 d-flex gap-2">
            <a href="/admin/update/product?productNo=${product.productNo}" class="btn btn-primary">수정</a>
            <button id="deleteBtn" data-product-no="${product.productNo}" class="btn btn-danger" onclick="deleteProduct()">삭제</button>
            <button onclick="history.back()" class="btn btn-secondary">뒤로가기</button>
        </div>
    </div>

    <!-- JS -->
    <script src="/js/admin/payment/AdminProduct.js"></script>
</body>
</html>
