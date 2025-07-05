<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<meta charset="UTF-8">
	<title>자소서 수정</title>
	<link rel="stylesheet" href="/css/member/resume/intoruction.css" >
</head>
<body>
	<p class="h1 mb-3 fw-bold">자소서 수정</p>
	
	<c:if test="${not empty introdDetail}">
		<div class="introduct_form_wrap">
			<form class="introduct_form">
				<div class="mb-3">
				  <label for="formGroupExampleInput" class="form-label">자소서 명</label>
				  <input type="text" class="form-control" id="formGroupExampleInput" placeholder="자소서 명을 입력해주세요." value="${introdDetail.introductionName}">
				</div>
				<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">자소서 내용</label>
				  <textarea class="form-control" id="exampleFormControlTextarea1" rows="10">${introdDetail.introductionContent}"</textarea>
				</div>
				<div class="text-end">
					<button class="btn btn_violet">전송</button>
				</div>
			</form>
		</div>
	</c:if>
	
</body>