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

<style>
:root {
	--violet70: #7c3aed;
	--gray200: #e5e5e5;
	--gray500: #6b7280;
	--gray700: #374151;
	--border: 1px solid #d1d5db;
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

.table-wrapper {
    margin-top: 1.5rem;
    margin-left: 80px;   /* 그래프와 동일한 왼쪽 여백 */
    margin-right: 40px;  /* 그래프와 동일한 오른쪽 여백 */
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
	margin-top: 2rem;
}

.section-title {
	font-size: 1.25rem;
	margin-bottom: 0.75rem;
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

.table-wrapper {
	overflow-x: auto;
}

.compare-table {
	width: 70%;
	border-collapse: collapse;
	text-align: center;
	font-size: 0.95rem;
}

.compare-table th, .compare-table td {
	padding: 0.7rem 0.6rem;
	border: var(--border);
	text-align: center;
}

.compare-table thead th {
	background: var(--gray200);
	font-weight: 500;
}

.compare-table tbody tr:nth-child(even) {
	background: #fafafa;
}

.compare-table .salary {
	font-weight: bold;
	color: var(--gray700);
}

.compare-table .selected {
	background: rgba(124, 58, 237, 0.2);
}

.compare-table .selected.salary {
	font-size: 1.1rem;
	color: var(--violet70);
}

.salary-rank-wrapper {
	margin-top: 2rem;
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
  width: 100%;      /* 눈금과 동일 */
  height: 30px;
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

.rank-min, .rank-max {
	width: 50x;
	text-align: center;
	font-size: 0.85rem;
	color: #555;
}

.salary-rank-scale {
  position: absolute;
  width: 100%;      /* 막대와 동일 */
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
/* 아이콘 옆 최소/최대 라벨 */
.rank-icon span {
	position: absolute;
	white-space: nowrap; /* 줄바꿈 방지 */
}

.chart-area {
  padding-top: 25px;
  position: relative;
  margin-left: 80px;  /* 직급명 영역 여백 */
  margin-right: 40px;  /* 우측 여백 */
}


/* 섹션 간 간격 */
.company-infomation-row {
    margin-bottom: 3rem; /* 연봉 요약과 다음 섹션 간격 */
}

.salary-rank-wrapper {
    margin-top: 4rem; /* 직급별 연봉 섹션 위쪽 여백 */
    margin-bottom: 4rem; /* 다음 섹션과 간격 */
}

.similar-companies {
    margin-top: 4rem; /* 유사기업 비교 위쪽 여백 */
}

/* 제목과 섹션 간 간격 */
.section-title {
    font-size: 1.25rem;
    margin-bottom: 1.5rem; /* 제목과 내용 간 간격 확대 */
    display: flex;
    align-items: center;
}

/* 표와 내용 간 간격 */
.table-wrapper {
    margin-top: 1.5rem;
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
		<h2 class="section-title">직급별 연봉 통계</h2>
		<div class="chart-area">
			<div class="salary-rank-scale" id="salaryRankScale"></div>
			<div id="salaryRankChart" class="salary-rank-chart"></div>
		</div>
		<div>최소연봉<i class="bx bxs-user"></i>최고연봉</div>
	</div>
	<div class="similar-companies">
		<h2 class="section-title">
			유사기업 비교 <span class="tooltip">?</span>
		</h2>
		<div class="table-wrapper">
			<table id="similarTable" class="compare-table">
				<thead></thead>
				<tbody></tbody>
			</table>
		</div>
	</div>


</body>
</html>