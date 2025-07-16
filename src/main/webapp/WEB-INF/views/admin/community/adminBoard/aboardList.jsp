<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<div id="memTypeBtn"></div><br>
	<ul id="aboardList">
		<input type="hidden" id="typeHidden" value="${type}">
	</ul>
	<button>등록</button>

<script src="/js/admin/community/adminBoard/aboardList.js"></script>
</body>
