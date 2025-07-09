<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>띹잡 고객센터 | 댓글 목록</title>

<body>

	<h4>관리자 게시판 댓글 목록</h4>
	
<table class="table">
	<thead>
		<tr>
			<th>댓글 번호</th>
			<th>사용자 ID</th>
			<th>게시글 번호</th>
			<th>댓글 내용</th>
			<th>작성 일시</th>
			<th>삭제일시</th>
			<th>댓글 상태</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty acommentList}">
			<c:forEach items="${acommentList}" var="acomment">
				<c:url value="/admin/adminComment/detail" var="detailURL">
					<c:param name="boardCommentNo" value="${acomment.boardCommentNo }"/>
				</c:url>
				<tr>
					<td>
					<a href="${detailURL}">${acomment.boardCommentNo}</a>
					</td>
					<td>${acomment.users.userId}</td>  <!-- has A 관계 -->					
					<td>${acomment.boardNo}</td>
					<td>${acomment.boardCommentContent}</td>
					<td>${acomment.boardWriteDate}</td>
					<td>${acomment.boardDeleteDate}</td>
					<td>${acomment.boardCommentStatus}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty acommentList }">
			<tr>
				<td colspan="9">댓글 없음.</td>
			</tr>
		</c:if>		
	</tbody>
</table>
</body>
