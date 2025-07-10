<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회사 시험 ${empty examNo ? '등록' : '수정'}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet"/>
  <script src="/js/formToJson.js"></script> <!-- 폼→JSON 변환 라이브러리 -->
  <script defer src="/js/company/recruitment/companyExam/createCompanyExam2.js"></script>
</head>
<body>
  <div class="container py-4" id="container" data-exam-id="${examNo}">
    <h1 class="mb-4 text-center">
      ${empty examNo ? '시험 생성' : '시험 수정'}
    </h1>
    <form id="examForm">
      <!-- 시험명 -->
      <div class="mb-3">
        <label for="comExamName" class="form-label">시험명</label>
        <input type="text"
               class="form-control"
               id="comExamName"
               name="comExamName"
               placeholder="시험명을 입력하세요"/>
        <!-- 에러 메시지 삽입 위치 -->
      </div>

      <!-- 문제 컨테이너 (JS가 동적으로 <div.question-card>를 추가) -->
      <div id="questionContainer"></div>

      <div class="mb-3">
        <button type="button" id="addQuestionBtn" class="btn btn-secondary">
          + 문제 추가
        </button>
      </div>

      <div class="d-flex gap-2">
        <button type="button"
                id="submitAllBtn"
                class="btn ${empty examNo ? 'btn-success' : 'btn-primary'}">
          ${empty examNo ? '시험 생성' : '시험 수정'}
        </button>
        <button type="button" id="exitBtn" class="btn btn-outline-secondary">
          목록
        </button>
      </div>
    </form>
  </div>
</body>
</html>
