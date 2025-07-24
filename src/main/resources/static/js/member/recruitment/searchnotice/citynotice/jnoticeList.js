/**
 * 
 */

//--------------------------------직업선택 옵션생성, 만들고 합칠 것--------------------------------------------------------------------------
const box_jobs = document.querySelector('.box_jobs');
const list_overview= document.querySelector('.list overview');
const option_list_depth1_wrapper = document.querySelector('.option_list depth1_wrapper > ul');  //ul 안에 넣어!

//상위직업 선택 값 불러옴
const getTopJobCodeList = function () {
  fetch('/ajax/admin/jobCode')
    .then(resp => resp.json())
    .then(data => {
	  data.forEach(topjob =>{
		console.log("topjob?", topjob);
		let topJobCode = topjob.topJobCode;
		let topJobName = topjob.topJobName;
		setTopJobCodeList(topJobCode, topJobName);
	  })
   });
};

//상위직업 선택 값 채우기
const setTopJobCodeList = function(topJobCode, topJobName){
	let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}">${topJobName}</button>`;
	box_jobs.innerHTML += html;  //상위직업 수만큼 생김
	
	html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
			    <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}">
			        <span class="txt">${topJobName}</span>
			        <span class="count">(4,056)</span>
			    </button>
			</li>`;
	list_overview.innerHTML += html;  //상위직업 수만큼 생김
	
	getJobCodeListByTopJob(topJobCode);  //하위직업 선택 값 호출
}

//하위직업 선택 값 불러옴
const getJobCodeListByTopJob = function (topJobCode) {
  fetch(`/ajax/admin/jobCode/${topJobCode}`)
    .then(resp => {resp.json()
    .then((data) => {
		data.forEach((job)=>{
			console.log("job?", job);
			let jobCode = job.jobCode;
			let topJobCode = job.topJobCode;
			let jobName = job.jobName;
			setJobCodeListByTopJob(jobCode, topJobCode, jobName);  //하위직업 선택 값 채우기
		})		
	  })
   })
};

//하위직업 선택 값 채우기
//const setJobCodeListByTopJob = function(jobCode, topJobCode, jobName){
	
//}

getTopJobCodeList();  //상위직업 선택 값 호출

//산업 코드 선택 값 불러옴
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

//--------------------- 직업 선택 동적클래스--------------------------------------------------------------------

//하위직업(li)까지 선택됐으면서 해당 열려있음 -> selected on
//하위직업까지 선택됐으면서 닫혀있음 -> selected만
//상위직업은 클릭했으나 하위직업을 안 선택하고 넘어감 -> selected가 취소되고 원래대로
