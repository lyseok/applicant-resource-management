<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>결제할 상품을 선택하세요</title>
  	<style>
  	body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

/* 제품 목록 컨테이너 */
.product-list {
    display: flex;
    flex-direction: column; /* 수직 방향으로 정렬 */
    gap: 20px; /* 각 제품 간 간격 */
}

/* 공통된 제품 스타일 */
.product {
    border: 1px solid #ccc; /* 경계선 */
    padding: 20px;
    border-radius: 8px; /* 둥근 모서리 */
    background-color: #f9f9f9;
}

/* 각 제품 제목 */
.product-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 10px; /* 제목과 내용 사이 간격 */
}

/* 각 제품 내용 */
.product-content {
    font-size: 1rem;
    color: #333;
}

/* Global 제품 스타일 */
.product.global {
    background-color: #e0f7fa; /* Global 등급을 위한 배경 색 */
    border-color: #00acc1; /* Global 등급을 위한 경계선 색 */
}

/* Premium 제품 스타일 */
.product.premium {
    background-color: #f3e5f5; /* Premium 등급을 위한 배경 색 */
    border-color: #8e24aa; /* Premium 등급을 위한 경계선 색 */
}
  	</style>
</head>
<body>
    <h1>여기는 결제리스트 페이지</h1>
    <div class="product-list">
        <div class="product global">
            <div class="product-title">Global 등급</div>
            <div class="product-content">Global 등급 내용</div>
        </div>
        <div class="product premium">
            <div class="product-title">Premium 등급</div>
            <div class="product-content">Premium 내용</div>
        </div>
    </div>
</body>
</html>
