/**
 * 
 */
const ulcity1 = document.querySelector('#1stcityopt > ul');  //내가 따로 부여한 id

const li1 = function(codeno, name){
	let depth1li = document.createElement('li');
	depth1li.className = 'depth1_btn_wrapper';  //활성화된 것에는 depth1_btn_wrapper on 이 붙음
	depth1li.id = `depth1_btn_${codeno}`;
	ulcity1.appendChild(depth1li);
	
	//console.log(ulcity1.innerHTML);
	bt1(codeno, name);
}

const bt1 = function(codeno, name){
	let licity1 = document.querySelector(`#depth1_btn_${codeno}`);
	
	let depth1btn = document.createElement('button');
	depth1btn.type = 'button';
	depth1btn.className = `depth1_btn_${codeno}`;
	depth1btn.setAttribute('data-code', `${codeno}`);
	licity1.appendChild(depth1btn);
	
	//console.log(licity1.innerHTML);
	sp1(codeno, name);
}

const sp1 = function(codeno, name){
	let btncity1 = document.querySelector(`#depth1_btn_${codeno} > button`);
	
	let sptxt = document.createElement('span');
	sptxt.className = 'txt';
	sptxt.textContent = `${name}`;
	
	let spcnt = document.createElement('span');
	spcnt.className = 'count';
	spcnt.textContent = '(58,664)';  //채용공고 리스트 개수랑 조인해서 받을 부분
	
	btncity1.appendChild(sptxt);
	btncity1.appendChild(spcnt);

	//console.log(btncity1.innerHTML);
	
	btncity1.onclick = function(){
	    let code = button.dataset.code;  //data-code
	    console.log('클릭된 도시 코드:', code);
	    cityData2(code);
    }
}

//첫 카테고리 기입
const cityData1 = function () {
  ulcity1.innerHTML = '';  //내부 비우고
  fetch('/ajax/admin/cityCode')
    .then(resp => resp.json())
    .then(data => {
	  data.forEach(city =>{
		let codeno = city.cityCodeNo.slice(4);
		let name = city.cityName;
		li1(codeno, name);
	  })
   });
};

cityData1();  //실행시키고 보자

/*
<div class="wrap_list_check" id="2ndcityopt">
	<ul class="list_check" id="sp_area_lastDepth_117000" style="display: none;">
	    <li>
	        <div class="inpChk">
	            <input type="checkbox" id="loc_mcd_117000" name="loc_mcd[]" value="117000" 
	            data-is_representative="n" data-representative="" data-mcode="117000" data-check-type="all">
	            <label for="loc_mcd_117000" class="lbl"><span class="txt">전국</span></label>
	        </div>
	    </li>
	</ul>
</div>
*/

const ulcity2 = document.querySelector('#2ndcityopt');  //이건 div

//두번째 카테고리
const cityData2 = function (code) {
  ulcity2.innerHTML = '';  //div 내부 비우고 ul부터 생성해야
  fetch(`/ajax/admin/cityCode/${code}`)
    .then(resp => resp.json())
    .then(data => {
	  data.forEach(city =>{
		//city.cityCodeNo;
		//city.cityName;
		let codeno = city.cityCodeNo.slice(4);
		let name = city.cityName;
		li1(codeno, name);
	  })
   });
};


//지역 검색 부분
const searchBar = function(){
    fetch('/ajax/member/recruit/city/list')
        .then(res => res.json())
        .then(data => {
            console.log("data?", data);
        })
}

//채용공고 리스트 부분
 