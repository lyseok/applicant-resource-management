<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<title>회사 상세</title>
<link rel="stylesheet" href="/dist/assets/css/company/companyView.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0"></script>
<script src="/js/member/common/companyDetailView/companyDetailView.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">


<style type="text/css">
.sales-summary-box, .profit-summary-box {
	display: flex;
	justify-content: space-between;
	margin: 10px 0 20px;
	font-size: 14px;
	font-family: 'Noto Sans KR', sans-serif;
}

.summary-item {
	flex: 1;
	text-align: center;
}

.summary-title {
	color: #777;
	margin-bottom: 4px;
}

.summary-value {
	font-weight: bold;
	font-size: 16px;
}

.recruit-history .chart-doughnut-recruit-history {
	width: 100%;
	display: flex;
	justify-content: center; /* 가로 중앙 */
	align-items: center; /* 세로 중앙 */
	padding: 20px 0; /* 필요하면 위아래 여백 */
}

#recruit-title-main {
	color: var(--violet70);
	font-size: 15px;
}

table.table-in-progress-announcement tr.clickable-row:hover {
	background-color: hotpink !important;
}
/* 커서를 전체에 포인터로 */
.clickable-row {
	cursor: pointer;
}

/* 기본 클릭 가능한 행 */
.clickable-row {
	cursor: pointer;
	transition: background-color 0.2s ease;
}

/* 마우스 올리면 약간 밝아짐 */
.clickable-row:hover {
	background-color: #f5f5f5;
}

/* 클릭(마우스 누르는) 상태 */
.clickable-row:active {
	background-color: #e0e0e0;
}

/* JS 에서 선택된 행에 붙일 수 있는 클래스 */
.clickable-row.selected {
	background-color: rgba(0, 123, 255, 0.1);
}

.company-tabs {
	border-bottom: 1px solid #ddd;
}

.tabs {
	display: flex;
	list-style: none;
	margin: 0;
	padding: 0;
}

.tab {
	margin-right: 1rem;
}

.tab a {
	display: block;
	padding: 0.75rem 1rem;
	text-decoration: none;
	color: #333;
	position: relative;
}

.tab.active a {
	color: var(--violet70);
	border-bottom: 2px solid var(--violet70);
}

.count {
	font-size: 0.85em;
	background: #eef;
	color: var(--violet70);
	border-radius: 999px;
	padding: 0 0.4em;
	margin-left: 0.3em;
}

/* 필터바 */
.filter-bar {
	display: flex;
	flex-wrap: wrap;
	gap: 0.5rem;
	padding: 1rem 0;
	border-bottom: 1px solid #eee;
	overflow-x: auto;
}

/* 필터 버튼 기본 스타일 */
.filter-btn {
  white-space: nowrap;
  border: 1px solid var(--violet70);
  background: #fff;
  color: var(--violet70);
  padding: 0.4rem 0.8rem;
  border-radius: 999px;
  cursor: pointer;
  font-size: 0.9rem;
}

/* 활성화된(선택된) 버튼 */
.filter-btn.active {
  background: var(--violet70);
  color: #fff;
  border-color: var(--violet70);
}



/* 카드 리스트 */
.card-list {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
	gap: 1rem;
	margin-top: 1rem;
}

.job-card {
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 1rem;
	background: #fff;
}

.job-card-header {
	font-weight: bold;
	color: var(--violet70);
}

.job-card-title {
	margin: 0.5rem 0;
	font-size: 1rem;
}

.job-card-meta {
	font-size: 0.85rem;
	color: #666;
	margin-bottom: 0.5rem;
}

.job-card-tags {
	font-size: 0.85rem;
	color: #333;
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

/* 차트 카드 중앙 정렬 */
.salary-chart-card {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding-top: 50px;
}

.salary-chart-note {
	margin-top: .70rem;
	font-size: .95rem;
	color: #333;
	text-align: center;
}

.salary-company-name {
	font-size: 1.1rem;
	font-weight: 600;
	color: #222;
	margin-bottom: 0.2rem;
	text-align: center;
}

#btnOpenNaverMap {
	color: var(--violet70);
}

/* 합격자소서 탭 */
.essay-tabs {
	display: flex;
	border-bottom: 2px solid #ddd;
	margin-bottom: 20px;
}

.essay-tab {
	padding: 12px 20px;
	font-size: 16px;
	cursor: pointer;
	border: 1px solid #ddd;
	border-bottom: none;
	background: #f5f5f5;
	margin-right: 5px;
	border-radius: 5px 5px 0 0;
}

.essay-tab.active {
	background: #fff;
	border-bottom: 2px solid #fff;
	font-weight: bold;
}

.essay-filters {
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	margin-bottom: 25px;
}

.essay-filter-btn {
	padding: 8px 15px;
	border: 1px solid var(--violet60);
	border-radius: 20px;
	color: var(--violet60);
	background: #fff;
	cursor: pointer;
	font-size: 14px;
	transition: 0.2s;
}

.essay-filter-btn.active {
  background: var(--violet60);
  color: #fff;
  border-color: var(--violet60);
}

.essay-filter-btn:hover {
	background: #f0f0f0;
}

/* 자소서 카드 */
.essay-card {
	background: #fff;
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 20px;
	margin-bottom: 15px;
	transition: 0.2s;
}

.essay-card:hover {
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}

.essay-card-title {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 8px;
}

.essay-card-meta {
	font-size: 14px;
	color: #888;
	margin-bottom: 12px;
}

.essay-card-content {
	font-size: 15px;
	color: #333;
	line-height: 1.5;
}

.essay-views {
	font-size: 13px;
	color: #999;
	margin-top: 10px;
}

/* 상세 페이지 */
#essay-detail {
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
}

/* 뒤로가기 */
.essay-back-btn {
	display: inline-block;
	margin-bottom: 15px;
	background: var(--violet60);
	border: 1px solid #ddd;
	border-radius: 5px;
	padding: 8px 12px;
	cursor: pointer;
}

/* 제목 및 합격자 정보 */
.essay-detail-header {
	margin-bottom: 20px;
}

.essay-detail-title {
	font-size: 22px;
	font-weight: bold;
	margin-bottom: 8px;
}

.essay-detail-subinfo {
	font-size: 14px;
	color: #555;
	margin-bottom: 12px;
}

.essay-detail-meta {
	display: flex;
	justify-content: flex-end; /* 오른쪽 정렬 */
	align-items: center; /* 수직 중앙정렬 */
	gap: 10px; /* 요소 간격 */
	font-size: 13px;
	color: #999;
}

.essay-divider {
	color: #ccc;
}

.essay-views {
	display: flex;
	align-items: center;
	gap: 4px;
}

.essay-views .material-symbols-outlined {
	font-size: 16px;
	line-height: 1;
	color: #999;
}

hr {
	border: none;
	border-top: 1px solid #eee;
	margin: 20px 0;
}

/* 자소서 질문/답변 */
.essay-questions {
	border: 1px solid #ddd;
	padding: 15px;
	margin-bottom: 20px;
	background: #fafafa;
}

.essay-questions p {
	margin: 8px 0;
	font-weight: bold;
}

.essay-answer {
	margin-bottom: 30px;
}

.essay-answer h4 {
	font-size: 18px;
	margin-bottom: 10px;
	color: #ff6600;
}

.essay-answer p {
	font-size: 15px;
	color: #333;
	line-height: 1.6;
}

.accordion {
	border: 1px solid #ddd;
	border-radius: 8px;
	overflow: hidden;
}

.accordion-item {
	border-bottom: 1px solid #eee;
}

.accordion-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 15px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	background: #fafafa;
	transition: background 0.3s;
}

.accordion-header:hover {
	background: #f0f0f0;
}

.q-number {
	color: var(--violet70);
	margin-right: 8px;
}

.arrow {
	display: inline-block;
	width: 12px;
	height: 12px;
	border-right: 2px solid #666;
	border-bottom: 2px solid #666;
	transform: rotate(45deg);
	transition: transform 0.3s ease;
	margin-left: 10px;
}

.accordion-header.active .arrow {
	transform: rotate(225deg);
}


#essayCount{
	color: var(--violet70);
}

.button-dibs {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    cursor: pointer;
    background: none;
    border: none;
}



/* 기본 하트 */
.button-dibs::before {
    content: "♡";
    font-size: 26px;
    color: #ccc;
    transition: color 0.2s ease;
}

/* 채워진 하트 */
.button-dibs.on::before {
    content: "♥";
    color: #e63946;
}

.company-header-branding .add-ons .button-dibs {
    padding: 8px !important; /* 정중앙 오도록 조정 */
    width: 40px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
}


</style>
</head>
<body>
	<div class="company-header n_inner">
		<div class="company-header-container">
			<div class="company-header-branding background-type1">
				<!-- ↓ 여기 이미지 삽입하세요~~~ -->
				<img alt="company_bg" id="company_bg">
				<div class="company-header-branding-container ">
					<input type="hidden" id="mId" value=""> <input
						type="hidden" id="cId" value=""> <input type="hidden"
						id="jkcId" value="kari042"> <input type="hidden"
						id="devPath" value="https://www.jobkorea.co.kr">
					<div class="logo">
						<a href="/company/1899862"><img id="logoImg" class="width"
							style="left: 9px; margin: -21px 0 0 0"></a>
						<!-- 로고이미지 -->
					</div>


					<div class="company-header-branding-body">

						<div id="head-com-name" class="name"></div>
						<div class="summary">
							<div id="head-com-indu" class="summary-item"></div>
						</div>
					</div>
					<div class="add-ons">
						<div class="dibs">
							<a class="button button-dibs " memtype="C" data-mem-sys="366630"
								href="javascript:void(0);"> 
							</a>
						</div>

						<div class="home">
							<a href="" class="button button-home"
								target="_blank" id = "com_url"></a>
						</div>

					</div>
				</div>
			</div>


			<div class="company-nav" role="navigation">
				<div class="company-nav-container">
					<a href="#" class="company-nav-item active" data-tab="info">
						<div class="name">기업정보</div>
					</a> <a href="#" class="company-nav-item" data-tab="recruit">
						<div class="name">채용</div>
					</a> <a href="#" class="company-nav-item" data-tab="salary"><div
							class="name">연봉정보</div></a> <a href="#" class="company-nav-item"
						data-tab="essay"><div class="name">합격자소서</div></a>
				</div>
			</div>
		</div>
	</div>



	<div class="company-body mt-4" id="company-body">
		<div class="company-body-infomation inner">
			<!-- 기업정보 탭 -->
			<div class="tab-content" data-section="info">
				<!--기업 사진/동영상 영역-->
				<div class="company-infomation-row corporate-gallery">
					<h2 class="header skip">기업 사진</h2>
					<div
						class="company-infomation-container corporate-gallery-container">
						<div class="company-gallery-viewer screen-out" aria-hidden="true">
							<button type="button" class="button button-close">닫기</button>
							<div id="gallery-tab-panel" class="panel" role="tabpanel"
								aria-hidden="true" aria-labelledby="gallery-tab">
								<div class="company-gallery-viewer-pagination">
									<span class="current">1</span>/<span class="total">4</span>
								</div>
								<div class="company-gallery-viewer-container">
									<ul class="company-gallery-viewer-list" style="width: 3200px"
										data-end-margin="-2400">
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img style="width: 515px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">KSR-Ⅲ 발사</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img style="width: 462px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">아음속풍동</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img style="width: 490px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">기체구조시험</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img style="width: 456px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">기체구조시험동</div>
											</div>
										</li>
									</ul>
								</div>
								<button type="button" class="button button-previous">이전</button>
								<button type="button" class="button button-next">다음</button>
							</div>
						</div>
						<div class="company-gallery-container">
							<ul class="company-gallery-list" id="companyGalleryList"></ul>
						</div>
					</div>
				</div>
				<!--기본정보 영역-->


				<div class="company-infomation-row basic-infomation">
					<h2 class="header">기업정보</h2>
					<div
						class="company-infomation-container basic-infomation-container">

						<table class="table-basic-infomation-primary"
							summary="해당 기업의 산업, 사원수, 기업구분, 설립일, 자본금, 매출액, 대표자, 주요사업, 4대보험, 주소, 홈페이지 등의 정보">
							<caption class="table-caption">기업 상세 정보</caption>
							<colgroup>
								<col class="col-label">
								<col class="col-value">
								<col class="col-label">
								<col class="col-value">
							</colgroup>
							<tbody>
								<tr class="field">
									<th class="field-label">산업</th>
									<td class="field-value">
										<div class="value-container">
											<div id="industryType" class="value"></div>
										</div>
									</td>

									<th class="field-label">사원수</th>
									<td class="field-value">
										<div class="value-container">
											<div class="values">
												<div id="comMem" class="value"></div>
												<div class="reference"></div>
											</div>
										</div>
									</td>
								</tr>
								<tr class="field">

									<th class="field-label">기업구분</th>
									<td class="field-value">
										<div class="value-container">
											<div id="comType" class="value"></div>
										</div>
									</td>

									<th class="field-label">설립일</th>
									<td class="field-value">
										<div class="value-container">
											<div class="values">
												<div id="comCreateYear" class="value"></div>
												<div class="reference"></div>
											</div>
										</div>
									</td>
								</tr>
								<tr class="field">

									<th class="field-label">자본금</th>
									<td class="field-value">
										<div class="value-container">
											<div class="values">

												<div id="comCapital" class="value"></div>
												<div class="reference"></div>
											</div>
										</div>
									</td>

									<th class="field-label">매출액</th>
									<td class="field-value">
										<div class="value-container">
											<div class="values">
												<div id="comSales" class="value"></div>
												<div class="reference"></div>
											</div>
										</div>
									</td>
								</tr>
								<tr class="field">

									<th class="field-label">대표자</th>
									<td class="field-value">
										<div class="value-container">
											<div id="ceoName" class="value"></div>
										</div>
									</td>

									<th class="field-label">평균연봉</th>
									<td class="field-value">
										<div class="value-container">
											<div class="values">
												<div class="salary-average">
													<div id="salary-avg" class="salary-average-item"></div>
												</div>
												<a href="/company/1899862/salary"
													class="button button-view dev_new_pop"></a>
											</div>
										</div>
									</td>
								</tr>
								<tr class="field">

									<th class="field-label">주요사업</th>
									<td class="field-value">
										<div class="value-container">
											<div id="comMainBiz" class="value"></div>
										</div>
									</td>

									<th class="field-label">4대보험</th>
									<td class="field-value">
										<div class="value-container">
											<div id="insuranceYn" class="value"></div>
										</div>
									</td>
								</tr>
								<tr class="field">

									<th class="field-label">홈페이지</th>
									<td class="field-value">
										<div class="value-container">
											<div id="comUrl" class="value"></div>
										</div>
									</td>

									<th class="field-label">주소</th>
									<td class="field-value" colspan="3">
										<div class="value-container">
											<div id="comAddr" class="value"></div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<!--재무분석 영역-->



				<div class="company-infomation-row financial-analysis">
					<h2 class="header">재무분석</h2>

					<div
						class="company-infomation-container financial-analysis-container">
						<div class="financial-analysis-card-wrap">
							<!--사업분석-->
							<!--매출액-->
							<div class="financial-analysis-card">
								<div class="headers">
									<h3 class="header">매출액</h3>
								</div>
								<div class="sales-summary-box" id="salesHeaderSummary">
									<div class="summary-item">
										<div class="summary-title">최근 매출액</div>
										<div class="summary-value" id="totalSalesAmount">-</div>
									</div>
									<div class="summary-item">
										<div class="summary-title">작년 대비</div>
										<div class="summary-value" id="salesYoY">-</div>
									</div>
									<div class="summary-item">
										<div class="summary-title">업계 평균 대비</div>
										<div class="summary-value" id="salesVsAvg">-</div>
									</div>
								</div>

								<canvas id="salesChart" width="540" height="300"></canvas>
								<div class="update" id="salesUpdate"></div>
							</div>


							<!--영업이익-->



							<div class="financial-analysis-card">
								<div class="headers">
									<h3 class="header">영업이익</h3>
								</div>
								<div class="profit-summary-box" id="profitHeaderSummary">
									<div class="summary-item">
										<div class="summary-title">최근 영업이익</div>
										<div class="summary-value" id="totalOperatingProfit">-</div>
									</div>
									<div class="summary-item">
										<div class="summary-title">작년 대비</div>
										<div class="summary-value" id="profitYoY">-</div>
									</div>
									<div class="summary-item">
										<div class="summary-title">업계 평균 대비</div>
										<div class="summary-value" id="profitVsAvg">-</div>
									</div>
								</div>

								<canvas id="profitChart" width="540" height="300"></canvas>
								<div class="update" id="profitUpdate"></div>
							</div>



							<!--당기순이익-->


							<!--산업 내 위치-->
							<!--기업등급-->



							<!--총자산 증가율-->
							<!--동종업계 순위-->



						</div>
					</div>
				</div>
				<div class="ad ad-horizontal">


					<script type="text/javascript">
						// AD 배너 이미지 오류
						function adBnnrImageOnError_17(targetBnnr,
								pageTypeCode, bnnrIdx) {
							var imgTag = ".imgAdBnnr_" + pageTypeCode + "_"
									+ bnnrIdx;
							var divTag = ".divAdBnnr_" + pageTypeCode + "_"
									+ bnnrIdx;

							var defImgUrl = "";
							var defImgLink = "https://www.jobkorea.co.kr/service/company/ad";
							var defImgWidth = 0;
							var defImgHeight = 0;

							if (defImgUrl != "") {
								var img = new Image();
								img.addEventListener('error', function(e) {
									$(imgTag).removeClass("imgAdBnnr");
									$(divTag).css("display", "none");
								});
								img.addEventListener('load', function() {
									targetBnnr.src = defImgUrl;

									$(imgTag).addClass("IsDefault");
									$(imgTag).attr("target", "_blank");
									$(imgTag).attr("href", defImgLink);

									$(divTag).css("width", defImgWidth);
									$(divTag).css("height", defImgHeight);
								});
								img.src = defImgUrl;
							} else {
								$(divTag).css("display", "none");
							}
						}
					</script>
					<div class="ad-container divAdBnnr_17_0"
						style="width: 1040px; height: 80px; background-color: #ffffff;">
						<a class="imgAdBnnr imgAdBnnr_17_0" href="javascript:;"
							data-campaignid="189" data-campaignproductid="673"
							data-space="corpInfoMiddle" data-urlpath="/company/1899862"
							data-linkurl="https://forms.gle/vHVS3SpxVYHPvspd7"
							data-linktype="NEW_WINDOW" data-gender="" data-age="0"
							data-pagetypecode="17"> <img
							src="https://ads.jobkorea.co.kr/ads/pc/{service}/202505/572ff5ec-3d13-4866-bd5b-0a4ae0ada07a.png"
							alt="잠재성장청년 전용관" onerror="adBnnrImageOnError_17(this, 17, 0)">
						</a>
					</div>

				</div>
				<!--기업분석 / 1000대기업만 노출-->
				<!--기업분석 탭전환 작업 (기획자:안지현)-->






				<div class="detail">
					<p class="txt">
						더 상세한 기업정보를 원하시나요?<a
							href="https://www.nicebizinfo.com/ep/EP0100M002GE.nice?kiscode=123208&amp;siteid=JOBKR"
							target="_blank">상세기업정보 확인하기(유료)</a>
					</p>
					<p class="nice-txt">Nice 평가정보</p>
				</div>
				<!--고용현황-->

				<div class="company-infomation-row employment-status">
					<h2 class="header">고용현황</h2>
					<div
						class="company-infomation-container employment-status-container">

						<div class="employment-status-card-wrap  ">
							<div class="employment-status-card recruit-history">
								<div class="headers">
									<h3 class="header">채용 History</h3>
								</div>
								<div class="recruit-history-description-container">
									<div class="recruit-history-description">
										<div class="total"></div>
										<div class="of"></div>
									</div>
								</div>
								<div class="chart chart-doughnut-recruit-history">
									<canvas id="recruitTypeChart" width="400" height="200"></canvas>
								</div>
								<div class="benchmark">최근 3년 기준</div>
							</div>


							<div class="employment-status-card number-of-employees">
								<div class="headers">
									<h3 class="header">많이 모집한 직무</h3>
								</div>
								<div class="chart-bar-wrap">
									<canvas id="topJobChart" width="400" height="230"></canvas>
								</div>
								<div class="benchmark">최근 1년 기준</div>
							</div>

							<div
								class="employment-status-card employment-status-card-in-progress">
								<div class="headers">
									<h3 class="header">채용공고</h3>
								</div>
								<table class="table table-in-progress-announcement">
									<caption>
										<span class="skip"></span>
									</caption>
									<thead>
										<tr>
											<th scope="col" class="th-term">접수기간</th>
											<th scope="col" class="th-title">채용제목</th>
											<th scope="col" class="th-division">구분</th>
										</tr>
									</thead>
									<tbody id="inProgressTbody"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--기업소개-->


				<div class="company-infomation-row working-environment">
					<h2 class="header">기업소개</h2>

					<!--기업소개-->
					<div
						class="company-infomation-container working-environment-container working-environment-introduce fixed-height">
						<h3 class="header">기업소개</h3>
						<div class="introduce-body">
							<p id="devsummary"></p>
							<div align="justify">
								<div id="comInfo" align="justify"></div>
							</div>
							<p></p>
						</div>

					</div>

					<!--직무 인터뷰: Company일때만 노출-->

					<!--복리후생-->


					<!--기업위치 //jquery는 최대한 브라우저 상단에 넣는 것이 좋음. 다시 만나면, 뒤집어 쓰게 된다-->
					<div
						class="company-infomation-container working-environment-container working-environment-map">
						<h3 class="header">기업위치</h3>

						<div id="bottom-address" class="address">
							<span id="companyAddr"></span>
							<button id="btnOpenNaverMap" type="button">지도보기</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 채용 탭 -->
			<div class="tab-content hidden" data-section="recruit">
				<div class="status-tabs company-tabs">
					<ul class="tabs">
						<li class="tab active" data-status="all">전체 <span
							class="count">0</span>
						</li>
						<li class="tab" data-status="open">진행중 <span class="count">0</span>
						</li>
						<li class="tab" data-status="closed">마감 <span class="count">0</span>
						</li>
					</ul>
				</div>

				<!-- 필터 바 -->
				<div class="filter-bar"></div>

				<!-- 카드 리스트 -->
				<div class="card-list"></div>
			</div>



			<!-- 연봉정보 탭 -->
			<div class="company-infomation-row tab-content hidden"
				data-section="salary">
				<h2 class="header">연봉 정보</h2>
				<div class="salary-summary-box">
					<div class="summary-item">
						<div id="salaryCompanyName" class="salary-company-name"></div>
						<div class="summary-value" id="salaryAvgGross">-- 만원</div>
						<div class="summary-label">전체 평균 연봉</div>
						<div class="summary-sub">(임원 제외)</div>
						<div class="summary-monthly" id="salaryAvgNet"></div>
					</div>
					<div class="summary-item">
						<div class="salary-company-name" id="salaryCompanyIndu" ></div>
						<div class="summary-value" id="salaryGrade"></div>
						<div class="summary-label"> 평균 연봉 순위 </div>
						<div class="summary-sub"></div>
						<div class="summary-monthly" id="salaryNewhireNet"></div>
					</div>
				</div>


				<h2 class="header">직급별 평균 연봉</h2>
				<div class="financial-analysis-card salary-chart-card">
					<canvas id="salaryChart" width="640" height="300"></canvas>
					<div class="salary-chart-note" id="salaryChartNote">직급별 평균 연봉</div>
				</div>
				<h2 class="header">직급별 최소 최대 연봉</h2>
				<div class="financial-analysis-card salary-chart-card">
					<canvas id="salaryRangeChart" width="640" height="300"></canvas>
					<div class="salary-chart-note">직급별 최소 | 최대 연봉</div>
				</div>
			</div>



				<!-- 상세 영역 -->

				<!-- 합격자소서 탭 -->
				<div class="tab-content hidden" data-section="essay">
					<!-- 목록 영역 -->
					<div id="essay-list">
						<div class="essay-tabs">
							<div class="essay-tab active">
								합격자소서 (<span id="essayCount">0</span>)
							</div>
						</div>
						<!-- 직무 필터 -->
						<div class="essay-filters" id="essayFilters">
							<div class="essay-filter-btn active" data-filter="all">직무전체</div>
							<!-- JS로 직무 필터 버튼 추가 -->
						</div>

						<!-- 자소서 카드 리스트 (JS 바인딩) -->
						<div id="essayCardList"></div>
					</div>

					<!-- 상세 영역 -->
					<div id="essay-detail" style="display: none;"></div>
				</div>

				<div class="bottom-banner review">
					<a href="/review/home"
						onclick="GA_Event('기업정보_PC', '기업하단배너', '기업리뷰작성하기'); BrazeCallPageInto('기업리뷰진입');"
						target="_blank"> <img
						src="//i.jobkorea.kr/content/images/company/review/banner2_1040x80.png"
						alt="취업&amp;이직 준비중이라면? 현직자가 알려주는 기업리뷰 체크는 필수!기업리뷰 바로가기">
					</a>
				</div>
				<!--하단-->



				<div class="license">
					<div class="license-description">
						띹잡 기업정보와 NICE평가정보 기업정보를 기반으로 기업 프로필을 제공합니다. 잘못된 정보는 신고해주시면 빠르게
						전달하여 수정 검토하겠습니다.<br>게시된 정보는 무단으로 수집 및 배포할 수 없습니다.
					</div>
					<div class="license-help">
						<div class="license-help-item header">자료수정 및 정정문의</div>
						<div class="license-help-item tel">띹잡 고객센터 (T. 1588-9350, E.
							help@ddit.co.kr)</div>
					</div>
				</div>
			</div>
</body>
</html>