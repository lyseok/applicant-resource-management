<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>기업 정보 관리</title>

  <style type="text/css">
    /* 기본적인 인터페이스 스타일 */
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 20px;
    }

    .company-info-wrap {
      padding: 30px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* 섹션 제목 */
    .company-info-wrap .section-title {
      display: flex;
      justify-content: space-between;
      font-size: 18px;
      font-weight: 700;
      color: #2d3554;
      margin-bottom: 14px;
      padding-bottom: 10px;
      border-bottom: 1px solid #333;
    }

    .company-info-wrap .section-title h6 {
      font-weight: bold;
    }

    /* 섹션 내용 */
    .company-info-wrap .section-content {
      font-size: 15px;
      color: #434b62;
      min-height: 22px;
    }

    /* 섹션 폼 */
    .company-info-wrap .section-form-wrap {
      display: flex;
      flex-wrap: wrap;  /* 두 개씩 나란히 배치 */
      gap: 20px;
      background: #fff;
      border: 1.5px solid #dbdbdb;
      border-radius: 12px;
      padding: 34px 30px 22px 30px;
      position: relative;
    }

    /* 각 입력 필드 */
    .company-info-wrap .section-form-row {
      display: flex;
      flex-direction: column;
      gap: 8px;
      width: calc(50% - 10px);  /* 두 개씩 나란히 배치 */
    }

    /* 기업명, 기업설명, 업종 코드, 업종 설명 필드 수직 배치 */
    .company-info-wrap .section-form-row.vertical {
      width: 100%;  /* 100%로 너비를 확장하여 수직으로 배치 */
    }

    .company-info-wrap .section-form-row label {
      font-size: 15px;
      color: #395081;
      font-weight: bold;
    }

    .company-info-wrap .section-form-row input, 
    .company-info-wrap .section-form-row select, 
    .company-info-wrap .section-form-row textarea {
      width: 100%; /* 입력 필드 전체 너비 */
      padding: 10px 15px;
      border-radius: 7px;
      border: 1px solid #dbdbdb;
      font-size: 15px;
      background: #fff;
      outline: none;
      transition: border 0.2s;
    }

    .company-info-wrap .section-form-row textarea {
      min-height: 80px;
    }


    /* 각 섹션 간의 간격을 위한 padding 추가 */
    .company-info-wrap .section {
      padding-bottom: 40px;
    }

    /* 작은 화면에서 한 줄에 하나씩 입력 필드 표시 */
    @media (max-width: 768px) {
      .company-info-wrap .section-form-row {
        width: 100%; /* 화면이 작을 때 한 줄로 표시 */
      }
    }

  </style>
</head>
<body>
  <p class="h2">기업 정보 관리</p>

  <!-- form 태그로 전체 폼을 감싸기 -->
  <form id="companyForm">
    <div class="company-info-wrap">
      <!-- 기업 기본 정보 (Basic Information) -->
      <div class="section" id="section-company-basic">
        <div class="section-title">
          <h6>기업 기본 정보</h6>
        </div>
        <div class="section-content" style="color: #b8bfc9;">기업에 대한 기본 정보를 입력해주세요.</div>
        <div class="section-form-wrap">
          <!-- 기업명 -->
          <div class="section-form-row vertical">
            <label class="required">기업명</label>
            <input type="text" name="company_name" id="company_name" required disabled>
          </div>

          <!-- 기업설명 -->
          <div class="section-form-row vertical">
            <label class="required">기업설명</label>
            <textarea name="company_info" id="company_info" required disabled></textarea>
          </div>

          <!-- 대표번호 -->
          <div class="section-form-row">
            <label class="required">대표번호</label>
            <input type="text" name="company_phone" id="company_phone" required disabled>
          </div>

          <!-- 이메일 -->
          <div class="section-form-row">
            <label class="required">이메일</label>
            <input type="email" name="company_email" id="company_email" required disabled>
          </div>

          <!-- 홈페이지 URL -->
          <div class="section-form-row">
            <label class="required">홈페이지 URL</label>
            <input type="url" name="company_url" id="company_url" disabled>
          </div>

          <!-- 설립년도 -->
          <div class="section-form-row">
            <label class="required">설립년도</label>
            <input type="date" name="company_year" id="company_year" required disabled>
          </div>

          <!-- 직원수 -->
          <div class="section-form-row">
            <label class="required">직원수</label>
            <input type="number" name="company_employee_count" id="company_employee_count" required disabled>
          </div>

          <!-- 업종 코드 -->
          <div class="section-form-row">
            <label class="required">업종 코드</label>
            <input type="text" name="industry_code" id="industry_code" required disabled>
          </div>
        </div>
      </div>

      <!-- 버튼들 -->
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" id="cancelButton" style="display: none;">취소</button>
        <button type="button" class="btn btn_violet" id="editButton">수정</button>
        <button type="submit" class="btn btn_violet" id="saveButton" style="display: none;">저장</button>
      </div>
    </div>
  </form> <!-- form 태그 닫기 -->

 
</body>
</html>
