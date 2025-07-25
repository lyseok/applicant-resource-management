/**
 * 
 */

//-------------------------------- 직업선택 옵션생성 --------------------------------------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');
const box_detail_jobs = document.querySelector('.box_detail_jobs');

// 초기 데이터 로드
getTopJobCodeList();

//--------------------------- 상위 직업 데이터 ----------------------------------

// 상위직업 데이터 불러오기
function getTopJobCodeList() {
    fetch('/ajax/admin/jobCode')
        .then(resp => resp.json())
        .then(data => {
            const fragmentBtn = document.createDocumentFragment();
            const fragmentLi = document.createDocumentFragment();
            data.forEach(topjob => {
                const { topJobCode, topJobName } = topjob;

                // 버튼
                const btn = document.createElement('button');
                btn.type = "button";
                btn.className = "btn_job";
                btn.dataset.mcls_cd_no = topJobCode;
                btn.textContent = topJobName;
                btn.addEventListener('click', () => selectTopJob(btn));
                fragmentBtn.appendChild(btn);

                // 리스트 li
                const li = document.createElement('li');
                li.className = "item_job depth1_btn_wrapper";
                li.id = `depth1_btn_${topJobCode}`;
                li.innerHTML = `
                    <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}">
                        <span class="txt">${topJobName}</span>
                        <span class="count">(4,056)</span>
                    </button>`;
                li.querySelector('button').addEventListener('click', () => selectJob(li.querySelector('button')));
                fragmentLi.appendChild(li);
            });
            box_jobs.appendChild(fragmentBtn);
            box_jobs_btn.appendChild(fragmentLi);
        });
}

//--------------------------- 하위 직업 데이터 ----------------------------------

// 하위직업 DOM 생성
const setJobCodeListByTopJob = function (jobCode, topJobCode, topJobName, jobName) {
  const hardCodedCount = 100; // 하드코딩된 count 값

  console.log("하위직업 돔 생성됨!");

  let html = `
    <div class="box_detail_depth" id="sp_job_category_subDepth_${topJobCode}" style="display: none;">
      <div class="row row_all_select">
        <input type="checkbox" id="all_check_onedepth_${topJobCode}" 
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
        <div class="viewport">
          <div class="overview">
            
            <!-- 직무·직업 -->
            <dl class="row_item">
              <dt><span class="txt">직무·직업</span></dt>
              <dd class="area_list">
                <button type="button" name="cat_kewd[]" class="btn_three_depth" 
                  data-code="${jobCode}" 
                  data-mcls_cd_no="${topJobCode}" 
                  data-mcls_cd_nm="${topJobName}" 
                  data-kewd_cd_no="${jobCode}" 
                  data-kewd_cd_nm="${jobName}" 
                  data-sort="0" 
                  data-count="${hardCodedCount}">
                  ${jobName}<span class="count">(${hardCodedCount})</span>
                </button>
              </dd>
            </dl>
          </div>
        </div>
      </div>
    </div>
  `;
  box_detail_jobs.innerHTML += html;
};


//--------------------------- 이벤트 처리 ----------------------------------

// 상위직업 버튼 클릭
const selectTopJob = function(button) {
	console.log("상위직업 버튼 클릭됨!");
	
    const code = button.dataset.mcls_cd_no;
    const targetBox = document.getElementById('sp_job_category_subDepth_' + code);

    //box_jobs 숨기기 제거! (리스트는 계속 보여야 함)
    const boxJobs = document.querySelector('.box_jobs');
		if (boxJobs) {
		boxJobs.style.display = 'none';
    }

    // box_detail_jobs 보이기
    const boxDetailJobs = document.querySelector('.box_detail_jobs');
    if (boxDetailJobs) {
        boxDetailJobs.style.display = 'block';
    }

    // 모든 box_detail_depth 숨김
    document.querySelectorAll('.box_detail_depth').forEach(div => {
        div.style.display = 'none';
        div.classList.remove('on');
    });

    // 해당 box_detail_depth만 표시
    if (targetBox) {
        targetBox.style.display = 'block';
        targetBox.classList.add('on');
    }

    // 모든 li에서 on 제거
    document.querySelectorAll('.item_job').forEach(li => li.classList.remove('on'));

    // 클릭된 li에 on 추가
    const parentLi = document.querySelector('#depth1_btn_' + code);
    if (parentLi) parentLi.classList.add('on');
}


// 상위직업 리스트 클릭
function selectJob(button) {
	console.log("상위직업 리스트 클릭됨!");
	
    const code = button.dataset.mcls_cd_no;
    const name = button.querySelector('.txt')?.innerText || '';
    const targetBox = document.querySelector(`#sp_job_category_subDepth_${code}`);

    if (!targetBox) {
        // 처음 클릭 시 데이터 로드
        fetch(`/ajax/admin/jobCode/${code}`)
            .then(resp => resp.json())
            .then(data => {
                setJobCodeListByTopJob(data, code, name);
                showDetailBox(code);
            });
    } else {
        showDetailBox(code);
    }
}

// 상세 박스 표시
function showDetailBox(code) {
	console.log("상세박스 표시됨!");
	
    document.querySelectorAll('.box_detail_depth').forEach(div => {
        div.style.display = 'none';
        div.classList.remove('on');
    });
    const targetBox = document.querySelector(`#sp_job_category_subDepth_${code}`);
    if (targetBox) {
        targetBox.style.display = 'block';
        targetBox.classList.add('on');
    }
    document.querySelectorAll('.item_job').forEach(li => li.classList.remove('on'));
    const parentLi = document.querySelector(`#depth1_btn_${code}`);
    if (parentLi) parentLi.classList.add('on');
}



//--------------------------------------------산업선택 옵션 생성----------------------------------------------

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


