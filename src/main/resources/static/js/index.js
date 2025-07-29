const topListBody = document.querySelector('.goodsBox.observe-job');
const middleListBody = document.querySelector('.prd_list.plus');
const bottomListBody = document.querySelector('.bottom_list');

const slideList = document.querySelector('.sub_curation_list');

const params = {
  page: 1,
  pageSize: 7,
  sort: 'RD',
};

let myCompanyList;
let myRecruitList;

// function fetchTopRecruitData() {
//   axios.get('/ajax/recruit/search', { params }).then((res) => {
//     const resp = res.data;

//     myCompanyList = resp.myScrabCompany;
//     myRecruitList = resp.myScrabRecruit;

//     topRecruitNoticeInit(resp.data); // 목록 그리기 함수
//     fetchMiddleRecruitData();
//   });
// }

function shuffleAndTrim(arr, premium = false) {
  // 1. 배열 섞기 (Fisher-Yates 알고리즘)
  for (let i = arr.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [arr[i], arr[j]] = [arr[j], arr[i]];
  }

  // 2. 조건에 따라 자르기
  const length = arr.length;
  let trimLength;
  if (premium) {
    trimLength = length % 4 === 0 ? length - 1 : length - (length % 4) + 3;
  } else {
    trimLength = length - (length % 4);
  }
  return arr.slice(0, trimLength);
}

function fetchTopRecruitData() {
  axios.get('/ajax/recruit/main_p').then((res) => {
    const resp = res.data;
    const shuffledData = shuffleAndTrim(resp.data, true);
    myCompanyList = resp.myScrabCompany;
    myRecruitList = resp.myScrabRecruit;

    topRecruitNoticeInit(shuffledData); // 목록 그리기 함수
    fetchMiddleRecruitData();
  });
}
fetchTopRecruitData();

function topRecruitNoticeInit(data) {
  if (!topListBody) return;
  let firstImg = '';
  topListBody.innerHTML = data
    .map((item, idx) => {
      const isFirst = idx === 0;
      const liClass = isFirst ? 'first superTop uid_coupangcls' : 'superpower';
      const summaryId = isFirst ? 'SummaryView_random_no' : 'SummaryView_no';
      const scrapClass = myRecruitList.includes(item.recruitmentNo)
        ? ' on'
        : '';
      if (isFirst) {
        firstImg = item.recruitmentImg;
      }
      return `
      <li class="${liClass}">
        <a href="/recruit_notice/${
          item.recruitmentNo
        }" class="goodsBox-info info">
          <span class="logo ${item.comLogo ? '' : 'opacity-25'}">
            <img src="${item.comLogo ? item.comLogo :'/dist/assets/images/logo.png'}"
              alt="${item.comName} 로고">
          </span>
          <span class="company">
            <span class="B_MyAd_coupangcls"></span>
            ${item.comName}
          </span>
          <span class="title">
            <span>${item.recruitmentTitle}</span>
          </span>
          <span class="wrap">
            <span class="badge">
              👉
              <span>${item.jobName} 모집중</span>
            </span>
            <span class="date">${getTimeAgo(item.recruitmentFinishDate)}</span>
          </span>
          ${
            !isFirst
              ? `
          <span class="ani">
            <img src="${item.recruitmentImg ? item.recruitmentImg : 'https://placehold.co/298x269'}" class="superpower_1" alt="배경이미지">
          </span>`
              : ''
          }
        </a>
        <div class="goodsBox-scrap${scrapClass} main-scrap-btn" data-recruitmentno="${
        item.recruitmentNo
      }">스크랩</div>
        <a href="javascript:void(0)" id="${summaryId}" class="goodsBox-summary">요약보기</a>
      </li>
      `;
    })
    .join('');
  const superTopInfo = document.querySelector(
    '#MainSuper > .goodsBox > li.superTop.uid_coupangcls > .goodsBox-info'
  );

  if (superTopInfo) {
    superTopInfo.style.backgroundImage = 'url(' + firstImg + ')';
  }
  topListBody.addEventListener('click', async (e) => {
    const btn = e.target.closest('div');
    if (!btn) return;

    // 스크랩 버튼
    if (btn.classList.contains('main-scrap-btn')) {
      console.log(btn);
      const recruitmentNo = btn.dataset.recruitmentno;
      console.log('스크랩 버튼 클릭:', recruitmentNo);
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

function fetchMiddleRecruitData() {
  axios.get('/ajax/recruit/main_middle').then((res) => {
    const resp = res.data;
    const shuffledData = shuffleAndTrim(resp.data);
    middleRecruitNoticeInit(shuffledData); // 목록 그리기 함수
    fetchBottomRecruitData();
  });
}

function middleRecruitNoticeInit(data) {
  if (!middleListBody) return;
  middleListBody.innerHTML = data
    .map((item, idx) => {
      console.log('middleRecruitNoticeInit', item.recruitmentNo);
      const scrapClass = myRecruitList.includes(item.recruitmentNo)
        ? ' on'
        : '';
      return `
      <li class="option">
        <a href="javascript:void(0)" class="link_box track_event">
          <span class="logo ${item.comLogo ? '' : 'opacity-25'}">
            <img src="${item.comLogo ? item.comLogo : '/dist/assets/images/logo.png'}" class="img" alt="${
        item.comName
      } 로고" loading="lazy"/>
          </span>
          <span class="c_name">${item.comName}</span>
          <span class="title">${item.jobName}<br>경력직원 채용</span>
          <span class="title_ex">상반기 부문별<br>경력직원 채용</span>
          <span class="bg_ex">
            <img
              src="${item.recruitmentImg ? item.recruitmentImg : 'https://placehold.co/96x96'}"
              alt="배경이미지" loading="lazy">
          </span>
          <span class="tags">
            <span>${item.cityName} ${item.districtName}</span>
            <span>${item.yearCodeName}</span>
          </span>
          <span class="date">${getTimeAgo(item.recruitmentFinishDate)}</span>
        </a>
        <button class="btn_scrap track_event main-scrap-btn ${scrapClass}" data-recruitmentno="${
        item.recruitmentNo
      }">
          <span class="material-symbols-outlined" style="${
            scrapClass === ' on'
              ? 'color: #ffe002;background: none; '
              : 'background: none;'
          }">kid_star</span>
          <span class="blind">스크랩 버튼</span>
        </button>
      </li>
      
      `;
    })
    .join('');
  middleListBody.addEventListener('click', async (e) => {
    console.log('click', e.target);
    const btn = e.target.closest('button');
    if (!btn) return;

    // 스크랩 버튼
    if (btn.classList.contains('main-scrap-btn')) {
      console.log(btn);
      const recruitmentNo = btn.dataset.recruitmentno;
      console.log('스크랩 버튼 클릭:', recruitmentNo);
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

function fetchBottomRecruitData() {
  axios.get('/ajax/recruit/main_bottom').then((res) => {
    const resp = res.data;
    const shuffledData = shuffleAndTrim(resp.data);
    bottomRecruitNoticeInit(shuffledData); // 목록 그리기 함수
  });
}

function bottomRecruitNoticeInit(data) {
  console.log(bottomListBody);
  if (!bottomListBody) return;
  bottomListBody.innerHTML = data
    .map((item, idx) => {
      console.log('bottomRecruitNoticeInit', item.recruitmentNo);
      const scrapClass = myRecruitList.includes(item.recruitmentNo)
        ? ' on'
        : '';
      return `
      <li>
        <a href="javascript:void(0)" class="link_box track_event">
          <span class="logo ${item.comLogo ? '' : 'opacity-25'}">
            <img
              src="${item.comLogo? item.comLogo:'/dist/assets/images/logo.png'}"
              class="img" alt="${item.comName}" loading="lazy">
          </span>
          <span class="c_name">${item.comName}</span>
          <span class="title">${item.recruitmentTitle}</span>
          <span class="badge">
            👉
            <span>${item.jobName}</span>
          </span>
          <span class="date">${getTimeAgo(item.recruitmentFinishDate)}</span>
        </a>
        <button class="btn_scrap track_event main-scrap-btn ${scrapClass}" data-recruitmentno="${
        item.recruitmentNo
      }">
          <span class="material-symbols-outlined" style="${
            scrapClass === ' on'
              ? 'color: #ffe002;background: none; '
              : 'background: none;'
          }">kid_star</span>
          <span class="blind">스크랩 버튼</span>
        </button>
      </li>
      `;
    })
    .join('');
  bottomListBody.addEventListener('click', async (e) => {
    console.log('click', e.target);
    const btn = e.target.closest('button');
    if (!btn) return;

    // 스크랩 버튼
    if (btn.classList.contains('main-scrap-btn')) {
      console.log(btn);
      const recruitmentNo = btn.dataset.recruitmentno;
      console.log('스크랩 버튼 클릭:', recruitmentNo);
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

function getTimeAgo(dateString) {
  const now = new Date();
  const posted = new Date(dateString);
  const diffMs = now - posted;
  const diffSec = Math.floor(diffMs / 1000);
  const diffMin = Math.floor(diffSec / 60);
  const diffHour = Math.floor(diffMin / 60);
  const diffDay = Math.floor(diffHour / 24);

  if (diffSec < 60) return '마감임박';
  if (diffMin < 60) return `마감임박`;
  if (diffHour < 24) return `마감임박`;
  return `D-${diffDay}`;
}
