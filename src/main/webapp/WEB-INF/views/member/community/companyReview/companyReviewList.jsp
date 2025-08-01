<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>기업 리뷰</title>
<script src="/js/member/community/companyView/companyReviewList.js"></script>
<style>
/* 결과 리스트 */
.list {
	list-style: none;
	margin: 0;
	padding: 0;
	display:flex;
	gap:30px;
}

.item {
	padding: 2.5rem 1rem;
	width:calc((100% - 30px) / 2);
	border:1px solid var(--gray40);
	border-radius:10px;
	cursor:pointer;
}
.item:hover{
	background:var(--gray20);
}

.info {
	display: flex;
	align-items: center;
	gap: 30px;
}

.logo {
	width: 70px;
	height: 70px;
	object-fit: contain;
}

.text {
	display: flex;
	flex-direction: column;
}

.name {
	font-weight: bold;
	font-size: 1.1rem;
}

.meta {
	font-size: .9rem;
	color: var(--gray500);
	margin-top: .25rem;
}

.salary {
	font-size: 1.25rem;
	font-weight: bold;
	color: var(--gray700);
}
</style>
</head>
<body>
	<p class="h1 mb-3 fw-bold">기업 리뷰</p>
	
	<!-- 제목 -->
	<div class="title fs-14" id="company-title">
		<div style="display: flex; align-items: center; gap: 0.5rem;">
		</div>
	</div>

	<!-- 탭 + 검색창 -->
	<div class="Post_filter">
		<div class="Post_category d-flex align-items-center justify-content-between overflow-visible">
			<div class="d-flex">
				<div class="Post_categoryItem">
					<input id="status_all" type="radio" value="status_all" name="category" checked="">
					<label for="status_all"><span>전체</span></label>
				</div>
				<div class="Post_categoryItem">
					<input id="status_n" type="radio" value="status_n" name="category">
					<label for="status_n"><span>산업별</span></label>
				</div>
				<div class="Post_categoryItem">
					<input id="status_y" type="radio" value="status_y" name="category">
					<label for="status_y"><span>직무별</span></label>
				</div>
				<div class="Post_categoryItem">
					<input id="status_c" type="radio" value="status_c" name="category">
					<label for="status_c"><span>기업규모별</span></label>
				</div>
				<select class="Select_root Select_size38 Select_round w140" name="selectName" id="sortSelect">
					<option value="name">기업명 순</option>
					<option value="highScore">평점 높은 순</option>
					<option value="lowScore">평점 낮은 순</option>
					<option value="date" selected="">최신순</option>
					<option value="pop">인기순</option>
					<option value="like">시작일순</option>
				</select>
			</div>
			
			<div class="d-flex gap-2">				
				<!-- 검색창 -->
				<div class="TypoBox searchBar">
					<div class="searchBarWrap">
						<label class="searchBarLabel" for="listKeyword">검색어</label>
						<input type="text" id="listKeyword" class="searchBarInput" placeholder="회사명으로 리뷰 검색" maxlength="24" autocomplete="off" value="">
					</div>
					<a href="javascript:void(0)" class="searchBarBtn">
						<span class="material-symbols-outlined">search</span>
					</a>
				</div>
				
				<!-- 글쓰기 버튼 -->					
				<button type="button" class="btn btn_violet btn-primary" id="review_form_btn">리뷰등록</button>
			</div>
		</div>
	</div>
	
	<!-- 결과 리스트 -->
	<ul class="list" id="company-list"></ul>

	<!-- 페이지네이션 -->
	<div class="PageBox">
		<span class="BtnType SizeS active">1</span>
		<button class="BtnType SizeS page" data-page="2">2</button>
		<button data-page="2" class="BtnType SizeS BtnNext">다음</button>
	</div>

	<!-- 경력 선택 모달 -->
	<div class="modal fade" id="careerModal" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered" style="max-width: 450px;">
			<div class="modal-content">
				<div class="modal-header bg-white">
					<h5 class="modal-title fs-5 fw-bold text-success" id="recruitTitle">리뷰 작성 가능한 경력목록</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body py-4 px-4">
					<div>
						<div class="d-flex justify-content-between align-items-center mb-2">
							<label class="form-label fw-semibold fs-14 m-0">선택된 경력</label>
							<button type="button" class="btn btn_violet_line fs-13 fw-semibold" id="btnShowResumeList">경력 선택/변경</button>
						</div>
						<div id="selectedCareerCard"></div>
						<div id="careerList" class="mt-2 overflow-auto" style="display: none; max-height: 220px;"></div>
					</div>
				</div>
				<!-- 하단 버튼 -->
				<div class="modal-footer bg-white">
					<button id="btnSaveCareer" class="btn btn_violet w100p justify-content-center">등록</button>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- -- -->
