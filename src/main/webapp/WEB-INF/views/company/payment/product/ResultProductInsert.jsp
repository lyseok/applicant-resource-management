<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제완료</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="alert alert-success text-center" role="alert">
        <h4 class="alert-heading">🎉 상품 등록 완료!</h4>
        <p>아래는 등록된 상품 정보입니다.</p>
    </div>

    <table class="table table-bordered">
        <tr>
            <th>상품명</th>
            <td>${productVO.productName}</td>
        </tr>
        <tr>
            <th>상품 설명</th>
            <td>${productVO.productDetail}</td>
        </tr>
        <tr>
            <th>상품 가격</th>
            <td>${productVO.productPrice}</td>
        </tr>
        <tr>
            <th>이용기간</th>
            <td>구매일로 부터 1달</td>
        </tr>
        <tr>
            <th>유형</th>
            <td>
                <c:choose>
                    <c:when test="${productVO.productType eq 'L'}">정기권</c:when>
                    <c:when test="${productVO.productType eq 'S'}">단건</c:when>
                    <c:otherwise>기타</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th>상품 이미지</th>
            <td>
                <c:if test="${not empty productVO.productImg}">
                    <img src="/upload/${productVO.productImg}" class="img-thumbnail" style="max-width: 300px;">
                </c:if>
                <c:if test="${empty productVO.productImg}">
                    이미지 없음
                </c:if>
            </td>
        </tr>
    </table>

    <div class="text-center mt-4">
        <a href="/admin/add" class="btn btn-primary">다시 등록하기</a>
        <a href="/admin/product/list" class="btn btn-secondary">목록으로</a>
    </div>
</div>
</body>
</html>