<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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


    <!-- 학력 -->
    <!-- 학력 (EDUCATION) 입력폼 -->
	<div class="section" id="section-edu">
	  <div class="section-title">
	    <h6>학력<span class="must">필수</span></h6>
	    <button class="add-btn" type="button" id="btn-edu">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">졸업학교 정보를 입력해 주세요</div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	</div>
	
	
	


    <!-- 경력 (CAREER) 입력폼 -->
	<div class="section" id="section-career">
	  <div class="section-title">
	    <h6>경력</h6>
	    <button class="add-btn" id="btn-career">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    경력사항 또는 인턴, 현장실습 등 실제로 해본 경험이 있다면 자세히 작성해주세요!
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	</div>


    <!-- 스킬 -->
    <!-- 보유기술 (MY_SKILL) 입력폼 -->
	<div class="section" id="section-skill">
	  <div class="section-title">
	    <h6>보유기술</h6>
	    <button class="add-btn" id="btn-skill">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    주요 보유기술, 툴, 언어 등을 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-skill" onbutton="buttonForm(event,'skill')">
	    보유기술명
	    <div class="section-form-row">
	      <label>보유기술명</label>
	      <input type="text" name="mySkillName" placeholder="예: Java, Spring, React" maxlength="255" required>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('skill',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>


    <!-- 보유경험 (MY_EXPERIENCE) 입력폼 -->
	<div class="section" id="section-exp">
	  <div class="section-title">
	    <h6>보유경험</h6>
	    <button class="add-btn" id="btn-exp">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    대외활동, 인턴, 교육 등 주요 경험을 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-exp" onbutton="buttonForm(event,'exp')">
	    경험구분코드
	    <div class="section-form-row">
	      <label>경험구분</label>
	      <select name="expCode" id="expCodeSelect" required>
	        <option value="">선택</option>
	        JS로 옵션 채움
	      </select>
	    </div>
	    경험명
	    <div class="section-form-row">
	      <label>경험명</label>
	      <input type="text" name="expName" placeholder="예: 네이버 인턴십" maxlength="255" required>
	    </div>
	    경험 시작 날짜
	    <div class="section-form-row">
	        <label>시작일</label>
	        <input type="month" name="expStartDate" required>
	    </div>
	    경험 종료 날짜
	    <div class="section-form-row">
	        <label>종료일</label>
	        <input type="month" name="expEndDate">
	    </div>
	    기관명
	    <div class="section-form-row">
	      <label>기관명</label>
	      <input type="text" name="organizationName" placeholder="예: 네이버" maxlength="255" required>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('exp',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>

    
    <!-- 활동 - 고용지원 (SUPPORT) 입력폼 -->
	<div class="section" id="section-support">
	  <div class="section-title">
	    <h6>고용지원 정보</h6>
	    <button class="add-btn" id="btn-support">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    장애 등 고용지원 대상 정보를 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-support" onbutton="buttonForm(event,'support')">
	    장애코드(공통)
	    <div class="section-form-row">
	      <label>장애유형</label>
	      <select name="disabilityCode" id="disabilityCodeSelect" required>
	        <option value="">선택</option>
	        JS로 옵션 채움
	      </select>
	    </div>
	    장애등급 코드(공통)
	    <div class="section-form-row">
	      <label>장애등급</label>
	      <select name="disabilityLevelCode" id="disabilityLevelCodeSelect" required>
	        <option value="">선택</option>
	        JS로 옵션 채움
	      </select>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('support',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>


    
    <!-- 보유자격 (MY_LICENSE) 입력폼 -->
	<div class="section" id="section-license">
	  <div class="section-title">
	    <h6>보유자격</h6>
	    <button class="add-btn" id="btn-license">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    보유한 자격증 정보를 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-license" onbutton="buttonForm(event,'license')">
	    자격 코드(공통)
	    <div class="section-form-row">
	      <label>자격증명</label>
	      <input tpye="text" name="licenseCode" id="licenseCodeSelect" required placeholder="자격증 명을 입력해주세요.">
	    </div>
	    자격 합격년월
	    <div class="section-form-row">
	      <label>취득일자</label>
	      <input type="date" name="licensePassDate" required>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('license',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>

    
    <!-- 어학 (LANGUAGE_SKILL) 입력폼 -->
	<div class="section" id="section-language">
	  <div class="section-title">
	    <h6>어학</h6>
	    <button class="add-btn" id="btn-language">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    어학 자격증, 시험 정보 등을 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-language" onbutton="buttonForm(event,'language')">
	    <div class="section-form-row">
	      <label>시험 구분</label>
	      <select name="languageExamCode" id="languageExamCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    <div class="section-form-row">
	      <label>어학명</label>
	      <select name="languageCode" id="languageCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    <div class="section-form-row">
	      <label>시험명</label>
	      <input type="text" name="languageExamName" placeholder="예: TOEIC, JLPT N1" maxlength="255" required>
	    </div>
	    <div class="section-form-row">
	      <label>취득일자</label>
	      <input type="date" name="passDate" required>
	    </div>
	    <div class="section-form-row">
	      <label>시험유형</label>
	      <input type="text" name="languageExamType" placeholder="예: 정기/특별" maxlength="10">
	    </div>
	    <div class="section-form-row">
	      <label>시험점수</label>
	      <input type="text" name="languageExamScore" placeholder="예: 900" maxlength="10">
	    </div>
	    <div class="section-form-row">
	      <label>급수(레벨)</label>
	      <input type="text" name="languageExamLevelCode" id="languageExamLevelCode" placeholder="레벨을 입력해주세요. 예) N5">
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('language',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>

    
    <!-- 수상 (AWARD) 입력폼 -->
	<div class="section" id="section-award">
	  <div class="section-title">
	    <h6>수상</h6>
	    <button class="add-btn" id="btn-award">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    주요 수상 이력을 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-award" onbutton="buttonForm(event,'award')">
	    수상명
	    <div class="section-form-row">
	      <label>수상명</label>
	      <input type="text" name="awardName" placeholder="예: 프로그래밍 경진대회 대상" maxlength="255" required>
	    </div>
	    수상일
	    <div class="section-form-row">
	      <label>수상일</label>
	      <input type="date" name="awardDate" required>
	    </div>
	    주최기관
	    <div class="section-form-row">
	      <label>주최기관</label>
	      <input type="text" name="hosting" placeholder="예: 한국정보화진흥원" maxlength="255" required>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('award',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>


    <!-- 포트폴리오 (PORTFOLIO) 입력폼 -->
	<div class="section" id="section-portfolio">
	  <div class="section-title">
	    <h6>포트폴리오</h6>
	    <button class="add-btn" id="btn-portfolio">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    주요 프로젝트, 작업 포트폴리오 정보를 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-portfolio" onbutton="buttonForm(event,'portfolio')">
	    포트폴리오 이름
	    <div class="section-form-row">
	      <label>포트폴리오 이름</label>
	      <input type="text" name="porName" placeholder="예: 쇼핑몰 구축 프로젝트" maxlength="60" required>
	    </div>
	    포트폴리오 URL
	    <div class="section-form-row">
	      <label>포트폴리오 URL</label>
	      <input type="url" name="porUrl" placeholder="예: https://github.com/username/project" maxlength="255">
	    </div>
	    작업 시작일자
	    <div class="section-form-row">
	        <label>시작일자</label>
	        <input type="date" name="porStartDate" required>
	    </div>
	    작업 종료일자
	    <div class="section-form-row">
	        <label>종료일자</label>
	        <input type="date" name="porEndDate">
	    </div>
	    작업 설명
	    <div class="section-form-row w-100">
	      <label>작업 설명</label>
	      <textarea name="porInformation" placeholder="프로젝트 상세 내용을 입력하세요" maxlength="255" required></textarea>
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('portfolio',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>


    <!-- 자기소개서 (INTRODUCTION) 입력폼 -->
	<div class="section" id="section-selfintro">
	  <div class="section-title">
	    <h6>자기소개서</h6>
	    <button class="add-btn" id="btn-selfintro">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    자기소개서를 작성해주세요.
	  </div>
	  <div class="section-content">
	    여기 리스트 불러오는거 or 직접입력하는 거 둘중 하나 선택할 수 있게 작업, 직접 입력하는 건 문항을 내용에 입력하게끔 문항 없앰 (단건등록이니까)
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-selfintro" onbutton="buttonForm(event,'selfintro')">
	    자기소개서 제목
	    <div class="section-form-row">
	      <label>자기소개서 제목</label>
	      <input type="text" name="introductionName" maxlength="85" placeholder="예: 성장하는 개발자" required>
	    </div>
	    자기소개서 내용
	    <div class="section-form-row w-100">
	      <label class="mb-3">자기소개서 내용</label>
	      <div class="d-flex direction-column w-100">
		      <textarea name="introductionContent" maxlength="2000" placeholder="자기소개서 본문을 입력하세요. (최대 2000자)" required></textarea>
	      </div>
	    </div>
	    (제출여부, 작성일 등은 입력받지 않음)
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('selfintro',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>


    <!-- 병역 (MILITARY) 입력폼 -->
	<div class="section" id="section-military">
	  <div class="section-title">
	    <h6>병역</h6>
	    <button class="add-btn" id="btn-military">+ 추가</button>
	  </div>
	  <div class="section-content" style="color: #b8bfc9;">
	    병역 정보를 입력해주세요.
	  </div>
	  <div class="formContainer"></div>
      <div class="listContainer"></div>
	  <!-- <form class="section-form-wrap" id="form-military" onbutton="buttonForm(event,'military')">
	    복무구분
	    <div class="section-form-row">
	      <label>복무구분</label>
	      <select name="serviceCategoryCode" id="serviceCategoryCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    군별
	    <div class="section-form-row">
	      <label>군별</label>
	      <select name="militaryTypeCode" id="militaryTypeCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    계급
	    <div class="section-form-row">
	      <label>계급</label>
	      <select name="militaryRankCode" id="militaryRankCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    전역구분
	    <div class="section-form-row">
	      <label>전역구분</label>
	      <select name="dischargeCode" id="dischargeCodeSelect" required>
	        <option value="">선택</option>
	        AJAX로 옵션 채움
	      </select>
	    </div>
	    복무 시작일
	    <div class="section-form-row">
	      <label>복무 시작일자</label>
	      <input type="date" name="militaryStartDate" required>
	    </div>
	    복무 종료일
	    <div class="section-form-row">
	      <label>복무 종료일자</label>
	      <input type="date" name="militaryEndDate">
	    </div>
	    면제/미필 사유
	    <div class="section-form-row">
	      <label>면제/미필 사유</label>
	      <input type="text" name="militaryReason" maxlength="2000" placeholder="사유를 입력하세요">
	    </div>
	    <div class="section-form-btns">
	      <button type="button" class="btn btn_red_line" onclick="toggleForm('military',true)">취소</button>
	      <button type="button" class="btn btn_violet_line">저장</button>
	    </div>
	  </form> -->
	</div>
	<div class="section-form-btns">
      <button type="button" class="btn btn_red_line" onclick="toggleForm('military',true)">취소</button>
      <button type="submit" class="btn btn_violet">등록</button>
    </div>
  </div>
</body>