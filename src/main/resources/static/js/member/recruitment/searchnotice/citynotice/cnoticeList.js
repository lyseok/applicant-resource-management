/**
 * 
 */
const first_cityopt = document.querySelector('#first_cityopt > ul');
const second_cityopt = document.querySelector('#second_cityopt');

//도시 선택 값 불러옴
const getCityCodeList = function () {
  fetch('/ajax/admin/cityCode')
    .then(resp => resp.json())
    .then(data => {
	  data.forEach(city =>{
		let cityCodeNo = city.cityCodeNo.slice(4);
		let cityName = city.cityName;
		setCityCodeList(cityCodeNo, cityName);  //시 선택 값 채우기
	  })
   });
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

//구군 선택 값 불러옴
const getDistrictCodeList = function (cityCodeNo) {
  fetch(`/ajax/admin/cityCode/CICO${cityCodeNo}`)  //CICO115000
    .then(resp => {resp.json()
    .then((data) => {
		data.forEach((district)=>{
			let districtCodeNo = district.districtCodeNo;
			let cityCodeNo = district.cityCodeNo.slice(4);
			let districtName = district.districtName;
			setDistrictCodeList(districtCodeNo, cityCodeNo, districtName);  //구군 선택 값 채우기
		})		
	  })
   })
};

//구군 선택 값 채우기
const setDistrictCodeList = function(districtCodeNo, cityCodeNo, districtName){
	let sp_area_lastDepth =  document.querySelector(`#sp_area_lastDepth_${cityCodeNo}`);
	if(districtName.includes('전체')||districtName==='전국'){
		html = `<li>
	                <div class="inpChk">
	                    <input type="checkbox" id="loc_mcd_${districtCodeNo}" name="loc_mcd[]" value="${districtCodeNo}" data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type="all">
	                    <label for="loc_mcd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span></label>
	                </div>
            	</li>`;
	}else{
		html = `<li>
	                <div class="inpChk">
	                    <input type="checkbox" id="loc_cd_${districtCodeNo}" name="loc_cd[]" value="${districtCodeNo}" data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type="">
	                    <label for="loc_cd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span><span class="count"> (1,180)</span></label>
	                </div>
	            </li>`;
	}
	
    sp_area_lastDepth.innerHTML += html;  //구군 수만큼 생김
}

getCityCodeList();  //도시 선택 값 호출

//-------------------------------------------------------------------------------------------------------

//도시 선택 중첩
function selectCity(button) {
    // 1. 모든 li에 selected 추가 + on 제거
    document.querySelectorAll('#first_cityopt li').forEach(li => {
        li.classList.add('selected');
        li.classList.remove('on');
    });
    // 2. 클릭한 li에 on 추가
    const currentLi = button.closest('li');
    currentLi.classList.add('on');
    // 3. 모든 하위 ul 숨기기
    document.querySelectorAll('#second_cityopt .list_check').forEach(ul => {
        ul.style.display = 'none';
    });
    // 4. 현재 클릭한 li에 해당하는 하위 ul만 보이도록
    const cityCode = button.getAttribute('data-code');
    const targetUl = document.getElementById(`sp_area_lastDepth_${cityCode}`);
    if (targetUl) {
        targetUl.style.display = 'block';
    }
}


//<span class="selected_keyword">서울&gt;노원구<button type="button" id="sp_preview_area_101090" data-code="" class="btn_del remove-btn ">삭제</button></span>


