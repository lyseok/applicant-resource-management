<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 답변 등록</title>
</head>

<body>


<p class="h4">문의사항 답변 등록</p><br>

  <h1><sec:authentication property="principal.realUser.userId" var="userId"/></h1> <%-- 이건 로그인유저 --%>
  <%-- 
  답글 폼은 게시글 단건과 같이 넘어옴, 비동기 중 <AdminBoardVO> 조회부터 
  거기서 List<AdminCommentVO> 조회하면서 폼 입력이 가능하게
  --%>
  	<div id="aboard"></div>
	<form id="acommentForm">
		<input type="hidden" name="userId" value="${userId}"><br> <%-- 이건 작성자 --%>
		<input type="hidden" name="boardNo" value="${boardNo}"><br>
		<input type="text" name="boardCommentContent" placeholder="내용">
	  <button type="submit">등록</button>
	</form>
<script>
fetch(`/ajax/admin/board/admin_comment/\${boardNo}`)
.then(resp => {resp.json()
	.then(rslt=> {
		const aboard = document.querySelector("#aboard");
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
		aboard.innerHTML = html;
	});
});


const acommentForm = document.querySelector("#acommentForm");
acommentForm.onsubmit = function(){
	event.preventDefault();
	let adminComment = {
		userId : acommentForm.userId.value,
		boardNo : acommentForm.boardNo.value,
		boardCommentContent : acommentForm.boardCommentContent.value
	}
	fetch(`/ajax/admin/board/admin_comment/\${adminComment.boardNo}`, {
		method : "post",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify(adminComment)
	}).then(resp => {
		resp.json().then(rslt=> {
			console.log("글자", rslt.ok);
		});
	});
};
</script>
</body>