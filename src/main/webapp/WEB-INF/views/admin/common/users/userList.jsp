<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 회원관리 | 회원 목록</title>

<body>

	<h4>회원 목록</h4>
	
<table class="table">
	<thead>
		<tr>
			<th>사용자 ID</th>
			<th>사용자 비밀번호</th>
			<th>사용자 역할</th>
			<th>탈퇴일시</th>
			<th>탈퇴여부</th>
			<th>사용 가능</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty userList}">
			<c:forEach items="${userList}" var="user">
				<c:url value="/admin/users/detail" var="detailURL">
					<c:param name="userId" value="${user.userId }"/>
				</c:url>
				<tr>
					<td>
					<a href="${detailURL}">${user.userId}</a>
					</td>
					<td>${user.userPassword}</td>
					<td>${user.userRole}</td>
					<td>${user.userWithdrawDate}</td>
					<td>${user.userStatus}</td>
					<td>${user.userEnabled}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty userList }">
			<tr>
				<td colspan="8">해당 회원 없음.</td>
			</tr>
		</c:if>		
	</tbody>
</table>

</body>
