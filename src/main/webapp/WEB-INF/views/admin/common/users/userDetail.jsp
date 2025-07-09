<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 회원관리 | 회원정보 상세</title>

<body>

	<h4>회원정보 상세보기</h4>
	
	<!-- 회원 존재 여부에 따른 분기 -->
	<c:if test="${not empty user}">
	<ul>
		<li>${user.userId}</li>
		<li>${user.userPassword}</li>
		<li>${user.userRole}</li>
		<li>${user.userWithdrawDate}</li>
		<li>${user.userStatus}</li>
		<li>${user.userEnabled}</li>
	</ul>
	</c:if>
	
	<c:if test="${empty user}">
	    <p>존재하지 않는 회원입니다.</p>
	</c:if>

</body>