/**
 * 
 */
const first_cityopt = document.querySelector('#first_cityopt > ul');
const second_cityopt = document.querySelector('#second_cityopt');
const add_keyword = document.querySelector('.add_keyword');
const search_btn = document.querySelector('#search_btn');  //검색하기 버튼 클릭후 콘솔에 찍히는 params 값 보기->axios로 넘기기만 하면됨
let params = {
    page: 1,
    pageSize: 20,
    districtCode: ['113100'],
    jobCode: ['1800', '1957'],
    keyword: '프론트엔드'
  }

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

//-------------------------------------------------------------------------------------------------------

//도시 선택 중첩
function selectCity(button) {
    const currentLi = button.closest('li');

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


//구군 선택 중첩
// 미리 선언된 add_keyword 활용
const selectedContainer = add_keyword.querySelector('#sp_preview_selected');
const previewWrapper = document.getElementById('sp_preview'); // 패널 래퍼

// 체크박스 클릭 시 호출
function selectDistrict(checkbox, cityCodeNo, districtName) {
    const cityName = document.querySelector(`#depth1_btn_${cityCodeNo} .txt`).innerText;
    const districtCodeNo = checkbox.value;

    if (checkbox.checked) {
        addKeywordSpan(cityName, districtName, districtCodeNo);
    } else {
        removeKeywordSpan(districtCodeNo);
    }
    toggleKeywordDisplay();
}

// span 추가
function addKeywordSpan(cityName, districtName, districtCodeNo) {
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

    // 삭제 버튼 이벤트
    span.querySelector('.remove-btn').addEventListener('click', function () {
        document.querySelector(`input[value="${districtCodeNo}"]`).checked = false;
        removeKeywordSpan(districtCodeNo);
        toggleKeywordDisplay();
    });
}

// span 제거
function removeKeywordSpan(districtCodeNo) {
    const btn = document.getElementById(`sp_preview_area_${districtCodeNo}`);
    if (btn) btn.closest('.selected_keyword').remove();
}

// 선택된 값이 없으면 숨김 + 클래스 토글
function toggleKeywordDisplay() {
    const hasKeywords = selectedContainer.children.length > 0;
    selectedContainer.style.display = hasKeywords ? 'block' : 'none';

    // 검색조건 여부에 따라 resetting 클래스 토글
    if (hasKeywords) {
        previewWrapper.classList.remove('resetting'); // 조건 있음
    } else {
        previewWrapper.classList.add('resetting'); // 조건 없음
    }
}



//----------------------------------------------------------------------------------------------------------




