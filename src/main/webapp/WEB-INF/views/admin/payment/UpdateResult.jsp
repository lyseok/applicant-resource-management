<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>상품 수정 결과</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="alert alert-success text-center">
        <h4 class="mb-3">${message}</h4>
        <p><strong>상품명:</strong> ${product.productName}</p>
        <p><strong>설명:</strong> ${product.productDetail}</p>
        <p><strong>가격:</strong> ₩${product.productPrice}</p>
        <p><strong>유형:</strong> ${product.productType}</p>
        <p><strong>기간:</strong> ${product.productPeriod}</p>

        <c:if test="${not empty product.productImg}">
            <img src="/upload/${product.productImg}" class="img-thumbnail mt-2" style="max-width: 200px;">
        </c:if>

        <div class="mt-4">
            <a href="/admin/product/list" class="btn btn-primary">상품 목록으로</a>
            <a href="/admin/update/product?productNo=${product.productNo}" class="btn btn-secondary" >수정 다시하기</a>
        </div>
    </div>
</div>
</body>
</html>
