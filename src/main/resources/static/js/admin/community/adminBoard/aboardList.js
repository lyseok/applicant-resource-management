/**
 * 
 */
let type = document.querySelector("#typeHidden").value;
const aboardList = document.querySelector("#aboardList");
const memTypeBtn = document.querySelector("#memTypeBtn");
const formBtn = document.querySelector("#formBtn");
const detTitle = document.querySelector("#detTitle");
const listTitle = document.querySelector("#listTitle");
const allBtns = document.querySelector("#allBtns");
window.userId = document.querySelector("#userIdHidden")?.value;
const aboardform = document.querySelector("#aboardForm");
const modalElement = document.querySelector('#deleteModal');
const TypoBox_searchBar = document.querySelector('.TypoBox.searchBar');

//==================== 필터링 및 페이징 ===============================================

// 0. 페이지 데이터
const params = {
  page: 1,       // 시작 페이지, 
  pageSize: 10,  // 리스트에 몇개씩 보여줄건지
};

// 1. 출력할 리스트 가져오기
function fetchData(type, activeTab) {

  const paramsString = paramsSerializer(params);
  console.log('필터링 요청:', paramsString);

  setActiveTab(activeTab);  //클릭한 탭(user, corp, event)에 맞게 넘어옴
  console.log("setActiveTAb?", activeTab);

  axios
    .get(`/ajax/admin/board/admin_board/${type}/notice-page?` + paramsString)
    .then((res) => {
      const resp = res.data;
      console.log("resp?", resp);
      bhtml(resp.data);
	  nlist(activeTab);  //클릭한 탭(user, corp, event)이 들어옴

      totalPage = Math.ceil(resp.totalCnt / params.pageSize);
      console.log(totalPage, params.page);
      renderPager(totalPage, params.page, activeTab); // 페이저 렌더링
      console.log("renderPager?", activeTab);
    })
    .catch((err) => {
      alert('데이터를 불러오는 데 실패했습니다.');
      console.log("현정 에렁",err);
    })
}

// 2. 가져온 리스트에 페이저 찍기
function renderPager(totalPages, page, activeTab) {
	console.log("activeTab?", activeTab);
	setActiveTab(activeTab);
  let pagerHtml = '';
  for (let i = 1; i <= totalPages; i++) {
    if (i === page) {  //<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="noticeUser('UNTC')">
      pagerHtml += `<span class="BtnType SizeS active" data-tab="${activeTab}">${i}</span>`;
    } else {
      pagerHtml += `<button class="BtnType SizeS page" data-page="${i}" data-tab="${activeTab}">${i}</button>`;
    }
  }
  if (page < totalPages) {
    pagerHtml += `<button data-page="${
      page + 1
    }" class="BtnType SizeS BtnNext btnNext" data-tab="${activeTab}">다음</button>`;
  }
  document.querySelector('.PageBox').innerHTML = pagerHtml;
}

// 페이지 숫자 클릭
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page') || e.target.classList.contains('BtnNext')) {
    let page = e.target.classList.contains('page')
      ? Number(e.target.dataset.page)
      : params.page + 1;

    let activeTab = e.target.dataset.tab; // 버튼에 저장된 탭 값
    params.page = page;

    // 탭 활성화 처리
    document.querySelectorAll('#noticeTabs .nav-link').forEach(link => link.classList.remove('active'));
    document.querySelector(`#noticeTabs .nav-link.${activeTab}`)?.classList.add('active');

    // 데이터 로드
    fetchData(type, activeTab, params.page, params.pageSize);
  }
});

/*
// 페이지 숫자 클릭
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    let page = Number(e.target.dataset.page);
    let activeTab = e.target.dataset.tab; // ← 버튼에 저장된 탭 값 가져오기
    params.page = page;

    // 탭 활성화는 따로 처리 (버튼 클릭이니까 setActiveTab은 직접 클래스만 바꿔주는 걸로)
    document.querySelectorAll('#noticeTabs .nav-link').forEach(link => link.classList.remove('active'));
    document.querySelector(`#noticeTabs .nav-link.${activeTab}`)?.classList.add('active');

    fetchData(type, activeTab, params.page, params.pageSize);  //noticeUser()등에서 type 부여
  } else if (e.target.classList.contains('BtnNext')) {
    params.page += 1;
    let activeTab = e.target.dataset.tab; // ← 버튼에 저장된 탭 값 가져오기
    
    // 탭 활성화는 따로 처리 (버튼 클릭이니까 setActiveTab은 직접 클래스만 바꿔주는 걸로)
    document.querySelectorAll('#noticeTabs .nav-link').forEach(link => link.classList.remove('active'));
    document.querySelector(`#noticeTabs .nav-link.${activeTab}`)?.classList.add('active');
    
    fetchData(type, activeTab, params.page, params.pageSize);
  }
  // 필요시 이전(Prev) 버튼도 처리
});
*/

// 페이지 쿼리스트링에 검색 필터링 쿼리스트링 더하기
const paramsSerializer = function (params) {
  const query = [];
  for (const key in params) {
    const value = params[key];
    if (Array.isArray(value)) {
      value.forEach((v) =>
        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(v))
      );
    } else if (value !== null && value !== undefined) {
      query.push(encodeURIComponent(key) + '=' + encodeURIComponent(value));
    }
  }
  return query.join('&');
};

//=============== 공통 함수 =================================================================

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
	console.log("e?", e);
	
	if (!e || !e.target) return;

	const parent = e.target.closest("ul");
	if (!parent) return;

	const links = parent.querySelectorAll(".nav-link");
	links.forEach(link => link.classList.remove("active"));

	e.target.classList.add("active");
}

// ✅ 관리자게시판 목록 UI
const bhtml = function (rslt) {
	console.log("체킁 bhtml",rslt);
	listTitle.style.display = "block";
	aboardform.style.display = "none";
	TypoBox_searchBar.style.display = "block";

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

//=========================== 유형별 함수 ==================================================================================

//==========!공지!=================================================================================

// ✅ 공지사항 탭 렌더링
const nlist = function (activeTab) {
	let html = `
		<p class="h4">공지사항 탭 선택</p>
		<ul class="nav nav-underline" id="noticeTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="javascript:void(0)" onclick="noticeUser('UNTC')">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="javascript:void(0)" onclick="noticeCorp('CNTC')">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'event' ? 'active' : ''}" href="javascript:void(0)" onclick="noticeEvent('ENTC')">이벤트</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ 공지사항 일반회원 데이터
const noticeUser = function(type) {
	setActiveTab('user');
	fetchData(type, 'user');
};

// ✅ 공지사항 기업회원 데이터
const noticeCorp = function(type) {
	setActiveTab('corp');
	fetchData(type, 'corp');
};

// ✅ 공지사항 이벤트 데이터
const noticeEvent = function(type) {
	setActiveTab('event');
	fetchData(type, 'event');
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

//==========!자주묻는질문!============================================

// ✅ FAQ 탭 렌더링
const flist = function (activeTab = 'all') {
	//fetchData(type);
	let html = `
		<p class="h4">자주 묻는 질문 탭 선택</p>
		<ul class="nav nav-underline" id="faqTabs" style="display: flex; gap: 12px;">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'all' ? 'active' : ''}" href="#" onclick="faqAll()">전체</a>
			</li>

			<li class="nav-item dropdown" id="userFaqDropdown">
				<a class="nav-link dropdown-toggle ${activeTab === 'user' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" role="button" aria-expanded="false">
					일반회원
				</a>
				<ul class="dropdown-menu" id="userFaqDropdownMenu">
					<li><a class="dropdown-item" href="#" onclick="faqUser()">전체</a></li>
					<li><hr class="dropdown-divider"></li>
				</ul>
			</li>

			<li class="nav-item dropdown" id="corpFaqDropdown">
				<a class="nav-link dropdown-toggle ${activeTab === 'corp' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" role="button" aria-expanded="false">
					기업회원
				</a>
				<ul class="dropdown-menu" id="corpFaqDropdownMenu">
					<li><a class="dropdown-item" href="#" onclick="faqCorp()">전체</a></li>
					<li><hr class="dropdown-divider"></li>
				</ul>
			</li>
		</ul>`;

	memTypeBtn.innerHTML = html;

	// ✅ 드롭다운 항목 생성 함수 호출
	loadFaqDropdownItems('UFAQ', 'userFaqDropdownMenu', 'user');
	loadFaqDropdownItems('CFAQ', 'corpFaqDropdownMenu', 'corp');
};

// ✅ 드롭다운 항목을 동적으로 생성하는 함수
const loadFaqDropdownItems = function(groupCode, containerId, userType) {
	fetch(`/ajax/admin/board/admin_board/cmn/${groupCode}`)
		.then(resp => resp.json())
		.then(rslt => {
			const menu = document.querySelector(`#${containerId}`);
			rslt.forEach(item => {
				const li = document.createElement("li");
				li.innerHTML = `
					<a class="dropdown-item" href="#" onclick="faqDetail('${item.codeDetailNo}', '${userType}')">
						${item.codeName}
					</a>`;
				menu.appendChild(li);
			});
		});
};

// ✅ 자주묻는 질문 상세유형 데이터
const faqDetail = function(codeDetailNo, userType = 'all') {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${codeDetailNo}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist(userType);
		});
};

// ✅ 자주묻는질문 전체 데이터
const faqAll = function() {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/list/BRDD-002`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('all');
		});
};

// ✅ 자주묻는질문 일반회원 데이터
const faqUser = function() {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/UFAQ`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('user');
		});
};

// ✅ 자주묻는질문 기업회원 데이터
const faqCorp = function() {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/CFAQ`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('corp');
		});
};

//============!문의사항!==================================================================

// ✅ 문의사항 탭 렌더링
const asklist = function (type, activeTab = 'all') {
	let html = `
		<p class="h4">문의사항 탭 선택</p>
		<ul class="nav nav-underline" id="askTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="askUser('${type}')">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="#" onclick="askCorp('${type}')">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'all' ? 'active' : ''}" href="#" onclick="askAll('${type}')">전체</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ 문의사항 전체 데이터
const askAll = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'all');
		});
};

// ✅ 문의사항 일반회원 데이터
const askUser = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_USER`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'user');
		});
};

// ✅ 문의사항 기업회원 데이터
const askCorp = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_COMPANY`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'corp');
		});
};


//================================ 리스트 자체 호출 ====================================== 

// ✅ 초기 로딩
const alist = function(type) {
	if (type === "BRDD-001") {
		askAll(type);
	} else if (type === "BRDD-003") {
		noticeUser('UNTC');  //처음엔 일반회원으로 가게
	} else if (type === "BRDD-002") {
		faqAll();
	}
};

// ✅ 상세보기에서 목록 클릭시 로딩
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

