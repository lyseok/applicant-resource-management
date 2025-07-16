// =============================== 데이터 ===============================
let announcementData = {
  prjAnncNo: '',               // 수정 시 PK, 등록 시 빈값
  prjEmpTitle: '',             // 제목
  prjTopic: '',                // 주제
  anncCreateDate: '',          // 등록일 (서버에서)
  prjStartPlanDate: '',        // 시작 예정일
  prjEndPlanDate: '',          // 마감 예정일
  prjAnncContent: '',          // 프로젝트 소개/내용
  prjAnncBoardTagList: [],     // 태그 [{tagNo, tag:{tagName}}, ...]
  prjRcrtPsncntList: [         // 모집팀원 [{jobCode, rcrtPsncnt}]
    // {jobCode: "2248", rcrtPsncnt: 2}
  ],
  anncEndYn: 'N'               // 'N' or 'Y'
};


// 데이터 받아오고, announcementData 세팅 + input들에 값 세팅
function fetchAndFillForm(prjAnncNo) {
  axios.get('/ajax/board/project/' + prjAnncNo)
    .then(res => {
      const data = res.data;
      if (!data) return;

      // announcementData 객체에 데이터 복사
      Object.assign(announcementData, data);

      // 제목
      document.querySelector('[name="prjEmpTitle"]').value = data.prjEmpTitle || '';
      // 주제
      document.querySelector('[name="prjTopic"]').value = data.prjTopic || '';
      // 시작일
      document.querySelector('[name="prjStartPlanDate"]').value = formatDateForInput(data.prjStartPlanDate);
      // 마감일
      document.querySelector('[name="prjEndPlanDate"]').value = formatDateForInput(data.prjEndPlanDate);

      // 태그
      tags = (data.prjAnncBoardTagList || []).map(tagObj => tagObj.tag.tagName);
      renderTags();

      // 모집팀원
      roleListArr = (data.prjRcrtPsncntList || []).map(role => ({
        jobCode: role.jobCode,
        jobCodeName: role.jobCodeName,
        rcrtPsncnt: role.rcrtPsncnt,
        name: role.jobCodeName
      }));
      renderRoleList();

      // 에디터
      editor.setMarkdown(data.prjAnncContent || '');
    })
    .catch(err => {
      alert('데이터를 불러올 수 없습니다.');
      console.error(err);
    });
}

// 'YYYYMMDD' → 'YYYY-MM-DD' 변환 함수 (input type="date"용)
function formatDateForInput(val) {
  if (!val) return '';
  if (val.length === 8)
    return `${val.slice(0,4)}-${val.slice(4,6)}-${val.slice(6,8)}`;
  return val;
}

// 사용 예시 (페이지 진입 시 호출)
const urlParams = new URLSearchParams(window.location.search);
const prjAnncNo = urlParams.get('prjAnncNo');
if (prjAnncNo) {
  fetchAndFillForm(prjAnncNo);
}







// 제목 입력시
document.querySelector('[name="prjEmpTitle"]').addEventListener('input', e => {
  announcementData.prjEmpTitle = e.target.value;
});
// 주제, 기간, 등등도 동일하게...

// 태그(추가/삭제)시
function updateTags(newTags) {
  announcementData.prjAnncBoardTagList = newTags.map(tagText => ({
    tag: { tagName: tagText }
  }));
}

// 모집팀원(추가/삭제/수정)시
function updateRoles(rolesArr) {
  announcementData.prjRcrtPsncntList = rolesArr; // [{jobCode, rcrtPsncnt}]
}

// 에디터 내용은 저장/submit시점에 추출해서 저장
function updateContent() {
  announcementData.prjAnncContent = editor.getMarkdown(); // toast ui 등
}

document.getElementById('projectForm').onsubmit = function(e){
  e.preventDefault();
  
  // 모든 input/selct 값 강제 동기화 (name이 같다는 가정 하에)
  document.querySelectorAll('#projectForm input, #projectForm select').forEach(input => {
    if (announcementData.hasOwnProperty(input.name)) {
      announcementData[input.name] = input.value;
    }
  });

  updateContent(); // 에디터 내용 반영
  updateTags(tags); // 태그 값 반영
  updateRoles(roleListArr); // 모집팀원 반영
  // (최신 입력값 반영)
  updateContent(); // 에디터 내용
  console.log(announcementData);
  // 서버로 전송 (POST/PUT)
  axios.post('/ajax/board/project', announcementData)
    .then(res => {
      alert('등록/수정 성공!');
      if (announcementData.prjAnncNo) {
        // 수정 완료 → 프로젝트 공고 목록으로
        location.href = `/mypage/notice_management/detail?prjAnncNo=${announcementData.prjAnncNo}`;
      } else {
        location.href = "/board/project";
      }
    })
    .catch(err => {
      if (err.response && err.response.data) {
        showValidationErrors(err.response.data);
      }
    });
}

function showValidationErrors(errors) {
  // 기존 에러 메시지 제거
  document.querySelectorAll('.input-error-msg').forEach(e => e.remove());

  Object.entries(errors).forEach(([field, messages]) => {
    // input name과 field명이 같다고 가정
    const input = document.querySelector(`[name="${field}"]`);
    if (input) {
      const errorSpan = document.createElement('span');
      errorSpan.className = 'input-error-msg text-danger mt-1';
      errorSpan.style.fontSize = '0.98em';
      errorSpan.textContent = messages[0]; // 여러 메시지일 경우 첫번째만 표시
      input.parentNode.appendChild(errorSpan); // input 바로 아래에 추가
    }
  });
}

// =============================== 화면구성 ===============================
const tagInput = document.getElementById('tagInput');
const tagInputBox = document.getElementById('tagInputBox');
let tags = [];
tagInput.addEventListener('keydown', function(e) {
    if (e.key === 'Enter' && this.value.trim()) {
        addTag(this.value.trim());
        this.value = '';
        e.preventDefault();
    }
});
function addTag(text) {
    if (tags.includes(text)) return;
    tags.push(text);
    renderTags();
}
function removeTag(idx) {
    tags.splice(idx, 1);
    renderTags();
}
function renderTags() {
    tagInputBox.querySelectorAll('.tag-chip').forEach(chip => chip.remove());
    tags.forEach((t, i) => {
        const chip = document.createElement('span');
        chip.className = 'tag-chip';
        chip.innerHTML = `${t}<span class="remove-tag" onclick="removeTag(${i})">&times;</span>`;
        tagInputBox.insertBefore(chip, tagInput);
    });
}
window.removeTag = removeTag;

/** ---- 모집 팀원 역할/인원 관리 ---- **/
let jobData = []; // 전역에서 쓸 수 있도록 let으로 선언

// 페이지 로드 시 한 번만 호출
(function fetchJobData() {
  axios.get('/ajax/code/job')
    .then(res => {
      if (Array.isArray(res.data)) {
        jobData = res.data.map(j => ({
          jobCode: j.jobCode,
          jobName: j.jobName
        }));
      }
    })
    .catch(err => {
      console.error('직무 코드 목록을 불러오지 못했습니다.', err);
    });
})();

const jobInput = document.getElementById('jobSearchInput');
const jobResult = document.getElementById('jobSearchResult');
const roleCountSelect = document.getElementById('roleCountSelect');
const addRoleBtn = document.getElementById('addRoleBtn');
const roleList = document.getElementById('roleList');
let selectedJob = null;
let roleListArr = [];

jobInput.addEventListener('input', function() {
    const val = this.value.trim().toLowerCase();
    if (!val) return jobResult.style.display = 'none';
    const filtered = jobData.filter(j => j.jobName.toLowerCase().includes(val));
    if (filtered.length === 0) return jobResult.style.display = 'none';
    // 상위 5개만 표시
    jobResult.innerHTML = filtered.slice(0, 5).map(j => 
        `<li class="list-group-item list-group-item-action" data-code="${j.jobCode}">${j.jobName}</li>`
    ).join('');
    jobResult.style.display = '';
    jobResult.style.left = jobInput.getBoundingClientRect().left + "px";
});

jobResult.addEventListener('click', function(e) {
    const li = e.target.closest('li');
    if (!li) return;
    jobInput.value = li.textContent;
    selectedJob = { code: li.dataset.code, name: li.textContent };
    jobResult.style.display = 'none';
});

document.addEventListener('click', function(e){
    if (!jobInput.contains(e.target) && !jobResult.contains(e.target)) jobResult.style.display = 'none';
});

addRoleBtn.onclick = function() {
    let name = jobInput.value.trim();
    let code = selectedJob?.code || null;
    let count = roleCountSelect.value;
    if (!name) return alert('직무를 입력 또는 선택하세요.');
    if (roleListArr.some(r => r.name === name)) return alert('이미 추가된 직무입니다.');
    // 코드 못찾으면 직접입력 (이름만 저장)
    roleListArr.push({ jobCode: code, jobCodeName: name, rcrtPsncnt: count , name});
    renderRoleList();
    jobInput.value = '';
    selectedJob = null;
};
function renderRoleList() {
    roleList.innerHTML = '';
    roleListArr.forEach((role, i) => {
        const li = document.createElement('li');
        li.className = 'd-flex align-items-center justify-content-between position-row py-2 px-3';
        li.innerHTML = `
            <span>
                ${role.name}
                <button type="button" class="delete-role" onclick="removeRole(${i})">&times;</button>
                <input type="hidden" name="teamRoles[${i}].jobName" value="${role.name}">
                <input type="hidden" name="teamRoles[${i}].jobCode" value="${role.code || ''}">
            </span>
            <select name="teamRoles[${i}].count" class="form-select ms-3" 
              style="max-width:100px;display:inline-block;" 
              data-idx="${i}">
                ${[1,2,3,4,5].map(n => `<option value="${n}"${n==role.rcrtPsncnt?' selected':''}>${n}명</option>`).join('')}
            </select>
        `;
        roleList.appendChild(li);
    });

    // ★ 변경 이벤트 바인딩 ★
    roleList.querySelectorAll('select[data-idx]').forEach(select => {
      select.addEventListener('change', function() {
        const idx = this.getAttribute('data-idx');
        roleListArr[idx].rcrtPsncnt = this.value;
        // 필요시 아래 한줄도: announcementData.prjRcrtPsncntList = roleListArr;
      });
    });
}
window.removeRole = function(idx) {
    roleListArr.splice(idx, 1);
    renderRoleList();
}

/** ---- Toast UI Editor ---- **/
const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '380px',
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    plugins: [],
    placeholder: '프로젝트 소개와 상세 업무 내용을 입력하세요. (이미지 첨부 가능)'
});
