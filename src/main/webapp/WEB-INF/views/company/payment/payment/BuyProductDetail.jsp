<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 상세 정보</title>
</head>
<body>
<h2>결제 상세 정보</h2>

<c:forEach var="product" items="${payment.paymentProductList}">
    <p>상품명: ${product.productName}</p>
    <p>상품내용 : ${product.productDetail}</p>
    <p></p>
    <p>남은기간: ${product.daysRemaining}일</p>

<p>결제일시: ${payment.paymentDate}</p>
<p>결제수단: ${payment.paymentMethod}</p>
<p>결제금액: ${payment.paymentPay}원</p>
<a href="/company/payment/main"><button>상품이용페이지로 이동하기</button></a>
<a href="/company/payment/change/product?productNo=${product.productNo}&paymentNo=${payment.paymentNo}"><button>요금제변경하기</button></a>
</c:forEach>
된다면 여기에 PDF 출력 버튼도 해보자
</body>
</html>
