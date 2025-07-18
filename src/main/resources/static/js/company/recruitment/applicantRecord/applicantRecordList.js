let currentStep = 'step1'; // 초기 단계

// 컬럼 정보(스텝별로 다르면 이렇게 관리)
const columns = {};

function generateColumns(step, type) {
  const baseCols = [
    { key: 'bir', label: '생년월일' },
    { key: 'name', label: '이름' },
    { key: 'resumeNo', label: '이력서' },
    { key: 'career', label: '경력' },
    { key: 'language', label: '어학' },
    { key: 'major', label: '전공' },
    { key: 'cert', label: '자격증' },
    { key: 'skill', label: '기술' },
    { key: 'attend', label: '응시여부' },
    { key: 'pass', label: '합격여부' }
  ];

  // 점수 항목 이름 다르게 처리
  const scoreLabel = type === 'RERP-001' ? '시험점수'
                  : type === 'RERP-002' ? '면접점수'
                  : '점수';

  baseCols.push({ key: 'score', label: scoreLabel });
  baseCols.push({ key: 'passSelect', label: '합격선택' });

  columns["step" + step] = baseCols;
}

function formatCellValue(key, value, applicant) {
  if (key === 'resumeNo' ) {
    return `<button class="btn btn-outline-primary btn-sm resume-link" data-resume-no="${value}" data-user-id="${applicant._userId}">상세</button>`;
  }
  if (key === 'passSelect') {
    const checked = applicant.pass === 'Y' ? 'checked' : '';
    return `<input type="checkbox" class="form-check-input passCheck" ${checked} />`;
  }
  if (Array.isArray(value)) {
    return value.join(', ');
  }
  if (value === null || value === undefined || value === '') {
    return '-';
  }
  return key === 'career' ? `${value}년` : value;
}

function generateRowDataAttributes(applicant) {
  return `
    data-career="${applicant.career}"
    data-language="${applicant.language}"
    data-major="${applicant.major}"
    data-cert="${(applicant.cert || []).join(',')}"
    data-skill="${(applicant.skill || []).join(',')}"
    data-applicant-id="${applicant._applicantId}"
    data-recruitment-no="${applicant._recruitmentNo}"
    data-applicant-name="${applicant.name}"
    data-process-no="${applicant._processNo}"
    data-step="${applicant._step}"
    data-final="${applicant._final}"
  `;
}

let applicantData = {};

// ======================= 테이블랜더 ========================

// 테이블 렌더링 함수
function renderApplicantTable() {
  const tableHead = document.querySelector('#mainTable thead tr');
  const tableBody = document.querySelector('#mainTable tbody');
  
  const columnSet = columns[currentStep];
  const dataList = applicantData[currentStep];
  
  // 컬럼 정의 안 된 경우 안전 처리
  if (!columnSet) {
    tableHead.innerHTML = '<th colspan="100%" class="text-center text-danger">이 단계에 대한 컬럼 정보가 없습니다</th>';
    tableBody.innerHTML = '<tr><td colspan="100%">해당 단계에 대한 컬럼 설정이 필요합니다</td></tr>';
    return;
  }
  
  // 헤더 렌더링
  tableHead.innerHTML = columnSet.map(col => `<th class="text-center">${col.label}</th>`).join('');
  
  // "지원자 ID"가 없는 행 제외
  const validDataList = Array.isArray(dataList)
    ? dataList.filter(row => row.name)
    : [];

  if (validDataList.length === 0) {
    tableBody.innerHTML = ''; // 아무 행도 안 만듦
    return;
  }
  
  // 바디 렌더링
  tableBody.innerHTML = validDataList.map(applicant => {
	return `<tr ${generateRowDataAttributes(applicant)}>
	      ${columnSet.map(col => {
	        const value = applicant[col.key];
	        return `<td>${formatCellValue(col.key, value, applicant)}</td>`;
	      }).join('')}
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
    let selectedApplicants = [];
    activeTable.querySelectorAll('tbody tr').forEach(tr => {
      const check = tr.querySelector('.passCheck');
      if(check && check.checked){
		const applicantId = tr.dataset.applicantId;
      	const applicantName = tr.dataset.applicantName;
      	const recruitmentNo = tr.dataset.recruitmentNo;
      	const recruitProcessNo = tr.dataset.processNo;
      	const recruitProcessFinal = tr.dataset.final;
      	const recruitProcessStep = tr.dataset.step;
      	
      	selectedApplicants.push({
			applicantId,
			applicantName,
			recruitmentNo,
			recruitProcessNo,
			recruitProcessStep,
			recruitProcessFinal
		});
		
        tr.children[9].innerText = 'Y';
      }else{
        tr.children[9].innerText = 'N';
      }
    });
    
    if(selectedApplicants.length === 0){
		alert('선택된 지원자가 없습니다.');
		return;
	}
	
	axios.post(`/applicant/record/pass`, selectedApplicants)
		.then(res=>{
			alert(`마감 완료, ${selectedApplicants.length}명 반영됨`);
			fetchApplicantData(recruitmentNo);
		})
		.catch(err=>{
			console.error('단계 마감 실패', err);
			alert('단계 마감 중 오류가 발생했습니다.');
		});
   	
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

async function fetchApplicantData(recruitmentNo) {
  try {
    const response = await axios.get(`/applicant/record/${recruitmentNo}`);
    const result = response.data;
	
	const stepMetaList = result.map(r => ({
      step: r.STEP,
      step_type: r.STEP_TYPE
    })).filter((v, i, arr) =>
      v.step && arr.findIndex(d => d.step === v.step) === i
    );
    
    stepMetaList.forEach(info => generateColumns(info.step, info.step_type));

    const applicantDataTemp = {};
    result.forEach(row => {
      const stepKey = "step" + row.STEP;
      if (!applicantDataTemp[stepKey]) applicantDataTemp[stepKey] = [];
      
      const resumeNo = row.RESUMEURL
      const userId = row.USERID
      
        applicantDataTemp[stepKey].push({
        bir: row.bir,
        name: row.APPLICANT_NAME,
        resumeNo: resumeNo || "#",
        career: row.career ?? 0,
        language: row.language ?? 0,
        major: row.major ?? "",
        cert: row.cert ?? [],
        skill: row.skill ?? [],
        attend: row.attend ?? "-",
        pass: row.PASS ?? "-",
        score: row.exam_score ?? row.interview_score ?? null,
        
        _userId:userId,
        _recruitmentNo : row.RECRUITMENT_NO,
        _applicantId : row.APPLICANT_ID,
        _processNo : row.PROCESS_NO,
        _step : row.STEP,
        _final : row.FINAL,
      });
    });

    applicantData = applicantDataTemp;
    renderApplicantTable();
    fillFilterOptions();
  } catch (error) {
    console.error('지원자 데이터 가져오기 실패:', error);
  }
}

fetchApplicantData(recruitmentNo);
console.log(applicantData);