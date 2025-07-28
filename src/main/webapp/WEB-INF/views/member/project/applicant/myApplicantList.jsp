<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8" />
  <title>프로젝트 게시판</title>
  <script defer src="/js/member/project/applicant/myApplicantList.js" ></script>
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
    }
    .tab-btn:hover:not(.active) {
	    color: #181f2a;
	    border-bottom: 3.5px solid #181f2a;
	    background: none;
	   }
	   
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
    
    .badge-recruit { background:var(--violet80); }
    .card-post { border-radius: 10px; cursor:pointer;}
    .card-post:hover { box-shadow: 0 4px 28px 0 rgba(120, 80, 210, 0.16);}
    .post-title { font-size:1.25em; font-weight:700; color:#222;}
    .badge-tag { 
	    background: var(--violet10);
	    padding: 5px 13px;
	    border-radius: 15px;
	    border: 1px solid var(--violet30);
	    font-weight: 500;
	    font-size: 12px;
	    color: var(--violet70);
    }
    @media (max-width: 768px) {
      .tab-btn { font-size: 1em; margin-right: .5rem; padding: .5em 1.2em; }
      .sort-btn { font-size: .99em; padding: .28em .6em; }
      .btn-write { font-size: .98em; padding: .5em 1.1em;}
    }
  </style>
</head>
<body>
  <div class="container">
    <p class="h1 mb-4 fw-bold">내 지원 관리</p>

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
