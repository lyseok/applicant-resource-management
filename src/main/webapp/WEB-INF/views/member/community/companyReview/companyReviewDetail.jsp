<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<head>
	<meta charset="UTF-8">
	<title>기업 리뷰 상세</title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="/js/member/community/companyView/companyReviewChart.js"></script>
	<style>
	
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
	
	
	
	/* 콘텐츠 영역 */
	.tab-content {
		display: none;
		margin-top: 50px;
	}
	
	.tab-content.active {
		display: block;
	}
	
	
	/* 전체평균 */
	/* 전체 평균 차트 크기 */
	#overallAvgChart,
	#topJobAvgChart {
		width: 200px !important;
		height: 200px !important;
	}
	.chart-sec-wrap{
		position:relative;
	}
	.chart-sec-wrap:after{
		content: '';
	  position: absolute;
	  left: 50%;
	  top: 50%;
	  width: 1px;
	  height: 150px;
	  background: #ddd;
	  transform: translateY(-50%);
	}
	.chart-sec-col{
		flex-basis:calc((100% - 24px) / 2);
	}
	#employeeStatusChart{margin-top:-30px;}
	.employee-text-box{margin-top:-36px;}
	
	/* 질문별 평균 차트 */
	.question-charts > div{
		position:relative;
		display:flex;
		align-items:center;
		justify-content:center;
		flex-direction: column;
		gap:16px;
		width:calc((100% - 32px) / 3);
		text-align:center;
		border-radius:10px 10px 0 0;
		padding:24px 16px;
		border:1px solid #ddd;
	}
	.question-charts > div .total_count {
    position: absolute;
    top: 41%;
    left: 50%;
    transform: translate(-50%, -50%);
	}
	/* 질문별 평균 차트 크기 */
	#questionCharts canvas,
	#topJobQuestionCharts canvas {
		width: 120px !important;
		height: 120px !important;
	}
	
	
	
	
	
	
	/* 기업 정보 */
	.company-info-box {
		position:relative;
		display: flex;
		justify-content: space-between;
		align-items: flex-end; /* 하단 정렬로 맞춤 */
		min-height: 70px; /* 로고와 차트 높이 맞춤 */
		padding-top:90px;
		/* padding-bottom: 50px; */
		margin-bottom: 20px;
	}
	.company-info-box:after{
		content:'';
		position:absolute;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(239 229 255/0.5);
		z-index:1;
	}
	
	.company-info-left {
		position:relative;
		display: flex;
	  flex-direction: column;
		gap: 20px;
	  width: 100%;
	  background: #fff;
	  padding: 30px 50px;
	  padding-top: 80px;
	  margin-top: 30px;
	  border-radius: 10px;
	  z-index:2;
	}
	
	
	.company-logo {
		position:absolute;
		top:0;
		left:50px;
		transform:translateY(-50%);
		width: 100px;
		height: 100px;
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
	
	.employee-status-box {
		width:220px;
		text-align: center;
		display: flex;
		flex-direction: column;
		justify-content: flex-end; /* 하단 정렬 */
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

<body class="n_inner">
	<p class="h1 mb-5 fw-bold mw-1260 m-auto">기업 리뷰 상세</p>


	<div id="companyInfoBox" class="company-info-box">
		<div class="company-info-left mw-1260 m-auto">
			<img id="companyLogo" class="company-logo" src="" alt="기업 로고">
			<div>
				<h3 id="companyName" class="company-name"></h3>
				<p class="company-info fs-14 text-muted d-flex gap-3">
					<span id="comIndu"></span>
					<span id="comSize"></span>
					<span id="comMem"></span>
					<span id="ceoName"></span>
				</p>
			</div>
		</div>
	</div>
	
	<div class="mw-1260 m-auto">
		<!-- 상단 탭 -->
		<div class="tabs">
			<button class="tab-btn active" data-tab="overall">전체</button>
	
			<button class="tab-btn" data-tab="topjob">직무별</button>
		</div>
	
	
	
		<div id="tab-overall" class="tab-content active">
			<p class="h3">전체 평균</p>
			<div class="d-flex align-items-center gap-4 mb-5 chart-sec-wrap">
				<!-- 왼쪽 차트 영역 -->
				<div class="chart-sec-lt chart-sec-col">
					<div class="d-flex flex-column align-items-center gap-4">
						<canvas id="overallAvgChart"></canvas>
						<div class="text-center d-flex gap-3">
							<p class="fs-6 fw-semibold text-muted">
								평균 점수 <span id="overallAvgText" class="fw-bold fs-5 text-violet90"></span>점
							</p>
							<p class="fs-6 fw-semibold text-muted">
								총 <span id="reviewCount" class="count fs-5 text-violet90"></span>개 리뷰 등록
							</p>
						</div>
					</div>
				</div>	
				<!-- 오른쪽 차트 영역 -->
				<div class="chart-sec-rt chart-sec-col d-flex justify-content-center">
					<div class="employee-status-box">
						<canvas id="employeeStatusChart"></canvas>
						<div class="d-flex justify-content-between fs-14 employee-text-box">
							<div>
								<div class="fw-semibold fs-16">현직원</div>
								<div class="text-violet90 fw-semibold" id="workingPercent">0%</div>
							</div>
							<div>
								<div class="fw-semibold fs-16">전직원</div>
								<div class="text-violet90 fw-semibold" id="notWorkingPercent">0%</div>
							</div>
						</div>
						<p class="fs-6 fw-semibold mt-4" id="totalRespondentsText">총 0명의 답변입니다.</p>
					</div>
				</div>
			</div>
			
			<p class="h3 mb-4">질문별 평균</p>
			<div class="chart-section">
				<div id="questionCharts" class="question-charts d-flex flex-wrap gap-3"></div>
			</div>
		</div>
	
	
	
	
		<div id="tab-topjob" class="tab-content">
			<div id="topJobButtons" class="filter-buttons  mb-4"></div>
			<h4 class="h3">직무 평균</h4>
			<div class="chart-section">
				<div class="d-flex flex-column align-items-center gap-4">
					<canvas id="topJobAvgChart"></canvas>
					
					<div class="text-center d-flex gap-3">
						<p class="fs-6 fw-semibold text-muted">
							평균 점수 <span id="topJobAvgText" class="fw-bold fs-5 text-violet90"></span>점
						</p>
						<p class="fs-6 fw-semibold text-muted">
							총 <span id="topJobCountInfo" class="count fw-bold fs-5 text-violet90"></span>개 직무 리뷰 등록
						</p>
					</div>
				</div>
			</div>
			<h3 class="h3 mb-4">질문별 평균</h3>
			<div class="chart-section">
				<div id="topJobQuestionCharts" class="question-charts d-flex flex-wrap gap-3"></div>
			</div>
		</div>
	</div>
</body>
</html>