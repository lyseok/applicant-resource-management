<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	 .card {
      border: 1px solid #e0e0e0;
      border-radius: 4px;
      padding: 12px;
      background-color: #fff;
      box-shadow: 0 1px 3px rgba(0,0,0,0.1);
      margin-bottom: 16px;
    }
    
    

</style>
<script src = "/js/company/recruitment/companyExam/companyExamDetail.js"></script>
</head>
<body>

	<div class = "exam-no" data-exam-id ="${examNo }">
		
		<div class = "exam-name">
			<button id="exam-edit-btn" data-exam-id="${examNo}" class="btn btn_violet_line">
	    		수정
	  		</button>
			<div class = "card exam-question"></div>
		</div>
	</div>



	
</body>
</html>