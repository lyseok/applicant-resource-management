<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<ul id="aboardList"></ul>
	<input type="hidden" value="boardTypeCode" name="boardTypeCode">

<script>
document.addEventListener("DOMContentLoaded", () => {
const boardTypeCode = "${type}";
fetch(`/ajax/admin/board/admin_board/\${boardTypeCode}`)
	.then(resp => {resp.json()
		.then(rslt=> {
			const aboardList = document.querySelector("#aboardList");
			let html = "";
			rslt.forEach(item => {
				html += `
					<li>게시글 번호: ${item.boardNo}</li>
					<li>사용자 ID: ${item.userId}</li>
					<li>게시판 유형 코드: ${item.boardTypeCode}</li>
					<li>제목: ${item.boardTitle}</li>
					<li>등록일시: ${item.boardWriteDate}</li>
					<li>내용: ${item.boardContent}</li>
					<li>삭제일시: ${item.boardDeleteDate}</li>
					<li>조회수: ${item.boardPostHit}</li>
					<li>게시글 상태: ${item.boardStatus}</li>`;
			});
			aboardList.innerHTML = html;
		});
	});
});
</script>
</body>
