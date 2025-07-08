<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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

.search-filter-bar button:hover {
	background-color: #3367d6;
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
<script defer src="/js/company/recruitment/talentpool/talentpool.js"></script>
</head>
<body>

	<!-- 🔍 상단 검색 바 -->
	<div class="search-filter-bar">
		<input type="text" name="keywordOr" placeholder="OR 키워드"> <input
			type="text" name="keywordAnd" placeholder="AND 키워드"> <input
			type="text" name="keywordNot" placeholder="NOT 키워드">
		<button type="submit">검색</button>
	</div>

	<!-- 📦 메인 컨텐츠 -->
	<div class="main-wrapper">

		<!-- 왼쪽 필터 -->
		<form action="/filter" method="post">
			<div class="left-filter">
				<h3>필터</h3>

				<!-- 경력 필터 -->
				<label>경력</label>
				<div style="display: flex; gap: 10px;">
					<select name="careerStart" varStatus="status">
						<option>선택</option>
						<c:forEach begin="0" end="40" var="i" varStatus="status">
							<option value="${i}">
								<c:choose>
									<c:when test="${status.first}">1년 이하</c:when>
									<c:otherwise>${i}년 이상</c:otherwise>
								</c:choose>
							</option>
						</c:forEach>
					</select>
				</div>

				<!-- 지역 필터 -->
				<label>지역</label> <select name="location">
    					<option value="">선택</option>
    					<c:forEach var="city" items="${cityList}">
        				<option value="${city.cityName}">${city.cityName}</option>
    				</c:forEach>
					</select>

				<!-- 최종학력 필터 -->
				<label>최종학력</label> 
					<select name="highest">
    					<option value="">선택</option>
    					<c:forEach var="fedu" items="${eduList}">
        				<option value="${fedu.highestEducationCode}">${fedu.highestEducationCode}</option>
    				</c:forEach>
					</select>

				<!-- 졸업 여부 필터 -->
				<label>졸업 상태</label> 
				<select name="graduate_edu">
    					<option value="">선택</option>
    					<c:forEach var="gedu" items="${eduList}">
        				<option value="${gedu.graduateYn}">${gedu.graduateYn}</option>
    				</c:forEach>
					</select>

				<!-- 직군 필터 -->
				<label>직군</label>
				 <select name="topJob">
				 	<option value="">선택</option>
				 	<c:forEach var="tjob" items="${tjobList }">
				 	<option value="${tjob.topJobName}">${tjob.topJobName }</option>
				 	</c:forEach>
				</select>

				<!-- 직무 필터 -->
				<label>직무</label> 
					<select name="job">
    					<option value="">선택</option>
    					<c:forEach var="job" items="${jobList}">
        				<option value="${job.jobCode}">${job.jobName}</option>
    				</c:forEach>
					</select>
				
				<!-- 검색 버튼 -->
				<div style="margin-top: 20px; text-align: right;">
					<button type="submit"
						style="background-color: #4285f4; color: white; padding: 10px 16px; border: none; border-radius: 6px; font-weight: bold; cursor: pointer;">검색하기</button>
				</div>
			</div>
		</form>

		<!-- 오른쪽 리스트 -->
		<div class="content-area">
			<c:if test="${not empty talentpoolList}">
				<c:forEach items="${talentpoolList}" var="talent">
					<div class="user-card" data-user-id="${talent.userId}">
						<div class="user-avatar"></div>
						<div class="user-info">
							<div class="user-meta">
								<span class="user-name">${talent.userName}</span> 
								<c:forEach var="career" items="${talent.careerList }" varStatus="status">
								<span class="user-experience">경력 ${career.careerYear}년 
								<c:if test="${talent.careerYear}">
										<span class="new-badge">New</span>
									</c:if>
								</span>
								</c:forEach>
							</div>
							<div class="user-position-salary">
							<c:forEach var="job" items="${talent.careerList }" varStatus="status">
								<span class="position-name">${job.jobCode}</span> <span
									class="working-period"> <c:if
										test="${not empty job.startWorkDate}">
                                    (${job.startWorkDate} 
                                    <c:if
											test="${not empty job.retireDate}">- ${talent.retireDate}</c:if>
										<c:if test="${empty job.retireDate}">- 재직중</c:if>)
                                </c:if> <c:if test="${not empty job.salary}">
                                    , 연봉 ${job.salary}
                                </c:if>
								</span>
								</c:forEach>
							</div>
							<c:forEach var="job2" items="${job2.careerList }" varStatus="status">
							<div class="user-description">${job2.responsibility}</div>
							<div class="user-career-path">
								<span class="company-team">${job2.jobCode}</span> (1년 2개월) <span
									class="company-team">${job2.jobCode}</span> (2년)
							</div>
								</c:forEach>
							<div class="user-education">서울대학교 컴퓨터 공학과 (졸업)</div>
						</div>
						<div class="action-buttons">
							<button class="save-button">후보자 저장</button>
							<button class="reject-button">이직제안 하기</button>
						</div>
					</div>
				</c:forEach>
			</c:if>

			<c:if test="${empty talentpoolList}">
				<p style="text-align: center; margin-top: 50px; color: #777;">검색
					결과가 없습니다.</p>
			</c:if>
		</div>

	</div>
</body>
</html>
