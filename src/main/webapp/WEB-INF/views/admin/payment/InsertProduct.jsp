<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>상품 등록</title>
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
            color: #b39ddb;
        }

        /* 유형 카드 */
        .type-card {
            cursor: pointer;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            transition: transform 0.2s ease, box-shadow 0.2s ease, border 0.2s ease;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
        }
        .type-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 6px 15px rgba(126, 87, 194, 0.2);
            border: 2px solid #7e57c2;
        }
        .selected {
            border: 2px solid #7e57c2 !important;
            box-shadow: 0 4px 10px rgba(126, 87, 194, 0.3);
        }

        /* 등록 폼 */
        #formCard {
            border: 2px solid #b39ddb;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(126, 87, 194, 0.2);
            transition: all 0.3s ease;
        }
        #formCard .card-header {
            background: #b39ddb;
            color: #fff;
            font-weight: bold;
            font-size: 1.2rem;
        }

        #formCard .btn {
            background-color: #b39ddb;
            border: none;
            font-weight: bold;
            margin-top: 10px;
        }
        #formCard .btn:hover {
            background-color: #5e3fa3;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 class="text-center mb-4">상품 유형 선택</h3>
    <div class="row justify-content-center mb-5">
        <div class="col-md-4">
            <div class="card type-card" id="card-regular"  onclick="selectType('Premium')">
                <div class="card-body text-center">
                    <h5 class="card-title">Premium</h5>
                    <p class="card-text text-muted">무제한 사용 가능</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card type-card" id="card-single" onclick="selectType('Business')">
                <div class="card-body text-center">
                    <h5 class="card-title">Business</h5>
                    <p class="card-text text-muted">제한적 사용 가능</p>
                </div>
            </div>
        </div>
    </div>

    <!-- 상품 입력 폼 -->
    <div class="card d-none" id="formCard">
        <div class="card-header">
            <h4 class="mb-0">상품 등록 <span id="selectedType" class="fw-normal"></span></h4>
        </div>
        <div class="card-body">
            <form id="insertForm" enctype="multipart/form-data">
                <!-- 선택된 상품 유형 -->
                <input type="hidden" name="productType" id="productType">

                <!-- 상품명 -->
                <div class="mb-3">
                    <label for="productName" class="form-label">상품명</label>
                    <input type="text" class="form-control" id="productName" name="productName" required>
                </div>

                <!-- 상품 설명 -->
                <div class="mb-3">
                    <label for="productDetail" class="form-label">상품 설명</label>
                    <textarea class="form-control" id="productDetail" name="productDetail" rows="3"></textarea>
                </div>

                <!-- 가격 -->
                <div class="mb-3">
                    <label for="productPrice" class="form-label">가격</label>
                    <input type="text" class="form-control" id="productPrice" name="productPrice" required>
                </div>

                <!-- 상품 이미지 -->
                <div class="mb-3">
                    <label for="productImg" class="form-label">상품 이미지</label>
                     <input type="file" class="form-control" id="productImgFile" name="productImgFile">
                </div>

                <!-- 상품 한도 -->
                <div class="mb-3" id="productLimitDiv" style="display:none;">
                    <label for="productLimit" class="form-label">상품 한도</label>
                    <input type="number" class="form-control" id="productLimit" name="productLimit" min="1">
                </div>

                <!-- 상품 기간 -->
                <div class="mb-3" id="productPeriodDiv" style="display:none;">
                    <label for="productPeriod" class="form-label">상품 기간</label>
                    <input type="number" class="form-control" id="productPeriod" name="productPeriod" placeholder="기간을 입력해주세요">개월
                </div>

                <!-- 버튼 -->
                <button type="reset" class="btn btn-secondary w-100">초기화</button>
                <button type="submit" class="btn btn-primary w-100">등록하기</button>
            </form>
        </div>
    </div>
</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/company/payment/Addproduct.js"></script>
</body>
</html>
