const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroup = document.querySelector("#codeGroup");
const memType = document.querySelector("#memType");
let updateNo = null;

// 등록 & 수정 폼 열기
// 옵션 로딩 (등록폼)
const addopt = function(type){
    aboardform.style.display = "block";
    boardTypeCode.innerHTML = `<option value="-1">--선택--</option>`;  // 항상 초기화

    fetch(`/ajax/code/cmncodegroup/BRDD`).then(resp => resp.json()).then(rslt => {
        rslt.cmnCodeList.forEach((v, i) => {
            if (i > 0 && v.codeName !== '문의사항') {  // 문의사항 제외
                let option = document.createElement("option");
                option.value = v.codeDetailNo;
                option.innerHTML = v.codeName;
                boardTypeCode.appendChild(option);
            }
        });
    });

    let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');
    backBtn.onclick = () => { resetView(); restoreListWithTabs(); };
};

// 삭제 버튼 클릭 시
if (delBtn) {
    delBtn.onclick = function () {
        const modal = new bootstrap.Modal(modalElement);
        modal.show();

        const confirmBtn = document.querySelector("#deleteModal .btn-danger");
        const newConfirmBtn = confirmBtn.cloneNode(true);
        confirmBtn.parentNode.replaceChild(newConfirmBtn, confirmBtn);

        newConfirmBtn.onclick = function () {
            fetch(`/ajax/admin/board/admin_board/hidden/${no}`, {
                method: "post",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ boardNo: no, boardTypeCode: type }),
            }).then(resp => resp.json())
            .then(rslt => {
                if (rslt.ok) {
                    modal.hide();
                    detTitle.innerHTML = '';  // 상세 타이틀 제거
                    aboardDetail.innerHTML = '';  // 상세 내용 제거
                    restoreListWithTabs();  // 탭 + 목록 복원
                }
            });
        };
    };
}


// 등록 / 수정 제출
aboardform.onsubmit = function (e) {
    e.preventDefault();
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
    const url = updateNo
        ? `/ajax/admin/board/admin_board/detail/${updateNo}`
        : `/ajax/admin/board/admin_board/${adminBoard.boardTypeCode}`;

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(adminBoard),
    })
    .then(resp => resp.json())
    .then(rslt => {
        if (rslt.ok && rslt.boardNo) {
            // 등록 & 수정 후 상세보기로 바로 이동
            abno(rslt.boardNo);
            params.page = 1; 
            aboardform.reset();
            bdis();
            cdis();
            codeGroup.disabled = true;
            mdis();
            memType.disabled = true;
            updateNo = null;
        }
    })
    .catch(err => console.error("등록 오류:", err));
};

// 코드 파싱 함수 (boardTypeCode → mainType/group/subType 분리)
const parseBoardTypeCode = function(fullCode) {
    let mainType = "-1";
    if (fullCode.startsWith("UFAQ") || fullCode.startsWith("CFAQ")) mainType = "BRDD-002";
    else if (["UNTC", "CNTC", "ENTC"].some(prefix => fullCode.startsWith(prefix))) mainType = "BRDD-003";
    else if (fullCode.startsWith("BRDD")) mainType = "BRDD-001";
    let group = fullCode.includes("-") ? fullCode.split("-")[0] : fullCode;
    let subType = fullCode.includes("-") ? fullCode : "-1";
    return { mainType, group, subType };
};

// 취소 버튼
document.querySelector("#aboardForm .btn.btn-secondary").addEventListener("click", handleCancel);

function handleCancel() {
    aboardform.style.display = 'none';
    aboardDetail.innerHTML = '';
    detTitle.innerHTML = '';
    TypoBox_searchBar.style.display = 'block';
    restoreListWithTabs();
}

// 무조건 처음 한번 실행 되는 부분
//1차 옵션 추가
/*
const addopt = function(type){
	aboardform.style.display = "block";
	let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');  //취소 버튼 이벤트
	backBtn.addEventListener("click", function(){
	    resetView();
	    restoreListWithTabs();
	});
	*/
	/*
	backBtn.addEventListener("click", function(){
	    // 등록 or 수정 구분
		console.log("등록에서 취소?", type);
		aboardForm.style.display = "none";
		alist2(type);
	})
	*/
	/*
	document.querySelector('.PageBox').innerHTML = "";
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
*/

//등록 버튼 누를 경우
/*
aboardform.onsubmit = function (e) {
	  e.preventDefault();
	  //JSON Object
	  //JavaScript Object Notation => {"키":값}
	  */
	  /*
		1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
		2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
		3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
		*/
		/*
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
	  */
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
		/*
	  console.log("adminBoard(JSON Object) : ", adminBoard);
	  // 등록 or 수정 구분
	  const url = updateNo
	    ? `/ajax/admin/board/admin_board/detail/${updateNo}`
	    : `/ajax/admin/board/admin_board/${adminBoard.boardTypeCode}`;
		
		// 새 글 등록 완료 후 → 상세보기로 이동
		fetch(url, {
	        method: "POST",
	        headers: { "Content-Type": "application/json" },
	        body: JSON.stringify(adminBoard),
	    })
        .then(resp => resp.json())
        .then(rslt => {
            if (rslt.ok && rslt.boardNo) {
                // 바로 상세보기 이동
                abno(rslt.boardNo);
                params.page = 1; // 등록 후는 1페이지로
                aboardform.reset();
                bdis();
                cdis();
                codeGroup.disabled = true;
                mdis();
                memType.disabled = true;
                updateNo = null;
            }
        })
        .catch(err => console.error("등록 오류:", err));
		*/
	
	  /*
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
*/

//수정에서 넘어올 시, 0차 옵션 기입하고 선택
// 수정에서 넘어올 때 모든 옵션 채우고 값 세팅
const addopt2 = async function(board) {
    // 0차 (게시판 유형)
    let resp = await fetch(`/ajax/code/cmncodegroup/BRDD`);
    let rslt = await resp.json();
    boardTypeCode.innerHTML = "";
    rslt.cmnCodeList.forEach((v, i) => {
        if (i > 0) {
            let option = document.createElement("option");
            option.value = v.codeDetailNo;
            option.innerHTML = v.codeName;
            boardTypeCode.appendChild(option);
        }
    });
    boardTypeCode.value = board.boardTypeCode; // DB에서 가져온 값 세팅

    // 1차 분류
    if (board.boardTypeCode !== '-1') {
        codeGroup.disabled = false;
        let resp2 = await fetch(`/ajax/admin/board/admin_board/group/${board.boardTypeCode}`);
        let rslt2 = await resp2.json();
        codeGroup.innerHTML = "";
        rslt2.forEach(codeVO => {
            let option = document.createElement("option");
            option.value = codeVO.codeGroupNo;
            option.innerHTML = codeVO.description.split(" ")[0];
            codeGroup.appendChild(option);
        });
        codeGroup.value = board.codeGroupNo; // 기존 값 세팅
    }

    // 2차 분류
    if (board.codeGroupNo && board.boardTypeCode !== 'BRDD-003') {
        memType.disabled = false;
        let resp3 = await fetch(`/ajax/admin/board/admin_board/cmn/${board.codeGroupNo}`);
        let rslt3 = await resp3.json();
        memType.innerHTML = "";
        rslt3.forEach(item => {
            let option = document.createElement("option");
            option.value = item.codeDetailNo;
            option.innerHTML = item.codeName;
            memType.appendChild(option);
        });
        memType.value = board.memType; // 기존 값 세팅
    }
};

// 수정 데이터 기입
const abno2 = async function(no) {
    updateNo = no;
    let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');
    backBtn.addEventListener("click", function() { abno(no); });

    let resp = await fetch(`/ajax/admin/board/admin_board/detail/${no}`);
    let board = await resp.json();

    // 제목/내용 세팅
    document.querySelector("#noHidden").value = board.boardNo;
    document.querySelector('input[name="boardTitle"]').value = board.boardTitle;
    document.querySelector('input[name="userId"]').value = board.userId;
    document.querySelector('textarea[name="boardContent"]').value = board.boardContent;

    // 옵션 세팅
    await addopt2({
        boardTypeCode: board.boardTypeCode,
        codeGroupNo: board.codeGroupNo,
        memType: board.memType
    });
};


/*
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
*/

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

//수정 폼 옵션 제외 데이터 기입
/*
const abno2 = function(no){
	updateNo = no;  // 전역변수에 저장
	let backBtn = document.querySelector('button.btn.btn-secondary.px-4.me-2');
	backBtn.addEventListener("click", function(){
	    resetView();
	    restoreListWithTabs();
	});
	*/
	/*
	backBtn.addEventListener("click", function(){
	    // 등록 or 수정 구분
		console.log("수정에서 취소!", no);
		abno(no);
	})
	*/
	/*
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
*/

// 1. 수정 진입 시 호출
// 수정 폼 채우기
const aform = async function(no) {
    allBtns.innerHTML = '';
    aboardform.style.display = 'block';

    // 게시글 데이터 가져오기
    let board = await fetch(`/ajax/admin/board/admin_board/detail/${no}`).then(r => r.json());

    // 옵션 세팅
    await setOptionsForEdit(board);

    // 필드 채우기
    document.querySelector("#noHidden").value = board.boardNo;
    document.querySelector("#boardTitle").value = board.boardTitle;
    document.querySelector('textarea[name="boardContent"]').value = board.boardContent;
    document.querySelector('input[name="userId"]').value = board.userId;

    updateNo = no;
};

// 수정 진입 시 3단 옵션 채우기
const setOptionsForEdit = async function(board) {
    let { mainType, group, subType } = parseBoardTypeCode(board.boardTypeCode);

    // 0차 옵션
    let resp = await fetch(`/ajax/code/cmncodegroup/BRDD`);
    let rslt = await resp.json();
    boardTypeCode.innerHTML = `<option value="-1">--선택--</option>`;
    rslt.cmnCodeList.forEach(v => {
        let option = document.createElement("option");
        option.value = v.codeDetailNo;
        option.innerHTML = v.codeName;
        boardTypeCode.appendChild(option);
    });
    boardTypeCode.value = mainType;

    // 1차 옵션
    codeGroup.disabled = false;
    let resp2 = await fetch(`/ajax/admin/board/admin_board/group/${mainType}`);
    let rslt2 = await resp2.json();
    codeGroup.innerHTML = `<option value="-1">--선택--</option>`;
    rslt2.forEach(codeVO => {
        let option = document.createElement("option");
        option.value = codeVO.codeGroupNo;
        option.innerHTML = codeVO.description?.split(" ")[0] || codeVO.codeGroupNo;
        codeGroup.appendChild(option);
    });
    codeGroup.value = group;

    // 2차 옵션 (FAQ 계열만)
    if (mainType === "BRDD-002") {
        memType.disabled = false;
        let resp3 = await fetch(`/ajax/admin/board/admin_board/cmn/${group}`);
        let rslt3 = await resp3.json();
        memType.innerHTML = `<option value="-1">--선택--</option>`;
        rslt3.forEach(item => {
            let option = document.createElement("option");
            option.value = item.codeDetailNo;
            option.innerHTML = item.codeName;
            memType.appendChild(option);
        });
        memType.value = subType;
    } else {
        memType.disabled = true;
        memType.innerHTML = `<option value="-1">--선택--</option>`;
    }
};

// 취소 버튼 동작
document.querySelector("#aboardForm .btn.btn-secondary").addEventListener("click", handleCancel);

function handleCancel() {
    aboardform.style.display = 'none';
    aboardDetail.innerHTML = '';
    detTitle.innerHTML = '';
    TypoBox_searchBar.style.display = 'block';
    fetchData(currentType, currentTab);
}

// 3. 제목/내용 등 세팅
const fillFormFields = function(board) {
  document.querySelector("#noHidden").value = board.boardNo;
  document.querySelector('input[name="boardTitle"]').value = board.boardTitle;
  document.querySelector('input[name="userId"]').value = board.userId;
  document.querySelector('textarea[name="boardContent"]').value = board.boardContent;
};

// 등록 or 수정 폼 데이터
/*
const aform = function(no, type){
	console.log("디테일에서 넘어온 수정 번호", no);
	console.log("디테일에서 넘어온 수정 타입", type);
	allBtns.innerHTML = '';
	
	addopt2(type);  //옵션 넣어줌
	abno2(no);  //제목, 내용 넣어줌
}
*/



