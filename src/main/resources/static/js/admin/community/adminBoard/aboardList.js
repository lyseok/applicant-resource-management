const type = document.querySelector("#typeHidden").value;
const aboardList = document.querySelector("#aboardList");
const memTypeBtn = document.querySelector("#memTypeBtn");

const flist = function () {
	let html = `
		<p class="h4">자주 묻는 질문 탭 선택</p>
		<button id="ufaq" onclick="faqUser()">일반회원</button>
		<button id="cfaq" onclick="faqCorp()">기업회원</button>
		<button id="afaq" onclick="faqAll()">전체</button>`;
	memTypeBtn.innerHTML = html;
};

const nlist = function () {
	let html = `
		<p class="h4">공지사항 탭 선택</p>
		<button id="untc" onclick="noticeUser()">일반회원</button>
		<button id="cntc" onclick="noticeCorp()">기업회원</button>
		<button id="antc" onclick="noticeEvent()">이벤트</button>`;
	memTypeBtn.innerHTML = html;
};

const bhtml = function (rslt) {
	let html = `<p class="h4">게시글 목록</p>`;
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
			<li>게시글 상태: ${item.boardStatus}</li>
			<hr/>`;
	});
	aboardList.innerHTML = html;
};

// FAQ 전체
const faqAll = function() {
	fetch(`/ajax/admin/board/admin_board/list/${type}`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			flist();
		});
}

// FAQ 일반회원
const faqUser = function() {
	fetch(`/ajax/admin/board/admin_board/pre/UFAQ`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			flist();
		});
}

// FAQ 기업회원
const faqCorp = function() {
	fetch(`/ajax/admin/board/admin_board/pre/CFAQ`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			flist();
		});
}

// 공지 일반
const noticeUser = function() {
	fetch(`/ajax/admin/board/admin_board/pre/UNTC`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			nlist();
		});
}

// 공지 기업
const noticeCorp = function() {
	fetch(`/ajax/admin/board/admin_board/pre/CNTC`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			nlist();
		});
}

// 공지 이벤트
const noticeEvent = function() {
	fetch(`/ajax/admin/board/admin_board/pre/ENTC`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			nlist();
		});
}

const pre = function(type){
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			nlist();
		});
}

// 초기 로딩
const alist = function(type) {
	console.log("디테일에서 넘어온 리스트", type);
	if (type === "BRDD-001") {
		fetch(`/ajax/admin/board/admin_board/${type}`)
			.then((resp) => resp.json())
			.then((rslt) => {
				bhtml(rslt);
			});
	} else if (type === "BRDD-003") {
		noticeUser();  // 공지사항 일반
		nlist();
	} else if (type === "BRDD-002") {
		faqAll();  // FAQ 전체
		flist();
	}
}

//초기 이후 로딩
const alist2 = function(type) {
	console.log("디테일에서 넘어온 리스트", type);
	if (type === "BRDD-001") {
		fetch(`/ajax/admin/board/admin_board/${type}`)
			.then((resp) => resp.json())
			.then((rslt) => {
				bhtml(rslt);
			});
	} else{
		pre();
	}
}

alist(type);
