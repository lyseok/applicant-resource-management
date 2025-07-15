<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<title>띹잡 고객센터 | 게시글 상세</title>

<body>

	<sec:authentication property="principal.realUser.userId" var="userId"/>

	<div id="aboardDetail"></div>
	<!-- 게시글 상세 -->

	<!-- 답글 폼 -->
  	<input type="hidden" id="noHidden" value="${aboard.boardNo}">
  	<input type="hidden" id="userIdHidden" value="${userId}">
  	
	<div id="acommentFormContainer"></div>

<script src="/js/admin/community/adminBoard/aboardDetail.js"></script>	
</body>
