<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
  <meta charset="UTF-8">
  <title>이력서 등록</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
  <script type="text/javascript" src="/js/member/resume/resumeForm.js" defer></script>
</head>
<body>
  <div class="resume-wrap">
   
    <!-- Header -->
    <div class="resume-header">
      <div class="profile-img">${memberInfo.memImg }</div>
      <div class="header-info">
      	<div class="d-flex align-items-center">
	        <span class="name lh1">${memberInfo.memName }</span>
	        <span class="age lh1">${memberInfo.memBir }</span>
        </div>
        <div class="contact d-flex gap-4">
        	<span class="d-flex align-items-center gap-1"><i class='bx  bx-envelope-alt'  ></i> ${memberInfo.memEmail }</span>
        	<span class="d-flex align-items-center gap-1"><i class='bx  bx-mobile'  ></i> ${memberInfo.memTel }</span>
        </div>
        <div class="address d-flex align-items-center gap-1"><i class='bx  bx-home-alt'  ></i> ${memberInfo.memAdd1 } ${memberInfo.memAdd2 }</div>
      </div>
    </div>


	<form action="post" id="resume_form">
		
	    <!-- 학력 -->
	    <!-- 학력 (EDUCATION) 입력폼 -->
		<div class="section" id="section-educationList">
		  <div class="section-title">
		    <h6>학력<span class="must">필수</span></h6>
		    <button class="add-btn" type="button" id="btn-educationList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">졸업학교 정보를 입력해 주세요</div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	    <!-- 경력 (CAREER) 입력폼 -->
		<div class="section" id="section-careerList">
		  <div class="section-title">
		    <h6>경력</h6>
		    <button class="add-btn" type="button" id="btn-careerList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    경력사항 또는 인턴, 현장실습 등 실제로 해본 경험이 있다면 자세히 작성해주세요!
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    <!-- 스킬 -->
	    <!-- 보유기술 (MY_SKILL) 입력폼 -->
		<div class="section" id="section-mySkillList">
		  <div class="section-title">
		    <h6>보유기술</h6>
		    <button class="add-btn" type="button" id="btn-mySkillList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    주요 보유기술, 툴, 언어 등을 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    <!-- 보유경험 (MY_EXPERIENCE) 입력폼 -->
		<div class="section" id="section-myExperienceList">
		  <div class="section-title">
		    <h6>보유경험</h6>
		    <button class="add-btn" type="button" id="btn-myExperienceList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    대외활동, 인턴, 교육 등 주요 경험을 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	    
	    <!-- 활동 - 고용지원 (SUPPORT) 입력폼 -->
		<div class="section" id="section-supportList">
		  <div class="section-title">
		    <h6>고용지원 정보</h6>
		    <button class="add-btn" type="button" id="btn-supportList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    장애 등 고용지원 대상 정보를 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    
	    <!-- 보유자격 (MY_LICENSE) 입력폼 -->
		<div class="section" id="section-myLicenseList">
		  <div class="section-title">
		    <h6>보유자격</h6>
		    <button class="add-btn" type="button" id="btn-myLicenseList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    보유한 자격증 정보를 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	    
	    <!-- 어학 (LANGUAGE_SKILL) 입력폼 -->
		<div class="section" id="section-languageSkillList">
		  <div class="section-title">
		    <h6>어학</h6>
		    <button class="add-btn" type="button" id="btn-languageSkillList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    어학 자격증, 시험 정보 등을 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	    
	    <!-- 수상 (AWARD) 입력폼 -->
		<div class="section" id="section-awardList">
		  <div class="section-title">
		    <h6>수상</h6>
		    <button class="add-btn" type="button" id="btn-awardList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    주요 수상 이력을 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    <!-- 포트폴리오 (PORTFOLIO) 입력폼 -->
		<div class="section" id="section-portfolioList">
		  <div class="section-title">
		    <h6>포트폴리오</h6>
		    <button class="add-btn" type="button" id="btn-portfolioList">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    주요 프로젝트, 작업 포트폴리오 정보를 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    <!-- 자기소개서 (INTRODUCTION) 입력폼 -->
		<div class="section" id="section-introduction">
		  <div class="section-title">
		    <h6>자기소개서</h6>
		    <button class="add-btn" type="button" id="btn-introduction">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    자기소개서를 작성해주세요.
		  </div>
		  <div class="section-content">
		    여기 리스트 불러오는거 or 직접입력하는 거 둘중 하나 선택할 수 있게 작업, 직접 입력하는 건 문항을 내용에 입력하게끔 문항 없앰 (단건등록이니까)
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
		</div>
	
	
	    <!-- 병역 (MILITARY) 입력폼 -->
		<div class="section" id="section-militaryList">
		  <div class="section-title">
		    <h6>병역</h6>
		    <button class="add-btn" type="button" id="btn-military">+ 추가</button>
		  </div>
		  <div class="section-content" style="color: #b8bfc9;">
		    병역 정보를 입력해주세요.
		  </div>
		  <div class="formContainer"></div>
	      <div class="listContainer"></div>
	    </div>
	    
	  	<div class="" id="form-resume">		  			  	
			<input type="hidden" name="userId" value="${memberInfo.userId }">
			<input type="hidden" name="userName" value="${memberInfo.memName }">
			<input type="hidden" name="birth" value="${memberInfo.memBir }">
			<input type="hidden" name="email" value="${memberInfo.memEmail }">
			<input type="hidden" name="tel" value="${memberInfo.memTel }">
			<input type="hidden" name="address" value="${memberInfo.memAdd1 }">
			<input type="hidden" name="address" value="${memberInfo.memAdd2 }" >
			<input type="hidden" name="resumeSubmitYn" value="N" >
		    <div class="resume_ft">
		    	<div class="inner">
		    		<div class="section-form-row">
				        <label>자기소개서 제목</label>
				        <input type="text" name="resumeName" value="${memberInfo.memName }님의 이력서" class="h50">
				    </div>
			    	
					<div class="section-form-btns">
				      <button type="button" class="btn btn_red_line" onclick="toggleForm('military',true)">취소</button>
				      <button type="submit" class="btn btn_violet">등록</button>
				    </div>
			    </div>
		    </div>
		</div>
	</form>
  </div>
	
	
	<!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteResumeList" tabindex="-1" aria-labelledby="deleteResumeModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 이력서 입력 정보 삭제
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
				<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
					해당 입력 정보를 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
				</p>
				<p class="text-secondary mb-0" style="font-size:1.08rem;">
						삭제된 데이터는 복구할 수 없습니다.<br>
						실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4">삭제하기</button>
				</div>
			</div>
		</div>
	</div>
</body>