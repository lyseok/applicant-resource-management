/**
 *
 */
const no = document.querySelector("#noHidden").value;
const userId = document.querySelector("#userIdHidden").value;
const aboardDetail = document.querySelector("#aboardDetail");

//함수 미리 만들어놓기
function iniBtn(no, type) {
      const editBtn = document.querySelector("#editBtn");
      const listBtn = document.querySelector("#listBtn");
		
	  console.log("타입2 : ", type);  //얘는 const한 애로 잘 골라짐
      if (editBtn) {
        editBtn.onclick = function () {
          alert("클릭 돼??");

          aboardDetail.innerHTML = "";
          const acommentFormContainer = document.querySelector(
            "#acommentFormContainer"
          );
          if (acommentFormContainer) acommentFormContainer.innerHTML = "";

          const aboardForm = document.querySelector("#aboardForm");
          if (aboardForm) aboardForm.style.display = "block";

          aform(no, type); // 수정 폼 불러오기
        };
      }

      if (listBtn) {
        listBtn.onclick = function () {
          alist(type); // 목록으로
        };
      }
    }

fetch(`/ajax/admin/board/admin_board/detail/${no}`)
  .then((resp) => resp.json())
  .then((rslt) => {
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
			
	const type = rslt.boardTypeCode;
    
	rslt.adminCommentList.forEach((comment) => {
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

    if (type === "BRDD-001") {
      //답변 등록 폼이 비슷한 시기에 나오도록 DOMContentLoaded
      //답변 등록 폼은 문의사항(BRDD-001)일 때만 나옴

      //DOMContentLoaded 하면 답변 폼 사라지기도 하니 주의
      const acommentFormContainer = document.querySelector(
        "#acommentFormContainer"
      );

      let acommentFormHtml = `
					<form id="acommentForm">
						<label>작성자 아이디: </label>
						<input type="text" name="userId" value="${userId}" disabled>
						<input type="hidden" name="boardNo" value="${no}"><br>
						<textarea cols="60" rows="6" name="boardCommentContent" placeholder="답변 내용 입력..."></textarea>
					<button type="submit">등록</button>
					</form>`;

      acommentFormContainer.innerHTML = acommentFormHtml;

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
    } //'BRDD-001' if문 종료
    html += `
				<button id="listBtn">목록</button>
				<button id="editBtn">수정</button>`;
    aboardDetail.innerHTML = html;

	//함수 호출은 html 출력된 후에
    iniBtn(no, type);
    
  });
