// ==================== 전역 변수 및 데이터 ====================
let projectData = null;
let activeTabIdx = 0;

// 상태 코드 매핑
const STATUS_MAP = {
  "PRST-000": "참여 거절",
  "PRST-001": "지원 완료",
  "PRST-002": "참여 요청",
  "PRST-003": "참여 완료"
};

// ==================== 데이터 요청 ====================
document.addEventListener('DOMContentLoaded', async function () {
  const prjAnncNo = new URLSearchParams(location.search).get('prjAnncNo');
  try {
    const res = await axios.get(`/ajax/board/project/${prjAnncNo}/applicant`);
    projectData = res.data;
    renderTabsAndContent();
    setupFilterEvents();
    setupSortEvents();
  } catch (err) {
    document.querySelector('#recruitTabContent').innerHTML = `<div class="text-danger py-5">데이터를 불러올 수 없습니다.</div>`;
  }
});

// ==================== 탭/테이블 렌더링 ====================
function renderTabsAndContent() {
  const tabs = projectData.prjRcrtPsncntList || [];
  const tabNav = document.getElementById('recruitTabNav');
  const tabContent = document.getElementById('recruitTabContent');
  tabNav.innerHTML = '';
  tabContent.innerHTML = '';

  tabs.forEach((section, idx) => {
    // 탭 버튼
    const tabId = `recruitTab${idx}`;
    tabNav.innerHTML += `<li class="nav-item">
      <button class="nav-link${idx === 0 ? ' active' : ''}" data-bs-toggle="tab" data-bs-target="#${tabId}" type="button">${section.jobCodeName}</button>
    </li>`;
    // 탭 내용 (테이블)
    tabContent.innerHTML += `
      <div class="tab-pane fade${idx === 0 ? ' show active' : ''}" id="${tabId}" role="tabpanel">
        <table class="table table-sm table-bordered align-middle text-center" id="mainTable${idx}">
          <thead>
            <tr>
              <th class="text-center">선택</th>
              <th class="text-center">이름</th>
              <th class="text-center">이력서</th>
              <th class="text-center">기술</th>
              <th class="text-center">상태</th>
            </tr>
          </thead>
          <tbody>
            ${renderApplicants(section.aplcntList || [], idx)}
          </tbody>
        </table>
      </div>`;
  });

  // 탭 활성화 이벤트
  tabNav.querySelectorAll('.nav-link').forEach((btn, idx) => {
    btn.addEventListener('click', function () {
      activeTabIdx = idx;
      setTimeout(applyFilters, 0); // 탭 변경 후 필터 적용
    });
  });

  // 필터 초기화 옵션 채우기
  fillFilterOptions();
  applyFilters();
}

// ==================== 지원자 렌더 ====================
function renderApplicants(list, idx) {
  return (list || []).map(app => `
    <tr data-username="${app.resume?.userName || ''}" data-skill="${(app.resume?.mySkillList||[]).map(k=>k.mySkillName).join(',')}" data-status="${app.aplcntStatusCode}">
      <td><input type="checkbox" class="rowCheck"></td>
      <td>${app.resume?.userName || '-'}</td>
      <td>
        ${app.resume?.resumeName || '-'}
        <a href="#" onclick="showResumeDetail('${app.resumeNo}');return false;" class="ms-2 text-primary">상세</a>
      </td>
      <td>${(app.resume?.mySkillList||[]).map(k=>k.mySkillName).join(', ') || '-'}</td>
      <td class="status-cell">${STATUS_MAP[app.aplcntStatusCode] || '-'}</td>
    </tr>
  `).join('');
}

// 이력서 상세 (예시)
window.showResumeDetail = function(resumeNo) {
  alert('이력서 상세보기: ' + resumeNo);
}

// ==================== 필터 UI/로직 ====================
function fillFilterOptions() {
  // 전체 모집부문 지원자의 기술 태그/이름 중복없이 뽑기
  const allApplicants = (projectData.prjRcrtPsncntList || []).flatMap(sec => sec.aplcntList || []);
  const allSkills = Array.from(new Set(allApplicants.flatMap(app => (app.resume?.mySkillList||[]).map(k=>k.mySkillName)).filter(Boolean)));
  const allNames = Array.from(new Set(allApplicants.map(app => app.resume?.userName).filter(Boolean)));

  // 기술 태그 체크박스
  const $skillTag = document.getElementById('skillTagFilter');
  if ($skillTag) {
    $skillTag.innerHTML = allSkills.map(skill =>
      `<label class="badge rounded-pill bg-light text-dark border p-2 mb-0" style="cursor:pointer;">
        <input type="checkbox" class="form-check-input me-1" value="${skill}" style="vertical-align:middle;">${skill}
      </label>`
    ).join('');
  }
  // 이름 검색 자동완성 힌트(옵션은 안 넣어도 됨)
}

// 필터 적용
function applyFilters() {
  // 기술 필터
  const selectedSkills = Array.from(document.querySelectorAll('#skillTagFilter input:checked')).map(cb=>cb.value);
  // 이름 검색
  const nameKeyword = document.getElementById('searchInput')?.value.trim();

  // 현재 테이블에서 row show/hide
  const tableBody = document.querySelector(`.tab-pane.active table tbody`);
  Array.from(tableBody.querySelectorAll('tr')).forEach(tr => {
    let show = true;
    if (selectedSkills.length) {
      const skillArr = tr.dataset.skill.split(',').map(s=>s.trim());
      if(!selectedSkills.every(skill => skillArr.includes(skill))) show = false;
    }
    if(nameKeyword && !tr.dataset.username.includes(nameKeyword)) show = false;
    tr.style.display = show ? '' : 'none';
  });
}

// ==================== 정렬 이벤트 ====================
function setupSortEvents() {
  document.querySelectorAll('button[onclick^="sortTable"]').forEach(btn => {
    btn.addEventListener('click', function() {
      const key = this.getAttribute('onclick').match(/'(.*?)'/)[1];
      sortTable(key);
    });
  });
}

function sortTable(key) {
  const table = document.querySelector('.tab-pane.active table tbody');
  const rows = Array.from(table.querySelectorAll('tr')).filter(tr=>tr.style.display!=='none');
  let idx = 0;
  if (key === 'career') idx = 3; // 기술은 idx=3
  if (key === 'score') idx = 4; // 상태는 idx=4

  let asc = sortTable.asc = !sortTable.asc;
  rows.sort((a, b) => {
    let va = a.children[idx].innerText.trim();
    let vb = b.children[idx].innerText.trim();
    return asc ? va.localeCompare(vb, 'ko') : vb.localeCompare(va, 'ko');
  });
  rows.forEach(tr => table.appendChild(tr));
}

// ==================== 필터 이벤트 ====================
function setupFilterEvents() {
  // 기술 태그
  document.getElementById('skillTagFilter')?.addEventListener('change', applyFilters);
  // 이름검색
  document.getElementById('searchBtn')?.addEventListener('click', applyFilters);
  // 초기화
  document.getElementById('resetFilters')?.addEventListener('click', function(){
    Array.from(document.querySelectorAll('#skillTagFilter input')).forEach(cb=>cb.checked=false);
    document.getElementById('searchInput').value = "";
    applyFilters();
  });
}


// ========================= 참여 요청 =============================
// 참여요청 버튼 클릭 이벤트
document.getElementById('btnRequestParticipation').addEventListener('click', async function () {
  // 현재 활성 탭 index
  const tabIdx = activeTabIdx;
  const tabSection = (projectData.prjRcrtPsncntList || [])[tabIdx];
  if (!tabSection) return;

  // 체크된 지원자 찾기
  const table = document.querySelector(`.tab-pane.active table`);
  const checkedRows = Array.from(table.querySelectorAll('tbody tr')).filter(tr => tr.querySelector('.rowCheck:checked'));

  if (checkedRows.length === 0) {
    alert('참여 요청할 지원자를 선택하세요.');
    return;
  }

  // 지원자 번호(prjAplcntNo) 배열로 모으기
  const targetAplcntNos = checkedRows.map(tr => {
    // aplcntList는 tabSection에만 있음, 각 row의 data-username 기준 매칭
    const username = tr.getAttribute('data-username');
    const app = (tabSection.aplcntList || []).find(a => a.resume?.userName === username);
    return app?.prjAplcntNo;
  }).filter(Boolean);

  if (targetAplcntNos.length === 0) {
    alert('대상이 없습니다.');
    return;
  }

  // 서버로 상태 변경 요청 (배열로 전송)
  try {
    const res = await axios.put('/ajax/project/applicant/join', {
      prjAplcntNoList: targetAplcntNos,
      status: 'PRST-002'
    });
    if (res.data === 'ok') {
      // 프론트 상태도 변경
      checkedRows.forEach(tr => {
        tr.querySelector('.status-cell').innerText = STATUS_MAP['PRST-002'];
        // 체크박스 해제(optional)
        tr.querySelector('.rowCheck').checked = false;
      });
    } else {
      alert('변경에 실패했습니다. 다시 시도해주세요.');
    }
  } catch (err) {
    alert('서버 오류가 발생했습니다.');
  }
});