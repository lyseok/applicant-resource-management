/**
 * 
 */

//--------------------------------직업선택 옵션생성, 만들고 합칠 것--------------------------------------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const box_detail_jobs = document.querySelector('.box_detail_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');
const details = document.querySelector('.details');
const option_list_depth1_wrapper = document.querySelector('.option_list depth1_wrapper > ul');


//-----------------------클릭 이벤트---------------------------------------------

const selectTopJob = function(topJobCode, topJobName){
	console.log("살살이~");
	
	//getJobCodeListByTopJob(topJobCode, topJobName);  // 하위직업 값 채움
	
	details.style.maxHeight = '202px';
	box_jobs.style.display = 'none';
	box_detail_jobs.style.display = 'block';

	depth(topJobCode, topJobName);  //전체선택 부분만 미리 생성
}

const nextTopJob = function(topJobCode, topJobName){
	console.log("클릭됨!");
	
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
	console.log("전체선택 생성!");
	let html = `<div class="box_detail_depth" id="sp_job_category_subDepth_${topJobCode}" style="display: none;">
			        <div class="row row_all_select">
			            <input type="checkbox" id="all_check_onedepth_${topJobCode}" name="cat_mcls[]" 
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
			data-count="0">
			${jobName}
			<span class="count">(0)</span>
		</button>`;

	targetDl.innerHTML += html;
};

getTopJobCodeList();  //상위직업 선택 값 호출

//--------------------------선택된 지역코드 객체에 넣기------------------------------------------

// 선택된 jobCode를 params에 반영
const updateJobCodes = function () {
    const selectedButtons = document.querySelectorAll('.selected_keyword .remove-btn');
    const codes = Array.from(selectedButtons).map(btn => btn.dataset.code); 
    params.jobCode = codes;  // params 객체 갱신
    console.log('갱신된 jobCode:', params.jobCode);
}


//-----------------------내부 스크롤 동작-----------------------------------------


