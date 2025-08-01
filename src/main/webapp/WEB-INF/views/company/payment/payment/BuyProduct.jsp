<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 결제</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Noto Sans KR', sans-serif;
    }
    .product-detail-container {
        width: 100%;
        max-width: 1140px; /* Bootstrap container 기준 */
        margin: 40px auto;
        border: 2px solid #7e57c2;
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
    .product-detail-container h3 {
        font-weight: bold;
        margin-bottom: 25px;
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
        margin-bottom: 25px;
    }
    .btn-purple {
        background-color: #7e57c6;
        border-color: #7e57c6;
    }
    .btn-purple:hover {
        background-color: #5e3fa3;
        border-color: #5e3fa3;
    }
</style>
</head>
<body class="container my-5"
      data-product-no="${product.productNo}"
      data-billing-key="${sessionScope.billingKey}"
      data-customer-key="${customerKey}"
      data-amount="${product.productPrice}"
      data-order-name="${product.productName}">

    <div class="product-detail-container">
        <h3>💳 상품 자동 결제</h3>
        <hr>
        <p><strong>상품명:</strong> ${product.productName}</p>
        <p><strong>이용 기간:</strong> 구매일로 부터 30일</p>
        <p><strong>결제 방식:</strong> 등록된 카드 자동결제</p>
        <p class="price-text">₩ <fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>

        <div class="d-grid mt-4">
            <button class="btn btn-purple btn-lg" onclick="doBilling()">✅ 결제하기</button>
        </div>
    </div>

    <script>
        console.log("customerKey from server : '${customerKey}'");
        const PproductNo = "${product.productNo}";
        const PbillingKey = "${sessionScope.billingKey}";
        const PcustomerKey = "${sessionScope.customerKey}";
        const Pamount = "${product.productPrice}";
        const PproductName = "${product.productName}";
    </script>

    <script src="/js/company/payment/BuyProductBilling.js"></script>
</body>
</html>
