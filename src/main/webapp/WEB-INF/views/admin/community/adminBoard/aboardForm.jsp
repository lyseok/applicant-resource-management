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
    <div class="input-group mb-3">
	  <label class="input-group-text" for="boardTypeCode">게시판 유형</label>
	  <select class="form-select" id="aboardTC" name="boardTypeCode" for="boardTypeCode">
		  <option selected value="">-- 선택 --</option>
		  <option value="BRDD-002">자주 묻는 질문</option>
	      <option value="BRDD-003">공지사항</option>
	      <option value="BRDD-001">문의사항</option>
	  </select>
	</div>
    <div class="input-group mb-3">
	  <label class="input-group-text" id="faqTypeWrapper">회원 유형</label>
	  <select class="form-select" id="faqType" name="boardTypeCode" for="boardTypeCode" selected disabled>
		  <option selected value="">-- 선택 --</option>
	      <option value="UFAQ">일반회원</option>
	      <option value="CFAQ">기업회원</option>
	  </select>
	</div>
    <div class="input-group mb-3">
	  <label class="input-group-text">질문 유형</label>
	  <select class="form-select" id="faqSubType" name="boardTypeCode" for="boardTypeCode" selected disabled>
		  <option selected value="">-- 선택 --</option>
	  </select>
	</div>

	<!-- 제목 -->
	<div class="mb-3">
	  <label for="aboardTitle" class="form-label">제목</label>
	  <form:input path="boardTitle" class="form-control" id="aboardTitle" placeholder="제목을 이곳에 입력"></form:input>
	  <form:errors path="boardTitle" cssClass="error" />
	</div>
	
	<!-- 내용 -->
	<div class="mb-3">
	  <label for="aboardContent" class="form-label">내용</label>
	  <textarea path="boardContent" class="form-control" id="aboardContent" rows="6" cols="60" placeholder="내용을 이곳에 입력"/>
	  <form:errors path="boardContent" cssClass="error" />
	</div>

    <!-- 등록 버튼 -->
    <div>
        <button type="submit">등록</button>
    </div>

</form:form>
	
</body>
