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
	padding: 2.5rem 2rem;
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
	<div class="container">

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
					<select class="Select_root Select_size38 Select_round w140" name="selectName">
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
		<ul class="list" id="company-list">
		</ul>



		<!-- 페이지네이션 -->
		<div class="PageBox">
			<!-- <button data-page="2" class="BtnType SizeS BtnPrev">이전</button> -->
			<span class="BtnType SizeS active">1</span>
			<button class="BtnType SizeS page" data-page="2">2</button>
			<button data-page="2" class="BtnType SizeS BtnNext">다음</button>
		</div>




	</div>



	<div class="modal fade" id="careerModal" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered"
			style="max-width: 450px;">
			<div class="modal-content">
				<div class="modal-header bg-white">
					<h5 class="modal-title text-purple fw-semibold" id="recruitTitle">기업명</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body bg-light py-4 px-4">
					<div class="mb-4">
						<div
							class="d-flex justify-content-between align-items-center mb-2">
							<label class="form-label fw-medium text-secondary">선택된
								경력</label>
							<button type="button" class="btn btn-link p-0 text-secondary"
								id="btnShowResumeList">경력 변경 &gt;</button>
						</div>
						<div id="selectedCareerCard"></div>
						<div id="careerList" class="mt-2 overflow-auto" style="display: none; max-height: 220px;"></div>
					</div>
					<!-- 하단 버튼 -->
					<div class="modal-footer bg-white">
						<button id="btnSaveCareer" class="btn w-100 py-2 text-white"
							style="background: #ae8be1;">등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>