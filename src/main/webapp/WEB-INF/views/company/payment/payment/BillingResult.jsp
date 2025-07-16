<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>Billing 등록 결과</h2>

    <c:if test="${not empty billingKey}">
        <p><strong>Billing Key:</strong> ${billingKey}</p>
        <pre>${result}</pre>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color:red;"><strong>오류:</strong> ${error}</p>
    </c:if>
</body>
</html>