<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>상품 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
            color: #333;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 30px;
        }
        h3.text-center {
            font-weight: bold;
            color: #5e3fa3;
        }
        /* 카드 스타일 */
        .card {
            border: 2px solid #b39ddb;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(179, 157, 219, 0.25);
            transition: box-shadow 0.3s ease;
        }
        .card:hover {
            box-shadow: 0 6px 18px rgba(179, 157, 219, 0.35);
        }
        .card-header {
            background: #b39ddb;
            color: #fff;
            font-weight: bold;
            font-size: 1.2rem;
            border-radius: 10px 10px 0 0;
        }
        /* 버튼 스타일 */
        .btn-primary,
        .btn-success {
            background-color: #b39ddb;
            border: none;
            font-weight: bold;
        }
        .btn-primary:hover,
        .btn-success:hover {
            background-color: #9c89c7;
        }
        .btn-secondary {
            background-color: #e0e0e0;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #cfcfcf;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h3 class="text-center mb-4">상품 정보 수정</h3>

    <div class="card">
        <div class="card-header">
            <h4 class="mb-0">상품 수정</h4>
        </div>
        <div class="card-body">
            <form action="/admin/update/done" method="post" enctype="multipart/form-data">
                <!-- 상품 ID (필수) -->
                <input type="hidden" name="productNo" value="${product.productNo}">
                <input type="hidden" name="productType" value="${product.productType}">

                <div class="mb-3">
                    <label for="productName" class="form-label">상품명</label>
                    <input type="text" class="form-control" id="productName" name="productName"
                           placeholder="상품명을 입력하세요" value="${product.productName}" required>
                </div>

                <div class="mb-3">
                    <label for="productDetail" class="form-label">상품 설명</label>
                    <textarea class="form-control" id="productDetail" name="productDetail" rows="3"
                              placeholder="설명을 입력하세요">${product.productDetail}</textarea>
                </div>

                <div class="mb-3">
                    <label for="productPrice" class="form-label">가격</label>
                    <input type="text" class="form-control" id="productPrice" name="productPrice"
                           placeholder="가격 입력" value="${product.productPrice}" required>
                </div>

                <div class="mb-3">
                    <label for="productImgFile" class="form-label">상품 이미지</label>
                    <input type="file" class="form-control" id="productImgFile" name="productImgFile">
                    <c:if test="${not empty product.productImg}">
                        <small class="form-text text-muted">기존 이미지: ${product.productImg}</small><br/>
                        <img src="/upload/${product.productImg}" class="img-thumbnail mt-2" style="max-width: 150px;">
                    </c:if>
                </div>

                <button type="reset" class="btn btn-secondary w-100 mb-2">초기화</button>
                <button type="submit" class="btn btn-primary w-100">수정하기</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
