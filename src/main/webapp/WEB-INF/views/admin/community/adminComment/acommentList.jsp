<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>띹잡 고객센터 | 문의사항 답글</title>

<body>

	<p class="h4">문의 답글 목록</p><br>
	
	<ul id="acommentList"></ul>
	<input type="hidden" value="boardNo" name="boardNo">

<script>
//답글 전체가 나오고
fetch(`/ajax/admin/board/admin_comment`)
	.then(resp => {resp.json()
		.then(rslt=> {
			const acommentList = document.querySelector("#acommentList");
			let html = "";
			rslt.forEach(item => {
				console.log("아이템 나오니? :", item);
				html += `
					<p class="h4">답글 목록</p>
					<li>작성자: \${item.userId}</li>
					<li>문의글: 
						<a href="/admin/board/admin_board/detail?no=\${item.boardNo}">
						\${item.boardNo.boardTitle}
						</a>
					</li>
					<li>답변: \${item.boardCommentContent}</li>
					<li>등록일시: \${item.boardWriteDate}</li>
					<li>삭제일시: \${item.boardDeleteDate}</li>
					<li>답변 상태: \${item.boardCommentStatus}</li><hr/>`;
			});
			acommentList.innerHTML = html;
	});
});
</script>
</body>
