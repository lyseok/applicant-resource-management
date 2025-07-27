<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title></title>
<script src="/js/member/community/companyView/companyReviewList.js"></script>
<style>
:root {
	--violet40: #ede9fe; /* 연봉 필터 배경 */
	--violet70: #7c3aed; /* 주요 포인트 컬러 */
	--gray100: #f5f5f5;
	--gray200: #e5e5e5;
	--gray500: #6b7280;
	--gray700: #374151;
	--border: 1px solid #d1d5db;
	--radius: 8px; /* 둥근 모서리 반경 */
}

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0
}

body {
	font-family: sans-serif;
	color: var(--gray700);
	background: #fff;
}

.container {
	max-width: 1000px;
	margin: 2rem auto;
	padding: 0 1rem;
}

/* 제목 */
.title {
	display: flex;
	justify-content: space-between; /* 추가 */
	align-items: center;
	color: var(--violet70);
	font-size: 1.5rem;
	margin-bottom: 1rem;
}

.title svg {
	width: 32px;
	height: 32px;
	margin-right: 0.5rem;
}

/* 탭 + 검색창을 같은 줄에 배치 */
.tabs-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1rem;
}

.tabs {
	display: flex;
	gap: .5rem;
}

.tab {
	padding: .5rem 1rem;
	border: var(--border);
	border-radius: var(--radius);
	background: #fff;
	color: var(--gray500);
	cursor: pointer;
	font-size: .95rem;
}

.tab.active {
	background: var(--violet70);
	color: #fff;
	border-color: var(--violet70);
}

/* 검색창 */
.search-box {
	width: 30%;
}

.search-box input {
	width: 100%;
	padding: .75rem 1rem;
	border: var(--border);
	border-radius: var(--radius);
	font-size: 1rem;
}

/* 검색 조건 + 연봉 슬라이더 한 줄 배치 */
.search-conditions {
	display: flex;
	align-items: center;
	gap: 1.5rem;
	margin-bottom: 2rem;
}

.search-conditions select {
	padding: .5rem;
	border: var(--border);
	border-radius: var(--radius);
	background: #fff;
	font-size: .9rem;
}

.search-conditions label {
	font-size: .9rem;
	display: flex;
	align-items: center;
	gap: .25rem;
}

/* 연봉 필터 박스 */
.salary-filter {
	flex: 1;
	padding: 12px 16px;
	background: var(--violet40);
	border-radius: var(--radius);
	/* border 제거 */
}

.salary-filter label {
	display: block;
	font-weight: bold;
	margin-bottom: .75rem;
	font-size: .95rem;
}

.salary-controls {
	display: flex;
	align-items: center;
	gap: 1rem;
}

.tabs-container select {
	min-width: 6rem;
	padding: .5rem;
	border: var(--border);
	border-radius: var(--radius);
	background: #fff;
	font-size: .9rem;
}

/* 결과 리스트 */
.list {
	list-style: none;
	margin: 0;
	padding: 0;
}

.item {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 3rem 0;
	border-bottom: var(--border);
}

.info {
	display: flex;
	align-items: center;
	gap: 1rem;
}

.logo {
	width: 48px;
	height: 48px;
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

/* 페이지네이션 */
.pagination {
	display: flex;
	list-style: none;
	gap: .5rem;
	justify-content: center;
	margin: 2rem 0;
	padding: 0;
}

.pagination li {
	width: 32px;
	height: 32px;
	display: flex;
	align-items: center;
	justify-content: center;
	border: var(--border);
	border-radius: var(--radius);
	cursor: pointer;
	color: var(--gray500);
	font-size: .9rem;
}

.pagination li.active {
	background: var(--violet70);
	color: #fff;
	border-color: var(--violet70);
}
</style>
</head>
<body>
	<div class="container">

		<p class="h1 mb-3 fw-bold">기업 리뷰</p>
		<div
			class="p-3 pt-4 pb-4 bg-violet03 rounded d-flex justify-content-between align-items-center mb-5">
			<p class="fw-bold">✍ 자신의 경험을 공유해주세요</p>
			<div class="">

				<button type="button" id="review_form_btn"
					class="btn btn_violet h50 lh50">기업 리뷰 등록</button>
			</div>
		</div>

		<!-- 제목 -->
		<div class="title" id="company-title">
			<div style="display: flex; align-items: center; gap: 0.5rem;">
				<svg viewBox="0 0 24 24" fill="currentColor">
			      <path d="M3 13h4v8H3v-8zm7-6h4v14h-4V7zm7 4h4v10h-4V11z" />
			    </svg>

			</div>

			<button type="button" class="btn btn_violet btn-primary">리뷰등록</button>
		</div>


		<!-- 탭 + 검색창 -->
		<div class="tabs-container">
			<div class="tabs">
				<div class="tab active">전체</div>
				<div class="tab">산업별</div>
				<div class="tab">직무별</div>
			</div>
			<!-- 검색창 -->
			<div class="search-box">
				<input type="text" placeholder="기업명을 검색해 보세요." />
			</div>
		</div>


		<!-- 결과 리스트 -->
		<ul class="list" id="company-list">
		</ul>



		<!-- 페이지네이션 -->
		<ul class="pagination">
			<li class="active">1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
			<li>…</li>
			<li>10</li>
		</ul>




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
</html>