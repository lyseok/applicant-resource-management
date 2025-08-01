<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>채용 통계 대시보드</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"> -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/company/indexTest.js" defer></script>
<script defer src="/js/company/companyPage.js"></script>
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css" rel="stylesheet">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.18/index.global.min.js'></script>
<!-- <link rel="stylesheet" href="styles.css"> -->

<style>
.mypage-body {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		sans-serif;
	background-color: var(--violet10);
}

.mypage-body .layout-demo-wrapper {
	background-color: var(--violet10);
	box-shadow: none;
}

/* Global Styles */
/* body {
        background-color: #f8f9fa;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
        color: #333;
    }
    
    .container-fluid {
        padding: 20px;
        max-width: 1400px;
        margin: 0 auto;
    } */

/* Header Section */
.header-section {
	background: white;
	border-radius: 12px;
	padding: 20px;
	margin-bottom: 20px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.user-info {
	display: flex;
	align-items: center;
	gap: 15px;
}

.user-avatar {
	width: 50px;
	height: 50px;
	background: #e9ecef;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 20px;
	color: #6c757d;
}

.user-details h4 {
	margin: 0;
	font-size: 18px;
	font-weight: 600;
}

.user-details p {
	margin: 0;
	font-size: 14px;
}

/* Stats Cards */
.stats-card, .chart-card {
	background: white;
	border-radius: 12px;
	padding: 24px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	height: 100%;
}

.stats-title {
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 20px;
	color: #333;
}

.stat-item, .project-stat {
	padding: 10px 0;
}

.stat-number, .project-number {
	font-size: 32px;
	font-weight: 700;
	color: #333;
	margin-bottom: 5px;
}

.project-number {
	font-size: 36px;
	color: #333;
}

.stat-label, .project-label {
	font-size: 12px;
	color: #6c757d;
	font-weight: 500;
}

/* Chart Cards */
.chart-header {
	display: flex;
	justify-content: between;
	align-items: center;
	margin-bottom: 20px;
}

.chart-header h5 {
	margin: 0;
	font-size: 16px;
	font-weight: 600;
}

.chart-controls {
	display: flex;
	gap: 5px;
}

.chart-controls .btn.active {
	background-color: var(--violet70);
	color: white;
	border-color: var(--violet70);
}

/* Ranking List */
.ranking-list {
	max-height: 300px;
	overflow-y: auto;
}

.ranking-item {
	display: flex;
	align-items: center;
	padding: 12px 0;
	border-bottom: 1px solid #f1f3f4;
}

.ranking-item:last-child {
	border-bottom: none;
}

.rank {
	width: 30px;
	height: 30px;
	background: var(--violet70);
	color: white;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-weight: 600;
	font-size: 14px;
	margin-right: 15px;
}

.job-title {
	flex: 1;
	font-weight: 500;
}

.applicants {
	color: #6c757d;
	font-size: 14px;
}

/* Calendar Styles */
.calendar-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
	flex-wrap: wrap;
	gap: 15px;
}

.calendar-nav {
	display: flex;
	align-items: center;
	gap: 10px;
}

.calendar-title {
	font-size: 18px;
	font-weight: 600;
	margin: 0 15px;
}

.view-controls {
	display: flex;
	gap: 5px;
}

.calendar-grid {
	display: grid;
	grid-template-columns: repeat(7, 1fr);
	gap: 1px;
	background: #e9ecef;
	border-radius: 8px;
	overflow: hidden;
}

.calendar-header-cell {
	background: #f8f9fa;
	padding: 12px;
	text-align: center;
	font-weight: 600;
	font-size: 14px;
	color: #6c757d;
}

.calendar-cell {
	background: white;
	padding: 12px;
	min-height: 80px;
	display: flex;
	flex-direction: column;
	position: relative;
}

.calendar-cell.other-month {
	background: #f8f9fa;
	color: #adb5bd;
}

.calendar-cell.today {
	background: #e3f2fd;
}

.calendar-date {
	font-weight: 500;
	margin-bottom: 5px;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.container-fluid {
		padding: 10px;
	}
	.user-info {
		flex-direction: column;
		text-align: center;
	}
	.calendar-header {
		flex-direction: column;
		align-items: stretch;
	}
	.calendar-nav {
		justify-content: center;
	}
	.stat-number, .project-number {
		font-size: 24px;
	}
	.chart-header {
		flex-direction: column;
		align-items: stretch;
		gap: 10px;
	}
}

/* Chart Container Styles */
canvas {
	max-height: 300px;
}

.chart-card canvas {
	width: 100% !important;
	height: auto !important;
}

/* Button Styles */
.btn-outline-success {
	border-color: #28a745;
	color: #28a745;
}

.btn-outline-success:hover {
	background-color: #28a745;
	border-color: #28a745;
}

/* Animation */
.stats-card, .chart-card {
	transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.stats-card:hover, .chart-card:hover {
	transform: translateY(-2px);
	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.user-avatar {
    width: 48px;           /* 원하는 크기 */
    height: 48px;
    border-radius: 50%;    /* 원형으로 */
    background-color: #eaeef1; /* 로고 없을 때 배경색 */
    overflow: hidden;      /* 이미지가 넘치지 않게 */
    display: flex;
    align-items: center;
    justify-content: center;
}

.user-avatar img {
    width: 100%;
    height: 100%;
    object-fit: cover;      /* 이미지 꽉 채우기 */
}
</style>
</head>

<body class="mypage-body">
	<div class="container-fluid">
		<!-- Header Section -->
		<div class="header-section">
			<div class="row align-items-center mb-4">
				<div class="col-md-6">
					<div class="user-info">
						<div class="user-avatar">
						    <img id="comImage" src="" alt="회사 로고">
						</div>
						<div class="user-details">
							<h4 id = "comName">
								기업명 <i class="fas fa-chevron-right"></i>
							</h4>
							<p class="text-muted">채용 · 인사관리 솔루션</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 text-end">
					<button class="btn btn-outline-success">
						이력서 업데이트 <i class="fas fa-chevron-right"></i>
					</button>
				</div>
			</div>
		</div>

		<!-- Statistics Overview -->
		<div class="stats-overview mb-5">
			<div class="row">
				<div class="col-md-6">
					<div class="stats-card">
						<h5 class="stats-title">지원 현황</h5>
						<div class="row text-center">
							<div class="col-3">
								<div class="stat-item">
									<div class="stat-number">1,247</div>
									<div class="stat-label">지원 접수</div>
								</div>
							</div>
							<div class="col-3">
								<div class="stat-item">
									<div class="stat-number">892</div>
									<div class="stat-label">서류 합격</div>
								</div>
							</div>
							<div class="col-3">
								<div class="stat-item">
									<div class="stat-number">234</div>
									<div class="stat-label">최종 합격</div>
								</div>
							</div>
							<div class="col-3">
								<div class="stat-item">
									<div class="stat-number">156</div>
									<div class="stat-label">활성 공고</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 받은 제안 → 인재풀 정보로 교체 -->
				<div class="col-md-6">
					<div class="stats-card">
						<h5 class="stats-title">인재풀 정보</h5>
						<div class="row text-center">
							<div class="col-3">
								<div class="project-stat">
									<div class="project-number">15,234</div>
									<div class="project-label">총 구직자 수</div>
								</div>
							</div>
							<div class="col-3">
								<div class="project-stat">
									<div class="project-number">2,456</div>
									<div class="project-label">신규 가입자</div>
								</div>
							</div>
							<div class="col-3">
								<div class="project-stat">
									<div class="project-number">8,901</div>
									<div class="project-label">활성화 프로필</div>
								</div>
							</div>
							<div class="col-3">
								<div class="project-stat">
									<div class="project-number">1,234</div>
									<div class="project-label">내가 찜한 인재</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Charts Section -->
		<div class="charts-section">
			<div class="row mb-4">
				<div class="col-md-8">
					<div class="chart-card">
						<div class="chart-header">
							<h5>신규 가입자 추이</h5>
							<div class="chart-controls">
								<button class="btn btn-sm btn-outline-secondary active">월별</button>
								<button class="btn btn-sm btn-outline-secondary">분기별</button>
							</div>
						</div>
						<canvas id="newMembersChart"></canvas>
					</div>
				</div>
				<div class="col-md-4">
					<div class="chart-card">
						<div class="chart-header">
							<h5>연령대별 분포</h5>
						</div>
						<canvas id="ageDistributionChart"></canvas>
					</div>
				</div>
			</div>

			<div class="row mb-4">
				<div class="col-md-4">
					<div class="chart-card">
						<div class="chart-header">
							<h5>학력별 분포</h5>
						</div>
						<canvas id="educationChart"></canvas>
					</div>
				</div>
				<div class="col-md-4">
					<div class="chart-card">
						<div class="chart-header">
							<h5>경력 구간별 분포</h5>
						</div>
						<canvas id="experienceChart"></canvas>
					</div>
				</div>
				<div class="col-md-4">
					<div class="chart-card">
						<div class="chart-header">
							<h5>성별 분포</h5>
						</div>
						<canvas id="genderChart"></canvas>
					</div>
				</div>
			</div>

			<div class="row mb-4">
				<div class="col-md-8">
					<div class="chart-card">
						<div class="chart-header">
							<h5>보유 기술 스택 TOP 10</h5>
						</div>
						<canvas id="skillsChart"></canvas>
					</div>
				</div>
				<!-- 채용 단계별 현황 → 인기 공고 랭킹으로 교체 -->
				<div class="col-md-4">
					<div class="chart-card">
						<div class="chart-header">
							<h5>인기 공고 랭킹 TOP 10</h5>
						</div>
						<div class="ranking-list">
							<div class="ranking-item">
								<span class="rank">1</span><span class="job-title">프론트엔드
									개발자</span><span class="applicants">1,234명</span>
							</div>
							<div class="ranking-item">
								<span class="rank">2</span><span class="job-title">백엔드
									개발자</span><span class="applicants">987명</span>
							</div>
							<div class="ranking-item">
								<span class="rank">3</span><span class="job-title">풀스택
									개발자</span><span class="applicants">856명</span>
							</div>
							<div class="ranking-item">
								<span class="rank">4</span><span class="job-title">데이터
									분석가</span><span class="applicants">743명</span>
							</div>
							<div class="ranking-item">
								<span class="rank">5</span><span class="job-title">UI/UX
									디자이너</span><span class="applicants">692명</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 월별 지원자 추이 및 합격률 (가로 전체) -->
			<div class="row mb-4">
				<div class="col-12">
					<div class="chart-card">
						<div class="chart-header">
							<h5>월별 지원자 추이 및 합격률</h5>
						</div>
						<canvas id="applicantTrendChart"></canvas>
					</div>
				</div>
			</div>
		</div>

		<!-- Calendar 바로 아래 -->
		<div class="calendar-section">
			<div class="chart-card">
				<div id="calendar"></div>
		   </div>
	</div>
	
	
	
	

<!-- 모달 -->
<div class="modal fade" id="eventViewModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p id="modalDateInfo"></p>
        <div id="recruitDateBlock">
            <p><strong>공고 시작일:</strong> <span id="modalRecruitStart"></span></p>
            <p><strong>마감일:</strong> <span id="modalRecruitFinish"></span></p>
        </div>
      </div>
    </div>
  </div>
</div>

	
</body>