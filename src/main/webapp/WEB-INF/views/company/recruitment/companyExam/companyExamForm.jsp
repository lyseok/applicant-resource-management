<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src = "/js/company/recruitment/companyExam/createCompanyExam.js"></script>
</head>
<body>
	<div>
		<h1>시험 생성 페이지</h1>
	</div>
	
	<div class = "card">
		<label>시험명 : <input type = "text" id = "comExamName" placeholder = "시험명을 입력하세요"/></label>
	</div>
	
	<div id = "questionContainer"></div>
	<button class = "btn btn_violet" id = "addQuestionBtn"> + 문항 추가</button>
	
	<div>
		<button class = "btn btn_violet"  id = "submitAllBtn" >시험 생성</button>
	</div>
</body>
</html>