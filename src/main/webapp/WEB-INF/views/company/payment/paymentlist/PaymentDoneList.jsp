<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제내역 List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    .page-link.active {
      background-color: #0d6efd;
      border-color: #0d6efd;
    }
  </style>
</head>
<body class="bg-light">
<div class="container py-5">

  <!-- 제목 -->
  <h2 class="mb-4 fw-bold">💳 내가 구매한 상품</h2>

  <!-- 필터 폼 -->
  <form method="get" class="row gy-2 gx-2 align-items-center mb-4">
    <div class="col-auto">
      <select name="filter" class="form-select">
        <option value="" ${empty selectedFilter ? 'selected' : ''}>전체 보기</option>
        <option value="A" ${selectedFilter == 'A' ? 'selected' : ''}>현재 사용 중</option>
        <option value="E" ${selectedFilter == 'E' ? 'selected' : ''}>사용기간 만료</option>
        <option value="S" ${selectedFilter == 'S' ? 'selected' : ''}>다음결제일부터 사용가능</option>
        <option value="C" ${selectedFilter == 'C' ? 'selected' : ''}>다음결제일부터 사용불가</option>
      </select>
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn_violet h50">필터 적용</button>
    </div>
  </form>

  <!-- 결제 내역 테이블 -->
  <c:choose>
    <c:when test="${empty purchaseList}">
      <div class="alert alert-info text-center">조건에 맞는 상품이 없습니다.</div>
    </c:when>
    <c:otherwise>
      <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle">
          <thead class="table-secondary text-center">
            <tr>
              <th>상품명</th>
              <th>결제금액</th>
              <th>사용기간</th>
              <th>결제수단</th>
              <th>현재상태</th>
              <th>순차 누적 사용액</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${purchaseList}" varStatus="status">
              <tr>
                <td>
                  <c:forEach var="product" items="${item.paymentProductList}">
                    <strong>${product.productName}</strong><br />
                    <small class="text-muted">
                      <c:choose>
                        <c:when test="${product.daysRemaining <= 0}">🔴 만료됨</c:when>
                        <c:otherwise>🟢 ${product.daysRemaining}일 남음</c:otherwise>
                      </c:choose>
                    </small><br />
                  </c:forEach>
                </td>
                <td class="text-end">
                  <fmt:formatNumber value="${item.paymentPay}" type="number" /> 원
                </td>
                <td class="text-center">${item.startDate} ~ ${item.endDate}</td>
                <td class="text-center">${item.paymentMethod}</td>
                <td class="text-center">
                  <c:choose>
                    <c:when test="${item.status == 'A'}">현재 사용 중</c:when>
                    <c:when test="${item.status == 'E'}">사용기간 만료</c:when>
                    <c:when test="${item.status == 'S'}">다음결제일부터 사용가능</c:when>
                    <c:when test="${item.status == 'C'}">다음결제일부터 사용불가</c:when>
                    <c:otherwise>알 수 없음</c:otherwise>
                  </c:choose>
                </td>
                <td class="text-end">
                  <fmt:formatNumber value="${cumulativeList[status.index]}" type="number" /> 원
                </td>
              </tr>
            </c:forEach>

            <!-- 총 누적 -->
            <tr class="table-light">
              <td colspan="5" class="text-end fw-bold">총 누적 사용액 :</td>
              <td class="text-end fw-bold">
                <fmt:formatNumber value="${totalUsedAmount}" type="number" /> 원
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>

  <!-- 페이지네이션 -->
  <c:if test="${not empty totalPages}">
    <nav aria-label="Page navigation" class="mt-4">
      <ul class="pagination justify-content-center">
        <c:if test="${currentPage > 1}">
          <li class="page-item">
            <a class="page-link" href="?page=${currentPage - 1}&filter=${selectedFilter}">이전</a>
          </li>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="i">
          <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="?page=${i}&filter=${selectedFilter}">${i}</a>
          </li>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
          <li class="page-item">
            <a class="page-link" href="?page=${currentPage + 1}&filter=${selectedFilter}">다음</a>
          </li>
        </c:if>
      </ul>
    </nav>
  </c:if>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
