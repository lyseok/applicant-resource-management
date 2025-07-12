<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script defer src="/js/company/recruitment/talentpool/talentpool.js"></script>

<head>
<meta charset="UTF-8">
<title>인재풀 목록</title>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	margin: 0;
	background-color: #f5f6f7;
}

/* 검색 바 */
.search-filter-bar {
	display: flex;
	justify-content: center;
	gap: 10px;
	padding: 20px;
	background-color: #ffffff;
	border-bottom: 1px solid #ddd;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.03);
	flex-wrap: wrap;
}

.search-filter-bar input {
	padding: 10px 12px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
	width: 220px;
}

.search-filter-bar button {
	padding: 10px 18px;
	background-color: #4285f4;
	color: #fff;
	border: none;
	border-radius: 6px;
	font-weight: bold;
	cursor: pointer;
}



/* 전체 레이아웃 */
.main-wrapper {
	display: flex;
	max-width: 1200px;
	margin: 20px auto;
	gap: 20px;
}

/* 왼쪽 필터 박스 */
.left-filter {
	width: 250px;
	background: #ffffff;
	border-radius: 10px;
	padding: 20px;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
	flex-shrink: 0;
}

.left-filter h3 {
	margin-top: 0;
	margin-bottom: 16px;
	font-size: 18px;
}

.left-filter label {
	display: block;
	margin-top: 14px;
	font-weight: bold;
	font-size: 14px;
	color: #333;
}

.left-filter select, .left-filter input {
	width: 100%;
	padding: 8px;
	margin-top: 6px;
	border-radius: 6px;
	border: 1px solid #ccc;
	font-size: 14px;
}

/* 오른쪽 카드 리스트 */
.content-area {
	flex: 1;
}

/* 카드 디자인 */
.user-card {
	display: flex;
	align-items: flex-start;
	background-color: #ffffff;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
	margin-bottom: 20px;
	gap: 20px;
	position: relative;
}

.user-avatar {
	width: 60px;
	height: 60px;
	background-color: #e0e0e0;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-shrink: 0;
}

.user-avatar::before {
	content: '👤';
	font-size: 30px;
	color: #666;
}

.user-info {
	flex-grow: 1;
}

.user-meta {
	display: flex;
	align-items: center;
	font-size: 16px;
	color: #333;
	margin-bottom: 8px;
}

.user-name {
	font-weight: bold;
	margin-right: 8px;
}

.user-experience {
	color: #666;
	margin-right: 8px;
}

.new-badge {
	background-color: #ff5722;
	color: #ffffff;
	font-size: 12px;
	padding: 2px 6px;
	border-radius: 4px;
	font-weight: bold;
	margin-left: 4px;
	white-space: nowrap;
}

.user-position-salary {
	font-size: 15px;
	color: #444;
	margin-bottom: 4px;
}

.position-name {
	font-weight: 600;
}

.working-period {
	color: #777;
	font-size: 14px;
}

.user-description {
	font-size: 14px;
	color: #555;
	line-height: 1.5;
	margin-bottom: 8px;
}

.user-career-path, .user-education {
	font-size: 14px;
	color: #666;
	margin-bottom: 4px;
}

.company-team {
	font-weight: 500;
}

.action-buttons {
	display: flex;
	flex-direction: column;
	gap: 10px;
	flex-shrink: 0;
}

.save-button, .reject-button {
	padding: 10px 15px;
	border: 1px solid;
	border-radius: 6px;
	font-weight: bold;
	cursor: pointer;
	white-space: nowrap;
	min-width: 120px;
	text-align: center;
}

.save-button {
	background-color: #4285f4;
	color: #ffffff;
	border-color: #4285f4;
}

.save-button:hover {
	background-color: #3367d6;
}

.reject-button {
	background-color: #ffffff;
	color: #777;
	border-color: #ccc;
}

.reject-button:hover {
	background-color: #f0f0f0;
}
</style>
</head>
<body>

	<!-- 🔍 상단 검색 바 -->
	<form action="/company/talentpool/higtsearch" method="post">
	<div class="search-filter-bar">
		<input type="text" name="license" placeholder="원하시는 자격증있나요?">
		<input type="text" name="skillName" placeholder="원하시는 기술명있나요?">
		<button class="btn btn_violet" type="submit">검색</button>
	</div>
	</form>
	
	

	<!-- 📦 메인 컨텐츠 -->
	<div class="main-wrapper">

		<!-- 왼쪽 필터 -->
		<form id="filterForm" action="/company/talentpool/filter" method="post">
			<div class="left-filter">
				<h3>필터</h3>

				<!-- 경력 필터 -->
				<label>경력</label>
				<div style="display: flex; gap: 10px;">
					<select name="careerlong" id="yearSel">
						<option value="" >선택</option>
<%-- 						<c:forEach var="career" items="${careerList}"> --%>
<%-- 							<option value="${career.careerYear}"  --%>
<%-- 							<c:if test="${careerlong == career.careerYear}"> selected</c:if>> --%>
<%-- 							${career.careerYear } --%>
<!-- 							</option> -->
<%-- 						</c:forEach> --%>
					</select>
				</div>

				<label>지역</label> <select name="location">
					<option value="">선택</option>
					<c:forEach var="city" items="${cityList}">
						<option value="${city.cityName}"
							<c:if test="${location == city.cityName}">selected</c:if>>
							${city.cityName}</option>
					</c:forEach>
				</select>

				<!-- 최종학력 필터 -->
				<label>최종학력</label> 
				<select name="edudone" id="finedu">
					<option value="">선택</option>		
				</select>

				<!-- 졸업 여부 필터 -->
				<label>졸업 상태</label> <select name="gedu" id="edustatus">
					<option value="">선택</option>	
				</select>

				<!-- 직군 -->
				<label>직군</label> <select id="positionSelect" name="topJob">
					<option value="">선택</option>
					<c:forEach var="tjob" items="${tjobList}">
						<option value="${tjob.topJobCode}"
							<c:if test="${topJob == tjob.topJobName}">selected</c:if>>
							${tjob.topJobName}</option>
					</c:forEach>
				</select>

				<!-- 직무 -->
				<label>직무</label> <select id="jobSelect" name="job">
					<option value="">선택</option>
					<c:forEach var="job" items="${jobList}">
						<option value="${job.jobCode}"
							data-top-job-code="${job.topJobCode}">${job.jobName}</option>
					</c:forEach>
				</select>


				<!-- 검색 버튼 -->
				<div class="d-flex justify-content-between mt-3">
					<button type="button" id="resetFilter" class="btn btn_red_line">초기화</button>
					<button type="submit"  class="btn btn_violet">검색하기</button>
				</div>
			</div>
		</form>


		<!-- 오른쪽 콘텐츠 영역 -->
		<div class="content-area">
			<div id="talent-list-wrapper">
				<jsp:include page="TalentpoolListFragment.jsp" />
			</div>
		</div>
	</div>


</body>
