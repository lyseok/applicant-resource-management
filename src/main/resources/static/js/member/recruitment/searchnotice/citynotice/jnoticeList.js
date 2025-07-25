/**
 * 
 */

//--------------------------------직업선택 옵션생성, 만들고 합칠 것--------------------------------------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const option_list_depth1_wrapper = document.querySelector('.option_list depth1_wrapper > ul');  //ul 안에 넣어!
const box_detail_jobs = document.querySelector('.box_detail_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');

//상위직업 선택 값 불러옴
const getTopJobCodeList = function () {
  fetch('/ajax/admin/jobCode')
    .then(resp => resp.json())
    .then(data => {
	  data.forEach(topjob =>{
		let topJobCode = topjob.topJobCode;
		let topJobName = topjob.topJobName;
		setTopJobCodeList(topJobCode, topJobName);
	  })
   });
};

//상위직업 선택 값 채우기
const setTopJobCodeList = function(topJobCode, topJobName){
	let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}" onclick="selectTopJob(this)">
		            ${topJobName}
		        </button>`;
	box_jobs.innerHTML += html;  //상위직업 수만큼 생김
	
	html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
	            <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}" onclick="selectJob(this)">
	                <span class="txt">${topJobName}</span>
	                <span class="count">(4,056)</span>
	            </button>
	        </li>`;
	box_jobs_btn.innerHTML += html;  //상위직업 수만큼 생김
	
	html = `<div class="box_detail_depth on" id="sp_job_category_subDepth_${topJobCode}" style="display: none;">
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
	        </div>`;
	box_detail_jobs.innerHTML += html;  //미리 만들기?
	
	getJobCodeListByTopJob(topJobCode);  //하위직업 선택 값 호출
}

//하위직업 선택 값 불러옴
const getJobCodeListByTopJob = function (topJobCode) {
  fetch(`/ajax/admin/jobCode/${topJobCode}`)
    .then(resp => {resp.json()
    .then((data) => {
		data.forEach((job)=>{
			console.log("job?", job);
			/*
			let jobCode = job.jobCode;
			let jobName = job.jobName;
			let topJobCode = job.topJobCode;
			setJobCodeListByTopJob(jobCode, jobName, topJobCode);  //하위직업 선택 값 채우기
			*/
		})		
	  })
   })
};

//하위직업 선택 값 채우기
const setJobCodeListByTopJob = function(jobCode, topJobCode, jobName){
	let html = `<div class="row list" style="height: 243px;">
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
	                                <dl class="row_item">
	                                    <dt>
	                                        <span class="txt">직무·직업</span>
	                                    </dt>
	                                    <dd class="area_list">
	                                    	<button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
	                                   		 data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="0" data-count="581">
		                                    ${jobName}
		                                    	<span class="count">(581)</span>
	                                    	</button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" 
		                                     data-mcls_cd_no="${topJobCode}" data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" 
		                                    data-sort="1" data-count="387">
		                                    ${jobName}
		                                    	<span class="count">(387)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                    data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="2" data-count="201">
		                                     ${jobName}
		                                    	<span class="count">(201)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                    data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="3" data-count="431">
		                                     ${jobName}
		                                    	<span class="count">(431)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                    data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="4" data-count="58">
		                                     ${jobName}
		                                    	<span class="count">(58)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}"
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="5" data-count="365">
		                                      ${jobName}
		                                     	<span class="count">(365)</span>
		                                    </button>
		                                 </dd>
	                                </dl>
	                                <dl class="row_item">
	                                    <dt>
	                                        <button type="button" class="btn_expand" data-scls_cd_no="65">
	                                        	<span class="txt">전문분야</span>
	                                        </button>
	                                    </dt>
	                                    <dd class="area_list">
		                           ${topJobName}       <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="0" data-count="207">
		                                    ${jobName}
		                                    	<span class="count">(207)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="1" data-count="96">
		                                    ${jobName}
		                                    	<span class="count">(96)</span>
		                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="2" data-count="82">
		                                    ${jobName}
		                                    	<span class="count">(82)</span>
		                                    </button>
	                                    </dd>
	                                </dl>
	                                <dl class="row_item">
	                                    <dt>
	                                        <span class="txt">${businessTypeName}</span>
	                                    </dt>
	                                    <dd class="area_list">
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="0" data-count="15">
		                                    ${jobName}
		                                    	<span class="count">(15)</span>
	                                    </button>
		                                    <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
		                                     data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="1" data-count="73">
		                                     ${jobName}
		                                     	<span class="count">(73)</span>
		                                     </button>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>`;
	box_detail_jobs.innerHTML += html;
}

getTopJobCodeList();  //상위직업 선택 값 호출

//산업 코드 선택 값 불러옴
/*
const getInduClassCodeList = function (topJobCode) {
  fetch(`/ajax/member/induclasscode`)
    .then(resp => {resp.json()
    .then((data) => {
		data.forEach((indu)=>{
			console.log("indu?", indu);
			let induClassNo = indu.induClassNo.match(/\d$/)[0];
			let induClassName = indu.induClassName;
			setInduClassCodeList(induClassNo, induClassName);  //산업 코드 선택 값 채우기
		})		
	  })
   })
};

//산업 코드 값 채우기
const setInduClassCodeList = function(induClassNo, induClassName){
	html = `<li>
			    <button type="button" id="sp_industry_1depth_${induClassNo}" 
				class="btn_option job_category01 depth1" data-code="${induClassNo}">${induClassName}</button>
			</li>`;
	option_list_depth1_wrapper += html;
}
*/

//--------------------- 직업 선택 동적클래스--------------------------------------------------------------------

//상위직업 선택
const selectTopJob = function(button) {
    const code = button.dataset.mcls_cd_no;
    const targetBox = document.getElementById('sp_job_category_subDepth_' + code);

    // 1. box_jobs 숨기기
    const boxJobs = document.querySelector('.box_jobs');
    if (boxJobs) {
        boxJobs.style.display = 'none';
    }

    // 2. box_detail_jobs 보이기
    const boxDetailJobs = document.querySelector('.box_detail_jobs');
    if (boxDetailJobs) {
        boxDetailJobs.style.display = 'block';
    }

    // 3. 높이 하드코딩 (클릭 후 상태로)
    const boxOnedepth = document.querySelector('.box_detail_jobs .box_onedepth');
    const scrollbar = document.querySelector('.box_detail_jobs .scrollbar');
    const track = document.querySelector('.box_detail_jobs .track');
    if (boxOnedepth && scrollbar && track) {
        boxOnedepth.style.height = '243px';
        scrollbar.style.height = '243px';
        track.style.height = '243px';
    }

    // 4. 모든 box_detail_depth 숨김
    document.querySelectorAll('.box_detail_depth').forEach(div => {
        div.style.display = 'none';
        div.classList.remove('on');
    });

    // 5. 해당 box_detail_depth만 표시
    if (targetBox) {
        targetBox.style.display = 'block';
        targetBox.classList.add('on');
    }

    // 6. 모든 li에서 on 제거
    document.querySelectorAll('.item_job').forEach(li => li.classList.remove('on'));

    // 7. 클릭된 li에 on 추가
    const parentLi = document.querySelector('#depth1_btn_' + code);
    if (parentLi) parentLi.classList.add('on');
}

const selectJob = function(button){
	
}
