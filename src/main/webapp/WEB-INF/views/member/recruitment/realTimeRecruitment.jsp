<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
    <title>채용공고 리스트</title>
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
		 <c:if test="${not empty recruitmentList }">
			<c:forEach items="${recruitmentList }" var="recruitment"> 
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
	</tbody>
</table>
</body>
</html>