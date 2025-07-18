<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>결제하기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
    
    <script>
    	const productNo = "${product.productNo }"
    </script>
</head>
<body class="container my-5"
      data-billing-key="${billingKey}"
   	 data-customer-key="${customerKey}" 
      data-amount="${product.productPrice}"
      data-order-name="${product.productName}">

    <div class="card shadow">
        <div class="card-body">
            <h3>상품 결제</h3>
            <hr>
            <p><strong>상품명:</strong> ${product.productName}</p>
            <p><strong>이용 기간:</strong> ${product.productPeriod}</p>
            <p><strong>결제 방식:</strong> 등록된 카드 자동결제</p>
            <p><strong>결제 금액:</strong> ₩<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>
            <button class="btn btn-success" onclick="doBilling()">결제하기</button>
        </div>
    </div>
    <script src="/js/company/payment/BuyProductBilling.js"></script>
</body>
</html>
