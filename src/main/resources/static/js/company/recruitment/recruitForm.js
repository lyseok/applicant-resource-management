/**
 * 
 */
const yearCode = document.getElementById('yearCode');
const eduCode = document.getElementById('eduCode');
const rankCodeList = document.querySelectorAll('.rank');
const cityCode = document.getElementById('cityCode');
const districtCode = document.getElementById('districtCode');

const topJobCode = document.getElementById('upperJobCode');
const hiddenJobCode = document.getElementById('hiddenJobCode');
const jobSearchInput = document.getElementById('jobSearchInput');
const jobSuggestions = document.getElementById('jobSuggestions');

const positionSelect = document.getElementById('positionSelect');
const positionTagWrapper = document.getElementById('positionTagWrapper');

let jobList = [];
let positionCodeMap = {};
let positionTagIndex = 0;

async function bringTopJob() {
  const resp = await axios.get('/ajax/admin/jobCode');
  const list = resp.data;

  topJobCode.innerHTML += list.map(
    ({ topJobCode, topJobName }) =>
      `<option value="${topJobCode}">${topJobName}</option>`
  ).join('');
}

topJobCode.addEventListener('change', async (e) => {
  const selectedTopJobCode = e.target.value;

  const resp = await axios.get(`/ajax/admin/jobCode/${selectedTopJobCode}`);
  jobList = resp.data;

  jobSearchInput.value = '';
  hiddenJobCode.value = '';
  jobSuggestions.style.display = 'none';
});

jobSearchInput.addEventListener('input', () => {
  const keyword = jobSearchInput.value.trim().toLowerCase();

  if (!keyword) {
    jobSuggestions.style.display = 'none';
    return;
  }

  const matches = jobList.filter(({ jobName }) =>
    jobName.toLowerCase().includes(keyword)
  );

  if (matches.length === 0) {
    jobSuggestions.style.display = 'none';
    return;
  }

  jobSuggestions.innerHTML = matches.map(
    ({ jobCode, jobName }) =>
      `<li class="list-group-item" data-code="${jobCode}">${jobName}</li>`
  ).join('');

  jobSuggestions.style.display = 'block';
});

jobSuggestions.addEventListener('click', (e) => {
  if (e.target.tagName === 'LI') {
    const selectedName = e.target.textContent;
    const selectedCode = e.target.dataset.code;

    jobSearchInput.value = selectedName;
    hiddenJobCode.value = selectedCode;
    jobSuggestions.style.display = 'none';
  }
});

document.addEventListener('click', (e) => {
  if (!jobSearchInput.contains(e.target) && !jobSuggestions.contains(e.target)) {
    jobSuggestions.style.display = 'none';
  }
});

bringTopJob();

async function bringCity() {
	const resp = await axios.get("/ajax/admin/cityCode");
	console.log('응답:', resp);

	const list = resp.data;

	cityCode.innerHTML += list.map(
		({ cityCodeNo, cityName }) =>
			`<option value="${cityCodeNo}">${cityName}</option>`
	).join('');
}
bringCity();

cityCode.addEventListener('change', async (e) => {
	const selectedCityCode = e.target.value;

	const resp = await axios.get(`/ajax/admin/cityCode/${selectedCityCode}`)
	const districtList = resp.data;

	districtCode.innerHTML = districtList.map(
		({ districtCodeNo, districtName }) =>
			`<option value="${districtCodeNo}">${districtName}</option>`
	).join('');
})

async function initPositionSelect() {
  try {
    const [rankResp, posiResp] = await Promise.all([
      axios.get('/ajax/company/cmncodegroup/RANK'),
      axios.get('/ajax/company/cmncodegroup/SEAT')
    ]);

    const rankList = rankResp.data.cmnCodeList;
    const posiList = posiResp.data.cmnCodeList;
    const fullList = [...rankList, ...posiList];

    positionCodeMap = Object.fromEntries(
      fullList.map(({ codeDetailNo, codeName }) => [codeDetailNo, codeName])
    );

    positionSelect.innerHTML = `
      <option disabled selected>선택</option>
      <optgroup label="직급">
        ${rankList.map(({ codeDetailNo, codeName }) => `<option value="${codeDetailNo}">${codeName}</option>`).join('')}
      </optgroup>
      <optgroup label="직책">
        ${posiList.map(({ codeDetailNo, codeName }) => `<option value="${codeDetailNo}">${codeName}</option>`).join('')}
      </optgroup>
    `;
  } catch (err) {
    console.error('직급/직책 불러오기 실패', err);
  }
}

// 선택된 값 → 태그 UI + hidden input
function addPositionTag() {
  const selectedCode = positionSelect.value;
  const selectedText = positionCodeMap[selectedCode];

  // 아무것도 선택 안 했으면 추가하지 않음
  if (!selectedCode || !selectedText) {
    alert("직급/직책을 선택해주세요.");
    return;
  }

  const tag = document.createElement('span');
  tag.className = 'position-tag';

  tag.innerHTML = `
    ${selectedText}
    <button type="button" onclick="this.closest('span').remove()">x</button>
    <input type="hidden" name="positionList[${positionTagIndex}].codeDetailNo" value="${selectedCode}" />
  `;

  positionTagWrapper.appendChild(tag);
  positionSelect.value = ''; // 초기화
  positionTagIndex++;
}

initPositionSelect();

let skillIndex = 1;
function addSkill() {
	const input = document.getElementById('skillInput');
	const skillName = input.value.trim();
	if (!skillName) return;

	const wrapper = document.getElementById('skillWrapper');

	const tag = document.createElement('span');
	tag.className = 'skill-tag';
	tag.innerHTML = `
	   #${skillName}
	   <button type="button" aria-label="삭제">&times;</button>
	   <input type="hidden" name="skillList[${skillIndex}].recruitSkillName" value="${skillName}">
	 `;

	tag.querySelector('button').addEventListener('click', () => tag.remove());

	wrapper.appendChild(tag);
	input.value = '';
	skillIndex++;
}


async function bringYear() {

	const resp = await axios.get('/ajax/company/cmncodegroup/YEXP');
	console.log('응답:', resp);

	const yearCodeList = resp.data.cmnCodeList;

	if (Array.isArray(yearCodeList)) {
		const yearOptions = yearCodeList.map(({ codeDetailNo, codeName }) =>
			`<option value="${codeDetailNo}">${codeName}</option>`
		).join('\n');

		yearCode.innerHTML += yearOptions;
	} else {
		console.error('연차 코드 응답이 배열이 아닙니다:', yearCodeList);
	}
}
bringYear();


async function bringEdu() {
	const eduResp = await axios.get('/ajax/company/cmncodegroup/EDUC');
	console.log('응답:', eduResp);

	const eduCodeList = eduResp.data.cmnCodeList;

	if (Array.isArray(eduCodeList)) {
		const eduOptions = eduCodeList.map(({ codeDetailNo, codeName }) =>
			`<option value="${codeDetailNo}">${codeName}</option>`
		).join('\n');

		eduCode.innerHTML += eduOptions;
	} else {
		console.error('연차 코드 응답이 배열이 아닙니다:', eduCodeLists);
	}
}
bringEdu();

const uploadImages = new Set();
const editor = new toastui.Editor({
	el: document.querySelector('#editor'),
	height: '400px',
	initialEditType: 'wysiwyg',
	previewStyle: 'vertical',
	hooks: {
		async addImageBlobHook(blob, callback) {
			const formData = new FormData();
			formData.append('file', blob);
			try {
				const res = await axios.post('/upload/editor', formData, {
					headers: { 'Content-Type': 'multipart/form-data' }
				});

				const imageUrl = res.data.url;
				uploadImages.add(imageUrl);
				callback(res.data.url, '이미지');
			} catch (err) {
				alert('이미지 업로드 실패');
				console.error(err);
			}
		}
	}
});

document.getElementById('recruitForm').addEventListener('submit', (e) => {
	document.getElementById('recContent').value = editor.getHTML();
});

let processIndex = 0;

function addProcess() {
	const wrapper = document.getElementById('processSection');

	const processBlock = document.createElement('div');
	processBlock.className = 'border p-3 rounded position-relative';
	processBlock.style.minWidth = '300px';

	const removeBtn = document.createElement('button');
	removeBtn.type = 'button';
	removeBtn.className = 'btn-close position-absolute top-0 end-0 m-2';
	removeBtn.onclick = () => processBlock.remove();

	processBlock.innerHTML = `
     <div class="mb-2">
       <label class="form-label">전형 단계</label>
       <select name="recruitProcessList[${processIndex}].recruitProcessStep" class="form-select">
         <option value="1">1단계</option>
         <option value="2">2단계</option>
         <option value="3">3단계</option>
         <option value="4">4단계</option>
         <option value="5">5단계</option>
       </select>
     </div>

     <div class="mb-2">
       <label class="form-label">최종 전형 여부</label>
       <select name="recruitProcessList[${processIndex}].recruitProcessFinal" class="form-select">
         <option value="N">아니오</option>
         <option value="Y">예</option>
       </select>
     </div>

     <div class="mb-2">
       <label class="form-label">전형 유형</label>
       <select class="form-select processTypeSelect">
         <option value="RERP-003">서류</option>
         <option value="RERP-002">면접</option>
         <option value="RERP-001">시험</option>
       </select>
     </div>

     <div class="process-detail mt-2">
       <!-- 전형 유형에 따른 상세 폼이 여기 삽입 -->
     </div>
   `;

	processBlock.appendChild(removeBtn);
	wrapper.appendChild(processBlock);
	initProcessTypeSelect(processBlock);
	processIndex++;
}

function initProcessTypeSelect(container) {
	const select = container.querySelector('.processTypeSelect');
	const detailContainer = container.querySelector('.process-detail');

	select.addEventListener('change', async () => {
		const type = select.value;

		if (type === 'RERP-002') {
			detailContainer.innerHTML = `
        <div>
          <label class="form-label">면접 일시</label>
          <input type="datetime-local" name="recruitProcessList[${processIndex}].interviewList[0].interviewDate" class="form-control">
        </div>
        <div class="mt-2">
          <label class="form-label">면접 장소</label>
          <input type="text" name="recruitProcessList[${processIndex}].interviewList[0].interviewLocation" class="form-control">
        </div>
        <div class="mt-2">
          <label class="form-label">면접 방식</label>
          <select name="recruitProcessList[${processIndex}].interviewList[0].interviewType" class="form-select">
		    <option value="Y">화상</option>
		    <option value="N">대면</option>
		  </select>
        </div>
        <div class="mt-2">
          <label class="form-label">합격 점수</label>
          <input type="text" name="recruitProcessList[${processIndex}].interviewList[0].interviewPassScore" class="form-control">
        </div>
      `;
		} else if (type === 'RERP-001') {
			detailContainer.innerHTML = `
      	<div>
      		<label class="form-label">합격 점수</label>
      		<input type="text" name="recruitProcessList[${processIndex}].recruitmentExamList[0].recruitExamCutline" class="form-control">
      	</div>
      	<div>
      		<label class="form-label">시험 일시</label>
      		<input type="datetime-local" name="recruitProcessList[${processIndex}].recruitmentExamList[0].recruitExamStartDate" class="form-control">
      	</div>
      	<div>
      		<label class="form-label">시험 시간(분)</label>
      		<input type="text" name="recruitProcessList[${processIndex}].recruitmentExamList[0].recruitExamTime" class="form-control">
      	</div>
        <div>
          <label class="form-label">시험 선택</label>
          <select name="recruitProcessList[${processIndex}].recruitmentExamList[0].comExamNo" class="form-select exam-select">
            <option value="">시험을 선택하세요</option>
            <!-- 시험 목록 동적 로딩 필요 시 여기에 처리 -->
          </select>
        </div>
      `;
			const examSelect = detailContainer.querySelector('.exam-select');
			const resp = await axios.get('/ajax/company/company_exam/list');
			console.log(resp);
			const list = resp.data;
			examSelect.innerHTML += list.map(
				({ comExamNo, comExamName }) =>
					`<option value="${comExamNo}">${comExamName}</option>`
			).join('');
		} else {
			detailContainer.innerHTML = '';
		}
	});

	select.dispatchEvent(new Event('change'));
}

document.getElementById('recruitForm').addEventListener('submit', async (e) => {
	e.preventDefault();

	document.getElementById('recContent').value = editor.getHTML();

	const contentHTML = editor.getHTML();
	const usedImages = Array.from(contentHTML.matchAll(/<img[^>]*src="([^"]+)"[^>]*>/g)).map(m => m[1]);

	const unusedImages = Array.from(uploadImages).filter(img => !usedImages.includes(img));

	for (const url of unusedImages) {
		try {
			await axios.delete(`/upload/editor`, { url });
		} catch (err) {
			console.warn('이미지 삭제 실패 :', url, err);
		}
	}
	

	const form = e.target;

	const notice = {
		recruitmentTitle: form.recruitmentTitle.value,
		yearCode: form.yearCode.value,
		jobCode: form.jobCode.value,
		recruitmentChargerTel: form.recruitmentChargerTel.value,
		cityCode: form.cityCode.value,
		districtCode: form.districtCode.value,
		recruitmentDesk: form.recruitmentDesk.value,
		recruitmentSalary: form.recruitmentSalary.value,
		recPositionNumber: form.recPositionNumber.value,
		recruitmentFinishDate: form.recruitmentFinishDate.value,
		recContent: form.recContent.value,
		education: {
			codeDetailNo: form['education.codeDetailNo'].value
		}
	};

	notice.positionList = [...document.querySelectorAll('#positionTagWrapper input[type="hidden"]')]
		.map(input => ({
			codeDetailNo: input.value
	}));

	notice.skillList = [...document.querySelectorAll('#skillWrapper input')].map(input => ({
		recruitSkillName: input.value
	}));

	notice.processList = [...document.querySelectorAll('#processSection > .border')].map((el, idx) => {
		const step = el.querySelector('[name*="recruitProcessStep"]').value;
		const isFinal = el.querySelector('[name*="recruitProcessFinal"]').value;
		const type = el.querySelector('.processTypeSelect').value;

		const process = {
			recruitProcessStep: step,
			recruitProcessFinal: isFinal,
			recruitProcessType: type
		};

		if (type === 'RERP-002') {
			process.interviewList = [{
				interviewDate: el.querySelector('[name*="interviewDate"]').value,
				interviewLocation: el.querySelector('[name*="interviewLocation"]').value,
				interviewType: el.querySelector('[name*="interviewType"]').value,
				interviewPassScore: el.querySelector('[name*="interviewPassScore"]').value
			}];
		}
		if (type === 'RERP-001') {
			process.recruitmentExamList = [{
				recruitExamCutline: el.querySelector('[name*="recruitExamCutline"]').value,
				recruitExamStartDate: el.querySelector('[name*="recruitExamStartDate"]').value,
				recruitExamTime: el.querySelector('[name*="recruitExamTime"]').value,
				comExamNo: el.querySelector('[name*="comExamNo"]').value
			}];
		}
		return process;
	});

	await axios.post('/ajax/recruit/notice', notice)
		.then(res => {
			alert('저장 성공!');
			location.href = `/company/recruit_notice/${res.data}`;
		})
		.catch(err => {
			if (err.response && err.response.data) {
				const errors = err.response.data;

				// 🔸 모든 기존 에러 메시 지우기
				document.querySelectorAll('.text-danger.small').forEach(el => el.remove());

				let firstErrorMessage = '';

				Object.entries(errors).forEach(([field, messages], idx) => {
					const el = document.querySelector(`[name="${field}"]`);

					// 🔸 첫 번째 메시지를 alert로 띄우기 위해 저장
					if (idx === 0) firstErrorMessage = messages.join(', ');

					if (el) {
						const span = document.createElement('span');
						span.className = 'text-danger small';
						span.textContent = messages.join(', ');
						el.insertAdjacentElement('afterend', span);
					}
				});

				if (firstErrorMessage) {
					alert(firstErrorMessage); // ✅ 제일 처음 오류 메시지 alert로 표시
				}
			} else {
				alert('저장 중 에러 발생');
			}
		});

});