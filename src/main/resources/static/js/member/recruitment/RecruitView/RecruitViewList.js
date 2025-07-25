

// 초기 랜더링
loadRecruitViewList();
initSearchButton()

// 리스트 불러오기 함수
// 1. 공통 fetch 함수
async function fetchRecuitViewList(url) {
  try {
    const response = await fetch(url);
    if (!response.ok) throw new Error('네트워크 응답 오류');
    const data = await response.json();
    return data;
  } catch (e) {
    console.error('[fetchRecuitViewList] 오류:', e);
    return null;
  }
}

// 📌 2. 공통 렌더링 함수
function renderRecuitViewData(data) {
  if (!data || data.length === 0) {
    document.getElementById('recruitViewListArea').innerHTML = `
      <div class="interviewList p-6 d-flex flex-column align-items-center gap-2">
        <span>최근 본 공고가 없어요!</span>
        <a class="fw-bold d-flex" href="/write/new">
          띹잡 공고 보러가기
          <span class="material-symbols-outlined">chevron_right</span>
        </a>
      </div>
    `;
    document.getElementById('pageBox').innerHTML = '';
    return;
  }

  const totalCount = data.length || 0;
  document.getElementById('list-count').textContent = totalCount;
  renderIntroductionList(data); // <- 기존 리스트 렌더링 함수
  // renderPagination(...); // 필요 시 페이징도 넣기
}



// 리스트 DOM 렌더 함수
function renderIntroductionList(list) {
  const area = document.getElementById('recruitViewListArea');
  if (list.length === 0) {
    area.innerHTML = `
      <div class="p-6 d-flex flex-column align-items-center gap-2">
        <span>띹잡에 예정된 면접이 없어요!</span>
        <a class="fw-bold d-flex" href="/write/new">
          입사 지원하러 가기
          <span class="material-symbols-outlined">chevron_right</span>
        </a>
      </div>
    `;
    return;
  }
  area.innerHTML = list.map(data => `
    <ul>
      <li class="p-4 border-bottom d-flex justify-content-between align-items-center recruit_list gap-5" data-interview="${data.INTR_INTERVIEW_NO}">
        <div class="d-flex flex-fill align-items-start">
					<div class="d-flex align-items-center viewat_box">
						<span class="material-symbols-outlined">history</span>
						<span class="viewAt">${data.viewAt}시간 전</span>
					</div>
					<div class="recruit_tit"> 
						<h6 class="recruit_comName mb-2 text-muted">${data.comName}</h6>
          	<h5 class="d-block fs16 fw-bold m-0">${data.recruitmentTitle}</h5>
					</div>
          <div class="recruit_info">
          	<div class="d-flex align-items-center">
          		<span class="material-symbols-outlined">distance</span>
          		<span class="">${data.cityCodeName || ''} ${data.districtCodeName || ''}</span>
        		</div>
						<div class="d-flex align-items-center">
							<span class="material-symbols-outlined">money_bag</span>
							<span class="num_line">${data.recruitmentSalary === '0' ?  '협의 후 결정':(data.recruitmentSalary + '만원' ?? '')}</span>
						</div>
						<div class="d-flex align-items-center">
							<span class="material-symbols-outlined">business_center</span>
							<span class="num_line">${data.yearCodeName || ''}</span>
						</div>      	
          </div>
        </div>
        <div class="d-flex flex-column align-items-center gap-2 " >
        ${
          data.recruitmentFinishDate > 0 
            ? `<a class="btn btn_violet review-btn w140 justify-content-center fw-light fs-14" href="/recruit_notice/${data.recruitmentNo}">공고보기</a>`
            : `<a class="btn btn_violet review-btn w140 justify-content-center fw-light fs-14 disabled" href="javascript:void(0)">공고마감</a>`
        }
					<div class="text-end w-100 ">
						<span class="fs-12 text-muted">${data.recruitmentFinishDate > 0 ? "D-"+data.recruitmentFinishDate : "접수마감"}</span>
					</div>
        </div>
      </li>
    </ul>
  `).join('');

}


// 페이지네이션 렌더 함수 (간단 예시, 필요시 확장)
function renderPagination(current, total, keyword) {
  let html = '';
  for (let i = 1; i <= total; i++) {
    if (i === current) {
      html += `<span class="BtnType SizeS active">${i}</span>`;
    } else {
      html += `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
    }
  }
  document.getElementById('pageBox').innerHTML = html;
  // 페이지 버튼 이벤트
  document.querySelectorAll('#pageBox .page').forEach(btn => {
    btn.onclick = () => loadRecruitViewList(Number(btn.dataset.page), keyword);
  });
}

async function loadRecruitViewList() {
  const data = await fetchRecuitViewList('/ajax/recruit_view/list');
  renderRecuitViewData(data);
}

async function loadSearchRecruitViewList(keyword) {
  const data = await fetchRecuitViewList(`/ajax/recruit_view/list/${keyword}`);
  renderRecuitViewData(data);
}

function initSearchButton() {
  document.querySelector('.searchBarBtn').addEventListener('click', function (e) {
    e.preventDefault();
    const keyword = document.getElementById('listKeyword').value.trim();
    if (keyword) {
      loadSearchRecruitViewList(keyword); // ✅ keyword 전달
    } else {
      loadRecruitViewList(); // ✅ 검색어 없을 때 전체 리스트
    }
  });
}
