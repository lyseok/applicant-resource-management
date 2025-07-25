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
	--violet70: #7c3aed; /* 주요 포인트 컬러 */
	--gray200: #e5e5e5;
	--gray500: #6b7280;
	--gray700: #374151;
	--border: 1px solid #d1d5db;
	--radius: 8px;
	--bg-light: #f9fafb;
	--animation-duration: 2s;
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

/* 제목 스타일 */
.title {
	font-size: 1.5rem;
	color: var(--violet70);
	font-weight: bold;
	margin-bottom: 1rem;
}

/* 직급별 연봉 테이블 */
.salary-table {
	width: 100%;
	border-collapse: collapse;
}

.salary-table th, .salary-table td {
	padding: 1rem;
	text-align: center;
	border: var(--border);
}

.salary-table th {
	background-color: var(--gray200);
	font-size: 1.1rem;
}

.salary-table td {
	font-size: 1rem;
}

/* 연봉 그래프 스타일 */
.salary-bar {
	position: relative;
	height: 20px;
	background-color: var(--gray200);
	border-radius: var(--radius);
	margin: 5px 0;
}

.salary-bar-inner {
	position: absolute;
	top: 0;
	left: 0;
	height: 100%;
	background-color: var(--violet70);
	border-radius: var(--radius);
}

/* 사람 아이콘 */
.person-icon {
	font-size: 24px;
	color: white;
	background-color: var(--violet70);
	width: 30px;
	height: 30px;
	border-radius: 50%;
	position: absolute;
	top: -15px; /* 위치를 조정하여 그래프 상단에 맞춤 */
	left: 0; /* 초기 위치: 왼쪽 끝 */
	animation: movePerson var(--animation-duration) ease-out forwards;
	z-index: 10; /* 다른 요소들보다 앞에 오도록 설정 */
}

/* 사람 아이콘 움직이는 애니메이션 */
@
keyframes movePerson { 0% {
	left: 0;
}

100




%
{
left




:




52


%; /* 그래프의 평균 연봉 위치에 맞추어 이동 */
}
}

/* 연봉 정보 */
.salary-info {
	position: absolute;
	top: 50%;
	left: 100%;
	transform: translateY(-50%) translateX(10px);
	font-size: 0.85rem;
	color: var(--gray500);
}

/* 연봉 텍스트 */
.salary-text {
	font-weight: bold;
}

/* 데이터 목록 */
.salary-data {
	display: flex;
	justify-content: space-between;
	margin: 0.5rem 0;
}

.salary-data span {
	font-size: 1rem;
}

/* 직급별 연봉 그래프에서 평균연봉 표시 */
.salary-avg {
	font-size: 1.2rem;
	font-weight: bold;
	color: var(--violet70);
}

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
	width: 100%;
	border-collapse: collapse;
	text-align: center;
	font-size: 0.95rem;
}

.compare-table th, .compare-table td {
	padding: 0.75rem 1rem;
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
	/* border: 5px solid var(--violet70) !important; */
	background: rgba(124, 58, 237, 0.2);
}

.compare-table  .selected.salary {
	font-size: 1.1rem;
	color: var(--violet70);
}

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




.chart-cell {
  position: relative;
  padding-top: 1.5rem; /* 레이블 공간 확보 */
}

/* 레이블만 위에 올려줄 때 */
.chart-labels {
  position: absolute;
  top: 0; left: 0;
  width: 100%; height: 1rem;
  pointer-events: none;
}

.salary-label {
  position: absolute;
  top: 0;
  font-size: 0.75rem;
  color: var(--gray500);
  white-space: nowrap;
   transition: left 2s ease-out;
}

/* 좌우로 붙여줄 transform */
.salary-label.min {   transform: translateX( calc(-100% - 8px) ); }
.salary-label.max {transform: translateX( 8px );}

/* salary-bar: 그래프 영역 */
.salary-bar {
  position: relative;
  margin-top: 1.5rem; /* 레이블 띄우기 */
  height: 30px;
  background: var(--gray200);
  border-radius: var(--radius);
  overflow: hidden;
}

/* 평균 아이콘 */
.person-icon {
  position: absolute;
  top: 50%;           /* salary-bar 높이 기준 중앙 */
  transform: translate(-50%, -50%);
  transition: left 2s ease-out;
  font-size: 1.2rem;
  width: 24px; height: 24px;
  background: var(--violet50);
  color: #fff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
}

/* 평균 바 */
.salary-bar-inner {
  position: absolute;
  top: 0; left: 0;
  height:100%;
  width:0;
  background: var(--violet50);
  border-radius: var(--radius);
  transition: width 2s ease-out;
}

/* 회색 구간 */
.range-bar-inner {
  position: absolute;
  top:0;
  height:100%;
  background: #e5e5e5;
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
	
	<div class="salary-chart">
	<h2 class="section-title">직급별 비교 <span class="tooltip">?</span></h2>
    <table id="salaryTable" class="salary-table">
    
     <colgroup>
    <col style="width: 10%;">  <!-- 직급 -->
    <col style="width: 10%;">  <!-- 평균연봉 -->
    <col style="width: 60%;">  <!-- 최저‑최고 구간 -->
  </colgroup>
      <thead>
        <tr>
          <th>직급</th>
          <th>평균연봉</th>
          <th>최저‑최고 구간</th>
        </tr>
      </thead>
      <tbody></tbody>
    </table>
  </div>
  
  
	<div class="similar-companies">
		<h2 class="section-title">유사기업 비교 <span class="tooltip">?</span></h2>
		<div class="table-wrapper">
			<table id="similarTable" class="compare-table">
				<thead></thead>
				<tbody></tbody>
			</table>
		</div>
	</div>


</body>
</html>