<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<ul id="aboardList"></ul>
	<input type="hidden" value="boardTypeCode" name="boardTypeCode">
	
	<%--구상을 위한 예시 --%>
	<div id="faqno">
		<h3>자주 묻는 질문 탭 선택</h3>
		<button type="button" id="ufaq">일반회원</button>
		<button type="button" id="cfaq">기업회원</button>
	</div>
	

<script>
document.addEventListener("DOMContentLoaded", () => {
const boardTypeCode = "${type}";
fetch(`/ajax/admin/board/admin_board/\${boardTypeCode}`)
	.then(resp => {resp.json()
		.then(rslt=> {
			const aboardList = document.querySelector("#aboardList");
			let html = "";
			rslt.forEach(item => {
				console.log("아이템 나오니? :", item);
				html += `
					<p class="h4">게시글 목록</p>
					<li>작성자 : \${item.userId}</li>
					<li>게시판 유형 코드: \${item.boardTypeCode}</li>
					<li>제목: 
						<a href="/admin/board/admin_board/detail?no=\${item.boardNo}">
						\${item.boardTitle}
						</a>
					</li>
					<li>등록일시: \${item.boardWriteDate}</li>
					<li>내용: \${item.boardContent}</li>
					<li>삭제일시: \${item.boardDeleteDate}</li>
					<li>조회수: \${item.boardPostHit}</li>
					<li>게시글 상태: \${item.boardStatus}</li><hr/>`;
			});
			aboardList.innerHTML = html;
		});
	});
});
</script>
</body>
