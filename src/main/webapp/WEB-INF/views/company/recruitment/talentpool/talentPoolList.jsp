<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script defer src="/js/company/recruitment/talentpool/talentPoolList.js"></script>
		<title>인재풀</title>
		<style>

			.search-input {
				width: 70%;
				padding: 10px 15px;
				border: 1px solid #ddd;
				border-radius: 4px;
				font-size: 14px;
				background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="%23999" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"/><path d="M21 21l-4.35-4.35"/></svg>');
				background-repeat: no-repeat;
				background-position: 10px center;
				padding-left: 35px;
			}

			.button-group {
				display: flex;
				gap: 10px;
			}


			.filter-container {
				position:relative;
				display: flex;
				flex-direction:column;
				margin-bottom: 30px;
				padding: 40px 20px;
				border: 1px solid #eee;
				border-radius: 4px;
				gap:30px;
			}
			
			
			.top_filter .filter-group{
				width:calc((100% - 24px) /2)
			}
			.bt_filter .filter-group{
				width:calc((100% - 48px) /3)
			}
			
			.filter-label {
				font-weight: 600;
				margin-bottom: 5px;
				color: #333;
			}

			.range-slider {
				position: relative;
				height: 30px;
				margin: 10px 0;
				margin-left:8px;
				width:calc(100% - 16px);
			}

			.slider-track {
				position: absolute;
				top: 50%;
				transform: translateY(-50%);
				width: 100%;
				height: 2px;
				background-color: #ddd;
			}

			.slider-range {
				position: absolute;
				top: 50%;
				transform: translateY(-50%);
				height: 2px;
				background-color: var(--violet70);
			}

			.slider-thumb {
				position: absolute;
				top: 50%;
				transform: translate(-50%, -50%);
				width: 16px;
				height: 16px;
				background-color: var(--violet70);
				border-radius: 50%;
				cursor: pointer;
			}

			.range-values {
				display: flex;
				justify-content: space-between;
				margin-top: 5px;
				font-size: 12px;
				color: #666;
			}

			.input-field {
				width: 100%;
				padding: 10px;
				border: 1px solid #ddd;
				border-radius: 4px;
				font-size: 14px;
			}

			.education-options {
				display: flex;
				flex-wrap: wrap;
				gap: 10px;
			}

			.education-option {
				padding: 8px 15px;
				border: 1px solid #ddd;
				border-radius: 4px;
				cursor: pointer;
				font-size: 14px;
			}

			.education-option.active {
				background-color: var(--violet70);
				color: white;
				border-color: var(--violet70);
			}

			.status-tabs {
				display: flex;
				overflow-x: auto;
				margin-bottom: 20px;
				border-bottom: 1px solid #eee;
				padding-bottom: 10px;
			}

			.status-tab {
				padding: 8px 15px;
				border-radius: 20px;
				background-color: #f8f9fa;
				margin-right: 10px;
				cursor: pointer;
				white-space: nowrap;
				display: flex;
				align-items: center;
				gap: 5px;
			}

			.status-tab.active {
				background-color: #e8f0fe;
				color: var(--violet70);
			}

			.status-indicator {
				display: inline-block;
				width: 8px;
				height: 8px;
				border-radius: 50%;
			}

			.status-count {
				background-color: #eee;
				padding: 2px 6px;
				border-radius: 10px;
				font-size: 12px;
				margin-left: 5px;
			}


			th {
				text-align: left;
				padding: 12px 15px;
				border-bottom: 1px solid #eee;
				color: #666;
				font-weight: normal;
			}

			td {
				padding: 12px 15px;
				border-bottom: 1px solid #eee;
				vertical-align: middle;
			}

			.checkbox {
				width: 18px;
				height: 18px;
				border: 1px solid #ddd;
				border-radius: 3px;
				cursor: pointer;
			}

			.candidate-info {
				display: flex;
				flex-direction: column;
			}

			.candidate-name {
				font-weight: 600;
				margin-bottom: 3px;
			}

			.candidate-email {
				color: #666;
				font-size: 13px;
			}

			.experience-status {
				display: flex;
				flex-direction: column;
			}

			.status-detail {
				color: #666;
				font-size: 12px;
			}

			.data-info {
				display: flex;
				flex-direction: column;
			}

			.data-name {
				font-weight: 500;
				margin-bottom: 3px;
			}

			.data-sub {
				color: #666;
				font-size: 13px;
			}

			.position {
				color: #666;
				font-size: 13px;
			}

			.pagination {
				display: flex;
				justify-content: center;
				margin-top: 20px;
				gap: 5px;
			}

			.page-item {
				width: 30px;
				height: 30px;
				display: flex;
				align-items: center;
				justify-content: center;
				border-radius: 4px;
				cursor: pointer;
			}

			.page-item.active {
				background-color: var(--violet70);
				color: white;
			}

			.page-nav {
				width: 30px;
				height: 30px;
				display: flex;
				align-items: center;
				justify-content: center;
				border-radius: 4px;
				cursor: pointer;
			}

			.my-list {
				width: 140px;
				overflow: auto;
				display: flex;
				gap: 10px;
			}

			.my-list::-webkit-scrollbar {
				height: 3px;
				/* width: 5px; */
			}
			
			
			
			.filter-box {
				display:flex;
				flex-direction:column;
				gap:10px;
			  max-height: 500px;
			  overflow: hidden;
			  transition: max-height 0.3s ease;
			}
			.filter-box.hide {
			  max-height: 0; /* 충분히 큰 값 (컨텐츠보다 크게) */
			}

			.filter-toggle-btn{
				position:absolute;
				top:20px;
				right:20px;
			}
			.filter-toggle-btn span{
				transition:0.3s;
			}
			.filter-toggle-btn span.on {
		    transform: rotate(180deg);
			}
		</style>
	</head>

	<body>
			<p class="h2 mb-5 fw-bold">인재 탐색</p>

			<!-- Filter Section -->
			<div class="filter-container">
				
				<button class="filter-toggle-btn">
					<span class="material-symbols-outlined fw-200 fs-2">keyboard_arrow_down</span>
				</button>
				
				<div class="filter-box">
				<div class="d-flex top_filter gap-4">
					<div class="filter-group">
						<div class="filter-label">최근 경력 (년)</div>
						<div class="range-slider">
							<div class="slider-track"></div>
							<div class="slider-range" style="left: 0%; right: 0%"></div>
							<div class="slider-thumb" style="left: 0%"></div>
							<div class="slider-thumb" style="left: 100%"></div>
						</div>
						<div class="range-values">
							<span>0년</span>
							<span>10년</span>
						</div>
					</div>
					<div class="filter-group">
						<div class="filter-label">최종 학력</div>
						<div class="education-options">
						</div>
					</div>
				</div>
				
				<div class="d-flex bt_filter gap-4">
					<div class="filter-group">
						<div class="filter-label">보유기술</div>
						<input type="text" class="input-field skill-input" placeholder="기술을 입력하고 Enter를 누르세요">
					</div>
					<div class="filter-group">
						<div class="filter-label">자격증</div>
						<input type="text" class="input-field license-input" placeholder="자격증을 입력하고 Enter를 누르세요">
					</div>

					<div class="filter-group">
						<div class="filter-label">학과</div>
						<input type="text" class="input-field major-input" placeholder="학과를 입력하고 Enter를 누르세요">
					</div>
				</div>
				
				<!-- 필터링 검색 -->
				<div class="text-end">
					<!-- <button class="btn">정렬: 최신순</button> -->
					<button class="btn btn_violet" id="filterBtn">검색</button>
				</div>
			</div>	
		</div>

			<!-- Status Tabs -->
			<!-- <div class="status-tabs">
				<div class="status-tab active">
					전체 <span class="status-count">58</span>
				</div>
				<div class="status-tab">
					<span class="status-indicator" style="background-color: var(--violet70);"></span>
					신규 지원 <span class="status-count">31</span>
				</div>
				<div class="status-tab">
					<span class="status-indicator" style="background-color: #fbbc04;"></span>
					제안 받음 <span class="status-count">11</span>
				</div>
				<div class="status-tab">
					<span class="status-indicator" style="background-color: #a142f4;"></span>
					면접 중 <span class="status-count">8</span>
				</div>
				<div class="status-tab">
					<span class="status-indicator" style="background-color: #34a853;"></span>
					제안 수락 <span class="status-count">3</span>
				</div>
				<div class="status-tab">
					<span class="status-indicator" style="background-color: #ea4335;"></span>
					제안 거절 <span class="status-count">5</span>
				</div>
			</div> -->

			<div class="d-flex flex-column mb-2 align-items-start justify-content-start gap-1">
				<button class="btn btn_violet_line fs-12 btn-sm" id="addTalent">관심 인재 수정</button>
				<p class="text-muted fw-500 fs-14">체크박스 선택 후 관심인재 수정 버튼 클릭 시 특정 회원을 관심인재로 등록/해제 할 수 있습니다.</p>
			</div>
			<!-- Candidate Table -->
			<table class="border-top">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkbox"></th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">회원</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">이력서</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">최종 학력</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">어학</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">연락처</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">최근 경력(년)</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">자격증</th>
						<th class="text-center fw-bold fs-13 py-3 align-middle">보유기술</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<!-- pager 영역 -->
			<div class="PageBox">
			</div>
	</body>