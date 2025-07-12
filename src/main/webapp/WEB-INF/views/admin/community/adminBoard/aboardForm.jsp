<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 상세 입력</title>
	<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
</head>

<body>
  <h1><sec:authentication property="principal.realUser.userId" var="userId"/></h1>
	<form id="aboardForm">
		<input type="hidden" name="userId" value="${userId}" placeholder="사용자 ID"><br>
		<label>게시판 유형 코드</label>
		<select name="boardTypeCode">
			<option value="-1">--선택--</option>
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
		</select>
		<br>
		<input type="text" name="boardTitle" placeholder="제목">
		<input type="text" name="boardContent" placeholder="내용">
	  <button type="submit">전송</button>
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
</script>
</body>