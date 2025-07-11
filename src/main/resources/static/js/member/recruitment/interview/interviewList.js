
// 초기 데이터 불러오기
loadIntroductionList();

// 검색 버튼 클릭 시
document.getElementById('searchBtn').onclick = () => {
  loadIntroductionList(1, document.getElementById('listKeyword').value);
};


// 리스트 불러오기 함수
async function loadIntroductionList(page = 1, keyword = '') {
  try {
    const { data } = await axios.get('/ajax/member/mypage/interview/list');

    const interview = data || [];
    const recruitProcess = interview.recruitProcess;
    const applicantRecordList = recruitProcess.applicantRecordList;
    const recruitmentNotice = recruitProcess.recruitmentNotice;

    const totalCount = data.totalCount || 0;
    const totalPages = data.totalPages || 1;
    document.getElementById('list-count').textContent = totalCount;
    console.log(applicantRecordList);
    renderIntroductionList(applicantRecordList);
    renderPagination(page, totalPages, keyword);

  } catch (e) {
    document.getElementById('introductionListArea').innerHTML = `
      <div class="intoructionList p-6 d-flex flex-column align-items-center gap-2">
        <span>띹잡에 예정된 면접이 없어요!</span>
        <a class="fw-bold d-flex" href="/write/new">
          자소서 등록하러 가기
          <span class="material-symbols-outlined">chevron_right</span>
        </a>
      </div>
    `;
    document.getElementById('pageBox').innerHTML = '';
  }
}

// 리스트 DOM 렌더 함수
function renderIntroductionList(list) {
  const area = document.getElementById('interviewListArea');
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
  area.innerHTML = list.map(applicantRecord => `
    <ul>
      <li class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
        <div>
          <a class="d-block h4 fw-bold" href="/mypage/introduction/${applicantRecord.introductionNo}">${applicantRecord.introductionName}</a>
          <p class="text-truncate w800">${applicantRecord.introductionContent}</p>
        </div>
        <div class="d-flex gap-1">
          <a class="btn btn_violet_line fw-normal" href="/mypage/introduction/edit/${applicantRecord.introductionNo}">수정</a>
          <a class="btn btn_red_line fw-normal" href="/mypage/introduction/delete/${applicantRecord.introductionNo}" onclick="return confirmDelete();">삭제</a>
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
    btn.onclick = () => loadIntroductionList(Number(btn.dataset.page), keyword);
  });
}

// 삭제 컨펌 함수
function confirmDelete() {
  return confirm('정말 삭제하시겠습니까?');
}