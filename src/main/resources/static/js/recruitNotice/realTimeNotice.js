const listBody = document.querySelector('.list_body');
const slideList = document.querySelector('.sub_curation_list');

let currentPage = 1;
let currentPageSize = 20;
let currentSort = 'RD';

let myCompanyList;
let myRecruitList;

function fetchRecruitData(page = 1, pageSize = 20, sort = 'RD') {
  axios
    .get('/ajax/recruit/search', {
      params: {
        page: page,
        pageSize: pageSize,
        sort: sort,
      },
    })
    .then((res) => {
      const resp = res.data;

      myCompanyList = resp.myScrabCompany;
      myRecruitList = resp.myScrabRecruit;

      const noticeCntEl = document.querySelector('#notice_cnt');
      if (noticeCntEl && resp.data.length > 0 && resp.noticeCnt !== undefined) {
        noticeCntEl.innerHTML = resp.noticeCnt;
      }

      recruitNoticeInit(resp.data); // 목록 그리기 함수
      totalPage = Math.ceil(resp.noticeCnt / pageSize);
      console.log(totalPage, page);
      renderPager(totalPage, page); // 페이저 렌더링

      renderTopNoticeSlide(resp.data);
    });
}
fetchRecruitData();

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
  currentPageSize = parseInt(this.value, 10);
  currentPage = 1; // 페이지 초기화
  fetchRecruitData(currentPage, currentPageSize, currentSort);
});

// sort 변경
document.getElementById('sort').addEventListener('change', function () {
  currentSort = this.value;
  currentPage = 1;
  fetchRecruitData(currentPage, currentPageSize, currentSort);
});

// 페이저 클릭 (이벤트 위임)
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    const page = Number(e.target.dataset.page);
    currentPage = page;
    fetchRecruitData(currentPage, currentPageSize, currentSort);
  } else if (e.target.classList.contains('BtnNext')) {
    currentPage += 1;
    fetchRecruitData(currentPage, currentPageSize, currentSort);
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

// ====================================== Swiper Data ======================================

function renderTopNoticeSlide(data, myScrabRecruit) {
  // 최대 6개만 보여주려면 .slice(0, 6) 등 사용
  slideList.innerHTML = data
    .filter((item) => item.yearCode === 'YEXP-Y0')
    .slice(0, 10)
    .map((item) => {
      // D-day 계산
      const finishDate = new Date(item.recruitmentFinishDate);
      const now = new Date();
      const diffMs = finishDate - now;
      const diffDay = Math.ceil(diffMs / (1000 * 60 * 60 * 24));
      const dday = diffDay > 0 ? `D-${diffDay}` : '마감';

      // 지역명
      const location = `${item.cityName || ''} ${
        item.districtName || ''
      }`.trim();

      // 북마크(스크랩) on/off
      const isScrab = myRecruitList.includes(item.recruitmentNo) ? ' on' : '';

      // 템플릿 리턴
      return `
      <li class="swiper-slide swiper-slide-visible" style="margin-right: 15px;">
        <a href="/recruit_notice/${
          item.recruitmentNo
        }" class="newcomer_link_view ga_data_layer">
          <div class="sub_curation_slide">
            <div class="tag_line">
              <span class="tag d-day">${dday}</span>
              <span class="tag theme major">${location || '-'}</span>
            </div>
            <span class="company_name">${item.comName || '-'}</span>
            <span class="announcement">${item.recruitmentTitle || '-'}</span>
          </div>
        </a>
        <button type="button" 
          class="newcomer_book_mark like_btn${isScrab}" 
          data-recruitment-no="${item.recruitmentNo}">
          <span class="blind">북마크</span>
        </button>
      </li>
    `;
    })
    .join('');

  slideList.addEventListener('click', async (e) => {
    const btn = e.target.closest('button');
    if (!btn) return;

    // 스크랩 버튼
    if (btn.classList.contains('like_btn')) {
      const recruitmentNo = btn.dataset.recruitmentNo;
      if (btn.classList.contains('on')) {
        // 스크랩 취소
        await axios.delete('/ajax/member/scrab_recruit/' + recruitmentNo);
        btn.classList.remove('on');
      } else {
        // 스크랩 등록
        await axios.post('/ajax/member/scrab_recruit/' + recruitmentNo);
        btn.classList.add('on');
      }
    }
  });
}

// ====================================== Swiper ======================================

function swiperInit() {
  // 1) .priority_top_list 컨테이너 선택
  const target = document.querySelector('.priority_top_list');
  if (!target) return; // 요소 없으면 종료

  // 2) 모든 링크·버튼 요소 수집
  const subCurationLinks = Array.from(
    target.querySelectorAll(
      '.sub_curation_swiper .swiper-slide a, .sub_curation_swiper .swiper-slide button'
    )
  );

  // 3) Swiper 컨테이너 요소
  const swiperContainer = target.querySelector('.sub_curation_swiper');
  if (!swiperContainer) return;

  // 4) Swiper 인스턴스 생성
  const swiper = new Swiper(swiperContainer, {
    slidesPerGroup: 5,
    slidesPerView: 5,
    spaceBetween: 15,
    watchSlidesVisibility: true,
    navigation: {
      nextEl: target.querySelector('.sub_curation_swiper .swiper-next'),
      prevEl: target.querySelector('.sub_curation_swiper .swiper-prev'),
    },
    width: 1260,
    on: {
      slideChange: () => {
        // a) 모든 링크·버튼 tabindex="-1"
        subCurationLinks.forEach((el) => el.setAttribute('tabindex', '-1'));

        // b) 보이는 슬라이드 안 요소만 tabindex="0"
        const visibleSlides = target.querySelectorAll(
          '.sub_curation_swiper .swiper-slide-visible'
        );
        visibleSlides.forEach((slide) => {
          slide
            .querySelectorAll('a, button')
            .forEach((el) => el.setAttribute('tabindex', '0'));
        });
      },
    },
  });
}
swiperInit();

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
