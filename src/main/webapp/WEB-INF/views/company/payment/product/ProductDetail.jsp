<%@page import="jakarta.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productName} 상세보기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://js.tosspayments.com/v1"></script>

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
    window.PRODUCT_NO = "${product.productNo}";
    const BillingKey = "${sessionScope.billingKey}";
    const Amount = "${product.productPrice}";
    const orderName = "${product.productName}";
</script>
</head>
<body class="container my-5" data-billing-key="${param.billingKey}" data-product-no="${product.productNo}">

    <div class="product-detail-container">
        <!-- 이미지 -->
        <img src="${product.productImg}" alt="상품 이미지">

        <!-- 제목 -->
        <h3>${product.productName}</h3>

        <!-- 설명 -->
        <p>${product.productDetail}</p>

        <!-- 가격 -->
        <p class="price-text">₩ <fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>

        <!-- 유형 -->
        <p>
            <strong>유형:</strong>
            <c:choose>
                <c:when test="${product.productType == 'L'}">정기권</c:when>
                <c:when test="${product.productType == 'S'}">단건</c:when>
                <c:otherwise>${product.productType}</c:otherwise>
            </c:choose>
        </p>

        <!-- 이용기간 -->
        <p><strong>이용기간:</strong> 구매일로 부터 1달</p>

    
        <!-- 버튼 -->
        <button class="btn btn-primary w-100 mb-2" onclick="subscribe(event, this)" id="sub-btn"
                data-product-no="${product.productNo}" data-billing-key="${payment.paymentBillingKey}">
            구독하기
        </button>
        <button class="btn btn-secondary w-100" onclick="history.back()">뒤로가기</button>
    </div>

    <!-- 카드 등록 필요 모달 -->
    <div class="modal fade" id="noCardModal" tabindex="-1" aria-labelledby="noCardModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="noCardModalLabel">카드 등록 필요</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
                </div>
                <div class="modal-body">
                    등록된 결제 카드가 없습니다.<br> 먼저 카드를 등록해주세요.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button id="card-btn" data-product-no="${product.productNo}" onclick="requestBillingAuth(event, this)">카드 등록하기</button>
                </div>
            </div>
        </div>
    </div>

    <script src="/js/company/payment/ProductDetail.js"></script>
    <script src="/js/company/payment/TossBillingCard.js"></script>
</body>
</html>
