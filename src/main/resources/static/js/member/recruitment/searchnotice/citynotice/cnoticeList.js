/**
 * 
 */
const ulcity1 = document.querySelector('#first_cityopt > ul');  //내가 따로 부여한 id

const li1 = function(codeno, name){
	let depth1li = document.createElement('li');
	depth1li.className = 'depth1_btn_wrapper';  //활성화된 것에는 depth1_btn_wrapper on 이 동적으로 붙음
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
	    let code = btncity1.dataset.code;  //data-code 값 가져옴
	    //console.log('클릭된 도시 코드:', code);
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

//---------------------------------------------------------------------

const divcity2 = document.querySelector('#second_cityopt');  //이건 div

//ul 생성
const ul2 = function(codeno, name){
	let ulDepth = document.createElement('ul');
	ulDepth.className = 'list_check';
	ulDepth.id = `sp_area_lastDepth_${codeno}`;
	ulDepth.style.display = 'none';  //1차 옵션 선택값의 codeno와 같으면 display='block'(동적)
	divcity2.appendChild(ulDepth);
	
	//console.log(divcity2.innerHTML);
	
	if(divcity2 && divcity2.querySelector('.list_check')){  //해당 div가 있고 그 안에 ul이 있으면
		let ulcity2 = document.querySelector(`#sp_area_lastDepth_${codeno}`);  //방금 만든 ul 태그 명명
		let li2 = document.createElement('li');  //빈 li 생성 그냥 여기서
		ulcity2.appendChild(li2);  //방금 만든 ul에 li 추가
		
		//console.log(ulcity2.innerHTML);
		
		div2(codeno, name);
	}
}

//div 생성
const div2 = function(codeno, name){
	let licity2 = document.querySelector(`#sp_area_lastDepth_${codeno} > li`);  //아까 만든 ul의 li
	
	let divDepth = document.createElement('div');
	divDepth.className = 'inpChk';
	divDepth.id = `second_divopt_${codeno}`;
	licity2.appendChild(divDepth);
	
	//console.log(licity2.innerHTML);
	
	input2(codeno, name);
}

//input 생성
const input2 = function(codeno, name){
	let divcity_2 = document.querySelector(`#second_divopt_${codeno}`);
	
	let inputDepth = document.createElement('input');
	inputDepth.type = 'checkbox';
	inputDepth.id = `loc_mcd_${codeno}`;
	inputDepth.name = `loc_mcd[]`;
	inputDepth.value = `${codeno}`;
	inputDepth.setAttribute('data-is_representative', `n`);
	inputDepth.setAttribute('data-representative', '');
	inputDepth.setAttribute('data-mcode', `${codeno}`);
	//전국이나 전체일 때
	if(name === '전국' || name.includes('전체')){
		inputDepth.setAttribute('data-check-type', 'all');
	}
	divcity_2.appendChild(inputDepth);
	
	//console.log("인풋 만들고", divcity_2.innerHTML);
	
	label2(codeno, name);
}

//label 생성
const label2 = function(codeno, name){
	let divcity_2 = document.querySelector(`#second_divopt_${codeno}`);  //얘도 div에 주는 거라 let으로 한번더 명명
	
	let labelDepth = document.createElement('label');
	labelDepth.setAttribute('for', `loc_mcd_${codeno}`);
	labelDepth.className = 'lbl';
	divcity_2.appendChild(labelDepth);
	
	//console.log("라벨 만들고", divcity_2.innerHTML);
	
	//span 생성
	if(divcity_2 && divcity_2.querySelector('input') && divcity_2.querySelector('label')){
		let labelcity2 = document.querySelector(`#second_divopt_${codeno} > .lbl`);
		
		let sptxt2 = document.createElement('span');
		sptxt2.className = 'txt';
		sptxt2.textContent = `${name}`;
		labelcity2.appendChild(sptxt2);
		
		if(name !== '전국' && !name.includes('전체')){
			let spcnt2 = document.createElement('span');
			spcnt2.className = 'count';
			spcnt2.textContent = `(1,180)`;  //추후에 채용공고 리스트와 조인한 값 기입
			labelcity2.appendChild(spcnt2);
		}
		//console.log(labelcity2.innerHTML);
	}
	//console.log("스판 만들고", divcity_2.innerHTML);
}


//지역 초기화
const recityOpt = function(){
	let divcity__2 = document.createElement('div');
	divcity__2.className = 'area_btn';
	divcity2.appendChild(divcity__2);
	
	if(divcity2 && divcity2.querySelector(`.area_btn`)){
		let divcity__2 = divcity2.querySelector(`.area_btn`);
		
		let btncity2 = document.createElement('button');
		btncity2.type = 'button';
		btncity2.className = 'btn_all_category';
		btncity2.setAttribute('data-logging-flow', 'area');
		btncity2.textContent = '지역 펼쳐보기';
		
		let btncity_2 = document.createElement('button');
		btncity_2.type = 'button';
		btncity_2.className = 'btn_reset';
		btncity_2.textContent = '지역 초기화';
		
		divcity__2.appendChild(btncity2);
		divcity__2.appendChild(btncity_2);
		
		//console.log(divcity__2.innerHTML);
	}
}

//두번째 카테고리
const cityData2 = function (code) {
  //console.log("url?", `/ajax/admin/cityCode/CICO${code}`);
  
  divcity2.innerHTML = '';  //div 비움
  fetch(`/ajax/admin/cityCode/CICO${code}`)
    .then(resp => resp.json())
    .then(data => {
	  //console.log("data?",data);
	  data.forEach(city =>{
		let codeno = city.districtCodeNo;
		let name = city.districtName;
		ul2(codeno, name);
	  })
   });
   
   recityOpt();  //초기화 버튼 호출
};

//----------------------------------------------------------------

//지역 검색 부분
const searchBar = function(){
    fetch('/ajax/member/recruit/city/list')
        .then(res => res.json())
        .then(data => {
            console.log("data?", data);
        })
}

//채용공고 리스트 부분
 