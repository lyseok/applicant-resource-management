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
document.addEventListener('DOMContentLoaded', async () => {
  await loadMyPageData();
  setupEventListeners();
  setupEditMember();
});

/** 프로필 수정 **/
function setupEditMember() {
  const editWrapper = document.getElementById("editMember");
  if (!editWrapper) return;

  const editBtn = document.getElementById("editMemBtn");
  if (!editBtn) return;

  let originalHTML = editWrapper.innerHTML;

   editBtn.addEventListener("click", () => {
    editWrapper.innerHTML = `
      <div class="container">
        <div class="row g-3">
          <div class="col-md-6">
            <label class="form-label">프로필 사진</label>
            <input type="file" id="editImg" class="form-control">
            <img id="previewImg" src="${profileData.img}" class="mt-2 rounded" style="width:80px;height:80px;object-fit:cover;">
          </div>
          <div class="col-md-6">
            <label class="form-label">이름</label>
            <input type="text" id="editName" class="form-control" value="${profileData.name}">
          </div>
          <div class="col-md-6">
            <label class="form-label">생년월일</label>
            <input type="date" id="editBirth" class="form-control" value="${formatBirthForInput(profileData.birth)}">
          </div>
          <div class="col-md-6">
            <label class="form-label">성별</label>
            <select id="editGender" class="form-select">
              <option value="M" ${profileData.gender === "M" ? "selected" : ""}>남성</option>
              <option value="F" ${profileData.gender === "F" ? "selected" : ""}>여성</option>
            </select>
          </div>
          <div class="col-md-6">
			  <label class="form-label">희망 직무</label>
			  <select id="topJobCode" class="form-select mb-2">
			    <option value="">상위 직무 선택</option>
			  </select>
			  <div class="position-relative">
			    <input type="text" id="jobSearchInput" value="${profileData.job}" class="form-control" placeholder="하위 직무 검색">
			    <ul id="jobSuggestions" class="list-group position-absolute w-100" style="z-index:1000; display:none;"></ul>
			    <input type="hidden" id="hiddenJobCode" value="${profileData.preferredPosition}">
			</div>
		</div>
          <div class="col-md-6">
            <label class="form-label">연락처</label>
            <input type="text" id="editTel" class="form-control" value="${profileData.tel}">
          </div>
          <div class="col-md-8">
            <label class="form-label">주소</label>
            <div class="input-group">
              <input type="text" id="editAddr1" class="form-control" value="${profileData.address1}" readonly>
              <button class="btn btn-outline-secondary" type="button" id="btnSearchAddr">주소검색</button>
            </div>
          </div>
          <div class="col-md-4">
            <label class="form-label">상세주소</label>
            <input type="text" id="editAddr2" class="form-control" value="${profileData.address2}">
          </div>
        </div>
        <div class="d-flex justify-content-end gap-2 mt-3">
          <button class="btn btn-secondary" id="cancelEdit">취소</button>
          <button class="btn btn-success" id="saveEdit">저장</button>
        </div>
      </div>
    `;
	   bringTopJob(); // 상위 직무 데이터 불러오기

	   // 이벤트 바인딩
	   document.getElementById('topJobCode').addEventListener('change', async (e) => {
		   const selectedTopJobCode = e.target.value;
		   const resp = await axios.get(`/ajax/admin/jobCode/${selectedTopJobCode}`);
		   jobList = resp.data;
		   document.getElementById('jobSearchInput').value = '';
		   document.getElementById('hiddenJobCode').value = '';
		   document.getElementById('jobSuggestions').style.display = 'none';
	   });

	   document.getElementById('jobSearchInput').addEventListener('input', () => {
		   const keyword = document.getElementById('jobSearchInput').value.trim().toLowerCase();
		   const jobSuggestions = document.getElementById('jobSuggestions');
		   const hiddenJobCode = document.getElementById('hiddenJobCode');

		   if (!keyword) {
			   jobSuggestions.style.display = 'none';
			   return;
		   }

		   const matches = jobList.filter(({ jobName }) =>
			   jobName.toLowerCase().includes(keyword)
		   );

		   if (matches.length === 0) {
			   jobSuggestions.style.display = 'none';
			   return;
		   }

		   jobSuggestions.innerHTML = matches.map(
			   ({ jobCode, jobName }) =>
				   `<li class="list-group-item" data-code="${jobCode}">${jobName}</li>`
		   ).join('');

		   jobSuggestions.style.display = 'block';

		   jobSuggestions.querySelectorAll('li').forEach(li => {
			   li.addEventListener('click', () => {
				   document.getElementById('jobSearchInput').value = li.textContent;
				   hiddenJobCode.value = li.dataset.code;
				   jobSuggestions.style.display = 'none';
			   });
		   });
	   });

    // 사진 미리보기
    document.getElementById("editImg").addEventListener("change", (e) => {
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => document.getElementById("previewImg").src = e.target.result;
        reader.readAsDataURL(file);
      }
    });

    // 다음 주소 검색
    document.getElementById("btnSearchAddr").addEventListener("click", () => {
      new daum.Postcode({
        oncomplete: function(data) {
          document.getElementById("editAddr1").value = data.roadAddress || data.jibunAddress;
        }
      }).open();
    });

    // 취소 → 원래 상태로 복구
    document.getElementById("cancelEdit").addEventListener("click", () => {
      editWrapper.innerHTML = originalHTML;
      setupEditMember();
    });

    // 저장
    document.getElementById("saveEdit").addEventListener("click", async () => {
      const formData = new FormData();
      formData.append("memName", document.getElementById("editName").value.trim());
      formData.append("memBir", formatBirthForSave(document.getElementById("editBirth").value));
      formData.append("memGender", document.getElementById("editGender").value);
      formData.append("preferredPosition", document.getElementById("hiddenJobCode").value);
      formData.append("memTel", document.getElementById("editTel").value.trim());
      formData.append("memAdd1", document.getElementById("editAddr1").value.trim());
      formData.append("memAdd2", document.getElementById("editAddr2").value.trim());

      const imgFile = document.getElementById("editImg").files[0];
      if (imgFile) formData.append("memberImage", imgFile);

      try {
        await fetch("/ajax/member/mypage", { method: "POST", body: formData });
        alert("프로필이 수정되었습니다.");
        await loadMyPageData();
        setupEditMember();
      } catch (err) {
        console.error(err);
        alert("수정 중 오류가 발생했습니다.");
      }
    });
  });
}

function formatBirthForInput(birth) {
  if (!birth || birth.length !== 8) return ""; // 잘못된 값이면 빈칸
  return `${birth.slice(0,4)}-${birth.slice(4,6)}-${birth.slice(6,8)}`;
}

function formatBirthForSave(dateStr) {
  return dateStr ? dateStr.replace(/-/g, "") : "";
}

let profileData = {};
/** 마이페이지 데이터 로드 **/
async function loadMyPageData() {
  try {
    const response = await fetch('/ajax/member/mypage/info');
    if (!response.ok) throw new Error("데이터를 불러올 수 없습니다.");
    const data = await response.json();
    const user = data.user || {};
    const projects = data.projects || {};

    // 전역 profileData 저장
    profileData = {
      name: user.MEMNAME || "",
      birth: user.MEMBIR || "",
      gender: user.MEMGENDER || "M",
      job: user.PREFERREDPOSITION || "",
      email: user.MEMEMAIL || "",
      tel: user.MEMTEL || "",
      address1: user.MEMADD1 || "",
      address2: user.MEMADD2 || "",
      img: user.MEMIMG || "",
      preferredPosition: user.PREFERREDPOSITIONCODE || ""
    };

    /** --- DOM 값 세팅 (존재 여부 체크 후) --- **/
    const userNameEl = document.querySelector('.user-name');
    if (userNameEl)
      userNameEl.innerHTML = `${profileData.name || '이름 없음'} <i class="bi bi-chevron-right text-muted"></i>`;

    const userJobEl = document.querySelector('.user-job');
    if (userJobEl)
      userJobEl.textContent = (user.CAREER_YEAR_NAME || '경력 없음') + ' · ' + (profileData.job || '선호 직종 없음');

    const resumeLabelEl = document.querySelector('.resume-label');
    if (resumeLabelEl)
      resumeLabelEl.innerHTML = user.RESUMENO ? `<a href="/mypage/resume/${user.RESUMENO}">${user.RESUMENAME}</a>` : '대표 이력서 없음';

    const avatarEl = document.querySelector('.profile-avatar');
    if (avatarEl && profileData.img)
      avatarEl.innerHTML = `<img src="${profileData.img}" alt="프로필 이미지" class="rounded-circle" style="width:60px; height:60px; object-fit:cover;">`;

    const profileSection = document.querySelector('.profile-section');
    if (profileSection) {
      let profileDetails = profileSection.querySelector('.profile-details');
      if (!profileDetails) {
        profileDetails = document.createElement('div');
        profileDetails.classList.add('mt-3', 'profile-details');
        profileSection.appendChild(profileDetails);
      }
      profileDetails.innerHTML = `
        <p class="mb-1 text-muted"><i class="bi bi-envelope"></i> ${profileData.email}</p>
        <p class="mb-1 text-muted"><i class="bi bi-telephone"></i> ${profileData.tel}</p>
        <p class="mb-1 text-muted"><i class="bi bi-geo-alt"></i> ${profileData.address1 + ' ' + profileData.address2}</p>
      `;
    }

    /** --- 지원 현황 (숫자) --- **/
    const applyCountEl = document.querySelector('.apply-count');
    if (applyCountEl) applyCountEl.textContent = user.COUNT_APPLICATED || 0;

    const documentPassCountEl = document.querySelector('.document-pass-count');
    if (documentPassCountEl) documentPassCountEl.textContent = user.COUNT_PASS_RERP003 || 0;

    const finalPassCountEl = document.querySelector('.final-pass-count');
    if (finalPassCountEl) finalPassCountEl.textContent = user.COUNT_PASSER || 0;

    const failCountEl = document.querySelector('.fail-count');
    if (failCountEl) failCountEl.textContent = user.COUNT_FAILED || 0;

    const proposalCountEl = document.querySelector('.proposal-count');
    if (proposalCountEl) proposalCountEl.textContent = user.COUNT_PROPOSAL || 0;

    const interestCountEl = document.querySelector('.interest-count');
    if (interestCountEl) interestCountEl.textContent = user.COUNT_SCRAB || 0;

    const resumeViewCountEl = document.querySelector('.resume-view-count');
    if (resumeViewCountEl) resumeViewCountEl.textContent = user.COUNT_READ_RESUME || 0;

    const totalProjectsEl = document.querySelector('.total-projects');
    if (totalProjectsEl) totalProjectsEl.textContent = projects.TOTAL_PROJECTS ?? 0;

    const ongoingProjectsEl = document.querySelector('.ongoing-projects');
    if (ongoingProjectsEl) ongoingProjectsEl.textContent = projects.IN_PROGRESS_PROJECTS ?? 0;

    const completedProjectsEl = document.querySelector('.completed-projects');
    if (completedProjectsEl) completedProjectsEl.textContent = projects.FINISHED_PROJECTS ?? 0;

    const myPostingsEl = document.querySelector('.my-postings');
    if (myPostingsEl) myPostingsEl.textContent = user.COUNT_SCRAB ?? 0;

    /** 추천 공고 **/
    if (data.jobs && Array.isArray(data.jobs)) loadRecommendedJobs(data.jobs);

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

//==============================================================================
// 이벤트 리스너 설정
function setupEventListeners() {
  // 이력서 업데이트 링크
  document.querySelector('.update-link').addEventListener('click', (e) => {
    e.preventDefault();
    const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
    modal.show();
  });
  
  // ================================ 이력서 랜더링 ================================
let selectedResume = null;
let resumeListCache = [];
const resumeListDiv = document.getElementById('resumeList');
// JS파일 로드 시 즉시 호출!
(async function preloadResumeList() {
	try {
		const res = await axios.get(`/ajax/resume`);
		resumeListCache = res.data;
	} catch (err) {
		resumeListCache = [];
		// console.error('이력서 미리 로딩 실패', err);
	}
})();

// ... 아래는 동일하게 사용 가능
btnShowResumeList.onclick = function() {
	if (resumeListDiv.style.display !== 'none') {
		resumeListDiv.style.display = 'none';
		return;
	}

	// 이미 로딩되어 있으므로 바로 사용
	const resumeList = resumeListCache || [];
	selectedResumeCard.style.display = 'none';

	if (!Array.isArray(resumeList) || resumeList.length === 0) {
		resumeListDiv.innerHTML = `<div class="text-secondary py-2">등록된 이력서가 없습니다.</div>`;
		resumeListDiv.style.display = 'block';
		return;
	}

	// 카드 UI 렌더링
	resumeListDiv.innerHTML = resumeList.map(resume => `
    <div class="card mb-2 resume-card ${selectedResume && selectedResume.RESUME_NO === resume.RESUME_NO ? 'selected-card' : ''}" data-id="${resume.RESUME_NO}">
      <div class="card-body py-2 px-3">
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p class="mb-1 text-secondary fs-13 fw-500" >
              ${resume.UPDATE_DATE ? `${resume.UPDATE_DATE}` : ''}
            </p>
            <h6 class="mb-1 fw-500">${resume.RESUME_NAME || resume.RESUME_NAME}</h6>
            <div class="text-secondary" style="font-size:.96em;">
              ${resume.RESUME_MAIN_YN === 'Y' ? `<span class="badge bg-purple">대표 이력서</span>` : ''}
              ${resume.RESUME_SUBMIT_YN === 'Y' ? `<span class="badge bg-success">제출됨</span>` : ''}
            </div>
          </div>
          <div>
            ${resume.PHOTO ? `<img src="${resume.PHOTO}" alt="증명사진" style="width:38px; height:38px; border-radius:50%;">` : ''}
          </div>
        </div>
      </div>
    </div>
  `).join('');

	// 카드 클릭 이벤트로 선택
	resumeListDiv.querySelectorAll('.resume-card').forEach(card => {
		card.onclick = function() {
			const rid = this.getAttribute('data-id');
			selectedResume = resumeList.find(r => r.RESUME_NO === rid);
			renderSelectedResumeCard();
			selectedResumeCard.style.display = 'block';
			resumeListDiv.style.display = 'none';
		};
	});

	resumeListDiv.style.display = 'block';
};

// 이력서 카드(선택된 것) 랜더링 함수
function renderSelectedResumeCard() {
	const div = document.getElementById('selectedResumeCard');
	if (!selectedResume) {
		div.innerHTML = '<span class="text-secondary">이력서를 선택하세요.</span>';
		return;
	}
	div.innerHTML = `
    <div class="card mb-0 selected-card">
      <div class="card-body py-2 px-3">
        <p class="mb-1 text-secondary fs-13 fw-500">${selectedResume.UPDATE_DATE ? `${selectedResume.UPDATE_DATE}` : ''}</p>
        <h6 class="mb-0 fw-500">${selectedResume.RESUME_NAME || selectedResume.RESUME_NO}</h6>
      </div>
    </div>
  `;
}

// ================================= 지원 로직 ==================================
document.getElementById('btnChangeResume').addEventListener('click', async () => {
  if (!selectedResume) {
    alert('이력서를 선택하세요.');
    return;
  }

  try {
    // 서버로 대표 이력서 업데이트 요청
    await axios.post(`/ajax/member/mypage/${selectedResume.RESUME_NO}`); 

    loadMyPageData();

    // 모달 닫기
    const modal = bootstrap.Modal.getInstance(document.getElementById('applicationModal'));
    modal.hide();

    alert('대표 이력서가 업데이트되었습니다.');
  } catch (err) {
    console.error(err);
    alert('대표 이력서 업데이트에 실패했습니다.');
  }
});
//============================================================================================

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

async function bringTopJob() {
  const resp = await axios.get('/ajax/admin/jobCode');
  const list = resp.data;

  const topJobSelect = document.getElementById('topJobCode');
  topJobSelect.innerHTML += list.map(
    ({ topJobCode, topJobName }) =>
      `<option value="${topJobCode}">${topJobName}</option>`
  ).join('');
}

let jobList = [];
const jobSearchInput = document.getElementById('jobSearchInput');
const jobSuggestions = document.getElementById('jobSuggestions');
const hiddenJobCode = document.getElementById('hiddenJobCode');

document.getElementById('topJobCode').addEventListener('change', async (e) => {
  const selectedTopJobCode = e.target.value;
  const resp = await axios.get(`/ajax/admin/jobCode/${selectedTopJobCode}`);
  jobList = resp.data;
  jobSearchInput.value = '';
  hiddenJobCode.value = '';
  jobSuggestions.style.display = 'none';
});

jobSearchInput.addEventListener('input', () => {
  const keyword = jobSearchInput.value.trim().toLowerCase();
  if (!keyword) {
    jobSuggestions.style.display = 'none';
    return;
  }

  const matches = jobList.filter(({ jobName }) =>
    jobName.toLowerCase().includes(keyword)
  );

  if (matches.length === 0) {
    jobSuggestions.style.display = 'none';
    return;
  }

  jobSuggestions.innerHTML = matches.map(
    ({ jobCode, jobName }) =>
      `<li class="list-group-item" data-code="${jobCode}">${jobName}</li>`
  ).join('');

  jobSuggestions.style.display = 'block';
});

jobSuggestions.addEventListener('click', (e) => {
  if (e.target.tagName === 'LI') {
    const selectedName = e.target.textContent;
    const selectedCode = e.target.dataset.code;
    jobSearchInput.value = selectedName;
    hiddenJobCode.value = selectedCode;
    jobSuggestions.style.display = 'none';
  }
});

document.addEventListener('click', (e) => {
  if (!jobSearchInput.contains(e.target) && !jobSuggestions.contains(e.target)) {
    jobSuggestions.style.display = 'none';
  }
});

