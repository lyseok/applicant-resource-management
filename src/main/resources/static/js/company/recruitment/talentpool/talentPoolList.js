// Floating dropdown overlay for .dropdown-hover
document.addEventListener('mouseover', function (e) {
  if (e.target.closest('.dropdown-hover')) {
    const container = e.target.closest('.dropdown-hover');
    const dropdown = container.querySelector('.dropdown-content');
    if (dropdown) {
      const rect = container.getBoundingClientRect();
      dropdown.style.top = rect.top - dropdown.offsetHeight + 'px';
      dropdown.style.left = rect.left + 'px';
      dropdown.style.display = 'block';
    }
  }
});
document.addEventListener('mouseout', function (e) {
  if (e.target.closest('.dropdown-hover')) {
    const dropdown = e.target
      .closest('.dropdown-hover')
      .querySelector('.dropdown-content');
    if (dropdown) {
      dropdown.style.display = 'none';
    }
  }
});
const skillInput = document.querySelector('.skill-input');
const licenseInput = document.querySelector('.license-input');
const majorInput = document.querySelector('.major-input');
// 1. 페이지 로드 시 데이터 불러오기
axios.get('/ajax/company/talentpool/setupdata').then((res) => {
  const { skiList = [], licList = [], eduList = [] } = res.data;
  createAutocomplete(skillInput, skiList);
  createAutocomplete(licenseInput, licList);
  createAutocomplete(majorInput, eduList);
});

// 2. 자동완성 생성 함수
function createAutocomplete(inputEl, dataList) {
  // 컨테이너 생성
  const container = document.createElement('div');
  container.className = 'autocomplete-list';
  container.style.position = 'absolute';
  container.style.background = '#fff';
  container.style.border = '1px solid #ddd';
  container.style.width = inputEl.offsetWidth + 'px';
  container.style.maxHeight = '150px';
  container.style.overflowY = 'auto';
  container.style.zIndex = '1000';
  container.style.display = 'none';
  inputEl.parentElement.style.position = 'relative';
  inputEl.parentElement.appendChild(container);

  // input 이벤트 처리
  inputEl.addEventListener('input', function () {
    const query = this.value.trim().toLowerCase();
    container.innerHTML = '';
    if (!query) {
      container.style.display = 'none';
      return;
    }
    // 필터링
    const filtered = dataList.filter(
      (item) => item && item.toLowerCase().includes(query)
    );
    if (filtered.length === 0) {
      container.style.display = 'none';
      return;
    }
    // 목록 렌더링
    filtered.forEach((item) => {
      const option = document.createElement('div');
      option.textContent = item;
      option.style.padding = '8px';
      option.style.cursor = 'pointer';
      option.addEventListener('click', () => {
        const tag = document.createElement('span');
        tag.className = 'skill-tag';
        tag.style.marginRight = '5px';
        tag.style.marginBottom = '5px';
        tag.style.display = 'inline-block';
        tag.innerHTML =
          item +
          ' <span style="cursor:pointer;margin-left:5px;" onclick="this.parentElement.remove()">×</span>';
        inputEl.parentElement.appendChild(tag);
        inputEl.value = '';
        container.style.display = 'none';
      });
      container.appendChild(option);
    });
    container.style.display = 'block';
  });

  // focus out 시 닫기
  document.addEventListener('click', function (e) {
    if (!container.contains(e.target) && e.target !== inputEl) {
      container.style.display = 'none';
    }
  });
}

function renderTalentPoolTable(talentList) {
  const tbody = document.querySelector('table tbody');
  tbody.innerHTML = ''; // 기존 행 삭제

  talentList.forEach((item) => {
    // (1) 회원
    const userId = item.userId || '';
    const userName = item.userName || '';
    const email = item.email || '';
    const tel = item.tel || '';

    // (2) 최종 학력 (educationList 중 가장 높은 코드)
    let edu = { schoolName: '', highestEducationCodeName: '', major: '' };
    if (item.education) {
      const eduObj = item.education;

      edu = {
        schoolName: eduObj.schoolName || '',
        highestEducationCodeName: eduObj.highestEducationCodeName || '',
        major: eduObj.departmentCode || '',
      };
    }

    // (3) 어학 (최고 점수 기준 하나만, 예: TOEIC)
    let lang = { name: '', score: '' };
    if (item.languageSkillList && item.languageSkillList.length) {
      // 점수가 숫자이면 내림차순 정렬
      lang =
        item.languageSkillList
          .map((l) => ({
            name: l.languageExamName,
            score: Number(l.languageExamScore || 0),
          }))
          .sort((a, b) => b.score - a.score)[0] || lang;
    }

    // (4) 최근 경력 (입사일 기준 가장 최근)
    let lastCareer = { company: '', job: '' };
    if (item.careerList && item.careerList.length) {
      lastCareer = item.careerList.sort((a, b) =>
        (b.startWorkDate || '').localeCompare(a.startWorkDate || '')
      )[0];
    }

    // (5) 총 경력 (year 합계 또는 max)
    let totalYear = 0;
    if (item.careerList && item.careerList.length) {
      totalYear = item.careerList.reduce((sum, cur) => {
        const year = parseInt(
          cur.careerYearName?.replace(/[^0-9]/g, '') || '0',
          10
        );
        return sum + year;
      }, 0);
    }

    // (6) 자격증
    const licenses = (item.myLicenseList || [])
      .map((l) => l.licenseCode)
      .filter(Boolean);

    // (7) 보유기술
    const skills = (item.mySkillList || [])
      .map((s) => s.mySkillName)
      .filter(Boolean);

    // (8) 테이블 row 만들기
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td><input type="checkbox" class="checkbox" data-user-id="${userId}"></td>
      <td>
        <div class="candidate-info">
          <div class="candidate-name">${userName}</div>
          <div class="candidate-email">${email}</div>
        </div>
      </td>
      <td>
        <div class="experience-status">
          <div class="status-text resume-btn" data-resumeno="${
            item.resumeNo
          }">확인</div>
        </div>
      </td>
      <td>
        <div class="data-info">
          <div class="data-name">${edu.schoolName}</div>
          <div class="data-sub">${edu.major || '-'}</div>
        </div>
      </td>
      <td>
        <div class="data-info">
          <div class="data-name">${lang.name || '-'}</div>
          <div class="data-sub">${lang.score}</div>
        </div>
      </td>
      <td>${tel}</td>
      <td>
        <div class="data-info">
          <div class="data-name">${lastCareer?.comName || '-'}</div>
          <div class="data-sub">
            ${lastCareer?.jobCodeName || ''} 
            ${lastCareer?.careerYearName || ''}
          </div>
        </div>
      </td>
      <td>
        <div class="my-list dropdown-hover">
          ${licenses.map((l) => `<span class="skill-tag">${l}</span>`).join('')}

        </div>
      </td>
      <td>
        <div class="my-list dropdown-hover">
          ${skills.map((s) => `<span class="skill-tag">${s}</span>`).join('')}
        </div>
      </td>
    `;
    tbody.appendChild(tr);
    // resume-btn을 클릭했을때 '/popup/resume'로 이동하는데 새로운 창을 열어서 이동 할거야 새로운 창의 너비는 950px로해줘
    tr.querySelector('.resume-btn').addEventListener('click', function () {
      window.open(
        '/popup/resume/' + item.resumeNo,
        'resumePopup',
        'width=950,height=800'
      );
      // 이력서 열람 업데이트 axios 들어갑니다잉~
		axios
			.post(`/ajax/company/talentpool/${userId}`)
			.then(() => {
				console.log(`이력서 열람 기록 완료: ${userId}`);
			})
			.catch((err) => {
				console.error(`이력서 열람 기록 실패: ${userId}`, err);
		});
    });
  });
}
{
  /* <td>${lastCareer ? totalYear + '년' : '-'}</td>; */
}
const params = {
  page: 1,
  pageSize: 10,
};

function fetchData() {
  showLoading();
  const paramsString = paramsSerializer(params);
  console.log('필터링 요청:', paramsString);
  axios
    .get('/ajax/company/talentpool/filter?' + paramsString)
    .then((res) => {
      // res.data가 배열이면 바로 사용
      const resp = res.data;
      renderTalentPoolTable(resp.data);

      totalPage = Math.ceil(resp.totalCnt / params.pageSize);
      console.log(totalPage, params.page);
      renderPager(totalPage, params.page); // 페이저 렌더링
      applyCheckboxStates();
    })
    .catch((err) => {
      alert('데이터를 불러오는 데 실패했습니다.');
    })
    .finally(() => {
      hideLoading();
    });
}

// 로딩 show/hide 함수
function showLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'none', 'important');
}

function renderPager(totalPages, page) {
  let pagerHtml = '';
  for (let i = 1; i <= totalPages; i++) {
    if (i === page) {
      pagerHtml += `<span class="BtnType SizeS active">${i}</span>`;
    } else {
      pagerHtml += `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
    }
  }
  if (page < totalPages) {
    pagerHtml += `<button data-page="${
      page + 1
    }" class="BtnType SizeS BtnNext btnNext">다음</button>`;
  }
  document.querySelector('.PageBox').innerHTML = pagerHtml;
}

// 페이저 클릭 (이벤트 위임)
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    const page = Number(e.target.dataset.page);
    params.page = page;
    fetchData(params.page, params.pageSize);
  } else if (e.target.classList.contains('BtnNext')) {
    params.page += 1;
    fetchData(params.page, params.pageSize);
  }
  // 필요시 이전(Prev) 버튼도 처리
});

// ============================== 학력 코드 ==============================
axios.get('/ajax/code/cmncodegroup/EDUC').then((res) => {
  const codeList = res.data.cmnCodeList;
  const filtered = codeList.filter((c) =>
    ['EDUC-002', 'EDUC-003', 'EDUC-004', 'EDUC-005'].includes(c.codeDetailNo)
  );

  // 옵션 렌더링
  const eduOptions = document.querySelector('.education-options');
  eduOptions.innerHTML = filtered
    .map(
      (c, idx) => `
      <div class="education-option" data-code="${c.codeDetailNo}">
        ${c.codeName}
      </div>
    `
    )
    .join('');

  // 클릭 이벤트 부여
  eduOptions.querySelectorAll('.education-option').forEach((option) => {
    option.addEventListener('click', function () {
      if (this.classList.contains('active')) {
        // 이미 선택된 상태면 해제
        this.classList.remove('active');
      } else {
        // 다른 active 제거 후 현재 옵션만 active
        eduOptions
          .querySelectorAll('.education-option')
          .forEach((opt) => opt.classList.remove('active'));
        this.classList.add('active');
      }
    });
  });
});
document
  .querySelector('.btn:first-child')
  .addEventListener('click', function () {
    const filterContainer = document.querySelector('.filter-container');
    filterContainer.style.display =
      filterContainer.style.display === 'none' ? 'flex' : 'none';
  });

// [전체 선택] 체크박스 클릭 시 tbody의 모든 체크박스 선택/해제
document
  .querySelector('thead .checkbox')
  .addEventListener('click', function () {
    const isChecked = this.checked;
    document.querySelectorAll('tbody .checkbox').forEach((checkbox) => {
      checkbox.checked = isChecked;
    });
  });

// [태그 입력] Enter 누르면 입력값을 태그로 생성, x 클릭 시 삭제
document.querySelectorAll('.input-field').forEach((input) => {
  input.addEventListener('keypress', function (e) {
    if (e.key === 'Enter' && this.value.trim()) {
      const tag = document.createElement('span');
      tag.className = 'skill-tag';
      tag.style.marginRight = '5px';
      tag.style.marginBottom = '5px';
      tag.style.display = 'inline-block';
      tag.innerHTML =
        this.value.trim() +
        ' <span style="cursor:pointer;margin-left:5px;" onclick="this.parentElement.remove()">×</span>';

      this.parentElement.appendChild(tag);
      this.value = '';
      e.preventDefault();
    }
  });
});

// =========================== 경력 ==================================
const thumbs = document.querySelectorAll('.slider-thumb'); // 좌/우 썸
const range = document.querySelector('.slider-range'); // 색상 바
const track = document.querySelector('.slider-track'); // 전체 바
const minValue = document.querySelector('.range-values span:first-child'); // 최소 표시
const maxValue = document.querySelector('.range-values span:last-child'); // 최대 표시

let isDragging = false;
let currentThumb = null;

// 썸 클릭 시작
thumbs.forEach((thumb) => {
  thumb.addEventListener('mousedown', function (e) {
    isDragging = true;
    currentThumb = this;
    e.preventDefault();
  });
});

// 썸을 드래그 중일 때 마우스 이동 처리
document.addEventListener('mousemove', function (e) {
  if (!isDragging) return;

  const trackRect = track.getBoundingClientRect();
  let percent = ((e.clientX - trackRect.left) / trackRect.width) * 100;
  percent = Math.min(Math.max(percent, 0), 100); // 0~100% 제한

  if (currentThumb === thumbs[0]) {
    // 왼쪽 썸
    const rightPercent = parseFloat(thumbs[1].style.left);
    if (percent >= rightPercent) {
      percent = rightPercent - 1; // 썸 겹침 방지
    }
    currentThumb.style.left = percent + '%';
    range.style.left = percent + '%';
    minValue.textContent = Math.round(percent / 10) + '년';
  } else {
    // 오른쪽 썸
    const leftPercent = parseFloat(thumbs[0].style.left);
    if (percent <= leftPercent) {
      percent = leftPercent + 1;
    }
    currentThumb.style.left = percent + '%';
    range.style.right = 100 - percent + '%';
    maxValue.textContent = Math.round(percent / 10) + '년';
  }
});

// 드래그 종료
document.addEventListener('mouseup', function () {
  isDragging = false;
  currentThumb = null;
});

// ============================== 연차코드 ==============================
function getYearCodeList(minYear, maxYear) {
  const codes = [];
  for (let i = minYear; i <= maxYear; i++) {
    // const code = i === 10 ? '10' : `${i}`;
    const code = i === 10 ? 'YEXP-Y10' : `YEXP-Y${i}`;
    codes.push(code);
  }
  return codes;
}

// ================================= 필터링 요청 =================================
document
  .querySelector('.btn.btn_violet_line')
  .addEventListener('click', function () {
    // 경력
    const minText = minValue.textContent.replace(/[^0-9]/g, '');
    const maxText = maxValue.textContent.replace(/[^0-9]/g, '');

    const min = Number(minText);
    const max = Number(maxText);
    let yearCodes = getYearCodeList(min, max);
    params.careerYearList = yearCodes;

    // 학력
    const eduOptions = document.querySelector('.education-options');
    const selectedEdu = eduOptions.querySelector('.education-option.active');
    params.educationCode = selectedEdu ? selectedEdu.dataset.code : null;

    // 보유기술 태그 추출
    params.skillCodeList = Array.from(
      document.querySelectorAll('.skill-input ~ .skill-tag')
    ).map((tag) => tag.textContent.replace('×', '').trim());
    // 자격증 태그 추출
    params.licenseCodeList = Array.from(
      document.querySelectorAll('.license-input ~ .skill-tag')
    ).map((tag) => tag.textContent.replace('×', '').trim());
    // 학과 태그 추출
    params.majorName = Array.from(
      document.querySelectorAll('.major-input ~ .skill-tag')
    ).map((tag) => tag.textContent.replace('×', '').trim());

    // input 값 가져와서 params에 추가
    // const searchInput = document.querySelector('.search-input');
    // params.keyWord = searchInput.value.trim();

    console.log('필터링 요청:', params);
    fetchData();
  });

const paramsSerializer = function (params) {
  const query = [];
  for (const key in params) {
    const value = params[key];
    if (Array.isArray(value)) {
      value.forEach((v) =>
        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(v))
      );
    } else if (value !== null && value !== undefined) {
      query.push(encodeURIComponent(key) + '=' + encodeURIComponent(value));
    }
  }
  return query.join('&');
};

// ============================== 체크박스 상태 관리 ==============================
let savedTalentList = []; //  기존 인재풀
let talentList = []; // 수정 인재풀

// 페이지 로드 시 기존 저장된 리스트 불러오기
function loadSavedTalentList() {
  axios.get('/ajax/company/talentpool/savelist').then((res) => {
    savedTalentList = res.data || [];
    talentList = [...savedTalentList];
    applyCheckboxStates();
  });
}

// 체크박스 상태 반영
function applyCheckboxStates() {
  document.querySelectorAll('tbody .checkbox').forEach((checkbox) => {
    const userId = checkbox.dataset.userId;
    checkbox.checked = talentList.includes(userId);
  });
}

// 체크박스 변경 시 talentList 갱신
document.addEventListener('change', function (e) {
  if (e.target.classList.contains('checkbox') && e.target.closest('tbody')) {
    const userId = e.target.dataset.userId;
    if (e.target.checked) {
      if (!talentList.includes(userId)) talentList.push(userId);
    } else {
      talentList = talentList.filter((id) => id !== userId);
    }
    console.log('현재 선택된 인재:', talentList);
  }
});

// 증분 저장
document.getElementById('addTalent').addEventListener('click', function () {
  // 추가된 리스트
  const addList = talentList.filter((id) => !savedTalentList.includes(id));
  // 삭제된 리스트
  const removeList = savedTalentList.filter((id) => !talentList.includes(id));

  if (addList.length === 0 && removeList.length === 0) {
    alert('변경 사항이 없습니다.');
    return;
  }
  console.log('추가할 인재:', addList);
  console.log('삭제할 인재:', removeList);
  axios
    .post('/ajax/company/talentpool/savelist', { addList, removeList })
    .then(() => {
      alert('변경 사항이 저장되었습니다.');
      savedTalentList = [...talentList]; // 저장 후 현재 상태를 최신 상태로 업데이트
    })
    .catch(() => {
      alert('저장 중 오류가 발생했습니다.');
    });
});

// init
fetchData();
loadSavedTalentList();
