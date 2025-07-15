<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>기업 정보 관리</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
  <script src = "/js/company/common/companyManagement/companyManagementDetail.js"></script>
  <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
  <link href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" rel="stylesheet">

 
</head>
<body>
	<div class="company-info-wrap">
		<!-- Header Section -->
		<div class="company-header">
			<div class="profile-img">
				<!-- 여기에 기업 로고 이미지 삽입 -->
			</div>
			<div class="header-info">
				<div class="d-flex align-items-center">
					<span class="name" id="com_name">기업명 데이터</span> <span class="age"
						id="com_create_year">설립년도</span>
				</div>
				<div class="contact d-flex gap-4">
					<span><i class='bx bx-envelope-alt'></i> <span
						id="com_email">이메일 데이터</span></span> <span><i class='bx bx-mobile'></i>
						<span id="com_num">대표번호 데이터</span></span>
				</div>
				<div class="address d-flex" id="com_url">
					<a href="#">홈페이지 링크</a>
				</div>
			</div>
		</div>

		<!-- 기업 정보 섹션들 -->
		<div class="section">
			<div class="section-title">기업설명</div>
			<div class="section-content" id="com_info">기업설명 데이터</div>
		</div>

		<div class="section">
			<div class="section-title">직원수</div>
			<div class="section-content" id="com_mem">직원수 데이터</div>
		</div>

		<div class="section">
			<div class="section-title">구독여부</div>
			<div class="section-content" id="com_payment">구독여부 데이터</div>
		</div>

		<div class="section">
			<div class="section-title">업종 코드</div>
			<div class="section-content" id="industry_type">업종 코드 데이터</div>
		</div>

		<!-- 수정 버튼 -->
		<div class="d-flex gap-1">
			<button type="button" class="btn btn_violet_line" id="edit-btn"
				onclick="goToEditForm()">수정</button>
		</div>
	</div>
	
	
	
  <!-- form 태그로 전체 폼을 감싸기 -->
  <form id="companyForm" style="display: none;">
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
        <button type="button" class="btn btn_red_line" id="cancel-btn" style="display: none;">취소</button>
        <button type="submit" class="btn btn_violet" id="save-btn" style="display: none;">저장</button>
      </div>
    </div>
  </form> <!-- form 태그 닫기 -->


</body>
</html>
