<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 상세</title>
	<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
	<script src="/js/admin/community/adminBoard/aboardList.js"></script>
</head>
<body>

	<sec:authentication property="principal.realUser.userId" var="userId"/>

	<div id="aboardDetail"></div>
	<!-- 게시글 상세 -->

	<!-- 답글 폼 -->
  	<input type="hidden" id="noHidden" value="${aboard.boardNo}">
  	<input type="hidden" id="userIdHidden" value="${userId}">
  	
	<div id="acommentFormContainer"></div>
	
	<!-- 등록 폼 미리 숨겨놓기 -->
	  <form id="aboardForm" style="display: none;">
	    <input type="hidden" name="userId" value="${userId}"><br>
	    
	    <label>게시판 유형 코드</label>
	    <select id="boardTypeCode">
	      <option value="-1">--선택--</option>
	    </select>
	    
	    <select id="codeGroupNo" disabled>
	      <option value="-1">--선택--</option>
	      <option value="UFAQ">일반회원</option>
	      <option value="CFAQ">기업회원</option>
	    </select>
	    
	    <select id="memType" disabled>
	      <option value="-1">--선택--</option>
	    </select>
	    
	    <br>
	    <input type="text" name="boardTitle" placeholder="제목">
	    <input type="text" name="boardContent" placeholder="내용">
	    
	    <button type="submit">등록</button>
	  </form>
	

<script src="/js/admin/community/adminBoard/aboardDetail.js"></script>	
</body>
