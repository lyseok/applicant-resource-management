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
		width:100%;
	}
	.result-card .card-header{
		font-size:20px;
		font-weight:bold;
		background:#fff;
	}
	
	.result-card .card-body{		
		font-size:20px !important;
	}
	.score-display {
		font-size: 3rem;
		font-weight: bold;
		text-align: center;
		margin-top: 20px;
	}
	
	.score-value {
		color: var(--violet80);
		font-size: 3rem;
		font-weight: bold;
		letter-spacing:-2px;
	}
	.card-footer{
		background:#fff;
	}
	
</style>
<script src ="/js/member/recruitment/recruitmentExam/recruitmentExamResult.js"></script>
</head>
<body>
	<div class="card result-card">
    <div class="card-header">
      📝 시험 결과 확인
    </div>
    <div class="card-body d-flex align-items-center justify-content-center fs-16" id="result-container">
     	 
    </div>
    <div class="card-footer text-end">
      <button class="btn btn_gray_line w140 justify-content-center" onclick="window.close()">닫기</button>
    </div>
  </div>
</body>
</html>