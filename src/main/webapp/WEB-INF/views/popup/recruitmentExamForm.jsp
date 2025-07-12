<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
    /* 좌측 문제, 우측 체크 상태 */
    .container {
      display: flex;
      justify-content: space-between;
    }
    .left-section {
      width: 60%;
    }
    .right-section {
      width: 35%;
      border-left: 2px solid #ddd;
      padding-left: 20px;
    }
    .question-item {
      margin-bottom: 20px;
    }
    .question-item h5 {
      margin-bottom: 10px;
    }
    .options label {
      display: block;
    }
  </style>
  
  <script src = "/js/member/recruitment/recruitmentExam/recruitmentExamForm.js"></script>
</head>
<body>
  <div class="container mt-5">
    <div class="left-section">
      <p class = "h3">시험문제</p>
      <div id="question-container"></div>
      <button id="back-btn" class="btn btn-primary" disabled>이전 문제</button>
      <button id="next-btn" class="btn btn-primary" disabled>다음 문제</button>
    </div>
    <div class="right-section">
      <p class = "h3">답안 표기란</p>
      <div id="status-container"></div>
    </div>
  </div>

</body>
</html>