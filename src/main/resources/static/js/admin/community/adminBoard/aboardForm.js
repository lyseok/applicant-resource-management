const aboardForm = document.querySelector("#aboardForm");
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroup = document.querySelector("#codeGroup");
const memType = document.querySelector("#memType");

// 무조건 처음 한번 실행 되는 부분
//1차 옵션 추가
const addopt = function(){
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

addopt();

//등록 버튼 누를 경우
aboardForm.onsubmit = function (e) {
  e.preventDefault();
  //JSON Object
  //JavaScript Object Notation => {"키":값}
  /*
	1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
	2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
	3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
	*/
  let adminBoard = {
    userId: aboardForm.userId.value,
    boardTypeCode: boardTypeCode.value,
    cmnCodeGroupVOList: [
      {
        codeGroupNo: codeGroup.value,
        cmnCodeList: [{ codeDetailNo: memType.value }],
      },
    ],
    boardTitle: aboardForm.boardTitle.value,
    boardContent: aboardForm.boardContent.value,
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

  //등록 비동기 이벤트
  fetch(`/ajax/admin/board/admin_board/${adminBoard.boardTypeCode}`, {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(adminBoard),
  }).then((resp) => {
    resp.json().then((rslt) => {
      console.log("글자", rslt.ok);
    });
  });
};

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

//수정에서 넘어올 때
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

const abno2 = function(no){
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

const aform = function(no, type){
	console.log("디테일에서 넘어온 수정", no);
	console.log("디테일에서 넘어온 수정2", type);
	if3(type);  //옵션 넣어줌
	abno2(no);  //제목, 내용 넣어줌
}

