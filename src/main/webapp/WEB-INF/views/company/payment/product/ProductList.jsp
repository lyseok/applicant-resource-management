<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://js.tosspayments.com/v1"></script>
<script src="/js/company/payment/TossPayment.js"></script>
  <meta charset="UTF-8">
  <title>결제할 상품을 선택하세요</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    .product-list {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .product {
      position: relative; /* 오버레이를 위한 기준 */
      border: 1px solid #ccc;
      padding: 20px;
      border-radius: 8px;
      background-color: #f9f9f9;
      cursor: pointer;
    }

    .product-title {
      font-size: 1.5rem;
      font-weight: bold;
      margin-bottom: 10px;
    }

    .product-content {
      font-size: 1rem;
      color: #333;
    }

    /* Global 등급 */
    .product.global {
      background-color: #e0f7fa;
      border-color: #00acc1;
    }
    /* Platinum 등급 */
    .product.platinum {
      background-color: #f3e5f5;
      border-color: #8e24aa;
    }

    /* 오버레이 공통 스타일 */
    .product .overlay {
      display: none;
      position: fixed 0;  /* 화면 전체를 덮기 위해 fixed */
      top: 0; left: 0;
      width: 100vw;
      height: 100vh;
      background: rgba(0,0,0,0.7);
      color: white;
      z-index: 1000;
      padding: 40px;
      box-sizing: border-box;
    }

    /* hover 시 오버레이 보이기 */
    .product.global:hover .overlay-global,
    .product.platinum:hover .overlay-platinum {
      display: block;
    }
  </style>
</head>
<body>
  <h1>여기는 결제리스트 페이지</h1>
  <div class="product-list">
    <div class="product global">
      <div class="product-title">Global 등급</div>
      <div class="product-content">Global 등급 내용</div>
      <div class="overlay overlay-global">
        <h2>Global 등급 상세 정보</h2>
        <p>	Product 제목 : Grobal 기업에 어올리는 회사에게
	Product 내용 : 
	Grobal  특권 : 화상회의 가능,
				  채용공고 맨위에 노출,
				  공고 검색시 맨위에 노출,
				  회원 등급 표시기능(filter에서 걸리는 조건에 맞춰 등급표시)
				  이직제안 무제한</p>
      </div>
    </div>

    <div class="product platinum">
      <div class="product-title">Platinum 등급</div>
      <div class="product-content">Platinum 등급 내용</div>
      <div class="overlay overlay-platinum">
        <h2>Platinum 등급 상세 정보</h2>
        <p>여기에 Platinum 등급을 선택했을 때 보여줄 전용 화면을 넣으세요.</p>
      </div>
    </div>
  </div>
</body>
</html>
