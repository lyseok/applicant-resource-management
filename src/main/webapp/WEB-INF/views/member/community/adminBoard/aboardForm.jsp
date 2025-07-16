<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 고객센터 | 게시글 등록</title>
</head>

<body>

<p class="h4">개인회원 문의</p><br>

  <h1><sec:authentication property="principal.realUser.userId" var="userId"/></h1>
  <h1><sec:authentication property="principal.realUser.memEmail" var="memEmail" /></h1>
  <h1><sec:authentication property="principal.realUser.userRole" var="userRole" /></h1>
	<form id="m_aboardForm">
	<fieldset>
	<legend>이메일 문의 입력 폼</legend>
	<p>평일 09시부터 17시까지 문의하신 내용은 당일 답변해드리고 있으며,<br>
	17시 이후에 문의하신 내용은 다음날에 답변, 주말에 문의하신 내용은 그 다음주 월요일에 답변해 드립니다.
	</p>
		<ul>
			<li>
				<div class="col-md-6">
					<label class="form-label">아이디</label>
					<input type="text" class="form-label" name="userId" value="${userId}" readonly>
				    <label class="form-label">이메일</label>
				    <input type="email" class="form-control" name="memEmail" value="${memEmail}" readonly>
				    <label class="form-label">회원</label>
				    <input type="text" class="form-control" name="userRole" value="${userRole}" readonly>
					<input type="hidden" name="boardTypeCode" value="BRDD-001">
				</div>
				<div class="col-md-4">
				  <label class="form-label">문의종류</label>
				  <label class="visually-hidden" for="">Preference</label>
				  <select class="form-select" name="boardTitle">
				   	<option selected>--선택--</option>
				   	<option>최저임금 위반/불량 기업/공고 신고</option>
					<option>오류 신고</option>
					<option>이력서 문의</option>
					<option>입사지원 문의</option>
					<option>검색 문의</option>
					<option>회원가입/탈퇴/ID/PW</option>
					<option>공고 문의</option>
					<option>지원자관리 문의</option>
					<option>이메일/알림 문의</option>
					<option>결제/유료 상품 문의</option>
					<option>결체취소 신청</option>
					<option>연봉정보 수정요청</option>
					<option>인적성검사 문의</option>
					<option>기업정보</option>
					<option>제안사항</option>
					<option>멘토링매치</option>
					<option>장기 미접속 차단 해제 요청</option>
					<option>해외 로그인 차단 해제 요청</option>
					<option>인적성검사 결제/환불 문의</option>
					<option>기타</option>
				  </select>
				  <label class="form-label">문의내용</label>
				  <textarea name="boardContent" rows="6" cols="60"></textarea>
				</div>
			</li>
		</ul>
	</fieldset>
	  <div class="col-12">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" required>
	      <label class="form-check-label" for="gridCheck">
	        개인정보 제공에 동의합니다
	      </label>
	    </div>
	  </div>
	  <button type="submit">등록</button>
	</form>
		
<script>
const aboardForm = document.querySelector("#m_aboardForm");
aboardForm.onsubmit = function(){
	event.preventDefault();
	let adminBoard = {
		userId : aboardForm.userId.value,
		boardTypeCode : aboardForm.boardTypeCode.value,
		boardTitle : aboardForm.boardTitle.value,
		boardContent : aboardForm.boardContent.value
	}
	fetch(`/ajax/member/board/admin_board/\${adminBoard.boardTypeCode}`, {
		method : "post",
		headers : {
			"Content-Type" : "application/json"
		},
		body : JSON.stringify(adminBoard)
	}).then(resp => {
	console.log("타입이 안 나오낭? : ", adminBoard.boardTypeCode);
		resp.json().then(rslt=> {
			console.log("글자", rslt.ok);
		})
	})
}
</script>		
</body>