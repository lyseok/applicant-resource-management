<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제 로그</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">

  <!-- 제목 -->
  <h1 class="h3 mb-4 fw-bold">📑 결제 로그</h1>

  <!-- 검색 -->
  <form action="/company/log/selectlog" method="get" class="row gx-2 gy-2 align-items-center mb-4">
    <div class="col-auto">
      <select name="key" class="form-select">
        <option value="all" ${param.key == 'all' ? 'selected' : ''}>전체</option>
        <option value="emailAddress" ${param.key == 'emailAddress' ? 'selected' : ''}>이메일</option>
        <option value="subject" ${param.key == 'subject' ? 'selected' : ''}>제목</option>
        <option value="messageBody" ${param.key == 'messageBody' ? 'selected' : ''}>내용</option>
      </select>
    </div>
    <div class="col-auto">
      <input type="text" name="keyword" class="form-control" value="${param.keyword}" placeholder="검색어 입력">
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn_violet h50">검색</button>
    </div>
  </form>

  <!-- 로그 없을 때 -->
  <c:if test="${empty history}">
    <div class="alert alert-info text-center">🔍 검색 결과가 없습니다.</div>
  </c:if>

  <!-- 로그 테이블 -->
  <c:if test="${not empty history}">
    <div class="table-responsive">
      <table class="table table-hover align-middle text-center border">
        <thead class="table-light">
          <tr>
            <th>No.</th>
            <th>사용일시</th>
            <th>사용횟수</th>
            <th>이메일</th>
            <th>제목</th>
            <th>메시지</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="log" items="${history}" varStatus="st">
            <tr>
              <td>${st.index + 1}</td>
              <td>
                <c:choose>
                  <c:when test="${not empty log.usedAt}">${log.usedAt}</c:when>
                  <c:otherwise><span class="text-muted">미사용</span></c:otherwise>
                </c:choose>
              </td>
              <td>${log.usedCount}</td>
              <td>${log.emailAddress}</td>
              <td>${log.subject}</td>
              <td>
                <c:choose>
                  <c:when test="${fn:length(log.messageBody) > 15}">
                    ${fn:escapeXml(fn:substring(log.messageBody, 0, 15))}...
                  </c:when>
                  <c:otherwise>${fn:escapeXml(log.messageBody)}</c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <!-- 페이지네이션 -->
  <c:if test="${not empty totalPages}">
    <nav class="mt-4">
      <ul class="pagination justify-content-center">
        <c:if test="${currentPage > 1}">
          <li class="page-item">
            <a class="page-link" href="?page=${currentPage - 1}&key=${param.key}&keyword=${param.keyword}">이전</a>
          </li>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="i">
          <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="?page=${i}&key=${param.key}&keyword=${param.keyword}">${i}</a>
          </li>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
          <li class="page-item">
            <a class="page-link" href="?page=${currentPage + 1}&key=${param.key}&keyword=${param.keyword}">다음</a>
          </li>
        </c:if>
      </ul>
    </nav>
  </c:if>

  <!-- 하단 버튼 -->
  <div class="mt-4 text-end">
	<a href="/company/payment/main" class="btn btn_gray_line h50">← 뒤로가기</a>
  </div>

</div>

<script src="/js/company/payment/LogFilter.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
