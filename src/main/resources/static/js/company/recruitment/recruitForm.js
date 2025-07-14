/**
 * 
 */
const yearCode = document.getElementById('yearCode');
const eduCode = document.getElementById('eduCode');
const rankCodeList = document.querySelectorAll('.rank');
const topJobCode = document.getElementById('upperJobCode');
const jobCode = document.getElementById('jobCode');
const cityCode = document.getElementById('cityCode');
const districtCode = document.getElementById('districtCode');

async function bringTobJob() {

	const resp = await axios.get('/ajax/admin/jobCode');
	console.log('응답:', resp);

	const list = resp.data;
	
	topJobCode.innerHTML += list.map(
	      ({ topJobCode, topJobName }) =>
	        `<option value="${topJobCode}">${topJobName}</option>`
	    ).join('');
	  } 
bringTobJob();

topJobCode.addEventListener('change', async (e)=>{
	const selectedTopJobCode = e.target.value;
	
	const resp = await axios.get(`/ajax/admin/jobCode/${selectedTopJobCode}`);
	const jobList = resp.data;
	
	jobCode.innerHTML += jobList.map(
		      ({ jobCode, jobName }) =>
		        `<option value="${jobCode}">${jobName}</option>`
		    ).join('');
})

async function bringCity(){
	const resp = await axios.get("/ajax/admin/cityCode");
	console.log('응답:', resp);
	
	const list = resp.data;
	
	cityCode.innerHTML += list.map(
		      ({ cityCodeNo, cityName }) =>
		        `<option value="${cityCodeNo}">${cityName}</option>`
		    ).join('');
}
bringCity();

cityCode.addEventListener('change', async(e)=>{
	const selectedCityCode = e.target.value;
	
	const resp = await axios.get(`/ajax/admin/cityCode/${selectedCityCode}`)
	const districtList = resp.data;
	
	districtCode.innerHTML += districtList.map(
		({districtCodeNo, districtName}) =>
			`<option value="${districtCodeNo}">${districtName}</option>`
		).join('');
})

async function bringPosition(targetSelect) {
  try {
    const [rankResp, posiResp] = await Promise.all([
      axios.get('/ajax/company/cmncodegroup/RANK'),
      axios.get('/ajax/company/cmncodegroup/SEAT')
    ]);

    const rankOptions = rankResp.data.cmnCodeList.map(({ codeDetailNo, codeName }) =>
      `<option value="${codeDetailNo}">${codeName}</option>`
    ).join('\n');

    const posiOptions = posiResp.data.cmnCodeList.map(({ codeDetailNo, codeName }) =>
      `<option value="${codeDetailNo}">${codeName}</option>`
    ).join('\n');

    targetSelect.innerHTML = `
      <option disabled selected>선택</option>
      <optgroup label="직급">
        ${rankOptions}
      </optgroup>
      <optgroup label="직책">
        ${posiOptions}
      </optgroup>
    `;
  } catch (err) {
    console.error('직급/직책 코드 로딩 실패', err);
  }
}

 rankCodeList.forEach(select => bringPosition(select));

let positionIndex = 1;
function addPosition() {
	const wrapper = document.getElementById('positionWrapper');
	const newSelect = document.createElement('select');
	newSelect.name = `positionList[${positionIndex}].codeDetailName`;
	newSelect.className = "form-select mb-2 rank";
	wrapper.appendChild(newSelect);
	bringPosition(newSelect);
	positionIndex++;
}

let skillIndex = 1;
function addSkill() {
	const wrapper = document.getElementById('skillWrapper');
	const newInput = document.createElement('input');
	newInput.type = "text";
	newInput.name = `skillList[${skillIndex}].recruitSkillName`;
	newInput.className = "form-control mb-2";
	wrapper.appendChild(newInput);
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

const editor = new toastui.Editor({
   el: document.querySelector('#editor'),
   height: '400px',
   initialEditType: 'wysiwyg',  // wysiwyg or markdown
   previewStyle: 'vertical',
   placeholder: '공고 내용을 입력하세요...'
});

let processIndex = 0;

function addProcess() {
  const wrapper = document.getElementById('processSection');

  const processBlock = document.createElement('div');
  processBlock.className = 'border p-3 rounded';
  processBlock.style.minWidth = '300px';
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
        <option value="Y">예</option>
        <option value="N">아니오</option>
      </select>
    </div>

    <div class="mb-2">
      <label class="form-label">전형 유형</label>
      <select class="form-select processTypeSelect">
	  	<option value="">서류</option>
        <option value="interview">면접</option>
        <option value="exam">시험</option>
      </select>
    </div>

    <div class="process-detail mt-2">
      <!-- 전형 유형에 따른 상세 폼이 여기 삽입 -->
    </div>
  `;

  wrapper.appendChild(processBlock);
  initProcessTypeSelect(processBlock);
  processIndex++;
}

function initProcessTypeSelect(container) {
  const select = container.querySelector('.processTypeSelect');
  const detailContainer = container.querySelector('.process-detail');

  select.addEventListener('change', () => {
    const type = select.value;

    if (type === 'interview') {
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
          <input type="text" name="recruitProcessList[${processIndex}].interviewList[0].interviewType" class="form-control">
        </div>
      `;
    } else if (type === 'exam') {
      detailContainer.innerHTML = `
        <div>
          <label class="form-label">시험 선택</label>
          <select name="recruitProcessList[${processIndex}].recruitmentExamList[0].recruitExamNo" class="form-select">
            <option value="">시험을 선택하세요</option>
            <!-- 시험 목록 동적 로딩 필요 시 여기에 처리 -->
          </select>
        </div>
      `;
    } else {
      detailContainer.innerHTML = '';
    }
  });

  select.dispatchEvent(new Event('change'));
}

