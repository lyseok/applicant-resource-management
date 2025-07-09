<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 마이페이지 | 관심기업 상세</title>

<body>

	<h4>관심기업 상세보기</h4>
	
	<!-- 관심기업 존재 여부에 따른 분기 -->
	<c:if test="${not empty scompany}">
	<tr>
	    <td>${acomment.userId}</td>
		<td>${acomment.companyId}</td>
		<td>${acomment.scrabCompanyDate}</td>
	</tr>
	</c:if>
	
	<c:if test="${empty scompany}">
	    <td>관심 기업이 없습니다, 등록해보세요!</td>
	</c:if>

</body>