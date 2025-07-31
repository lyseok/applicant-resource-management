document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById('calendar');
  let selectedEvent = null;

  const modal = new bootstrap.Modal(document.getElementById('eventViewModal'));

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko',
    timeZone: 'local',
    editable: true,
    selectable: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    eventSources: [
      {
        url: '/ajax/schedule/recruit',
        method: 'GET',
        color: '#4e73df',
        editable: false
      },
      {
        url: '/ajax/schedule/custom',
        method: 'GET',
        color: '#f6c23e',
        editable: true
      }
    ],
    select: function(info) {
      const title = prompt("일정 제목을 입력하세요:");
      if (title) {
        fetch('/ajax/schedule/custom', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            scheduleName: title,
            scheduleStartDate: info.startStr,
            scheduleEndDate: info.endStr
          })
        }).then(() => calendar.refetchEvents());
      }
    },
    eventClick: function(info) {
      selectedEvent = info.event;

      // 값 세팅
		document.getElementById('modalTitleInput').value = selectedEvent.title;
		document.getElementById('modalStartInput').value = selectedEvent.start.toISOString().slice(0, 16);
		document.getElementById('modalEndInput').value = selectedEvent.end ? selectedEvent.end.toISOString().slice(0, 16) : '';

      // 사용자 일정만 수정/삭제 버튼 보이게
      const customControls = document.getElementById('customControls');
      if (selectedEvent.source.url.includes('/custom')) {
        customControls.style.display = 'flex';
      } else {
        customControls.style.display = 'none';
      }

      modal.show();
    },
    eventDrop: function(info) {
      if (info.event.source.url.includes('/custom')) {
        fetch('/ajax/schedule/custom/update/' + info.event.id, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
			scheduleNo: info.event.id,
			scheduleName: info.event.title,
            scheduleStartDate: info.event.start.toISOString(),
            scheduleEndDate: info.event.end ? info.event.end.toISOString() : null
          })
        });
      }
    }
  });

  document.getElementById('editBtn').addEventListener('click', function () {
  const newTitle = document.getElementById('modalTitleInput').value;
  const newStart = document.getElementById('modalStartInput').value;
  const newEnd = document.getElementById('modalEndInput').value;

  if (newTitle && newStart) {
    fetch('/ajax/schedule/custom/update/' + selectedEvent.id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        scheduleNo: selectedEvent.id,
        scheduleName: newTitle,
        scheduleStartDate: newStart,
        scheduleEndDate: newEnd || null
      })
    }).then(() => {
      modal.hide();
      calendar.refetchEvents();
    });
  } else {
    alert('제목과 시작 시간은 필수입니다!');
  }
});

  // 삭제 버튼
  document.getElementById('deleteBtn').addEventListener('click', function () {
    if (confirm("정말 삭제하시겠습니까?")) {
      fetch('/ajax/schedule/custom/' + selectedEvent.id, { method: 'DELETE' })
        .then(() => {
          modal.hide();
          calendar.refetchEvents();
        });
    }
  });

  calendar.render();
});

// 페이지 로드 시 초기화
document.addEventListener('DOMContentLoaded', () => {
  loadMyPageData();
  setupEventListeners();
});

/** 마이페이지 데이터 로드 **/
async function loadMyPageData() {
  try {
    const response = await fetch('/ajax/member/mypage/info');
    if (!response.ok) throw new Error("데이터를 불러올 수 없습니다.");
    const data = await response.json();
    const user = data.user || {};
	const projects = data.projects || {};

    /** ------------------ 1. 프로필 ------------------ **/
    document.querySelector('.user-name').innerHTML = `${user.MEMNAME || '이름 없음'} <i class="bi bi-chevron-right text-muted"></i>`;
    document.querySelector('.user-job').textContent = user.RESUMENO ? '대표 이력서 등록됨' : '이력서 없음';

    if (user.MEMIMG) {
      const avatar = document.querySelector('.profile-avatar');
      avatar.innerHTML = `<img src="${user.MEMIMG}" alt="프로필 이미지" class="rounded-circle" style="width:60px; height:60px; object-fit:cover;">`;
    }

    const profileSection = document.querySelector('.profile-section');
    let profileDetails = profileSection.querySelector('.profile-details');
    if (!profileDetails) {
      profileDetails = document.createElement('div');
      profileDetails.classList.add('mt-3', 'profile-details');
      profileSection.appendChild(profileDetails);
    }
    profileDetails.innerHTML = `
      <p class="mb-1 text-muted"><i class="bi bi-envelope"></i> ${user.MEMEMAIL || '-'}</p>
      <p class="mb-1 text-muted"><i class="bi bi-telephone"></i> ${user.MEMTEL || '-'}</p>
      <p class="mb-1 text-muted"><i class="bi bi-geo-alt"></i> ${(user.MEMADD1 || '') + ' ' + (user.MEMADD2 || '')}</p>
    `;

    /** ------------------ 2. 지원 현황 ------------------ **/
    document.querySelector('.apply-count').textContent = user.COUNT_APPLICATED || 0;
    document.querySelector('.document-pass-count').textContent = user.COUNT_PASS_RERP003 || 0;
    document.querySelector('.final-pass-count').textContent = user.COUNT_PASSER || 0;
    document.querySelector('.fail-count').textContent = user.COUNT_FAILED || 0;

    /** ------------------ 3. 받은 제안 ------------------ **/
    document.querySelector('.proposal-count').textContent = user.COUNT_PROPOSAL || 0;
    document.querySelector('.interest-count').textContent = user.COUNT_SCRAB || 0;
    document.querySelector('.resume-view-count').textContent = user.COUNT_READ_RESUME || 0;

    /** ------------------ 4. 프로젝트 ------------------ **/
	document.querySelector('.total-projects').textContent = projects.TOTAL_PROJECTS ?? 0;
	document.querySelector('.ongoing-projects').textContent = projects.IN_PROGRESS_PROJECTS ?? 0;
	document.querySelector('.completed-projects').textContent = projects.FINISHED_PROJECTS ?? 0;
	document.querySelector('.my-postings').textContent = user.COUNT_SCRAB ?? 0;

    /** ------------------ 5. 추천 공고 ------------------ **/
    if (data.jobs && Array.isArray(data.jobs)) {
      loadRecommendedJobs(data.jobs);
    }

    /** ------------------ 6. 숫자 애니메이션 ------------------ **/
    animateNumbers();

  } catch (error) {
    console.error(error);
    alert("마이페이지 데이터를 불러오는데 실패했습니다.");
  }
}

/** 추천 공고 렌더링 **/
function loadRecommendedJobs(jobs) {
  const container = document.getElementById('recommendedJobs');
  container.innerHTML = jobs.map(job => `
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
  `).join('');
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

function animateNumbers() {
  const numbers = document.querySelectorAll('.status-number');
  numbers.forEach((num) => {
    const target = Number(num.textContent);
    if (isNaN(target)) return; // 숫자 아니면 스킵

    let current = 0;
    const step = Math.max(1, Math.floor(target / 20)); // 최소 1씩 증가
    num.textContent = 0;

    const timer = setInterval(() => {
      current += step;
      if (current >= target) {
        current = target;
        clearInterval(timer);
      }
      num.textContent = current;
    }, 30);
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
