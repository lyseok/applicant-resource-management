<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 고객센터 | 게시글 상세</title>

<body>

	<h4>관리자 게시판 상세보기</h4>
	
	<table class="table table-bordered">
		<tr>
			<td colspan="2">
				<c:url value="/admin/adminBoard/detail" var="updateURL">
					<c:param name="what" value="${buyer.buyerId }"></c:param>
				</c:url>
				<a class="btn btn-primary" href="${updateURL }">수정</a>
			</td>
		</tr>
	
	<!-- 게시글 존재 여부에 따른 분기 -->
	<c:if test="${not empty aboard}">
	    <h2>${aboard.boardTitle}</h2>
	    <p>${aboard.boardContent}</p>
	</c:if>
	
	<c:if test="${empty aboard}">
	    <p>존재하지 않는 게시글입니다.</p>
	</c:if>

</body>
