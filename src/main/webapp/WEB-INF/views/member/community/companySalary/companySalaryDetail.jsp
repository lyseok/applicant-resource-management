<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<head>
<meta charset="UTF-8">
<title>연봉정보 상세</title>

<script src="/js/member/community/companySalary/companySalaryDetail.js"></script>
<style type="text/css">
:root {
	--violet70: #7c3aed;
	--gray200: #e5e5e5;
	--gray500: #6b7280;
	--gray700: #374151;
	--radius: 8px;
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


.table-scroll-container {
    position: relative;
    display: flex;
    align-items: flex-start;
    border:1px solid #ddd;
    border-radius:10px;
    overflow:hidden;
}

.fixed-table-wrapper {
    position: sticky;
    left: 0;
    z-index: 5;
    background: #fff;
}

.fixed-table-wrapper .compare-table {
    width: auto;
    border-collapse: collapse;
}

.table-wrapper {
    overflow-x: hidden; /* 넘치는 부분 가리기 */
    flex: 1;
    max-width: calc(210px * 4); /* 4개의 열만 보이도록 (열 너비 * 4) */
    scroll-behavior: smooth;
}

.compare-table {
    border-collapse: collapse;
    text-align: center;
    font-size: 1.2rem;
    border: none;
    min-width: max-content;
}
.compare-table th, .compare-table td {
    padding: 20px 10px;
    border: none;
    text-align: center;
    min-width: 210px;
}
#fixedTable thead th:first-child,
#fixedTable tbody td:first-child {
    font-weight: bold;
    border-bottom: 1px solid #ddd;
}
.compare-table tr {
	border-bottom:1px solid #ddd;
}
#fixedTable tbody td:first-child,
.compare-table tbody tr:last-child{
	border-bottom:0;
}


.compare-table .selected {
    background: rgba(124, 58, 237, 0.08);
}

.scroll_btn_wrap{
	display:flex;
	align-items:center;
	justify-content:center;
  gap:10px;
}
.scroll-btn {
    width: 40px;
    height: 40px;
    background: #fff;
    border: 1px solid var(--violet80);
    border-radius: 50%; /* 원형 */
    z-index: 10;
    font-size: 1.5rem;
    color: var(--violet80);
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: background 0.2s, color 0.2s;
}
.scroll-btn:hover {
    background: var(--violet70);
    color: #fff;
}
.scroll-btn.left {
    left: -60px; /* 살짝 바깥으로 */
}
.scroll-btn.right {
    right: -60px;
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
	background: var(--violet30);
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
	color: var(--violet70);
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

.section-card{
	padding-bottom: 70px;
}

#sim-section{
	padding-top: 30px;
}

#cht-section{
	width: 90%;
	align-items: center;
}
</style>
</head>
<body>

		<h1 class="h2 mb-5 fw-bold  mw-1260 mx-auto">연봉정보 상세</h1>
		
    <!-- 요약 -->
    <div class="section-card">
        <div class="h3 mb-4">기업 연봉 요약</div>
        <div class="salary-summary-box">
            <div class="summary-item">
                <div id="salaryCompanyName"></div>
                <div class="summary-value" id="salaryAvgGross">-- 만원</div>
                <div class="summary-label">전체 평균 연봉</div>
                <div class="summary-sub">(임원 제외)</div>
                <div class="summary-monthly" id="salaryAvgNet"></div>
            </div>
            <div class="summary-item">
                <div id="salaryCompanyIndu"></div>
                <div class="summary-value" id="salaryRank">-- 위</div>
                <div class="summary-label">평균연봉 순위 </div>
                <div class="summary-monthly" id="salaryNewhireNet"></div>
            </div>
        </div>
    </div>

    <!-- 차트 -->
    <div class="section-card" id="cht-section">
        <div class="h3 mb-4">직급별 연봉 통계</div>
        <div class="chart-area">
            <div id="salaryRankScale"></div>
            <div id="salaryRankChart" class="salary-rank-chart"></div>
        </div>
    </div>

    <!-- 유사 기업 -->
    <div class="section-card" id="sim-section">
        <div class="d-flex justify-content-between align-items-center mb-4">
        	<h3 class="h3 m-0">유사기업 비교</h3>        	
	        <div class="scroll_btn_wrap">
		       <button class="scroll-btn left">
		       		<span class="material-symbols-outlined">keyboard_arrow_left</span>
		       </button>
		       <button class="scroll-btn right">
		       		<span class="material-symbols-outlined">keyboard_arrow_right</span>	       		
		       </button>
	       </div>
        </div>
        <div class="table-scroll-container">
            <div class="fixed-table-wrapper">
                <table id="fixedTable" class="compare-table">
                    <thead></thead>
                    <tbody></tbody>
                </table>
            </div>
            <div class="table-wrapper">
                <table id="scrollTable" class="compare-table">
                    <thead></thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
        
    </div>
</body>