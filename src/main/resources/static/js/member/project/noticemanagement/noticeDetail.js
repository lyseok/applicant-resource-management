let selectedField = "";

const selectField = document.getElementById('selectField');
const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');



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

    // 수정 버튼 이벤트 (DOMContentLoaded 안에 넣기!)
    document.getElementById('btnEditNotice').addEventListener('click', function() {
      if (prjAnncNo) {
        location.href = `/mypage/notice_management/form?prjAnncNo=${prjAnncNo}`;
      }
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
    .map(t => t.tag && t.tag.tagName ? `<span class="badge-tag">${t.tag.tagName}</span>` : '')
    .join('');

  // 내용 삽입
  document.querySelector('.card-body').innerHTML = /* html */`
    <!-- 제목/상태 -->
    <div class="d-flex align-items-center mb-2 gap-3">
	      <span class="badge ${statusClass} text-white fs-14 py-2 px-4 bg-violet08">${statusText}</span>
	      <h2 class="fw-bold text-dark fs-5">${data.prjEmpTitle}</h2>
    </div>

    <!-- 글 정보 (작성일, 수정일, 조회수) -->
    <div class="d-flex align-items-center gap-5 mb-5 text-secondary small">
      <div class="d-flex gap-2"><b>작성</b><span>${data.anncCreateDate.split(" ")[0] || '-'}</span></div>
      <!-- <span>수정일 ${data.updateDate || '-'}</span> -->
      <div class="d-flex gap-2"><b>조회</b><span>${data.view || 0}</span></div>
    </div>
    

		<!-- 작업 기간 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">프로젝트 작업 기간</div>
      <div class="fs-14">
	      <span class="">
	        ${data.prjStartPlanDate
	          ? `${data.prjStartPlanDate.slice(0,4)}-${data.prjStartPlanDate.slice(4,6)}-${data.prjStartPlanDate.slice(6,8)}`
	          : '-'} ~
	      </span>
	      <span>
	        <i class="bi bi-calendar-check me-1"></i>
	        ${data.prjEndPlanDate
	          ? `${data.prjEndPlanDate.slice(0,4)}-${data.prjEndPlanDate.slice(4,6)}-${data.prjEndPlanDate.slice(6,8)}`
	          : '-'}
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
      ${recruitNow ? `<div class="mt-3 text-secondary">→ 현재 ${recruitNow}을 새로이 모십니다!</div>` : ''}
    </div>

    <!-- 기술스택 & 업무 내용 -->
    <div class="mb-5">
      <div class="mb-1 fs-6 fw-bold">기술 스택</div>
      <div class="d-flex gap-2 mb-2">${techListHtml}</div>
    </div>

    
    
    <div class="d-flex align-items-center justify-content-between">
	    <!-- 작성자 정보 -->
	    <div class="d-flex align-items-center gap-3">
	    	<div class="profile">
	    		<img src="${data.memProfile || 'https://placehold.co/32x32'}">
	    	</div>
	    	<div class="fw-bold fs-16">${data.memName || '익명'}</div>
	    </div>
    </div>
    <hr />

  `;

}



