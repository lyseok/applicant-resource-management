<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script defer src="/js/company/recruitment/talentpool/myTalentPoolList.js"></script>
		<title>인재관리</title>
		<style>
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
			
			.table th,
			.table td{
				border-color: rgba(33,37,41 / 50%);
			}
		</style>
	</head>

	<body>

			<p class="h2 mb-3 fw-bold">인재 관리</p>
			<!-- 입사제인 -->
			<div class="text-end mb-2">
				<button class="btn btn_violet" id="job-offer">입사제안</button>
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
				<thead class="border-top">
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