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
		<select name="boardTypeCode">
			<option value="-1">--선택--</option>
			<%--
			<option value="BRDD-003">공지사항</option>
			<option value="UFAQ-U1">개인회원-이력서등록/관리</option>
			<option value="UFAQ-U2">개인회원-회원정보/아이디/비밀번호</option>
			<option value="UFAQ-U3">개인회원-입사지원/관리</option>
			<option value="UFAQ-U4">개인회원-채용정보 검색/관리</option>
			<option value="UFAQ-U5">개인회원-회원가입/탈퇴</option>
			<option value="UFAQ-U6">개인회원-추천/나의검색</option>
			<option value="UFAQ-U7">개인회원-계정 통합</option>
			<option value="UFAQ-U8">개인회원-기타회원 서비스</option>
			<option value="UFAQ-U9">개인회원-KoMate</option>
			<option value="CFAQ-C1">기업회원-기업채용정보 등록/관리</option>
			<option value="CFAQ-C2">기업회원-개인유료서비스/결제</option>
			<option value="CFAQ-C3">기업회원-개인인재풀</option>
			<option value="CFAQ-C4">기업회원-회원,기업정보/아이디/비밀번호</option>
			<option value="CFAQ-C5">기업회원-회원가입/탈퇴</option>
			<option value="CFAQ-C6">기업회원-지원자 관리/면접관리</option>
			<option value="CFAQ-C7">기업회원-인적성검사</option>
			<option value="CFAQ-C8">기업회원-채용홈페이지</option>
			<option value="CFAQ-C9">기업회원-계정통합</option>
			<option value="CFAQ-C10">기업회원-기타회원서비스</option>
			<option value="CFAQ-C11">기업회원-KoMate</option>
			 --%>
		</select>
		<select name="codeGroupNo">
			<option value="-1">--선택--</option>
		</select>
		<select name="memType">
			<option value="-1">--선택--</option>
		</select>
		<select name="comType">
			<option value="-1">--선택--</option>
		</select>
		<br>
		<input type="text" name="boardTitle" placeholder="제목">
		<input type="text" name="boardContent" placeholder="내용">
	  <button type="submit">등록</button>
	</form>
<script>
const aboardForm = document.querySelector("#aboardForm");
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
			
			aboardForm.boardTypeCode.appendChild(option);
		})
		
	})
})

fetch(`/ajax/admin/cmncodegroup/UFAQ`)
.then(resp => {
	resp.json()
	.then(rslt =>{
		console.log("그룹명이 뜨나? : ", rslt);
		/*
		rslt.cmnCodeList.forEach(vo => { 
			let option = document.createElement("option");
			option.value = vo.codeDetailNo;
			option.innerHTML = vo.codeName;
			
			aboardForm.codeGroupNo.appendChild(option);
		})*/

	})
})

fetch(`/ajax/code/cmncodegroup/UFAQ`)
.then(resp => {
	resp.json()
	.then(rslt =>{
		console.log("UFAQ 반복문 : ", rslt);
		
		rslt.cmnCodeList.forEach(vo => { 
			let option = document.createElement("option");
			option.value = vo.codeDetailNo;
			option.innerHTML = vo.codeName;
			
			aboardForm.memType.appendChild(option);
		})

	})
})

fetch(`/ajax/code/cmncodegroup/CFAQ`)
.then(resp => {
	resp.json()
	.then(rslt =>{
		console.log("CFAQ 반복문 : ", rslt);
		
		rslt.cmnCodeList.forEach(vo => { 
			let option = document.createElement("option");
			option.value = vo.codeDetailNo;
			option.innerHTML = vo.codeName;
			
			aboardForm.comType.appendChild(option);
		})

	})
})

</script>
</body>