// 각 form 몇개 추가 됐는지 카운트
const typeCounters = {
  edu: 0,
  career: 0,
  skill: 0,
  exp: 0,
  support: 0,
  license: 0,
  language: 0,
  award: 0,
  portfolio: 0,
  selfintro: 0,
  military: 0
};

/* + 추가 버튼 클릭 시 등록 form 추가 List */
const templateMap = {
  edu: idx => `
    <div class="section-form-wrap" id="form-edu${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>최종 학력</label>
        <select name="resumeVO.educationList[${idx}].highestEducationCode" required id="highestEducationCode${idx}">
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label>학교명</label>
        <input type="text" name="resumeVO.educationList[${idx}].schoolName" placeholder="예: 서울대학교" maxlength="150" required>
      </div>
      <div class="section-form-row">
        <label>졸업여부</label>
		<select name="resumeVO.educationList[${idx}].graduateYn" id="graduateYn${idx}" data-idx="${idx}">
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label>편입여부</label>
        <div class="form-check-wrap d-flex">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].transferYn" value="Y" checked>
            <label class="form-check-label">편입</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].transferYn" value="N">
            <label class="form-check-label">비편입</label>
          </div>
        </div>
      </div>
      <div class="section-form-row">
        <label>입학일자</label>
        <input type="date" name="resumeVO.educationList[${idx}].entranceDate">
      </div>
      <div class="section-form-row">
        <label>졸업일자</label>
        <input type="date" name="resumeVO.educationList[${idx}].graduateDate">
      </div>
      <div class="section-form-row">
        <label>지역</label>
        <input type="text" name="resumeVO.educationList[${idx}].location">
      </div>
      <div class="section-form-row">
        <label>학과</label>
        <input type="text" name="resumeVO.educationList[${idx}].departmentCode" placeholder="예: CSE01" maxlength="12">
      </div>
      <div class="section-form-row w-100">
        <label>주전공</label>
        <input type="text" name="resumeVO.educationList[${idx}].specialtyList[0].mainMajor" placeholder="주전공 학과를 입력해주세요." maxlength="12">
      </div>
      <div class="section-form-row">
        <label>전공 구분</label>
        <select name="resumeVO.educationList[${idx}].specialtyList[0].subMajorCode" id="majorTypeCode${idx}" data-idx="${idx}">
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label>부전공</label>
        <input type="text" name="resumeVO.educationList[${idx}].specialtyList[0].subMajor" placeholder="부전공 학과를 입력해주세요." maxlength="12">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  career: idx => `
    <div class="section-form-wrap" id="form-career${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>회사명</label>
        <div class="d-flex gap-2 w-100">
          <input type="text" name="resumeVO.careerList[${idx}].comId" placeholder="예: NAVER" maxlength="100" required>
          <button class="btn save_btn btn_violet_line">확인</button>
        </div>
      </div>
      <div class="section-form-row">
        <label>직무</label>
        <select name="resumeVO.careerList[${idx}].jobCode" required id="jobCode${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>입사일자</label>
        <input type="date" name="resumeVO.careerList[${idx}].startWorkDate" required>
      </div>
      <div class="section-form-row">
        <label>퇴사일자</label>
        <input type="date" name="resumeVO.careerList[${idx}].retireDate">
      </div>
      <div class="section-form-row">
        <label>재직여부</label>
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
        <label>근무부서</label>
        <input type="text" name="resumeVO.careerList[${idx}].department" placeholder="예: 개발팀" maxlength="100">
      </div>
      <div class="section-form-row">
        <label>담당업무</label>
        <input type="text" name="resumeVO.careerList[${idx}].responsibility" placeholder="예: 백엔드 개발 및 유지보수" maxlength="255">
      </div>
      <div class="section-form-row">
        <label>프리랜서 여부</label>
        <div class="form-check-wrap d-flex">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].freelancer" value="Y">
            <label class="form-check-label">프리랜서</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.careerList[${idx}].freelancer" value="N" checked>
            <label class="form-check-label">정규직/계약직</label>
          </div>
        </div>
      </div>
      <div class="section-form-row">
        <label>직급</label>
        <select name="resumeVO.careerList[${idx}].jobGradeCode"id="jobGradeCode${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>직책</label>
        <select name="resumeVO.careerList[${idx}].positionCode" id="positionCode${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>연차</label>
        <select name="resumeVO.careerList[${idx}].careerYear" id="careerYear${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>연봉</label>
        <input type="text" name="resumeVO.careerList[${idx}].salary" placeholder="예: 4000만원" maxlength="30">
      </div>
      <div class="section-form-row">
        <label>근무지역</label>
        <input type="text" name="resumeVO.careerList[${idx}].location" placeholder="예: 서울" maxlength="100">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  skill: idx => `
    <div class="section-form-wrap" id="form-skill${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>보유기술명</label>
        <input type="text" name="resumeVO.mySkillList[${idx}].mySkillName" placeholder="예: Java, Spring, React" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  exp: idx => `
    <div class="section-form-wrap" id="form-exp${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>경험구분</label>
        <select name="resumeVO.myExperienceList[${idx}].expCode" required id="expCode${idx}">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>경험명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].expName" placeholder="예: 네이버 인턴십" maxlength="255" required>
      </div>
      <div class="section-form-row">
        <label>시작일자</label>
        <input type="date" name="resumeVO.myExperienceList[${idx}].expStartDate" required>
      </div>
      <div class="section-form-row">
        <label>종료일자</label>
        <input type="date" name="resumeVO.myExperienceList[${idx}].expEndDate">
      </div>
      <div class="section-form-row">
        <label>기관명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].organizationName" placeholder="예: 네이버" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  support: idx => `
    <div class="section-form-wrap" id="form-support${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>고용지원대상</label>
        <select name="resumeVO.supportList[${idx}].disabilityCode" id="disabilityCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>장애등급</label>
        <select name="resumeVO.supportList[${idx}].disabilityLevelCode" id="disabilityLevelCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  license: idx => `
    <div class="section-form-wrap" id="form-license${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>자격증명</label>
        <input type="text" name="resumeVO.myLicenseList[${idx}].licenseCode" required placeholder="자격증 명을 입력해주세요.">
      </div>
      <div class="section-form-row">
        <label>취득일자</label>
        <input type="date" name="resumeVO.myLicenseList[${idx}].licensePassDate" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  language: idx => `
    <div class="section-form-wrap" id="form-language${idx}" data-idx="${idx}">
	  <div class="section-form-row">
	    <label>어학명</label>
	    <select name="resumeVO.languageSkillList[${idx}].languageCode" id="languageCode${idx}" required>
	      <option value="">선택</option>
	    </select>
	  </div>
      <div class="section-form-row">
        <label>시험 구분</label>
        <select name="resumeVO.languageSkillList[${idx}].languageExamCode" id="languageExamCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>시험명</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamName" placeholder="예: TOEIC, JLPT N1" maxlength="255" required>
      </div>
      <div class="section-form-row">
        <label>취득일자</label>
        <input type="date" name="resumeVO.languageSkillList[${idx}].passDate" required>
      </div>
      <div class="section-form-row">
        <label>시험유형</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamType" placeholder="예: 정기/특별" maxlength="10">
      </div>
      <div class="section-form-row">
        <label>시험점수</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamScore" placeholder="예: 900" maxlength="10">
      </div>
      <div class="section-form-row">
        <label>급수(레벨)</label>
        <input type="text" name="resumeVO.languageSkillList[${idx}].languageExamLevelCode" placeholder="레벨을 입력해주세요. 예) N5">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  award: idx => `
    <div class="section-form-wrap" id="form-award${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>수상명</label>
        <input type="text" name="resumeVO.awardList[${idx}].awardName" placeholder="예: 프로그래밍 경진대회 대상" maxlength="255" required>
      </div>
      <div class="section-form-row">
        <label>수상일자</label>
        <input type="date" name="resumeVO.awardList[${idx}].awardDate" required>
      </div>
      <div class="section-form-row">
        <label>주최기관</label>
        <input type="text" name="resumeVO.awardList[${idx}].hosting" placeholder="예: 한국정보화진흥원" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  portfolio: idx => `
    <div class="section-form-wrap" id="form-portfolio${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>포트폴리오 이름</label>
        <input type="text" name="resumeVO.portfolioList[${idx}].porName" placeholder="예: 쇼핑몰 구축 프로젝트" maxlength="60" required>
      </div>
      <div class="section-form-row">
        <label>포트폴리오 URL</label>
        <input type="url" name="resumeVO.portfolioList[${idx}].porUrl" placeholder="예: https://github.com/username/project" maxlength="255">
      </div>
      <div class="section-form-row">
        <label>시작일자</label>
        <input type="date" name="resumeVO.portfolioList[${idx}].porStartDate" required>
      </div>
      <div class="section-form-row">
        <label>종료일자</label>
        <input type="date" name="resumeVO.portfolioList[${idx}].porEndDate">
      </div>
      <div class="section-form-row w-100">
        <label>작업 설명</label>
        <textarea name="resumeVO.portfolioList[${idx}].porInformation" placeholder="프로젝트 상세 내용을 입력하세요" maxlength="255" required></textarea>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  selfintro: idx => `
    <div class="section-form-wrap" id="form-selfintro${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>자기소개서 제목</label>
        <input type="text" name="resumeVO.introduction.introductionName" maxlength="85" placeholder="예: 성장하는 개발자" required>
      </div>
      <div class="section-form-row w-100">
        <label class="mb-3">자기소개서 내용</label>
        <div class="d-flex direction-column w-100">
          <textarea name="resumeVO.introduction.introductionContent" maxlength="2000" placeholder="자기소개서 본문을 입력하세요. (최대 2000자)" required></textarea>
        </div>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  military: idx => `
    <div class="section-form-wrap" id="form-military${idx}" data-idx="${idx}">
      <div class="section-form-row">
        <label>복무구분</label>
        <select name="resumeVO.militaryList[${idx}].serviceCategoryCode" id="serviceCategoryCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>군별</label>
        <select name="resumeVO.militaryList[${idx}].militaryTypeCode" id="militaryTypeCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>계급</label>
        <select name="resumeVO.militaryList[${idx}].militaryRankCode" id="militaryRankCode${idx}" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>전역구분</label>
        <select name="resumeVO.militaryList[${idx}].dischargeCode" id="dischargeCode${idx}"required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>복무 시작일자</label>
        <input type="date" name="resumeVO.militaryList[${idx}].militaryStartDate" required>
      </div>
      <div class="section-form-row">
        <label>복무 종료일자</label>
        <input type="date" name="resumeVO.militaryList[${idx}].militaryEndDate">
      </div>
      <div class="section-form-row">
        <label>면제/미필 사유</label>
        <input type="text" name="resumeVO.militaryList[${idx}].militaryReason" maxlength="2000" placeholder="사유를 입력하세요">
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn save_btn btn_violet_line">저장</button>
      </div>
    </div>
  `
  // ※ selfintro(자기소개서)는 List가 아니라 단일 IntroductionVO이므로, 'resumeVO.introduction.introductionName'처럼 리스트 인덱스 없음! ✔️
};


// 저장버튼 클릭 시 list 형태로 띄워주기.
// ♥♥♥♥ 여기 작업해야됌 ~~
const listItemMap = {
  edu: (idx, data) => `
    <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
      <div class="d-flex">
	      <strong>${data.schoolName}</strong>
	      <span class="ms-3">${data.entranceDate} ~ ${data.graduateDate}</span>
      </div>
      <div class="d-flex gap-2">
	      <button type="button" class="btn_edit">
	      	<span class="material-symbols-outlined fs-3">stylus</span>
	      </button>
	      <button type="button" class="btn_del">
	      	<span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
	     </button>
      </div>
    </div>
  `,
  career: (idx, data) => `
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
        <button type="button" class="btn_del">
          <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
        </button>
      </div>
    </div>
  `,
  skill: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <strong>${data.mySkillName || '기술명 없음'}</strong>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    exp: (idx, data) => `
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
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    license: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.licenseCode || '자격증명 없음'}</strong>
          <span>${data.licensePassDate || ''}</span>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    support: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <strong>${data.disabilityCodeName || '장애유형'} / ${data.disabilityLevelCodeName || '등급'}</strong>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    award: (idx, data) => `
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
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    language: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <div class="d-flex flex-column">
          <strong>${data.languageExamName || '시험명 없음'}</strong>
          <span>${data.passDate || ''}</span>
          <small>${data.languageCode || ''} / ${data.languageExamScore || '점수 없음'}</small>
        </div>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    portfolio: (idx, data) => `
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
          <button type="button" class="btn_del">
            <span class="material-symbols-outlined fs-3 text-Secondary">delete</span>
          </button>
        </div>
      </div>
    `,
    military: (idx, data) => `
      <div class="list-item d-flex justify-content-between align-items-center border-bottom py-2" data-idx="${idx}">
        <strong>${data.serviceCategoryCode || '복무구분'} / ${data.militaryTypeCode || '군별'}</strong>
        <div class="d-flex gap-2">
          <button type="button" class="btn_edit">
            <span class="material-symbols-outlined fs-3">stylus</span>
          </button>
          <button type="button" class="btn_del">
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
  "company": {
    "userId": "",
    "userPassword": "",
    "userRole": "",
    "userWithdrawDate": "",
    "userStatus": false,
    "userEnabled": "",
    "comName": "",
    "comInfo": "",
    "comNum": "",
    "comEmail": "",
    "comUrl": "",
    "comCreateYear": "",
    "comMem": 0,
    "comLogo": "",
    "comPayment": "",
    "industryType": ""
  },
  "careerList": [
    {
      "careerNo": "",
      "resumeNo": "",
      "jobCode": "",
      "jobCodeName": "",
      "startWorkDate": "",
      "retireDate": "",
      "tenure": "",
      "department": "",
      "responsibility": "",
      "freelancer": "",
      "jobGradeCode": "",
      "jobGradeCodeName": "",
      "positionCode": "",
      "positionCodeName": "",
      "careerYear": "",
      "careerYearName": "",
      "salary": "",
      "location": "",
      "deleteDate": "",
      "comId": "",
      /*"company": {
        "userId": "",
        "userPassword": "",
        "userRole": "",
        "userWithdrawDate": "",
        "userStatus": false,
        "userEnabled": "",
        "comName": "",
        "comInfo": "",
        "comNum": "",
        "comEmail": "",
        "comUrl": "",
        "comCreateYear": "",
        "comMem": 0,
        "comLogo": "",
        "comImage": "",
        "comPayment": "",
        "industryType": ""
      },*/
    }
  ],
  "supportList": [
    {
      "supportNo": "",
      "resumeNo": "",
      "disabilityCode": "",
      "disabilityCodeName": "",
      "disabilityLevelCode": "",
      "disabilityLevelCodeName": "",
      "deleteDate": ""
    }
  ],
  "awardList": [
    {
      "awardCode": "",
      "resumeNo": "",
      "awardName": "",
      "awardDate": "",
      "hosting": "",
      "deleteDate": ""
    }
  ],
  "myExperienceList": [
    {
      "myExpCode": "",
      "resumeNo": "",
      "expCode": "",
      "expCodeName": "",
      "expName": "",
      "organizationName": "",
      "expStartDate": "",
      "expEndDate": "",
      "deleteDate": ""
    }
  ],
  "mySkillList": [
    {
      "mySkillCode": "",
      "resumeNo": "",
      "mySkillName": "",
      "deleteDate": ""
    }
  ],
  "myLicenseList": [
    {
      "myLicense": "",
      "resumeNo": "",
      "licenseCode": "",
      "licenseCodeName": "",
      "licensePassDate": "",
      "deleteDate": ""
    }
  ],
  "introduction": {
    "introductionDeleteDate": "",
    "introductionSubmitYn": "",
    "introductionNo": "",
    "userId": "",
    "introductionName": "",
    "introductionContent": "",
    "introductionQuestion": "",
    "introductionCreateDate": ""
  },
  "languageSkillList": [
    {
      "languageSkillNo": "",
      "resumeNo": "",
      "languageExamCode": "",
      "languageExamCodeName": "",
      "languageCode": "",
      "languageCodeName": "",
      "languageExamName": "",
      "passDate": "",
      "languageExamType": "",
      "languageExamScore": "",
      "languageExamLevelCode": "",
      "languageExamLevelCodeName": "",
      "deleteDate": ""
    }
  ],
  "portfolioList": [
    {
      "porCode": "",
      "resumeNo": "",
      "porName": "",
      "porStartDate": "",
      "porEndDate": "",
      "porInformation": "",
      "deleteDate": "",
      "porUrl": ""
    }
  ],
  "militaryList": [
    {
      "militaryNo": "",
      "resumeNo": "",
      "serviceCategoryCode": "",
      "militaryTypeCode": "",
      "militaryRankCode": "",
      "dischargeCode": "",
      "militaryStartDate": "",
      "militaryEndDate": "",
      "militaryReason": "",
      "deleteDate": ""
    }
  ],
  "educationList": [
    {
      "educationNo": "",
      "resumeNo": "",
      "highestEducationCode": "",
      "schoolName": "",
      "graduateYn": "",
      "transferYn": "",
      "entranceDate": "",
      "graduateDate": "",
      "location": "",
      "departmentCode": "",
      "deleteDate": "",
      "specialtyList": [
        {
          "educationNo": "",
          "resumeNo": "",
          "mainMajor": "",
          "subMajor": "",
          "subMajorCode": "",
          "deleteDate": ""
        }
      ]
    }
  ],
  "joblist": [
    {
      "jobCode": "",
      "topJobCode": "",
      "jobName": ""
    }
  ],
  "topjoblist": [
    {
      "topJobCode": "",
      "topJobName": "",
      "jobVO": [
        {
          "jobCode": "",
          "topJobCode": "",
          "jobName": ""
        }
      ]
    }
  ],
  "introductionNo": ""
}

// 저장 버튼 클릭 시에 json에 추가해 줄 vo 세팅하기
function makeVO(type, data) {
  switch (type) {
    case "edu":
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
    case "license":
      return {
        myLicense: "",
        resumeNo: "",
        licenseCode: data.licenseCode,
        licenseCodeName: "",
        licensePassDate: data.licensePassDate,
        deleteDate: ""
      };
    case "career":
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
    // ...필요한 유형 모두 추가 ★★★★★ 여기 추가해야됌
    default:
      return data; // fallback, 커스텀 필요
  }
}

// makeVO랑 세트로 동작, 확인 버튼 클릭 시 vo를 위에서 만들고 > 여기서 어느위치에 추가할지 정해줌 
const arrayKey = {
  edu: "educationList",
  license: "myLicenseList",
  career: "careerList",
  skill: "mySkillList",
  exp: "myExperienceList",
  support: "supportList",
  award: "awardList",
  language: "languageSkillList",
  portfolio: "portfolioList",
  military: "militaryList"
  // 필요하면 계속 추가
};




/* selct 비동기 요청 코드 */
// 1. select 셋업 정보를 한 곳에 모아둠
// // ♥♥♥♥ 여기 작업해야됌 ~~
const selectLoadConfig = {
  edu: [

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
   }
  ],
  career: [
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
  exp: [
    { 
      selector: el => el.querySelector("#expCode" + el.dataset.idx),
      url: "/ajax/code/cmncodegroup/EXPR",
      key: "cmnCodeList",  // 만약 배열이 data 전체라면 빈 문자열
      valueKey: "codeDetailNo",
      labelKey: "codeName"
    },
  ],
  support: [
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
  language: [
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
  military: [
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
  ],
  // ...type별 추가
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

// 3. 엑시오스 비동기 요청 전송
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
    selectElem.innerHTML = list.map(i =>
      `<option value="${i[valueKey]}">${i[labelKey]}</option>`
    ).join("");
  } catch (err) {
    console.error("옵션 로드 실패:", err);
  }
}





// ---- JS 추가 바인딩 부분 ----
document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".add-btn").forEach(btn => {
    btn.addEventListener("click", async function () {
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
      let idx = typeCounters[type] = (typeCounters[type] || 0) + 1;
      const templateFunc = templateMap[type];
      if (!templateFunc) {
        alert(`${type} 폼은 준비중입니다.`);
        return;
      }
      // 폼 추가
	  formContainer.insertAdjacentHTML('beforeend', templateFunc(idx));
      // section-content, +버튼 숨김
      secCont.style.display = "none";
      if (addBtn) addBtn.style.display = 'none';
      const newForm = formContainer.lastElementChild;
      await setDynamicSelects(type, newForm);

      // formContainer 내 이벤트 위임 - 중복 바인딩 방지
      if (!formContainer.hasAttribute('data-bound')) {
        formContainer.setAttribute('data-bound', 'true');
        formContainer.addEventListener("click", function (e) {
			
			
          // 저장 버튼 클릭
          if (e.target.classList.contains('btn_violet_line')) {
            const formWrap = e.target.closest('.section-form-wrap');
            if (!formWrap) return;
            const thisType = formWrap.id.split('-')[1].replace(/\d+$/, '');
            const idx = formWrap.dataset.idx;
            const section = formWrap.closest('.section');
            const addBtn = section.querySelector('.add-btn');
            const listContainer = section.querySelector('.listContainer');
            // 입력값 추출
            let data = {};
            formWrap.querySelectorAll("input, select, textarea").forEach(input => {
              const name = input.name.split('.').pop().replace(/\[\d+\]/, '');
              data[name] = input.value;
            });
            
            // JS JSON 객체에 넘기기 위한 반복문 코드
			formWrap.querySelectorAll("input, select, textarea").forEach(input => {
				const fromDataName = input.name.split('.').pop().replace(/\[\d+\]/, '');
				data[fromDataName] = input.value;
			});
			
			// 저장버튼 클릭 시 위에서 만든 json 객체 
			const vo = makeVO(thisType, data);
			const key = arrayKey[thisType];
			if (key) resume[key].push(vo);
			
			
			console.log(resume)
			
			
            // 리스트 템플릿 추가
            const itemHtml = listItemMap[thisType](idx, data);
            listContainer.insertAdjacentHTML('beforeend', itemHtml);
            // 폼 제거, +버튼 복원
            formWrap.remove();
            typeCounters[thisType] = Math.max((typeCounters[thisType] || 1) - 1, 0);
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
			const matches = formWrap.id.match(/^form-([a-z]+)\d*$/);
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
	console.log(resumeName, resumeValue)

    if (resumeName && resumeValue) {
      resume[resumeName] = resumeValue; // resume 객체에 바로 세팅
    }
	resume[resumeName] = resumeValue; // resume 객체에 바로 세팅
	console.log(resume)
  });
}

// 전송하기 버튼 눌렀을 떄 insert 진행 로직 !
document.addEventListener("submit", function (e) {
  e.preventDefault(); // ✅ 기본 폼 제출 막기
  setBasicResumeInfo();	// 기존 이력서 hidden태그 가져와서 insert 하기

  const cleanedResume = removeEmptyStrings(resume);
  const form = e.target;
  if (!form.matches("#resume_form")) return; // ✅ 특정 폼만 처리하고 나머지는 무시

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


    // axios 비동기 전송
    return axios.post("/mypage/resume/create", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    });

});
