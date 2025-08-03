<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>회사 시험 ${empty examNo ? '등록' : '수정'}</title>
      <style>
        .text-danger {
          display: block;
          /* 블록 형태로 만들어 에러 메시지를 아래로 표시 */
          font-size: 12px;
          /* 작은 크기로 설정 */
          color: red;
          /* 빨간색으로 표시 */
          margin-top: 5px;
          /* input과 메시지 사이에 여백 추가 */
        }

        .btn {}
      </style>
      <script defer type="module" src="/js/company/recruitment/companyExam/createCompanyExam.js"></script>
    </head>

    <body>
      <div class="container py-4" id="container" data-exam-id="${examNo}">
        <h1 class="mb-4 text-center" id="data-btn">
          ${empty examNo ? '시험 생성' : '시험 수정'}
        </h1>
        <form id="examForm">

          <div class="mb-3">
            <label for="comExamName" class="form-label">시험명</label>
            <input type="text" class="form-control" id="comExamName" name="comExamName" placeholder="시험명을 입력하세요" />

          </div>

          <div id="questionContainer"></div>

          <div class="mb-3">
            <button type="button" id="addQuestionBtn" class="btn btn_violet">
              + 문제 추가
            </button>
          </div>

          <div class="d-flex gap-2">
            <button type="button" id="submitAllBtn"
              class="btn ${empty examNo ? 'btn btn_violet_line' : 'btn btn_violet_line'}">
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