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

// 최초 클릭 시 데이터 생성 + 탭 이동
const selectTopJobInitial = function(button, topJobCode, topJobName) {
    getJobCodeListByTopJob(topJobCode, topJobName);  // 데이터 로드
    depth(topJobCode, topJobName);                  // DOM 생성
    selectTopJobTab(button, topJobCode);            // 탭 전환 처리
};

// 탭 전환만 (데이터는 이미 생성되어 있다고 가정)
const selectTopJobTab = function(button, topJobCode) {
    details.style.maxHeight = '202px';
    box_jobs.style.display = 'none';
    box_detail_jobs.style.display = 'block';

    // li에 on 클래스 추가
    const currentLi = button.closest('li');
    document.querySelectorAll('#box_jobs_btn li').forEach(li => li.classList.remove('on'));
    if (currentLi) currentLi.classList.add('on');

    // 모든 상세 박스 숨기고 해당 것만 보이기
    document.querySelectorAll('.box_detail_depth').forEach(div => div.style.display = 'none');
    const target = document.querySelector(`#sp_job_category_subDepth_${topJobCode}`);
    if (target) target.style.display = 'block';
};

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
	// box_jobs 영역 버튼 (최초 클릭만 가능)
	let html = `<button type="button" class="btn_job" 
	    onclick="selectTopJobInitial(this, '${topJobCode}', '${topJobName}')">
	    ${topJobName}
	</button>`;
	
	box_jobs.innerHTML += html;  // 상위직업 버튼
	  
	// box_jobs_btn 리스트 버튼 (탭 전환만 가능)
	html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
	    <button type="button" class="first_depth" 
	        onclick="selectTopJobTab(this, '${topJobCode}')">
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
    const html = `
    <div class="box_detail_depth on" id="sp_job_category_subDepth_${topJobCode}" style="display: block;">
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
                        <!-- dl.row_item 항목은 모두 동적으로 삽입됨 -->
                    </div>
                </div>
            </div>
        </div>
    </div>`;
    
    box_detail_jobs.innerHTML += html;
};


// 카테고리 내 순서 부여용 배열
const sortCounters = {};  
const getSortCounter = function (categoryKey) {
    if (!sortCounters.hasOwnProperty(categoryKey)) {
        sortCounters[categoryKey] = 0; 
    }
    return sortCounters[categoryKey]++;
};

// 하위직업 선택 값 채우기
const setJobCodeListByTopJob = function(jobCode, jobName, topJobCode, topJobName){
    const overview = document.querySelector(`#sp_job_category_subDepth_${topJobCode} .overview`);
    if (!overview) return;

    const firstDigitTop = String(topJobCode).charAt(0);
    const firstDigitJob = String(jobCode).charAt(0);

    let targetDl, categoryKey;

    // --- 조건 1: 전문분야 ---
    if (firstDigitTop === firstDigitJob) {
        let specialtyDl = overview.querySelector('.specialty_category');
        if (!specialtyDl) {
            // ✅ [동적 생성] 전문분야
            const specialtyHtml = `
                <dl class="row_item specialty_category">
                    <dt>
                        <button type="button" class="btn_expand" data-scls_cd_no="65">
                            <span class="txt" style="font-weight: normal;">전문분야</span>
                        </button>
                    </dt>
                    <dd class="area_list"></dd>
                </dl>`;
            overview.insertAdjacentHTML('beforeend', specialtyHtml);
            specialtyDl = overview.querySelector('.specialty_category');
        }
        targetDl = specialtyDl.querySelector('.area_list');
        categoryKey = `${topJobCode}_specialty`;

    // --- 조건 2: 직무·직업 ---
    } else if (Number(firstDigitTop) - 1 === Number(firstDigitJob)) {
        let jobDl = overview.querySelector('.job_category');
        if (!jobDl) {
            // ✅ [동적 생성] 직무·직업
            const jobHtml = `
                <dl class="row_item job_category">
                    <dt>
                        <span class="txt" style="font-weight: normal;">직무·직업</span>
                    </dt>
                    <dd class="area_list"></dd>
                </dl>`;
            overview.insertAdjacentHTML('afterbegin', jobHtml);
            jobDl = overview.querySelector('.job_category');
        }
        targetDl = jobDl.querySelector('.area_list');
        categoryKey = `${topJobCode}_job`;

    // --- 조건 3: 기타 ---
    } else {
        let etcDl = overview.querySelector('.etc_category');
        if (!etcDl) {
            // ✅ [동적 생성] 기타
            const etcHtml = `
                <dl class="row_item etc_category">
                    <dt>
                        <span class="txt" style="font-weight: normal;">기타</span>
                    </dt>
                    <dd class="area_list"></dd>
                </dl>`;
            overview.insertAdjacentHTML('beforeend', etcHtml);
            etcDl = overview.querySelector('.etc_category');
        }
        targetDl = etcDl.querySelector('.area_list');
        categoryKey = `${topJobCode}_etc`;
    }

    if (!targetDl) return;

    // 순번 부여
    const sortValue = getSortCounter(categoryKey);

    // 공통 버튼 HTML
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


//--------------------- 직업 선택 동적클래스--------------------------------------------------------------------




//하위직업 선택 중첩
//const selectJob = function (button) {
    
//}

//-----------------------내부 스크롤 동작-----------------------------------------
