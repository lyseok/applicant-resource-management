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
    .section-header {
      margin-top: 2rem;
      margin-bottom: 1.5rem;
    }
    .card-highlight {
      background-color: #f8f9fa;
      border: 1px solid #dee2e6;
      border-radius: 0.5rem;
      padding: 1.5rem;
    }
    .btn-purple {
      background-color: #6f42c1;
      color: white;
    }
    .btn-purple:hover {
      background-color: #59359e;
    }
  </style>
</head>
<body>
<div class="container my-5">

  <!-- 제목 및 안내 영역 -->
  <div class="d-flex justify-content-between align-items-center section-header">
    <h2 class="fw-bold">🧾 나의 결제 상품</h2>
    <a href="/company/payment/product/list" class="btn btn-purple">상품 목록 보기</a>
  </div>

  <!-- 상품 없을 경우 -->
  <c:if test="${empty ppvo}">
    <div class="alert alert-info text-center">활성화된 상품이 없습니다.</div>
  </c:if>

  <!-- 상품 있을 경우 -->
  <c:if test="${not empty ppvo}">
    <div class="card card-highlight">
      <div class="table-responsive">
        <table class="table table-bordered text-center align-middle mb-0">
          <thead class="table-secondary">
            <tr>
              <th>상품명</th>
              <th>결제금액</th>
              <th>결제일시</th>
              <th>남은기간</th>
              <th>결제수단</th>
              <th>잔여메일발송횟수</th>
              <th>메일발송초기화일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <!-- 상품명 -->
              <td>
                <c:forEach var="prod" items="${ppvo.paymentProductList}">
                  <a href="/company/payment/buydetail?paymentNo=${ppvo.paymentNo}&productNo=${prod.productNo}">
                    ${prod.productName}
                  </a><br/>
                </c:forEach>
              </td>

              <!-- 결제 금액 -->
              <td><fmt:formatNumber value="${ppvo.paymentPay}" type="currency" currencySymbol="₩"/></td>

              <!-- 결제일시 -->
              <td>${ppvo.paymentDate}</td>

              <!-- 남은기간 -->
              <td>
                <c:choose>
                  <c:when test="${ppvo.daysRemaining >= 0}">
                    ${ppvo.daysRemaining}일 남음
                  </c:when>
                  <c:otherwise>기간 만료</c:otherwise>
                </c:choose>
              </td>

              <!-- 결제 수단 -->
              <td>${ppvo.paymentMethod}</td>

              <!-- 잔여 횟수 -->
              <td>
                <c:choose>
                  <c:when test="${ppvo.usageRemaining == -1}">
                    무제한 이용 가능
                  </c:when>
                  <c:otherwise>
                    ${ppvo.usageRemaining}회 남음
                  </c:otherwise>
                </c:choose>
              </td>

              <!-- 메일 발송 초기화일 -->
              <td>${ppvo.endDate}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
