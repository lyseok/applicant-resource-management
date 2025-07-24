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
	color: var(--violet70);
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
	border: 2px solid var(--violet70) !important;
	background: rgba(124, 58, 237, 0.1);
}

.compare-table .selected.salary {
	font-size: 1.1rem;
	color: var(--violet70);
}
</style>
</head>
<body>
	<div class="container">

		<div class="similar-companies">
			<h2 class="section-title">
				유사기업 비교 <span class="tooltip">?</span>
			</h2>
			<div class="table-wrapper">
				<table class="compare-table">
					<thead>
						<tr>
							<th>기업명</th>
							<th class="selected">에스케이하이닉스</th>
							<th>엘지전자(주)</th>
							<th>삼성SDI</th>
							<th>삼성전자(주)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>평균연봉</td>
							<td class="selected salary">11,391만원</td>
							<td class="salary">8,497만원</td>
							<td class="salary">8,989만원</td>
							<td class="salary">10,376만원</td>
						</tr>
						<tr>
							<td>신입초봉</td>
							<td class="selected">5,559만원</td>
							<td>4,963만원</td>
							<td>4,693만원</td>
							<td>5,233만원</td>
						</tr>
						<tr>
							<td>매출액</td>
							<td class="selected">55조 7천억원</td>
							<td>28조 7천억원</td>
							<td>4조 4,228억원</td>
							<td>–</td>
						</tr>
						<tr>
							<td>평균근속연수</td>
							<td class="selected">13년 4개월</td>
							<td>11년 8개월</td>
							<td>12년 2개월</td>
							<td>12년 1개월</td>
						</tr>
						<tr>
							<td>사원수</td>
							<td class="selected">32,390명</td>
							<td>36,924명</td>
							<td>9,145명</td>
							<td>–명</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		
	</div>



</body>
</html>