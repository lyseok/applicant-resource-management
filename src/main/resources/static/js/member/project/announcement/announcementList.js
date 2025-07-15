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
    anncEndYn: item.anncEndYn // 모집마감여부(N/Y)
  }));
}

let allPosts = [];
let filterStatus = "all"; // all | open | closed
let sortType = "latest"; // latest | like | view
let searchTitle = "";

// ================ 데이터 요청 및 초기 렌더 =================
axios.get('/ajax/board/project')
  .then(res => {
    allPosts = convertServerPosts(res.data);
    renderTags();
    renderPostList();
  })
  .catch(err => {
    allPosts = [];
    renderPostList();
  });

// ================ 렌더 함수 ================
let pendingTag = "";  // 입력 중인 태그

// --- 태그 입력창: 엔터 시 태그 추가만!
const tagInput = document.getElementById('tagInput');
if (tagInput) {
  tagInput.addEventListener('keydown', function(e) {
    if (e.key === 'Enter' && this.value.trim() !== '') {
      const newTag = this.value.trim();
      if (!searchTags.includes(newTag)) {
        searchTags.push(newTag);
        renderTags();
      }
      this.value = '';
      e.preventDefault();
    }
  });
}

// --- 태그 카드 그리기
let searchTags = [];

// 태그 입력: 엔터 시 추가
if (tagInput) {
  tagInput.addEventListener('keydown', function(e) {
    if (e.key === 'Enter' && this.value.trim() !== '') {
      const newTag = this.value.trim();
      if (!searchTags.includes(newTag)) {
        searchTags.push(newTag);
        renderTags();
      }
      this.value = '';
      e.preventDefault();
    }
  });
}

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

// 검색 버튼 클릭시 태그 AND 필터링 적용!
document.getElementById('searchBtn').onclick = () => {
  searchTitle = titleInput.value.trim();
  renderPostList();
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
  area.innerHTML = posts.map(post => /* html */`
    <li li class="board-list-item" data-id="${post.postId}" style="cursor:pointer;">
      <div class="card card-post mb-4 border-0" >
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
        </div>
      </div>
    </li>
  `).join('');
  // 게시글 클릭시 상세페이지로 이동
  document.querySelectorAll('.board-list-item').forEach(item => {
    item.addEventListener('click', function() {
      const prjAnncNo = this.getAttribute('data-id');
      if (prjAnncNo) {
        window.location.href = '/board/project/detail?prjAnncNo=' + encodeURIComponent(prjAnncNo);
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

// 정렬
document.querySelectorAll('.sort-btn').forEach(btn => {
  btn.addEventListener('click', function() {
    document.querySelectorAll('.sort-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    sortType = btn.getAttribute('data-sort');
    renderPostList();
  });
});

// 제목 검색
const titleInput = document.getElementById('titleInput');
if (titleInput) {
  titleInput.addEventListener('keydown', function(e){
    if (e.key === 'Enter') {
      searchTitle = this.value.trim();
      renderPostList();
    }
  });
}
document.getElementById('searchBtn').onclick = () => {
  searchTitle = titleInput.value.trim();
  renderPostList();
};


// 초기화 버튼
document.getElementById('resetBtn').onclick = () => {
  titleInput.value = '';
  tagInput.value = '';
  searchTitle = '';
  searchTags = [];
  renderTags();
  renderPostList();
};

// 글쓰기 버튼
document.getElementById('writeBtn').onclick = () => {
  location.href = '/board/project/form';
};