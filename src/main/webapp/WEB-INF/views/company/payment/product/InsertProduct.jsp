<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
     <title>상품 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .type-card {
            cursor: pointer;
            transition: transform 0.2s ease;
        }
        .type-card:hover {
            transform: scale(1.03);
        }
        .selected {
            border: 2px solid #0d6efd;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h3 class="text-center mb-4">상품 유형 선택</h3>
    <div class="row justify-content-center mb-5">
        <div class="col-md-4">
            <div class="card type-card" id="card-regular" onclick="selectType('정기권')">
                <div class="card-body text-center">
                    <h5 class="card-title">정기권</h5>
                    <p class="card-text">구매일로부터 1개월 이용 가능</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card type-card" id="card-single" onclick="selectType('단건')">
                <div class="card-body text-center">
                    <h5 class="card-title">단건</h5>
                    <p class="card-text">구매일로부터 1주일 이용 가능</p>
                </div>
            </div>
        </div>
    </div>

    <!-- 상품 입력 폼 -->
    <div class="card d-none" id="formCard">
        <div class="card-header bg-success text-white">
            <h4 class="mb-0">상품 등록<span id="selectedType" class="fw-normal"></span></h4>
        </div>
        <div class="card-body">
           <!--  <form action="insertProduct" method="post" enctype="multipart/form-data"> -->
            <form id="insertForm">
                <!-- 선택된 상품 유형도 hidden으로 전송 -->
                <input type="hidden" name="productType" id="productType">

                <div class="mb-3">
                    <label for="productName" class="form-label">상품명</label>
                    <input type="text" class="form-control" id="productName" name="productName" required>
                </div>
                <div class="mb-3">
                    <label for="productDetail" class="form-label">상품 설명</label>
                    <textarea class="form-control" id="productDetail" name="productDetail" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label for="productPrice" class="form-label">가격</label>
                    <input type="text" class="form-control" id="productPrice" name="productPrice" required>
                </div>
                <div class="mb-3">
                    <label for="productImg" class="form-label">상품 이미지</label>
                    <input type="file" class="form-control" id="productImg" name="productImg">
                </div>

                <button type="reset" class="btn btn-primary w-100">초기화</button>
                <button type="submit" class="btn btn-primary w-100">등록하기</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS (optional, for components like modal or toast if needed) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/company/payment/Addproduct.js"></script>
</body>
</html>
