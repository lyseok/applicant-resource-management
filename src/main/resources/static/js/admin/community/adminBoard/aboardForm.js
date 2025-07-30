const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroup = document.querySelector("#codeGroup");
const memType = document.querySelector("#memType");
let updateNo = null;  // 전역으로 선언
//let userId = window.userId;

// 무조건 처음 한번 실행 되는 부분
//1차 옵션 추가
const addopt = function(type){
	aboardform.style.display = "block";
	TypoBox_searchBar.style.display = 'none';
	document.querySelector('.PageBox').innerHTML = "";
	//취소 버튼 이벤트 등록
	let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');  
	backBtn.addEventListener("click", function(){
	    // 등록 폼에서 취소
		console.log("등록에서 취소?", type);
		aboardForm.style.display = "none";
		alist2(type);
		//등록하다가 취소해도 리셋은 해야 다음에 등록 시 데이터 중복 생성되지 않음
		aboardform.reset(); //폼은 초기화
        //옵션 초기화
        bdis();
        cdis(); codeGroup.disabled = true;	
        mdis(); memType.disabled = true;
        //수정 상태 해제 - 전역의 값 비워줘야
        updateNo = null;
	})
	fetch(`/ajax/code/cmncodegroup/BRDD`).then((resp) => {
	  resp.json().then((rslt) => {
	    rslt.cmnCodeList.map((v, i) => {
	      if (i > 0) {  //vo[1], vo[2] 만 넣어야 함
	        let option = document.createElement("option");
	        option.value = v.codeDetailNo;
	        option.innerHTML = v.codeName;
	        boardTypeCode.appendChild(option);
	      }
	    });
	  });
	});
}

//등록 버튼 누를 경우
aboardform.onsubmit = function (e) {
	  e.preventDefault();
	  //JSON Object
	  //JavaScript Object Notation => {"키":값}
	  /*
		1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
		2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
		3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
		*/
	  let adminBoard = {
	    userId: aboardform.userId.value,
	    boardTypeCode: boardTypeCode.value,
	    cmnCodeGroupVOList: [
	      {
	        codeGroupNo: codeGroup.value,
	        cmnCodeList: [{ codeDetailNo: memType.value }],
	      },
	    ],
	    boardTitle: aboardform.boardTitle.value,
	    boardContent: aboardform.boardContent.value,
	  };
	  /*
		{
			"userId": "testAdmin",
			"boardTypeCode": "BRDD-002",
			"codeGroupNo": "UFAQ",
			"memType": "UFAQ-U2",
			"boardTitle":"제목 연습",
			"boardContent": "내용 연습"
		}
		*/
	  console.log("adminBoard(JSON Object) : ", adminBoard);
	  // 등록 or 수정 구분
	  const url = updateNo
	    ? `/ajax/admin/board/admin_board/detail/${updateNo}`
	    : `/ajax/admin/board/admin_board/${adminBoard.boardTypeCode}`;
	
	  fetch(url, {
	    method: "post",
	    headers: { "Content-Type": "application/json" },
	    body: JSON.stringify(adminBoard),
	  }).then((resp) => {
	    resp.json().then((rslt) => {
	      if (rslt.ok && rslt.boardNo) {
	        abno(rslt.boardNo); // 상세 페이지로 이동
	        aboardform.reset(); //폼 초기화
	        //옵션 초기화
	        bdis();
	        cdis(); codeGroup.disabled = true;	
	        mdis(); memType.disabled = true;
	        //수정 상태 해제
	        updateNo = null;
	      }
	   });
	});
};

//수정에서 넘어올 시, 0차 옵션 기입하고 선택
const addopt2 = function(type){
	fetch(`/ajax/code/cmncodegroup/BRDD`).then((resp) => {
	  resp.json().then((rslt) => {
	    rslt.cmnCodeList.map((v, i) => {
	      if (i > 0) {  //vo[1], vo[2] 만 넣어야 함
	        let option = document.createElement("option");
	        option.value = v.codeDetailNo;
	        option.innerHTML = v.codeName;
	        boardTypeCode.appendChild(option);
	      }
	    });
		boardTypeCode.value = type;  //수정에서 가져온 type을 0차 옵션에 배정
		if3(type);  
	  });
	});
	//이 줄에서 값을 할당해버리면 fetch가 돌기 전이라서 순서가 거꾸로 됨
}

//1차 옵션 선택
boardTypeCode.onchange = function () {
	if1();
    //2차 옵션 추가
    fetch(`/ajax/admin/board/admin_board/group/${boardTypeCode.value}`).then(resp=>{
       resp.json().then(rslt=>{
		let codeGroupNos = [];
		 rslt.forEach(codeVO => {
			
			let codeNo = codeVO.codeGroupNo;
			let description = (codeVO.description || '').split(" ")[0];
			
              if(!codeGroupNos.includes(codeNo)){
                 codeGroupNos.push(codeNo);

				let option = document.createElement("option");
                 
				option.value = codeNo;
				option.innerText = `${description}`;

				codeGroup.appendChild(option);
              }
          });
		})
    });
};

//수정시 1차 옵션 기입 - 공지사항
boardTypeCode.onchange2 = function (type) {
	codeGroup.disabled = false;	
    fetch(`/ajax/admin/board/admin_board/group/BRDD-003`).then(resp=>
       resp.json()).then(rslt=>{
		let codeGroupNos = [];
		 rslt.forEach(codeVO => {
			let codeNo = codeVO.codeGroupNo;
			let description = (codeVO.description || '').split(" ")[0];
              if(!codeGroupNos.includes(codeNo)){
                 codeGroupNos.push(codeNo);
				let option = document.createElement("option");
				option.value = codeNo;
				option.innerText = `${description}`;
				codeGroup.appendChild(option);
              }
        });
	    codeGroup.value = type;
	})
};

//수정시 1차 옵션 기입 - 자주묻는질문
boardTypeCode.onchange3 = function (type) {
	codeGroup.disabled = false;	
    fetch(`/ajax/admin/board/admin_board/group/BRDD-002`).then(resp=>
       resp.json()).then(rslt=>{
		let codeGroupNos = [];
		 rslt.forEach(codeVO => {
			let codeNo = codeVO.codeGroupNo;
			let description = (codeVO.description || '').split(" ")[0];
              if(!codeGroupNos.includes(codeNo)){
                 codeGroupNos.push(codeNo);
				let option = document.createElement("option");
				option.value = codeNo;
				option.innerText = `${description}`;
				codeGroup.appendChild(option);
              }
        });
	    codeGroup.value = type.split("-")[0];
	})
};

codeGroup.onchange = function(){
	if2();
	fetch(`/ajax/admin/board/admin_board/cmn/${codeGroup.value}`).then(resp=>{
		resp.json().then(rslt=>{
			rslt.forEach(item=>{
				let option = document.createElement("option");
				option.value = item.codeDetailNo;
		    	option.innerHTML = item.codeName;
		    	memType.appendChild(option);
			})
		})
	})
}

//수정시 2차 옵션 기입
codeGroup.onchange2 = function(type){
	memType.disabled = false;
	let ctype = type.split("-")[0];
	fetch(`/ajax/admin/board/admin_board/cmn/${ctype}`).then(resp=>
		resp.json()).then(rslt=>{
			rslt.forEach(item=>{
				let option = document.createElement("option");
				option.value = item.codeDetailNo;
		    	option.innerHTML = item.codeName;
		    	memType.appendChild(option);
			})
		memType.value = type;
	})
}

const if1 = function(){
	if(boardTypeCode.value === '-1'){
		cdis();
		codeGroup.disabled = true;
	}else if(boardTypeCode.value ==='BRDD-003'){
		cdis();
		memType.disabled = true;
	}else if(boardTypeCode.value ==='BRDD-002'){
		cdis();
	}
}

const if2 = function(){
	if(codeGroup.value === '-1'||boardTypeCode.value ==='BRDD-003'){
		mdis();
		memType.disabled = true;
	}else{
		mdis();
	}
}

//수정에서 넘어오는 옵션 데이터 기입
const if3 = function(type){
	
	if(type.includes('NTC')){
		boardTypeCode.value ='BRDD-003';  //비교 연산자 아니고 할당 연산자로
		boardTypeCode.onchange2(type);
	}else{
		boardTypeCode.value ='BRDD-002';
		boardTypeCode.onchange3(type);
		codeGroup.onchange2(type);
	}
}

const bdis = function(){
	boardTypeCode.innerHTML = "";
	let bopt = document.createElement("option");
	bopt.value = "-1";
	bopt.textContent = "--선택--";
	boardTypeCode.appendChild(bopt);
	boardTypeCode.value = "-1";
}

const cdis = function(){
	codeGroup.disabled = false;		
	codeGroup.innerHTML = "";
	let copt = document.createElement("option");
	copt.value = "-1";
	copt.textContent = "--선택--";
	codeGroup.appendChild(copt);
	codeGroup.value = "-1";		
	
	if2();
}

const mdis = function(){
	memType.disabled = false;		
	memType.innerHTML = "";
	let mopt = document.createElement("option");
	mopt.value = "-1";
	mopt.textContent = "--선택--";
	memType.appendChild(mopt);
	memType.value = "-1";		
}

// 수정에서 넘어오는 제목, 내용 데이터 기입
const abno2 = function(no){
	updateNo = no;  // 전역변수에 저장
	
	fetch(`/ajax/admin/board/admin_board/detail/${no}`)
	  .then((resp) => resp.json())
	  .then((rslt) => {
	    let nohi = document.querySelector("#noHidden");
	    if (nohi) nohi.value = rslt.boardNo;  
        let boti = document.querySelector('input[name="boardTitle"]');
        if (boti) boti.value = rslt.boardTitle;
        let usid = document.querySelector('input[name="userId"]');
        if (usid) usid.value = rslt.userId;
        let boct = document.querySelector('textarea[name="boardContent"]');
        if (boct) boct.value = rslt.boardContent;
	});
	
}

// 등록 or 수정 폼 데이터
const aform = function(no, type){
	console.log("디테일에서 넘어온 수정 번호", no);
	console.log("디테일에서 넘어온 수정 타입", type);
	allBtns.innerHTML = '';
	
	addopt2(type);  //옵션 넣어줌
	abno2(no);  //제목, 내용 넣어줌
	
	// 폼 취소
	let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');
	backBtn.addEventListener("click", function(){
		if(updateNo){  //updateNo에 담긴 no가 null이 아니라서 수정일 때만 상세보기로
			abno(updateNo);  //상세보기 렌더링해주고
			
			aboardform.reset(); //폼은 초기화
	        //옵션 초기화
	        bdis();
	        cdis(); codeGroup.disabled = true;	
	        mdis(); memType.disabled = true;
	        //수정 상태 해제 - 전역의 값 비워줘야
	        updateNo = null;
	    }else{  //등록이면 리스트로
			alist2(type);
		}
	})
}




