let type = document.querySelector("#typeHidden").value;
const aboardList = document.querySelector("#aboardList");
const memTypeBtn = document.querySelector("#memTypeBtn");
const formBtn = document.querySelector("#formBtn");
const detTitle = document.querySelector("#detTitle");
const listTitle = document.querySelector("#listTitle");
const allBtns = document.querySelector("#allBtns");
const userId = document.querySelector("#userIdHidden")?.value;
const aboardform = document.querySelector("#aboardForm");
const modalElement = document.querySelector('#deleteModal');

// ✅ 게시글 상세 제목 설정
const pageTitle = function () {
	detTitle.innerHTML = "";
	let title = document.createElement("p");
	title.className = "h1 mb-3 fw-bold";
	title.textContent = "게시글 상세";
	detTitle.appendChild(title);
};

// ✅ 새 글 등록 버튼 생성
const newFormBtn = function () {
	formBtn.innerHTML = "";
	let newForm = document.createElement("button");
	newForm.id = "formForm";
	newForm.className = "btn btn_violet";
	newForm.textContent = "새 글 등록";
	formBtn.appendChild(newForm);

	const formForm = document.querySelector("#formForm");
	if (formForm != null) {
		formForm.onclick = function () {
			memTypeBtn.innerHTML = "";
			aboardList.innerHTML = "";
			listTitle.style.display = "none";
			formBtn.innerHTML = "";
			addopt();
		};
	}
};

// ✅ 탭 UI 활성화 처리
function setActiveTab(e) {
	if (!e || !e.target) return;

	const parent = e.target.closest("ul");
	if (!parent) return;

	const links = parent.querySelectorAll(".nav-link");
	links.forEach(link => link.classList.remove("active"));

	e.target.classList.add("active");
}

// ✅ 카드 UI 렌더링
const bhtml = function (rslt) {
	listTitle.style.display = "block";
	aboardform.style.display = "none";

	let html = '<div class="list_body">';
	rslt.forEach((item) => {
		html += `
			<div class="list_item mb-3">
				<div class="box_item p-3 border rounded">
					<div class="row align-items-center">
						<div class="col">
							<a href="javascript:void(0)" class="fw-bold fs-5 text-decoration-none" onclick="abno('${item.boardNo}')">
								${item.boardTitle}
							</a>
						</div>
					</div>
				</div>
			</div>
		`;
	});
	html += '</div>';

	let isthere = rslt.some(item => item.boardTypeCode !== 'BRDD-001');
	if (isthere) newFormBtn();

	allBtns.innerHTML = '';
	aboardList.innerHTML = html;
};

// ✅ 문의사항 탭 렌더링
const asklist = function (type, activeTab = 'all') {
	let html = `
		<p class="h4">문의사항 탭 선택</p>
		<ul class="nav nav-underline" id="askTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="askUser('${type}', event)">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="#" onclick="askCorp('${type}', event)">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'all' ? 'active' : ''}" href="#" onclick="askAll('${type}', event)">전체</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ FAQ 탭 렌더링
const flist = function (activeTab = 'all') {
	let html = `
		<p class="h4">자주 묻는 질문 탭 선택</p>
		<ul class="nav nav-underline" id="faqTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="faqUser(event)">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="#" onclick="faqCorp(event)">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'all' ? 'active' : ''}" href="#" onclick="faqAll(event)">전체</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ 공지사항 탭 렌더링
const nlist = function (activeTab = 'user') {
	let html = `
		<p class="h4">공지사항 탭 선택</p>
		<ul class="nav nav-underline" id="noticeTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="noticeUser(event)">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="#" onclick="noticeCorp(event)">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'event' ? 'active' : ''}" href="#" onclick="noticeEvent(event)">이벤트</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ 문의사항 데이터
const askAll = function(type, event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'all');
		});
};

const askUser = function(type, event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_USER`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'user');
		});
};

const askCorp = function(type, event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_COMPANY`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'corp');
		});
};

// ✅ FAQ 데이터
const faqAll = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/list/BRDD-002`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('all');
		});
};

const faqUser = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/pre/UFAQ`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('user');
		});
};

const faqCorp = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/pre/CFAQ`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('corp');
		});
};

// ✅ 공지사항 데이터
const noticeUser = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/pre/UNTC`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist('user');
		});
};

const noticeCorp = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/pre/CNTC`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist('corp');
		});
};

const noticeEvent = function(event) {
	if (event) setActiveTab(event);
	fetch(`/ajax/admin/board/admin_board/pre/ENTC`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist('event');
		});
};

// ✅ 단일 타입 프리로드
const pre = function(type) {
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist();
		});
};

// ✅ 초기 로딩
const alist = function(type) {
	if (type === "BRDD-001") {
		askAll(type);
	} else if (type === "BRDD-003") {
		noticeUser();
	} else if (type === "BRDD-002") {
		faqAll();
	}
};

// ✅ 상세 이후 복귀
const alist2 = function(type) {
	if (type === "BRDD-001") {
		askAll(type);
	} else if (type.startsWith('UFAQ') || type.startsWith('CFAQ')) {
		faqAll();
	} else {
		pre(type);
	}
};

alist(type);
