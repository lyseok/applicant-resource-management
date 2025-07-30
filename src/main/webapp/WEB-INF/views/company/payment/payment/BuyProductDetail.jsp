<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 상세 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Noto Sans KR', sans-serif;
    }
    .container {
        max-width: 800px;
        margin: 40px auto;
    }
    .card {
        border-radius: 10px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        padding: 25px;
        background: white;
    }
    .card h2 {
        color: #6a1b9a;
        font-weight: bold;
        margin-bottom: 25px;
    }
    .product-info {
        border-bottom: 1px solid #eee;
        padding-bottom: 15px;
        margin-bottom: 15px;
    }
    .btn-purple {
        background-color: #7e57c2;
        color: white;
        border-radius: 5px;
    }
    .btn-purple:hover {
        background-color: #6a1b9a;
    }
    .btn-outline-purple {
        border: 1px solid #7e57c2;
        color: #7e57c2;
        border-radius: 5px;
    }
    .btn-outline-purple:hover {
        background-color: #7e57c2;
        color: white;
    }
</style>
</head>
<body>
<div class="container">
    <div class="card">
        <h2>💳 결제 상세 정보</h2>

        <c:forEach var="product" items="${payment.paymentProductList}">
            <div class="product-info">
                <h5 class="fw-bold">${product.productName}</h5>
                <p class="text-muted">${product.productDetail}</p>
                <p><strong>남은기간:</strong> ${product.daysRemaining}일</p>
            </div>
        </c:forEach>

        <p><strong>결제일시:</strong> ${payment.paymentDate}</p>
        <p><strong>결제수단:</strong> ${payment.paymentMethod}</p>
        <p><strong>결제금액:</strong> <span class="text-primary fw-bold">${payment.paymentPay}원</span></p>

        <div class="d-flex gap-2 mt-4">
            <a href="/company/payment/main" class="btn btn-purple w-50">상품이용 페이지</a>
            <c:forEach var="product" items="${payment.paymentProductList}">
                <a href="/company/payment/change/product?productNo=${product.productNo}&paymentNo=${payment.paymentNo}" 
                   class="btn btn-outline-purple w-50">요금제 변경</a>
            </c:forEach>
        </div>

        <form action="/company/payment/cancel/subscription" method="post" class="mt-3">
            <input type="hidden" name="paymentNo" value="${payment.paymentNo}">
            <button type="submit" class="btn btn-danger w-100">정기결제 해지</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
