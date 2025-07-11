<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title>띹잡 고객센터 | 게시글 상세</title>

<body>

	<h4>관리자 게시판 상세보기</h4>
	<table class="table table-bordered">
		<tr>
			<td colspan="2"><c:url
					value="/admin/community/adminBoard/aboardForm/edit" var="updateURL">
					<c:param name="boardNo" value="${aboard.boardNo }"></c:param>
				</c:url> <a class="btn btn-primary" href="${updateURL }">등록</a></td>
		</tr>
		<tr>
			<th>게시글 번호</th>
			<td>${aboard.boardNo}</td>
		</tr>
		<tr>
			<th>사용자 ID</th>
			<td>${aboard.userId}</td>
		</tr>
		<tr>
			<th>게시판 유형 코드</th>
			<td>${aboard.boardTypeCode}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${aboard.boardTitle}</td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>${aboard.boardWriteDate}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${aboard.boardContent}</td>
		</tr>
		<tr>
			<th>삭제일시</th>
			<td>${aboard.boardDeleteDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${aboard.boardPostHit}</td>
		</tr>
		<tr>
			<th>게시글 상태</th>
			<td>${aboard.boardStatus}</td>
		</tr>
		<tr>
			<th>댓글</th>
			<td>
				<table>
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
						<!--댓글이 있는지 없는지 조건문의 형태 -->
						<c:choose>
							<c:when test="${not empty aboard.acommentList }">
								<c:forEach items="${aboard.acommentList }" var="acomment">
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
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7">댓글 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>

</body>
