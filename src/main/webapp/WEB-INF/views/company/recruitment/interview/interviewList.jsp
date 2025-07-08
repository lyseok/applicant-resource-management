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
		<p class="fs-14">총 ${introductionList.size() }건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label> <input
					type="text" id="listKeyword" class="searchBarInput"
					placeholder="면접 제목으로 검색하세요." maxlength="24"
					autocomplete="off" class="SearchInput_SearchInput__input__Cg7QD"
					value="">
			</div>
			<button type="button" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>

	<div class="Post_post">
		<div class="PostList_postList">
			<ul id='interviewList'>
			
				<li class="PostList_postItem">
					<div class="PostList_link ">
						<div class="PostList_post">
							<div class="ListItem_post">
								<div class="ListItem_post_item">
									<h3 class="h4 fw-bold">백엔드 개발자 모집 공고</h3>
									<h3 class="h5">업무내용</h3>
								</div>
							</div>
							<div class="ListItem_info">
								<div class="ListItem_profile">
									<span class="fw-bold">면접일시</span>
									<span>2025-07-14 14:00:00</span>
								</div>
								<div class="ListItem_profile_postInfoList">
									<div class="d-flex gap-2">
										<div class="ListItem_profile_postInfoItem">
											<span>단계</span><strong>3</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>채용인원</span><strong>2</strong>
										</div>
										<div class="ListItem_profile_divider"></div>
										<div class="ListItem_profile_postInfoItem">
											<span>면접인원</span><strong>10</strong>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex gap-2 justify-content-end w-100 mt-3">
								<div class='btn btn_gray_line'>수정</div>
								<div class='btn btn_red_line'>삭제</div>
							</div>
						</div>
					</div>
				</li>
				
			</ul>
		</div>
	</div>
</body>