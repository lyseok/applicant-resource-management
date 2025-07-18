const quickMenuList = document.querySelectorAll("#quick_menu a");
quickMenuList.forEach(a => {
	a.addEventListener("click", function(e) {
		quickMenuList.forEach(link => link.classList.remove("on"));
		e.target.classList.add("on")
	})
})
// 로딩 show/hide 함수
function showLoading() {
	document.getElementById('loadingSpinner').style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
	document.getElementById('loadingSpinner').style.setProperty('display', 'none', 'important');
}

// 각 form 몇개 추가 됐는지 카운트
const typeCounters = {
	educationList: 0,
	careerList: 0,
	mySkillList: 0,
	myExperienceList: 0,
	supportList: 0,
	myLicenseList: 0,
	languageSkillList: 0,
	awardList: 0,
	portfolioList: 0,
	introduction: 0,
	militaryList: 0
};

/* + 추가 버튼 클릭 시 등록 form 추가 List */
const templateMap = {
	basicInfo: idx => `
			<div class="section-title">
				<h6>이력서 기본정보<span class="must">필수</span></h6>
			</div>
	    <div id="form-basicInfo" class="section-form-wrap">
	      <div class="section-form-row">
	        <label for="inputUserName" class="form-label required">이름</label>
	        <input type="text" class="form-control" id="inputUserName" name="userName" placeholder="이름">
	      </div>

	      <div class="section-form-row">
	        <label for="inputBirth" class="form-label required">생년월일</label>
	        <input type="date" class="form-control" id="inputBirth" name="birth">
	      </div>

	      <div class="section-form-row">
	        <label for="inputEmail" class="form-label required">이메일</label>
	        <input type="email" class="form-control" id="inputEmail" name="email" placeholder="이메일">
	      </div>

	      <div class="section-form-row">
	        <label for="inputTel" class="form-label required">연락처</label>
	        <input type="text" class="form-control" id="inputTel" name="tel" placeholder="010-1234-5678">
	      </div>

	      <div class="section-form-row">
	        <label for="inputAddress" class="form-label required">주소</label>
	        <input type="text" class="form-control" id="inputAddress" name="address" placeholder="주소 전체">
	      </div>

	      <div class="section-form-row d-flex justify-content-end gap-2 mt-3">
	        <button type="button" class="btn btn_red_line" id="cancelBasicBtn">취소</button>
	        <button type="button" class="btn btn_violet" id="saveBasicBtn">확인</button>
	      </div>
	    </div>
	  `,
	educationList: idx => `
    <div class="section-form-wrap" id="form-educationList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">최종 학력</label>
        <select name="resumeVO.educationList[${idx}].highestEducationCode" id="highestEducationCode${idx}" >
		  <option value="">선택</option>
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label class="required">학교명</label>
        <input type="text" name="resumeVO.educationList[${idx}].schoolName" placeholder="예: 서울대학교" maxlength="150" >
      </div>
      <div class="section-form-row">
        <label class="required">졸업여부</label>
		<select name="resumeVO.educationList[${idx}].graduateYn" id="graduateYn${idx}" data-idx="${idx}" >
		  <option value="">선택</option>
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label class="required">편입여부</label>
        <div class="form-check-wrap d-flex">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].transferYn" value="N" checked>
            <label class="form-check-label">비편입</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].transferYn" value="Y" >
            <label class="form-check-label">편입</label>
          </div>
        </div>
      </div>
      <div class="section-form-row">
        <label class="required">입학일자</label>
        <input type="date" name="resumeVO.educationList[${idx}].entranceDate">
      </div>
      <div class="section-form-row graduateView d-none">
        <label class="">졸업일자</label>
        <input type="date" name="resumeVO.educationList[${idx}].graduateDate">
      </div>
      <div class="section-form-row">
        <label class="required">지역</label>
        <input type="text" name="resumeVO.educationList[${idx}].location">
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">학과</label>
        <input type="text" name="resumeVO.educationList[${idx}].departmentCode" placeholder="예: CSE01" maxlength="12">
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">주전공</label>
        <input type="text" name="resumeVO.educationList[${idx}].specialtyList[0].mainMajor" placeholder="주전공 학과를 입력해주세요." maxlength="12">
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">전공 구분</label>
        <select name="resumeVO.educationList[${idx}].specialtyList[0].subMajorCode" id="majorTypeCode${idx}" data-idx="${idx}" >
		    	<option value="">선택</option>
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row selectView d-none">
        <label class="">부전공</label>
        <input type="text" name="resumeVO.educationList[${idx}].specialtyList[0].subMajor" placeholder="부전공 학과를 입력해주세요.">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	careerList: idx => `
    <div class="section-form-wrap" id="form-careerList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">프리랜서 여부</label>
        <div class="form-check-wrap d-flex" id="freelancer${idx}">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].freelancer" value="N" checked>
            <label class="form-check-label">정규직/계약직</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].freelancer" value="Y">
            <label class="form-check-label">프리랜서</label>
          </div>
        </div>
      </div>
      <div class="section-form-row">
        <label class="required">회사명</label>
        <div class="d-flex w-100 flex-column">
  				<div class="d-flex position-relative gap-2">
  					<div class="flex-fill">
		          <input type="text" name="resumeVO.careerList[${idx}].comId" class="w-100 h-100" placeholder="예: NAVER" maxlength="100" id="comId" >
		          <ul id="comSuggestions" class="list-group position-absolute w-100 shadow" style="z-index: 2000; max-width:301px">
							</ul>
						</div>
          	<button class="btn search_com_btn btn_violet_line" type="button">검색</button>          
          </div>          
					<div><span id="comSearchMsg" class="mt-2 fs-14"></span></div>
        </div>
      </div>
      <div class="section-form-row">
        <label class="required">직무</label>
        <select name="resumeVO.careerList[${idx}].jobCode" id="jobCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label class="required">재직여부</label>
        <div class="form-check-wrap d-flex">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].tenure" value="Y" checked>
            <label class="form-check-label">재직중</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].tenure" value="N">
            <label class="form-check-label">퇴사</label>
          </div>
        </div>
      </div>
      <div class="section-form-row">
        <label class="required">입사일자</label>
        <input type="date" name="resumeVO.careerList[${idx}].startWorkDate" >
      </div>
      <div class="section-form-row selectView d-none">
        <label class="">퇴사일자</label>
        <input type="date" name="resumeVO.careerList[${idx}].retireDate">
      </div>
      <div class="section-form-row">
        <label class="required">연차</label>
        <select name="resumeVO.careerList[${idx}].careerYear" id="careerYear${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews">
        <label class="">근무부서</label>
        <input type="text" name="resumeVO.careerList[${idx}].department" placeholder="예: 개발팀" maxlength="100">
      </div>
      <div class="section-form-row selectViews">
        <label class="">담당업무</label>
        <input type="text" name="resumeVO.careerList[${idx}].responsibility" placeholder="예: 백엔드 개발 및 유지보수" maxlength="255">
      </div>
      <div class="section-form-row selectViews">
        <label class="">직급</label>
        <select name="resumeVO.careerList[${idx}].jobGradeCode"id="jobGradeCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews">
        <label class="">직책</label>
        <select name="resumeVO.careerList[${idx}].positionCode" id="positionCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label class="">연봉</label>
        <input type="text" name="resumeVO.careerList[${idx}].salary" placeholder="예: 4000만원" maxlength="30">
      </div>
      <div class="section-form-row">
        <label class="">근무지역</label>
        <input type="text" name="resumeVO.careerList[${idx}].location" placeholder="예: 서울" maxlength="100">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	mySkillList: idx => `
    <div class="section-form-wrap" id="form-mySkillList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">보유기술명</label>
        <input type="text" name="resumeVO.mySkillList[${idx}].mySkillName" placeholder="예: Java, Spring, React" maxlength="255" >
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	myExperienceList: idx => `
    <div class="section-form-wrap" id="form-myExperienceList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">경험구분</label>
        <select name="resumeVO.myExperienceList[${idx}].expCode" id="expCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label class="required">경험명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].expName" placeholder="예: 네이버 인턴십" maxlength="255" >
      </div>
      <div class="section-form-row">
        <label class="required">시작일자</label>
        <input type="date" name="resumeVO.myExperienceList[${idx}].expStartDate" >
      </div>
      <div class="section-form-row">
        <label class="required">종료일자</label>
        <input type="date" name="resumeVO.myExperienceList[${idx}].expEndDate">
      </div>
      <div class="section-form-row">
        <label class="required">기관명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].organizationName" placeholder="예: 네이버" maxlength="255" >
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	supportList: idx => `
    <div class="section-form-wrap" id="form-supportList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">고용지원대상</label>
        <select name="resumeVO.supportList[${idx}].disabilityCode" id="disabilityCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectView d-none">
        <label class="">장애등급</label>
        <select name="resumeVO.supportList[${idx}].disabilityLevelCode" id="disabilityLevelCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	myLicenseList: idx => `
    <div class="section-form-wrap" id="form-myLicenseList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">자격증명</label>
        <input type="text" name="resumeVO.myLicenseList[${idx}].licenseCode" required placeholder="자격증 명을 입력해주세요.">
      </div>
      <div class="section-form-row">
        <label class="required">취득일자</label>
        <input type="date" name="resumeVO.myLicenseList[${idx}].licensePassDate" >
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	languageSkillList: idx => `
    <div class="section-form-wrap" id="form-languageSkillList${idx}" data-idx="${idx}">
	  <div class="section-form-row">
	    <label class="required">어학 구분</label>
	    <select name="resumeVO.languageSkillList[${idx}].languageCode" id="languageCode${idx}" >
	      <option value="">선택</option>
	    </select>
	  </div>
      <div class="section-form-row">
        <label class="required">시험 구분</label>
        <select name="resumeVO.languageSkillList[${idx}].languageExamCode" id="languageExamCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label class="required">시험명</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamName" placeholder="예: TOEIC, JLPT N1" maxlength="255" >
      </div>
      <div class="section-form-row">
        <label class="required">취득일자</label>
        <input type="date" name="resumeVO.languageSkillList[${idx}].passDate" >
      </div>
      <div class="section-form-row">
        <label class="required">시험유형</label>        
        <select name="resumeVO.languageSkillList[${idx}].languageExamType" id="languageExamType" >
          <option value="">선택</option>
          <option value="1">점수제</option>
          <option value="2">급수제</option>
        </select>
      </div>
      <div class="section-form-row selectView d-none">
        <label>시험점수</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamScore" placeholder="예: 900" maxlength="10">
      </div>
      <div class="section-form-row languageView d-none">
        <label>급수(레벨)</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamLevelCode" placeholder="레벨을 입력해주세요. 예) N5">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	awardList: idx => `
    <div class="section-form-wrap" id="form-awardList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">수상명</label>
        <input type="text" name="resumeVO.awardList[${idx}].awardName" placeholder="예: 프로그래밍 경진대회 대상" maxlength="255" >
      </div>
      <div class="section-form-row">
        <label class="required">수상일자</label>
        <input type="date" name="resumeVO.awardList[${idx}].awardDate" >
      </div>
      <div class="section-form-row">
        <label class="required">주최기관</label>
        <input type="text" name="resumeVO.awardList[${idx}].hosting" placeholder="예: 한국정보화진흥원" maxlength="255" >
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	portfolioList: idx => `
    <div class="section-form-wrap" id="form-portfolioList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">포트폴리오 이름</label>
        <input type="text" name="resumeVO.portfolioList[${idx}].porName" placeholder="예: 쇼핑몰 구축 프로젝트" maxlength="60" >
      </div>
      <div class="section-form-row">
        <label class="required">포트폴리오 URL</label>
        <input type="url" name="resumeVO.portfolioList[${idx}].porUrl" placeholder="예: https://github.com/username/project" maxlength="255">
      </div>
      <div class="section-form-row">
        <label class="required">시작일자</label>
        <input type="date" name="resumeVO.portfolioList[${idx}].porStartDate" >
      </div>
      <div class="section-form-row">
        <label class="required">종료일자</label>
        <input type="date" name="resumeVO.portfolioList[${idx}].porEndDate">
      </div>
      <div class="section-form-row w-100">
        <label class="required">작업 설명</label>
        <textarea name="resumeVO.portfolioList[${idx}].porInformation" placeholder="프로젝트 상세 내용을 입력하세요" maxlength="255" ></textarea>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `,
	introduction: idx => `
	<div class="modal fade" id="introductionModal" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">자기소개서 선택</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	      <div class="modal-body">
	      	<div class="section-form-row">
	      		<label class="required">자기소개서</label>
		        <select id="introductionSelect" class="form-select">
		          <option value="">자기소개서를 선택하세요</option>
		        </select>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-primary" id="confirmIntroduction">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
  `,
	militaryList: idx => `
    <div class="section-form-wrap" id="form-militaryList${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label class="required">복무구분</label>
        <select name="resumeVO.militaryList[${idx}].serviceCategoryCode" id="serviceCategoryCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">군별</label>
        <select name="resumeVO.militaryList[${idx}].militaryTypeCode" id="militaryTypeCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">계급</label>
        <select name="resumeVO.militaryList[${idx}].militaryRankCode" id="militaryRankCode${idx}" >
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">전역사유</label>
        <select name="resumeVO.militaryList[${idx}].dischargeCode" id="dischargeCode${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row selectViews d-none">
        <label class="">복무 시작일자</label>
        <input type="date" name="resumeVO.militaryList[${idx}].militaryStartDate" >
      </div>
      <div class="section-form-row endDate d-none">
        <label class="">복무 종료일자</label>
        <input type="date" name="resumeVO.militaryList[${idx}].militaryEndDate">
      </div>
      <div class="section-form-row cateCodeB d-none">
        <label class="">면제/미필 사유</label>
        <input type="text" name="resumeVO.militaryList[${idx}].militaryReason" maxlength="2000" placeholder="사유를 입력하세요">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">확인</button>
      </div>
    </div>
  `
	// ※ selfintro(자기소개서)는 List가 아니라 단일 IntroductionVO이므로, 'resumeVO.introduction.introductionName'처럼 리스트 인덱스 없음! ✔️
};


// 저장버튼 클릭 시 list 형태로 띄워주기.
// ♥♥♥♥ 여기 작업해야됌 ~~
const listItemMap = {
	educationList: (idx, data, type = "educationList") => `
    <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}"  data-type="${type}">
      <div class="d-flex">
	      <div>
	      	<strong>${data.schoolName}</strong>
	      	<span>( ${codeLabelMap.GRAD[data.graduateYn]} )</span>
	      	<div><span class="text-secondary fs-14">${data.entranceDate} ~ ${data.graduateDate || ''}</span></div>
	      </div>
      </div>
      <div class="d-flex gap-2">
	      <button type="button" class="btn_edit">
	      	<span class="material-symbols-outlined fs-3">stylus</span>
	      </button>
	      <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="educationList" data-idx="${idx}" >
	      	<span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
	     </button>
      </div>
    </div>
  `,
	careerList: (idx, data) => `
    <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
      <div class="d-flex flex-column">
        <strong>${data.comId || '회사명 미입력'}</strong>
        <span>${data.startWorkDate || '입사일 없음'} ~ ${data.retireDate || '재직중'}</span>
        <small>${data.department || ''} / ${data.responsibility || ''}</small>
      </div>      
      <div class="d-flex gap-2">
        <button type="button" class="btn_edit">
          <span class="material-symbols-outlined fs-3">stylus</span>
        </button>
        <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="careerList" data-idx="${idx}" >
          <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
        </button>
      </div>
    </div>
  `,
	mySkillList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <strong>${data.mySkillName || '기술명 없음'}</strong>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="mySkillList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	myExperienceList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.expName || '경험명 없음'}</strong>
          <span>${data.expStartDate || ''} ~ ${data.expEndDate || '진행중'}</span>
          <small>${data.organizationName || ''}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="myExperienceList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	myLicenseList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.licenseCode || '자격증명 없음'}</strong>
          <span>${data.licensePassDate || ''}</span>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="myLicenseList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	supportList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <p><strong>${codeLabelMap.VULN[data.disabilityCode] || ''}</strong> (${codeLabelMap.DSBL[data.disabilityLevelCode] || ''})</p>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="supportList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	awardList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.awardName || '수상명 없음'}</strong>
          <span>${data.awardDate || ''}</span>
          <small>${data.hosting || ''}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="awardList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	languageSkillList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.languageExamName || '시험명 없음'}</strong>
          <span>${data.passDate || ''}</span>
          <small>${codeLabelMap.LANG[data.languageCode] || ''} / ${data.languageExamScore || '점수 없음'}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="languageSkillList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	portfolioList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.porName || '포트폴리오 제목'}</strong>
          <span>${data.porStartDate || ''} ~ ${data.porEndDate || '진행중'}</span>
          <small>${data.porUrl || 'URL 없음'}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="portfolioList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
	militaryList: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div>
        	<strong>${codeLabelMap.SRVC[data.serviceCategoryCode] || ''} / ${codeLabelMap.MILT[data.militaryTypeCode] || ''}</strong>
        	<small>${data.militaryReason || ''}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="militaryList" data-idx="${idx}" >
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `
};


// 미리 세팅해놓는 빈 json 객체
const resume = {
	"resumeName": "",
	"resumeMainYn": "",
	"resumeNo": "",
	"userId": "",
	"userName": "",
	"photo": "",
	"birth": "",
	"email": "",
	"tel": "",
	"address": "",
	"veteranReason": "",
	"updateDate": "",
	"resumeSubmitYn": "",
	"resumeDeleteDate": "",
	"introductionNo": "",
	"company": {},
	"careerList": [],
	"supportList": [],
	"awardList": [],
	"myExperienceList": [],
	"mySkillList": [],
	"myLicenseList": [],
	"introduction": {},
	"languageSkillList": [],
	"portfolioList": [],
	"militaryList": [],
	"educationList": []
}

// 저장 버튼 클릭 시에 json에 추가해 줄 vo 세팅하기
function makeVO(type, data) {
	switch (type) {
		case "educationList":
			return {
				educationNo: "",
				resumeNo: "",
				highestEducationCode: data.highestEducationCode,
				schoolName: data.schoolName,
				graduateYn: data.graduateYn,
				transferYn: data.transferYn,
				entranceDate: data.entranceDate,
				graduateDate: data.graduateDate,
				location: data.location,
				departmentCode: data.departmentCode,
				deleteDate: "",
				specialtyList: [
					{
						educationNo: "",
						resumeNo: "",
						mainMajor: data.mainMajor,
						subMajor: data.subMajor,
						subMajorCode: data.subMajorCode,
						deleteDate: ""
					}
				]
			};

		case "careerList":
			return {
				careerNo: "",
				resumeNo: "",
				jobCode: data.jobCode,
				jobCodeName: "",
				startWorkDate: data.startWorkDate,
				retireDate: data.retireDate,
				tenure: data.tenure,
				department: data.department,
				responsibility: data.responsibility,
				freelancer: data.freelancer,
				jobGradeCode: data.jobGradeCode,
				jobGradeCodeName: "",
				positionCode: data.positionCode,
				positionCodeName: "",
				careerYear: data.careerYear,
				careerYearName: "",
				salary: data.salary,
				location: data.location,
				deleteDate: "",
				comId: data.comId,
				company: {},
				resume: "",
				userName: "",
				userId: "",
				myskill: {},
				mySkillName: ""
			};

		case "mySkillList":
			return {
				mySkill: "",
				resumeNo: "",
				mySkillName: data.mySkillName,
				deleteDate: ""
			};

		case "myExperienceList":
			return {
				myExperience: "",
				resumeNo: "",
				expCode: data.expCode,
				expCodeName: "",
				expName: data.expName,
				expStartDate: data.expStartDate,
				expEndDate: data.expEndDate,
				organizationName: data.organizationName,
				deleteDate: ""
			};

		case "supportList":
			return {
				supportNo: "",
				resumeNo: "",
				disabilityCode: data.disabilityCode,
				disabilityCodeName: "",
				disabilityLevelCode: data.disabilityLevelCode,
				disabilityLevelCodeName: "",
				deleteDate: ""
			};

		case "myLicenseList":
			return {
				myLicense: "",
				resumeNo: "",
				licenseCode: data.licenseCode,
				licenseCodeName: "",
				licensePassDate: data.licensePassDate,
				deleteDate: ""
			};

		case "languageSkillList":
			return {
				languageSkillNo: "",
				resumeNo: "",
				languageCode: data.languageCode,
				languageExamCode: data.languageExamCode,
				languageExamName: data.languageExamName,
				passDate: data.passDate,
				languageExamType: data.languageExamType,
				languageExamScore: data.languageExamScore,
				languageExamLevelCode: data.languageExamLevelCode,
				deleteDate: ""
			};

		case "awardList":
			return {
				awardNo: "",
				resumeNo: "",
				awardName: data.awardName,
				awardDate: data.awardDate,
				hosting: data.hosting,
				deleteDate: ""
			};

		case "portfolioList":
			return {
				portfolioNo: "",
				resumeNo: "",
				porName: data.porName,
				porUrl: data.porUrl,
				porStartDate: data.porStartDate,
				porEndDate: data.porEndDate,
				porInformation: data.porInformation,
				deleteDate: ""
			};

		case "militaryList":
			return {
				militaryNo: "",
				resumeNo: "",
				serviceCategoryCode: data.serviceCategoryCode,
				militaryTypeCode: data.militaryTypeCode,
				militaryRankCode: data.militaryRankCode,
				dischargeCode: data.dischargeCode,
				militaryStartDate: data.militaryStartDate,
				militaryEndDate: data.militaryEndDate,
				militaryReason: data.militaryReason,
				deleteDate: ""
			};

		case "introduction":
			return {
				introductionName: data.introductionName,
				introductionContent: data.introductionContent
			};

		default:
			return data;
	}
}


// makeVO랑 세트로 동작, 확인 버튼 클릭 시 vo를 위에서 만들고 > 여기서 어느위치에 추가할지 정해줌 
const arrayKey = {
	educationList: "educationList",
	myLicenseList: "myLicenseList",
	careerList: "careerList",
	mySkillList: "mySkillList",
	myExperienceList: "myExperienceList",
	supportList: "supportList",
	awardList: "awardList",
	languageSkillList: "languageSkillList",
	portfolioList: "portfolioList",
	militaryList: "militaryList"
	// 필요하면 계속 추가
};




/* =============================== selct 비동기 요청 코드 =============================== */
const codeLabelMap = {}; // ex: codeLabelMap.GRAD["GRAD01"] === "졸업"

//코드 그룹 키 추출 함수
function getCodeGroupKeyFromUrl(url) {
	const match = url.match(/\/cmncodegroup\/([A-Z]+)/);
	return match ? match[1] : null; // 예: "/ajax/code/cmncodegroup/GRAD" → "GRAD"
}

// 1. select 셋업 정보를 한 곳에 모아둠
const selectLoadConfig = {
	educationList: [

		{
			selector: el => el.querySelector("#highestEducationCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/EDUC",
			key: "cmnCodeList", // 배열이 담긴 데이터 키
			valueKey: "codeDetailNo",  // 옵션 value
			labelKey: "codeName"       // 옵션 text
		},
		{
			selector: el => el.querySelector("#majorTypeCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/SPEC",
			key: "cmnCodeList",
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#graduateYn" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/GRAD",
			key: "cmnCodeList",
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#location" + el.dataset.idx),  // 지역
			url: "/ajax/code/city",
			key: "",
			valueKey: "cityCodeNo",
			labelKey: "cityName"
		}
	],
	careerList: [
		{
			selector: el => el.querySelector("#jobCode" + el.dataset.idx),
			url: "/ajax/code/job",
			key: "",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "jobCode",
			labelKey: "jobName"
		},
		{
			selector: el => el.querySelector("#jobGradeCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/RANK",
			key: "cmnCodeList",
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#positionCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/SEAT",
			key: "cmnCodeList",
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#careerYear" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/YEXP",
			key: "cmnCodeList",
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
	],
	myExperienceList: [
		{
			selector: el => el.querySelector("#expCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/EXPR",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
	],
	supportList: [
		{
			selector: el => el.querySelector("#disabilityLevelCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/DSBL",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#disabilityCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/VULN",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
	],
	languageSkillList: [
		{
			selector: el => el.querySelector("#languageCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/LANG",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#languageExamCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/LXAM",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
	],
	militaryList: [
		{
			selector: el => el.querySelector("#serviceCategoryCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/SRVC",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#militaryTypeCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/MILT",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#militaryRankCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/MLRK",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
		{
			selector: el => el.querySelector("#dischargeCode" + el.dataset.idx),
			url: "/ajax/code/cmncodegroup/DSCH",
			key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
			valueKey: "codeDetailNo",
			labelKey: "codeName"
		},
	]
};

// 2. 폼이 새로 생길 때마다 selectLoadConfig의 배열을 순회하며 요청&세팅
async function setDynamicSelects(type, formElem) {
	const confArr = selectLoadConfig[type];
	if (!confArr) return;

	for (const conf of confArr) {
		const selectElem = conf.selector(formElem);
		if (selectElem) {
			await setSelectOptionsByApi(selectElem, conf.url, conf.key, conf.valueKey, conf.labelKey);
		}
	}
}

// 3. 셀렉트 엑시오스 비동기 요청 전송
async function setSelectOptionsByApi(selectElem, url, arrKey, valueKey, labelKey) {
	if (!selectElem) return;
	try {
		const resp = await axios.get(url);
		let list;
		if (!arrKey) {
			list = Array.isArray(resp.data) ? resp.data : resp.data; // 배열이 data 전체일 때
		} else {
			list = resp.data[arrKey];
		}
		selectElem.insertAdjacentHTML('beforeend', list.map(i =>
			`<option value="${i[valueKey]}">${i[labelKey]}</option>`
		).join(""));
		// ✅ label 정보 전역 저장
		const codeGroupKey = getCodeGroupKeyFromUrl(url);
		if (codeGroupKey) {
			if (!codeLabelMap[codeGroupKey]) codeLabelMap[codeGroupKey] = {};
			list.forEach(i => {
				codeLabelMap[codeGroupKey][i[valueKey]] = i[labelKey];
			});
		}
	} catch (err) {
		console.error("옵션 로드 실패:", err);
	}
}
/* =============================== selct 비동기 요청 코드 =============================== */

// select에서 선택된 값에 따라 다음 필드 보여주기
document.addEventListener("change", function(e) {
	const isSelect = e.target.tagName === "SELECT";
	const isRadio = e.target.tagName === "INPUT" && e.target.type === "radio";

	if (!isSelect && !isRadio) return;

	const { name, value } = e.target;
	console.log(value); // ← 이게 바로 현재 선택된 값!

	// 처리할 대상 필드들 (name 속성 기준)
	const targetFields = [
		"resumeVO.educationList",         // highestEducationCode / graduateYn
		"resumeVO.careerList",            // 경력
		"resumeVO.militaryList",          // 병역
		"resumeVO.supportList",           // 
		"resumeVO.languageSkillList"      // 어학
	];

	const matchedField = targetFields.find(field => name.startsWith(field));
	if (!matchedField) return;

	// 인덱스 추출 (예: resumeVO.educationList[2].graduateYn → 2)
	const idxMatch = name.match(/\[(\d+)\]/);
	const idx = idxMatch ? idxMatch[1] : "0";

	const formEl = e.target.closest(".section-form-wrap");
	if (!formEl) return;

	const targetRows = formEl.querySelectorAll(".selectViews");
	console.log("찾은 selectViews 개수:", targetRows.length); // ← 0이면 안 됨!

	const targetRow = formEl.querySelector(".selectView");
	const graduateView = formEl.querySelector(".graduateView");
	const languageView = formEl.querySelector(".languageView");
	const cateCodeB = formEl.querySelector(".cateCodeB");

	// 값에 따라 분기
	if (name.includes("highestEducationCode")) {
		if (["EDUC-003", "EDUC-004", "EDUC-005"].includes(value)) {
			console.log(">>> 보여줄 예정");
			targetRows.forEach(row => row.classList.remove("d-none"));
		} else {
			console.log(">>> 숨길 예정");
			targetRows.forEach(row => row.classList.add("d-none"));
		}
	}

	if (name.includes("graduateYn")) {
		if (value === "GRAD-001") {
			graduateView?.classList.remove("d-none");
		} else {
			graduateView?.classList.add("d-none");
		}
	}

	if (name.includes("subMajorCode")) {
		if (value === "SPEC-001" || value === "SPEC-002" || value === "SPEC-003") {
			targetRow.classList.remove("d-none");
		} else {
			targetRow.classList.add("d-none");
		}
	}

	/* 경력 */
	if (name.includes("freelancer")) {
		if (value === "Y") {
			targetRows.forEach(row => row.classList.add("d-none"));
		} else {
			targetRows.forEach(row => row.classList.remove("d-none"));
		}
	}

	if (name.includes("tenure")) {
		if (value === "N") {
			targetRow.classList.remove("d-none");
		} else {
			targetRow.classList.add("d-none");
		}
	}

	/* 고용지원정보 */
	if (name.includes("disabilityCode")) {
		if (value === "VULN-002" || value === "VULN-008") {
			targetRow.classList.remove("d-none")
		} else {
			targetRow.classList.add("d-none");
		}
	}

	/* 어학 - 어학구분 변경 시 시험구분 옵션 로딩 */
	if (name.includes("languageCode")) {
		const langCode = value;
		const examSelect = formEl.querySelector(`[name="resumeVO.languageSkillList[${idx}].languageExamCode"]`);
		console.log(examSelect)
		if (!examSelect) return;

		// 기존 옵션 초기화
		examSelect.innerHTML = `<option value="">선택</option>`;

		// 비동기 요청
		axios.get(`/ajax/code/cmncode/${langCode}`)
			.then(resp => {
				console.log(resp.data)
				const list = resp.data || []; // 응답 형식에 따라 조정
				list.forEach(item => {
					const opt = document.createElement("option");
					opt.value = item.codeDetailNo;
					opt.textContent = item.codeName;
					examSelect.appendChild(opt);
				});
			})
			.catch(err => {
				console.error("어학 시험 구분 코드 로드 실패", err);
			});
	}


	/* 어학 - 시험유형 */
	if (name.includes("languageExamType")) {
		if (value === "1") {
			targetRow.classList.remove("d-none")
			languageView.classList.add("d-none")
		} else if (value === "2") {
			languageView.classList.remove("d-none")
			targetRow.classList.add("d-none");
		} else {
		}
	}

	/* 병역 - 복무구분 */
	if (name.includes("serviceCategoryCode")) {
		if (value === "SRVC-A" || value === "SRVC-D") {
			targetRows.forEach(item => { item.classList.remove("d-none") })
			cateCodeB.classList.add("d-none")
		} else if (value === "SRVC-B" || value === "SRVC-C") {
			targetRows.forEach(item => { item.classList.add("d-none") })
			cateCodeB.classList.remove("d-none")
		} else {
		}
	}

	if (name.includes("dischargeCode")) {
		const endDateRow = formEl.querySelector(".endDate");
		if (!endDateRow) return;

		if (value && value.trim() !== "") {
			endDateRow.classList.remove("d-none");
		} else {
			endDateRow.classList.add("d-none");
		}
	}


});





// ---- JS 추가 바인딩 부분 ----
document.addEventListener("DOMContentLoaded", function() {
	// DOM 로드 되자마자 Company 정보 불러오기
	axios.get("/ajax/career/company/list")
		.then(resp => {
			console.log(resp.data);
			companyList = resp.data;
		})
		.catch(err => {
			console.error(err)
		})


	document.querySelectorAll(".listContainer").forEach(container => {
		container.addEventListener("click", async function(e) {
			if (e.target.closest(".btn_edit")) {
				const itemWrap = e.target.closest('.list-item');
				const idx = itemWrap.dataset.idx;
				const type = itemWrap.dataset.type;
				const section = document.querySelector(`#section-${type}`);
				const formContainer = section.querySelector('.formContainer');
				const formId = `form-${type}${idx}`;
				let formEl = document.getElementById(formId);

				const data = resume[type][idx];

				// 기존 리스트 숨기기
				itemWrap.style.display = "none";

				if (formEl) {
					// 👉 이미 폼이 존재한다면 display만 변경
					formEl.style.display = "flex";
					formEl.dataset.edit = "true";
				} else {
					// 👉 폼이 없다면 새로 생성
					const formHtml = templateMap[type](idx);
					formContainer.insertAdjacentHTML('beforeend', formHtml);
					formEl = formContainer.lastElementChild;
					formEl.dataset.idx = idx;
					formEl.dataset.edit = "true";

					// select 세팅
					await setDynamicSelects(type, formEl);

					// 값 바인딩
					setTimeout(() => {
						for (const [key, val] of Object.entries(data)) {
							const input = formEl.querySelector(`[name$='.${key}']`);
							if (input) {
								if (input.tagName === "SELECT") {
									const hasOption = input.querySelector(`option[value="${val}"]`);
									if (hasOption) {
										input.value = val;
										input.dispatchEvent(new Event("change"));
									}
								} else {
									input.value = val;
								}
							}
						}
					}, 50);
				}
				return;
			}

		});
	});
	document.querySelectorAll(".add-btn").forEach(btn => {
		btn.addEventListener("click", async function() {
			// 1. section, type 등 기본 변수 셋업
			const btnId = btn.id;
			if (!btnId.startsWith('btn-')) return;
			const type = btnId.replace('btn-', '');
			if (!type) return;
			const section = document.querySelector(`#section-${type}`);
			if (!section) return;
			const formContainer = section.querySelector('.formContainer');
			const addBtn = section.querySelector('.add-btn');
			const secCont = section.querySelector('.section-content');
			if (!formContainer || !secCont) return;
			// 인덱스 관리
			let idx = typeCounters[type] || 0;
			const templateFunc = templateMap[type];
			if (!templateFunc) {
				alert(`${type} 폼은 준비중입니다.`);
				return;
			}
			// 폼 추가
			formContainer.insertAdjacentHTML('beforeend', templateFunc(idx));
			typeCounters[type] = idx + 1;

			if (addBtn.closest("#section-careerList")) {
				// 경력 - 회사명 DB에 등록된 회사인지 확인 후 VO에 SET 해줌
				document.querySelector("#comId").addEventListener("input", function() {
					const inputVal = this.value.trim();
					const suggestionBox = document.querySelector("#comSuggestions");
					suggestionBox.innerHTML = ""; // 초기화

					if (!inputVal) {
						this.removeAttribute("data-userid"); // 입력값 없을 때 속성 제거
						return;
					}

					const matches = companyList
						.filter(c => c.comName.includes(inputVal))
						.slice(0, 5); // 최대 5개

					let exactMatchFound = false;

					matches.forEach(c => {
						const li = document.createElement("li");
						li.innerText = c.comName;
						li.dataset.userid = c.userId;
						li.classList.add("list-group-item", "list-group-item-action");
						li.style.maxWidth = "301px"

						// 클릭 시 input 값과 data 설정
						li.addEventListener("click", () => {
							const inputEl = document.querySelector("#comId");
							inputEl.value = c.comName;
							inputEl.dataset.userid = c.userId; // userId 설정
							suggestionBox.innerHTML = "";
						});

						suggestionBox.appendChild(li);

						// 입력값이 정확히 일치하는 항목 확인
						if (c.comName === inputVal) {
							this.dataset.userid = c.userId;
							exactMatchFound = true;
						}
					});

					// 정확히 일치하는 항목이 없는 경우 userId 속성 제거
					if (!exactMatchFound) {
						this.removeAttribute("data-userid");
					}
				});
			}

			// 경력 - 회사명 DB에 등록된 회사인지 확인 후 VO에 SET 해줌
			document.addEventListener("DOMContentLoaded", function() {
				const searchButton = document.querySelector(".search_com_btn");
				if (searchButton) {
					const searchButton = document.querySelector(".search_com_btn");
					searchButton.addEventListener("click", function(e) {
						const comId = document.querySelector("#comId");
						const userId = comId.dataset.userid;
						const userName = comId.value;
						console.log(userId)
						console.log(userName)
						axios.get(`/ajax/career/company/${userId}`)
							.then(resp => {
								const comSearchMsg = document.querySelector("#comSearchMsg");
								console.log(resp.data)
								if (resp.data.success == true) {
									comSearchMsg.innerHTML = `${userName}는 입력 가능한 회사입니다.`;
									comSearchMsg.classList.remove("text-danger")
									comSearchMsg.classList.add("text-primary", "fs-14")
								} else {
									comId.value = "";
									comSearchMsg.innerHTML = `${resp.data.message}`;
									comSearchMsg.classList.remove("text-primary")
									comSearchMsg.classList.add("text-danger")
								}
							})
							.catch(err => {
								console.error(err);
							})
					})
				} else {
					console.warn("search_com_btn 버튼을 찾을 수 없습니다.");
				}
			})

			// section-content, +버튼 숨김
			secCont.style.display = "none";
			if (addBtn) addBtn.style.display = 'none';
			const newForm = formContainer.lastElementChild;
			await setDynamicSelects(type, newForm);

			// formContainer 내 이벤트 위임 - 중복 바인딩 방지
			if (!formContainer.hasAttribute('data-bound')) {
				formContainer.setAttribute('data-bound', 'true');
				formContainer.addEventListener("click", function(e) {


					// 확인 버튼 클릭
					if (e.target.classList.contains('save_btn')) {
						const formWrap = e.target.closest('.section-form-wrap');
						if (!formWrap) return;
						const thisType = formWrap.id.split('-')[1].replace(/\d+$/, '');
						const section = formWrap.closest('.section');
						const addBtn = section.querySelector('.add-btn');
						const listContainer = section.querySelector('.listContainer');

						// ✅ 필수 입력값 검사
						const requiredLabels = formWrap.querySelectorAll("label.required");
						for (const label of requiredLabels) {
							const row = label.closest(".section-form-row");
							if (!row) continue;

							const inputEl = row.querySelector("input, select, textarea");
							if (inputEl && !inputEl.value.trim()) {
								alert(`"${label.innerText}" 항목은 필수 입력 항목입니다. 값을 입력해주세요.`);
								inputEl.focus();
								return; // ⛔ 값이 없으면 아래 로직 실행 안 됨
							}
						}


						// 입력값 추출
						let data = {};
						formWrap.querySelectorAll("input, select, textarea").forEach(el => {
							const fullName = el.name;
							if (!fullName) return;

							// key 파싱 (ex: resumeVO.educationList[0].graduateYn -> graduateYn)
							const nameParts = fullName.split('.');
							const key = nameParts[nameParts.length - 1].replace(/\[\d+\]/g, '');
							data[key] = el.value;
							console.log("디버깅용 ▶ name:", el.name, "▶ value:", el.value);
						});

						// JS JSON 객체에 넘기기 위한 반복문 코드
						formWrap.querySelectorAll("input, select, textarea").forEach(input => {
							const fromDataName = input.name.split('.').pop().replace(/\[\d+\]/, '');
							data[fromDataName] = input.value;
						});

						// 위에서 만든 변수들을 vo 형태에 맞게 세팅할 수 있게 미리만들어둔 함수에 넘겨줌
						const vo = makeVO(thisType, data);
						const key = arrayKey[thisType];
						const isEdit = formWrap.dataset.edit === "true";
						const idx = parseInt(formWrap.dataset.idx || 0, 10);

						// resume 객체 갱신
						if (isEdit) {
							resume[key][idx] = vo;
						} else {
							resume[key].push(vo);
							typeCounters[thisType] = resume[key].length;
							console.log(resume)
						}

						// 리스트 아이템 갱신
						const listItemHtml = listItemMap[thisType](idx, data, thisType);
						const listItem = listContainer.querySelector(`[data-idx="${idx}"]`);

						if (isEdit && listItem) {
							listItem.outerHTML = listItemHtml;
						} else {
							listContainer.insertAdjacentHTML('beforeend', listItemHtml);
						}

						// 폼 제거, +버튼 복원
						formWrap.style.display = 'none';
						if (addBtn) addBtn.style.display = '';

						// 반드시 리스트 보여주기!
						listContainer.classList.remove('d-none');

						// section-content는 계속 숨김
						const secCont = section.querySelector('.section-content');
						if (secCont) secCont.style.display = "none";
					}



					// 취소 버튼 클릭
					if (e.target.classList.contains('btn_red_line')) {
						const formWrap = e.target.closest('.section-form-wrap');
						if (!formWrap) return;
						const matches = formWrap.id.match(/^form-([a-zA-Z]+)\d*$/);
						const thisType = matches?.[1];
						console.log("추출된 type:", thisType);
						if (!thisType) {
							console.warn("formWrap ID에서 type 추출 실패:", formWrap.id);
							return;
						}

						const section = document.querySelector(`#section-${thisType}`);
						const listContainer = section.querySelector('.listContainer');
						const secCont = section.querySelector('.section-content');
						const addBtn = section.querySelector('.add-btn');
						formWrap.remove();
						typeCounters[thisType] = Math.max((typeCounters[thisType] || 1) - 1, 0);

						// ★ [핵심!] listContainer에 아이템이 있으면 listContainer 보여주기!
						const hasList = listContainer.querySelectorAll('div').length > 0;
						if (hasList) {
							listContainer.classList.remove('d-none');
							if (secCont) secCont.style.display = 'none';
						} else {
							// 아이템 없으면 section-content 복원, 리스트 숨김
							if (secCont) secCont.style.display = '';
							listContainer.classList.add('d-none');
						}
						if (addBtn) addBtn.style.display = '';
					}
				});
			}
		});
	});
});

// ↓↓↓ listContainer 영역에서 삭제 버튼클릭 이벤트
// 모달 열릴 때 삭제할 타입/idx를 삭제 버튼에 세팅
document.addEventListener("DOMContentLoaded", function() {

	// 모달 내 삭제 버튼 클릭 시 진짜 삭제 실행
	document.querySelector("#deleteResumeList .btn-danger").addEventListener("click", function() {
		const modal = document.getElementById("deleteResumeList");
		const type = modal.dataset.type;
		const idx = parseInt(modal.dataset.idx);

		// 🔁 introduction은 단일 객체니까 따로 분기
		if (type === "introduction") {
			resume.introduction = {};

			const section = document.querySelector("#section-introduction");
			const listContainer = section.querySelector(".listContainer");
			listContainer.innerHTML = '';
			listContainer.classList.add("d-none");

			const sectionContent = section.querySelector(".section-content");
			if (sectionContent) sectionContent.classList.remove("d-none");

			const addBtn = section.querySelector(".btn-introduction");
			if (addBtn) addBtn.classList.remove("d-none");

			bootstrap.Modal.getInstance(modal)?.hide();
			return;
		}

		const key = arrayKey[type];
		if (!key || !Array.isArray(resume[key])) return;

		// 실제 데이터 삭제
		resume[key].splice(idx, 1);

		// type에 해당하는 섹션 내 listContainer 찾아서 갱신
		const section = document.querySelector(`#section-${type}`);
		const listContainer = section.querySelector('.listContainer');

		// 해당 list-item 제거
		const itemToRemove = listContainer.querySelector(`.list-item[data-idx="${idx}"]`);
		if (itemToRemove) itemToRemove.remove();

		// 인덱스 재정렬
		const items = listContainer.querySelectorAll(".list-item");
		items.forEach((el, i) => {
			el.dataset.idx = i;
			// 여기서 버튼의 data-idx도 갱신해줘야 안전해
			const btnDel = el.querySelector(".btn_del");
			if (btnDel) btnDel.dataset.idx = i;
		});

		// 카운터 보정
		typeCounters[type] = resume[key].length;

		// 리스트가 0개면 listContainer 숨김 + section-content 노출
		if (resume[key].length === 0) {
			listContainer.classList.add("d-none");
			const sectionContent = section.querySelector(".section-content");
			if (sectionContent) sectionContent.style.display = "";
		}

		// 모달 닫기
		const modalInstance = bootstrap.Modal.getInstance(modal);
		if (modalInstance) modalInstance.hide();
	});

	// 삭제 버튼 클릭 시 모달에 타입과 인덱스 주입
	document.addEventListener("click", function(e) {
		const delBtn = e.target.closest(".btn_del");
		if (delBtn) {
			const modal = document.getElementById("deleteResumeList");
			modal.dataset.type = delBtn.dataset.type;
			modal.dataset.idx = delBtn.dataset.idx;
		}
	});
});
// end 삭제버튼 클릭 영역

// 이력서 기본정보 수정로직 ====================================================================================================================
// 공통 필드 배열
const basicFields = mode === "update"
	? [
		{ key: "userName", inputId: "inputUserName", viewId: "viewName", hiddenId: "hiddenUserName" },
		{ key: "birth", inputId: "inputBirth", viewId: "viewBirth", hiddenId: "hiddenBirth" },
		{ key: "email", inputId: "inputEmail", viewId: "viewEmail", hiddenId: "hiddenEmail" },
		{ key: "tel", inputId: "inputTel", viewId: "viewTel", hiddenId: "hiddenTel" },
		{ key: "address", inputId: "inputAddress", viewId: "viewAddress", hiddenId: "hiddenAddress" }
	]
	: [
		{ key: "memName", inputId: "inputUserName", viewId: "viewName", hiddenId: "hiddenUserName" },
		{ key: "memBir", inputId: "inputBirth", viewId: "viewBirth", hiddenId: "hiddenBirth" },
		{ key: "memEmail", inputId: "inputEmail", viewId: "viewEmail", hiddenId: "hiddenEmail" },
		{ key: "memTel", inputId: "inputTel", viewId: "viewTel", hiddenId: "hiddenTel" },
		{ key: "memAddress", inputId: "inputAddress", viewId: "viewAddress", hiddenId: "hiddenAddress" }
	];



// ✅ 초기 바인딩
window.addEventListener("DOMContentLoaded", () => {
	const resumeEl = document.getElementById("resumeData");
	if (resumeEl) {
		resume.userId = resumeEl.dataset.userId;
		resume.userName = resumeEl.dataset.userName;
		resume.birth = resumeEl.dataset.birth;
		resume.email = resumeEl.dataset.email;
		resume.tel = resumeEl.dataset.tel;
		console.log("유저네임 잘 가져오는지 확인 !!!" + resumeEl.dataset.userName);
		console.log(resume.userName);

		// 주소는 구분자로 나눈 뒤 합쳐서 1개로 resume에 저장
		const addressParts = resumeEl.dataset.address.split("||");
		const address1 = addressParts[0] || "";
		const address2 = addressParts[1] || "";
		resume.address = `${address1} ${address2}`.trim();
	}
if (mode === "create") {
  const resumeData = document.getElementById("resumeData")?.dataset;
  if (resumeData) {
    resume.userName = resumeData.userName;
    resume.birth = resumeData.birth;
    resume.email = resumeData.email;
    resume.tel = resumeData.tel;
    const [addr1, addr2] = resumeData.address.split("||");
    resume.address = `${addr1 || ""} ${addr2 || ""}`.trim();
  }
}

	// input 요소에 resume 값 세팅
	basicFields.forEach(field => {
		const value = resume[field.key] || "";
		if (Array.isArray(field.inputId)) {
			const parts = value.split(" ");
			field.inputId.forEach((id, idx) => {
				document.getElementById(id).value = parts[idx] || "";
				console.log(id)
				console.log(parts[idx])

			});
		} else {
			document.getElementById(field.inputId).value = value;
		}
	});
	
	
	// 버튼 이벤트 등록
	const editBtn = document.getElementById("resumeEditBtn");


	editBtn.addEventListener("click", () => {
		document.getElementById("resumeInfoView").classList.add("d-none");

		const container = document.getElementById("resumebasicInfoWrap");
		container.innerHTML = templateMap["basicInfo"]();
		container.classList.remove("d-none");

		// 기본정보 바인딩
		basicFields.forEach(field => {
			const value = resume[field.key] || "";
			const input = document.getElementById(field.inputId);
			if (input) input.value = value;
		});


		// 저장, 취소 버튼 이벤트 등록
		document.getElementById("cancelBasicBtn").addEventListener("click", () => {
			container.classList.add("d-none");
			document.getElementById("resumeInfoView").classList.remove("d-none");
		});

		document.getElementById("saveBasicBtn").addEventListener("click", () => {
			basicFields.forEach(field => {
				const value = document.getElementById(field.inputId)?.value.trim() || "";
				resume[field.key] = value;
				document.getElementById(field.hiddenId).value = value;
				document.getElementById(field.viewId).textContent = value;
			});

			container.classList.add("d-none");
			document.getElementById("resumeInfoView").classList.remove("d-none");
		});
	});
});






// 자소서 선택 로직 ====================================================================================================================
document.addEventListener("DOMContentLoaded", function() {
	const modalEl = document.getElementById("introductionModal");

	// 1. 모달 열릴 때 셀렉트 옵션 비동기 로딩
	modalEl.addEventListener("show.bs.modal", async () => {
		const selectEl = document.getElementById("introductionSelect");
		selectEl.innerHTML = `<option value="">자기소개서를 선택하세요</option>`;

		try {
			const resp = await axios.get("/ajax/introduction/list");
			const introList = resp.data || [];

			introList.forEach(item => {
				const opt = document.createElement("option");
				opt.value = item.introductionNo;
				opt.textContent = item.introductionName;
				selectEl.appendChild(opt);
			});
		} catch (err) {
			console.error("자기소개서 목록 불러오기 실패", err);
			alert("자기소개서를 불러오는 데 실패했습니다.");
		}
	});

	// 2. 확인 버튼 클릭 → 선택한 값만 resume.introduction에 저장
	const confirmBtn = document.getElementById("confirmIntroduction");

	if (confirmBtn) {
		confirmBtn.addEventListener("click", function() {
			const selectEl = document.getElementById("introductionSelect");
			const selectedNo = selectEl.value;
			const selectedText = selectEl.options[selectEl.selectedIndex]?.textContent;

			if (!selectedNo) {
				alert("자기소개서를 선택해주세요.");
				return;
			}

			// ✅ VO에 introductionNo만 저장
			resume.introductionNo = selectedNo;

			// ✅ UI 리스트에 표시
			const section = document.querySelector("#section-introduction");
			const listContainer = section.querySelector(".listContainer");

			const html = `
        <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2 w-100">
          <div>
            <strong>${selectedText}</strong>
            <div class="text-secondary fs-14"></div>
          </div>
					<div class="d-flex gap-2">
			      <button type="button" class="btn_edit" data-type="introduction">
			        <span class="material-symbols-outlined fs-3">stylus</span>
			      </button>
			      <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="introduction" data-idx="0">
			        <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
			      </button>
			    </div>
        </div>
      `;
			listContainer.innerHTML = html;
			listContainer.classList.remove("d-none");

			section.querySelector(".section-content")?.classList.add("d-none");
			section.querySelector(".btn-introduction")?.classList.add("d-none");

			// ✅ 모달 닫기
			bootstrap.Modal.getInstance(modalEl)?.hide();
		});
	}

	// 수정
	document.addEventListener("click", function(e) {
		const editBtn = e.target.closest(".btn_edit");
		if (!editBtn) return;

		const type = editBtn.dataset.type;
		if (type !== "introduction") return;

		const modalEl = document.getElementById("introductionModal");
		const selectEl = document.getElementById("introductionSelect");

		// 모달 객체 안전하게 가져오기
		let modal = bootstrap.Modal.getInstance(modalEl);
		if (!modal) {
			modal = new bootstrap.Modal(modalEl);
		}
		modal.show();

		// ✅ option 비동기로 로드 + 선택된 값 세팅
		axios.get("/ajax/introduction/list").then(resp => {
			const introList = resp.data || [];

			// select 초기화
			selectEl.innerHTML = `<option value="">자기소개서를 선택하세요</option>`;

			// option 채우기
			introList.forEach(item => {
				const opt = document.createElement("option");
				opt.value = item.introductionNo;
				opt.textContent = item.introductionName;
				selectEl.appendChild(opt);
			});

			// ✅ 기존 선택값 세팅
			if (resume.introductionNo) {
				selectEl.value = String(resume.introductionNo);
			}

		}).catch(err => {
			console.error("자기소개서 리스트 로딩 실패", err);
			alert("자기소개서 목록을 불러오지 못했습니다.");
		});
	});

	// 삭제
	document.querySelector(".btn_red_line").addEventListener("click", function() {
		// 1. resume 객체에서 introduction 제거
		resume.introductionNo = "";

		// 2. 리스트 UI 초기화
		const section = document.querySelector("#section-introduction");
		const listContainer = section.querySelector(".listContainer");
		const addBtn = section.querySelector(".btn-introduction");
		const sectionContent = section.querySelector(".section-content");

		listContainer.innerHTML = '';
		listContainer.classList.add("d-none");

		sectionContent?.classList.remove("d-none");
		addBtn?.classList.remove("d-none");

		// 3. 삭제 확인 모달 닫기 (선택)
		const modalEl = document.getElementById("deleteResumeList");
		bootstrap.Modal.getInstance(modalEl)?.hide();
	});


});




// 모든 빈 문자열 "" 제거
function removeEmptyStrings(obj) {
	if (Array.isArray(obj)) {
		return obj.map(removeEmptyStrings).filter(item => item && Object.keys(item).length > 0);
	} else if (typeof obj === 'object' && obj !== null) {
		const newObj = {};
		for (const key in obj) {
			const value = obj[key];

			if (value === '') {
				continue; // 빈 문자열 제거
			}

			// 중첩 객체/배열은 재귀로 처리
			if (typeof value === 'object') {
				const cleaned = removeEmptyStrings(value);
				if (cleaned && (typeof cleaned !== 'object' || Object.keys(cleaned).length > 0)) {
					newObj[key] = cleaned;
				}
			} else {
				newObj[key] = value;
			}
		}
		return newObj;
	}

	return obj;
}

// resume 기본정보로 insert 하기!
function setBasicResumeInfo() {
	const resumeForm = document.querySelector("#form-resume");
	if (!resumeForm) return;

	const resumeInputs = resumeForm.querySelectorAll("input");
	resumeInputs.forEach(input => {
		const resumeName = input.name;
		const resumeValue = input.value;

		// 값이 ""인 경우는 무시하고 덮어쓰지 않도록!
		if (resumeName && resumeValue !== "") {
			resume[resumeName] = resumeValue;
		}
	});
}


// 전송하기 버튼 눌렀을 떄 insert 진행 로직 !
document.addEventListener("submit", function(e) {
	const form = e.target;
	if (!form.matches("#resume_form")) return; // ✅ 특정 폼만 처리하고 나머지는 무시
	e.preventDefault(); // ✅ 기본 폼 제출 막기
	setBasicResumeInfo();	// 기존 이력서 hidden태그 가져와서 insert 하기


	// 학력 입력 여부 확인! 위에서 관리하는 resume 객체의 educationList가 없거나, 길이가 1보다 작으면 return
	if (!resume.educationList || resume.educationList.length < 1) {
		alert("[학력]은 필수 기입란 입니다.");
		return; // ✅ 전송 중단
	}

	if (!form.querySelector("#section-introduction .listContainer > div")) {
		alert("[자기소개서]은 필수 기입란 입니다.");
		return; // ✅ 전송 중단
	}


	const cleanedResume = removeEmptyStrings(resume);

	const formData = new FormData();

	// resume 객체를 JSON 문자열로 묶어서 추가
	formData.append("resume", new Blob([JSON.stringify(cleanedResume)], {
		type: "application/json"
	}));

	// photo가 File 타입일 때만 첨부
	if (resume.photo instanceof File) {
		formData.append("photo", resume.photo);
	}

	if (resume.company?.comImage instanceof File) {
		formData.append("comImage", resume.company.comImage);
	}

	console.log(resume)

	// axios 비동기 전송
	axios.post("/mypage/resume/create", formData, {
		headers: {
			"Content-Type": "multipart/form-data"
		}
	})
		.then(resp => {
			console.log(resp.data);
			if (resp.data === "ok") {
				alert("정상적으로 등록되었습니다.");
				window.location.href = "/mypage/resume/list";
			} else {
				alert("이력서 등록에 실패했습니다. 다시 시도해주세요.");
				console.error("서버로부터 받은 응답", resp.data);
			}
		})
		.catch(err => {
			alert("이력서 등록 중 오류가 발생했습니다.");
			console.error("Error creating resume:", err);

			const { errors, vo } = err.response.data;

			// ✅ 1. 리스트 타입 에러 모아서 index 정리
			const listMap = {}; // 예: { educationList: Set(0,2), careerList: Set(1) }
			errors.forEach(e => {
				const match = e.field.match(/^(\w+)\[(\d+)]\.(\w+)$/);
				if (match) {
					const listName = match[1];
					const index = parseInt(match[2]);
					if (!listMap[listName]) listMap[listName] = new Set();
					listMap[listName].add(index);
				}
			});

			// ✅ 2. 실패 index만 resume 객체에서 제거
			for (const [listName, indexSet] of Object.entries(listMap)) {
				if (!Array.isArray(vo[listName])) continue;
				const sorted = Array.from(indexSet).sort((a, b) => b - a);
				sorted.forEach(idx => {
					vo[listName].splice(idx, 1);  // 💥 vo에서 제거
				});
			}

			// ✅ 3. 나머지 resume 데이터 덮어쓰기
			Object.assign(resume, vo);

			// ✅ 4. 에러 메시지 UI에 뿌리기
			errors.forEach(e => {
				const field = e.field;
				const message = e.message;

				const match = field.match(/^(\w+)\[(\d+)]\.(\w+)$/);

				if (match) {
					const listName = match[1];
					const index = parseInt(match[2]);
					const fieldName = match[3];
					// console.log("에러로그 찍기 >>> " + listName)

					// 폼 요소 찾기
					const formEl = document.querySelector(`#form-${listName}${index}`);
					if (formEl) {
						const inputEl = formEl.querySelector(`[name$='.${fieldName}']`);
						if (inputEl) {
							// ✅ inputEl 감싸는 새로운 div 생성
							const wrapper = document.createElement('div');
							wrapper.classList.add('input-wrapper', 'w-100'); // 필요한 class 있으면 추가

							// ✅ inputEl을 div (wrapper)로 감싸기
							inputEl.parentElement.insertBefore(wrapper, inputEl);
							wrapper.appendChild(inputEl);
							inputEl.classList.add("w-100")

							// 기존 에러 제거
							const existingError = wrapper.querySelector(`.${fieldName}-error`);
							if (existingError) existingError.remove();

							// 새 에러 span 추가
							const errorSpan = document.createElement('span');
							errorSpan.className = `${fieldName}-error text-danger d-block mt-1 fs-14`;
							errorSpan.innerText = message;

							wrapper.appendChild(errorSpan);
						}


						// 폼 보이게 처리
						formEl.style.display = "flex";
					}

					// 기존 list item 제거
					const listContainer = document.querySelector(`#section-${listName} .listContainer`);
					if (listContainer) {
						const item = listContainer.querySelector(`[data-idx="${index}"]`);
						if (item) item.remove();
					}


				} else {
					// 단일 필드 처리 (userId 등)
					const inputEl = document.querySelector(`[name='${field}']:not([type="hidden"])`);
					if (inputEl) {
						// ✅ inputEl 감싸는 새로운 div 생성
						const wrapper = document.createElement('div');
						wrapper.classList.add('input-wrapper', 'w-100'); // 필요한 class 있으면 추가

						// ✅ inputEl을 div (wrapper)로 감싸기
						inputEl.parentElement.insertBefore(wrapper, inputEl);
						wrapper.appendChild(inputEl);
						inputEl.classList.add("w-100")

						// 기존 에러 제거
						const existingError = inputEl.parentElement.querySelector(`.${field}-error`);
						if (existingError) existingError.remove();

						const errorSpan = document.createElement('span');
						errorSpan.className = `${field}-error text-danger d-block mt-1 fs-14`;
						errorSpan.innerText = message;
						wrapper.appendChild(errorSpan);
					}

					const basicInfoSection = document.querySelector("#resumebasicInfoWrap");
					// 기존 이력서 view 제거
					const resumeInfoView = document.querySelector("#resumeInfoView");

					console.log(basicInfoSection);
					console.log(field)
					// 에러로 올 수 필드명 관리
					const basicInfoFields = ["userId", "resumeName", "resumeMainYn", "userName", "photo", "birth", "email", "tel", "address", "veteranReason", "updateDate"];
					// 이력서 기본정보 - basicInfo 관련 필드 에러가 있으면 섹션 보이게 하기
					if (basicInfoFields.includes(field)) {
						if (basicInfoSection?.classList.contains("d-none")) {
							basicInfoSection.classList.remove("d-none");
							resumeInfoView.classList.add("d-none")
						}
					}
				}
			});
		});
});


// 수정모드일 때 사용할 공통코드들!
async function preloadCodeGroups(groups = []) {
	await Promise.all(groups.map(async group => {
		const url = `/ajax/code/cmncodegroup/${group}`;
		try {
			const resp = await axios.get(url);
			const list = resp.data.cmnCodeList || [];
			codeLabelMap[group] = {};
			list.forEach(i => {
				codeLabelMap[group][i.codeDetailNo] = i.codeName;
			});
		} catch (err) {
			console.warn(`[${group}] 코드 로딩 실패`, err);
		}
	}));
}



window.addEventListener("DOMContentLoaded", async () => {
	if (typeof mode !== "undefined" && mode === "update" && typeof resumeFromServer !== "undefined") {

		// ✅ 여기서 먼저 코드 그룹 로딩이 끝날 때까지 기다리기
		await preloadCodeGroups(["GRAD", "EDUC", "SPEC", "VULN", "DSBL", "LANG", "MILT", "SRVC"]);

		Object.assign(resume, resumeFromServer);

		// ✅ 자기소개서 존재 여부 체크
		if (resume.introductionNo && !resume.introduction) {
			alert("연결된 자기소개서가 삭제되었습니다. 다시 선택해 주세요.");
			// 예: 자소서 영역 리셋 or 비우기
			const introContainer = document.querySelector("#section-introduction .listContainer");
			if (introContainer) {
				introContainer.innerHTML = "";
				introContainer.classList.add("d-none");
			}
			const introSection = document.querySelector("#section-introduction");
			if (introSection) {
				introSection.querySelector(".section-content")?.classList.add("d-none");
				introSection.querySelector(".btn-introduction")?.classList.add("d-none");
			}
			// → 사용자에게 다시 선택하게 하기
		} else if (resume.introductionNo && resume.introduction) {
			// ✅ 자소서가 존재하면 렌더링
			const introContainer = document.querySelector("#section-introduction .listContainer");
			introContainer.classList.remove("d-none");
			introContainer.innerHTML = `
        <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2 w-100">
          <div>
            <strong>${resume.introduction?.introductionName || '자기소개서'}</strong>
          </div>
          <div class="d-flex gap-2">
            <button type="button" class="btn_edit" data-type="introduction">
              <span class="material-symbols-outlined fs-3">stylus</span>
            </button>
            <button type="button" class="btn_del" data-bs-toggle="modal" data-bs-target="#deleteResumeList" data-type="introduction" data-idx="0">
              <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
            </button>
          </div>
        </div>
      `;
			const introSection = document.querySelector("#section-introduction");
			if (introSection) {
				introSection.querySelector(".section-content")?.classList.add("d-none");
				introSection.querySelector(".btn-introduction")?.classList.add("d-none");
			}
		}

		// 기본정보 바인딩
		basicFields.forEach(field => {
			const value = resume[field.key] || "";
			const input = document.getElementById(field.inputId);
			const view = document.getElementById(field.viewId);
			if (input) input.value = value;
			if (view) view.textContent = value;
		});

		// 리스트 타입 항목들 렌더링
		for (const [key, arr] of Object.entries(resume)) {
			if (!Array.isArray(arr)) continue;
			const listType = key;
			const container = document.querySelector(`#section-${listType} .listContainer`);
			if (!container) continue;
			container.innerHTML = '';
			container.classList.remove("d-none");

			arr.forEach((item, idx) => {
				const html = listItemMap[listType]?.(idx, item, listType);
				if (html) container.insertAdjacentHTML("beforeend", html);
			});

			typeCounters[listType] = arr.length;

			const secCont = document.querySelector(`#section-${listType} .section-content`);
			const addBtn = document.querySelector(`#section-${listType} .add-btn`);
			if (secCont) secCont.style.display = 'none';
			if (addBtn) addBtn.style.display = '';
		}

	}
});