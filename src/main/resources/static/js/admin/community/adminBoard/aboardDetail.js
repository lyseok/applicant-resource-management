/**
 * 
 */
const no = document.querySelector("#noHidden").value;
const userId = document.querySelector("#userIdHidden").value;

console.log("넘어온 no 확인:", no);
console.log("넘어온 userId 확인:", userId);

const aboardDetail = document.querySelector("#aboardDetail");

fetch(`/ajax/admin/board/admin_board/detail/${no}`)
.then(resp => resp.json())
.then(rslt => {
	console.log("rslt 나오니? : ", rslt);
	let html = "";
	html += `
			<p class="h4">${rslt.boardTitle}</p>
			<p>작성자: ${rslt.userId}</p>
			<p>게시판 유형 코드: ${rslt.boardTypeCode}</p>
			<p>등록일시: ${rslt.boardWriteDate}</p>
			<p>내용: ${rslt.boardContent}</p>
			<p>삭제일시: ${rslt.boardDeleteDate}</p>
			<p>조회수: ${rslt.boardPostHit}</p>
			<p>게시글 상태: ${rslt.boardStatus}</p>`;
			
			rslt.boardTypeCode
			if(rslt.boardTypeCode === 'BRDD-001'){
				//답변 등록 폼이 비슷한 시기에 나오도록 DOMContentLoaded
				//답변 등록 폼은 문의사항(BRDD-001)일 때만 나옴
				console.log("타입은 들어있었어! : ", rslt.boardTypeCode);
				
				//DOMContentLoaded 하면 답변 폼 사라지기도 하니 주의
				const acommentFormContainer = document.querySelector("#acommentFormContainer");
				
				let acommentFormHtml = `
					<br>
					<form id="acommentForm">
						<label>작성자 아이디: </label>
						<input type="text" name="userId" value="${userId}" disabled>
						<input type="hidden" name="boardNo" value="${no}"><br>
						<textarea cols="60" rows="6" name="boardCommentContent" placeholder="답변 내용 입력..."></textarea>
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
					
					console.log("userId가 이상해! : ", acommentForm.userId.value);
					
					fetch(`/ajax/admin/board/admin_comment/${adminComment.boardNo}`, {
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
			          <p>답변 작성자: ${item.userId}</p>
			          <p>답변 내용: ${item.boardCommentContent}</p>`;
			    }
			});
	aboardDetail.innerHTML = html;
});