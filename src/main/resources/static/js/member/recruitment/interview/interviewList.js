let selectedApplicantRecordNo = null; // 전역 변수

// 초기 데이터 불러오기
loadIntroductionList();

// 검색 버튼 클릭 시
// document.getElementById('searchBtn').onclick = () => {
//   loadIntroductionList(1, document.getElementById('listKeyword').value);
// };


// 리스트 불러오기 함수
async function loadIntroductionList(page = 1, keyword = '') {
  try {
    const { data } = await axios.get('/ajax/member/mypage/interview/list');
    console.log(data);

    // const totalCount = data.totalCount || 0;
    // const totalPages = data.totalPages || 1;
    // document.getElementById('list-count').textContent = totalCount;
    renderIntroductionList(data);
    // renderPagination(page, totalPages, keyword);

  } catch (e) {
    console.log(e);
    document.getElementById('interviewListArea').innerHTML = `
      <div class="interviewList p-6 d-flex flex-column align-items-center gap-2">
        <span>띹잡에 예정된 면접이 없어요!</span>
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
          <p class="d-block h4 fw-bold">${data.RENO_RECRUITMENT_TITLE}</p>
          <p class="text-truncate w800">${data.RENO_REC_CONTENT}</p>
          <p class="text-truncate w800">${data.INTR_INTERVIEW_TYPE === 'Y' ? '화상면접' : '대면면접'}</p>
          <p class="text-truncate w800">면접일시 : ${formatIsoDateToDatetime(data.INTR_INTERVIEW_DATE)}</p>
          ${data.INTR_INTERVIEW_TYPE === 'Y'
            ? `<p class="text-truncate w800">화상면접일시 : ${formatIsoDateToDatetime(data.APRE_EVALUATION_START_TIME)}</p>`
            : ''
          }
        </div>
        <div class="d-flex gap-1">
          ${data.INTR_INTERVIEW_TYPE === 'Y'
            ? `<a class="btn btn_violet_line fw-normal" href="/mypage/introduction/edit/${data.INTR_INTERVIEW_NO}" data-record-no="${data.APRE_APPLICANT_RECORD_NO}" data-bs-toggle="modal" data-bs-target="#joinInterviewModal">면접 접속</a>`
            : ''
          }
        </div>
      </li>
    </ul>
  `).join('');

  // li 클릭시 상세 페이지 이동 (면접 상세)
  area.querySelectorAll('li[data-interview]').forEach(li => {
    li.addEventListener('click', function() {
      const interviewNo = this.dataset.interview;
      location.href = `/mypage/interview/detail?interviewNo=${interviewNo}`;
    });
  });

  document.querySelectorAll('.btn_violet_line[data-record-no]').forEach(btn => {
    btn.onclick = function (e) {
      e.stopPropagation(); // li 클릭 이벤트와 분리
      selectedApplicantRecordNo = this.dataset.recordNo; // 전역변수에 저장
      console.log(selectedApplicantRecordNo);
    };
  });
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

function formatIsoDateToDatetime(isoString) {
  if (!isoString) return '';
  // 브라우저 Date 객체로 변환
  const date = new Date(isoString);
  const pad = n => n < 10 ? '0' + n : n;
  const year = date.getFullYear();
  const month = pad(date.getMonth() + 1);
  const day = pad(date.getDate());
  const hour = pad(date.getHours());
  const min = pad(date.getMinutes());
  const sec = pad(date.getSeconds());
  return `${year}-${month}-${day} ${hour}:${min}:${sec}`;
}

document.getElementById('startInterviewBtn').onclick = async function() {
  // 1. 비동기 요청해서 "화상면접" 주소로 새 창 먼저 띄우기
  let interviewPopup;
  try {
    const res = await axios.get('/ajax/member/video_interview/' + selectedApplicantRecordNo);
    const url = res.data;
    if (url) {
      interviewPopup = window.open(url, '_blank'); // 새 창으로 열기

      const modalEl = document.getElementById('joinInterviewModal'); // 닫을 모달의 id
    } else {
      alert('화상면접 주소를 찾을 수 없습니다.');
    }
  } catch (e) {
    alert('화상면접 접속 주소를 불러오는 데 실패했습니다.');
    return;
  }

  // 2. 1초 뒤에 평가 팝업 띄우기
};

