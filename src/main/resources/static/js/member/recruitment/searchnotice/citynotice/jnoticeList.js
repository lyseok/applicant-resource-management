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
	getJobCodeListByTopJob(topJobCode, topJobName);  // 하위직업 값 채움
	
	details.style.maxHeight = '202px';
	box_jobs.style.display = 'none';
	box_detail_jobs.style.display = 'block';

	depth(topJobCode, topJobName);  //전체선택 부분만 미리 생성
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
  let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}" onclick="selectTopJob('${topJobCode}', '${topJobName}')">
                ${topJobName}
              </button>`;
  box_jobs.innerHTML += html;  // 상위직업 버튼

  html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
            <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}" onclick="selectJob(this)">
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
			//console.log("job???", job);
			let jobCode = job.jobCode;
			let jobName = job.jobName;
			let topJobCode = job.topJobCode;
			setJobCodeListByTopJob(jobCode, jobName, topJobCode, topJobName);  //하위직업 선택 값 채우기
			//console.log("jobCode?", jobCode, "jobName?", jobName, "topJobCode?", topJobCode, "topJobName?", topJobName);
		})		
	  })
   })
};

// 하위직업 카테고리 제목, 전체선택 부분 생성
const depth = function(topJobCode, topJobName){
	
	let html = `<div class="box_detail_depth on" id="sp_job_category_subDepth_${topJobCode}" style="display: block;">
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
	                                <dl class="row_item">
	                                    <dt>
	                                        <span class="txt">직무·직업</span>
	                                    </dt>
	                                </dl>
	                                <dl class="row_item">
	                                    <dt>
	                                        <button type="button" class="btn_expand" data-scls_cd_no="65">
	                                        	<span class="txt">전문분야</span>
	                                        </button>
	                                    </dt>
	                                </dl>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>`;
	box_detail_jobs.innerHTML += html;  //내부에 값 추가해야 제목끼리 안 겹침 주의
	
	
}

//하위직업 선택 값 채우기
const setJobCodeListByTopJob = function(jobCode, jobName, topJobCode, topJobName){
	//console.log("jobCode?", jobCode, "jobName?", jobName, "topJobCode?", topJobCode, "topJobName?", topJobName);
	const overview = document.querySelector('#sp_job_category_subDepth_17 .overview');
	const rowItems = overview.querySelectorAll('.row_item'); // 모든 형제 dl
	
	// 공통으로 들어가는 html
	let html = `<dd class="area_list">
			    	<button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
			   		 data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="0" data-count="0">
			        ${jobName}
			        	<span class="count">(0)</span>
			    	</button>
			     </dd>`;
    
	//예) 12..로 시작하면 직무직업으로
	if(jobCode.startsWith(topJobCode-1)){
		rowItems[0].innerHTML += html;
	}else{
	//예) 13으로 시작하면 전문분야로
		rowItems[1].innerHTML += html;
	}


    /*
    <!-- <dl class="row_item">
        <dt>
            <span class="txt">{businessTypeName}</span>
        </dt>
        <dd class="area_list">
            <button type="button" name="cat_kewd[]" class="btn_three_depth" data-code="${jobCode}" data-mcls_cd_no="${topJobCode}" 
             data-mcls_cd_nm="${topJobName}" data-kewd_cd_no="${jobCode}" data-kewd_cd_nm="${jobName}" data-sort="0" data-count="0">
            ${jobName}
            <span class="count">(0)</span>
        </dd>
    </dl> -->
    */
}

getTopJobCodeList();  //상위직업 선택 값 호출


//-----------------------내부 스크롤 동작-----------------------------------------

function initCustomScroll(wrapperSelector) {
    const wrappers = document.querySelectorAll(wrapperSelector);
    wrappers.forEach(wrapper => {
        const viewport = wrapper.querySelector('.viewport');
        const content = wrapper.querySelector('.overview');
        const track = wrapper.querySelector('.track');
        const thumb = wrapper.querySelector('.thumb');

        const updateThumb = () => {
            const viewportHeight = viewport.clientHeight;
            const contentHeight = content.scrollHeight;
            const trackHeight = track.clientHeight;
            const thumbHeight = Math.max((viewportHeight / contentHeight) * trackHeight, 20);
            thumb.style.height = `${thumbHeight}px`;

            const maxScroll = contentHeight - viewportHeight;
            const maxThumbMove = trackHeight - thumbHeight;
            const thumbTop = (viewport.scrollTop / maxScroll) * maxThumbMove;
            thumb.style.top = `${thumbTop}px`;
        };

        // viewport 스크롤 시 thumb 이동
        viewport.addEventListener('scroll', updateThumb);

        // 드래그로 스크롤
        let isDragging = false, startY, startTop;
        thumb.addEventListener('mousedown', e => {
            isDragging = true;
            startY = e.clientY;
            startTop = parseInt(thumb.style.top) || 0;
            e.preventDefault();
        });

        document.addEventListener('mousemove', e => {
            if (!isDragging) return;
            const delta = e.clientY - startY;
            const trackHeight = track.clientHeight;
            const thumbHeight = thumb.clientHeight;
            let newTop = Math.max(0, Math.min(startTop + delta, trackHeight - thumbHeight));
            thumb.style.top = `${newTop}px`;

            const scrollRatio = newTop / (trackHeight - thumbHeight);
            const contentHeight = content.scrollHeight;
            const viewportHeight = viewport.clientHeight;
            viewport.scrollTop = scrollRatio * (contentHeight - viewportHeight);
        });

        document.addEventListener('mouseup', () => isDragging = false);

        // 초기화
        updateThumb();
        window.addEventListener('resize', updateThumb);
    });
}

// 실행
initCustomScroll('.wrap_scroll');
