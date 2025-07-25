/**
 * 
 */

//--------------------------------직업선택 옵션생성, 만들고 합칠 것--------------------------------------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const option_list_depth1_wrapper = document.querySelector('.option_list depth1_wrapper > ul');  //ul 안에 넣어!
const box_detail_jobs = document.querySelector('.box_detail_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');

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
  let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}" onclick="selectTopJob(this)">
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


