<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src=""></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 리뷰 상세</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/member/community/companyView/companyReviewChart.js"></script>
<style>

/* 상단 탭 */
.tabs {
	display: flex;
	border-bottom: 2px solid #eee;
	margin-bottom: 15px;
}

.tab-btn {
	padding: 10px 20px;
	cursor: pointer;
	border: none;
	background: none;
	font-size: 16px;
	color: var(--violet60);
	font-weight: bold;
	position: relative;
}

.tab-btn.active {
	color: var(--violet60);
}

.tab-btn.active::after {
	content: '';
	position: absolute;
	bottom: -2px;
	left: 0;
	right: 0;
	height: 2px;
	background: var(--violet60);
}
/* 하위 진행 상태 버튼 */
.sub-tabs {
	margin-bottom: 15px;
}

.sub-btn {
	background: none;
	border: none;
	margin-right: 10px;
	font-size: 15px;
	cursor: pointer;
	color: #555;
	padding: 5px 10px;
}

.sub-btn.active {
	color: #6a0dad;
	font-weight: bold;
	border-bottom: 2px solid #6a0dad;
}
/* 필터 버튼 */
.filter-buttons {
	display: flex;
	flex-wrap: wrap;
	gap: 8px;
	margin-bottom: 20px;
}

.filter-btn {
	padding: 6px 14px;
	border-radius: 20px;
	border: 1px solid #ddd;
	cursor: pointer;
	background: #fff;
	font-size: 14px;
	color: #333;
	transition: all 0.2s;
}

.filter-btn.active {
	background: #e9d7f9;
	color: #6a0dad;
	border-color: #6a0dad;
}
/* 콘텐츠 영역 */
.tab-content {
	display: none;
	margin-top: 20px;
}

.tab-content.active {
	display: block;
}

.question-charts {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
}

.chart-card {
	display: flex;
	align-items: center;
	gap: 20px;
	margin-bottom: 20px;
}

.chart-card canvas {
	width: 120px;
	height: 120px;
}

.chart-card .label {
	font-size: 14px;
	color: #333;
}

.chart-card .avg {
	font-weight: bold;
	font-size: 16px;
	color: #6a0dad;
}

.chart-info {
	font-size: 14px;
	color: #333;
}

.chart-info .avg {
	font-size: 18px;
	font-weight: bold;
	color: var(--violet70);
	margin-bottom: 5px;
}

.chart-info .desc {
	font-size: 13px;
	color: #666;
}

/* 섹션 구분 */
.chart-section {
	padding: 20px;
	border-radius: 10px;
	margin-bottom: 25px;
}

.chart-section.large {
	flex-basis:48%;
}

/* 차트 카드 */
.chart-card {
	display: flex;
	align-items: center;
	gap: 20px;
	margin-bottom: 20px;
}

/* 전체 평균 차트 */
.chart-section.large canvas {
	width: 200px !important;
	height: 200px !important;
}

/* 질문별 평균 차트 */
.question-charts {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
}

.question-charts .chart-card {
	flex: 1 1 45%;
	min-width: 300px;
	padding: 10px;
	background: #fff;
	border-radius: 10px;
	border: 1px solid #ddd;
}

.question-charts canvas {
	width: 120px !important;
	height: 120px !important;
}

.company-info-box {
	display: flex;
	justify-content: space-between;
	align-items: flex-end; /* 하단 정렬로 맞춤 */
	min-height: 70px; /* 로고와 차트 높이 맞춤 */
	padding-bottom: 50px;
	margin-top: 50px;
}

.company-info-left {
	display: flex;
	gap: 20px;
	align-items: center;
}

.company-logo {
	width: 80px;
	height: 80px;
	object-fit: contain;
	border: 1px solid #ddd;
	border-radius: 10px;
	background: #fff;
}

.company-name {
	margin: 0 0 5px 0; /* 이름과 리뷰 수 간격 */
	font-size: 1.5rem;
	font-weight: bold;
}

.review-count {
	margin: 0;
	color: #555;
	font-size: 14px;
}

.review-count .count {
	font-weight: bold;
	color: var(--violet60);
}

.review-count-top {
	margin: 10px 0;
	font-size: 15px;
	font-weight: bold;
	color: #333;
}

.review-count-top .count {
	color: var(--violet60);
}

.employee-status-box {
	width: 250px;
	text-align: center;
	display: flex;
	flex-direction: column;
	justify-content: flex-end; /* 하단 정렬 */
   	margin-bottom: 80px;
}

.employee-status-percent {
	display: flex;
	justify-content: space-between;
	margin-top: 4px; /* 기존 8px → 4px로 간격 축소 */
	font-size: 14px;
}

.employee-status-percent .label {
	color: #333;
}

.employee-status-percent .percent {
	font-weight: bold;
	font-size: 16px;
}

.employee-status-total {
	margin-top: 4px; /* 기존 8px → 4px로 간격 축소 */
	font-weight: bold;
	font-size: 14px;
}

.tab-overall .d-flex {
    display: flex !important;
    justify-content: space-around;
    flex-direction: row;
    align-content: stretch;
    align-items: flex-end;
}

.tab-overall .gap-3 {
    gap: 5rem !important;
}
</style>
</head>

<body>
	<p class="h1 mb-3 fw-bold">기업 리뷰 상세</p>


	<div id="companyInfoBox" class="company-info-box">
		<div class="company-info-left">
			<img id="companyLogo" class="company-logo" src="" alt="기업 로고">
			<div>
				<h3 id="companyName" class="company-name"></h3>

			</div>
		</div>



	</div>


	<p class="review-count">
		<span id="reviewCount" class="count"></span>개의 리뷰가 등록되 있습니다.
	</p>

	<!-- 상단 탭 -->
	<div class="tabs">
		<button class="tab-btn active" data-tab="overall">전체</button>

		<button class="tab-btn" data-tab="topjob">직무별</button>
	</div>



	<div id="tab-overall" class="tab-content active">
		<p class="h3">전체 평균</p>
		<div class="d-flex align-items-center all-chart-wrap">
			<div class="chart-section large">
				<div class="chart-card">
					<canvas id="overallAvgChart"></canvas>
					<div class="chart-info">
						<p class="avg">
							평균 점수: <span id="overallAvgText"></span>점
						</p>
						<p class="desc">전체 리뷰를 종합한 평균 점수입니다.</p>
					</div>
				</div>
			</div>
	
	
			<!-- 오른쪽 비율 차트 -->
			<div class="employee-status-box">
				<canvas id="employeeStatusChart"></canvas>
				<div class="employee-status-percent">
					<div>
						<div class="label">현직원</div>
						<div class="percent" id="workingPercent">0%</div>
					</div>
					<div>
						<div class="label">전직원</div>
						<div class="percent" id="notWorkingPercent">0%</div>
					</div>
				</div>
				<p class="employee-status-total" id="totalRespondentsText">총 0명의
					답변입니다.</p>
			</div>
		</div>
		<hr>
		<p class="h3">질문별 평균</p>
		<div class="chart-section">
			<div id="questionCharts" class="question-charts"></div>
		</div>
	</div>

	<div id="tab-topjob" class="tab-content">
		<div id="topJobButtons" class="filter-buttons"></div>
		<p id="topJobCountInfo"
			style="margin: 10px 0; font-size: 14px; color: #555;"></p>
		<h4>직무 평균</h4>
		<div class="chart-section large">
			<div class="chart-card">
				<canvas id="topJobAvgChart"></canvas>
				<div class="chart-info">
					<p class="avg">
						평균 점수: <span id="topJobAvgText"></span>점
					</p>
					<p class="desc">선택한 직무의 전체 평균 점수입니다.</p>
				</div>
			</div>
		</div>
		<hr>
		<h5>질문별 평균</h5>
		<div class="chart-section">
			<div id="topJobQuestionCharts" class="question-charts"></div>
		</div>
	</div>

</body>
</html>