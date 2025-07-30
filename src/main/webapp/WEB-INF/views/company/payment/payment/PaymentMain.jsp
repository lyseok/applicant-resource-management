<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>나의 결제 상품</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
      background-color: #f8f9fa;
      color: #333;
    }
    .report-container {
      max-width: 900px;
      margin: 0 auto;
      padding: 30px;
      background: #fff;
      border-radius: 12px;
      border: 2px solid #d1c4e9;
      box-shadow: 0 4px 15px rgba(0,0,0,0.05);
    }
    .report-header {
      border-bottom: 2px solid #d1c4e9;
      padding-bottom: 15px;
      margin-bottom: 20px;
    }
    .report-header h2 {
      font-weight: bold;
      color: #5e3fa3;
    }
    .report-section {
      margin-bottom: 20px;
    }
    .report-label {
      font-weight: bold;
      color: #6f42c1;
      min-width: 150px;
    }
    .report-value {
      flex: 1;
      color: #333;
    }
    .report-row {
      display: flex;
      padding: 12px 0;
      border-bottom: 1px solid #eee;
    }
    .report-row:last-child {
      border-bottom: none;
    }
    .btn-purple {
      background-color: #b39ddb;
      color: white;
      border: none;
      font-weight: bold;
    }
    .btn-purple:hover {
      background-color: #9c89c7;
    }
    .no-product {
      text-align: center;
      padding: 50px 20px;
      color: #777;
      font-size: 1.1rem;
    }
  </style>
</head>
<body>
<div class="container my-5">
  <!-- 보고서 스타일 카드 -->
  <div class="report-container">
    <div class="d-flex justify-content-between align-items-center report-header">
      <h2>🧾 나의 결제 상품</h2>
      <a href="/company/payment/product/list" class="btn btn-purple">상품 목록 보기</a>
    </div>

    <!-- 상품 없을 경우 -->
    <c:if test="${empty ppvo}">
      <div class="no-product">활성화된 상품이 없습니다.</div>
    </c:if>

    <!-- 상품 있을 경우 -->
    <c:if test="${not empty ppvo}">
      <div class="report-section">
        <div class="report-row">
          <div class="report-label">상품명</div>
          <div class="report-value">
            <c:forEach var="prod" items="${ppvo.paymentProductList}">
              <a href="/company/payment/buydetail?paymentNo=${ppvo.paymentNo}&productNo=${prod.productNo}" class="text-decoration-none text-dark">
                ${prod.productName}
              </a><br/>
            </c:forEach>
          </div>
        </div>

        <div class="report-row">
          <div class="report-label">결제금액</div>
          <div class="report-value"><fmt:formatNumber value="${ppvo.paymentPay}" type="currency" currencySymbol="₩"/></div>
        </div>

        <div class="report-row">
          <div class="report-label">결제일시</div>
          <div class="report-value">${ppvo.paymentDate}</div>
        </div>

        <div class="report-row">
          <div class="report-label">남은기간</div>
          <div class="report-value">
            <c:choose>
              <c:when test="${ppvo.daysRemaining >= 0}">${ppvo.daysRemaining}일 남음</c:when>
              <c:otherwise>기간 만료</c:otherwise>
            </c:choose>
          </div>
        </div>

        <div class="report-row">
          <div class="report-label">결제수단</div>
          <div class="report-value">${ppvo.paymentMethod}</div>
        </div>

        <div class="report-row">
          <div class="report-label">잔여메일발송횟수</div>
          <div class="report-value">
            <c:choose>
              <c:when test="${ppvo.usageRemaining == -1}">무제한 이용 가능</c:when>
              <c:otherwise>${ppvo.usageRemaining}회 남음</c:otherwise>
            </c:choose>
          </div>
        </div>

        <div class="report-row">
          <div class="report-label">메일발송초기화일</div>
          <div class="report-value">${ppvo.endDate}</div>
        </div>
      </div>
    </c:if>
     <div class="report-row">
          <div class="report-label">다음달 결제 예정상품</div>
          <div class="report-value">${ppvo.nextMonth}</div>
        </div>
<!--     <a href="/company/payment/product/list" class="btn btn-purple">취소하기</a>
 -->  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
