<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>기업 정보 관리</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
  <link rel = "stylesheet" href="/css/company/common/companyManagement.css">
  <script src = "/js/company/common/companyManagement/companyManagementDetail.js"></script>
 
 <style type="text/css">
 
.company-info-wrap {
  max-width: 900px;
  margin: 40px auto;
  background: var(--color-bg);
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
  overflow: hidden;
  font-family: 'Noto Sans KR', sans-serif;
}

/* ──────────────────────────────────────────────────────────
   Header
─────────────────────────────────────────────────────────── */
.company-header {
  display: flex;
  align-items: center;
  gap: 24px;
  padding: 32px;
  background: var(--color-bg-alt);
}
.profile-img {
  flex-shrink: 0;
  width: 120px;
  height: 120px;
  border-radius: 10%;
  overflow: hidden;
  border: 2px solid gray;
  background: var(--color-bg);
}
.profile-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.header-info .name {
  display: block;
  font-size: 1.75rem;
  font-weight: 700;
  color: var(--color-secondary);
}
.header-info .contact {
  margin-top: 8px;
  font-size: 0.95rem;
  color: #667085;
}
.header-info .contact span {
  display: inline-flex;
  align-items: center;
  gap: 6px;
}
.header-info .contact i {
  font-size: 1.1rem;
  color: var(--color-primary);
}
.header-info .address {
  margin-top: 8px;
}
.header-info .address a {
  font-size: 0.95rem;
  color: var(--color-primary);
  text-decoration: none;
  transition: color .2s;
}
.header-info .address a:hover {
  color: #3248c3;
}

/* ──────────────────────────────────────────────────────────
   Sections (제목 위 · 내용 아래)
─────────────────────────────────────────────────────────── */
.section {
  display: flex;
  flex-direction: column;
  padding: 20px 32px;
  border-bottom: 1px solid var(--color-border);
  background: var(--color-bg);
  transition: background .3s;
}
.section:nth-child(even) {
  background: var(--color-bg-alt);
}
.section:hover {
  background: rgba(94,114,228,0.05);
}
.section:last-child {
  border-bottom: none;
}

/* 제목 + 언더라인 포인트 */
.section-title {
  font-weight: 600;
  font-size: 1rem;
  color: var(--color-secondary);
  position: relative;
  padding-bottom: 6px;
  margin-bottom: 12px;
}
.section-title::after {
  content: '';
  position: absolute;
  bottom: 0; left: 0;
  width: 40px; height: 3px;
  background: var(--color-primary);
  border-radius: 2px;
}

/* 본문 텍스트 */
.section-content {
  color: var(--color-text);
  font-size: 0.95rem;
  line-height: 1.6;
  word-break: keep-all;
}

/* ──────────────────────────────────────────────────────────
   Button 영역
─────────────────────────────────────────────────────────── */
.d-flex.gap-1 {
  display: flex;
  justify-content: flex-end;
  padding: 24px 32px;
  background: var(--color-bg-alt);
}
.btn_violet {
  padding: 10px 24px;
  font-size: 0.95rem;
  font-weight: 600;
  color: var(--color-bg);
  background-color: var(--color-primary);
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background .2s, transform .1s;
}
.btn_violet:hover {
  background-color: #3248c3;
  transform: translateY(-1px);
}

/* ──────────────────────────────────────────────────────────
   Responsive
─────────────────────────────────────────────────────────── */
@media (max-width: 600px) {
  .company-header {
    flex-direction: column;
    text-align: center;
  }
  .section {
    padding: 16px 24px;
  }
  .section-title {
    font-size: 0.95rem;
  }
}
 </style>
 
</head>
<body>
<div class="company-info-wrap">
    <!-- Header Section -->
    <div class="company-header">
        <div class="profile-img">
            <img id = "com_logo">
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
  
    <div class="d-flex gap-1">
        <button type="button" class="btn btn_violet" id="edit-btn">수정</button>
    </div>
</div>
</body>
</html>
