<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 이 부분을 추가해야 합니다. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-5 text-center">

<div>${paymentResult }</div>
    <div class="card p-5 shadow-sm">
        <h1 class="card-title text-success mb-4">결제 성공!</h1>
        
        <div class="mb-3">
            <h4 class="text-muted">상품 정보</h4>
            <ul class="list-unstyled"> 
                <li><strong>상품명:</strong> ${orderName}</li>
                <li><strong>결제 금액:</strong> <fmt:formatNumber value="${RtotalAmount}"  pattern="#,##0" />원</li>
                <li><strong>결제 방식:</strong> ${method}</li>
            </ul>
        </div>
        
        <a href="/company/payment/main" class="btn btn-primary mt-4">메인 페이지로 돌아가기</a>
    </div>

</body>
</html>