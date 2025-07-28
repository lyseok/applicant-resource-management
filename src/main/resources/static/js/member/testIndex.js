// 페이지 로드 시 초기화
document.addEventListener('DOMContentLoaded', () => {
  initializeCalendar();
  loadRecommendedJobs();
  loadProjectInfo();
  setupEventListeners();
});

// 캘린더 초기화
function initializeCalendar() {
  const calendarEl = document.getElementById('calendar');
  const FullCalendar = window.FullCalendar; // Declare FullCalendar variable
  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek',
    },
    events: [
      {
        title: '면접 일정',
        start: '2024-01-15',
        color: '#28a745',
      },
      {
        title: '프로젝트 마감',
        start: '2024-01-20',
        color: '#dc3545',
      },
      {
        title: '회사 설명회',
        start: '2024-01-25',
        color: '#007bff',
      },
    ],
    eventClick: (info) => {
      alert('일정: ' + info.event.title);
    },
  });
  calendar.render();
}

// 추천 공고 로드
function loadRecommendedJobs() {
  const jobs = [
    {
      id: 1,
      title: '[웹린지] 프론트엔드 개발자 (3년 이상)',
      company: '(주)하이트큐브',
      badge: '합격률상 200명',
      imageClass: 'yellow',
      logo: '웹',
    },
    {
      id: 2,
      title: '데이터 커머스 제주국 프로젝트 개발(ReactJS, NextJS, ...)',
      company: '이그레브',
      badge: '합격률상 100명',
      imageClass: 'purple',
      logo: 'IG',
    },
    {
      id: 3,
      title: 'Frontend Engineer',
      company: '(주)바비톡',
      badge: '합격률상 100명',
      imageClass: 'blue',
      logo: 'FE',
    },
    {
      id: 4,
      title: 'Sr. Frontend Engineer',
      company: '(주)브레이브모바일',
      badge: '합격률상 100명',
      imageClass: 'blue',
      logo: 'SR',
    },
  ];

  const container = document.getElementById('recommendedJobs');
  container.innerHTML = jobs
    .map(
      (job) => `
        <div class="col-md-3 col-sm-6 mb-3">
            <div class="job-card" onclick="viewJobDetail(${job.id})">
                <div class="job-image ${job.imageClass}">
                    <div class="job-badge">${job.badge}</div>
                    <div class="company-logo">${job.logo}</div>
                    <button class="bookmark-btn" onclick="toggleBookmark(event, ${job.id})">
                        <i class="bi bi-bookmark"></i>
                    </button>
                </div>
                <div class="job-content">
                    <h6 class="job-title">${job.title}</h6>
                    <p class="company-name">${job.company}</p>
                </div>
            </div>
        </div>
    `
    )
    .join('');
}

// 프로젝트 정보 로드
function loadProjectInfo() {
  // 실제 데이터는 API에서 가져와야 합니다
  const projectData = {
    total: 5,
    ongoing: 2,
    completed: 3,
    myPostings: 1,
  };

  document.getElementById('totalProjects').textContent = projectData.total;
  document.getElementById('ongoingProjects').textContent = projectData.ongoing;
  document.getElementById('completedProjects').textContent =
    projectData.completed;
  document.getElementById('myPostings').textContent = projectData.myPostings;
}

// 이벤트 리스너 설정
function setupEventListeners() {
  // 이력서 업데이트 링크
  document.querySelector('.update-link').addEventListener('click', (e) => {
    e.preventDefault();
    alert('이력서 업데이트 페이지로 이동합니다.');
  });

  // 더 보기 링크
  document.querySelector('.more-link').addEventListener('click', (e) => {
    e.preventDefault();
    alert('더 많은 추천 공고를 보여줍니다.');
  });

  // 액션 버튼들
  document.querySelectorAll('.action-btn').forEach((btn) => {
    btn.addEventListener('click', function () {
      const cardTitle =
        this.closest('.action-card').querySelector('.card-title').textContent;
      alert(`${cardTitle} 관련 페이지로 이동합니다.`);
    });
  });
}

// 공고 상세 보기
function viewJobDetail(jobId) {
  alert(`공고 ID ${jobId}의 상세 페이지로 이동합니다.`);
}

// 북마크 토글
function toggleBookmark(event, jobId) {
  event.stopPropagation();
  const btn = event.currentTarget;
  const icon = btn.querySelector('i');

  if (icon.classList.contains('bi-bookmark')) {
    icon.classList.remove('bi-bookmark');
    icon.classList.add('bi-bookmark-fill');
    btn.style.color = '#28a745';
    alert(`공고 ID ${jobId}를 북마크에 추가했습니다.`);
  } else {
    icon.classList.remove('bi-bookmark-fill');
    icon.classList.add('bi-bookmark');
    btn.style.color = '#6c757d';
    alert(`공고 ID ${jobId}를 북마크에서 제거했습니다.`);
  }
}

// 애니메이션 효과
function animateNumbers() {
  const numbers = document.querySelectorAll('.status-number');
  numbers.forEach((num) => {
    const target = Number.parseInt(num.textContent);
    let current = 0;
    const increment = target / 20;
    const timer = setInterval(() => {
      current += increment;
      if (current >= target) {
        current = target;
        clearInterval(timer);
      }
      num.textContent = Math.floor(current);
    }, 50);
  });
}

// 페이지 로드 완료 후 숫자 애니메이션 실행
window.addEventListener('load', () => {
  setTimeout(animateNumbers, 500);
});

// 태그 입력 기능 수정된 JavaScript
document.addEventListener('DOMContentLoaded', function () {
  const tagInput = document.querySelector('.tag-input');
  const tagsDisplay = document.querySelector('.tags-display');
  const tagContainer = document.querySelector('.tag-input-container');
  const tagResetBtn = document.querySelector('.tag-reset-btn');
  let tagList = [];
  let params = {}; // Declare params variable
  let fetchData = function () {}; // Declare fetchData function

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
        <span class="tag-chip">
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
