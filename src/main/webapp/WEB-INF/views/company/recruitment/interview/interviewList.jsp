<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
	<meta charset="UTF-8">
	<title>면접 목록</title>
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css">
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css">
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
	<script defer src="/js/company/recruitment/interview/interviewList.js"></script>
</head>
<body>
	<p class="h1 mb-3 fw-bold">면접 목록</p>
	<div
		class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="list-size" class="fw-bold">0</span> 건</p>
			
		<div class="TypoBox searchBar">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="listKeyword">검색어</label>
					<input type="text" id="examSearch" class="searchBarInput" placeholder="면접 제목으로 검색하세요." maxlength="24" autocomplete="off" value="" >
				</div>
				<button type="button" class="searchBarBtn" id="searchBtn">
					<span class="material-symbols-outlined">search</span>
				</button>
			</div>
	</div>

			<ul id='interviewList'>
			
				<li class="p-6 d-flex flex-column align-items-center gap-2">
					<span>띹잡에 등록된 면접이 없어요!</span>
				</li>
				
			</ul>



</body>