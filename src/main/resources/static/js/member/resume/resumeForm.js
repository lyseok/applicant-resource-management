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

const templateMap = {
  edu: idx => `
    <div class="section-form-wrap" id="form-edu${idx}">
      <div class="section-form-row">
        <label>최종 학력</label>
        <select name="resumeVO.educationList[${idx}].highestEducationCode" required id="highestEducationCode">
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label>학교명</label>
        <input type="text" name="resumeVO.educationList[${idx}].schoolName" placeholder="예: 서울대학교" maxlength="150" required>
      </div>
      <div class="section-form-row">
        <label>졸업여부</label>
        <div class="form-check-wrap d-flex">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].graduateYn" value="Y" checked>
            <label class="form-check-label">졸업</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="resumeVO.educationList[${idx}].graduateYn" value="N">
            <label class="form-check-label">졸업 전</label>
          </div>
        </div>
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
        <label>입학년월</label>
        <input type="month" name="resumeVO.educationList[${idx}].entranceDate">
      </div>
      <div class="section-form-row">
        <label>졸업년월</label>
        <input type="month" name="resumeVO.educationList[${idx}].graduateDate">
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
        <select name="resumeVO.educationList[${idx}].specialtyList[0].subMajorCode">
          <!-- 코드에 맞게 추가 -->
        </select>
      </div>
      <div class="section-form-row">
        <label>부전공</label>
        <input type="text" name="resumeVO.educationList[${idx}].specialtyList[0].subMajor" placeholder="부전공 학과를 입력해주세요." maxlength="12" disabled>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line">취소</button>
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  career: idx => `
    <div class="section-form-wrap" id="form-career${idx}">
      <div class="section-form-row">
        <label>회사명</label>
        <div class="d-flex gap-2 w-100">
          <input type="text" name="resumeVO.careerList[${idx}].comId" placeholder="예: NAVER" maxlength="100" required>
          <button class="btn btn_violet_line">확인</button>
        </div>
      </div>
      <div class="section-form-row">
        <label>직무</label>
        <select name="resumeVO.careerList[${idx}].jobCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>입사일</label>
        <input type="month" name="resumeVO.careerList[${idx}].startWorkDate" required>
      </div>
      <div class="section-form-row">
        <label>퇴사일</label>
        <input type="month" name="resumeVO.careerList[${idx}].retireDate">
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
        <select name="resumeVO.careerList[${idx}].jobGradeCode">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>직책</label>
        <select name="resumeVO.careerList[${idx}].positionCode">
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>연차</label>
        <select name="resumeVO.careerList[${idx}].careerYear">
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  skill: idx => `
    <div class="section-form-wrap" id="form-skill${idx}">
      <div class="section-form-row">
        <label>보유기술명</label>
        <input type="text" name="resumeVO.mySkillList[${idx}].mySkillName" placeholder="예: Java, Spring, React" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  exp: idx => `
    <div class="section-form-wrap" id="form-exp${idx}">
      <div class="section-form-row">
        <label>경험구분</label>
        <select name="resumeVO.myExperienceList[${idx}].expCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>경험명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].expName" placeholder="예: 네이버 인턴십" maxlength="255" required>
      </div>
      <div class="section-form-row">
        <label>시작일</label>
        <input type="month" name="resumeVO.myExperienceList[${idx}].expStartDate" required>
      </div>
      <div class="section-form-row">
        <label>종료일</label>
        <input type="month" name="resumeVO.myExperienceList[${idx}].expEndDate">
      </div>
      <div class="section-form-row">
        <label>기관명</label>
        <input type="text" name="resumeVO.myExperienceList[${idx}].organizationName" placeholder="예: 네이버" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  support: idx => `
    <div class="section-form-wrap" id="form-support${idx}">
      <div class="section-form-row">
        <label>장애유형</label>
        <select name="resumeVO.supportList[${idx}].disabilityCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>장애등급</label>
        <select name="resumeVO.supportList[${idx}].disabilityLevelCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  license: idx => `
    <div class="section-form-wrap" id="form-license${idx}">
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  language: idx => `
    <div class="section-form-wrap" id="form-language${idx}">
      <div class="section-form-row">
        <label>시험 구분</label>
        <select name="resumeVO.languageSkillList[${idx}].languageExamCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>어학명</label>
        <select name="resumeVO.languageSkillList[${idx}].languageCode" required>
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  award: idx => `
    <div class="section-form-wrap" id="form-award${idx}">
      <div class="section-form-row">
        <label>수상명</label>
        <input type="text" name="resumeVO.awardList[${idx}].awardName" placeholder="예: 프로그래밍 경진대회 대상" maxlength="255" required>
      </div>
      <div class="section-form-row">
        <label>수상일</label>
        <input type="date" name="resumeVO.awardList[${idx}].awardDate" required>
      </div>
      <div class="section-form-row">
        <label>주최기관</label>
        <input type="text" name="resumeVO.awardList[${idx}].hosting" placeholder="예: 한국정보화진흥원" maxlength="255" required>
      </div>
      <div class="section-form-btns">
        <button type="button" class="btn btn_red_line" onclick="this.closest('.section-form-wrap').remove()">취소</button>
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  portfolio: idx => `
    <div class="section-form-wrap" id="form-portfolio${idx}">
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  selfintro: idx => `
    <div class="section-form-wrap" id="form-selfintro${idx}">
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `,
  military: idx => `
    <div class="section-form-wrap" id="form-military${idx}">
      <div class="section-form-row">
        <label>복무구분</label>
        <select name="resumeVO.militaryList[${idx}].serviceCategoryCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>군별</label>
        <select name="resumeVO.militaryList[${idx}].militaryTypeCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>계급</label>
        <select name="resumeVO.militaryList[${idx}].militaryRankCode" required>
          <option value="">선택</option>
        </select>
      </div>
      <div class="section-form-row">
        <label>전역구분</label>
        <select name="resumeVO.militaryList[${idx}].dischargeCode" required>
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
        <button type="button" class="btn btn_violet_line">저장</button>
      </div>
    </div>
  `
  // ※ selfintro(자기소개서)는 List가 아니라 단일 IntroductionVO이므로, 'resumeVO.introduction.introductionName'처럼 리스트 인덱스 없음! ✔️
};

// ---- JS 추가 바인딩 부분 ----
document.addEventListener("DOMContentLoaded", function() {
  document.querySelectorAll(".add-btn").forEach(btn => {
    btn.addEventListener("click", function() {
      const btnId = btn.id;								// 모든 btn 찾기
      if (!btnId.startsWith('btn-')) return;
      const type = btnId.replace('btn-', '');			// id 찾기
      if (!type) return;
      const section = btn.closest(`#section-${type}`);	// 해당 id의 부모 찾기
      if (!section) return;
      const formContainer = section.querySelector(`.formContainer`);	// 추가될 div 찾기
      if (!formContainer) return;
      const secCont = section.querySelector(`.section-content`);		// 기존 보여지던 div 찾기
      if (!secCont) return;
      const cansel = section.querySelector(`.section-form-btns .btn_red_line`);		// 취소버튼
      console.log(cansel);
      if (!secCont) return;
      let  idx = typeCounters[type] = (typeCounters[type] || 0) + 1;
      const templateFunc = templateMap[type];
      if (!templateFunc) {
        alert(`${type} 폼은 준비중입니다.`);
        return;
      }
      formContainer.insertAdjacentHTML('beforeend', templateFunc(idx));
      // 공통코드 비동기 요청
		axios.get("/ajax/code/cmncodegroup/EDUC")
			.then(resp=>{
				const list = resp.data.cmnCodeList;
				// console.log(list);
				const hec = document.querySelector("#highestEducationCode");
				// console.log(hec);
				let html = ""; 
				list.forEach(i=>{
					// console.log(i)
					html += `<option value="${i.codeDetailNo}">${i.codeName}</option>`;
					// console.log(html)
				})
				hec.innerHTML = html;
			}).catch(err=>{
				console.error(err);
			})
      
      // 동적으로 생성된 취소 버튼에 이벤트 위임 (formContainer에서 이벤트 위임)
      formContainer.addEventListener("click", function(e) {
        if (e.target.classList.contains('btn_red_line')) {
          // 취소 버튼이 클릭됐을 때
          const formWrap = e.target.closest('.section-form-wrap');
          if (formWrap) formWrap.remove();
          typeCounters[type] = Math.max((typeCounters[type] || 1) - 1, 0);  // 0 미만 방지
          updateSectionDisplay(type, section);
        }
      });
    });
  });
  
  function updateSectionDisplay(type, section) {
    const count = typeCounters[type] || 0;
    const sectionContent = section.querySelector(`.section-content`);
    const listContainer = section.querySelector(`.listContainer`);
    console.log(sectionContent, listContainer);
    if (count <= 1) {
      if (sectionContent) sectionContent.classList.remove('d-none');
      if (listContainer) listContainer.classList.add('d-none');
    } else {
      if (sectionContent) sectionContent.classList.add('d-none');
      if (listContainer) listContainer.classList.remove('d-none');
    }
  }
});


