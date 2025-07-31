<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제 상품 관리</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
      background-color: #f8f9fa;
    }
    /* 좌측 보고서 */
    .report-container {
      background: #fff;
      border-radius: 12px;
      border: 2px solid #d1c4e9;
      box-shadow: 0 4px 15px rgba(0,0,0,0.05);
      padding: 30px;
    }
    .report-header {
      border-bottom: 2px solid #d1c4e9;
      padding-bottom: 15px;
      margin-bottom: 20px;
    }
    .report-header h2 { color: #5e3fa3; font-weight: bold; }
    .report-row { display: flex; padding: 12px 0; border-bottom: 1px solid #eee; }
    .report-label { font-weight: bold; color: #6f42c1; min-width: 150px; }
    .report-value { flex: 1; }
    .btn-purple { background-color: #b39ddb; color: white; border: none; font-weight: bold; }
    .btn-purple:hover { background-color: #9c89c7; }
    /* 우측 상품카드 */
    .package-card {
      background: white;
      border-radius: 8px;
      padding: 20px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      border: 1px solid #7e57c2;
      cursor: pointer;
      transition: transform .2s ease, box-shadow .2s ease;
      margin-bottom: 20px;
    }
    .package-card:hover { transform: translateY(-4px); box-shadow: 0 6px 20px rgba(0,0,0,.1); }
    .package-header h5 { font-size: 1.3rem; font-weight: bold; color: #212529; }
    .btn-violet { background-color: #8e44ad; color: white; border: none; }
    .btn-violet:hover { background-color: #732d91; }
  </style>
</head>
<body>
<div class="container-xxl flex-grow-1 container-p-y my-5">
  <div class="d-flex gap-4">
    <!-- 좌측: 현재 상품 -->
    <div class="flex-fill report-container">
      <div class="d-flex justify-content-between align-items-center report-header">
        <h2>🧾 나의 결제 상품</h2>
        <a href="/company/payment/product/list" class="btn btn-purple">상품 목록 보기</a>
      </div>
      <c:if test="${empty ppvo}">
        <div class="text-center text-muted py-5">활성화된 상품이 없습니다.</div>
      </c:if>
      <c:if test="${not empty ppvo}">
        <div class="report-row"><div class="report-label">상품명</div>
          <div class="report-value">
            <c:forEach var="prod" items="${ppvo.paymentProductList}">
              <a href="/company/payment/buydetail?paymentNo=${ppvo.paymentNo}&productNo=${prod.productNo}" class="text-decoration-none text-dark">${prod.productName}</a><br/>
            </c:forEach>
          </div>
        </div>
        <div class="report-row"><div class="report-label">결제금액</div><div class="report-value"><fmt:formatNumber value="${ppvo.paymentPay}" type="currency" currencySymbol="₩"/></div></div>
        <div class="report-row"><div class="report-label">결제일시</div><div class="report-value">${ppvo.paymentDate}</div></div>
        <div class="report-row"><div class="report-label">남은기간</div>
          <div class="report-value"><c:choose><c:when test="${ppvo.daysRemaining >= 0}">${ppvo.daysRemaining}일 남음</c:when><c:otherwise>기간 만료</c:otherwise></c:choose></div>
        </div>
        <div class="report-row"><div class="report-label">결제수단</div><div class="report-value">${ppvo.paymentMethod}</div></div>
        <div class="report-row"><div class="report-label">잔여메일발송횟수</div>
          <div class="report-value"><c:choose><c:when test="${ppvo.usageRemaining == -1}">무제한 이용 가능</c:when><c:otherwise>${ppvo.usageRemaining}회 남음</c:otherwise></c:choose></div>
        </div>
        <div class="report-row"><div class="report-label">메일발송초기화일</div><div class="report-value">${ppvo.endDate}</div></div>
        <div class="report-row"><div class="report-label">다음달 결제 예정상품</div><div class="report-value">${ppvo.nextMonth}</div></div>
      </c:if>
    </div>

    <!-- 우측: 변경 가능한 상품 목록 -->
    <div class="flex-fill">
      <h3 class="mb-4">변경 가능한 상품</h3>
      <c:forEach var="product" items="${changeableProducts}">
        <div class="package-card">
          <div class="package-header">
            <h5>${product.productName}</h5>
          </div>
          <p>${product.productDetail}</p>
          <p><strong>가격:</strong> ₩<fmt:formatNumber value="${product.productPrice}" pattern="#,##0"/> / 월</p>
          <form method="post" action="/company/payment/change">
            <input type="hidden" name="oldPaymentNo" value="${ppvo.paymentNo}">
            <input type="hidden" name="newProductNo" value="${product.productNo}">
            <input type="hidden" name="billingKey" value="${ppvo.paymentBillingKey}">
            <button class="btn btn-violet w-100 mt-3">이 요금제로 변경하기</button>
          </form>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
