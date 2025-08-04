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
 

/* ──────────────────────────────────────────────────────────
   Header
─────────────────────────────────────────────────────────── */
.company-header {
  display: flex;
  align-items: center;
  gap: 24px;
  margin-bottom:40px;
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
  gap:6px;
  color:#667085;
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
.section-wrap{
	gap:20px;
}
.section {
  display: flex;
  width:calc((100% - 30px) / 2);
  border-bottom: 1px solid var(--color-border);
  background: var(--color-bg);
  margin-bottom:0;
}

/* 제목 + 언더라인 포인트 */
.section-title {
  font-weight: 600;
  line-height:1;
  font-size: 1rem;
  position: relative;
  padding-bottom: 0;
  margin-bottom: 0;
  border-bottom:0;
  width:100px;
  color: var(--gray100);
  font-size: 14px;
}

/* 본문 텍스트 */
.section-content {
  color: var(--gray120);
  font-size: 0.95rem;
  line-height: 1;
  word-break: keep-all;
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






/* 예솔 추가 */
.company_summary{
	gap:80px;
}
.company_summary_item{}
.company_summary_item span{
	font-size:54px;
	font-weight:3
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
            	<i class='bx bx-home-alt-3'></i>
              <a href="#">홈페이지 링크</a>
            </div>
        </div>
    </div>

		<div class="company_summary d-flex rounded border text-center py-4 mb-5 justify-content-center">
	    <div class="company_summary_item">
	    	<span class="material-symbols-outlined">history</span> 
       <div class="fs-16 fw-bold" id="com_create_year"></div>
       <div class="fs-14 opacity-75" id="com_create_year_txt">설립년도</div>
	   </div>
   
	    <div class="company_summary_item">
				<span class="material-symbols-outlined">corporate_fare</span>
        <div class="fs-16 fw-bold" id="com_size"></div>
        <div class="fs-14 opacity-75">기업 규모</div>
	    </div>
	   
	   <div class="company_summary_item">
	     <span class="material-symbols-outlined">group</span> 
       <div class="fs-16 fw-bold" id="com_mem" >직원수 데이터</div>
       <div class="fs-14 opacity-75">직원수</div>
	   </div>
	   
	   <div class="company_summary_item">
	     <span class="material-symbols-outlined">leaderboard</span> 
       <div class="fs-16 fw-bold" id="com_capital"></div>
       <div class="fs-14 opacity-75">기업 자본금</div>
	   </div>
   </div>
   

    <!-- 기업 정보 섹션들 -->
    <div class="d-flex flex-wrap section-wrap mb-5">
    
	     <div class="section">
	        <div class="section-title">대표자 이름</div>
	        <div class="section-content" id="ceo_name"></div>
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
	        <div class="section-title">기업 주소</div>
	        <div class="section-content" id="com_addr"></div>
	    </div>
	    <div class="section">
	        <div class="section-title">기업 주요 사업</div>
	        <div class="section-content" id="com_main_biz"></div>
	    </div>
    </div>
    
    
    <div class="section w100p flex-column mb-5">
        <div class="section-title fs-5 text-dark fw-bold mb-3">기업설명</div>
        <div class="section-content lh1-6 keep-all" id="com_info">기업설명 데이터</div>
    </div>
	    
  
    <div class="text-end">
        <button type="button" class="btn btn_violet w140 justify-content-center" id="edit_btn">수정</button>
    </div>
</div>
</body>
</html>
