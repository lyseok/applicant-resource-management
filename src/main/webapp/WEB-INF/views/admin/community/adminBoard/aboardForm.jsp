<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 등록</title>
</head>
<body>

<p class="h4">관리자 게시판</p><br>

	<input type="hidden" id="noHidden" value="${aboard.boardNo}">
  	<sec:authentication property="principal.realUser.userId" var="userId"/>
	<form id="aboardForm">
		<label>게시판 유형 코드</label>
		<select id="boardTypeCode">
			<option value="-1">--선택--</option>
		</select>
		<select id="codeGroup" disabled>
			<option value="-1">--선택--</option>
		</select>
		<select id="memType" disabled>
			<option value="-1">--선택--</option>
		</select>
		<br>
		<input type="text" name="boardTitle" placeholder="제목">
		<label>작성자 아이디: </label>
		<input type="text" name="userId" value="${userId}" readonly><br>
		<textarea name="boardContent" placeholder="내용" rows="6" cols="60"></textarea>
	  <button type="submit">등록</button>
	</form>
	

<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
</body>