<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <c:if test="${empty history}">
    <div class="alert alert-info">로그 내역이 없습니다.</div>
  </c:if>

  <c:if test="${not empty history}">
    <div class="table-responsive">
      <table class="table table-bordered table-hover align-middle">
        <thead class="table-secondary">
          <tr>
            <th scope="col">No.</th>
            <th scope="col">사용 일시</th>
            <th scope="col">사용 횟수</th>
            <th scope="col">이메일</th>
            <th scope="col">제목</th>
            <th scope="col">메시지</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="log" items="${history}" varStatus="status">
            <tr>
              <th scope="row">${status.index + 1}</th>
              <td>
                <c:choose>
                  <c:when test="${not empty log.usedAt}">
                    ${log.usedAt}
                  </c:when>
                  <c:otherwise>
                    <span class="text-muted">미사용</span>
                  </c:otherwise>
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
    				<c:otherwise>
   					   ${fn:escapeXml(log.messageBody)}
    				</c:otherwise>
  				</c:choose>
			</td> --%>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <div class="mt-4">
    <a href="/company/payment/log/list" class="btn btn-outline-secondary">뒤로가기</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
