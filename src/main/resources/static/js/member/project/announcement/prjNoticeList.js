// ================== 파라미터 및 paramsSerializer 정의 ==================
const params = {
  page: 1,
  pageSize: 10,
  sort: 'date',
  status: 'all',
  search: '',
};

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

function timeAgo(dateString) {
  const now = new Date();
  const date = new Date(dateString.replace(/-/g, '/'));
  const diffMs = now - date;
  const diffSec = Math.floor(diffMs / 1000);
  if (diffSec < 60) return '방금 전';
  const diffMin = Math.floor(diffSec / 60);
  if (diffMin < 60) return diffMin + '분 전';
  const diffHour = Math.floor(diffMin / 60);
  if (diffHour < 24) return diffHour + '시간 전';
  const diffDay = Math.floor(diffHour / 24);
  return diffDay + '일 전';
}
// ===================== 데이터 가공 함수 =====================
function convertServerPosts(serverPosts) {
  return serverPosts.map((item) => ({
    postId: item.prjAnncNo,
    title: item.prjEmpTitle,
    content: item.prjAnncContent || '',
    nickname: item.memName,
    image: item.memImg,
    job: item.prjTopic || '',
    date: item.anncCreateDate || '',
    like: 0,
    comment: 0,
    view: item.prjAnncHit || 0,
    tags: (item.prjAnncBoardTagList || [])
      .map((t) => t?.tag?.tagName)
      .filter(Boolean),
    anncEndYn: item.anncEndYn, // 모집마감여부(N/Y)
  }));
}

let allPosts = [];

// renderPostList 내부
function renderPostList(posts) {
  const area = document.querySelector('.PostList_postList ul');

  area.innerHTML = (posts || [])
    .map(
      (post) => /* html */ `
    <li class="PostList_postItem board-list-item" data-id="${post.postId}">
      <a class="PostList_link" href="javascript:void(0)">
        <div class="PostList_post">
          <div class="ListItem_post">
            <div class="ListItem_post_item">
            	<div class="d-flex align-items-center gap-3 mb-2">
            		<span class="badge badge-recruit  ${post.anncEndYn === 'Y' ? 'opacity-50' : ''}">${post.anncEndYn === 'Y' ? '모집완료' : '모집중'}</span>
              	<h2 class="ListItem_post_title mb-0">${post.title}</h2>
              </div>
              <div class="fs-13 text-violet110 mb-1">#${post.job}</div>
              <div class="fs-14 text-truncate">
                ${
                  post.content.length > 80
                    ? post.content.slice(0, 80) + '...'
                    : post.content
                }
              </div>
              <div class="d-flex gap-2 my-2">
                ${(post.tags || []).map((tag) => `<span class="badge badge-tag">${tag}</span>`).join('')}
              </div>
            </div>
          </div>
          <div class="ListItem_info">
            <div class="ListItem_profile">
              <div class="ListItem_profile_pic">
                <img src="${post.image != null ? post.image : 'https://placehold.co/28x28'}"
                  alt="${post.nickname || ''} 프로필 이미지" />
              </div>
              <div class="ListItem_profile_info">
                <div class="ListItem_profile_infoDiv">
                  <div class="ListItem_profile_nickName">${
                    post.nickname || '익명'
                  }</div>
                </div>
                <div class="ListItem_profile_infoDiv">
                  <div class="ListItem_profile_jobInfo">
                    <div class="ListItem_profile_date">${timeAgo(
                      post.date
                    )}</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="ListItem_profile_postInfoList">
              <div class="ListItem_profile_postInfoItem">
                <span>조회</span><strong>${post.view}</strong>
              </div>
            </div>
          </div>
        </div>
      </a>
    </li>
  `
    )
    .join('');
  // 게시글 클릭시 상세페이지로 이동
  document.querySelectorAll('.board-list-item').forEach((item) => {
    item.addEventListener('click', function () {
      const prjAnncNo = this.getAttribute('data-id');
      if (prjAnncNo) {
        window.location.href =
          '/board/project/detail?prjAnncNo=' + encodeURIComponent(prjAnncNo);
      }
    });
  });
}

function renderTopPostList(posts) {
  const area = document.querySelector('.recommend_list_wrap .swiper-wrapper');

  area.innerHTML = (posts || [])
    .map(
      (post) => /* html */ `
    <div class="swiper-slide swiper-slide-next Recommend_recommendItem">
      <div class="Recommend_recommendItem_inner">
        <a href="javascript:void(0)">
          <div class="Recommend_chipArea">
            <span class="Chip_chipPick">PICK</span>
          </div>
          <h3 class="Recommend_post_title">${post.title}</h3>
          <div class="Recommend_post_content">
            ${
              post.content.length > 80
                ? post.content.slice(0, 80) + '...'
                : post.content
            }
          </div>
          <div class="Recommend_profile_postInfoList">
            <div class="Recommend_profile_postInfoItem">
              <span>작성자</span><strong>${post.nickname || '익명'}</strong>
            </div>
            <div class="Recommend_profile_divider"></div>
            <div class="Recommend_profile_postInfoItem">
              <span>조회</span><strong>${post.view}</strong>
            </div>
          </div>
        </a>
      </div>
    </div>
  `
    )
    .join('');
  // 게시글 클릭시 상세페이지로 이동
  document.querySelectorAll('.board-list-item').forEach((item) => {
    item.addEventListener('click', function () {
      const prjAnncNo = this.getAttribute('data-id');
      if (prjAnncNo) {
        window.location.href =
          '/board/project/detail?prjAnncNo=' + encodeURIComponent(prjAnncNo);
      }
    });
  });
}

// ================ 페이저 렌더링 함수 ================
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

// ================ 데이터 요청 함수(fetchData) ================
function fetchData() {
  const paramsString = paramsSerializer(params);
  axios
    .get('/ajax/board/project?' + paramsString)
    .then((res) => {
      // 서버 응답이 talentPoolList.js와 다를 경우 맞게 수정
      // 예상: res.data.data: 게시글 배열, res.data.totalCnt: 전체 게시글 수
      allPosts = convertServerPosts(res.data.data);
      renderPostList(allPosts);
      const totalPage = Math.ceil(res.data.totalCnt / params.pageSize);
      renderPager(totalPage, params.page);
    })
    .catch(() => {
      allPosts = [];
      renderPostList();
    });
}

// ================ 페이지 박스 클릭 이벤트 위임 ================
document.addEventListener('DOMContentLoaded', function () {
  const pageBox = document.querySelector('.PageBox');
  if (pageBox) {
    pageBox.addEventListener('click', function (e) {
      if (e.target.classList.contains('page')) {
        const page = Number(e.target.dataset.page);
        params.page = page;
        fetchData();
      } else if (e.target.classList.contains('BtnNext')) {
        params.page += 1;
        fetchData();
      }
    });
  }
});

// ================ 상태 라디오 클릭 이벤트 ================
document.addEventListener('DOMContentLoaded', function () {
  const statusRadios = document.querySelectorAll('input[name="category"]');
  statusRadios.forEach((radio) => {
    radio.addEventListener('change', function () {
      params.status = this.value;
      params.page = 1; // 상태 변경 시 첫 페이지로
      fetchData();
    });
  });
});

// ================ 정렬 Select 변경 이벤트 ================
document.addEventListener('DOMContentLoaded', function () {
  const sortSelect = document.querySelector('.Select_root');
  if (sortSelect) {
    sortSelect.addEventListener('change', function () {
      params.sort = this.value;
      params.page = 1; // 정렬 변경 시 첫 페이지로
      fetchData();
    });
  }
});

// ================== 상단 게시글 리스트 ==================
function fetchTopData() {
  const paramsString = paramsSerializer({
    page: 1,
    pageSize: 4,
    sort: 'pop',
    status: 'status_n',
  });
  axios
    .get('/ajax/board/project?' + paramsString)
    .then((res) => {
      renderTopPostList(convertServerPosts(res.data.data));
    })
    .catch(() => {
      allPosts = [];
      renderPostList();
    });
}

// ================ 최초 데이터 로드 ================
fetchData();
fetchTopData();

// 태그 입력 기능 수정된 JavaScript
document.addEventListener('DOMContentLoaded', function () {
  const tagInput = document.querySelector('.tag-input');
  const tagsDisplay = document.querySelector('.tags-display');
  const tagContainer = document.querySelector('.tag-input-container');
  const tagResetBtn = document.querySelector('.tag-reset-btn');
  let tagList = [];

  if (tagInput && tagsDisplay && tagContainer) {
    // 컨테이너 클릭 시 input에 포커스
    tagContainer.addEventListener('click', function (e) {
      if (e.target === tagContainer || e.target === tagsDisplay) {
        tagInput.focus();
      }
    });

    // 태그 입력 처리
    tagInput.addEventListener('keypress', function (e) {
      if (e.key === 'Enter' && this.value.trim()) {
        e.preventDefault();
        addTag(this.value.trim());
        this.value = '';
        updatePlaceholder();
      }
    });

    // 백스페이스로 태그 삭제
    tagInput.addEventListener('keydown', function (e) {
      if (e.key === 'Backspace' && this.value === '' && tagList.length > 0) {
        removeTag(tagList[tagList.length - 1]);
      }
    });

    // 태그 추가 함수
    function addTag(tagText) {
      if (!tagList.includes(tagText) && tagText.length > 0) {
        tagList.push(tagText);
        renderTags();
      }
    }

    // 태그 제거 함수
    function removeTag(tagText) {
      tagList = tagList.filter((t) => t !== tagText);
      renderTags();
      updatePlaceholder();
    }

    // 태그 렌더링
    function renderTags() {
      tagsDisplay.innerHTML = tagList
        .map(
          (tag) => `
        <span class="badge-tag d-flex">
          ${tag}
          <span class="remove-tag" data-tag="${tag}">×</span>
        </span>
      `
        )
        .join('');

      updatePlaceholder();
    }

    // placeholder 업데이트
    function updatePlaceholder() {
      if (tagList.length > 0) {
        tagInput.placeholder = '';
      } else {
        tagInput.placeholder = '태그로 검색해보세요!';
      }
    }

    // 태그 삭제 이벤트 위임
    tagsDisplay.addEventListener('click', function (e) {
      if (e.target.classList.contains('remove-tag')) {
        const tagText = e.target.getAttribute('data-tag');
        removeTag(tagText);
      }
    });

    // 초기화 버튼
    if (tagResetBtn) {
      tagResetBtn.addEventListener('click', function () {
        tagList = [];
        renderTags();
        tagInput.focus();
      });
    }

    // 검색 버튼 이벤트 (기존 코드와 연동)
    const searchBtn = document.getElementById('searchBtn');
    if (searchBtn) {
      searchBtn.addEventListener('click', function () {
        const keyword = document.querySelector('.search-input').value.trim();

        // 기존 params 객체 업데이트
        if (typeof params !== 'undefined') {
          params.search = keyword;
          params.tagList = tagList;
          params.page = 1;

          // 기존 fetchData 함수 호출
          if (typeof fetchData === 'function') {
            fetchData();
          }
        }

        console.log('검색 키워드:', keyword);
        console.log('선택된 태그:', tagList);
      });
    }

    // Enter 키로도 검색 가능
    document
      .querySelector('.search-input')
      .addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
          searchBtn.click();
        }
      });
  }
});

const writeBtn = document.getElementById('writeBtn');
writeBtn.addEventListener("click" , function(){
	location.href = `/board/project/form`
})
