<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script defer src="/js/company/recruitment/talentpool/talentPoolList.js"></script>
		<title>인재풀</title>
		<style>
			* {
				margin: 0;
				padding: 0;
				box-sizing: border-box;
				font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
			}

			body {
				background-color: #f8f9fa;
				font-size: 14px;
				color: #333;
			}

			.container {
				max-width: 1200px;
				margin: 0 auto;
				padding: 20px;
				background-color: #fff;
				border-radius: 8px;
				box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
			}

			.search-bar {
				display: flex;
				justify-content: space-between;
				margin-bottom: 20px;
				align-items: center;
			}

			.search-input-container {
				flex: 1;
				margin-right: 20px;
			}

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

			.btn {
				padding: 8px 15px;
				border: 1px solid #ddd;
				border-radius: 4px;
				background-color: #fff;
				font-size: 14px;
				cursor: pointer;
				display: flex;
				align-items: center;
				gap: 5px;
			}

			.btn-primary {
				background-color: #212529;
				color: white;
				border-color: #212529;
			}

			.filter-container {
				display: flex;
				justify-content: space-between;
				margin-bottom: 20px;
				padding: 20px;
				border: 1px solid #eee;
				border-radius: 4px;
			}

			.filter-column {
				flex: 1;
				padding: 0 10px;
			}

			.filter-group {
				margin-bottom: 20px;
			}

			.filter-label {
				font-weight: 600;
				margin-bottom: 10px;
				color: #333;
			}

			.range-slider {
				position: relative;
				height: 30px;
				margin: 10px 0;
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
				margin-bottom: 10px;
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

			table {
				width: 100%;
				border-collapse: collapse;
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

			.status-text {
				display: flex;
				align-items: center;
				gap: 5px;
				margin-bottom: 3px;
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

			.skill-tag {
				display: inline-block;
				padding: 3px 8px;
				background-color: #f1f3f4;
				border-radius: 4px;
				font-size: 12px;
				white-space: nowrap;
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
		</style>
	</head>

	<body>

		<div class="container">
			<p class="h1 mb-3 fw-bold">인재 탐색</p>
			<!-- Search Bar -->
			<div class="search-bar">
				<div class="search-input-container">
					<!-- <input type="text" class="search-input" placeholder="이름 또는 이메일, 연락처로 검색"> -->
				</div>
				<div class="button-group">
					<button class="btn">필터</button>
					<!-- <button class="btn">정렬: 최신순</button> -->
					<button class="btn">정보 수정 반영</button>
					<button class="btn btn_violet_line">필터링</button>
				</div>
			</div>

			<!-- Filter Section -->
			<div class="filter-container">
				<div class="filter-column">
					<div class="filter-group">
						<div class="filter-label">경력 (년)</div>
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
						<div class="filter-label">보유기술</div>
						<input type="text" class="input-field skill-input" placeholder="기술을 입력하고 Enter를 누르세요">
					</div>

				</div>

				<div class="filter-column">
					<div class="filter-group">
						<div class="filter-label">학력</div>
						<div class="education-options">
						</div>
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

			<!-- Candidate Table -->
			<table>
				<thead>
					<tr>
						<th><input type="checkbox" class="checkbox"></th>
						<th>회원</th>
						<th>이력서</th>
						<th>최종 학력</th>
						<th>어학</th>
						<th>연락처</th>
						<th>최근 경력</th>
						<th>자격증</th>
						<th>보유기술</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" class="checkbox"></td>
						<td>
							<div class="candidate-info">
								<div class="candidate-name">박진성</div>
								<div class="candidate-email">js.park@gmail.com</div>
							</div>
						</td>
						<td>
							<div class="experience-status">
								<div class="status-text">
									확인
								</div>
							</div>
						</td>
						<td>
							<div class="data-info">
								<div class="data-name">카이스트</div>
								<div class="data-sub">컴퓨터공학</div>
							</div>
						</td>
						<td>
							<div class="data-info">
								<div class="data-name">TOEIC</div>
								<div class="data-sub">850</div>
							</div>
						</td>
						<td>010-1234-5678</td>
						<td>
							<div class="data-info">
								<div class="data-name">(주) 네이버</div>
								<div class="data-sub">백엔드개발자</div>
							</div>
						</td>
						<td>5년</td>
						<td>
							<div class="my-list">
								<span class="skill-tag">정보처리기사</span>
								<span class="skill-tag">정보보안기사</span>
							</div>
						</td>
						<td>
							<div class="my-list">
								<span class="skill-tag">Spring</span>
								<span class="skill-tag">Oracle</span>
								<span class="skill-tag">Oracle</span>
								<span class="skill-tag">Oracle</span>
								<span class="skill-tag">Oracle</span>
							</div>
						</td>
					</tr>


				</tbody>
			</table>

			<!-- pager 영역 -->
			<div class="PageBox">
			</div>

		</div>
	</body>