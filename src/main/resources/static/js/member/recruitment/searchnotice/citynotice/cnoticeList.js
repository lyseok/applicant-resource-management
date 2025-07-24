/**
 * 
 */
const ulcity1 = document.querySelector('#first_cityopt > ul');  //첫번째 옵션 생성시 쓰일 ul태그
const divcity2 = document.querySelector('#second_cityopt');  //두번째 옵션 생성용 div

//지역 초기화 & 펼쳐보기 버튼 생성
const recityOpt = function(){
	console.log("여러번 생성?");
	
	let divcity__2 = document.createElement('div');
	divcity__2.className = 'area_btn';
	divcity2.appendChild(divcity__2);
	
	if(divcity2 && divcity2.querySelector(`.area_btn`)){
		let btncity2 = document.createElement('button');
		btncity2.type = 'button';
		btncity2.className = 'btn_all_category';
		btncity2.setAttribute('data-logging-flow', 'area');
		btncity2.textContent = '지역 펼쳐보기';
		
		let btncity_2 = document.createElement('button');
		btncity_2.type = 'button';
		btncity_2.className = 'btn_reset';
		btncity_2.textContent = '지역 초기화';
		
		btncity_2.onclick = function(){
			ulcity1.querySelectorAll('li').forEach(li => li.classList.remove('on'));
		}
		
		divcity__2.appendChild(btncity2);
		divcity__2.appendChild(btncity_2);
	}
}

//첫번째 옵션 생성 시작
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
   //recityOpt();  //생성해두면 알아서 before after로 조절, 처음과 이후 클릭시 생성
};

//첫번째 옵션 li 생성
const li1 = function(codeno, name){
	
	let depth1li = document.createElement('li');
	depth1li.className = 'depth1_btn_wrapper';  //활성화된 것에는 depth1_btn_wrapper on 이 동적으로 붙음
	depth1li.id = `depth1_btn_${codeno}`;
	ulcity1.appendChild(depth1li);
	
	bt1(codeno, name);
}

//첫번째 옵션 버튼 생성
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

//첫번째 옵션 스판 생성
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
	
	let depth1btn = document.querySelector(`.depth1_btn_${codeno}`);
	
	//없다가 클릭시 두번째 옵션 미리 생성되게
	depth1btn.onclick = function(){
		console.log(`여는 지역 클릭됨: ${codeno} / ${name}`);
	
	    //클릭한 li에만 on 클래스 추가되고(중첩 허용이니 on 자체는 제거 안함)
	    this.classList.add('on');

	    // ✅ 기존 열린 모든 2차 ul 닫기
	    document.querySelectorAll('#second_cityopt .list_check').forEach(ul => {
	      console.log("닫기");
	      ul.style.display = 'none';
	    });
	    
	    //ul 생성
	    ul2(codeno);
	    
	    
		//cityData2(codeno);  //두번째 옵션도 클릭시 생성
		//recityOpt();  //생성해두면 알아서 before after로 조절
	}
}

//두번째 옵션 ul 생성
const ul2 = function(codeno){
	
	let ulDepth = document.createElement('ul');
	ulDepth.className = 'list_check';
	ulDepth.id = `sp_area_lastDepth_${code}`;
	ulDepth.style.display = 'block';  //클릭시 기본적으로 생성되자마자 block이고 다른 게 클릭되면 none이 되게
	divcity2.appendChild(ulDepth);
	
	console.log(divcity2.innerHTML);
	
	if(divcity2 && divcity2.querySelector('.list_check')){  //해당 div가 있고 그 안에 ul이 있으면
		let li2 = document.createElement('li');  //빈 li 생성 그냥 여기서
		ulDepth.appendChild(li2);  //방금 만든 ul에 li 추가
		
		cityData2(codeno);  //클릭시 생기는 두번째 옵션의 div들 여기서 호출, codeno = 107000
	}
}

cityData1();  //첫번째 옵션 생성 호출
recityOpt();  //생성해두면 알아서 before after로 조절

//---------------------------------------------------------------------


//두번째 옵션 생성 시작
const cityData2 = function (code) {  //code = 107000
  //console.log("url?", `/ajax/admin/cityCode/CICO${code}`);
  
  divcity2.innerHTML = '';  //div 비움
  fetch(`/ajax/admin/cityCode/CICO${code}`)
    .then(resp => {resp.json()
    .then((data) => {
		/*
		for(i=0; i<data.length; i++){
			ul2(data[i].cityCodeNo.slice(4), data[i].districtCodeNo, data[i].districtName);
		}
		*/
		data.forEach((city)=>{
			let code = city.cityCodeNo.slice(4);
			let codeno = city.districtCodeNo;
			let name = city.districtName;
			div2(code, codeno, name);  //div가 반복적으로 생성!
		})		
	  })
   })
};

//두번째 옵션 div 생성
const div2 = function(code, codeno, name){
	let licity2 = document.querySelector(`#sp_area_lastDepth_${code} > li`);  //아까 만든 ul의 li
	
	let divDepth = document.createElement('div');
	divDepth.className = 'inpChk';
	divDepth.id = `second_divopt_${codeno}`;
	licity2.appendChild(divDepth);
	
	//console.log(licity2.innerHTML);
	
	input2(codeno, name);
}

//두번째 옵션 input 생성
const input2 = function(codeno, name){
	let divcity_2 = document.querySelector(`#second_divopt_${codeno}`);
	
	let inputDepth = document.createElement('input');
	inputDepth.type = 'checkbox';
	inputDepth.id = `loc_mcd_${codeno}`;
	inputDepth.name = `loc_cd[]`;
	inputDepth.value = `${codeno}`;
	inputDepth.setAttribute('data-is_representative', `n`);
	inputDepth.setAttribute('data-representative', '');
	inputDepth.setAttribute('data-mcode', `${codeno}`);
	//전국이나 전체일 때
	if(name === '전국' || name.includes('전체')){
		inputDepth.setAttribute('data-check-type', 'all');
		inputDepth.name = `loc_mcd[]`;
	}
	divcity_2.appendChild(inputDepth);
	label2(codeno, name);
}

//두번째 옵션 label 생성
const label2 = function(codeno, name){
	let divcity_2 = document.querySelector(`#second_divopt_${codeno}`);  //얘도 div에 주는 거라 let으로 한번더 명명
	
	let labelDepth = document.createElement('label');
	labelDepth.setAttribute('for', `loc_mcd_${codeno}`);
	labelDepth.className = 'lbl';
	divcity_2.appendChild(labelDepth);
	
	//span 생성
	if(divcity_2 && divcity_2.querySelector('input') && divcity_2.querySelector('label')){
		let sptxt2 = document.createElement('span');
		sptxt2.className = 'txt';
		sptxt2.textContent = `${name}`;
		labelDepth.appendChild(sptxt2);
		
		if(name !== '전국' && !name.includes('전체')){
			let spcnt2 = document.createElement('span');
			spcnt2.className = 'count';
			spcnt2.textContent = `(1,180)`;  //추후에 채용공고 리스트와 조인한 값 기입
			labelDepth.appendChild(spcnt2);
		}
	}
}



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
 