<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<title>띹잡 고객센터 | 게시글 상세</title>

<body>

	<div id="aboardDetail"></div>
	<!-- 여기까진 게시글 상세(작성자 나와야 함) -->
	
	<!-- 여기부턴 답글 폼 -->
	<div id="acommentFormContainer"></div>
	
<script>
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
			<p>작성자: \${rslt.userId}</p>
			<p>게시판 유형 코드: \${rslt.boardTypeCode}</p>
			<p>등록일시: \${rslt.boardWriteDate}</p>
			<p>내용: \${rslt.boardContent}</p>
			<p>삭제일시: \${rslt.boardDeleteDate}</p>
			<p>조회수: \${rslt.boardPostHit}</p>
			<p>게시글 상태: \${rslt.boardStatus}</p>`;
			
			rslt.boardTypeCode
			if(rslt.boardTypeCode === 'BRDD-001'){
				//답변 등록 폼이 비슷한 시기에 나오도록 DOMContentLoaded
				//답변 등록 폼은 문의사항(BRDD-001)일 때만 나옴
				console.log("타입은 들어있었어! : ", rslt.boardTypeCode);
				
				//DOMContentLoaded 하면 답변 폼 사라지기도 하니 주의
				const acommentFormContainer = document.querySelector("#acommentFormContainer");
				
				let acommentFormHtml = `
					<h1><sec:authentication property="principal.realUser.userId" var="userId"/></h1>
					<form id="acommentForm">
						<input type="hidden" name="userId" value="${userId}"><br>
						<input type="hidden" name="boardNo" value="${boardNo}"><br>
						<textarea cols="60" rows="6" name="boardCommentContent" placeholder="내용"></textarea>
					<button type="submit">등록</button>
					</form>`;
				
				acommentFormContainer.innerHTML = acommentFormHtml;
				
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
			} //'BRDD-001' if문 종료
			rslt.adminCommentList.forEach(item => {
				console.log("아이템 나오니? : ", item);
				
				console.log("댓글 상태가 나온다고? : ", item.boardCommentStatus);
				console.log("답변 내용이 비었다고? : ", item.boardCommentContent);
				
				let status = item.boardCommentStatus;
				
				if (status === 'R' || status === 'U' ) {
			        html += `
			          <hr/>
			          <p>답변 내용: \${item.boardCommentContent}</p>`;
			    }
			});
	aboardDetail.innerHTML = html;
});

</script>
</body>
