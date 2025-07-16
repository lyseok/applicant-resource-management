<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8" />
  <title>프로젝트 게시판</title>
  <script defer src="/js/member/project/noticemanagement/noticeList.js" ></script>
  <style>
    .tab-btn {
      border: none;
      background: none;
      color: #888;
      font-weight: 700;
      font-size: 1.18rem;
      padding: .65em 2.1em .58em 2.1em;
      border-radius: 0;
      border-bottom: 3.5px solid transparent;
      margin-right: 1.6rem;
      transition: color .19s, border .17s;
    }
    .tab-btn.active {
      color: #181f2a;
      border-bottom: 3.5px solid #181f2a;
      background: none;
    }
    .tab-btn:hover:not(.active) { color: #ad8be8; }
    .badge-recruit { background:#ae8be1 !important; }
    .search-mainbox { border-radius: 12px; border: 1.6px solid #e5e7eb; }
    .search-mainbox:focus-within { border-color: #ae8be1 !important; }
    .tag-chip {
      background: #f4f8fc;
      color: #243b5e;
      border-radius: 8px;
      font-weight: 500;
      padding: 4.5px 15px 4.5px 11px;
      font-size: 1.07em;
      margin-right: 7px;
      margin-bottom: 3px;
      display: inline-flex; align-items: center;
    }
    .sort-btn {
      font-size: 1.11em; font-weight: 600; color: #bbb; background: none; border: none; padding: .33em 1.4em;
      border-radius: 9px;
      transition: color .15s, background .15s;
    }
    .sort-btn.active {
      color: #181f2a; background: #e9dffc;
    }
    .sort-btn:not(.active):hover { color: #ae8be1; }
    .btn-write {
      background: #181f2a; color: #fff; font-size: 1.12em; font-weight: 600; border-radius: 11px;
      padding: .72em 2em; border: none; transition: background .16s;
    }
    .btn-write:hover { background: #7e3ff2; color: #fff; }
    .card-post { border-radius: 20px; box-shadow:0 2px 22px 0 rgba(170, 125, 241, 0.09);}
    .card-post:hover { box-shadow: 0 4px 28px 0 rgba(120, 80, 210, 0.16);}
    .post-title { font-size:1.25em; font-weight:700; color:#222;}
    .post-content { font-size:1.04em; color:#626b80; min-height:2.7em; overflow:hidden; text-overflow:ellipsis; display:-webkit-box; -webkit-line-clamp:2; -webkit-box-orient:vertical;}
    .badge-tag { background: #eebdff;; color: #39b1d2; font-size: .98em; margin-right:.3em;}
    @media (max-width: 768px) {
      .tab-btn { font-size: 1em; margin-right: .5rem; padding: .5em 1.2em; }
      .sort-btn { font-size: .99em; padding: .28em .6em; }
      .btn-write { font-size: .98em; padding: .5em 1.1em;}
    }
  </style>
</head>
<body style="background:#f8f8fc;">
  <div class="container py-5" style="max-width:950px;">

    <!-- 탭 네비게이션 -->
    <div class="mb-4">
      <button class="tab-btn active" type="button" data-tab="all">전체</button>
      <button class="tab-btn" type="button" data-tab="open">모집중</button>
      <button class="tab-btn" type="button" data-tab="closed">모집완료</button>
    </div>

    <!-- 검색 영역 -->
    <div class="row align-items-center mb-4 g-2">
      <div class="col-12 col-md-9">
        <div class="input-group mb-2 search-mainbox">
          <span class="input-group-text bg-white border-end-0">
            <i class="bi bi-search" style="font-size:1.2em; color:#ae8be1;"></i>
          </span>
          <input type="text" class="form-control border-start-0" placeholder="팀 프로젝트, 사이드프로젝트를 검색해보세요!" id="titleInput">
          <button class="btn btn-purple" id="searchBtn" type="button" style="background:#ae8be1;color:#fff;">검색</button>
        </div>
        <div class="input-group">
          <span class="input-group-text bg-white border-end-0">
            <i class="bi bi-hash" style="font-size:1.17em; color:#ae8be1;"></i>
          </span>
          <input type="text" class="form-control border-start-0" placeholder="태그로 검색해보세요!" id="tagInput" autocomplete="off">
          <button class="btn btn-outline-secondary ms-2" id="resetBtn" type="button"><i class="bi bi-arrow-clockwise"></i> 초기화</button>
        </div>
				<div id="tagList" class="d-flex flex-wrap gap-1 my-2"></div>
      </div>
      <div class="col-12 col-md-3 text-end">
        <button class="btn btn-write mt-2 mt-md-0" id="writeBtn" type="button">
          <i class="bi bi-pencil-square me-1"></i> 글쓰기
        </button>
      </div>
    </div>

    <!-- 정렬 -->
    <div class="d-flex align-items-center gap-2 mb-4">
      <button class="sort-btn active" data-sort="latest" type="button">최신순</button>
      <button class="sort-btn" data-sort="like" type="button">좋아요순</button>
      <button class="sort-btn" data-sort="view" type="button">조회수순</button>
    </div>

    <div class="Post_post">
			<div class="PostList_postList">
				<ul id='boardList'>
				
					
				</ul>
			</div>
		</div>

  </div>
</body>
