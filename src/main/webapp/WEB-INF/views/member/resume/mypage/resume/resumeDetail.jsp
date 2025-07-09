<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
  <meta charset="UTF-8">
  <title>이력서 상세페이지</title>
  <link rel="stylesheet" href="/css/member/resume/resume.css" >
</head>
<body>
<h1 class="h1">${resumeList }</h1>
  <div class="resume-main-wrap">
    <!-- Header -->
    <div class="resume-header">
      <div class="profile-img">🙍‍♂️</div>
      <div class="header-info">
        <span class="name">${resumeList.userName }</span>
        <span class="age">${resumeList.birth } (28세)</span>
        <div class="contact">${resumeList.birth } /  ${resumeList.tel }</div>
        <div class="address">${resumeList.address } </div>
        <c:if test="${not empty resumeList.resumeSubmitYn}">
        	<div class="info-badge">대표 이력서 번호 ${resumeList.resumeNo }</div>
        </c:if>
      </div>
    </div>


    <!-- 나의 스킬 - 기술 -->
    <c:if test="${not empty resumeList.mySkillList}">
	    <div class="section">
	      <div class="section-title">나의 스킬</div>
		    <div class="myskill-list">
		    <c:forEach items="${resumeList.mySkillList }" var="skill">
		      <div class="myskill-item">${skill.mySkillName }</div>
		      </c:forEach>
		    </div>
	   	</div>
	</c:if>


    <!-- 학력 -->
    <c:if test="${not empty resumeList.educationList}">
	    <div class="section">
	      <div class="section-title">학력</div>
		    <c:forEach items="${resumeList.educationList }" var="education">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">학교명</span>
		          <span class="list-content"><b>${education.schoolName }</b></span>
		        </li>
		        <li>
		          <span class="list-label">졸업구분</span>
		          <span class="list-content">${education.graduateYn }</span>
		        </li>
    			<c:if test="${not empty education.specialtyList}">
			        <c:forEach items="${education.specialtyList }" var="specialty">
				        <li>
				          <span class="list-label">전공</span>
				          <span class="list-content">${specialty.mainMajor } / ${specialty.subMajorCode }</span>
				        </li>
				    </c:forEach>
				</c:if>
		      </ul>
		    </c:forEach>
	    </div>
	</c:if>

    

    <!-- 경력 -->
    <c:if test="${not empty resumeList.careerList}">
	    <div class="section">
	      <div class="section-title">경력</div>
	      <c:forEach items="${resumeList.careerList }" var="career">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">기간</span>
		          <span class="list-content">${career.startWorkDate } ~ ${career.retireDate }</span>
		        </li>
		        <li>
		          <span class="list-label">회사명</span>
		          <span class="list-content">${career.company.comName }</span>
		        </li>
		        <li>
		          <span class="list-label">부서/직무</span>
		          <span class="list-content">${career.department } / ${career.responsibility }</span>
		        </li>
		        <li>
		          <span class="list-label">경력기간</span>
		          <span class="list-content">${career.careerYearName }년</span>
		        </li>
		      </ul>
	      </c:forEach>
	    </div>
	</c:if>

    

    <!-- 경험 -->
    <c:if test="${not empty resumeList.myExperienceList}">
	    <div class="section">
	      <div class="section-title">경험</div>
		      <c:forEach items="${resumeList.myExperienceList }" var="exp">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">기간</span>
		          <span class="list-content">${exp.expStartDate } ~ ${exp.expEndDate }</span>
		        </li>
		        <li>
		          <span class="list-label">활동명</span>
		          <span class="list-content">${exp.expName }</span>
		        </li>
		        <!-- <li>
		          <span class="list-label">설명</span>
		          <span class="list-content"><p></p></span>
		        </li> -->
		      </ul>
		      </c:forEach>
	    </div>
	</c:if>


    <!-- 자격 -->
    <c:if test="${not empty resumeList.myLicenseList}">
	    <div class="section">
	      <div class="section-title">자격증</div>
		      <c:forEach items="${resumeList.myLicenseList }" var="license">
			      <ul class="section-list">
			        <li>
			          <span class="list-label">취득일</span>
			          <span class="list-content">${license.licensePassDate }</span>
			        </li>
			        <li>
			          <span class="list-label">명칭</span>
			          <span class="list-content">${license.licenseCodeName }</span>
			        </li>
			      </ul>
		      </c:forEach>
		      <!-- <li>
		        <span class="list-label">발급기관</span>
		        <span class="list-content">대전남부경찰서(도로교통공단)</span>
		      </li> -->
	    </div>
	</c:if>

    <!-- 어학자격증 -->
    <c:if test="${not empty resumeList.languageSkillList}">
	    <div class="section">
	      <div class="section-title">어학 자격증</div>
	      <c:forEach items="${resumeList.languageSkillList }" var="lang">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">취득일</span>
		          <span class="list-content">${lang.passDate }</span>
		        </li>
		        <li>
		          <span class="list-label">명칭</span>
		          <span class="list-content">${lang.languageExamName }</span>
		        </li>
		        <li>
		          <span class="list-label">급수</span>
		          <span class="list-content">${lang.languageExamLevelCodeName } - 바인딩 잘 되는지 확인필요</span>
		        </li>
		      </ul>
	      </c:forEach>
	    </div>
	</c:if>

    <!-- 수상내역 -->
    <c:if test="${not empty resumeList.awardList}">
	    <div class="section">
	      <div class="section-title">수상</div>
	      <c:forEach items="${resumeList.awardList }" var="award">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">취득일</span>
		          <span class="list-content">${award.awardDate }</span>
		        </li>
		        <li>
		          <span class="list-label">수상명</span>
		          <span class="list-content">${award.awardName }</span>
		        </li>
		        <li>
		          <span class="list-label">주최기관</span>
		          <span class="list-content">${award.hosting }</span>
		        </li>
		      </ul>
	      </c:forEach>
   		</div>
	</c:if>

    

    <!-- 포트폴리오 및 기타문서 -->
    <c:if test="${not empty resumeList.portfolioList}">
	    <div class="section">
	      <div class="section-title">포트폴리오</div>
	      <c:forEach items="${resumeList.portfolioList }" var="portfolio">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">포트폴리오</span>
		          <span class="list-content">
		            <!-- <a class="portfolio-link" href="http://localhost/mypage/resume/detail">http://localhost/mypage/resume/detail</a> -->
		            <%-- <span>${ }</span> --%>
		            <br>
		            <span style="font-size:13px;color:#b5b8c6;">직접수행한 프로젝트와 협업프로젝트에서 제작한 포트폴리오와 독립된 포트폴리오입니다.</span>
		          </span>
		        </li>
		      </ul>
	      </c:forEach>
	    </div>
    </c:if>

    

    <!-- 자기소개서 -->
    <c:if test="${not empty resumeList.introductionList}">
	    <div class="section">
	      <div class="section-title">자기소개서</div>
	      <c:forEach items="${resumeList.introductionList }" var="intr">
		      <ul class="section-list">
		        <li style="flex-direction:column;align-items:flex-start;">
		          <span class="selfintro-title">자기소개서 명</span>
		          <span class="selfintro-content">${intr.introductionName }</span>
		        </li>
		        <li style="flex-direction:column;align-items:flex-start;">
		          <span class="selfintro-title">자기소개서 문항</span>
		          <span class="selfintro-content">${intr.introductionQuestion }</span>
		        </li>
		        <li style="flex-direction:column;align-items:flex-start;">
		          <span class="selfintro-title">자기소개서 내용</span>
		          <span class="selfintro-content">${intr.introductionContent }</span>
		        </li>
		        <li style="flex-direction:column;align-items:flex-start;">
		          <span class="selfintro-title">작성일시</span>
		          <span class="selfintro-content">${intr.introductionCreateDate }</span>
		        </li>
		      </ul>
	      </c:forEach>
	    </div>
    </c:if>

    

    <!-- 취업우대사항 -->
    <c:if test="${not empty resumeList.supportList}">
	    <div class="section">
	      <div class="section-title">취업우대사항</div>
	      <ul class="section-list">
	        <li>
	          <span class="prefer-label">고용지원대상:</span>
	          <span class="prefer-content">청년내일채움공제(고졸이상), 장애인(장애인등록증소지자 이상)</span>
	        </li>
	      </ul>
	    </div>
    </c:if>
    
    <!-- 병역 -->
    <c:if test="${not empty resumeList.militaryList}">
	    <div class="section">
	      <div class="section-title">병역</div>
	      <c:forEach items="${resumeList.militaryList }" var="mil">
		      <ul class="section-list">
		        <li>
		          <span class="list-label">복무</span>
		          <span class="list-content">${mil.serviceCategoryCode }</span>
		        </li>
		        <li>
		          <span class="list-label">군별</span>
		          <span class="list-content">${mil.militaryTypeCode }</span>
		        </li>
		        <li>
		          <span class="list-label">계급</span>
		          <span class="list-content">${mil.militaryRankCode }</span>
		        </li>
		        <li>
		          <span class="list-label">전역사유</span>
		          <span class="list-content">${mil.dischargeCode }</span>
		        </li>
		        <li>
		          <span class="list-label">복무기간</span>
		          <span class="list-content">${mil.militaryStartDate } ~ ${mil.militaryEndDate } </span>
		        </li>
		      </ul>
	      </c:forEach>
   		</div>
	</c:if>
  </div>
  <div class="d-flex justify-content-between">
  	<a href="/mypage/resume/list" class="btn btn_gray_line">목록</a>
  	<div class="d-flex gap-1">
  		<a href="" class="btn btn_red_line">삭제</a>
  		<a href="" class="btn btn_violet_line">수정</a>
  	</div>
  </div>
</body>