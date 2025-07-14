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
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroupNo = document.querySelector("#codeGroupNo");
const memType = document.querySelector("#memType");

aboardForm.onsubmit = function(){
	event.preventDefault();
	let adminBoard = {
		userId : aboardForm.userId.value,
		boardTypeCode : aboardForm.boardTypeCode.value,
		boardTitle : aboardForm.boardTitle.value,
		boardContent : aboardForm.boardContent.value
	}
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
		
		rslt.cmnCodeList.forEach(vo => {  //vo[1], vo[2] 만 넣어야 함
			let option = document.createElement("option");
			option.value = vo.codeDetailNo;
			option.innerHTML = vo.codeName;
			
			boardTypeCode.appendChild(option);
		})
		
	})
})

boardTypeCode.onchange = function(){
if(boardTypeCode.value === 'BRDD-002'){
	codeGroupNo.disabled = false;
	codeGroupNo.onchange = function(){
	if(codeGroupNo.value === '-1'){
		memType.disabled = true;
	}else{
		memType.disabled = false;

		if(memType.length != 0){
			memType.innerHTML = "";
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
}
}else{
	codeGroupNo.disabled = true;
	codeGroupNo.value = "-1";
	memType.disabled = true;
}
	

}
</script>
</body>