<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8">
	<title>면접 관리</title>
    <script defer src="/js/member/recruitment/interview/interviewList.js" ></script>
</head>

<body>
	<p class="h1 mb-3 fw-bold">면접 관리</p>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="list-count"></span>건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="기업명으로 검색하세요" maxlength="24" autocomplete="off" value="">
			</div>
			<button type="button" class="searchBarBtn" id="searchBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>
	
	<!-- 리스트 렌더링 영역 -->
	<div id="introductionListArea"></div>

	<!-- 페이지네이션 렌더링 영역 -->
	<div id="pageBox" class="PageBox"></div>

</body>