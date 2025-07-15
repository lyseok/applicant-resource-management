<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
		background: #f8f9fa;
	}
	
	.result-card {
		max-width: 500px;
		margin: 2rem auto;
	}
	
	.score-display {
		font-size: 3rem;
		font-weight: bold;
		text-align: center;
		margin-top: 20px;
	}
	
	.score-value {
		color: #28a745;
		font-size: 3rem;
		font-weight: bold;
	}
</style>
<script src ="/js/member/recruitment/recruitmentExam/recruitmentExamResult.js"></script>
</head>
<body>
	<div class="card result-card">
    <div class="card-header text-center">
      📝 시험 결과
    </div>
    <div class="card-body" id="result-container">
     	 
    </div>
    <div class="card-footer text-center">
      <button class="btn btn-secondary" onclick="window.close()">닫기</button>
    </div>
  </div>
</body>
</html>