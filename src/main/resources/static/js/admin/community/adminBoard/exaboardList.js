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
/*
const params = {
  page: 1,       // 시작 페이지, 
  pageSize: 10,  // 리스트에 몇개씩 보여줄건지
};

// 1. 출력할 리스트 가져오기
function fetchData() {
  const paramsString = paramsSerializer(params);
  console.log('필터링 요청:', paramsString);

  axios
    .get(`/ajax/admin/board/admin_board/${type}/page?` + paramsString)
    .then((res) => {
      const resp = res.data;
      console.log('응답:', resp);

      // 게시글 목록 렌더링
      bhtml(resp.data);

      // 페이징 처리
      const totalPage = Math.ceil(resp.totalCnt / params.pageSize);
      renderPager(totalPage, params.page);
      
    })
    .catch((err) => {
      console.error(err);
      alert('데이터를 불러오는 데 실패했습니다.');
    })
    .finally(() => {
    });
}

// 2. 가져온 리스트에 페이저 찍기
function renderPager(totalPages, page) {
  let pagerHtml = '';
  for (let i = 1; i <= totalPages; i++) {
    if (i === page) {
      pagerHtml += `<span class="BtnType SizeS active">${i}</span>`;
    } else {
      pagerHtml += `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
    }
  }
  if (page < totalPages) {
    pagerHtml += `<button data-page="${page + 1}" 
    class="BtnType SizeS BtnNext btnNext">다음</button>`;
  }
  document.querySelector('.PageBox').innerHTML = pagerHtml;
}

// 페이저 클릭 (이벤트 위임)
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    const page = Number(e.target.dataset.page);
    params.page = page;
    fetchData(params.page, params.pageSize);
  } else if (e.target.classList.contains('BtnNext')) {
    params.page += 1;
    fetchData(params.page, params.pageSize);
  }
  // 필요시 이전(Prev) 버튼도 처리
});

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
*/

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

// ✅ 드롭다운 항목을 동적으로 생성하는 함수
const loadFaqDropdownItems = function(groupCode, containerId, userType) {
	console.log("groupCode, containerId, userType", groupCode, containerId, userType);
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

// ✅ 상세 유형 FAQ 조회
const faqDetail = function(codeDetailNo, userType = 'all') {
	console.log("codeDetailNo?", codeDetailNo, "userType?", userType);
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${codeDetailNo}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist(codeDetailNo, userType); // 클릭된 탭 유지
		});
};

// ✅ FAQ 탭 렌더링
const flist = function (type, activeTab = 'all') {
	console.log("type?", type);
	let html = `
		<p class="h4">자주 묻는 질문 탭 선택</p>
		<ul class="nav nav-underline" id="faqTabs" style="display: flex; gap: 12px;">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'all' ? 'active' : ''}" href="#" onclick="faqAll('${type}')">전체</a>
			</li>

			<li class="nav-item dropdown" id="userFaqDropdown">
				<a class="nav-link dropdown-toggle ${activeTab === 'user' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" role="button" aria-expanded="false">
					일반회원
				</a>
				<ul class="dropdown-menu" id="userFaqDropdownMenu">
					<li><a class="dropdown-item" href="#" onclick="faqUser('UFAQ')">전체</a></li>
					<li><hr class="dropdown-divider"></li>
				</ul>
			</li>

			<li class="nav-item dropdown" id="corpFaqDropdown">
				<a class="nav-link dropdown-toggle ${activeTab === 'corp' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" role="button" aria-expanded="false">
					기업회원
				</a>
				<ul class="dropdown-menu" id="corpFaqDropdownMenu">
					<li><a class="dropdown-item" href="#" onclick="faqCorp('CFAQ')">전체</a></li>
					<li><hr class="dropdown-divider"></li>
				</ul>
			</li>
		</ul>`;

	memTypeBtn.innerHTML = html;

	// ✅ 드롭다운 항목 생성 함수 호출
	loadFaqDropdownItems('BRDD', 'allFaqDropdownMenu', 'all');
	loadFaqDropdownItems('UFAQ', 'userFaqDropdownMenu', 'user');
	loadFaqDropdownItems('CFAQ', 'corpFaqDropdownMenu', 'corp');
};

// ✅ 공지사항 탭 렌더링
const nlist = function (activeTab = 'user') {
	let html = `
		<p class="h4">공지사항 탭 선택</p>
		<ul class="nav nav-underline" id="noticeTabs">
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'user' ? 'active' : ''}" href="#" onclick="noticeUser('UNTC')">일반회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'corp' ? 'active' : ''}" href="#" onclick="noticeCorp('CNTC')">기업회원</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${activeTab === 'event' ? 'active' : ''}" href="#" onclick="noticeEvent('ENTC')">이벤트</a>
			</li>
		</ul>`;
	memTypeBtn.innerHTML = html;
};

// ✅ 문의사항 데이터
const askAll = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'all');
		});
};

const askUser = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_USER`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'user');
		});
};

const askCorp = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/${type}?userRole=ROLE_COMPANY`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			asklist(type, 'corp');
		});
};

// ✅ FAQ 데이터
const faqAll = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/list/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('all');
		});
};

const faqUser = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('user');
		});
};

const faqCorp = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			flist('corp');
		});
};

// ✅ 공지사항 데이터
const noticeUser = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist('user');
		});
};

const noticeCorp = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
		.then(resp => resp.json())
		.then(rslt => {
			bhtml(rslt);
			nlist('corp');
		});
};

const noticeEvent = function(type) {
	setActiveTab();
	fetch(`/ajax/admin/board/admin_board/pre/${type}`)
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
