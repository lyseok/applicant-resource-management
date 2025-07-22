/**
 *
 */
const aboardDetail = document.querySelector("#aboardDetail");
const acommentListContainer = document.querySelector("#acommentListContainer");
const acommentFormContainer = document.querySelector("#acommentFormContainer");

// 1
const abhit = async function (no) {
  const resp = await fetch(`/ajax/admin/board/admin_board/hit/${no}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ boardNo: no }),
  });
  return await resp.json();
};

// 2
const abno = function (no) {
  listTitle.style.display = "none";
  aboardform.style.display = "none";
  memTypeBtn.innerHTML = "";
  formBtn.innerHTML = "";
  aboardList.innerHTML = "";
  pageTitle();

  abhit(no) //조회수부터 증가
    .then((rslt) => {
      if (rslt.ok) {
        return fetch(`/ajax/admin/board/admin_board/detail/${no}`);
      } else {
        throw new Error("조회수 증가 실패");
      }
    })
    .then((resp) => resp.json())
    .then((rslt) => {
      abdetail(rslt); // 상세 정보 렌더링
    })
    .catch((err) => {
      console.error("에러 발생:", err);
    });
};

// 3
const abdetail = function (rslt) {
  let html = `
    <div class="jv_cont jv_benefit expand">
      <h2 class="jv_title h5">${rslt.boardTitle}</h2>
      <div class="desc_area" style="margin: 20px 0;">
        ${rslt.boardContent?.replaceAll("\n", "<br>")}
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

// 5
const b001 = function (no, type) {
  if (type === "BRDD-001") {
    aclist(no);
  }
};

//버튼 누르면(이거 자체가 온클릭)
function aceditable(pBtn, commentNo, userId, boardNo) {
  const hjDiv = pBtn.closest("#hjb").querySelector("#hjg");
  if (pBtn.innerText.includes("모드")) {
    hjDiv.contentEditable = true;
    hjDiv.focus();
    pBtn.innerText = "수정";
  } else {
    //alert("서버로 전송할꺼얌");
    // 서버에 떤송하고, 사용자에게는 잘 수정 되었다고 알려주면 끄읕!
    // 예쁘게 할 거면 sweetalert2

    //취소/수정 모달
    Swal.fire({
      title: "정말로 수정하시겠습니까?",
      text: "수정한 답글은 즉시 반영되며, 되돌릴 수 없습니다.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "확인",
    }).then((result) => {
      if (result.isConfirmed) {
        let acommentVO = {
          boardCommentNo: commentNo,
          userId: userId,
          boardNo: boardNo,
          boardCommentContent: hjDiv.innerHTML,
        };

        fetch(`/ajax/admin/board/admin_comment/detail/${commentNo}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(acommentVO),
        }).then((resp) => {
          resp.json().then((rslt) => {
            if (rslt.ok) {
              hjDiv.contentEditable = false;
              pBtn.innerText = "답글 수정모드";
            }
          });
        });

        Swal.fire({
          title: "수정 완료",
          text: "답글이 수정되었습니다.",
          icon: "success",
        });
      }
    });
  }
}

const acdel = function (cno, bno) {

  Swal.fire({
    title: "삭제하시겠습니까?",
    text: "삭제한 답글은 즉시 반영되며, 되돌릴 수 없습니다.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "확인"
  }).then((result) => {
    fetch(`/ajax/admin/board/admin_comment/hidden/${cno}`, {
    method : "POST",
    headers : {
      "Content-Type" : "application/json"
    },
    body : JSON.stringify({ boardCommentNo : cno, boardNo : bno }),
    })
    .then((resp)=>{
      resp.json()
      .then((rslt)=>{
        if (rslt.ok) {
          if (rslt.boardNo){
            abno(rslt.boardNo ?? "ABNO000001"); //목록으로, 널일 시 기본값 부여
            acommentListContainer.innerHTML = "";
            aclist();
          }
        }
      })
    })
    if (result.isConfirmed) {
      Swal.fire({
        title: "삭제 완료!",
        text: "답글이 삭제되었습니다.",
        icon: "success"
      });
    }
  });
};

// 6
const aclist = function (no) {
  fetch(`/ajax/admin/board/admin_comment/${no}`).then((resp) => {
    resp.json().then((rslt) => {
      let html = `
        <div class="Comments_root">
          <h3 class="PoseHeading_root mb-3">
            <span class="Typo_root Typo_primary Typo_title20"><b>답글</b></span>&nbsp;
            <span class="Typo_root Typo_violet Typo_title20"><b>${rslt.length}</b></span>
          </h3>
      `;

      rslt.forEach((comment) => {
        html += `
          <div id=hjb class="CommentItem_root mb-3 p-3 border rounded">
            <div class="CommentItem_header d-flex align-items-center mb-2">
              <div>
                <span class="Typo_root Typo_violet Typo_title40"><b>${comment.userId}</b></span>
              </div>
            </div>
            <div class="CommentItem_content mb-2" id=hjg >${comment.boardCommentContent}</div>
            <div class="CommentItem_meta Typo_root Typo_secondary Typo_label50">
              작성일: ${comment.boardWriteDate ?? "-"} / 상태: ${comment.boardCommentStatus}
              <input type="hidden" id="cnoHidden" value="${comment.boardCommentNo}">
            </div>
            <div class="mt-3">
				      <button class="btn btn-sm btn-outline-primary" 
				      onclick="aceditable(this,'${comment.boardCommentNo}','${comment.userId}','${comment.boardNo}')">
				      	답글 수정모드
				      </button>
				      <button class="btn btn-sm btn-outline-danger" onclick="acdel('${comment.boardCommentNo}','${comment.boardNo}')">답글 삭제</button>
			      </div>
          </div>
        `;
      });

      html += `</div>`; // Close Comments_root

      acommentListContainer.innerHTML = html;
    });

    acform(no); // 폼 로드, 폼에서 등록시 no(boardCommentNo)가 필요하니 같이 넘김
  });
};

// 7
const acform = function (no) {
  achtml(no);  //답글 등록 폼 html

  const acommentForm = document.querySelector("#acommentForm");

  acommentForm.onsubmit = function (e) {
    e.preventDefault();

    Swal.fire({
      title: "정말로 등록하시겠습니까?",
      text: "등록한 답글은 즉시 반영됩니다.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "확인",
    }).then((result) => {

    let adminComment = {
	  userId: acommentForm.userId.value,
	  boardNo: acommentForm.boardNo.value,
	  boardCommentContent: acommentForm.boardCommentContent.value
	    .split("\n")
	    .map(line => `<div>${line}</div>`)
	    .join(""),
	};

    fetch(`/ajax/admin/board/admin_comment/${adminComment.boardNo}`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(adminComment),
    }).then((resp) => {
      resp.json().then((rslt) => {
        if (rslt.ok) {
          if (rslt.boardNo){
            abno(rslt.boardNo ?? "ABNO000001"); //목록으로, 널일 시 기본값 부여
            acommentListContainer.innerHTML = "";
            aclist(rslt.boardCommentNo);
          }
        }
      });
    });
    if (result.isConfirmed) {
      Swal.fire({
        title: "답글 등록 완료!",
        text: "답글이 등록되었습니다.",
        icon: "success"
      });
    }
  });
 };
};

// 8
const achtml = function (no) {
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
const detailToList = function (type) {
  if (type.startsWith("BRDD")) {
    alist(type);
  } else {
    alist2(type);
  }
};

// 4
const abbtn = function (no, type) {
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
  if (type !== "BRDD-001") allBtns.appendChild(ebtn);
  allBtns.appendChild(dbtn);

  const editBtn = document.querySelector("#editBtn");
  const listBtn = document.querySelector("#listBtn");
  const delBtn = document.querySelector("#delBtn");

  if (listBtn) {
    listBtn.onclick = function () {
      aboardDetail.innerHTML = "";
      detTitle.innerHTML = "";

      if (acommentFormContainer) acommentFormContainer.innerHTML = "";
      if (acommentListContainer) acommentListContainer.innerHTML = "";

      detailToList(type); // 목록으로
    };
  }

  if (editBtn) {
    editBtn.onclick = function () {
      aboardDetail.innerHTML = "";
      detTitle.innerHTML = "";

      if (acommentFormContainer) acommentFormContainer.innerHTML = "";
      if (acommentListContainer) acommentListContainer.innerHTML = "";

      if (aboardform) aboardform.style.display = "block";

      aform(no, type); // 수정 폼 불러오기
    };
  }

  if (delBtn) {
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
              if (rslt.boardTypeCode){
                detailToList(rslt.boardTypeCode ?? "BRDD-001"); //목록으로, 널일 시 기본값 부여
                aboardDetail.innerHTML = "";
              }
            }
          });
        });
      };
    };
  }

  b001(no, type);
};
