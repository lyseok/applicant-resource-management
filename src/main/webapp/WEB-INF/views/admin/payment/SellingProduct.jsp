<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품별 매출 대시보드</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="row mb-4">
    <div class="col-md-3">
        <label for="yearSelect" class="form-label">비교할 연도 선택</label>
        <select id="yearSelect" class="form-select">
            <c:forEach var="y" begin="2020" end="2025">
                <option value="${y}" <c:if test="${y == currentYear}">selected</c:if>>${y}</option>
            </c:forEach>
        </select>
    </div>
</div>

<div class="container my-5">
    <h2 class="mb-4">📊 상품별 매출 현황</h2>

    <!-- 상단 차트 (좌/우 도넛) -->
    <div class="row mb-5">
        <div class="col-md-6">
            <h5 class="text-center">판매된 상품 비율</h5>
            <canvas id="productChart"></canvas>
        </div>
        <div class="col-md-6">
            <h5 class="text-center">구독상품 & 구독자 비율</h5>
            <canvas id="subscriptionChart"></canvas>
        </div>
    </div>

    <!-- 하단 Combo Chart -->
    <div class="mt-5">
        <h5 class="text-center">전년도 vs 이번년도 매출</h5>
        <canvas id="comboChart"></canvas>
    </div>

    <!-- 상품별 매출 상세 테이블 -->
  <div class="mt-5">
  <h4 class="fw-bold mb-4">📦 상품별 상세 매출 내역</h4>
  <div class="table-responsive">
    <table class="table table-bordered align-middle">
      <thead class="table-secondary text-center">
        <tr>
          <th>상품명</th>
          <th>상품타입</th>
          <th>당월 매출액</th>
          <th>총 매출액</th>
          <th>당월 판매수</th>
          <th>총 판매수</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="row" items="${getPaymentStatistics}">
          <tr>
            <td class="fw-semibold">${row.productName}</td>
            <td class="text-center">
              <c:choose>
                <c:when test="${row.productType == 'PREMIUM'}">PREMIUM</c:when>
                <c:when test="${row.productType == 'BUSINESS'}">BUSINESS</c:when>
                <c:otherwise>${row.productType}</c:otherwise>
              </c:choose>
            </td>
            <td class="text-end">
              <fmt:formatNumber value="${row.monthlySalesAmount}" type="currency" currencySymbol="₩" />
            </td>
            <td class="text-end">
              <fmt:formatNumber value="${row.totalSalesAmount}" type="currency" currencySymbol="₩" />
            </td>
            <td class="text-center">${row.monthlySalesCount}</td>
            <td class="text-center">${row.totalSalesCount}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- JS로 전달할 데이터 -->
<script>
    // 판매된 상품 비율 데이터
    const productLabels = [
        <c:forEach var="row" items="${getPaymentStatistics}" varStatus="st">
            "${row.productName}"<c:if test="${!st.last}">,</c:if>
        </c:forEach>
    ];
    const productData = [
        <c:forEach var="row" items="${getPaymentStatistics}" varStatus="st">
            ${row.totalSalesAmount}<c:if test="${!st.last}">,</c:if>
        </c:forEach>
    ];

    // 구독상품/구독자 비율 (예시: 컨트롤러에서 따로 값 넣어야 함)
    const subscriptionLabels = ["신규 구독자", "활성 구독자", "해지 구독자"];
    const subscriptionData = [${newSubs}, ${activeSubs}, ${churnedSubs}];

    // Combo Chart용 매출 데이터 (컨트롤러에서 준비)
    const lastYearSales = [<c:forEach var="d" items="${lastYearSales}" varStatus="st">${d}<c:if test="${!st.last}">,</c:if></c:forEach>];
    const thisYearSales = [<c:forEach var="d" items="${thisYearSales}" varStatus="st">${d}<c:if test="${!st.last}">,</c:if></c:forEach>];
</script>
<script src="/js/admin/payment/SellingProductDChart.js"></script>
</body>
</html>
