let currentStep = 'step1'; // 초기 단계

// 로딩 show/hide 함수
function showLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'none', 'important');
}

// 컬럼 정보(스텝별로 다르면 이렇게 관리)
const columns = {};

const STEP_TYPE_LABELS ={
	'RERP-001' : '시험',
	'RERP-002' : '면접',
	'RERP-003' : '서류'
};

const FINAL_STEP_KEY = 'final';

function generateColumns(step, type) {
  const baseCols = [
    { key: 'bir', label: '생년월일' },
    { key: 'name', label: '이름' },
    { key: 'resumeNo', label: '이력서' },
    { key: 'career', label: '경력' },
    { key: 'language', label: '어학' },
    { key: 'major', label: '전공' },
    { key: 'cert', label: '자격증' },
    { key: 'skill', label: '기술' }
  ];

  if(step === FINAL_STEP_KEY){
	baseCols.push({key : 'alarm', label : '합격 알림 여부'});
	baseCols.push({key : 'accept', label : '채용 수락 여부'});
	baseCols.push({key : 'hireDate', label : '입사 예정일'});
	baseCols.push({key : 'passSelect', label : '메일 발송'});
  }else{
	baseCols.push({key : 'attend', label : '응시 여부'});
	baseCols.push({key : 'pass', label : '합격 여부'});
	const scoreLabel = type === 'RERP-001' ? '시험점수'
					: type === 'RERP-002' ? '면접점수'
					: '점수';
	baseCols.push({key : 'score', label: scoreLabel});
	baseCols.push({key : 'passSelect', label: '합격선택'});
  }
  columns[step === FINAL_STEP_KEY ? FINAL_STEP_KEY : "step" + step] = baseCols;
}

function formatCellValue(key, value, applicant) {
  if (key === 'resumeNo' ) {
    return `<button class="btn btn-outline-primary btn-sm resume-link" 
    		data-resume-no="${value}" data-user-id="${applicant._userId}" data-applicant-id="${applicant._applicantId}">상세</button>`;
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
  return key === 'career' ? `${value}` : value;
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
	console.log("render", currentStep, columns[currentStep], applicantData[currentStep]);
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
  const validDataList = Array.isArray(dataList) ? dataList.filter(row => row.name): [];
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

// 필터링 초기화
document.getElementById('resetFilters').addEventListener('click', function(){
  // 슬라이더 초기화
  const slider = document.getElementById('careerSlider');
  if (slider && slider.noUiSlider) {
    slider.noUiSlider.set([0, 10]); // 초기범위로 다시 설정
  }

  // 드롭다운 초기화
  document.getElementById('languageFilter').value = "";
  document.getElementById('majorFilter').value = "";
  document.getElementById('certFilter').value = "";

  // 기술 태그 체크박스 초기화
  Array.from(document.querySelectorAll('#skillTagFilter input')).forEach(cb => cb.checked = false);

  // 이름 검색 input 초기화
  document.getElementById('searchInput').value = "";

  // 필터 적용
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

function closeStep() {
    const activeTable = document.querySelector('.tab-pane.active table');
    let selectedApplicants = [];   // 합격자
    let unselectedApplicants = []; // 불합격자
    let recruitmentNo = null;

    activeTable.querySelectorAll('tbody tr').forEach(tr => {
        const check = tr.querySelector('.passCheck');
        const applicantId = tr.dataset.applicantId;
        const applicantName = tr.dataset.applicantName;
        const recruitProcessNo = tr.dataset.processNo;
        const recruitProcessFinal = tr.dataset.final;
        const recruitProcessStep = tr.dataset.step;
        recruitmentNo = tr.dataset.recruitmentNo;

        const applicantData = {
            applicantId,
            applicantName,
            recruitmentNo,
            recruitProcessNo,
            recruitProcessStep,
            recruitProcessFinal
        };

        if (check && check.checked) {
            selectedApplicants.push(applicantData);
            tr.children[9].innerText = 'Y'; // 합격
        } else {
            unselectedApplicants.push(applicantData);
            tr.children[9].innerText = 'N'; // 불합격
        }
    });

    if (selectedApplicants.length === 0 && unselectedApplicants.length === 0) {
        alert('지원자가 없습니다.');
        return;
    }

    // 합격 & 불합격 비동기 처리
    const requests = [];
    if (selectedApplicants.length > 0) {
        requests.push(axios.post(`/applicant/record/pass`, selectedApplicants));
    }
    if (unselectedApplicants.length > 0) {
        requests.push(axios.post(`/applicant/record/fail`, unselectedApplicants));
    }

    Promise.all(requests)
        .then(responses => {
            alert(`단계 마감 완료\n합격: ${selectedApplicants.length}명\n불합격: ${unselectedApplicants.length}명`);
            fetchApplicantData(recruitmentNo);
        })
        .catch(err => {
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

async function fetchApplicantData(recruitmentNo, desiredStep = null) {
  try {
    const response = await axios.get(`/applicant/record/${recruitmentNo}`);
    const result = response.data;
	
	const stepMetaList = result.map(r => ({
      step: r.STEP,
      step_type: r.STEP_TYPE
    })).filter((v, i, arr) =>
      v.step && arr.findIndex(d => d.step === v.step) === i);
    
    stepMetaList.forEach(info => generateColumns(info.step, info.step_type));
	renderStepTabs(stepMetaList); // 여기에 탭 생성 추가

    const applicantDataTemp = {};
    result.forEach(row => {
      const stepKey = "step" + row.STEP;
      if (!applicantDataTemp[stepKey]) applicantDataTemp[stepKey] = [];
      
      
		let score = null;
		if (row.STEP_TYPE === 'RERP-001') score = row.EXAM_SCORE;
		else if (row.STEP_TYPE === 'RERP-002') score = row.INTERVIEW_SCORE;

      
        applicantDataTemp[stepKey].push({
        bir: row.bir,
        name: row.APPLICANT_NAME,
        career: row.career ?? 0,
        language: row.language ?? 0,
        major: row.major ?? "",
        cert: row.cert ?? [],
        skill: row.skill ?? [],
        attend: row.ATTEND ?? "-",
        pass: row.PASS ?? "-",
        score: score,
        
        _recruitmentNo : row.RECRUITMENT_NO,
        _applicantId : row.APPLICANT_ID,
        _processNo : row.PROCESS_NO,
        _step : row.STEP,
        _final : row.FINAL,
      });
    });

    applicantData = applicantDataTemp;
    await fetchFinalPassers(recruitmentNo);
    generateColumns(FINAL_STEP_KEY);
    await fetchResumeDetail();
    currentStep = desiredStep || 'step' + stepMetaList[0]?.step;
    updateStepActionButton();
    renderApplicantTable();
    fillFilterOptions();
  } catch (error) {
    console.error('지원자 데이터 가져오기 실패:', error);
  }
}

fetchApplicantData(recruitmentNo);
console.log(applicantData);

function renderStepTabs(stepMetaList){
	const $nav = document.querySelector('.nav-tabs');
	$nav.innerHTML = '';
	
	stepMetaList.forEach((stepInfo, idx)=>{
		const step = stepInfo.step;
		const type = STEP_TYPE_LABELS[stepInfo.step_type] || '단계';
		const isActive = idx === 0 ? 'active' : '';
		const li = document.createElement('li');
		li.className = 'nav-item';
		li.innerHTML = `<button class="nav-link ${isActive}" data-step="step${step}">${step}차 ${type}</button>`;
		$nav.appendChild(li);
	});
	
	// 최종 합격자
	const finalTab = document.createElement('li');
	finalTab.className = 'nav-item';
	finalTab.innerHTML = `<button class="nav-link" data-step="${FINAL_STEP_KEY}">최종 합격자</button>`;
	$nav.appendChild(finalTab);
	
	document.querySelectorAll('.nav-link[data-step]').forEach(btn =>{
		btn.addEventListener('click', function (){
			document.querySelectorAll('.nav-link[data-step]').forEach(b=>b.classList.remove('active'));
			this.classList.add('active');
			currentStep = this.getAttribute('data-step');
			updateStepActionButton();
			renderApplicantTable();
		});
	});
}

function updateStepActionButton(){
	const $btn = document.getElementById('stepActionBtn');
	if (!$btn) return; // 버튼이 없으면 무시
	
	if(currentStep === FINAL_STEP_KEY){
		$btn.textContent = '입사일 변경';
		$btn.onclick = () =>{
			const modal = new bootstrap.Modal(document.getElementById('hireDateModal'));
			const overlay = document.getElementById('overlay');
			if (overlay) overlay.style.display = 'none';
			modal.show();
		};
	}else{
		$btn.textContent = '단계 마감';
		$btn.onclick = closeStep;
	}
}

document.getElementById('confirmHireDateBtn').addEventListener('click', ()=>{
	const date = document.getElementById('hireDatePicker').value;
	const formatDate = date.replace(/-/g, '');
	if(!date){
		alert('입사일을 선택해 주세요.');
		return;
	}
	
	const finalApplicants = applicantData[FINAL_STEP_KEY] || [];
	const payload = finalApplicants.map(a=>({
		applicantId : a._applicantId,
		recruitmentNo : a._recruitmentNo,
		hireDate : formatDate
	}));
	
	if(payload.length === 0){
		alert("최종합격자 데이터가 없습니다.");
		return;
	}
	
	showLoading();
	axios.post('/applicant/record/hiredate', payload)
		.then(()=>{
			alert("입사 예정일이 변경되었습니다.");
			fetchApplicantData(payload[0].recruitmentNo, FINAL_STEP_KEY);
			bootstrap.Modal.getInstance(document.getElementById('hireDateModal')).hide();
		})
		.catch(err=>{
			console.error('입사일 업데이트 실패', err);
			alert("입사 예정일 변경 실패");
		})
		.finally(()=>{
			hideLoading();
		})
})

async function fetchFinalPassers(recruitmentNo){
	try{
		const response = await axios.get(`/applicant/record/passer/${recruitmentNo}`);
		const result = response.data;
		
		generateColumns(FINAL_STEP_KEY);
		
		const mergedFinalList = [];
		
		// 모든 step에서 지원자 데이터 하나의 배열로 flatten
		const allApplicants = Object.values(applicantData).flat();
		
		result.forEach(passer => {
			const base = allApplicants.find(a => a._applicantId === passer.applicantId);
			if (!base) return;

			mergedFinalList.push({
				...base, // 기존 applicant 정보
				alarm: passer.passAlarmYn,
				accept: passer.recruitAcceptYn,
				hireDate: passer.hireDate ?? "-",
				passSelect: true,
				_passerNo: passer.passerNo,
				_recruitmentNo : base._recruitmentNo ?? passer.recruitmentNo ?? recruitmentNo
			});
		});

		applicantData[FINAL_STEP_KEY] = mergedFinalList;
	} catch (e) {
		console.error('최종합격자 조회 실패:', e);
	}
}

function mergeResumeDetail(resumeDetailList){
	console.log('병합 대상 resume array:', resumeDetailList);
	const allApplicants = Object.values(applicantData).flat();

	resumeDetailList.forEach(resume => {
		const applicantId = resume.applicantId;
		if (!applicantId || !resume) return;

		const targets = allApplicants.filter(app => app._applicantId === applicantId);
		if (targets.length === 0) return;

		targets.forEach(app => {
			app.bir = app.bir || resume.birth;
			app.career = app.career || getHighestCareerYear(resume.careerList);
			app.language = app.language || (resume.languageSkillList?.map(l => l.languageExamScore).join(', ') || '');
			app.cert = app.cert?.length ? app.cert : (resume.myLicenseList?.map(l => l.licenseCode) || []);
			app.skill = app.skill?.length ? app.skill : (resume.mySkillList?.map(s => s.mySkillName) || []);
			app.major = app.major || (resume.educationList?.[0]?.departmentCode || '');
			
			app.resumeNo = resume.resumeNo || "";
			app._userId = resume.userId || app._userId;
		});
	});
}

async function fetchResumeDetail(){
	const allApplicants = Object.values(applicantData).flat();

	const targetIds = [...new Set(
		allApplicants
			.filter(a => a._applicantId && (!a.bir || !a.career || !a.language || !a.major || !a.cert?.length || !a.skill?.length))
			.map(a => a._applicantId)
	)];

	if (targetIds.length === 0) return;

	try {
		const response = await axios.post('/applicant/record/resume', targetIds); // 서버에서 applicantId로 처리
		const resumeDetailList = response.data;

		mergeResumeDetail(resumeDetailList); // applicantId 기준 병합
		renderApplicantTable();
	} catch (err) {
		console.error("상세 이력서 정보 가져오기 실패", err);
	}
}

document.addEventListener('click', function(e) {
  const $target = e.target.closest('.resume-link');
  if (!$target) return;

  const resumeNo = $target.dataset.resumeNo;
  const userId = $target.dataset.userId;
  const applicantId = $target.dataset.applicantId;

  if (!resumeNo || !userId) {
    alert('이력서 정보가 부족합니다.');
    return;
  }
  
  axios.post(`/applicant/record/${applicantId}`)
  	.then(()=>{
		console.log('이력서 열람');
	})
	.catch(err=>{
		console.error('이력서 열람 실패');
	})

  const width = 1000;
  const height = 800;

  window.open(
        '/popup/resume/' + resumeNo,
        'resumePopup',
        'width=950,height=800'
      );
});

function getHighestCareerYear(careerList) {
  if (!Array.isArray(careerList) || careerList.length === 0) return 0;

  const extractYear = (name) => {
    if (!name) return 0;
    const match = name.match(/(\d+)(년)/g);
    if (!match) return 0;

    // 예: "6년 ~ 7년" → 7 추출
    const nums = match.map(m => parseInt(m.replace('년', ''), 10));
    return Math.max(...nums);
  };

  const sorted = careerList
    .filter(c => c.careerYearName)
    .sort((a, b) => extractYear(b.careerYearName) - extractYear(a.careerYearName));

  return sorted[0]?.careerYearName || 0;
}