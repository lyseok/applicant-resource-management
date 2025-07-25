<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<head>
	<meta charset="UTF-8">
	<title>최근 본 공고</title>
	
   <link rel="stylesheet" href="/css/member/recruitment/RecruitView/RecruitViewList.css" >
	<script defer src="/js/member/recruitment/RecruitView/RecruitViewList.js" ></script>
</head>

<body>
	<h1 class=" mb-3">
		<b class="h1 fw-bold">최근 본 공고</b>
		<span class="d-block mt-1 fs-14 text-muted">최대 10건까지 확인 가능합니다.</span>
	</h1>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="list-count" class="fw-bold"></span>건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="공고명으로 검색하세요" maxlength="24" autocomplete="off" value="">
			</div>
			<button type="button" class="searchBarBtn" id="searchBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>
	
	<!-- 리스트 렌더링 영역 -->
	<div id="recruitViewListArea"></div>

	<!-- 페이지네이션 렌더링 영역 -->
	<div id="pageBox" class="PageBox"></div>

</body>