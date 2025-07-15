// 모달 데이터(MOCK)
const mockResumes = [
  {id: "1", title: "이훈석의 이력서 입니다", createdAt: "2025.06.04 (수) 19:57 작성", hasAttachment: false},
  {id: "2", title: "프론트엔드 개발자 이력서", createdAt: "2025.05.15 (월) 14:30 작성", hasAttachment: true},
  {id: "3", title: "백엔드 개발자 포트폴리오", createdAt: "2025.04.20 (목) 10:15 작성", hasAttachment: true},
];
const applicationFields = ["기획", "디자인", "프론트엔드"];

let selectedField = "";
let selectedResume = mockResumes[0];

const selectField = document.getElementById('selectField');
const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');
const modalProjectTitle = document.getElementById('modalProjectTitle');

// 필드 옵션 채우기
selectField.innerHTML += applicationFields.map(field => `<option value="${field}">${field}</option>`).join('');

// 필드 선택
selectField.onchange = function() {
  selectedField = this.value;
};

// // 이력서 카드 렌더
// function renderSelectedResumeCard() {
//   selectedResumeCard.innerHTML = `
//     <div class="card mb-0 selected-card">
//       <div class="card-body py-2 px-3">
//         <p class="mb-1 text-secondary" style="font-size: .92em;">${selectedResume.createdAt}</p>
//         <h6 class="mb-1">${selectedResume.title}</h6>
//         <div class="d-flex align-items-center gap-2 text-secondary" style="font-size:.96em;">
//           <i class="bi bi-file-earmark-text"></i>
//           <span>${selectedResume.hasAttachment ? "첨부파일이 있습니다" : "첨부파일이 없습니다"}</span>
//         </div>
//       </div>
//     </div>
//   `;
// }
// renderSelectedResumeCard();

// // 이력서 목록 렌더
// function renderResumeList() {
//   resumeList.innerHTML = mockResumes.map(resume => `
//     <div class="card mb-2 resume-card ${selectedResume.id === resume.id ? 'selected-card' : ''}" data-id="${resume.id}">
//       <div class="card-body py-2 px-3">
//         <p class="mb-1 text-secondary" style="font-size: .92em;">${resume.createdAt}</p>
//         <h6 class="mb-1">${resume.title}</h6>
//         <div class="d-flex align-items-center gap-2 text-secondary" style="font-size:.96em;">
//           <i class="bi bi-file-earmark-text"></i>
//           <span>${resume.hasAttachment ? "첨부파일이 있습니다" : "첨부파일이 없습니다"}</span>
//         </div>
//       </div>
//     </div>
//   `).join('');
//   // 카드 클릭시 이력서 선택
//   resumeList.querySelectorAll('.resume-card').forEach(card => {
//     card.onclick = function() {
//       const rid = this.getAttribute('data-id');
//       selectedResume = mockResumes.find(r => r.id === rid);
//       renderSelectedResumeCard();
//       resumeList.style.display = 'none';
//     };
//   });
// }

// // 이력서 변경 버튼
// btnShowResumeList.onclick = function() {
//   renderResumeList();
//   resumeList.style.display = resumeList.style.display === 'none' ? 'block' : 'none';
// };

// 임시저장 버튼
btnSaveApplication.onclick = function() {
  if (!selectedField) {
    alert("지원 부문을 선택해주세요!");
    selectField.focus();
    return;
  }
  alert(`[임시저장]\n지원부문: ${selectedField}\n이력서: ${selectedResume.title}`);
  // 모달 닫기 (부트스트랩 모달 제어)
  const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('applicationModal'));
  modal.hide();
};

// 모달 열기용 예시 (프로젝트 제목 넘기기)
function openApplicationModal(title) {
  modalProjectTitle.textContent = title;
  const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
  modal.show();
}


// ================================ 게시판 랜더링 ================================

let renderData = { }

document.addEventListener('DOMContentLoaded', function() {
  const params = new URLSearchParams(window.location.search);
  const prjAnncNo = params.get('prjAnncNo');
  
  // renderAnnouncementDetail(renderData);
  axios.get('/ajax/board/project/' + prjAnncNo)
    .then(res => {
      console.log(res); // <- 실제 응답 확인
      renderData = res.data;
      renderAnnouncementDetail(renderData);
    })
    .catch(err => {
      console.log(err); // <- catch에 들어온 실제 원인 확인
      document.querySelector('.card-body').innerHTML = `<div class="text-danger py-5">게시글을 불러올 수 없습니다.</div>`;
    });
});

function renderAnnouncementDetail(data) {
  // 상태 변환
  const statusText = data.anncEndYn === 'Y' ? '모집완료' : '모집중';
  const statusClass = data.anncEndYn === 'Y' ? 'bg-secondary' : 'badge-recruit';
  
  // 팀 구성 (모집 팀원)
  const teamListHtml = (data.prjRcrtPsncntList || [])
    .filter(r => !!r.jobCodeName && r.rcrtPsncnt)
    .map(r => `<li><span class="dot"></span>${r.jobCodeName} - ${r.rcrtPsncnt}명</li>`)
    .join('');

  // 모집 중인 역할 강조
  const recruitNow = (data.prjRcrtPsncntList || [])
    .filter(r => r.jobCodeName === '백엔드')
    .map(r => `<span class="fw-bold text-dark">${r.jobCodeName} ${r.rcrtPsncnt}명</span>`).join(', ');

  // 기술 스택 (태그)
  const techListHtml = (data.prjAnncBoardTagList || [])
    .map(t => t.tag && t.tag.tagName ? `<span class="badge bg-secondary-subtle me-1">${t.tag.tagName}</span>` : '')
    .join('');

  // 내용 삽입
  document.querySelector('.card-body').innerHTML = /* html */`
    <!-- 제목/상태 -->
    <div class="d-flex flex-wrap align-items-start justify-content-between mb-2">
      <h2 class="fw-bold mb-0 text-dark" style="font-size:1.7rem;">${data.prjEmpTitle}</h2>
      <span class="badge ${statusClass} text-white fs-6 py-2 px-4">${statusText}</span>
    </div>

    <!-- 작성자 정보 -->
    <div class="d-flex align-items-center justify-content-between mb-4 flex-wrap">
      <div>
        <div class="fw-medium" style="font-size:1.1rem;">작성자 : ${data.memName || '익명'}</div>
        <div class="text-secondary small mt-1 d-flex align-items-center gap-3 flex-wrap">
          <span>작성일 ${data.anncCreateDate || '-'}</span>
          <span>수정일 ${data.updateDate || '-'}</span>
          <span class="d-flex align-items-center gap-1"><i class="bi bi-eye"></i>조회수 ${data.view || 0}</span>
        </div>
        <div class="small text-secondary" style="font-size:1.01em;">
          <span class="me-4">
            <i class="bi bi-calendar-event me-1"></i>
            <b>시작 예정일:</b>
            ${data.prjStartPlanDate
              ? `${data.prjStartPlanDate.slice(0,4)}-${data.prjStartPlanDate.slice(4,6)}-${data.prjStartPlanDate.slice(6,8)}`
              : '-'}
          </span>
          <span>
            <i class="bi bi-calendar-check me-1"></i>
            <b>마감 예정일:</b>
            ${data.prjEndPlanDate
              ? `${data.prjEndPlanDate.slice(0,4)}-${data.prjEndPlanDate.slice(4,6)}-${data.prjEndPlanDate.slice(6,8)}`
              : '-'}
          </span>
        </div>
      </div>
      <div class="d-flex align-items-center gap-2">
        <button class="btn btn-outline-secondary btn-sm px-3 d-flex align-items-center gap-1"><i class="bi bi-share"></i>공유</button>
        <button class="btn btn-outline-secondary btn-sm px-3 d-flex align-items-center gap-1"><i class="bi bi-heart"></i>${data.like || 0}</button>
      </div>
    </div>
    

    <!-- 팀 구성 -->
    <div class="mb-5">
      <div class="section-title">현재 팀 구성</div>
      <ul class="list-unstyled list-dot mb-2">
        ${teamListHtml}
      </ul>
      ${recruitNow ? `<div class="mt-3 text-secondary">→ 현재 ${recruitNow}을 새로이 모십니다!</div>` : ''}
    </div>
    <hr />

    <!-- 기술스택 & 업무 내용 -->
    <div class="mb-5">
      <div class="section-title">기술 스택</div>
      <div class="mb-2">${techListHtml}</div>
    </div>
    <hr />

    <!-- 프로젝트 소개 -->
    <div class="mb-5">
      <div class="section-title">프로젝트 소개</div>
      <div class="text-secondary mb-3" style="font-size:1.05em;">
        ${data.prjAnncContent || '-'}
      </div>
    </div>
    <hr />

    <!-- PM 이력서 버튼 -->
    <div class="d-flex justify-content-center mb-3">
      <button class="btn btn-purple px-5 py-2 fw-semibold">PM 이력서 확인</button>
    </div>

    <!-- 하단 버튼 -->
    <div class="d-flex justify-content-center gap-3">
      <a href="/board/project" class="btn btn-outline-secondary px-5 py-2">목록</a>
      <button class="btn btn-purple px-5 py-2" onclick="openApplicationModal(renderData.prjEmpTitle, renderData.prjRcrtPsncntList)">지원</button>
    </div>
  `;

  const btn = document.getElementById('btnShowPMResume');
  if (btn) {
    btn.onclick = function() {
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
  (prjRcrtPsncntList || []).forEach(item => {
    // 중복 방지용 value로 jobCode 사용 (jobCodeName이 중복일 수 있으니)
    select.innerHTML += `<option value="${item.jobCode}">${item.jobCodeName} (${item.rcrtPsncnt}명)</option>`;
  });

  // 모달 띄우기 (부트스트랩)
  const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
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
  resumeListDiv.innerHTML = resumeList.map(resume => `
    <div class="card mb-2 resume-card ${selectedResume && selectedResume.resumeNo === resume.resumeNo ? 'selected-card' : ''}" data-id="${resume.resumeNo}">
      <div class="card-body py-2 px-3">
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p class="mb-1 text-secondary" style="font-size: .92em;">
              ${resume.updateDate ? `수정일: ${resume.updateDate}` : ''}
            </p>
            <h6 class="mb-1">${resume.resumeName || resume.resumeNo}</h6>
            <div class="text-secondary" style="font-size:.96em;">
              ${resume.resumeMainYn === 'Y' ? `<span class="badge bg-purple">대표 이력서</span>` : ''}
              ${resume.resumeSubmitYn === 'Y' ? `<span class="badge bg-success">제출됨</span>` : ''}
            </div>
          </div>
          <div>
            ${resume.photo ? `<img src="${resume.photo}" alt="증명사진" style="width:38px; height:38px; border-radius:50%;">` : ''}
          </div>
        </div>
      </div>
    </div>
  `).join('');

  // 카드 클릭 이벤트로 선택
  resumeListDiv.querySelectorAll('.resume-card').forEach(card => {
    card.onclick = function () {
      const rid = this.getAttribute('data-id');
      selectedResume = resumeList.find(r => r.resumeNo === rid);
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
        <p class="mb-1 text-secondary" style="font-size: .92em;">${selectedResume.updateDate ? `수정일: ${selectedResume.updateDate}` : ''}</p>
        <h6 class="mb-1">${selectedResume.resumeName || selectedResume.resumeNo}</h6>
      </div>
    </div>
  `;
}