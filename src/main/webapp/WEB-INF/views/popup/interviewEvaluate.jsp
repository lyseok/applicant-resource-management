<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
  <title>면접자 평가</title>
  <style>
    body { background: #f8f9fa; }
    .eval-radio label { width: 100%; }
    .score-label { min-width: 90px; }
    .eval-radio input[type="radio"] { margin-right: 6px; }
    .evaluation-form { max-width: 520px; margin: 40px auto 0; background: #fff; border-radius: 16px; box-shadow: 0 2px 10px #0001; padding: 2rem; }
  </style>
  <script defer src="/js/company/recruitment/interview/interviewEvaluate.js"></script>
</head>

<body>
  <div class="container evaluation-form">
    <h3 class="mb-4 fw-bold" style="color:#8d4dff">면접자 평가</h3>
    <!-- 지원자 선택 -->
    <!-- 지원자 선택 -->
    <div class="mb-3">
      <label class="form-label fw-bold">지원자 선택</label>
      <select id="applicantSelect" class="form-select"></select>
      <a id="resumeLink" class="btn btn-link mt-2 p-0" target="_blank" style="font-size:0.98rem;">이력서 보기</a>
    </div>

    <!-- 평가 폼 -->
    <div class="mb-4">
      <label class="form-label fw-bold">평가 항목</label>
      <form id="evaluationForm"></form>
    </div>
    <div class="d-grid">
      <button class="btn btn-success" id="submitEvaluationBtn">평가 완료</button>
    </div>
  </div>
</body>
