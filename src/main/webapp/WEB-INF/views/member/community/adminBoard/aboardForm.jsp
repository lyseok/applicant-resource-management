<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 고객센터 | 게시글 등록</title>

<body>

	<h4>관리자 게시판 등록폼</h4>

<form:form action="/admin/adminBoard/form/insert" modelAttribute="aboard" method="post">
    
    <!-- 게시판 유형 선택 -->
    <div>
        <label for="boardTypeCode">게시판 유형</label>
        <form:select path="boardTypeCode" id="boardTypeCode">
            <form:option value="" label="-- 선택하세요 --" />
            <form:option value="FAQ">자주 묻는 질문</form:option>
            <form:option value="NOTICE">공지사항</form:option>
            <form:option value="QNA">문의사항</form:option>
        </form:select>
        <form:errors path="boardTypeCode" cssClass="error" />
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
