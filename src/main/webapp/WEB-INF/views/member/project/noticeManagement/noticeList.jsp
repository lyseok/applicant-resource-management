<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8" />
  <title>프로젝트 공고 관리</title>
  <script defer src="/js/member/project/noticeManagement/noticeList.js" ></script>
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
    .card-post { border-radius: 20px; box-shadow:0 2px 22px 0 rgba(170, 125, 241, 0.09);}
    .card-post:hover { box-shadow: 0 4px 28px 0 rgba(120, 80, 210, 0.16);}
    .post-title { font-size:1.25em; font-weight:700; color:#222;}
    .post-content { font-size:1.04em; color:#626b80; min-height:2.7em; overflow:hidden; text-overflow:ellipsis; display:-webkit-box; -webkit-line-clamp:2; -webkit-box-orient:vertical;}
    .badge-tag { background: #eebdff; color: #39b1d2; font-size: .98em; margin-right:.3em;}
    @media (max-width: 768px) {
      .tab-btn { font-size: 1em; margin-right: .5rem; padding: .5em 1.2em; }
      .card-post { border-radius: 12px; }
      .post-title { font-size:1.08em; }
      .post-content { font-size:.97em; }
    }
  </style>
</head>
<body style="background:#f8f8fc;">
  <div class="container py-5" style="max-width:950px;">
    <p class="h1 mb-4 fw-bold">공고 관리</p>

    <!-- 탭 네비게이션 -->
    <div class="mb-4">
      <button class="tab-btn active" type="button" data-tab="all">전체</button>
      <button class="tab-btn" type="button" data-tab="open">모집중</button>
      <button class="tab-btn" type="button" data-tab="closed">모집완료</button>
    </div>
    
    <div class="Post_post">
			<div class="PostList_postList">
				<ul id='boardList'>
				
					
				</ul>
			</div>
		</div>

  </div>
</body>
