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
				<c:url value="/admin/aboardForm/edit" var="updateURL">
					<c:param name="boardNo" value="${aboard.boardNo}"></c:param>
				</c:url>
				<a class="btn btn-primary" href="${updateURL}">수정</a>
			</td>
		</tr>
	<tr><th>게시글 번호</th><td>${aboard.boardNo}</td></tr>
	<tr><th>사용자 ID</th><td>${aboard.userId}</td></tr>
	<tr><th>게시판 유형 코드</th><td>${aboard.boardTypeCode}</td></tr>
	<tr><th>제목</th><td>${aboard.boardTitle}</td></tr>
	<tr><th>등록일시</th><td>${aboard.boardWriteDate}</td></tr>
	<tr><th>내용</th><td>${aboard.boardContent}</td></tr>
	<tr><th>삭제일시</th><td>${aboard.boardDeleteDate}</td></tr>
	<tr><th>조회수</th><td>${aboard.boardPostHit}</td></tr>
	<tr><th>게시글 상태</th><td>${aboard.boardStatus}</td></tr>
	<!-- 게시글 존재 여부에 따른 분기 -->
	<c:if test="${not empty aboard.adminCommentList}">
		<c:forEach items="${aboard.adminCommentList}" var="acomment">
			<tr>
				<td>${acomment.boardCommentNo}</td>
				<td>${acomment.userId}</td>
				<td>${acomment.boardNo}</td>
				<td>${acomment.boardCommentContent}</td>
				<td>${acomment.boardWriteDate}</td>
				<td>${acomment.boardDeleteDate}</td>
				<td>${acomment.boardCommentStatus}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty aboard.adminCommentList}">
	    <tr>
			<td colspan="7">댓글 없음</td>
		</tr>
	</c:if>

</body>
