<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>관리자 통계 대시보드</title>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script defer src="/js/admin/adminIndex.js"></script>
		<style>
			body {
				background-color: #f8f9fa;
				font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
				color: #333;
			}

			.container-fluid {
				padding: 20px;
				max-width: 1600px;
				margin: 0 auto;
			}

			/* Header Section */
			.header-section {
				background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
				border-radius: 12px;
				padding: 25px;
				margin-bottom: 25px;
				color: white;
				box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
			}

			.user-avatar.admin {
				width: 55px;
				height: 55px;
				background: rgba(255, 255, 255, 0.2);
				border-radius: 50%;
				display: flex;
				align-items: center;
				justify-content: center;
				font-size: 24px;
				color: white;
				border: 2px solid rgba(255, 255, 255, 0.3);
			}

			.user-details h4 {
				color: white;
				margin: 0;
				font-size: 20px;
				font-weight: 600;
			}

			.user-details p {
				color: rgba(255, 255, 255, 0.8);
				margin: 0;
				font-size: 14px;
			}

			.admin-controls .btn {
				border-color: rgba(255, 255, 255, 0.3);
				color: white;
			}

			.admin-controls .btn:hover {
				background-color: rgba(255, 255, 255, 0.1);
				border-color: white;
			}

			/* Key Metrics */
			.key-metrics {
				margin-bottom: 30px;
			}

			.metric-card {
				background: white;
				border-radius: 12px;
				padding: 25px;
				box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
				display: flex;
				align-items: center;
				gap: 20px;
				height: 100%;
				transition: transform 0.2s ease, box-shadow 0.2s ease;
			}

			.metric-card:hover {
				transform: translateY(-3px);
				box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
			}

			.metric-icon {
				width: 60px;
				height: 60px;
				border-radius: 12px;
				display: flex;
				align-items: center;
				justify-content: center;
				font-size: 24px;
				color: white;
				background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
			}

			.metric-content h3 {
				font-size: 28px;
				font-weight: 700;
				margin: 0;
				color: #333;
			}

			.metric-content p {
				margin: 5px 0;
				color: #6c757d;
				font-size: 14px;
				font-weight: 500;
			}

			.metric-change {
				font-size: 12px;
				font-weight: 600;
				padding: 4px 8px;
				border-radius: 20px;
			}

			.metric-change.positive {
				background-color: #d4edda;
				color: #155724;
			}

			.metric-change.negative {
				background-color: #f8d7da;
				color: #721c24;
			}

			/* Tab Navigation */
			.tab-navigation {
				background: white;
				border-radius: 12px;
				padding: 8px;
				box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			}

			.nav-pills .nav-link {
				border-radius: 8px;
				color: #6c757d;
				font-weight: 500;
				padding: 12px 20px;
				margin: 0 4px;
				transition: all 0.2s ease;
			}

			.nav-pills .nav-link.active {
				background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
				color: white;
			}

			.nav-pills .nav-link:hover:not(.active) {
				background-color: #f8f9fa;
				color: #495057;
			}

			/* Chart Cards */
			.chart-card {
				background: white;
				border-radius: 12px;
				padding: 25px;
				box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
				height: 100%;
				margin-bottom: 20px;
			}

			.chart-header {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 20px;
				flex-wrap: wrap;
				gap: 10px;
			}

			.chart-header h5 {
				margin: 0;
				font-size: 16px;
				font-weight: 600;
				color: #333;
			}

			.chart-controls {
				display: flex;
				gap: 5px;
			}

			.chart-controls .btn.active {
				background-color: #667eea;
				color: white;
				border-color: #667eea;
			}

			/* Ranking List */
			.ranking-list {
				max-height: 350px;
				overflow-y: auto;
			}

			.ranking-item {
				display: flex;
				align-items: center;
				padding: 15px 0;
				border-bottom: 1px solid #f1f3f4;
			}

			.ranking-item:last-child {
				border-bottom: none;
			}

			.rank {
				width: 32px;
				height: 32px;
				background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
				color: #333;
			}

			.applicants {
				color: #6c757d;
				font-size: 14px;
				font-weight: 500;
			}

			/* Market Indicators */
			.market-indicators {
				display: flex;
				flex-direction: column;
				gap: 20px;
			}

			.indicator-item {
				padding: 15px;
				background: #f8f9fa;
				border-radius: 8px;
				border-left: 4px solid #667eea;
			}

			.indicator-label {
				font-size: 12px;
				color: #6c757d;
				font-weight: 500;
				margin-bottom: 5px;
			}

			.indicator-value {
				font-size: 20px;
				font-weight: 700;
				color: #333;
				margin-bottom: 5px;
			}

			.indicator-change {
				font-size: 12px;
				font-weight: 600;
			}

			.indicator-change.positive {
				color: #28a745;
			}

			.indicator-change.negative {
				color: #dc3545;
			}

			/* Alerts Section */
			.alerts-section {
				margin-top: 30px;
			}

			.alerts-list {
				max-height: 300px;
				overflow-y: auto;
			}

			.alert-item {
				display: flex;
				align-items: flex-start;
				padding: 15px 0;
				border-bottom: 1px solid #f1f3f4;
			}

			.alert-item:last-child {
				border-bottom: none;
			}

			.alert-icon {
				width: 40px;
				height: 40px;
				border-radius: 50%;
				display: flex;
				align-items: center;
				justify-content: center;
				margin-right: 15px;
				font-size: 16px;
			}

			.alert-icon.success {
				background-color: #d4edda;
				color: #155724;
			}

			.alert-icon.info {
				background-color: #d1ecf1;
				color: #0c5460;
			}

			.alert-icon.warning {
				background-color: #fff3cd;
				color: #856404;
			}

			.alert-content {
				flex: 1;
			}

			.alert-title {
				font-weight: 600;
				color: #333;
				margin-bottom: 5px;
			}

			.alert-desc {
				color: #6c757d;
				font-size: 14px;
				margin-bottom: 5px;
			}

			.alert-time {
				color: #adb5bd;
				font-size: 12px;
			}

			/* Chart Containers */
			canvas {
				max-height: 350px;
			}

			.chart-card canvas {
				width: 100% !important;
				height: auto !important;
			}

			/* Responsive Design */
			@media (max-width: 768px) {
				.container-fluid {
					padding: 15px;
				}

				.header-section {
					padding: 20px;
				}

				.admin-controls {
					margin-top: 15px;
				}

				.admin-controls .btn {
					width: 100%;
					margin-bottom: 10px;
				}

				.metric-card {
					flex-direction: column;
					text-align: center;
					padding: 20px;
				}

				.chart-header {
					flex-direction: column;
					align-items: stretch;
				}

				.nav-pills .nav-link {
					padding: 10px 15px;
					font-size: 14px;
				}

				.tab-navigation {
					overflow-x: auto;
				}

				.nav-pills {
					flex-wrap: nowrap;
					min-width: max-content;
				}
			}

			/* Animation Effects */
			.metric-card,
			.chart-card {
				animation: fadeInUp 0.6s ease-out;
			}

			@keyframes fadeInUp {
				from {
					opacity: 0;
					transform: translateY(20px);
				}

				to {
					opacity: 1;
					transform: translateY(0);
				}
			}

			/* Custom Scrollbar */
			.ranking-list::-webkit-scrollbar,
			.alerts-list::-webkit-scrollbar {
				width: 6px;
			}

			.ranking-list::-webkit-scrollbar-track,
			.alerts-list::-webkit-scrollbar-track {
				background: #f1f1f1;
				border-radius: 3px;
			}

			.ranking-list::-webkit-scrollbar-thumb,
			.alerts-list::-webkit-scrollbar-thumb {
				background: #c1c1c1;
				border-radius: 3px;
			}

			.ranking-list::-webkit-scrollbar-thumb:hover,
			.alerts-list::-webkit-scrollbar-thumb:hover {
				background: #a8a8a8;
			}
		</style>

	</head>

	<body>
		<div class="container-fluid">
			<!-- Header Section -->
			<div class="header-section">
				<div class="row align-items-center mb-4">
					<div class="col-md-6">
						<div class="user-info">
							<div class="user-avatar admin">
								<i class="fas fa-user-shield"></i>
							</div>
							<div class="user-details">
								<h4>관리자 <i class="fas fa-chevron-right"></i></h4>
								<p class="text-muted">시스템 관리 · 통계 분석</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 text-end">
						<div class="admin-controls">
							<button class="btn btn-outline-primary me-2">
								<i class="fas fa-download"></i> 리포트 다운로드
							</button>
							<button class="btn btn-outline-success">
								실시간 모니터링 <i class="fas fa-chevron-right"></i>
							</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Key Metrics Overview -->
			<div class="key-metrics mb-5">
				<div class="row">
					<div class="col-lg-3 col-md-6 mb-3">
						<div class="metric-card">
							<div class="metric-icon">
								<i class="fas fa-users"></i>
							</div>
							<div class="metric-content">
								<h3>24,567</h3>
								<p>총 구직자</p>
								<span class="metric-change positive">+12.5%</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 mb-3">
						<div class="metric-card">
							<div class="metric-icon">
								<i class="fas fa-building"></i>
							</div>
							<div class="metric-content">
								<h3>1,234</h3>
								<p>등록 기업</p>
								<span class="metric-change positive">+8.3%</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 mb-3">
						<div class="metric-card">
							<div class="metric-icon">
								<i class="fas fa-briefcase"></i>
							</div>
							<div class="metric-content">
								<h3>3,456</h3>
								<p>활성 공고</p>
								<span class="metric-change positive">+15.7%</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 mb-3">
						<div class="metric-card">
							<div class="metric-icon">
								<i class="fas fa-won-sign"></i>
							</div>
							<div class="metric-content">
								<h3>₩45.2M</h3>
								<p>월 매출</p>
								<span class="metric-change positive">+22.1%</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Tab Navigation -->
			<div class="tab-navigation mb-4">
				<ul class="nav nav-pills" id="adminTabs" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="jobseeker-tab" data-bs-toggle="pill" data-bs-target="#jobseeker"
							type="button" role="tab">
							<i class="fas fa-user"></i> 구직자 통계
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="company-tab" data-bs-toggle="pill" data-bs-target="#company" type="button"
							role="tab">
							<i class="fas fa-building"></i> 기업 통계
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="talent-tab" data-bs-toggle="pill" data-bs-target="#talent" type="button"
							role="tab">
							<i class="fas fa-star"></i> 인재풀 통계
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="business-tab" data-bs-toggle="pill" data-bs-target="#business" type="button"
							role="tab">
							<i class="fas fa-chart-line"></i> 사업 인사이트
						</button>
					</li>
				</ul>
			</div>

			<!-- Tab Content -->
			<div class="tab-content" id="adminTabsContent">
				<!-- 구직자 통계 탭 -->
				<div class="tab-pane fade show active" id="jobseeker" role="tabpanel">
					<div class="row mb-4">
						<!-- 신규 가입자 추이 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>신규 가입자 추이</h5>
									<div class="chart-controls">
										<button class="btn btn-sm btn-outline-secondary" id="jobseekerWeeklyBtn">주별</button>
										<button class="btn btn-sm btn-outline-secondary" id="jobseekerMonthlyBtn">월별</button>
									</div>
								</div>
								<canvas id="newJobseekerChart"></canvas>
							</div>
						</div>
						<!-- 회원 활동 현황 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>회원 활동 현황</h5>
								</div>
								<canvas id="memberActivityChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 연령대별 분포 -->
						<div class="col-lg-3">
							<div class="chart-card">
								<div class="chart-header">
									<h5>연령대별 분포</h5>
								</div>
								<canvas id="ageDistChart"></canvas>
							</div>
						</div>
						<!-- 성별 분포 -->
						<div class="col-lg-3">
							<div class="chart-card">
								<div class="chart-header">
									<h5>성별 분포</h5>
								</div>
								<canvas id="genderDistChart"></canvas>
							</div>
						</div>
						<!-- 학력 분포 -->
						<div class="col-lg-3">
							<div class="chart-card">
								<div class="chart-header">
									<h5>학력 분포</h5>
								</div>
								<canvas id="educationDistChart"></canvas>
							</div>
						</div>
						<!-- 직무 선호도 -->
						<div class="col-lg-3">
							<div class="chart-card">
								<div class="chart-header">
									<h5>직무 선호도</h5>
								</div>
								<canvas id="jobPreferenceChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 지원 행동 분석 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>지원 행동 분석</h5>
								</div>
								<canvas id="applicationBehaviorChart"></canvas>
							</div>
						</div>
						<!-- 인기 공고 TOP 10 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>인기 공고 TOP 10</h5>
								</div>
								<div class="ranking-list">
									<div class="ranking-item">
										<span class="rank">1</span>
										<span class="job-title">프론트엔드 개발자</span>
										<span class="applicants">1,234</span>
									</div>
									<div class="ranking-item">
										<span class="rank">2</span>
										<span class="job-title">백엔드 개발자</span>
										<span class="applicants">987</span>
									</div>
									<div class="ranking-item">
										<span class="rank">3</span>
										<span class="job-title">데이터 분석가</span>
										<span class="applicants">856</span>
									</div>
									<div class="ranking-item">
										<span class="rank">4</span>
										<span class="job-title">UI/UX 디자이너</span>
										<span class="applicants">743</span>
									</div>
									<div class="ranking-item">
										<span class="rank">5</span>
										<span class="job-title">풀스택 개발자</span>
										<span class="applicants">692</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 기업 통계 탭 -->
				<div class="tab-pane fade" id="company" role="tabpanel">
					<div class="row mb-4">
						<!-- 신규 기업 가입 추이 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>신규 기업 가입 추이</h5>
									<div class="chart-controls">
										<button class="btn btn-sm btn-outline-secondary active">월별</button>
										<button class="btn btn-sm btn-outline-secondary">분기별</button>
									</div>
								</div>
								<canvas id="newCompanyChart"></canvas>
							</div>
						</div>
						<!-- 기업 규모별 분포 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>기업 규모별 분포</h5>
								</div>
								<canvas id="companySizeChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 유료/무료 회원 비율 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>유료/무료 회원</h5>
								</div>
								<canvas id="membershipTypeChart"></canvas>
							</div>
						</div>
						<!-- 채용 공고 현황 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>채용 공고 현황</h5>
								</div>
								<canvas id="jobPostingStatusChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 기업별 채용 성공률 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>기업별 채용 성공률</h5>
								</div>
								<canvas id="recruitmentSuccessChart"></canvas>
							</div>
						</div>
						<!-- 인재풀 활용 현황 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>인재풀 활용 현황</h5>
								</div>
								<canvas id="talentPoolUsageChart"></canvas>
							</div>
						</div>
					</div>
				</div>

				<!-- 인재풀 통계 탭 -->
				<div class="tab-pane fade" id="talent" role="tabpanel">
					<div class="row mb-4">
						<!-- 인재풀 등록 추이 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>인재풀 등록 추이</h5>
								</div>
								<canvas id="talentPoolTrendChart"></canvas>
							</div>
						</div>
						<!-- 관심 추가된 프로필 카테고리 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>관심 프로필 카테고리</h5>
								</div>
								<canvas id="interestProfileChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 프로젝트 참여 현황 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>프로젝트 참여 현황</h5>
								</div>
								<canvas id="projectParticipationChart"></canvas>
							</div>
						</div>
						<!-- 프로젝트 진행 상태 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>프로젝트 진행 상태</h5>
								</div>
								<canvas id="projectStatusChart"></canvas>
							</div>
						</div>
					</div>
				</div>

				<!-- 사업 인사이트 탭 -->
				<div class="tab-pane fade" id="business" role="tabpanel">
					<div class="row mb-4">
						<!-- 매출 추이 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>매출 추이</h5>
									<div class="chart-controls">
										<button class="btn btn-sm btn-outline-secondary active">월별</button>
										<button class="btn btn-sm btn-outline-secondary">분기별</button>
									</div>
								</div>
								<canvas id="revenueChart"></canvas>
							</div>
						</div>
						<!-- 서비스별 매출 비율 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>서비스별 매출</h5>
								</div>
								<canvas id="serviceRevenueChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 채용 리드타임 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>채용 리드타임</h5>
								</div>
								<canvas id="recruitmentLeadTimeChart"></canvas>
							</div>
						</div>
						<!-- 회원 이탈 분석 -->
						<div class="col-lg-6">
							<div class="chart-card">
								<div class="chart-header">
									<h5>회원 이탈 분석</h5>
								</div>
								<canvas id="churnAnalysisChart"></canvas>
							</div>
						</div>
					</div>

					<div class="row mb-4">
						<!-- 인기 기술 스택 트렌드 -->
						<div class="col-lg-8">
							<div class="chart-card">
								<div class="chart-header">
									<h5>인기 기술 스택 트렌드</h5>
								</div>
								<canvas id="techTrendChart"></canvas>
							</div>
						</div>
						<!-- 시장 동향 지표 -->
						<div class="col-lg-4">
							<div class="chart-card">
								<div class="chart-header">
									<h5>시장 동향 지표</h5>
								</div>
								<div class="market-indicators">
									<div class="indicator-item">
										<div class="indicator-label">평균 지원률</div>
										<div class="indicator-value">23.5%</div>
										<div class="indicator-change positive">+2.1%</div>
									</div>
									<div class="indicator-item">
										<div class="indicator-label">평균 채용 기간</div>
										<div class="indicator-value">28일</div>
										<div class="indicator-change negative">-3일</div>
									</div>
									<div class="indicator-item">
										<div class="indicator-label">평균 연봉</div>
										<div class="indicator-value">4,200만원</div>
										<div class="indicator-change positive">+5.2%</div>
									</div>
									<div class="indicator-item">
										<div class="indicator-label">활성 사용자율</div>
										<div class="indicator-value">67.8%</div>
										<div class="indicator-change positive">+1.5%</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Real-time Alerts -->
			<div class="alerts-section">
				<div class="chart-card">
					<div class="chart-header">
						<h5><i class="fas fa-bell text-warning"></i> 실시간 알림</h5>
						<button class="btn btn-sm btn-outline-secondary">모두 보기</button>
					</div>
					<div class="alerts-list">
						<div class="alert-item">
							<div class="alert-icon success">
								<i class="fas fa-user-plus"></i>
							</div>
							<div class="alert-content">
								<div class="alert-title">신규 기업 가입</div>
								<div class="alert-desc">(주)테크스타트업이 가입했습니다.</div>
								<div class="alert-time">5분 전</div>
							</div>
						</div>
						<div class="alert-item">
							<div class="alert-icon info">
								<i class="fas fa-briefcase"></i>
							</div>
							<div class="alert-content">
								<div class="alert-title">대량 공고 등록</div>
								<div class="alert-desc">삼성전자에서 15개 공고를 등록했습니다.</div>
								<div class="alert-time">12분 전</div>
							</div>
						</div>
						<div class="alert-item">
							<div class="alert-icon warning">
								<i class="fas fa-exclamation-triangle"></i>
							</div>
							<div class="alert-content">
								<div class="alert-title">시스템 부하 감지</div>
								<div class="alert-desc">서버 응답 시간이 평소보다 느립니다.</div>
								<div class="alert-time">25분 전</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</body>