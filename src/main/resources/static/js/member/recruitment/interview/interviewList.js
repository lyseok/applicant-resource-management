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
    

    const totalCount = data.length || 0;
    // const totalPages = data.totalPages || 1;
    document.getElementById('list-count').textContent = totalCount;
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

// 빈 html 코드 제거
function cleanContent(html) {
  if (!html) return '';
  const trimmed = html.replace(/\s/g, '').toLowerCase();
  return (trimmed === '<p><br></p>' || trimmed === '<p></p>' || trimmed === '<p>&nbsp;</p>') ? '' : html;
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
  area.innerHTML = list.map(data => {
		const content = cleanContent(data.RENO_REC_CONTENT);
		/* ${content != null? `<div class="text-muted mt-2">${content}</div>` : ''} */
		return `
    <ul>
      <li class="py-4 border-bottom d-flex justify-content-between align-items-center" data-interview="${data.INTR_INTERVIEW_NO}">
        <div>
					<p class="text-truncate mb-2">
						<b>${
						  data.INTR_INTERVIEW_TYPE === 'Y' ? '화상면접' : '대면면접'
						}
						</b>
						/
						${
						  data.APRE_EVALUATION_START_TIME
						    ? formatIsoDateToDatetime(data.APRE_EVALUATION_START_TIME)
						    : formatIsoDateToDatetime(data.INTR_INTERVIEW_DATE)?
						     formatIsoDateToDatetime(data.INTR_INTERVIEW_DATE)
						     	: '-'
						}
					</p>
          <p class="d-block h4 mb-0 fw-bold">${data.RENO_RECRUITMENT_TITLE}</p>
          
        </div>
        <div class="d-flex flex-column align-items-center gap-2 w140">
				${data.APRE_STEP_APPLICATION_YN === 'Y'
				  ? `<a class="btn btn_violet_line fw-normal review-btn w-review"
				          href="/mypage/interview/review/write?intr_interview_no=${data.INTR_INTERVIEW_NO}"
				          data-record-no="${data.APRE_APPLICANT_RECORD_NO}">
				        면접 리뷰작성
				     </a>`
				  : (data.INTR_INTERVIEW_TYPE === 'Y'
				      ? `<a class="btn btn_violet_line fw-normal w-review" 
				             href="/mypage/introduction/edit/${data.INTR_INTERVIEW_NO}" 
				             data-record-no="${data.APRE_APPLICANT_RECORD_NO}" 
				             data-bs-toggle="modal" data-bs-target="#joinInterviewModal">
				           면접 접속
				         </a>`
				      : `
							<div class="d-block text-center lh1-2">
								<span class="material-symbols-outlined fw-300 d-block mb-2 text-violet60 fs-5">hourglass_empty</span>
								<span class="fs-14 text-black-50 fw-500">기업의 면접평가<br> 작성 대기중</span>
							</div>
					`)
				}

        </div>
      </li>
    </ul>
  `;
	}).join('');

  // 리뷰작성 버튼 클릭 제어
  document.querySelectorAll('.review-btn').forEach(btn => {
    btn.onclick = function (e) {
      if (btn.classList.contains('disabled')) {
        e.preventDefault();
        e.stopPropagation();
        return false;
      }
      // (활성화 상태에서만) 필요시 추가 이벤트 처리
    };
  });

  document.querySelectorAll('.btn_violet_line[data-record-no][data-bs-target="#joinInterviewModal"]').forEach(btn => {
    btn.onclick = function (e) {
      e.stopPropagation();
      selectedApplicantRecordNo = this.dataset.recordNo;
      // (필요시 추가 이벤트)
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
     // 오류일 때 메시지 보여주기
    const msg = e?.response?.data?.message || '알 수 없는 오류가 발생했습니다.';
    document.getElementById('interviewAlertMsg').innerText = msg;
    document.getElementById('interviewAlert').style.display = 'block';

    // 모달 닫기 (부트스트랩 Modal 인스턴스 사용)
    const modalEl = document.getElementById('joinInterviewModal');
    if (modalEl) {
      const modal = bootstrap.Modal.getInstance(modalEl);
      if (modal) modal.hide();
    }
    return;
  }
};




