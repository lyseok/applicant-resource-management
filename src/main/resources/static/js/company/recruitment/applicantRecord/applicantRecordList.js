let currentStep = 'step1'; // 초기 단계

// 컬럼 정보(스텝별로 다르면 이렇게 관리)
const columns = {
  step1: [
    { key: 'id', label: '지원자ID' },
    { key: 'name', label: '이름' },
    { key: 'resumeUrl', label: '이력서' },
    { key: 'career', label: '경력' },
    { key: 'language', label: '어학' },
    { key: 'major', label: '전공' },
    { key: 'cert', label: '자격증' },
    { key: 'skill', label: '기술' },
    { key: 'attend', label: '응시' },
    { key: 'pass', label: '합격' },
    { key: 'score', label: '점수' },
    { key: 'passSelect', label: '합격선택' }
  ],
  step2: [
    // 시험점수 등 step2 컬럼 맞게
    { key: 'id', label: '지원자ID' },
    { key: 'name', label: '이름' },
    { key: 'resumeUrl', label: '이력서' },
    { key: 'career', label: '경력' },
    { key: 'language', label: '어학' },
    { key: 'major', label: '전공' },
    { key: 'cert', label: '자격증' },
    { key: 'skill', label: '기술' },
    { key: 'attend', label: '응시' },
    { key: 'pass', label: '합격' },
    { key: 'score', label: '시험점수' },
    { key: 'passSelect', label: '합격선택' }
  ],
  step3: [
    // 면접점수 등 step3 컬럼 맞게
    { key: 'id', label: '지원자ID' },
    { key: 'name', label: '이름' },
    { key: 'resumeUrl', label: '이력서' },
    { key: 'career', label: '경력' },
    { key: 'language', label: '어학' },
    { key: 'major', label: '전공' },
    { key: 'cert', label: '자격증' },
    { key: 'skill', label: '기술' },
    { key: 'attend', label: '응시' },
    { key: 'pass', label: '합격' },
    { key: 'score', label: '면접점수' },
    { key: 'passSelect', label: '합격선택' }
  ]
};

let applicantData = {
  step1: [
    {
      id: "APPL1001",
      name: "김지원",
      resumeUrl: "resume/1001.pdf",
      career: 5,
      language: 900,
      major: "컴퓨터공학",
      cert: ["정보처리기사", "산업안전기사"],
      skill: ["Java", "Python", "React"],
      attend: "Y",
      pass: "Y",
      score: null
    },
    {
      id: "APPL1002",
      name: "이서류",
      resumeUrl: "resume/1002.pdf",
      career: 2,
      language: 800,
      major: "산업공학",
      cert: ["정보처리기사"],
      skill: ["Python"],
      attend: "Y",
      pass: "N",
      score: null
    },
    {
      id: "APPL1003",
      name: "박기계",
      resumeUrl: "resume/1003.pdf",
      career: 4,
      language: 700,
      major: "기계공학",
      cert: ["정보처리기능사"],
      skill: ["Java"],
      attend: "Y",
      pass: "Y",
      score: null
    },
    {
      id: "APPL1004",
      name: "최경력",
      resumeUrl: "resume/1004.pdf",
      career: 8,
      language: 850,
      major: "산업공학",
      cert: ["정보처리기사"],
      skill: ["Java", "React"],
      attend: "Y",
      pass: "Y",
      score: null
    },
    {
      id: "APPL1005",
      name: "유신입",
      resumeUrl: "resume/1005.pdf",
      career: 1,
      language: 750,
      major: "컴퓨터공학",
      cert: [],
      skill: ["Python", "HTML"],
      attend: "Y",
      pass: "N",
      score: null
    }
  ],
  step2: [
    {
      id: "APPL1001",
      name: "김지원",
      resumeUrl: "resume/1001.pdf",
      career: 5,
      language: 900,
      major: "컴퓨터공학",
      cert: ["정보처리기사", "산업안전기사"],
      skill: ["Java", "Python", "React"],
      attend: "Y",
      pass: "Y",
      score: 88
    },
    {
      id: "APPL1003",
      name: "박기계",
      resumeUrl: "resume/1003.pdf",
      career: 4,
      language: 700,
      major: "기계공학",
      cert: ["정보처리기능사"],
      skill: ["Java"],
      attend: "Y",
      pass: "N",
      score: 74
    },
    {
      id: "APPL1004",
      name: "최경력",
      resumeUrl: "resume/1004.pdf",
      career: 8,
      language: 850,
      major: "산업공학",
      cert: ["정보처리기사"],
      skill: ["Java", "React"],
      attend: "Y",
      pass: "Y",
      score: 93
    }
  ],
  step3: [
    {
      id: "APPL1001",
      name: "김지원",
      resumeUrl: "resume/1001.pdf",
      career: 5,
      language: 900,
      major: "컴퓨터공학",
      cert: ["정보처리기사", "산업안전기사"],
      skill: ["Java", "Python", "React"],
      attend: "Y",
      pass: "",
      score: 92
    },
    {
      id: "APPL1004",
      name: "최경력",
      resumeUrl: "resume/1004.pdf",
      career: 8,
      language: 850,
      major: "산업공학",
      cert: ["정보처리기사"],
      skill: ["Java", "React"],
      attend: "Y",
      pass: "",
      score: 85
    }
  ]
};



// ======================= 테이블랜더 ========================

// 테이블 렌더링 함수
function renderApplicantTable() {
  const tableHead = document.querySelector('#mainTable thead tr');
  const tableBody = document.querySelector('#mainTable tbody');
  // 헤더 렌더링
  tableHead.innerHTML = columns[currentStep].map(col => `<th class="text-center">${col.label}</th>`).join('');
  // 바디 렌더링
  tableBody.innerHTML = applicantData[currentStep].map(a => {
    return `<tr data-career="${a.career}" data-language="${a.language}" data-major="${a.major}"
          data-cert="${a.cert.join(',')}" data-skill="${a.skill.join(',')}">
        <td>${a.id}</td>
        <td>${a.name}</td>
        <td><a href="${a.resumeUrl}" target="_blank" class="btn btn-outline-primary btn-sm">상세</a></td>
        <td>${a.career}년</td>
        <td>${a.language}</td>
        <td>${a.major}</td>
        <td>${a.cert.join(', ')}</td>
        <td>${a.skill.join(', ')}</td>
        <td>${a.attend && a.attend.trim() ? a.attend : '-'}</td>
        <td>${a.pass && a.pass.trim() ? a.pass : '-'}</td>
        <td>${a.score !== null ? a.score : '-'}</td>
        <td>
          <input type="checkbox" class="form-check-input passCheck"
            ${a.pass === 'Y' ? 'checked' : ''}
            ${(a.pass === 'Y' || a.pass === 'N') ? 'disabled' : ''}>
        </td>
      </tr>`;
  }).join('');
  applyFilters();
}

// 탭 버튼 클릭시 active 변경 및 스텝 변경
document.querySelectorAll('.nav-link[data-step]').forEach(btn => {
  btn.addEventListener('click', function() {
    // active 클래스 변경
    document.querySelectorAll('.nav-link[data-step]').forEach(b => b.classList.remove('active'));
    this.classList.add('active');

    // currentStep 변경 및 테이블 렌더링
    currentStep = this.getAttribute('data-step');
    renderApplicantTable();
  });
});


document.querySelectorAll('button[data-bs-toggle="tab"]').forEach(btn=>{
  btn.addEventListener('shown.bs.tab',applyFilters);
});
document.getElementById('searchBtn').addEventListener('click', applyFilters);
document.getElementById('resetFilters').addEventListener('click', function(){
  document.getElementById('careerMin').value = "";
  document.getElementById('careerMax').value = "";
  document.getElementById('languageFilter').value = "";
  document.getElementById('majorFilter').value = "";
  Array.from(document.querySelectorAll('#skillTagFilter input')).forEach(cb=>cb.checked=false);
  document.getElementById('searchInput').value = "";
  applyFilters();
});

// 합격자 직접 체크 후 저장
function savePassStatus(){
  const activeTable = document.querySelector('.tab-pane.active table');
  activeTable.querySelectorAll('tbody tr').forEach(tr => {
    const check = tr.querySelector('.passCheck');
    if(check) tr.children[9].innerText = check.checked ? 'Y' : 'N'; // 9: 합격여부
  });
  alert('합격여부 저장(프론트기준)');
}

// 단계 마감(합격자 일괄적용)
function closeStep(){
    const activeTable = document.querySelector('.tab-pane.active table');
    let cnt=0;
    activeTable.querySelectorAll('tbody tr').forEach(tr => {
      const check = tr.querySelector('.passCheck');
      if(check && check.checked){
        tr.children[9].innerText = 'Y';
        cnt++;
      }else{
        tr.children[9].innerText = 'N';
      }
    });
    alert('마감 완료, 합격자 '+cnt+'명 반영(프론트)');
  }
  
  renderApplicantTable();
  
  
  
  // ======================= 필터 ========================
// 데이터에서 모든 언어, 전공, 기술 추출
function getUniqueValues(field) {
  const all = [];
  Object.values(applicantData).flat().forEach(a => {
    if (Array.isArray(a[field])) all.push(...a[field]);
    else all.push(a[field]);
  });
  return Array.from(new Set(all)).filter(v=>v); // 중복제거 + 빈값 제외
}

// 필터 UI 옵션 채우기
function fillFilterOptions() {
  // 어학
  const langs = getUniqueValues('languageName'); // 예시: 지원자 데이터에 languageName 필드 사용
  const $lang = document.getElementById('languageFilter');
  $lang.innerHTML = `<option value="">전체</option>` + langs.map(l=>`<option value="${l}">${l}</option>`).join('');
  // 전공
  const majors = getUniqueValues('major');
  const $major = document.getElementById('majorFilter');
  $major.innerHTML = `<option value="">전체</option>` + majors.map(m=>`<option value="${m}">${m}</option>`).join('');
  // 자격증
  const certs = getUniqueValues('cert');
  const $cert = document.getElementById('certFilter');
  $cert.innerHTML = `<option value="">전체</option>` + certs.map(c=>`<option value="${c}">${c}</option>`).join('');
  // 기술 태그형 체크박스
  const skills = getUniqueValues('skill');
  const $skillTag = document.getElementById('skillTagFilter');
  $skillTag.innerHTML = skills.map(skill =>
    `<label class="badge rounded-pill bg-light text-dark border p-2 mb-0" style="cursor:pointer;">
      <input type="checkbox" class="form-check-input me-1" value="${skill}" style="vertical-align:middle;">${skill}
    </label>`
  ).join('');
}
fillFilterOptions();

function applyFilters() {
  const slider = document.getElementById('careerSlider');
  let minCareer = 0, maxCareer = 10;
  if (slider && slider.noUiSlider) {
    const values = slider.noUiSlider.get();
    minCareer = parseInt(values[0]);
    maxCareer = parseInt(values[1]);
  }
  const lang = document.getElementById('languageFilter').value;
  const major = document.getElementById('majorFilter').value;
  const cert = document.getElementById('certFilter').value;
  const nameKeyword = document.getElementById('searchInput').value.trim();
  // 선택된 기술
  const selectedSkills = Array.from(document.querySelectorAll('#skillTagFilter input:checked')).map(cb=>cb.value);

  const tableBody = document.querySelector('#mainTable tbody');
  Array.from(tableBody.querySelectorAll('tr')).forEach(tr => {
    let show = true;
    const career = parseInt(tr.dataset.career);
    if(career < minCareer || career > maxCareer) show = false;
    if(lang && tr.dataset.languageName !== lang) show = false; // languageName 사용(데이터에 따라 맞게)
    if(major && tr.dataset.major !== major) show = false;
    if(nameKeyword && !tr.children[1].innerText.includes(nameKeyword)) show = false;
    if (cert) {
      // 지원자 row의 자격증: data-cert 속성(콤마구분)
      const certArr = tr.dataset.cert ? tr.dataset.cert.split(',').map(s=>s.trim()) : [];
      show = show && certArr.includes(cert);
    }
    // 기술 태그 다 포함되는지 체크 (교집합 있으면 true)
    if(selectedSkills.length) {
      const skillArr = tr.dataset.skill.split(',').map(s=>s.trim());
      if(!selectedSkills.every(skill => skillArr.includes(skill))) show = false;
    }
    tr.style.display = show ? '' : 'none';
  });
}

// 정렬 함수
function sortTable(key) {
    const activeTable = document.querySelector('.tab-pane.active table tbody');
    const rows = Array.from(activeTable.querySelectorAll('tr')).filter(tr=>tr.style.display!=='none');
    let idx = key==='career' ? 3 : (key==='score'? 10 : 0); // 3:경력, 10:점수(또는 단계별 다름)
    // 단계별 점수 컬럼 위치 맞게 조정 (2차 시험 11, 3차 11)
    if(key==='score'){
        const tab = document.querySelector('.tab-pane.active').id;
        idx = (tab==="step1"? 10 :  (tab==="step2"? 10 : 10));
    }
    // 오름차순/내림차순 토글
    let asc = sortTable.asc = !sortTable.asc;
    rows.sort((a, b) => {
        let va = a.children[idx].innerText.replace(/[^0-9]/g, '') || "0";
        let vb = b.children[idx].innerText.replace(/[^0-9]/g, '') || "0";
        return asc ? va-vb : vb-va;
    });
    rows.forEach(tr => activeTable.appendChild(tr));
}




// ======================= 경력 ========================

// 경력 바형태 슬라이더 생성 (noUiSlider)
window.addEventListener('DOMContentLoaded', function() {
  const minCareer = 0;
  const maxCareer = 10; // 실제 데이터 보고 최대값 조정
  const slider = document.getElementById('careerSlider');
  noUiSlider.create(slider, {
    start: [minCareer, maxCareer],
    connect: true,
    range: { 'min': minCareer, 'max': maxCareer },
    step: 1,
    tooltips: true,
    format: {
      to: value => Math.round(value),
      from: value => Number(value)
    }
  });
  // 슬라이더 값 input에 반영 + 필터
  slider.noUiSlider.on('update', function(values) {
    // document.getElementById('careerMin').value = values[0];
    // document.getElementById('careerMax').value = values[1];
    applyFilters();
  });
});