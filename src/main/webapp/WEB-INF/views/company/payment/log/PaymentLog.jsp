<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제 내역 로그</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
  <h3 class="mb-4">결제 내역 로그</h3>

  <!-- 검색 폼은 그대로 -->
  
  <form id="filterForm" class="row gx-2 gy-2 align-items-center mb-4" action="/company/log/selectlog" method="get">
    <div class="col-auto">
      <select name="key" class="form-select">
        <option value="all">전체</option>
        <option value="emailAddress">이메일</option>
        <option value="subject">제목</option>
        <option value="messageBody">내용</option>
      </select>
    </div>
    <div class="col-auto">
      <input type="text" name="keyword" class="form-control" placeholder="검색어 입력">
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary">검색하기</button>
    </div>
  </form>
  <!-- <form id="filterForm" … </form> -->
<!-- <form action="/company/log/selectlog">
	<select name="key">
		<option value="all">전체</option>
		<option value="subject">제목</option>
		<option value="messageBody">내용</option>
		<option value="emailAddress">이메일</option>
	</select>
	<input type="text" name="keyword">
</form> -->
  <!-- 알림은 조건부 -->
  <c:if test="${empty history}">
    <div class="alert alert-info">로그 내역이 없습니다.</div>
  </c:if>

  <!-- 테이블은 항상 출력 -->
  <div class="table-responsive">
    <table id="logTable" class="table table-bordered table-hover align-middle log-table">
      <thead class="table-secondary">
        <tr>
          <th>No.</th>
          <th>사용 일시</th>
          <th>사용 횟수</th>
          <th>이메일</th>
          <th>제목</th>
          <th>메시지</th>
        </tr>
      </thead>
      <tbody>
        <!-- 서버 초기 렌더링 시에만 history가 채워진다면, rows를 여기에 -->
        <c:if test="${not empty history}">
          <c:forEach var="log" items="${history}" varStatus="st">
            <tr>
              <th scope="row">${st.index + 1}</th>
              <td>
                <c:choose>
                  <c:when test="${not empty log.usedAt}">
                    ${log.usedAt}
                  </c:when>
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
        </c:if>
      </tbody>
    </table>
  </div>

  <div class="mt-4">
    <a href="/company/payment/log/list" class="btn btn-outline-secondary">뒤로가기</a>
  </div>
</div>

<script src="/js/company/payment/LogFilter.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
