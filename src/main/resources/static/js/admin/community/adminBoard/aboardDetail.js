/**
 *
 */
const no = document.querySelector("#noHidden").value;
const userId = document.querySelector("#userIdHidden").value;
const aboardDetail = document.querySelector("#aboardDetail");
const acommentListContainer = document.querySelector("#acommentListContainer");
const acommentFormContainer = document.querySelector("#acommentFormContainer");
const aboardform = document.querySelector("#aboardForm");

// 1
const abno = function(no){
	fetch(`/ajax/admin/board/admin_board/detail/${no}`)
	  .then((resp) => resp.json())
	  .then((rslt) => {
	    abdetail(rslt);
	});
}

// 2
const abdetail = function(rslt){
	let html = "";
	    html += `
				<p>${rslt.boardTitle}</p>
				<p>작성자: ${rslt.userId}</p>
				<p>게시판 유형 코드: ${rslt.boardTypeCode}</p>
				<p>등록일시: ${rslt.boardWriteDate}</p>
				<p>내용: ${rslt.boardContent}</p>
				<p>삭제일시: ${rslt.boardDeleteDate}</p>
				<p>조회수: ${rslt.boardPostHit}</p>
				<p>게시글 상태: ${rslt.boardStatus}</p>
				<hr>`;	
	aboardDetail.innerHTML = html;
	let no = rslt.boardNo;
	abbtn(no, rslt.boardTypeCode);
}

// 4
const b001 = function(no, type){
	if (type === "BRDD-001") {
		aclist(no);
	}	
}

// 5
const aclist = function(no){
	fetch(`/ajax/admin/board/admin_comment/${no}`).then(resp =>{
		resp.json().then(rslt =>{
			let html = "";
			rslt.forEach(comment =>{
	      		html += `
						<p>답변 번호: ${comment.boardCommentNo}</p>
						<p>답변 작성자: ${comment.userId}</p>
						<p>답변 내용: ${comment.boardCommentContent}</p>
						<p>답변 등록일자: ${comment.boardWriteDate}</p>
						<p>답변 삭제일자: ${comment.boardDeleteDate}</p>
						<p>답변 상태: ${comment.boardCommentStatus}</p>
						<hr/>
						`;
			});
			acommentListContainer.innerHTML = html;
		});
	    acform();
	});
};

// 6
const acform = function(){
	
	achtml();

    const acommentForm = document.querySelector("#acommentForm");
     
      acommentForm.onsubmit = function (e) {
	    e.preventDefault();
	    let adminComment = {
	      userId: acommentForm.userId.value,
	      boardNo: acommentForm.boardNo.value,
	      boardCommentContent: acommentForm.boardCommentContent.value,
	    };
	
	    fetch(`/ajax/admin/board/admin_comment/${adminComment.boardNo}`, {
	      method: "post",
	      headers: {
	        "Content-Type": "application/json",
	      },
	      body: JSON.stringify(adminComment),
	    }).then((resp) => {
	      resp.json().then((rslt) => {
	        console.log("글자", rslt.ok);
	      });
	    });
	};
}

// 7
const achtml = function(){
	let acommentFormHtml = `
						<form id="acommentForm">
							<label>작성자 아이디: </label>
							<input type="text" name="userId" value="${userId}" disabled>
							<input type="hidden" name="boardNo" value="${no}"><br>
							<textarea cols="60" rows="6" name="boardCommentContent" placeholder="답변 내용 입력..."></textarea>
						<button type="submit">등록</button>
						</form>`;
	
    acommentFormContainer.innerHTML = acommentFormHtml;
}

// 3
const abbtn = function(no, type){
	
	let lbtn = document.createElement("button");
	lbtn.id = "listBtn";
	lbtn.className = "btn btn_violet_line";
	lbtn.textContent = "목록";
	let ebtn = document.createElement("button");
	ebtn.id = "editBtn";
	ebtn.className = "btn btn_violet_line";
	ebtn.textContent = "수정";
	let dbtn = document.createElement("button");
	dbtn.id = "delBtn";
	dbtn.className = "btn btn_gray_line";
	dbtn.textContent = "삭제";
	
    aboardDetail.appendChild(lbtn);
    if(type !== 'BRDD-001') aboardDetail.appendChild(ebtn);
    aboardDetail.appendChild(dbtn);
    
    const editBtn = document.querySelector("#editBtn");
    const listBtn = document.querySelector("#listBtn");
    const delBtn = document.querySelector("#delBtn");
    
    if(listBtn != null){
		listBtn.onclick = function () {
		  aboardDetail.innerHTML = "";
		  
		  if (acommentFormContainer) acommentFormContainer.innerHTML = "";
		  if (acommentListContainer) acommentListContainer.innerHTML = "";
		  
		  alist2(type); // 목록으로
		};
	}
		
	if(editBtn != null){
		editBtn.onclick = function () {
		  aboardDetail.innerHTML = "";
		  
		  if (acommentFormContainer) acommentFormContainer.innerHTML = "";
		  if (acommentListContainer) acommentListContainer.innerHTML = "";
		  
		  if (aboardform) aboardform.style.display = "block";
		
		  aform(no, type); // 수정 폼 불러오기
		};
	}

	if(delBtn != null){
		delBtn.onclick = function () {
		  alert("해당 게시글을 삭제하시겠습니까?");
		  fetch(`/ajax/admin/board/admin_board/hidden/${no}`, {
		      method: "post",
		      headers: {
		        "Content-Type": "application/json",
		      },
		      body: JSON.stringify(adminComment),
		    }).then((resp) => {
		      resp.json().then((rslt) => {
		        console.log("글자", rslt.ok);
		      });
		    });
		};
	}
	
	b001(no, type);
}

abno(no);  //위에서 만든 시작 함수 실행

