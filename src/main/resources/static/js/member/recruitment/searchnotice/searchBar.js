const containers = document.querySelectorAll('.default_option .search_option');

containers.forEach((container) => {
  const btn = container.querySelector('.btn_open_layer');
  const btnClose = container.querySelector('.btn_close');
  btn.addEventListener('click', () => {
    // 1) 다른 열려 있는 것 모두 닫기
    containers.forEach((c) => c.classList.remove('open'));

    // 2) 내 컨테이너만 토글
    container.classList.toggle('open');
  });
  btnClose.addEventListener('click', () => {
    container.classList.remove('open');
  });
});

const customSelectCity = document.querySelector('.custom-select');
let selectStyledDetail;

if (!selectStyledDetail) {
  selectStyledDetail = customSelectCity.querySelector('.select-styled');
}
const selectOptions = customSelectCity.querySelectorAll('.select-options li');
console.log(customSelectCity);

selectStyledDetail.addEventListener('click', function () {
  customSelectCity.classList.toggle('open');
});

selectOptions.forEach((option) => {
  option.addEventListener('click', function () {
    const value = option.getAttribute('data-value');
    selectStyledDetail.querySelector('span').textContent = option.textContent;
    customSelectCity.classList.remove('open');

    console.log(value);
  });
});

const listBody = document.querySelector('.list_body');
const search_btn = document.querySelector('#search_btn'); //검색하기 버튼 클릭후 콘솔에 찍히는 params 값 보기->axios로 넘기기만 하면됨
//보낼 값 객체(axios로 바꿔서)
let params = {
  page: 1,
  pageSize: 10,
  sort: 'RD',
  districtCode: [],
  jobCode: [],
  keyword: '',
};

let myCompanyList;
let myRecruitList;

search_btn.addEventListener('click', function () {
  fetchRecruitData();
});

function fetchRecruitData() {
  console.log(params);
  const queryString = new URLSearchParams();
  Object.keys(params).forEach((key) => {
    const value = params[key];
    if (Array.isArray(value)) {
      value.forEach((v) => queryString.append(key, v));
    } else if (value !== null && value !== undefined && value !== '') {
      queryString.append(key, value);
    }
  });

  axios
    .get('/ajax/recruit/search?' + queryString.toString())
    .then((res) => {
      const resp = res.data;

      myCompanyList = resp.myScrabCompany;
      myRecruitList = resp.myScrabRecruit;

      const noticeCntEl = document.querySelector('#notice_cnt');
      if (noticeCntEl && resp.data.length > 0 && resp.noticeCnt !== undefined) {
        noticeCntEl.innerHTML = resp.noticeCnt;
      }

      recruitNoticeInit(resp.data); // 목록 그리기 함수
      totalPage = Math.ceil(resp.noticeCnt / params.pageSize);
      console.log(totalPage, params.page);
      renderPager(totalPage, params.page); // 페이저 렌더링
    })
    .catch((err) => {
      console.error('검색 실패:', err);
      alert('데이터를 불러오는 데 실패했습니다.');
    });
}

function recruitNoticeInit(data) {
  if (!listBody) return;

  listBody.innerHTML = data
    .map((item) => {
      return `
      <div id="rec-${
        item.recruitmentNo
      }" class="list_item" data-url="/recruit_notice/${item.recruitmentNo}">
        <div class="box_item">
          <div class="col company_nm">
            <a href="javascript:void(0)" class="str_tit" target="_blank">${
              item.comName
            }</a>
            <button type="button" 
              title="관심기업 등록" 
              class="interested_corp${
                myCompanyList.includes(item.userId) ? ' on' : ''
              }" data-company="${item.userId}">
                <span>관심기업 등록</span>
            </button>
          </div>
          <div class="col notification_info">
            <div class="job_tit">
              <a class="str_tit" title="${
                item.recruitmentTitle
              }" href="javascript:void(0)">
                <span>${item.recruitmentTitle}</span>
              </a>
              <button type="button" 
                title="스크랩" 
                class="spr_scrap btn_scrap${
                  myRecruitList.includes(item.recruitmentNo) ? ' on' : ''
                }" data-recruitment-no="${item.recruitmentNo}">
                  <span class="blind">스크랩</span>
              </button>
            </div>
            <div class="job_meta">
              <span class="job_sector">${
                item.jobName ? `<span>${item.jobName}</span>` : ''
              }</span>
              ${
                item.skillList && item.skillList.length > 0
                  ? '<span class="job_sector">' +
                    item.skillList
                      .map((skill) => `<span>${skill.recruitSkillName}</span>`)
                      .join('') +
                    '</span>'
                  : ''
              }
            </div>
            ${
              item.positionList && item.positionList.length > 0
                ? `<div class="job_badge">
                    ${item.positionList
                      .map(
                        (pos) =>
                          `<span class="mActive">${pos.recruitmentPositionCodeName}</span>`
                      )
                      .join('')}
                  </div>`
                : ''
            }
          </div>
          <div class="col recruit_info">
            <ul>
              <li>
                <p class="work_place">${item.cityName} ${item.districtName}</p>
              </li>
              <li>
                <p class="career">${item.yearCodeName || ''}
                ${item.recruitFinishYn === 'Y' ? ' · 마감' : ' · 진행중'}</p>
              </li>
              <li>
                <p class="education">${
                  item.education
                    ? item.education.recruitmentEducationCodeName
                    : '-'
                }</p>
              </li>
              <li>
                <p class="salary">
                ${formatSalary(item.recruitmentSalary) || '-'}
                </p>
              </li>
            </ul>
          </div>
          <div class="col support_info">
            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다.">
              <span class="sri_btn_immediately">입사지원</span>
            </button>
            <p class="support_detail">
              <span class="date">
                ${
                  item.recruitmentFinishDate
                    ? '~' + item.recruitmentFinishDate.slice(5, 10)
                    : ''
                }
              </span>
              <span class="deadlines d-block">
                ${
                  item.recruitmentStartDate
                    ? getTimeAgo(item.recruitmentStartDate)
                    : ''
                }
              </span>
              <span class="deadlines view_cnt">
                조회수 ${item.viewCnt}
              </span>
            </p>
          </div>
        </div>
        <div class="similar_recruit"></div>
      </div>
    `;
    })
    .join('');

  listBody.addEventListener('click', async (e) => {
    const btn = e.target.closest('button');
    if (!btn) return;

    // 관심기업 버튼
    if (btn.classList.contains('interested_corp')) {
      const company = btn.dataset.company;
      if (btn.classList.contains('on')) {
        // 관심기업 취소
        await axios.delete('/ajax/member/scrab_company/' + company);
        btn.classList.remove('on');
      } else {
        // 관심기업 등록
        await axios.post('/ajax/member/scrab_company/' + company);
        btn.classList.add('on');
      }
    }

    // 스크랩 버튼
    if (btn.classList.contains('btn_scrap')) {
      const recruitmentNo = btn.dataset.recruitmentNo;
      if (btn.classList.contains('on')) {
        // 스크랩 취소
        await axios.delete('/ajax/member/scrab_recruit/' + recruitmentNo);
        btn.classList.remove('on');
        btn.classList.add('off');
      } else {
        // 스크랩 등록
        await axios.post('/ajax/member/scrab_recruit/' + recruitmentNo);
        btn.classList.add('on');
        btn.classList.remove('off');
      }
    }
  });
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

document.getElementById('page_count').addEventListener('change', function () {
  params.pageSize = parseInt(this.value, 10);
  params.page = 1; // 페이지 초기화
  fetchRecruitData();
});

// sort 변경
document.getElementById('sort').addEventListener('change', function () {
  params.sort = this.value;
  params.page = 1;
  fetchRecruitData();
});

// 페이저 클릭 (이벤트 위임)
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    const page = Number(e.target.dataset.page);
    params.page = page;
    fetchRecruitData();
  } else if (e.target.classList.contains('BtnNext')) {
    params.page += 1;
    fetchRecruitData();
  }
  // 필요시 이전(Prev) 버튼도 처리
});

listBody.addEventListener('click', (e) => {
  let target = e.target;
  if (target.closest('button')) return;

  const itemDiv = target.closest('.list_item');
  if (itemDiv && itemDiv.dataset.url) {
    window.location.href = itemDiv.dataset.url;
  }
});

//------------------------키워드 검색 데이터------------------------------------------

const keywordInput = document.querySelector('#total_ipt_keyword');

keywordInput.addEventListener('keydown', function (e) {
  if (e.key === 'Enter') {
    e.preventDefault();
    const keyword = keywordInput.value.trim();
    if (keyword) {
      addTextKeywordSpan(keyword); // span 추가 함수 호출
      keywordInput.value = ''; // 입력창 초기화
    }
  }
});

const addTextKeywordSpan = function (keyword) {
  // 중복 방지
  if (document.querySelector(`#sp_preview_keyword_${keyword}`)) return;

  const span = document.createElement('span');
  span.className = 'selected_keyword';
  span.innerHTML = `
        검색어 &gt; ${keyword}
        <button type="button" id="sp_preview_keyword_${keyword}" data-code="${keyword}" class="btn_del remove-btn">삭제</button>
    `;
  selectedContainer.appendChild(span);

  toggleKeywordDisplay();
  updateAllCodes();

  // 삭제 이벤트
  span.querySelector('.remove-btn').addEventListener('click', function () {
    span.remove();
    toggleKeywordDisplay();
    updateAllCodes();
  });
};

//====================================================================================
//------------------------지역선택 옵션생성-------------------------------------------

const btn_reset = document.querySelector('#sp_preview .btn_reset');
const first_cityopt = document.querySelector('#first_cityopt > ul');
const second_cityopt = document.querySelector('#second_cityopt');
const add_keyword = document.querySelector('.add_keyword');

// 도시 선택 값 불러옴 (axios + async/await)
const getCityCodeList = async function () {
  try {
    const response = await axios.get('/ajax/admin/cityCode');
    const data = response.data;

    data.forEach((city) => {
      let cityCodeNo = city.cityCodeNo.slice(4);
      let cityName = city.cityName;
      setCityCodeList(cityCodeNo, cityName); // 시 선택 값 채우기
    });
  } catch (error) {
    console.error('도시 데이터 로드 실패:', error);
  }
};

//도시 선택 값 채우기
const setCityCodeList = function (cityCodeNo, cityName) {
  let html = `
			<li class="depth1_btn_wrapper" id="depth1_btn_${cityCodeNo}">
	        	<button type="button" class="depth1_btn_${cityCodeNo}" data-code="${cityCodeNo}" onclick="selectCity(this)">
	                <span class="txt">${cityName}</span>
	            </button>
	        </li>`;
  first_cityopt.innerHTML += html;

  html = `<ul class="list_check" id="sp_area_lastDepth_${cityCodeNo}" style="display: none;"></ul>`;
  second_cityopt.innerHTML += html; //도시 수만큼 생김

  getDistrictCodeList(cityCodeNo); //구군 선택 값 호출
};

// 구군 선택 값 불러옴 (axios + async/await)
const getDistrictCodeList = async function (cityCodeNo) {
  try {
    const response = await axios.get(`/ajax/admin/cityCode/CICO${cityCodeNo}`); // 예: CICO115000
    const data = response.data;

    data.forEach((district) => {
      let districtCodeNo = district.districtCodeNo;
      let cityCodeNo = district.cityCodeNo.slice(4);
      let districtName = district.districtName;
      setDistrictCodeList(districtCodeNo, cityCodeNo, districtName); // 구군 선택 값 채우기
    });
  } catch (error) {
    console.error('구군 데이터 로드 실패:', error);
  }
};

//구군 선택 값 채우기
const setDistrictCodeList = function (
  districtCodeNo,
  cityCodeNo,
  districtName
) {
  let sp_area_lastDepth = document.querySelector(
    `#sp_area_lastDepth_${cityCodeNo}`
  );
  if (districtName.includes('전체') || districtName === '전국') {
    html = `<li>
			        <div class="inpChk">
			            <input type="checkbox" id="loc_mcd_${districtCodeNo}" name="loc_mcd[]" value="${districtCodeNo}" 
			                data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type="all"
			                onclick="selectDistrict(this, '${cityCodeNo}', '${districtName}')">
			            <label for="loc_mcd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span></label>
			        </div>
			    </li>`;
  } else {
    html = `<li>
			        <div class="inpChk">
			            <input type="checkbox" id="loc_cd_${districtCodeNo}" name="loc_cd[]" value="${districtCodeNo}" 
			                data-is_representative="n" data-representative="" data-mcode="${cityCodeNo}" data-check-type=""
			                onclick="selectDistrict(this, '${cityCodeNo}', '${districtName}')">
			            <label for="loc_cd_${districtCodeNo}" class="lbl"><span class="txt">${districtName}</span></label>
			        </div>
			    </li>`;
  }
  sp_area_lastDepth.innerHTML += html; //구군 수만큼 생김
};

getCityCodeList(); //도시 선택 값 호출

//--------------------- 지역 선택 동적클래스--------------------------------------------------------------------

const selectedContainer = add_keyword.querySelector('#sp_preview_selected');
const previewWrapper = document.getElementById('sp_preview'); // 패널 래퍼

//도시 선택 중첩
const selectCity = function (button) {
  let currentLi = button.closest('li');

  // 1. 모든 li의 on 제거 (selected는 유지)
  document.querySelectorAll('#first_cityopt li').forEach((li) => {
    li.classList.remove('on');
  });

  // 2. 클릭한 li에 selected 추가 + on 추가
  currentLi.classList.add('selected', 'on');

  // 3. 모든 하위 구·군 영역 숨기기
  document
    .querySelectorAll('#second_cityopt .list_check')
    .forEach((ul) => (ul.style.display = 'none'));

  // 4. 클릭한 li의 하위 영역만 표시
  document.querySelector(
    `#sp_area_lastDepth_${button.dataset.code}`
  ).style.display = 'block';
};

//구군 선택 중첩
const selectDistrict = function (checkbox, mcode) {
  if (checkbox.dataset.checkType === 'all') {
    // 1. 하위 구군 체크박스 모두 해제
    const subDistricts = document.querySelectorAll(
      `input[name="loc_cd[]"][data-mcode="${mcode}"]`
    );
    subDistricts.forEach((cb) => {
      cb.checked = false;
      removeKeywordSpan(cb.value); // 선택 키워드에서도 제거
    });
  } else {
    // 2. 하위 단위 클릭 시 시도 전체 체크 해제
    const allCheckbox = document.querySelector(
      `input[name="loc_mcd[]"][data-mcode="${mcode}"]`
    );
    if (allCheckbox) allCheckbox.checked = false;
  }

  // 선택된 지역 span 갱신
  updateSelectedRegions();
};

const updateSelectedRegions = function () {
  // 기존 지역 관련 span 제거
  document
    .querySelectorAll(
      '#sp_preview_selected .selected_keyword button[id^="sp_preview_area_"]'
    )
    .forEach((btn) => {
      const span = btn.closest('.selected_keyword');
      if (span) span.remove();
    });

  // 현재 체크된 모든 지역코드 가져오기
  const checked = document.querySelectorAll(
    'input[name="loc_cd[]"]:checked, input[name="loc_mcd[]"]:checked'
  );
  checked.forEach((cb) => {
    const districtName = cb
      .closest('.inpChk')
      .querySelector('.txt')
      .innerText.trim(); // 예: 강남구
    const cityCode = cb.dataset.mcode; // 예: 105000

    // ✅ 시도명 추출
    const cityButton = document.querySelector(`.depth1_btn_${cityCode}`);
    const cityName =
      cityButton?.querySelector('.txt')?.innerText.trim() || '시도명';

    addCityKeywordSpan(cityName, districtName, cb.value);
  });

  updateAllCodes();
};

// span 추가
const addCityKeywordSpan = function (cityName, districtName, districtCodeNo) {
  // 중복 추가 방지
  if (document.querySelector(`#sp_preview_area_${districtCodeNo}`)) return;

  const span = document.createElement('span');
  span.className = 'selected_keyword';
  span.innerHTML = `
        ${cityName}&gt;${districtName}
        <button type="button" id="sp_preview_area_${districtCodeNo}" data-code="${districtCodeNo}" class="btn_del remove-btn">
            삭제
        </button>
    `;
  selectedContainer.appendChild(span);

  // params 갱신
  toggleKeywordDisplay();

  // 삭제 버튼 이벤트
  span.querySelector('.remove-btn').addEventListener('click', function () {
    document.querySelector(`input[value="${districtCodeNo}"]`).checked = false;
    removeKeywordSpan(districtCodeNo);
    toggleKeywordDisplay();
    updateAllCodes(); // 삭제 후 갱신
  });
};

// span 제거
const removeKeywordSpan = function (code) {
  // 지역 or 직업 버튼 ID 중 존재하는 것 선택
  const areaBtn = document.querySelector(`#sp_preview_area_${code}`);
  const jobBtn = document.querySelector(`#sp_preview_job_category_${code}`);

  const btn = areaBtn || jobBtn; // 둘 중 하나라도 존재하면
  if (btn) {
    const span = btn.closest('.selected_keyword');
    if (span) span.remove();
  }

  toggleKeywordDisplay();
};

// 선택된 값이 없으면 숨김 + 클래스 토글
const toggleKeywordDisplay = function () {
  const hasKeywords = selectedContainer.children.length > 0;
  selectedContainer.style.display = hasKeywords ? 'block' : 'none';

  // 검색조건 여부에 따라 resetting 클래스 토글
  if (hasKeywords) {
    previewWrapper.classList.remove('resetting'); // 조건 있음, 토글 확인함
    btn_reset.style.display = 'inline-block'; //초기화 버튼도 생기게
  } else {
    previewWrapper.classList.add('resetting'); // 조건 없음
    btn_reset.style.display = 'none';
  }
};

//--------------------------선택된 지역코드 객체에 넣기------------------------------------------

// 선택된 districtCode와 jobCode를 params에 반영
const updateAllCodes = function () {
  const selectedButtons = document.querySelectorAll(
    '.selected_keyword .remove-btn'
  );

  // 지역코드와 직업코드 구분
  const districtCodes = [];
  const jobCodes = [];
  let keyword = '';

  selectedButtons.forEach((btn) => {
    const code = btn.dataset.code;
    const id = btn.id;

    if (id.startsWith('sp_preview_area_')) {
      districtCodes.push(code);
    } else if (id.startsWith('sp_preview_job_category_')) {
      jobCodes.push(code);
    } else if (id.startsWith('sp_preview_keyword_')) {
      keyword = code; // 검색어는 하나만
    }
  });
  params.districtCode = districtCodes;
  params.jobCode = jobCodes;
  params.keyword = keyword;

  console.log('✅ 갱신된 districtCode:', districtCodes);
  console.log('✅ 갱신된 jobCode:', jobCodes);
  console.log('✅ 갱신된 keyword:', keyword);
};

//-----------------------내부 스크롤 동작-----------------------------------------

//==========================================================================================
//-----------------------직업선택 옵션생성-----------------------------------------

const box_jobs = document.querySelector('.box_jobs');
const box_detail_jobs = document.querySelector('.box_detail_jobs');
const box_jobs_btn = document.querySelector('#box_jobs_btn');
const details = document.querySelector('.details');
const option_list_depth1_wrapper = document.querySelector(
  '.option_list depth1_wrapper > ul'
);

//-----------------------클릭 이벤트---------------------------------------------

const selectTopJob = function (topJobCode, topJobName) {
  box_jobs.style.display = 'none';
  box_detail_jobs.style.display = 'block';

  depth(topJobCode, topJobName); //전체선택 부분만 미리 생성
  nextTopJob(topJobCode, topJobName);
};

const nextTopJob = function (topJobCode, topJobName) {
  const depth1_btn_ = document.querySelector(`#depth1_btn_${topJobCode}`);
  const li_wrapper = depth1_btn_.closest('li');
  console.log('nextTopJob');
  if (!li_wrapper.classList.contains('on')) {
    // 현재 클릭한 li에 'on' 추가
    li_wrapper.classList.add('on');
    // 다른 li들의 'on'을 제거
    document.querySelectorAll('#box_jobs_btn li').forEach((otherli) => {
      if (otherli !== li_wrapper) {
        otherli.classList.remove('on');
      }
    });
  }

  // 하위직업 상세 div 처리

  // nextTopJob() : 클릭 시 활성화
  let sp_job_category_subDepth_ = document.querySelector(
    `#sp_job_category_subDepth_${topJobCode}`
  );
  if (!sp_job_category_subDepth_) {
    depth(topJobCode, topJobName);
    sp_job_category_subDepth_ = document.querySelector(
      `#sp_job_category_subDepth_${topJobCode}`
    );
  }

  // 모든 박스 비활성화
  document.querySelectorAll('.box_detail_depth').forEach((div) => {
    div.classList.remove('on');
    div.style.display = 'none';
  });

  // 클릭한 것만 활성화
  sp_job_category_subDepth_.classList.add('on');
  sp_job_category_subDepth_.style.display = 'block';
};

//-----------------------데이터 호출---------------------------------------------

// 상위직업 선택 값 불러옴
const getTopJobCodeList = function () {
  axios
    .get('/ajax/admin/jobCode')
    .then((response) => {
      const data = response.data;
      data.forEach((topjob) => {
        let topJobCode = topjob.topJobCode;
        let topJobName = topjob.topJobName;
        setTopJobCodeList(topJobCode, topJobName);
      });
    })
    .catch((error) => {
      console.error('상위직업 코드 불러오기 실패:', error);
    });
};

// 상위직업 선택 값 채우기
const setTopJobCodeList = function (topJobCode, topJobName) {
  let html = `<button type="button" class="btn_job" data-mcls_cd_no="${topJobCode}" 
  				onclick="selectTopJob('${topJobCode}', '${topJobName}')">
                ${topJobName}
              </button>`;
  box_jobs.innerHTML += html; // 상위직업 버튼

  // Add click event to highlight the corresponding li
  const newBtn = box_jobs.querySelector(
    `.btn_job[data-mcls_cd_no="${topJobCode}"]`
  );

  html = `<li class="item_job depth1_btn_wrapper" id="depth1_btn_${topJobCode}">
            <button type="button" data-mcls_cd_no="${topJobCode}" class="first_depth depth1_btn_${topJobCode}" 
            onclick="nextTopJob('${topJobCode}', '${topJobName}')">
              <span class="txt">${topJobName}</span>
              
            </button>
          </li>`;
  box_jobs_btn.innerHTML += html; // 상위직업 버튼(리스트)
};

// 하위직업 선택 값 불러옴
const getJobCodeListByTopJob = function (topJobCode, topJobName) {
  axios
    .get(`/ajax/admin/jobCode/${topJobCode}`)
    .then((response) => {
      const data = response.data;
      data.forEach((job) => {
        let jobCode = job.jobCode;
        let jobName = job.jobName;
        let topJobCode = job.topJobCode;
        setJobCodeListByTopJob(jobCode, jobName, topJobCode, topJobName); // 하위직업 선택 값 채우기
      });
    })
    .catch((error) => {
      console.error('하위직업 코드 불러오기 실패:', error);
    });
};

// 하위직업 카테고리 제목, 전체선택 부분 생성
const depth = function (topJobCode, topJobName) {
  let html = `<div class="box_detail_depth" id="sp_job_category_subDepth_${topJobCode}" style="display: none;">
			        <div class="row row_all_select">
			            <input type="checkbox" id="all_check_onedepth_${topJobCode}" name="cat_mcls[]" onclick="onClickJobAllCheck(this)"
			            class="select_all" data-code="${topJobCode}" data-mcls_cd_no="${topJobCode}" data-mcls_cd_nm="${topJobName}">
			            <label for="all_check_onedepth_${topJobCode}">
			            	<span>${topJobName} 전체선택</span>
			            </label>
			            <!--
			            정렬 못 쓸 거 같고 css 깨짐
			            <span class="inpSel">
			                <select class="select_sort" title="정렬방법">
			                    <option value="default">가나다순</option>
			                    <option value="favor">공고많은순</option>
			                </select>
			            </span>
			            -->
			        </div>
					<div class="row list" style="height: 243px;">
	                    <div class="wrap_scroll">
	                        <div class="scrollbar disable" style="height: 243px;">
		                        <div class="track" style="height: 243px;">
			                        <div class="thumb" style="height: 243px;">
			                        	<div class="end"></div>
			                        </div>
		                        </div>
	                        </div>
	                        <div class="viewport">
	                            <div class="overview" style="top: 0px;">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>`;
  box_detail_jobs.innerHTML += html; //내부에 값 추가해야 제목끼리 안 겹침 주의

  //DOM이 구성되었으니 거기에 넣을 하위직업 호출
  getJobCodeListByTopJob(topJobCode, topJobName); // 하위직업 값 채움
};

/*
<dl class="row_item">
    <dt>
        <button type="button" class="btn_expand" data-scls_cd_no="65">   <!-- count가 길면 버튼 추가되게 -->
        	<span class="txt" style="font-weight: normal;">전문분야</span>
        </button>
    </dt>
    <dd class="area_list">
    </dd>
</dl>`;
*/

//---------------------------카테고리 생성----------------------------------------------

// 전역으로 카테고리별 sort 카운터 저장
const sortCounters = {};

// 카운터 반환 및 증가
const getSortCounter = function (categoryKey) {
  if (!sortCounters.hasOwnProperty(categoryKey)) {
    sortCounters[categoryKey] = 0; // 처음이면 초기화
  }
  return sortCounters[categoryKey]++; // 현재값 반환 후 증가
};

//하위직업 선택 값 채우기
const setJobCodeListByTopJob = function (
  jobCode,
  jobName,
  topJobCode,
  topJobName
) {
  const overview = document.querySelector(
    `#sp_job_category_subDepth_${topJobCode} .overview`
  );
  if (!overview) return;

  let targetSelector = '',
    categoryKey = '',
    categoryClass = '',
    categoryLabel = '';

  // 카테고리 조건 분기
  if (jobCode.startsWith(String(topJobCode - 1))) {
    categoryClass = 'job_category';
    categoryLabel = '직무·직업';
    categoryKey = `${topJobCode}_job`;
  } else if (jobCode.startsWith(topJobCode)) {
    categoryClass = 'specialty_category';
    categoryLabel = '전문분야';
    categoryKey = `${topJobCode}_specialty`;
  } else {
    categoryClass = 'etc_category';
    categoryLabel = '기타';
    categoryKey = `${topJobCode}_etc`;
  }

  // 카테고리가 없으면 동적 생성
  let categoryDl = overview.querySelector(`.${categoryClass}`);
  if (!categoryDl) {
    const html = `
			<dl class="row_item ${categoryClass}">
				<dt><span class="txt" style="font-weight: normal;">${categoryLabel}</span></dt>
				<dd class="area_list"></dd>
			</dl>`;
    overview.insertAdjacentHTML('beforeend', html);
    categoryDl = overview.querySelector(`.${categoryClass}`);
  }

  const targetDl = categoryDl.querySelector('.area_list');
  if (!targetDl) return;

  const sortValue = getSortCounter(categoryKey);

  const html = `
		<button type="button" name="cat_kewd[]" class="btn_three_depth" 
			data-code="${jobCode}" 
			data-mcls_cd_no="${topJobCode}" 
			data-mcls_cd_nm="${topJobName}" 
			data-kewd_cd_no="${jobCode}" 
			data-kewd_cd_nm="${jobName}" 
			data-sort="${sortValue}" 
			data-count="0"
			onclick="onClickJobCategory(this)">
			${jobName}
		</button>`;

  targetDl.innerHTML += html;
};

getTopJobCodeList(); //상위직업 선택 값 호출

//--------------------------선택된 직업코드 객체에 넣기------------------------------------------

//하위직업 선택 감지
const onClickJobCategory = function (btn) {
  let jobCode = btn.dataset.code;
  let jobName = btn.getAttribute('data-kewd_cd_nm'); // ✅ 직접 속성 읽기
  let topJobName = btn.getAttribute('data-mcls_cd_nm'); // ✅ 직접 속성 읽기
  let topJobCode = btn.getAttribute('data-mcls_cd_no');
  console.log(
    '🟢 onClickJobCategory 호출됨:',
    jobCode,
    jobName,
    topJobName,
    topJobCode
  );

  //하위직업이 체크되어 있었으면
  if (btn.classList.contains('on')) {
    btn.classList.remove('on');
    removeKeywordSpan(jobCode);
    updateAllCodes();
    return; // 선택 해제 후 종료
  }

  //전체선택이 체크되어 있었으면
  let topJobAll = document.querySelector(`#all_check_onedepth_${topJobCode}`);
  if (topJobAll && topJobAll.checked) {
    topJobAll.checked = false;
    removeKeywordSpan(topJobCode);
  }

  // 중복 추가 방지
  if (
    document.querySelector(`#sp_preview_job_category_${jobCode}`) ||
    document.querySelector(`#sp_preview_job_category_${topJobCode}`)
  )
    return;

  // 클래스 추가
  btn.classList.add('on');

  // span 추가
  addJobKeywordSpan(topJobName, jobName, jobCode, topJobCode);
};

// ✅ 직업 전체 선택 시 하위 해제 + "전체" span 추가
const onClickJobAllCheck = function (checkbox) {
  const topJobCode = checkbox.dataset.code;
  const topJobName = checkbox.dataset.mcls_cd_nm;

  if (checkbox.checked) {
    let subButtons = document.querySelectorAll(
      `.btn_three_depth[data-mcls_cd_no="${topJobCode}"]`
    );
    subButtons.forEach((btn) => {
      let jobCode = btn.dataset.code;
      btn.classList.remove('on');
      removeKeywordSpan(jobCode);
    });
    // ✅ 2. "전체" 선택 span 추가 (선택 상태 표시용)
    addJobKeywordSpan(topJobName, '전체', `${topJobCode}`);
  } else {
    //다시 누르면 해당 span 제거
    removeKeywordSpan(`${topJobCode}`);
  }
  updateAllCodes();
  toggleKeywordDisplay();
};

// ✅ 전체 선택 span도 처리할 수 있도록 수정
const addJobKeywordSpan = function (topJobName, jobName, jobCode) {
  //topJobCode여도 jobCode로 들어가고 그냥 jobCode는 jobCode로 들어감
  if (document.querySelector(`#sp_preview_job_category_${jobCode}`)) return;

  const span = document.createElement('span');
  span.className = 'selected_keyword';

  span.innerHTML = `
        ${topJobName} &gt; ${jobName}
        <button type="button" id="sp_preview_job_category_${jobCode}" data-code="${jobCode}" class="btn_del remove-btn">
            삭제
        </button>
    `;
  selectedContainer.appendChild(span);

  updateAllCodes();
  toggleKeywordDisplay();

  span.querySelector('.remove-btn').addEventListener('click', function () {
    let all_check_onedepth_ = document.querySelector(
      `#sp_preview_job_category_${jobCode}`
    );

    if (all_check_onedepth_.getAttribute('data-code') === `${jobCode}`) {
      const allCheckbox = document.querySelector(
        `#all_check_onedepth_${jobCode}`
      );
      if (allCheckbox) allCheckbox.checked = false;
      const targetBtn = document.querySelector(
        `button[data-code="${jobCode}"].btn_three_depth`
      );
      if (targetBtn) targetBtn.classList.remove('on');
    }
    removeKeywordSpan(jobCode);
    toggleKeywordDisplay();
    updateAllCodes();
  });
};

//----------------------- 공통 함수------------------------------------------

//일괄 검색조건 초기화 버튼
const clearAllFilters = function () {
  // 🔹 1. 모든 선택된 span 제거
  document
    .querySelectorAll('.selected_keyword')
    .forEach((span) => span.remove());

  // 🔹 2. 모든 체크박스 해제 (지역)
  document.querySelectorAll('input[type="checkbox"]').forEach((checkbox) => {
    checkbox.checked = false;
  });

  // 🔹 3. 모든 직업 버튼 'on' 클래스 제거
  document.querySelectorAll('.btn_three_depth.on').forEach((btn) => {
    btn.classList.remove('on');
  });

  // 🔹 4. params 초기화
  params.page = 1;
  params.districtCode = [];
  params.jobCode = [];
  params.keyword = '';

  // 🔹 5. UI 업데이트
  toggleKeywordDisplay(); // -> span 없으므로 자동으로 display: none + resetting 클래스 적용
  updateAllCodes(); // -> params 다시 동기화

  // 🔹 6. 결과 콘솔
  console.log('🔄 모든 검색 조건이 초기화되었습니다.');
};

btn_reset.addEventListener('click', clearAllFilters);

// 초기화 버튼 클릭 시(현재는 직업만 됨)
/*
const clearJobCategoryByTopCode = function (topJobCode) {
    // 1. 전체선택 체크박스 상태 해제
    const allCheckbox = document.querySelector(`#all_check_onedepth_${topJobCode}`);
    if (allCheckbox) allCheckbox.checked = false;

    // 2. 하위 직업 버튼들 초기화
    const subButtons = document.querySelectorAll(`.btn_three_depth[data-mcls_cd_no="${topJobCode}"]`);
    subButtons.forEach(btn => {
        const jobCode = btn.dataset.code;
        btn.classList.remove('on');
        removeKeywordSpan(jobCode);
    });

    updateAllCodes();
    toggleKeywordDisplay();
};

// 초기화 함수 생성 이후에 해당 버튼에 추가
btn_reset.addEventListener('click', function (e) {
	
    //if (e.target.classList.contains('btn_reset')) {
	if(btn_reset){
        const jobTab = document.querySelector('.tab_job.on'); // 예시: 활성 탭의 topJobCode
        if (!jobTab) return;

        const topJobCode = jobTab.dataset.mclsCdNo || e.target.dataset.topjobcode;
        if (topJobCode) {
            clearJobCategoryByTopCode(topJobCode);
        }
    }
})
*/

//-----------------------내부 스크롤 동작-----------------------------------------

function getTimeAgo(dateString) {
  const now = new Date();
  const posted = new Date(dateString);
  const diffMs = now - posted;
  const diffSec = Math.floor(diffMs / 1000);
  const diffMin = Math.floor(diffSec / 60);
  const diffHour = Math.floor(diffMin / 60);
  const diffDay = Math.floor(diffHour / 24);

  if (diffSec < 60) return '방금 전';
  if (diffMin < 60) return `${diffMin}분 전`;
  if (diffHour < 24) return `${diffHour}시간 전`;
  return `${diffDay}일 전`;
}

function formatSalary(salary) {
  salary = Number(salary);
  if (isNaN(salary) || salary === 0) return '면접 후 결정';
  if (salary < 10000) {
    return `${salary.toString()}만원`;
  }
  const eok = Math.floor(salary / 10000);
  const man = salary % 10000;
  return man > 0 ? `${eok}억 ${man.toString()}만원` : `${eok}억원`;
}

fetchRecruitData();

document.addEventListener('DOMContentLoaded', () => {
  const schBox = document.querySelector('.main_option');
  const schTab = schBox.children[0];
  const schItems = schTab.querySelectorAll('li');
  const schContents = schBox.querySelectorAll('.wrap_section_contents > div');

  schItems.forEach((item) => {
    item.addEventListener('click', () => {
      const isActive = item.classList.contains('on');

      // 1) 일단 모두 비우고
      schItems.forEach((li) => li.classList.remove('on'));
      schContents.forEach((div) => div.classList.remove('on'));

      if (!isActive) {
        // 2) 기존에 활성화되지 않았으면만 켜기
        item.classList.add('on');
        const key = Array.from(item.classList).find((c) => c !== 'on');
        const target = schBox.querySelector(`.wrap_section_contents > .${key}`);
        if (target) target.classList.add('on');
        if (
          item.classList.contains('keyword_section') &&
          item.classList.contains('on')
        )
          item.style.zIndex = 0;
      }
    });
  });
});
// 현재 URL에서 경로 확인
const currentUrl = window.location.pathname;
const searchName = document.querySelector('#search_name');
let areaBtn = null;
// URL에 '/job'이 포함되어 있으면 클릭 이벤트 실행
if (currentUrl.includes('/city')) {
  areaBtn = document.querySelector('.area_section .btn_open_layer');
  searchName.innerHTML = '지역별 공고';
}
if (currentUrl.includes('/job')) {
  areaBtn = document.querySelector('.job_category_section .btn_open_layer');
  searchName.innerHTML = '직업별 공고';
}
setTimeout(() => {
  if (areaBtn) {
    areaBtn.click();
  }
}, 100);
