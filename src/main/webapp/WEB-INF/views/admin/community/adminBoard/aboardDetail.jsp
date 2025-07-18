<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 상세</title>
</head>
<body>

	<sec:authentication property="principal.realUser.userId" var="userId"/>

	<!-- 게시글 상세 -->
	<div id="aboardDetail"></div>

	<!-- 리스트 -->
	<div id="memTypeBtn"></div><br>
	<ul id="aboardList">
		<input type="hidden" id="typeHidden" value="${type}">
	</ul>

	<!-- 답글 폼 -->
  	<input type="hidden" id="noHidden" value="${aboard.boardNo}">
  	<input type="hidden" id="userIdHidden" value="${userId}">
  	
	<div id="acommentFormContainer"></div>
	
	<!-- 등록 폼 미리 숨겨놓기 -->
	  <form id="aboardForm" style="display: none;">
	    
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
		<input type="text" name="userId" value="${userId}" disabled><br>
		<textarea name="boardContent" placeholder="내용" rows="6" cols="60"></textarea>
	    
	    <button type="submit">등록</button>
	  </form>
	  
	  <button><a href="/admin/board/admin_board/form">새로 등록</a></button>
	
<script src="/js/admin/community/adminBoard/aboardList.js"></script>
<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
<script src="/js/admin/community/adminBoard/aboardDetail.js"></script>	
</body>
