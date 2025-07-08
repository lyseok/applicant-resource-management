<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>띹잡 고객센터 | 게시글 상세 입력</title>
	<script src="/js/admin/community/aboardForm.js"></script>
</head>
<body>

	<h4>관리자 게시글 입력 폼</h4>

<form:form action="/admin/adminBoard/form/insert" modelAttribute="aboard" method="post">
    
    <!-- 게시판 유형 선택 -->
	  <div>
	    <label for="boardTypeCode">게시판 유형</label>
	    <select id="boardTypeCode" name="boardTypeCode">
	      <option value="">-- 선택하세요 --</option>
	      <option value="BRDD-002">자주 묻는 질문</option>
	      <option value="BRDD-003">공지사항</option>
	      <option value="BRDD-001">문의사항</option>
	    </select>
	  </div>
	
	  <div id="faqTypeWrapper" style="display: none;">
	    <label for="faqType">FAQ 유형</label>
	    <select id="faqType" name="faqType">
	      <option value="">-- 선택하세요 --</option>
	      <option value="UFAQ">사용자 FAQ</option>
	      <option value="CFAQ">기업 FAQ</option>
	    </select>
	  </div>
	
	  <div id="faqSubTypeWrapper" style="display: none;">
	    <label for="faqSubType">FAQ 상세 항목</label>
	    <select id="faqSubType" name="faqSubType">
	      <option value="">-- 선택하세요 --</option>
	    </select>
	  </div>

    <!-- 제목 -->
    <div>
        <label for="boardTitle">제목</label>
        <form:input path="boardTitle" id="boardTitle" />
        <form:errors path="boardTitle" cssClass="error" />
    </div>

    <!-- 내용 -->
    <div>
        <label for="boardContent">내용</label>
        <form:textarea path="boardContent" id="boardContent" rows="6" cols="60" />
        <form:errors path="boardContent" cssClass="error" />
    </div>

    <!-- 등록 버튼 -->
    <div>
        <button type="submit">등록</button>
    </div>

</form:form>
	
</body>
