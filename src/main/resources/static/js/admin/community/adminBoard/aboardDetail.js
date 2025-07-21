/**
 *
 */
const aboardDetail = document.querySelector("#aboardDetail");
const acommentListContainer = document.querySelector("#acommentListContainer");
const acommentFormContainer = document.querySelector("#acommentFormContainer");

// 1
const abno = function(no){
	listTitle.style.display = "none";
	aboardform.style.display = "none";
	memTypeBtn.innerHTML = "";
	formBtn.innerHTML = "";
	aboardList.innerHTML = "";
	pageTitle();
	fetch(`/ajax/admin/board/admin_board/detail/${no}`)
	  .then((resp) => resp.json())
	  .then((rslt) => {
	    abdetail(rslt);
	});
}

// 2
const abdetail = function(rslt) {
  let html = `
    <div class="jv_cont jv_benefit expand">
      <h2 class="jv_title h5">${rslt.boardTitle}</h2>
      <div class="desc_area" style="margin: 20px 0;">
        ${rslt.boardContent}
      </div>
      <div class="cont">
        <div class="details">
          <div class="row">
            <dl class="col">
              <dt>작성자</dt>
              <dd>${rslt.userId}</dd>
            </dl>
            <dl class="col">
              <dt>게시판 유형</dt>
              <dd>${rslt.boardTypeCode}</dd>
            </dl>
            <dl class="col">
              <dt>등록일시</dt>
              <dd>${rslt.boardWriteDate ?? "-"}</dd>
            </dl>
          </div>
          <div class="row">
            <dl class="col">
              <dt>조회수</dt>
              <dd>${rslt.boardPostHit}</dd>
            </dl>
            <dl class="col">
              <dt>상태</dt>
              <dd>${rslt.boardStatus}</dd>
            </dl>
            <dl class="col">
              <dt>삭제일시</dt>
              <dd>${rslt.boardDeleteDate ?? "-"}</dd>
            </dl>
          </div>
        </div>
      </div>
    </div>
  `;

  aboardDetail.innerHTML = html;

  let no = rslt.boardNo;
  abbtn(no, rslt.boardTypeCode);
};

// 4
const b001 = function(no, type){
	if (type === "BRDD-001") {
		aclist(no);
	}	
}

// 5
const aclist = function(no){
  fetch(`/ajax/admin/board/admin_comment/${no}`).then(resp => {
    resp.json().then(rslt => {
      let html = `
        <div class="Comments_root">
          <h3 class="PoseHeading_root mb-3">
            <span class="Typo_root Typo_primary Typo_title20"><b>답글</b></span>&nbsp;
            <span class="Typo_root Typo_violet Typo_title20"><b>${rslt.length}</b></span>
          </h3>
      `;

      rslt.forEach(comment => {
        html += `
          <div class="CommentItem_root mb-3 p-3 border rounded">
            <div class="CommentItem_header d-flex align-items-center mb-2">
              <div>
                <span class="Typo_root Typo_violet Typo_title40"><b>${comment.userId}</b></span>
              </div>
            </div>
            <div class="CommentItem_content mb-2">${comment.boardCommentContent}</div>
            <div class="CommentItem_meta Typo_root Typo_secondary Typo_label50">
              작성일: ${comment.boardWriteDate ?? "-"} / 상태: ${comment.boardCommentStatus}
            </div>
          </div>
        `;
      });

      html += `</div>`; // Close Comments_root

      acommentListContainer.innerHTML = html;
    });

    acform(no); // 폼 로드
  });
};

// 6
const acform = function(no){
	
	achtml(no);

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
const achtml = function(no){
  let html = `
    <div class="CommentForm_root mt-4 p-3 border rounded">
      <div class="CommentForm_profile d-flex align-items-center mb-3">
        <div>
          <span class="Typo_root Typo_violet Typo_title40"><b>${userId}</b></span>
          <span class="Typo_root Typo_primary Typo_title40"><b>로 문의 답글 등록</b></span>
        </div>
      </div>

      <form id="acommentForm">
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="boardNo" value="${no}">

        <div class="FormTextarea_root mb-2">
          <div class="FormTextarea_textareaWrap">
            <textarea class="FormTextarea_textarea w-100" name="boardCommentContent" placeholder="답글 내용 입력..."></textarea>
          </div>
          <div class="FormTextarea_messageWrap d-flex justify-content-between">
            <span class="Typo_root Typo_error Typo_label50"></span>
            <span class="Typo_root Typo_secondaryNHM9X Typo_label50 FormTextarea_hintMessage">0/5000자</span>
          </div>
        </div>

        <div class="CommentForm_btnWrap d-flex justify-content-end gap-2">
          <button type="reset" class="Button_root Button_secondary Button_outlined Button_size32 Button_isRound">취소</button>
          <button type="submit" class="Button_root Button_primary Button_filled Button_size32 Button_isRound">등록</button>
        </div>
      </form>
    </div>
  `;

  acommentFormContainer.innerHTML = html;
};

// 상세보기->목록
const detailToList = function(type){
	if(type.startsWith('BRDD')){
		alist(type)
	}else{
		alist2(type);
	}	
}

// 3
const abbtn = function(no, type){
	allBtns.innerHTML = "";
	
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
	
    allBtns.appendChild(lbtn);
    if(type !== 'BRDD-001') allBtns.appendChild(ebtn);
    allBtns.appendChild(dbtn);
    
    const editBtn = document.querySelector("#editBtn");
    const listBtn = document.querySelector("#listBtn");
    const delBtn = document.querySelector("#delBtn");
    
    if(listBtn){
		listBtn.onclick = function () {
		  aboardDetail.innerHTML = "";
		  detTitle.innerHTML = "";
		  
		  if (acommentFormContainer) acommentFormContainer.innerHTML = "";
		  if (acommentListContainer) acommentListContainer.innerHTML = "";
		  
		  detailToList(type); // 목록으로
		};
	}
		
	if(editBtn){
		editBtn.onclick = function () {
		  aboardDetail.innerHTML = "";
		  detTitle.innerHTML = "";
		  
		  if (acommentFormContainer) acommentFormContainer.innerHTML = "";
		  if (acommentListContainer) acommentListContainer.innerHTML = "";
		  
		  if (aboardform) aboardform.style.display = "block";
		
		  aform(no, type); // 수정 폼 불러오기
		};
	}

	if(delBtn){
		delBtn.onclick = function () {
		  const modal = new bootstrap.Modal(modalElement);
		  modal.show();
		
		  // '삭제하기' 버튼에 이벤트 연결 (중복 방지 위해 기존 제거)
		  const confirmBtn = document.querySelector("#deleteModal .btn-danger");
		  const newConfirmBtn = confirmBtn.cloneNode(true); // 기존 버튼 복제
		  confirmBtn.parentNode.replaceChild(newConfirmBtn, confirmBtn); // 이벤트 중첩 방지
		
		  newConfirmBtn.onclick = function () {
		    fetch(`/ajax/admin/board/admin_board/hidden/${no}`, {
		      method: "post",
		      headers: {
		        "Content-Type": "application/json",
		      },
		      body: JSON.stringify({ boardNo: no, boardTypeCode: type }),
		    }).then((resp) => {
		      resp.json().then((rslt) => {
		        if (rslt.ok) {
		          modal.hide();
		          console.log("글자", rslt.ok);
		          if (rslt.boardTypeCode) detailToList(rslt.boardTypeCode ?? "BRDD-001");  //목록으로, 널일 시 기본값 부여
		          aboardDetail.innerHTML = "";
        		}
      		  });
    		});
  		  };
		};
	}
	
	b001(no, type);
}

