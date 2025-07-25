<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역 List</title>
</head>
<body>
	<div>
		<!-- card형태의 list 로 놓고 클릭시 payment detail로 -->
		<c:forEach var="payment" items="${comPayList}">
			<p>결제번호: ${payment.paymentNo}</p>
		</c:forEach>

	</div>
</body>
</html>