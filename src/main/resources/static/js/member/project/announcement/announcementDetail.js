// 모달 데이터(MOCK)
const mockResumes = [
  {
    id: '1',
    title: '이훈석의 이력서 입니다',
    createdAt: '2025.06.04 (수) 19:57 작성',
    hasAttachment: false,
  },
  {
    id: '2',
    title: '프론트엔드 개발자 이력서',
    createdAt: '2025.05.15 (월) 14:30 작성',
    hasAttachment: true,
  },
  {
    id: '3',
    title: '백엔드 개발자 포트폴리오',
    createdAt: '2025.04.20 (목) 10:15 작성',
    hasAttachment: true,
  },
];
const applicationFields = ['기획', '디자인', '프론트엔드'];

let selectedField = '';
let selectedResume = mockResumes[0];

const selectField = document.getElementById('selectField');
const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');
const modalProjectTitle = document.getElementById('modalProjectTitle');

function formatTextWithLineBreaks(text) {
  if (!text) return '';
  return text
    .split('\n')
    .map((line) => escapeHtml(line))
    .join('<br/>');
}

// 간단한 HTML 이스케이프 처리 (보안용)
function escapeHtml(unsafe) {
  return unsafe
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

// 필드 옵션 채우기
selectField.innerHTML += applicationFields
  .map((field) => `<option value="${field}">${field}</option>`)
  .join('');

// 필드 선택
selectField.onchange = function () {
  selectedField = this.value;
};

// 모달 열기용 예시 (프로젝트 제목 넘기기)
function openApplicationModal(title) {
  modalProjectTitle.textContent = title;
  const modal = new bootstrap.Modal(
    document.getElementById('applicationModal')
  );
  modal.show();
}

// ================================ 게시판 랜더링 ================================

let renderData = {};

document.addEventListener('DOMContentLoaded', function () {
  const params = new URLSearchParams(window.location.search);
  const prjAnncNo = params.get('prjAnncNo');

  // renderAnnouncementDetail(renderData);
  axios
    .get('/ajax/board/project/' + prjAnncNo)
    .then((res) => {
      console.log(res); // <- 실제 응답 확인
      renderData = res.data;
      renderAnnouncementDetail(renderData);
    })
    .catch((err) => {
      console.log(err); // <- catch에 들어온 실제 원인 확인
      document.querySelector(
        '.card-body'
      ).innerHTML = `<div class="text-danger py-5">게시글을 불러올 수 없습니다.</div>`;
    });
});

function renderAnnouncementDetail(data) {
  // 상태 변환
  const statusText = data.anncEndYn === 'Y' ? '모집완료' : '모집중';
  const statusClass = data.anncEndYn === 'Y' ? 'opacity-25' : 'badge-recruit';
  data.prjAnncContent = formatTextWithLineBreaks(data.prjAnncContent);
  // 팀 구성 (모집 팀원)
  const teamListHtml = (data.prjRcrtPsncntList || [])
    .filter((r) => !!r.jobCodeName && r.rcrtPsncnt)
    .map(
      (r) =>
        `<li><span class="dot"></span>${r.jobCodeName} - ${r.rcrtPsncnt}명</li>`
    )
    .join('');

  // 모집 중인 역할 강조
  const recruitNow = (data.prjRcrtPsncntList || [])
    .filter((r) => r.jobCodeName === '백엔드')
    .map(
      (r) =>
        `<span class="fw-bold text-dark">${r.jobCodeName} ${r.rcrtPsncnt}명</span>`
    )
    .join(', ');

  // 기술 스택 (태그)
  const techListHtml = (data.prjAnncBoardTagList || [])
    .map((t) =>
      t.tag && t.tag.tagName
        ? `<span class="badge badge-tag">${t.tag.tagName}</span>`
        : ''
    )
    .join('');

  // 내용 삽입
  document.querySelector('.card-body').innerHTML = /* html */ `
    <!-- 제목/상태 -->
    <div class="d-flex align-items-center mb-2 gap-3">
	      <span class="badge ${statusClass} text-white fs-14 py-2 px-4 bg-violet08">${statusText}</span>
	      <h2 class="fw-bold text-dark fs-5">${data.prjEmpTitle}</h2>
    </div>
<script>alert('test');</script>
    <!-- 글 정보 (작성일, 수정일, 조회수) -->
    <div class="d-flex align-items-center gap-5 mb-5 text-secondary small">
      <div class="d-flex gap-2"><b>작성</b><span>${
        data.anncCreateDate.split(' ')[0] || '-'
      }</span></div>
      <!-- <span>수정일 ${data.updateDate || '-'}</span> -->
      <div class="d-flex gap-2"><b>조회</b><span>${data.view || 0}</span></div>
    </div>
    

		<!-- 작업 기간 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">프로젝트 작업 기간</div>
      <div class="d-flex align-items-center gap-2 fs-14 fw-500">
      	<span class="material-symbols-outlined fw-300 text-violet70">calendar_today</span>
	      <span class="">
	      	<!-- 시작일자 -->
	        ${
            data.prjStartPlanDate
              ? `${data.prjStartPlanDate.slice(
                  0,
                  4
                )}-${data.prjStartPlanDate.slice(
                  4,
                  6
                )}-${data.prjStartPlanDate.slice(6, 8)}`
              : '-'
          }
	          ~
          <!-- 마감일자 --> 
	        ${
            data.prjEndPlanDate
              ? `${data.prjEndPlanDate.slice(0, 4)}-${data.prjEndPlanDate.slice(
                  4,
                  6
                )}-${data.prjEndPlanDate.slice(6, 8)}`
              : '-'
          }
	      </span>
      </div>
    </div>
    
    <!-- 프로젝트 소개 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">프로젝트 소개</div>
      <div class="text-secondary fs-16">
        ${data.prjAnncContent || '-'}
      </div>
    </div>
    
    <!-- 팀 구성 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">현재 팀 구성</div>
      <ul class="list-unstyled list-dot mb-2  fs-14">
        ${teamListHtml}
      </ul>
      ${
        recruitNow
          ? `<div class="mt-3 text-secondary">→ 현재 ${recruitNow}을 새로이 모십니다!</div>`
          : ''
      }
    </div>

    <!-- 기술스택 & 업무 내용 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">기술 스택</div>
      <div class="d-flex gap-2 mb-2">${techListHtml}</div>
    </div>

    
    
    <div class="d-flex align-items-center justify-content-between">
	    <!-- 작성자 정보 -->
	    <div class="d-flex align-items-center gap-3">
	    	<div class="profile rounded-circle overflow-hidden mw-28 mh-28">
	    		<img src="${data.memImg || 'https://placehold.co/32x32'}" class="w100p">
	    	</div>
	    	<div class="fw-bold fs-16">${data.memName || '익명'}</div>
	    	
		    <!-- PM 이력서 버튼 -->
		    <div class="d-flex justify-content-center">
		      <button class="btn btn_violet_line btn-sm fs-14 fw-500 fs-13">이력서 확인</button>
		    </div>
	    </div>
    </div>
    <hr />


    <!-- 하단 버튼 -->
    <div class="d-flex justify-content-end gap-2">
      <a href="/board/project" class="btn btn_gray_line">목록</a>
      <button class="btn btn-purple px-5 py-2 btn btn_violet" onclick="openApplicationModal(renderData.prjEmpTitle, renderData.prjRcrtPsncntList)">지원</button>
    </div>
  `;

  const btn = document.getElementById('btnShowPMResume');
  if (btn) {
    btn.onclick = function () {
      openApplicationModal(data.prjEmpTitle || '프로젝트 지원');
    };
  }
}

// ================================ 모달 랜더링 ================================

function openApplicationModal(projectTitle, prjRcrtPsncntList) {
  // 프로젝트 제목
  document.getElementById('modalProjectTitle').textContent = projectTitle;

  // 지원부문 select 채우기
  const select = document.getElementById('selectField');
  select.innerHTML = `<option value="">지원 부문을 선택해주세요</option>`;
  (prjRcrtPsncntList || []).forEach((item) => {
    // 중복 방지용 value로 jobCode 사용 (jobCodeName이 중복일 수 있으니)
    select.innerHTML += `<option value="${item.jobCode}">${item.jobCodeName} (${item.rcrtPsncnt}명)</option>`;
  });

  // 모달 띄우기 (부트스트랩)
  const modal = new bootstrap.Modal(
    document.getElementById('applicationModal')
  );
  modal.show();
}

// ================================ 이력서 랜더링 ================================

let resumeListCache = [];
const resumeListDiv = document.getElementById('resumeList');
// JS파일 로드 시 즉시 호출!
(async function preloadResumeList() {
  try {
    const res = await axios.get(`/ajax/resume`);
    resumeListCache = res.data;
  } catch (err) {
    resumeListCache = [];
    // console.error('이력서 미리 로딩 실패', err);
  }
})();

// ... 아래는 동일하게 사용 가능
btnShowResumeList.onclick = function () {
  if (resumeListDiv.style.display !== 'none') {
    resumeListDiv.style.display = 'none';
    return;
  }

  // 이미 로딩되어 있으므로 바로 사용
  const resumeList = resumeListCache || [];
  selectedResumeCard.style.display = 'none';

  if (!Array.isArray(resumeList) || resumeList.length === 0) {
    resumeListDiv.innerHTML = `<div class="text-secondary py-2">등록된 이력서가 없습니다.</div>`;
    resumeListDiv.style.display = 'block';
    return;
  }

  // 카드 UI 렌더링
  resumeListDiv.innerHTML = resumeList
    .map(
      (resume) => `
    <div class="card mb-2 resume-card ${
      selectedResume && selectedResume.RESUME_NO === resume.RESUME_NO
        ? 'selected-card'
        : ''
    }" data-id="${resume.RESUME_NO}">
      <div class="card-body py-2 px-3">
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p class="mb-1 text-secondary fs-13 fw-500">
              ${resume.UPDATE_DATE ? `${resume.UPDATE_DATE}` : ''}
            </p>
            <h6 class="mb-1 fw-500">${
              resume.RESUME_NAME || resume.RESUME_NO
            }</h6>
            <div class="text-secondary" style="font-size:.96em;">
              ${
                resume.RESUME_MAIN_YN === 'Y'
                  ? `<span class="badge bg-purple">대표 이력서</span>`
                  : ''
              }
              ${
                resume.RESUME_SUBMIT_YN === 'Y'
                  ? `<span class="badge bg-success">제출됨</span>`
                  : ''
              }
            </div>
          </div>
          <div>
            ${
              resume.photo
                ? `<img src="${resume.photo}" alt="증명사진" style="width:38px; height:38px; border-radius:50%;">`
                : ''
            }
          </div>
        </div>
      </div>
    </div>
  `
    )
    .join('');

  // 카드 클릭 이벤트로 선택
  resumeListDiv.querySelectorAll('.resume-card').forEach((card) => {
    card.onclick = function () {
      const rid = this.getAttribute('data-id');
      selectedResume = resumeList.find((r) => r.RESUME_NO === rid);
      renderSelectedResumeCard();
      selectedResumeCard.style.display = 'block';
      resumeListDiv.style.display = 'none';
    };
  });

  resumeListDiv.style.display = 'block';
};

// 이력서 카드(선택된 것) 랜더링 함수
function renderSelectedResumeCard() {
  const div = document.getElementById('selectedResumeCard');
  if (!selectedResume) {
    div.innerHTML = '<span class="text-secondary">이력서를 선택하세요.</span>';
    return;
  }
  div.innerHTML = `
    <div class="card mb-0 selected-card">
      <div class="card-body py-2 px-3">
        <p class="mb-1 text-secondary" style="font-size: .92em;">${
          selectedResume.UPDATE_DATE
            ? `수정일: ${selectedResume.UPDATE_DATE}`
            : ''
        }</p>
        <h6 class="mb-1">${
          selectedResume.RESUME_NAME || selectedResume.RESUME_NO
        }</h6>
      </div>
    </div>
  `;
}

// ================================= 지원 로직 ==================================
btnSaveApplication.onclick = async function () {
  if (!selectedField) {
    alert('지원 부문을 선택해주세요!');
    selectField.focus();
    return;
  }
  if (!selectedResume) {
    alert('이력서를 선택해주세요!');
    return;
  }

  // prjAnncNo (현재 공고 번호)
  const prjAnncNo = renderData.prjAnncNo;
  // 모집인원 번호: 지원부문 select의 value (jobCode)로 모집인원 목록에서 찾기
  const rcrt = (renderData.prjRcrtPsncntList || []).find(
    (r) => r.jobCode === selectedField
  );
  const rcrtPsncntNo = rcrt ? rcrt.rcrtPsncntNo : null;
  if (!rcrtPsncntNo) {
    alert('모집 인원 정보가 없습니다. 새로고침 후 다시 시도해주세요.');
    return;
  }

  // 이력서 번호
  const resumeNo = selectedResume.RESUME_NO;

  // 서버로 전송할 객체
  const applyData = {
    prjAnncNo, // 게시글 번호
    rcrtPsncntNo, // 모집인원 번호
    resumeNo, // 이력서 번호
  };

  try {
    // POST로 전송 (엔드포인트는 실제 경로에 맞게!)
    const res = await axios.post('/ajax/resume', applyData);
    if (res.data === 'ok') {
      alert('지원이 완료되었습니다!');
      // 모달 닫기
      const modal = bootstrap.Modal.getOrCreateInstance(
        document.getElementById('applicationModal')
      );
      modal.hide();
    } else {
      alert(res.data.msg || '지원에 실패했습니다. 다시 시도해 주세요.');
    }
  } catch (err) {
    alert('서버 오류가 발생했습니다.\n' + (err.response?.data?.message || ''));
  }
};
