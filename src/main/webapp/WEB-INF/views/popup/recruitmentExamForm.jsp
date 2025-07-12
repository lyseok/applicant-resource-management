<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
       .exam-container { display: flex; gap: 1rem; }
    .left-panel { flex: 1 1 60%; }
    .right-panel { flex: 0 0 35%; border-left: 1px solid #ccc; padding-left: 1rem; }
    .option-label { display: block; margin-bottom: .5rem; }
    .status-item { margin-bottom: .5rem; }
    .status-item {
      display: flex; align-items: center; margin-bottom: .5rem;
    }
    .status-item .qno {
      width: 2rem; text-align: right; margin-right: .5rem; font-weight: 500;
    }
    .status-item .circle {
      display: inline-flex; justify-content: center; align-items: center;
      width: 1.5rem; height: 1.5rem; margin-right: .25rem;
      border: 1px solid #6c757d; border-radius: 50%;
      font-size: .875rem; color: #6c757d; cursor: default;
    }
    .status-item .circle.selected {
      background-color: #6f42c1; border-color: #6f42c1; color: #fff;
    }
  </style>
  <script src = "/js/member/recruitment/recruitmentExam/recruitmentExamForm.js?v=${System.currentTimeMillis()}""></script>
</head>
<body>
 <div class="container my-4">
    <h2>시험 문제 </h2>
    <div class="exam-container">
      <!-- 왼쪽: 문제 + 보기 -->
      <div class="left-panel">
        <h4 id="q-title">로딩 중…</h4>
      	<div id="q-options"></div>
        <div class="mt-3">
          <button id="prevBtn" class="btn btn-secondary me-2" disabled>이전 문제</button>
          <button id="nextBtn" class="btn btn-primary" disabled>다음 문제</button>
          <button id="submitBtn" class="btn btn-success d-none">제출하기</button>
        </div>
      </div>
      <!-- 오른쪽: 전체 문제 답안 여부 -->
      <div class="right-panel">
        <h5>답안 표기란 </h5>
        <div id="status"></div>
      </div>
    </div>
  </div>
  


</body>
</html>