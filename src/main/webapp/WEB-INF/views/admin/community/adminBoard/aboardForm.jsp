<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 등록</title>
	<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
</head>

<body>

<p class="h4">관리자 게시판</p><br>

  <h1><sec:authentication property="principal.realUser.userId" var="userId"/></h1>
	<form id="aboardForm">
		<input type="hidden" name="userId" value="${userId}"><br>
		<label>게시판 유형 코드</label>
		<select id="boardTypeCode">
			<option value="-1">--선택--</option>
		</select>
		<select id="codeGroupNo" disabled>
			<option value="-1">--선택--</option>
			<option value="UFAQ">일반회원</option>
			<option value="CFAQ">기업회원</option>
		</select>
		<select id="memType" disabled>
			<option value="-1">--선택--</option>
		</select>
		<br>
		<input type="text" name="boardTitle" placeholder="제목">
		<input type="text" name="boardContent" placeholder="내용">
	  <button type="submit">등록</button>
	</form>
<script>
const aboardForm = document.querySelector("#aboardForm");
//<select id="boardTypeCode">..</select>
//엘리먼트(object)     <body>다큐먼트</body> 그 안에 들어있는 id속성의 값이 boardTypeCode인 엘리먼트
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroupNo = document.querySelector("#codeGroupNo");
const memType = document.querySelector("#memType");

aboardForm.onsubmit = function(){
	event.preventDefault();
	//JSON Object
	//JavaScript Object Notation => {"키":값}
	/*
	1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
	2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
	3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
	*/
	let adminBoard = {
		"userId":aboardForm.userId.value,
		"boardTypeCode":boardTypeCode.value,
		"cmnCodeGroupVOList":[
			{
				"codeGroupNo":codeGroupNo.value,
				"cmnCodeList":[{"codeDetailNo":memType.value}]
			}
		],
		"boardTitle":aboardForm.boardTitle.value,
		"boardContent":aboardForm.boardContent.value
	}
	console.log("boardTypeCode가 2인지 1인지 : ", boardTypeCode);
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

	fetch(`/ajax/admin/board/admin_board/\${adminBoard.boardTypeCode}`, {
		method : "post",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify(adminBoard)
	}).then(resp => {
		resp.json().then(rslt=> {
			console.log("글자", rslt.ok);
		})
	})
}

fetch(`/ajax/code/cmncodegroup/BRDD`)
.then(resp => {
	resp.json()
	.then(rslt =>{
		console.log("BRDD 반복문 : ", rslt);
		
		rslt.cmnCodeList.map((v,i) => {  //vo[1], vo[2] 만 넣어야 함
			if(i > 0){
				let option = document.createElement("option");
				option.value = v.codeDetailNo;
				option.innerHTML = v.codeName;
				
				boardTypeCode.appendChild(option);
			}
		})
		
	})
})

boardTypeCode.onchange = function(){
if(boardTypeCode.value === 'BRDD-002'){
	codeGroupNo.disabled = false;
	codeGroupNo.onchange = function(){
		if(codeGroupNo.value === '-1'){
			//첫번째 option 엘리먼트 선택
			memType.selectedIndex = 0;
			memType.disabled = true;
		}else{	
			memType.disabled = false;

			if(memType.length != 0 || codeGroupNo == -1){
				memType.innerHTML = "";
				
				memOpt = document.createElement("option");
				memOpt.value = "-1";
				memOpt.textContent = "--선택--";
				memType.appendChild(memOpt);

				memType.value = "-1";
			} 

			console.log("url 확인해~ : ", `/ajax/code/cmncodegroup/\${codeGroupNo.value}`);
			fetch(`/ajax/code/cmncodegroup/\${codeGroupNo.value}`)
				.then(resp => {
					resp.json()
					.then(rslt =>{
						console.log(" select 값? : ", rslt);

						rslt.cmnCodeList.forEach(vo => { 
						let option = document.createElement("option");
						option.value = vo.codeDetailNo;
						option.innerHTML = vo.codeName;
						
						memType.appendChild(option);
					})

				})
			})  

		}
	}//end codeGroupNo.onchange
}else{
	codeGroupNo.disabled = true;
	codeGroupNo.value = "-1";
	memType.disabled = true;
	memType.value = "-1";
}
	

}
</script>
</body>