<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 고객센터 | 게시글 상세</title>

<body>

	<div id="aboardDetail"></div>
	
<script>
document.addEventListener("DOMContentLoaded", () => {
const boardNo = "${aboard.boardNo}";
console.log("boardNo 나오니? : ", boardNo);

const aboardDetail = document.querySelector("#aboardDetail");

fetch(`/ajax/admin/board/admin_board/detail/\${boardNo}`)
.then(resp => resp.json())
.then(rslt => {
	console.log("rslt 나오니? : ", rslt);
	let html = "";
	html += `
			<p class="h4">\${rslt.boardTitle}</p>
			<p>작성자 : \${rslt.userId}</p>
			<p>게시판 유형 코드: \${rslt.boardTypeCode}</p>
			<p>등록일시: \${rslt.boardWriteDate}</p>
			<p>내용: \${rslt.boardContent}</p>
			<p>삭제일시: \${rslt.boardDeleteDate}</p>
			<p>조회수: \${rslt.boardPostHit}</p>
			<p>게시글 상태: \${rslt.boardStatus}</p>`;
			
			console.log("댓글 상태가 나온다고? : ", rslt.adminCommentList.boardCommentStatus);
			
			rslt.adminCommentList.forEach(item => {
				console.log("아이템 나오니? :", item);
				
				let status = item.boardCommentStatus;
				
				if (status === 'R' || status === 'U' ) {
			        html += `
			          <hr/>
			          <p>답변 내용: \${item.boardCommentContent}</p>`;
			          
			          console.log("답변 내용이 비었다고? : ", item.boardCommentContent);
			    }
			});

			aboardDetail.innerHTML = html;
	});
});
</script>
</body>
