<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신입 초봉관련 페이지</title>
 <script>
    	document.addEventListener("DOMContentLoaded", ()=>{
    		$("body")
    	});
    </script>
</head>
<body>
<h2>채용공고 리스트</h2>

<table class="table">
	<thead>
		<tr>
			<th>기업명</th>
			<th>직무코드</th>
			<th>채용내용</th>
			<th>근무지역</th>
			<th>복지</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		 <c:if test="${not empty recruitmentList }">
			<c:forEach items="${recruitmentNotice.recruitmentList }" var="recruitment"> 
				<tr>		
					<td>
					</td> 
					<td>${recruitmentNotice.userId }</td>
					<td>${recruitmentNotice.jobCode }</td>
					<td>${recruitmentNotice.recContent }</td>
					<td>${recruitmentNotice.cityCode }</td>
					<td>${recruitmentNotice.welfare }</td>
				</tr>
			 </c:forEach>
		</c:if> 
		 <c:if test="${empty buyerList }">
			<tr>
				<td colspan="7">채용공고 없음.</td>
			</tr>
		</c:if> 
		</c:choose>
	</tbody>
</table>
</body>
</html>