<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${productName} 구매 기업 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-5">
    <h2 class="mb-4">💼 '${productName}'을 구매한 기업 리스트</h2>

    <c:choose>
        <c:when test="${not empty customers}">
            <ul class="list-group">
                <c:forEach var="name" items="${customers}">
                    <li class="list-group-item">${name}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">구매한 기업이 없습니다.</div>
        </c:otherwise>
    </c:choose>

    <a href="/admin/select/product" class="btn btn-secondary mt-4">← 대시보드로 돌아가기</a>
</body>
</html>