<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>기업 정보 관리</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
  <link rel = "stylesheet" href="/css/company/common/companyManagement.css">
  <script src = "/js/company/common/companyManagement/companyManagementDetail.js"></script>
 

 
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
                <span class="name" id="com_name" >기업명 데이터</span>
              <!--   <span class="age" id="com_create_year">설립년도</span> -->
            </div>
            <div class="contact d-flex gap-4">
                <span><i class='bx bx-envelope-alt'></i> <span id="com_email" >이메일 데이터</span></span>
                <span><i class='bx bx-mobile'></i> <span id="com_num">대표번호 데이터</span></span>
            </div>
            <div class="address d-flex" id="com_url">
                <a href="#">홈페이지 링크</a>
            </div>
        </div>
    </div>

    <!-- 기업 정보 섹션들 -->
    
     <div class="section">
        <div class="section-title">대표자 이름</div>
        <div class="section-content" id="ceo_name"></div>
    </div>
   
    
    <div class="section">
        <div class="section-title">기업설명</div>
        <div class="section-content" id="com_info">기업설명 데이터</div>
    </div>
    
     <div class="section">
        <div class="section-title">설립년도</div>
        <div class="section-content" id="com_create_year"></div>
    </div>

    <div class="section">
        <div class="section-title">직원수</div>
        <div class="section-content" id="com_mem" >직원수 데이터</div>
    </div>
    
    <div class="section">
        <div class="section-title">4대보험</div>
        <div class="section-content" id="insurance_Yn"></div>
    </div>

    <div class="section">
        <div class="section-title">구독여부</div>
        <div class="section-content" id="com_payment">구독여부 데이터</div>
    </div>

    <div class="section">
        <div class="section-title">업종</div>
        <div class="section-content" id="industry_type">업종 코드 데이터</div>
    </div>
    
	<div class="section">
        <div class="section-title">기업 형태</div>
        <div class="section-content" id="com_type"></div>
    </div>
    
    <div class="section">
        <div class="section-title">기업 규모</div>
        <div class="section-content" id="com_size"></div>
    </div>
    <div class="section">
        <div class="section-title">기업 주소</div>
        <div class="section-content" id="com_addr"></div>
    </div>
    <div class="section">
        <div class="section-title">기업 자본금</div>
        <div class="section-content" id="com_capital"></div>
    </div>
    <div class="section">
        <div class="section-title">기업 주요 사업</div>
        <div class="section-content" id="com_main_biz"></div>
    </div>
    <!-- 수정 버튼 -->
    <div class="d-flex gap-1">
        <button type="button" class="btn btn_violet" id="edit-btn">수정</button>
    </div>
</div>




</body>
</html>
