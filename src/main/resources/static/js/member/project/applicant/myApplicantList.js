const STATUS_MAP = {
  "PRST-000": "참여 거절",
  "PRST-001": "지원 완료",
  "PRST-002": "참여 요청",
  "PRST-003": "참여 완료"
};

// ===================== 데이터 가공 함수 =====================
function convertServerPosts(serverPosts) {
  return serverPosts.map(item => ({
    postId: item.prjAnncNo,
    title: item.prjEmpTitle,
    content: item.prjAnncContent || '',
    nickname: item.memName,
    job: item.prjTopic || '',
    date: item.anncCreateDate || '',
    like: 0,
    comment: 0,
    view: item.prjAnncHit || 0,
    tags: (item.prjAnncBoardTagList || []).map(t => t?.tag?.tagName).filter(Boolean),
    anncEndYn: item.anncEndYn, // 모집마감여부(N/Y)
    // 본인 신청 현황!
    myStatus: (item.prjRcrtPsncntList?.[0]?.aplcntList?.[0]?.aplcntStatusCode) || '',
    prjAplcntNo: (item.prjRcrtPsncntList?.[0]?.aplcntList?.[0]?.prjAplcntNo) || '',
  }));
}

let allPosts = [];
let filterStatus = "all"; // all | open | closed
let sortType = "latest"; // latest | like | view
let searchTitle = "";

// ================ 데이터 요청 및 초기 렌더 =================
axios.get('/ajax/board/project/applicantList')
  .then(res => {
    allPosts = convertServerPosts(res.data);
    renderTags();
    renderPostList();
  })
  .catch(err => {
    allPosts = [];
    renderPostList();
  });

  // --- 태그 카드 그리기
let searchTags = [];

// 태그 카드 UI
function renderTags() {
  const tagListBox = document.getElementById('tagList');
  if (tagListBox)
    tagListBox.innerHTML = searchTags.map((t, i) =>
      `<span class="tag-chip badge bg-light text-dark me-1 mb-1">${t}
        <span class="remove-tag" style="cursor:pointer" onclick="removeTag(${i})">&times;</span>
      </span>`
    ).join('');
}
window.removeTag = function(idx) {
  searchTags.splice(idx, 1);
  renderTags();
};

// renderPostList 내부
function renderPostList() {
  const area = document.getElementById('boardList');
  let posts = allPosts.slice();

  // ... 모집중/마감 필터 및 제목 검색 유지
  if (filterStatus === 'open') posts = posts.filter(p => p.anncEndYn === 'N');
  else if (filterStatus === 'closed') posts = posts.filter(p => p.anncEndYn === 'Y');
  if (searchTitle) posts = posts.filter(p => p.title.toLowerCase().includes(searchTitle.toLowerCase()));

  // ====== AND 태그 필터링 ======
  if (searchTags.length > 0) {
    posts = posts.filter(p =>
      searchTags.every(tag =>
        (p.tags || []).map(t => t.toLowerCase()).includes(tag.toLowerCase())
      )
    );
  }
  area.innerHTML = posts.map(post => `
    <li class="board-list-item" data-id="${post.postId}" style="cursor:pointer;">
      <div class="card card-post mb-4 border-0">
        <div class="card-body py-4 px-4">
          <div class="d-flex align-items-center gap-3 mb-2">
            <span class="badge badge-recruit">${post.anncEndYn === 'Y' ? '모집완료' : '모집중'}</span>
            <div class="post-title flex-grow-1">${post.title}</div>
          </div>
          <div class="post-content mb-2">
            ${post.content.length > 80 ? post.content.slice(0, 80) + '...' : post.content}
          </div>
          <div class="mb-2">
            ${post.job ? `<span class="badge badge-tag">${post.job}</span>` : ''}
            ${(post.tags || []).map(tag =>
              `<span class="badge badge-tag">${tag}</span>`
            ).join('')}
          </div>
          <div class="d-flex justify-content-between align-items-center mt-2">
            <div class="d-flex align-items-center gap-2 text-secondary">
              <span class="fw-semibold">${post.nickname || '익명'}</span>
              <span style="font-size:1.05em;">·</span>
              <span style="font-size:.99em;">${post.date}</span>
            </div>
            <div class="d-flex align-items-center gap-3 text-secondary" style="font-size:1.07em;">
              <span><i class="bi bi-heart"></i>좋아요 ${post.like}</span>
              <span><i class="bi bi-eye"></i>조회수 ${post.view}</span>
            </div>
          </div>
          <div class="mt-3 d-flex align-items-center gap-2">
            <span class="btn btn_gray_line ${post.myStatus === 'PRST-002' ? 'bg-warning' : 'bg-light'}">
              ${STATUS_MAP[post.myStatus] || '상태없음'}
            </span>
            ${post.myStatus === 'PRST-002' ? `
              <div class="btn btn_violet_line btn-accept"
                data-aplcnt-no="${post.prjAplcntNo}">
                참여수락
              </div>
            ` : ''}
          </div>
        </div>
      </div>
    </li>
  `).join('');

  // "참여수락" 버튼 이벤트
  document.querySelectorAll('.btn-accept').forEach(btn => {
    btn.addEventListener('click', async function(e) {
      e.stopPropagation();
      const prjAplcntNo = this.dataset.aplcntNo;
      try {
        await axios.put('/ajax/project/applicant/agree', {
          prjAplcntNo,
          status: 'PRST-003'
        });
        alert('참여완료로 처리되었습니다!');
        // 리스트 다시 불러오기
        location.reload(); // 또는 allPosts를 새로 갱신 후 renderPostList();
      } catch (err) {
        alert('참여 처리 실패!\n' + (err.response?.data?.message || ''));
      }
    });
  });
}

// ================ 이벤트 바인딩 ================

// 탭(모집상태)
document.querySelectorAll('.tab-btn').forEach(btn => {
  btn.addEventListener('click', function() {
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    filterStatus = btn.getAttribute('data-tab');
    renderPostList();
  });
});

