<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<ul id="aboardList">
		<p class="h4">게시글 목록</p>
		<input type="hidden" id="typeHidden" value="${type}">
	</ul>
	
	<%--구상을 위한 예시 --%>
	<div id="faqno">
		<h3>자주 묻는 질문 탭 선택</h3>
		<button type="button" id="ufaq">일반회원</button>
		<button type="button" id="cfaq">기업회원</button>
	</div>

<script src="/js/admin/community/adminBoard/aboardList.js"></script>
</body>
