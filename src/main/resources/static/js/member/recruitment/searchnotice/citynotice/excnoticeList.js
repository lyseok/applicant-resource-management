/**
 * 
 */
//보낼 값 객체(axios로 바꿔서)
let params = {
    page: 1,
    pageSize: 20,
    districtCode: [],
    jobCode: [],
    keyword: '프론트엔드'
  }
  
//------------------------지역선택 옵션생성-------------------------------------------
const first_cityopt = document.querySelector('#first_cityopt > ul');
const second_cityopt = document.querySelector('#second_cityopt');
const add_keyword = document.querySelector('.add_keyword');
const search_btn = document.querySelector('#search_btn');  //검색하기 버튼 클릭후 콘솔에 찍히는 params 값 보기->axios로 넘기기만 하면됨

// 도시 선택 값 불러옴 (axios + async/await)
const getCityCodeList = async function () {
  try {
    const response = await axios.get('/ajax/admin/cityCode');
    const data = response.data;

    data.forEach(city => {
      let cityCodeNo = city.cityCodeNo.slice(4);
      let cityName = city.cityName;
      setCityCodeList(cityCodeNo, cityName);  // 시 선택 값 채우기
    });

  } catch (error) {
    console.error("도시 데이터 로드 실패:", error);
  }
};

//도시 선택 값 채우기
const setCityCodeList = function(cityCodeNo, cityName){
	let html = `
			<li class="depth1_btn_wrapper" id="depth1_btn_${cityCodeNo}">
	        	<button type="button" class="depth1_btn_${cityCodeNo}" data-code="${cityCodeNo}" onclick="selectCity(this)">
	                <span class="txt">${cityName}</span>
	                <span class="count">(58,664)</span>
	            </button>
	        </li>`;
	first_cityopt.innerHTML += html;
	
	html = `<ul class="list_check" id="sp_area_lastDepth_${cityCodeNo}" style="display: none;"></ul>`;
	second_cityopt.innerHTML += html;  //도시 수만큼 생김
	
	getDistrictCodeList(cityCodeNo);  //구군 선택 값 호출
}

// 구군 선택 값 불러옴 (axios + async/await)
const getDistrictCodeList = async function (cityCodeNo) {
  try {
    const response = await axios.get(`/ajax/admin/cityCode/CICO${cityCodeNo}`);  // 예: CICO115000
    const data = response.data;

    data.forEach(district => {
      let districtCodeNo = district.districtCodeNo;
      let cityCodeNo = district.cityCodeNo.slice(4);
      let districtName = district.districtName;
      setDistrictCodeList(districtCodeNo, cityCodeNo, districtName);  // 구군 선택 값 채우기
    });

  } catch (error) {
    console.error("구군 데이터 로드 실패:", error);
  }
};

//구군 선택 값 채우기
const setDistrictCodeList = function(districtCodeNo, cityCodeNo, districtName){
	let sp_area_lastDepth =  document.querySelector(`#sp_area_lastDepth_${cityCodeNo}`);
	if (districtName.includes('전체') || districtName === '전국') {
    	html = `<li>
			        <div class="inpChk">
			            <input type="checkbox" id="loc_mcd_${districtCodeNo}" name="loc_mcd[]" value="${districtCodeNo}" 
			                data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type="all"
			                onclick="selectDistrict(this, '${cityCodeNo}', '${districtName}')">
			            <label for="loc_mcd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span></label>
			        </div>
			    </li>`;
	} else {
	    html = `<li>
			        <div class="inpChk">
			            <input type="checkbox" id="loc_cd_${districtCodeNo}" name="loc_cd[]" value="${districtCodeNo}" 
			                data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type=""
			                onclick="selectDistrict(this, '${cityCodeNo}', '${districtName}')">
			            <label for="loc_cd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span><span class="count"> (1,180)</span></label>
			        </div>
			    </li>`;
	}
    sp_area_lastDepth.innerHTML += html;  //구군 수만큼 생김
}

getCityCodeList();  //도시 선택 값 호출

//--------------------- 지역 선택 동적클래스--------------------------------------------------------------------

const selectedContainer = add_keyword.querySelector('#sp_preview_selected');
const previewWrapper = document.getElementById('sp_preview'); // 패널 래퍼

//도시 선택 중첩
const selectCity = function (button) {
    let currentLi = button.closest('li');

    // 1. 모든 li의 on 제거 (selected는 유지)
    document.querySelectorAll('#first_cityopt li').forEach(li => {
        li.classList.remove('on');
    });

    // 2. 클릭한 li에 selected 추가 + on 추가
    currentLi.classList.add('selected', 'on');

    // 3. 모든 하위 구·군 영역 숨기기
    document.querySelectorAll('#second_cityopt .list_check').forEach(ul => ul.style.display = 'none');

    // 4. 클릭한 li의 하위 영역만 표시
    document.querySelector(`#sp_area_lastDepth_${button.dataset.code}`).style.display = 'block';
}

// 지역 전체 선택 시 분기
const selectDistrict = function (checkbox, code, mcode) {
    if (checkbox.dataset.checkType === "all") {
        // 1. 하위 구군 체크박스 모두 해제
        const subDistricts = document.querySelectorAll(`input[name="loc_cd[]"][data-mcode="${mcode}"]`);
        subDistricts.forEach(cb => {
            cb.checked = false;
            removeKeywordSpan(cb.value); // 선택 키워드에서도 제거
        });
    } else {
        // 2. 하위 단위 클릭 시 시도 전체 체크 해제
        const allCheckbox = document.querySelector(`input[name="loc_mcd[]"][data-mcode="${mcode}"]`);
        if (allCheckbox) allCheckbox.checked = false;
    }

    // 선택된 지역 span 갱신
    updateSelectedRegions(code, mcode);
}

// 선택된 지역 span 갱신
const updateSelectedRegions = function (districtName, cityName) {
    // 기존 지역 관련 span만 제거
    document.querySelectorAll('#sp_preview_selected .selected_keyword button[id^="sp_preview_area_"]').forEach(btn => {
        const span = btn.closest('.selected_keyword');
        if (span) span.remove();
    });

    // 현재 체크된 모든 지역코드 가져오기
    const checked = document.querySelectorAll('input[name="loc_cd[]"]:checked, input[name="loc_mcd[]"]:checked');
    checked.forEach(cb => {
        //const label = cb.closest('.inpChk').querySelector('.txt').innerText;
        let districtName = cb.closest('.inpChk').querySelector('.txt').innerText;
        //addKeywordSpan(`${cityName}`, label, cb.value);
        addKeywordSpan(`${cityName}`, districtName, cb.value);
    });

    updateAllCodes(); // params 갱신
}

// span 추가
const addKeywordSpan = function (cityName, districtName, districtCodeNo) {
    // 중복 추가 방지
    if (document.querySelector(`#sp_preview_area_${districtCodeNo}`)) return;

    const span = document.createElement('span');
    span.className = 'selected_keyword';
    span.innerHTML = `
        ${cityName}&gt;${districtName}
        <button type="button" id="sp_preview_area_${districtCodeNo}" data-code="${districtCodeNo}" class="btn_del remove-btn">
            삭제
        </button>
    `;
    selectedContainer.appendChild(span);
    
    // params 갱신
    toggleKeywordDisplay();

    // 삭제 버튼 이벤트
    span.querySelector('.remove-btn').addEventListener('click', function () {
        document.querySelector(`input[value="${districtCodeNo}"]`).checked = false;
        removeKeywordSpan(districtCodeNo);
        toggleKeywordDisplay();
        updateAllCodes(); // 삭제 후 갱신
    });
}

// span 제거
const removeKeywordSpan = function (code) {
    // 지역 or 직업 버튼 ID 중 존재하는 것 선택
    const areaBtn = document.querySelector(`#sp_preview_area_${code}`);
    const jobBtn = document.querySelector(`#sp_preview_job_category_${code}`);

    const btn = areaBtn || jobBtn;  // 둘 중 하나라도 존재하면
    if (btn) {
        const span = btn.closest('.selected_keyword');
        if (span) span.remove();
    }
};

// 선택된 값이 없으면 숨김 + 클래스 토글
const toggleKeywordDisplay = function () {
    const hasKeywords = selectedContainer.children.length > 0;
    selectedContainer.style.display = hasKeywords ? 'block' : 'none';

    // 검색조건 여부에 따라 resetting 클래스 토글
    if (hasKeywords) {
        previewWrapper.classList.remove('resetting'); // 조건 있음
    } else {
        previewWrapper.classList.add('resetting'); // 조건 없음
    }
}

//--------------------------선택된 지역코드 객체에 넣기------------------------------------------

// 선택된 districtCode와 jobCode를 params에 반영
const updateAllCodes = function () {
    const selectedButtons = document.querySelectorAll('.selected_keyword .remove-btn');

    // 지역코드와 직업코드 구분
    const districtCodes = [];
    const jobCodes = [];

    selectedButtons.forEach(btn => {
        const code = btn.dataset.code;
        const id = btn.id;

        if (id.startsWith('sp_preview_area_')) {
            districtCodes.push(code);
        } else if (id.startsWith('sp_preview_job_category_')) {
            jobCodes.push(code);
        }
    });

    params.districtCode = districtCodes;
    params.jobCode = jobCodes;

    console.log('✅ 갱신된 districtCode:', districtCodes);
    console.log('✅ 갱신된 jobCode:', jobCodes);
};


//-----------------------내부 스크롤 동작-----------------------------------------

//--------------------------------직업선택 옵션생성----------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const box_detail_jobs = document.querySelector('.box_detail_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');
const details = document.querySelector('.details');
const option_list_depth1_wrapper = document.querySelector('.option_list depth1_wrapper > ul');


//-----------------------클릭 이벤트---------------------------------------------

const selectTopJob = function(topJobCode, topJobName){
	//getJobCodeListByTopJob(topJobCode, topJobName);  // 하위직업 값 채움
	
	details.style.maxHeight = '202px';
	box_jobs.style.display = 'none';
	box_detail_jobs.style.display = 'block';

	depth(topJobCode, topJobName);  //전체선택 부분만 미리 생성
}

const nextTopJob = function(topJobCode, topJobName){
	
	const depth1_btn_ = document.querySelector(`#depth1_btn_${topJobCode}`);
	const li_wrapper = depth1_btn_.closest('li');
	
	//처음에는 wrapper on이 아니면 on을 붙인다
	//다른 wrapper에는 on을 뗀다
	
	//하위선택이 될 때 wrapper에 selected가 붙는다
	//selected가 붙은 wrapper는 클릭시 selected on 이 된다
	//selected가 붙은 wrapper는 해당 li를 클릭하지 않으면 selected로만 남는다
	
	//하위선택이 떨어지면 해당 wrapper는 selected를 뗀다
	
	
	if (!li_wrapper.classList.contains('on')) {
	  	// 이미 선택된 상태가 아니면
		// 현재 클릭한 li에 'on' 추가
		li_wrapper.classList.add('on');
		//다른 li들의 'on'을 제거
		document.querySelectorAll('#box_jobs_btn li').forEach(otherli=>{
			if (otherli !== li_wrapper) {
		      otherli.classList.remove('on');
		    }
		})
	}
	
	// 하위직업 상세 div 처리
	
	// nextTopJob() : 클릭 시 활성화
	let sp_job_category_subDepth_ = document.querySelector(`#sp_job_category_subDepth_${topJobCode}`);
	if (!sp_job_category_subDepth_) {
	    depth(topJobCode, topJobName);
	    sp_job_category_subDepth_ = document.querySelector(`#sp_job_category_subDepth_${topJobCode}`);
	}
	
	// 모든 박스 비활성화
	document.querySelectorAll('.box_detail_depth').forEach(div => {
	    div.classList.remove('on');
	    div.style.display = 'none';
	});
	
	// 클릭한 것만 활성화
	sp_job_category_subDepth_.classList.add('on');
	sp_job_category_subDepth_.style.display = 'block';
}

//-----------------------데이터 호출---------------------------------------------

// 상위직업 선택 값 불러옴
const getTopJobCodeList = function () {
  fetch('/ajax/admin/jobCode')
    .then(resp => resp.json())
    .then(data => {
      data.forEach(topjob => {
        let topJobCode = topjob.topJobCode;
        let topJobName = topjob.topJobName;
        setTopJobCodeList(topJobCode, topJobName);
      });
    });
};

// 상위직업 선택 값 채우기
const setTopJobCodeList = function (topJobCode, topJobName) {
  let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}" 
  				onclick="selectTopJob('${topJobCode}', '${topJobName}')">
                ${topJobName}
              </button>`;
  box_jobs.innerHTML += html;  // 상위직업 버튼

  html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
            <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}" 
            onclick="nextTopJob('${topJobCode}', '${topJobName}')">
              <span class="txt">${topJobName}</span>
              <span class="count">(4,056)</span>
            </button>
          </li>`;
  box_jobs_btn.innerHTML += html;  // 상위직업 버튼(리스트)
};

//하위직업 선택 값 불러옴
const getJobCodeListByTopJob = function (topJobCode, topJobName) {
  fetch(`/ajax/admin/jobCode/${topJobCode}`)
    .then(resp => {resp.json()
    .then((data) => {
		data.forEach((job)=>{
			let jobCode = job.jobCode;
			let jobName = job.jobName;
			let topJobCode = job.topJobCode;
			setJobCodeListByTopJob(jobCode, jobName, topJobCode, topJobName);  //하위직업 선택 값 채우기
		})		
	  })
   })
};

// 하위직업 카테고리 제목, 전체선택 부분 생성
const depth = function(topJobCode, topJobName){
	let html = `<div class="box_detail_depth" id="sp_job_category_subDepth_${topJobCode}" style="display: none;">
			        <div class="row row_all_select">
			            <input type="checkbox" id="all_check_onedepth_${topJobCode}" name="cat_mcls[]" onclick="onClickJobAllCheck(this)"
			            class="select_all" data-code="${topJobCode}" data-mcls_cd_no="${topJobCode}" data-mcls_cd_nm="${topJobName}">
			            <label for="all_check_onedepth_${topJobCode}">
			            	<span>${topJobName} 전체선택</span>
			            </label>
			            <span class="inpSel">
			                <select class="select_sort" title="정렬방법">
			                    <option value="default">가나다순</option>
			                    <option value="favor">공고많은순</option>
			                </select>
			            </span>
			        </div>
					<div class="row list" style="height: 243px;">
	                    <div class="wrap_scroll">
	                        <div class="scrollbar disable" style="height: 243px;">
		                        <div class="track" style="height: 243px;">
			                        <div class="thumb" style="height: 243px;">
			                        	<div class="end"></div>
			                        </div>
		                        </div>
	                        </div>
	                        <div class="viewport">
	                            <div class="overview" style="top: 0px;">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>`;
	box_detail_jobs.innerHTML += html;  //내부에 값 추가해야 제목끼리 안 겹침 주의
	
	//DOM이 구성되었으니 거기에 넣을 하위직업 호출
	getJobCodeListByTopJob(topJobCode, topJobName);  // 하위직업 값 채움
}

/*
count가 길면 버튼 추가되게 나중 처리

<dl class="row_item">
    <dt>
        <button type="button" class="btn_expand" data-scls_cd_no="65">
        	<span class="txt" style="font-weight: normal;">전문분야</span>
        </button>
    </dt>
    <dd class="area_list">
    </dd>
</dl>`;
*/

//카테고리 생성



// 전역으로 카테고리별 sort 카운터 저장
const sortCounters = {};  

// 카운터 반환 및 증가
const getSortCounter = function (categoryKey) {
    if (!sortCounters.hasOwnProperty(categoryKey)) {
        sortCounters[categoryKey] = 0;  // 처음이면 초기화
    }
    return sortCounters[categoryKey]++;  // 현재값 반환 후 증가
}

//하위직업 선택 값 채우기
// 전역에서 카테고리 세팅을 한 번만 하도록 제어
//const categoryInitialized = new Set();

const setJobCodeListByTopJob = function(jobCode, jobName, topJobCode, topJobName){
	const overview = document.querySelector(`#sp_job_category_subDepth_${topJobCode} .overview`);
	if (!overview) return;

	let targetSelector = '', categoryKey = '', categoryClass = '', categoryLabel = '';

	// 카테고리 조건 분기
	if (jobCode.startsWith(String(topJobCode - 1))) {
		categoryClass = 'job_category';
		categoryLabel = '직무·직업';
		categoryKey = `${topJobCode}_job`;
	} else if (jobCode.startsWith(topJobCode)) {
		categoryClass = 'specialty_category';
		categoryLabel = '전문분야';
		categoryKey = `${topJobCode}_specialty`;
	} else {
		categoryClass = 'etc_category';
		categoryLabel = '기타';
		categoryKey = `${topJobCode}_etc`;
	}

	// 카테고리가 없으면 동적 생성
	let categoryDl = overview.querySelector(`.${categoryClass}`);
	if (!categoryDl) {
		const html = `
			<dl class="row_item ${categoryClass}">
				<dt><span class="txt" style="font-weight: normal;">${categoryLabel}</span></dt>
				<dd class="area_list"></dd>
			</dl>`;
		overview.insertAdjacentHTML('beforeend', html);
		categoryDl = overview.querySelector(`.${categoryClass}`);
	}

	const targetDl = categoryDl.querySelector('.area_list');
	if (!targetDl) return;

	const sortValue = getSortCounter(categoryKey);

	const html = `
		<button type="button" name="cat_kewd[]" class="btn_three_depth" 
			data-code="${jobCode}" 
			data-mcls_cd_no="${topJobCode}" 
			data-mcls_cd_nm="${topJobName}" 
			data-kewd_cd_no="${jobCode}" 
			data-kewd_cd_nm="${jobName}" 
			data-sort="${sortValue}" 
			data-count="0"
			onclick="onClickJobCategory(this)">
			${jobName}
			<span class="count">(0)</span>
		</button>`;

	targetDl.innerHTML += html;
};

getTopJobCodeList();  //상위직업 선택 값 호출

//--------------------------선택된 직업코드 객체에 넣기------------------------------------------

//하위직업 선택 감지
const onClickJobCategory = function (btn) {
    let jobCode = btn.dataset.code;
	let jobName = btn.getAttribute('data-kewd_cd_nm');      // ✅ 직접 속성 읽기
	let topJobName = btn.getAttribute('data-mcls_cd_nm');   // ✅ 직접 속성 읽기
    console.log('🟢 onClickJobCategory 호출됨:', jobCode, jobName, topJobName);

    // 중복 추가 방지
    if (document.querySelector(`#sp_preview_job_category_${jobCode}`)) return;

    // ✅ 하위 클릭 시 전체선택 해제
    //const allCheckbox = document.querySelector(`#all_check_onedepth_${topJobCode}`);
    //if (allCheckbox) allCheckbox.checked = false;

    btn.classList.add('on');
    addJobKeywordSpan(topJobName, jobName, jobCode);
};

const addJobKeywordSpan = function (topJobName, jobName, jobCode) {
    if (document.querySelector(`#sp_preview_job_category_${jobCode}`)) return;

    const span = document.createElement('span');
    span.className = 'selected_keyword';
    span.innerHTML = `
        ${topJobName} &gt; ${jobName}
        <button type="button" id="sp_preview_job_category_${jobCode}" data-code="${jobCode}" class="btn_del remove-btn">
            삭제
        </button>
    `;
    selectedContainer.appendChild(span);

    updateAllCodes();
    toggleKeywordDisplay();

    // 삭제 버튼 이벤트
    span.querySelector('.remove-btn').addEventListener('click', function () {
        const targetBtn = document.querySelector(`button[data-code="${jobCode}"].btn_three_depth`);
        if (targetBtn) targetBtn.classList.remove('on');
        removeKeywordSpan(jobCode);
        toggleKeywordDisplay();
        updateAllCodes();
    });
};

// 직업 전체 선택 시 분기
const onClickJobAllCheck = function (checkbox) {
	let topJobCode = checkbox.dataset.code;
	let topJobName = checkbox.dataset.mclsCdNm;

    if (checkbox.checked) {
		// 1. 전체 선택의 코드가 params 반영
		addJobKeywordSpan(topJobName, jobName, topJobCode);  //topJobCode가 알아서 JobCode로 들어갈 거임 이름은 달라도!
		
        // 2. 하위 직업 버튼들 초기화
	    const subButtons = document.querySelectorAll(`.btn_three_depth[data-mcls_cd_no="${topJobCode}"]`);
	    subButtons.forEach(btn => {
	        const jobCode = btn.dataset.code;
	        btn.classList.remove('on');
	        removeKeywordSpan(jobCode);
	    });
	
	    updateAllCodes();
	    toggleKeywordDisplay();
    }

    updateAllCodes();       // params 반영
    toggleKeywordDisplay(); // UI 반영
};


//-----------------------검색 공통 함수------------------------------------------

// 초기화 버튼 클릭 시(현재는 직업만 됨)
const clearJobCategoryByTopCode = function (topJobCode) {
    // 1. 전체선택 체크박스 상태 해제
    const allCheckbox = document.querySelector(`#all_check_onedepth_${topJobCode}`);
    if (allCheckbox) allCheckbox.checked = false;

    // 2. 하위 직업 버튼들 초기화
    const subButtons = document.querySelectorAll(`.btn_three_depth[data-mcls_cd_no="${topJobCode}"]`);
    subButtons.forEach(btn => {
        const jobCode = btn.dataset.code;
        btn.classList.remove('on');
        removeKeywordSpan(jobCode);
    });

    updateAllCodes();
    toggleKeywordDisplay();
};

// 초기화 함수 생성 이후에 해당 버튼에 추가
document.addEventListener('DOMContentLoaded', function () {
    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('btn_reset')) {
            const jobTab = document.querySelector('.tab_job.on'); // 예시: 활성 탭의 topJobCode
            if (!jobTab) return;

            const topJobCode = jobTab.dataset.mclsCdNo || e.target.dataset.topjobcode;
            if (topJobCode) {
                clearJobCategoryByTopCode(topJobCode);
            }
        }
    });
});


//-----------------------내부 스크롤 동작-----------------------------------------

