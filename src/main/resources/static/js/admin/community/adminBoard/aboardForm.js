const aboardForm = document.querySelector("#aboardForm");
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroup = document.querySelector("#codeGroup");
const memType = document.querySelector("#memType");

// 무조건 처음 한번 실행 되는 부분
//1차 옵션 추가
fetch(`/ajax/code/cmncodegroup/BRDD`).then((resp) => {
  resp.json().then((rslt) => {
    console.log("1차 옵션 선택 : ", rslt);

    rslt.cmnCodeList.map((v, i) => {
      //vo[1], vo[2] 만 넣어야 함
      if (i > 0) {
        let option = document.createElement("option");
        option.value = v.codeDetailNo;
        option.innerHTML = v.codeName;

        boardTypeCode.appendChild(option);
      }
    });
  });
});

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
	first();
	alert("1차 옵션 선택됨!");
let code = boardTypeCode.value;
    //2차 옵션 추가
    fetch(`/ajax/admin/board/admin_board/group/${code}`).then(resp=>{
       resp.json().then(rslt=>{
		rslt.forEach(item=>{
			let option = document.createElement("option");
			option.value = item.codeGroupNo;
	    	option.innerHTML = (item.description).split(" ")[0];
	    	codeGroup.appendChild(option);
		})
    });
  });
};

codeGroup.onchange = function(){
	alert("2차 옵션 선택됨!");
let type = codeGroup.value;
	fetch(`/ajax/admin/board/admin_board/cmn/${type}`).then(resp=>{
		resp.json().then(rslt=>{
			rslt.forEach(item=>{
				let option = document.createElement("option");
				option.value = item.codeDetailNo;
		    	option.innerHTML = item.codeName;
		    	memType.appendChild(option);
			})
		})
	})
	mdis();
}


cdis = function(){
	alert("2차가 풀림!");
	codeGroup.disabled = false;		
	codeGroup.innerHTML = "";
	copt = document.createElement("option");
	copt.value = "-1";
	copt.textContent = "--선택--";
	codeGroup.appendChild(copt);
	codeGroup.value = "-1";		
}

mdis = function(){
	alert("3차가 풀림!");
	memType.disabled = false;		
	memType.innerHTML = "";
	mopt = document.createElement("option");
	mopt.value = "-1";
	mopt.textContent = "--선택--";
	memType.appendChild(mopt);
	memType.value = "-1";		
}

defa = function(){
	alert("기본값!");
	codeGroup.disabled = true;
	codeGroup.value = "-1";
	memType.disabled = true;
	memType.value = "-1";
}

first = function(){
	if(boardTypeCode !== '-1'){
		cdis();
	}
	alert("조건문 첫째!");
}

second = function(){
	if(boardTypeCode === 'BRDD-003'){
		mdis();
		memType.disabled = true;
	}
	alert("조건문 둘째!");
}

third = function(){
	if(boardTypeCode === 'BRDD-002'){
		cdis();
	}
	alert("조건문 셋째!");
}

every = function(){
	if(boardTypeCode === '-1'){
		defa();		
	}
	alert("조건문!");
}
