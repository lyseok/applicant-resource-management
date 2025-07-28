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
let filterStatus = "all"; // "all" | "open" | "closed"

// ================ 데이터 요청 및 초기 렌더 =================
axios.get('/ajax/board/project/my')
  .then(res => {
    allPosts = convertServerPosts(res.data);
    renderPostList();
  })
  .catch(() => {
    allPosts = [];
    renderPostList();
  });

// ================ 탭 이벤트 =================
document.querySelectorAll('.tab-btn').forEach(btn => {
  btn.addEventListener('click', function() {
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    this.classList.add('active');
    filterStatus = this.getAttribute('data-tab');
    renderPostList();
  });
});

// ================ 게시글 리스트 렌더 ================
function renderPostList() {
  const area = document.getElementById('boardList');
  let posts = allPosts.slice();

  if (filterStatus === 'open') posts = posts.filter(p => p.anncEndYn === 'N');
  else if (filterStatus === 'closed') posts = posts.filter(p => p.anncEndYn === 'Y');

  area.innerHTML = posts.map(post => `
    <li class="board-list-item" data-id="${post.postId}" style="cursor:pointer;">
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
        window.location.href = '/mypage/project/applicant/detail?prjAnncNo=' + encodeURIComponent(prjAnncNo);
      }
    });
  });
}