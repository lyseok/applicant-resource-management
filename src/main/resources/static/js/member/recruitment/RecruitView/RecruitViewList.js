

// 초기 랜더링
loadIntroductionList();

// 리스트 불러오기 함수
async function loadIntroductionList(page = 1, keyword = '') {
  try {
    const {data}  = await axios.get('/ajax/recruit_view/list');
    console.log(data);
    

    const totalCount = data.length || 0;
    // const totalPages = data.totalPages || 1;
    document.getElementById('list-count').textContent = totalCount;
    renderIntroductionList(data);
    // renderPagination(page, totalPages, keyword);

  } catch (e) {
    console.log(e);
    document.getElementById('interviewListArea').innerHTML = `
      <div class="interviewList p-6 d-flex flex-column align-items-center gap-2">
        <span>최근 본 공고가 없어요!</span>
        <a class="fw-bold d-flex" href="/write/new">
          입사 지원하러 가기
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
  area.innerHTML = list.map(data => `
    <ul>
      <li class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center" data-interview="${data.INTR_INTERVIEW_NO}">
        <div>
        	<div>${data.comName}</div> 
          <a class="d-block h4 fw-bold" href="/recruit_notice/${data.recruitmentNo}">${data.recruitmentTitle}</a>
          <div>
          	<div>
          		<span class="material-symbols-outlined">distance</span>
          		<span class="fs-16 ">${data.cityCodeName || ''} ${data.districtCodeName || ''}</span>
          		</div>
          	<div><span class="fs-16 ">${data.recruitmentSalary || ''}</span></div>
          	<div><span class="fs-16 ">${data.welfare || ''}</span></div>
          	<div><span class="fs-16 ">${data.welfare || ''}</span></div>          	
          </div>
          <div>
          	<span class="fs-16 text-body-tertiary">${data.viewAt}</span>
          </div>
        </div>
        <div class="d-flex flex-column align-items-center gap-2 " >
          <a class="btn btn_red_line review-btn" href="/mypage/interview/review/write/' + data.INTR_INTERVIEW_NO">
            삭제
          </a>
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
