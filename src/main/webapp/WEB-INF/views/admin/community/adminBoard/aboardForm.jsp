<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>띹잡 고객센터 | 게시글 상세 입력</title>
	<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
</head>

<body>

	<form id="aboardForm">
		<input type="text" name="boardNo" placeholder="게시글 번호">
		<input type="text" name="userId" placeholder="사용자 ID">
		<input type="text" name="boardTypeCode" placeholder="게시판 유형 코드">
		<input type="text" name="boardTitle" placeholder="제목">
		<input type="text" name="boardWriteDate" placeholder="등록일시">
		<input type="text" name="boardContent" placeholder="내용">
		<input type="text" name="boardDeleteDate" placeholder="삭제일시">
		<input type="text" name="boardPostHit" placeholder="조회수">
		<input type="text" name="boardStatus" placeholder="게시글 상태">
	  <button type="submit">전송</button>
	</form>

</body>