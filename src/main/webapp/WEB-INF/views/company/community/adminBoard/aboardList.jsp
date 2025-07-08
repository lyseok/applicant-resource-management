<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>띹잡 고객센터 | 게시글 목록</title>

<body>

	<h4>관리자 게시판 목록</h4>
	
<table class="table">
	<thead>
		<tr>
			<th>게시글 번호</th>
			<th>사용자 ID</th>
			<th>게시판 유형 코드</th>
			<th>제목</th>
			<th>등록일시</th>
			<th>내용</th>
			<th>삭제일시</th>
			<th>조회수</th>
			<th>게시글 상태</th>
			<th>댓글 수</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty aboardList}">
			<c:forEach items="${aboardList}" var="aboard">
				<c:url value="/admin/adminBoard/detail" var="detailURL">
					<c:param name="boardNo" value="${aboard.boardNo }"/>
				</c:url>
				<c:url value="/admin/adminBoard/list" var="typeURL">
					<c:param name="boardTypeCode" value="${aboard.boardTypeCode }"/>
				</c:url>
				<tr>
					<td>
					<a href="${detailURL}">${aboard.boardNo}</a>
					</td>
					<td>${aboard.users.userId}</td>  <!-- has A 관계 -->					
					<td>
					<a href="${typeURL}">${aboard.boardTypeCode}</a>
					</td>
					<td>${aboard.boardTitle}</td>
					<td>${aboard.boardWriteDate}</td>
					<td>${aboard.boardContent}</td>
					<td>${aboard.boardDeleteDate}</td>
					<td>${aboard.boardPostHit}</td>
					<td>${aboard.boardStatus}</td>
					<td>${aboard.adminCommentList.size() }</td>  <!-- has Many 관계 -->
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty aboardList }">
			<tr>
				<td colspan="10">게시글 없음.</td>
			</tr>
		</c:if>		
	</tbody>
</table>

</body>
