let type = document.querySelector("#typeHidden").value;
const aboardList = document.querySelector("#aboardList");
const memTypeBtn = document.querySelector("#memTypeBtn");
const formBtn = document.querySelector("#formBtn");
const detTitle = document.querySelector("#detTitle");
const listTitle = document.querySelector("#listTitle");
const allBtns = document.querySelector("#allBtns");
const userId = document.querySelector("#userIdHidden")?.value;

const bhtml = function (rslt) {
	listTitle.style.display = "block";
	let html = '';
	rslt.forEach((item) => {
		html += `
			<li>게시판 유형 코드: ${item.boardTypeCode}</li>
			<li>제목: 
				<button id="abDetail" onclick="abno('${item.boardNo}')">${item.boardTitle}</button>
			</li>
			<li>등록일시: ${item.boardWriteDate}</li>
			<li>삭제일시: ${item.boardDeleteDate}</li>
			<li>조회수: ${item.boardPostHit}</li>
			<li>게시글 상태: ${item.boardStatus}</li>
			<hr/>
			`;
	});
	newFormBtn();
	allBtns.innerHTML = '';
	aboardList.innerHTML = html;
};

const pageTitle = function(){
	detTitle.innerHTML = "";
	let title = document.createElement("p"); 
	title.className = "h4";                  
	title.textContent = "게시글 상세";         
	detTitle.appendChild(title);         
}

const newFormBtn = function(){
	formBtn.innerHTML = "";
	let newForm = document.createElement("a");
	newForm.href = "/admin/board/admin_board/form";
	newForm.textContent = "새로 등록";
	newForm.className = "btn btn_violet"; // 버튼처럼 보이게
	formBtn.appendChild(newForm);
}

// FAQ 전체
const faqAll = function(type) {
	fetch(`/ajax/admin/board/admin_board/list/BRDD-002`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			flist(type);
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

const pre = function(type){
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then((resp) => resp.json())
		.then((rslt) => {
			bhtml(rslt);
			nlist();
		});
}

// 초기 로딩(메뉴바 고정)
const alist = function(type) {
	if (type === "BRDD-001") {
		fetch(`/ajax/admin/board/admin_board/${type}`)
			.then((resp) => resp.json())
			.then((rslt) => {
				bhtml(rslt);
			});
	} else if (type === "BRDD-003") {
		noticeUser();  // 공지사항 일반(고정)
	} else if (type === "BRDD-002") {
		faqAll();  // FAQ 전체
	}
}

// 상세 이후 로딩
const alist2 = function(type){
	if (type === "BRDD-001") {
		fetch(`/ajax/admin/board/admin_board/${type}`)
			.then((resp) => resp.json())
			.then((rslt) => {
				bhtml(rslt);
			});
	}else if (type.startsWith('UFAQ') || type.startsWith('CFAQ')) {
		faqAll();
	}else{
		pre(type);
	}
}

alist(type);



