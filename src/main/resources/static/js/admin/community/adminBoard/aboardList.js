/**
 *
 */
const type = document.querySelector("#typeHidden").value;
const aboardList = document.querySelector("#aboardList");
const memTypeBtn = document.querySelector("#memTypeBtn");

console.log("넘어온 type 확인:", type);

//처음 전체 조회 및 전체 탭 클릭 시
function afaq() {
  fetch(`/ajax/admin/board/admin_board/faqlist/${type}`).then((resp) => {
    resp.json().then((rslt) => {
      let html = "";
      html += `<p class="h4">게시글 목록</p>`;
      rslt.forEach((item) => {
        html += `
				<li>작성자 : ${item.userId}</li>
				<li>게시판 유형 코드: ${item.boardTypeCode}</li>
				<li>제목: 
					<a href="/admin/board/admin_board/detail?no=${item.boardNo}">
					${item.boardTitle}
					</a>
				</li>
				<li>등록일시: ${item.boardWriteDate}</li>
				<li>내용: ${item.boardContent}</li>
				<li>삭제일시: ${item.boardDeleteDate}</li>
				<li>조회수: ${item.boardPostHit}</li>
				<li>게시글 상태: ${item.boardStatus}</li><hr/>`;
      });

      aboardList.innerHTML = html;
    });
  });

  // 함수 생성된 뒤에 onclick 들어가게
  let html = "";
  html += `<p class="h4">자주 묻는 질문 탭 선택</p>
		   <button id="ufaq" onclick="ufaq()">일반회원</button>
		   <button id="cfaq" onclick="cfaq()">기업회원</button>
		   <button id="afaq" onclick="afaq()">전체</button>`;

  memTypeBtn.innerHTML = html;
}
//일반회원 탭 클릭 시
function ufaq() {
  //함수 먼저->dom 나중->등록 가능
  fetch(`/ajax/admin/board/admin_board/faq/UFAQ`).then((resp) => {
    resp.json().then((rslt) => {
      let html = "";
      html += `<p class="h4">게시글 목록</p>`;
      rslt.forEach((item) => {
        html += `
					<li>작성자 : ${item.userId}</li>
					<li>게시판 유형 코드: ${item.boardTypeCode}</li>
					<li>제목: 
						<a href="/admin/board/admin_board/detail?no=${item.boardNo}">
						${item.boardTitle}
						</a>
					</li>
					<li>등록일시: ${item.boardWriteDate}</li>
					<li>내용: ${item.boardContent}</li>
					<li>삭제일시: ${item.boardDeleteDate}</li>
					<li>조회수: ${item.boardPostHit}</li>
					<li>게시글 상태: ${item.boardStatus}</li><hr/>`;
      });
      aboardList.innerHTML = html;
    });
  });
}
//기업회원 탭 클릭 시
function cfaq() {
  fetch(`/ajax/admin/board/admin_board/faq/CFAQ`).then((resp) => {
    resp.json().then((rslt) => {
      let html = "";
      html += `<p class="h4">게시글 목록</p>`;
      rslt.forEach((item) => {
        html += `
					<li>작성자 : ${item.userId}</li>
					<li>게시판 유형 코드: ${item.boardTypeCode}</li>
					<li>제목: 
						<a href="/admin/board/admin_board/detail?no=${item.boardNo}">
						${item.boardTitle}
						</a>
					</li>
					<li>등록일시: ${item.boardWriteDate}</li>
					<li>내용: ${item.boardContent}</li>
					<li>삭제일시: ${item.boardDeleteDate}</li>
					<li>조회수: ${item.boardPostHit}</li>
					<li>게시글 상태: ${item.boardStatus}</li><hr/>`;
      });
      aboardList.innerHTML = html;
    });
  });
}

function alist(type) {
  if (type === "BRDD-001" || type === "BRDD-003") {
    fetch(`/ajax/admin/board/admin_board/${type}`).then((resp) => {
      resp.json().then((rslt) => {
        let html = "";
        html += `<p class="h4">게시글 목록</p>`;
        rslt.forEach((item) => {
          html += `
						<li>작성자 : ${item.userId}</li>
						<li>게시판 유형 코드: ${item.boardTypeCode}</li>
						<li>제목: 
							<a href="/admin/board/admin_board/detail?no=${item.boardNo}">
							${item.boardTitle}
							</a>
						</li>
						<li>등록일시: ${item.boardWriteDate}</li>
						<li>내용: ${item.boardContent}</li>
						<li>삭제일시: ${item.boardDeleteDate}</li>
						<li>조회수: ${item.boardPostHit}</li>
						<li>게시글 상태: ${item.boardStatus}</li><hr/>`;
        });
        aboardList.innerHTML = html;
      });
    });
  } else {
    //시작시 일단 전체 호출
    // 페이지 로드시 전체 FAQ 자동 조회
    document.addEventListener("DOMContentLoaded", afaq);
    // 함수 생성된 뒤에 onclick 들어가게
    let html = "";
    html += `<p class="h4">자주 묻는 질문 탭 선택</p>
		   <button id="ufaq" onclick="ufaq()">일반회원</button>
		   <button id="cfaq" onclick="cfaq()">기업회원</button>
		   <button id="afaq" onclick="afaq()">전체</button>`;

    memTypeBtn.innerHTML = html;
  } //else 끝...
} //alist 함수 끝...

alist(type);  //만든 다음 마지막으로 호출!
