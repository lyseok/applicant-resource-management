<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 회원관리 | 회원정보 입력</title>

<body>

	<h4>관리자 회원정보 입력 폼</h4>

<form:form action="/admin/users/form/insert" modelAttribute="user" method="post">

    <!-- 등록 버튼 -->
    <div>
        <button type="submit">등록</button>
    </div>

</form:form>
	
</body>