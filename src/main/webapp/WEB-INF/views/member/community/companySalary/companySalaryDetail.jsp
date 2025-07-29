<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="/js/member/community/companySalary/companySalaryDetail.js"></script>
<!-- Boxicons CDN 추가 -->
<link
	href="https://cdn.jsdelivr.net/npm/boxicons@2.1.0/css/boxicons.min.css"
	rel="stylesheet">
<style type="text/css">
:root {
	--violet70: #7c3aed;
	--gray200: #e5e5e5;
	--gray500: #6b7280;
	--gray700: #374151;
	--radius: 8px;
}

body {
	font-family: sans-serif;
	color: var(--gray700);
	background: #fff;
	padding: 2rem;
	overflow-x: hidden;
}

.container {
	max-width: 1000px;
	margin: 0 auto;
	padding: 0 1rem;
}

/* 데이터 요약 */
.salary-summary-box {
	display: flex;
	justify-content: space-around;
	padding: 1rem 0;
	border-bottom: 1px solid #eee;
	margin-bottom: 1rem;
}

.salary-summary-box .summary-item {
	flex: 1;
	text-align: center;
}

.salary-summary-box .summary-value {
	font-size: 2rem;
	font-weight: bold;
	color: var(--violet70);
}

.salary-summary-box .summary-label {
	margin-top: .25rem;
	color: #666;
	font-size: .9rem;
}

.salary-summary-box .summary-sub {
	margin-top: .25rem;
	color: #999;
	font-size: .8rem;
}

.salary-summary-box .summary-monthly {
	margin-top: .5rem;
	font-size: .9rem;
	color: #333;
}

/* 유사 기업 테이블 */
.similar-companies {
	margin-top: 4rem;
}

.section-title {
	font-size: 1.25rem;
	margin-bottom: 1.5rem;
	display: flex;
	align-items: center;
}

.tooltip {
	display: inline-block;
	margin-left: 0.5rem;
	width: 16px;
	height: 16px;
	background: var(--gray200);
	color: var(--gray500);
	font-size: 0.75rem;
	line-height: 16px;
	text-align: center;
	border-radius: 50%;
	cursor: default;
}

/* 테이블 */
.table-wrapper {
	overflow-x: auto;
	margin-top: 2rem;
	margin-left: 80px;
	margin-right: 40px;
}

.compare-table {
	width: 100%;
	border-collapse: collapse;
	text-align: center;
	font-size: 1.2rem;
	border: none;
}

.compare-table th, .compare-table td {
	padding: 0.7rem 0.6rem;
	border: none; /* 각 셀 테두리 제거 */
	text-align: center;
}

.compare-table thead th {
	background: none;
	font-weight: 500;
	border-bottom: 1px solid #ddd; /* 헤더 아래만 라인 */
}

.compare-table tbody tr:nth-child(even) {
	background: #fafafa;
}

/* 선택된 열 강조 */
.compare-table .selected {
	background: rgba(124, 58, 237, 0.08); /* 연보라색 배경 */
}

/* 연봉 차트 */
.salary-rank-wrapper {
	margin-top: 4rem;
	margin-bottom: 4rem;
}

.salary-rank-chart {
	display: flex;
	flex-direction: column;
	gap: 2rem;
}

.rank-row {
	display: flex;
	align-items: center;
	gap: 2rem;
}

.rank-title {
	width: 60px;
	text-align: right;
	font-weight: bold;
}

.rank-bar {
	position: relative;
	width: 100%;
	height: 50px;
	background: #e5e5e5;
	border-radius: 8px;
}

.rank-icon {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	font-size: 18px;
	color: #7c3aed;
	transition: left 1.5s ease;
}

.salary-rank-scale {
	position: absolute;
	width: 100%;
	top: 0;
	left: 0;
	height: 20px;
}

.scale-mark {
	position: absolute;
	top: -20px;
	font-size: 0.8rem;
	color: #666;
	transform: translateX(-50%);
	white-space: nowrap;
}

.scale-line {
	position: absolute;
	top: 0;
	height: 100%;
	border-left: 1px dotted #ccc;
}

.chart-area {
	padding-top: 25px;
	position: relative;
	margin-left: 80px;
	margin-right: 40px;
}

#icon {
	display: inline-flex; /* 내용 길이에 맞게 */
	align-items: center; /* 텍스트와 아이콘 세로 정렬 */
	gap: 15px; /* 아이콘과 텍스트 간격 */
	padding: 6px 12px; /* 여백 최소화 */
	background-color: var(--violet40);
	border-radius: 6px; /* 살짝 둥글게 */
	font-size: 0.95rem;
	color: #333;
}

.table-scroll-container {
    position: relative;
    display: flex;
    align-items: center;
}

.table-wrapper {
    overflow-x: auto;
    flex: 1;
    scroll-behavior: smooth;
}

.scroll-btn {
    background: #fff;
    border: 1px solid #ddd;
    font-size: 1.5rem;
    cursor: pointer;
    width: 40px;
    height: 100%;
    color: #555;
    position: sticky;
    z-index: 10;
}

.scroll-btn.left {
    left: 0;
}

.scroll-btn.right {
    right: 0;
}


</style>
</head>
<body>
	<div class="container">

		<div class="company-infomation-row" data-section="salary">
			<div class="salary-summary-box">
				<div class="summary-item">
					<div id="salaryCompanyName" class="salary-company-name"></div>
					<div class="summary-value" id="salaryAvgGross">-- 만원</div>
					<div class="summary-label">전체 평균 연봉</div>
					<div class="summary-sub">(임원 제외)</div>
					<div class="summary-monthly" id="salaryAvgNet"></div>
				</div>
				<div class="summary-item">
					<div class="summary-value" id="salaryNewhireGross">-- 만원</div>
					<div class="summary-label">신입사원 초봉</div>
					<div class="summary-monthly" id="salaryNewhireNet"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="salary-rank-wrapper">
		<p class="section-title h3">직급별 연봉 통계</p>
		<div class="chart-area">
			<div class="salary-rank-scale" id="salaryRankScale"></div>
			<div id="salaryRankChart" class="salary-rank-chart"></div>
		</div>
		<div id="icon">
			최소연봉<i class="bx bxs-user"></i>최고연봉
		</div>
	</div>
	<div class="similar-companies">
		<p class="section-title h3">
			유사기업 비교 <span class="tooltip">?</span>
		</p>
		<div class="table-scroll-container">
			<button class="scroll-btn left">&lt;</button>
			<div class="table-wrapper">
				<table id="similarTable" class="compare-table">
					<thead></thead>
					<tbody></tbody>
				</table>
			</div>
			<button class="scroll-btn right">&gt;</button>
		</div>
	</div>


</body>
</html>