<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<title>회사 상세</title>
<link rel="stylesheet" href="/dist/assets/css/company/companyView.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0"></script>
<script src="/js/member/common/companyDetailView/companyDetailView.js"></script>



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


#recruit-title-main{
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

.filter-btn {
	white-space: nowrap;
	border: 1px solid #ccc;
	background: #fff;
	padding: 0.4rem 0.8rem;
	border-radius: 999px;
	cursor: pointer;
	font-size: 0.9rem;
}

.filter-btn.active {
	background: var(--violet70);
	border-color: var(--violet70);
	color: #fff;
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
  padding-top : 50px;
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
</style>
</head>
<body>
	<div class="company-header n_inner">
		<div class="company-header-container">
			<div class="company-header-branding background-type1">
				<div class="company-header-branding-container ">
					<input type="hidden" id="mId" value=""> <input
						type="hidden" id="cId" value=""> <input type="hidden"
						id="jkcId" value="kari042"> <input type="hidden"
						id="devPath" value="https://www.jobkorea.co.kr">
					<div class="logo">
						<a href="/company/1899862"><img
							src="//imgs.jobkorea.co.kr/img1/_whitebg/200X80/Co_Logo/Logo/2008/3/2R12v007Pq_dW2M2J3829q0dvMlS5_8Qvq.jpg?v=202506270920&amp;hash=r&amp;serviceCode=CL"
							onerror="companylogoOnError('//imgs.jobkorea.co.kr/img1/_whitebg/200X80/Co_Logo/Logo/2008/3/2R12v007Pq_dW2M2J3829q0dvMlS5_8Qvq.jpg?v=202506270920&amp;hash=r&amp;serviceCode=CL' , this)"
							alt="(재)한국항공우주연구원 로고" class="width"
							style="left: 9px; margin: -21px 0 0 0"></a>
					</div>


					<div class="company-header-branding-body">
						<div class="special-feature">
							<div class="special-feature-item web-awards">
								<a href="javascript:void(0);" class="button button-tooltip">
									i-AWARDS 수상</a>
							</div>
						</div>
						<div id="head-com-name" class="name"></div>
						<div class="summary">
							<div id="head-com-indu" class="summary-item"></div>
						</div>
					</div>
					<div class="add-ons">
						<div class="dibs">
							<a class="button button-dibs " memtype="C" data-mem-sys="366630"
								href="javascript:void(0);"> <i class="icon"
								aria-hidden="true"></i>
								<div class="count" id="devFavCnt">2145</div>
							</a>
							<!-- [Dev] 툴팁 레이어 : 관심기업 off인 경우 노출 (클래스 open 추가/삭제) -->
							<div class="tooltip-box-wrap open">
								<p class="txt">
									관심기업 추가하고 <em>채용소식 받기</em>
								</p>
								<a href="javascript:void(0);" class="btn-tooltip-close"><span
									class="skip">닫기</span></a>
							</div>
						</div>
						<div class="share">
							<a href="javascript:void(0);" class="button-share"
								data-target=".layer-share" aria-haspopup="true">공유</a>
							<div class="layer-share hidden" aria-hidden="true">
								<a href="javascript:goFacebook('http://joburl.kr/Zo3pI')"
									class="button button-facebook">페이스북</a> <a
									href="javascript:void(0);" class="button button-bookmark"
									id="devBookMark">즐겨찾기</a> <a href="javascript:void(0);"
									class="button button-copy-url button-popup-component"
									data-target=".layer-url-copied">URL복사</a>
							</div>
						</div>

						<div class="home">
							<a href="http://www.kari.re.kr" class="button button-home"
								target="_blank"></a>
						</div>

					</div>
				</div>
			</div>


			<div class="company-nav" role="navigation">
				<div class="company-nav-container">
					<a href="#" class="company-nav-item active" data-tab="info">
					<div class="name">기업정보</div></a> 
					<a href="#" class="company-nav-item" data-tab="recruit">
					<div class="name">채용</div></a> 
					<a href="#" class="company-nav-item" data-tab="salary"><div class="name">연봉정보</div></a>
					<a href="#" class="company-nav-item" data-tab="essay"><div class="name">합격자소서</div></a>
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
					<h2 class="header skip">기업 사진/동영상</h2>
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
													<img
														src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg"
														data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg"
														alt="" class="thumbnail attatched"
														onerror="Company.Gallery.fileOnerror(this);"
														style="width: 515px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">KSR-Ⅲ 발사</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img
														src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg"
														data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg"
														alt="" class="thumbnail attatched"
														onerror="Company.Gallery.fileOnerror(this);"
														style="width: 462px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">아음속풍동</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img
														src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg"
														data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg"
														alt="" class="thumbnail attatched"
														onerror="Company.Gallery.fileOnerror(this);"
														style="width: 490px; height: auto;">
												</div>
											</div>
											<div class="header">
												<div class="header-inner">기체구조시험</div>
											</div>
										</li>
										<li class="company-gallery-viewer-item thumbnail js-lazy-img">
											<div class="js-lazy-img__inner display-table">
												<div class="cell">
													<img
														src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg"
														data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/0x390/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg"
														alt="" class="thumbnail attatched"
														onerror="Company.Gallery.fileOnerror(this);"
														style="width: 456px; height: auto;">
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
							<ul class="company-gallery-list">
								<li class="company-gallery-list-item thumbnail js-lazy-img"><a
									href="#company-gallery-viewer" data-category="undefined"
									data-index="1" class="js-lazy-img__inner"><img
										src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg"
										alt=""
										data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_1.jpg"
										class="attatched" onerror="Company.Gallery.fileOnerror(this);">
										<div class="mask">
											<div class="mask-header">KSR-Ⅲ 발사</div>
										</div> </a></li>
								<li class="company-gallery-list-item thumbnail js-lazy-img"><a
									href="#company-gallery-viewer" data-category="undefined"
									data-index="2" class="js-lazy-img__inner"><img
										src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg"
										alt=""
										data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_4.jpg"
										class="attatched" onerror="Company.Gallery.fileOnerror(this);">
										<div class="mask">
											<div class="mask-header">아음속풍동</div>
										</div> </a></li>
								<li class="company-gallery-list-item thumbnail js-lazy-img"><a
									href="#company-gallery-viewer" data-category="undefined"
									data-index="3" class="js-lazy-img__inner"><img
										src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg"
										alt=""
										data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_3.jpg"
										class="attatched" onerror="Company.Gallery.fileOnerror(this);">
										<div class="mask">
											<div class="mask-header">기체구조시험</div>
										</div> </a></li>
								<li class="company-gallery-list-item thumbnail js-lazy-img"><a
									href="#company-gallery-viewer" data-category="undefined"
									data-index="4" class="js-lazy-img__inner"><img
										src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg"
										alt=""
										data-lg-src="//imgs.jobkorea.co.kr/img3/_thumb/300x0/Company/Visual_Co/images/2007/10/JK_CO_kari042_2.jpg"
										class="attatched" onerror="Company.Gallery.fileOnerror(this);">
										<div class="mask">
											<div class="mask-header">기체구조시험동</div>
										</div> </a></li>
							</ul>
							<!-- [Dev]
	            <div class="mtcBtnB file-count"><span class=""></span></div>  -->
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
												<div class="value"></div>
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
													<div class="salary-average-item"></div>
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
					<a href="javascript:void(0);"
						class="button button-view-financial-status">재무현황 전체보기</a>

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


							<div
								class="financial-analysis-card financial-analysis-card-ranking full-width">
								<div class="headers" id="devRankIndustry">
									<h3 class="header">동종업계 순위</h3>
									<button type="button" class="button button-view-ranking-more">동종업계
										더보기</button>
								</div>
								<div class="financial-analysis-card-container">
									<div class="ranking-peers">
										<div class="ranking">
											<div class="ranking-container">
												<div class="ranking-header">동종업계</div>
												<div class="ranking-value">
													<strong>2</strong>위
												</div>
											</div>
										</div>
									</div>
									<ol class="list list-ranking-peers">
										<li class="">
											<div class="order">1위</div>
											<div class="company">
												<a href="/company/1642064" target="_blank"> 한국산업기술기획평가원
												</a>
											</div>
										</li>
										<li class="current">
											<div class="order">2위</div>
											<div class="company">(재)한국항공우주연구원</div>
										</li>
										<li class="">
											<div class="order">3위</div>
											<div class="company">
												<a href="/company/44959175" target="_blank">
													지엠테크니컬센터코리아(주) </a>
											</div>
										</li>
										<li class="">
											<div class="order">4위</div>
											<div class="company">
												<a href="/company/1850794" target="_blank"> (재)한국원자력연구원
												</a>
											</div>
										</li>
										<li class="">
											<div class="order">5위</div>
											<div class="company">
												<a href="/company/1544107" target="_blank"> 한국생산기술연구원 </a>
											</div>
										</li>
									</ol>
									<ol class="list list-ranking-peers">
										<li class="">
											<div class="order">6위</div>
											<div class="company">
												<a href="/company/16152311" target="_blank"> 고려대학교산학협력단
												</a>
											</div>
										</li>
										<li class="">
											<div class="order">7위</div>
											<div class="company">
												<a href="/company/1830354" target="_blank">
													(재)한국에너지기술연구원 </a>
											</div>
										</li>
										<li class="">
											<div class="order">8위</div>
											<div class="company">
												<a href="/company/1969855" target="_blank"> (재)한국표준과학연구원
												</a>
											</div>
										</li>
										<li class="">
											<div class="order">9위</div>
											<div class="company">
												<a href="/company/45438928" target="_blank"> 전북대학교산학협력단
												</a>
											</div>
										</li>
										<li class="">
											<div class="order">10위</div>
											<div class="company">
												<a href="/company/1864249" target="_blank"> 한국과학기술정보연구원
												</a>
											</div>
										</li>
									</ol>
								</div>

								<div class="based-on-data">표준산업 분류 기준 [기타 공학 연구개발업]의 공시된
									재무정보(매출액)를 기준으로 평가된 순위입니다.</div>

								<div class="button button-view-reference-table">2019기준</div>

							</div>
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
									<canvas id="recruitTypeChart" width="300" height="200"></canvas>
								</div>
								<div class="benchmark">최근 3년 기준</div>
							</div>


							<div class="employment-status-card number-of-employees">
								<div class="headers">
									<h3 class="header">사원수</h3>
								</div>
								<div class="chart-bar-wrap">
									<div
										class="chart chart-bar chart-bar-number-of-employees chart-bar-plus">
										<div class="axios-y" aria-hidden="true">0</div>
										<div class="bar bar1">
											<div class="label-container">
												<div class="label">2002</div>
											</div>
											<div class="progress" style="height: 69px">
												<div class="value">317명</div>
											</div>
										</div>
										<div class="bar bar2">
											<div class="label-container">
												<div class="label">2003</div>
											</div>
											<div class="progress" style="height: 69px">
												<div class="value">317명</div>
											</div>
										</div>
										<div class="bar bar3">
											<div class="label-container">
												<div class="label">2004</div>
											</div>
											<div class="progress" style="height: 112px">
												<div class="value">518명</div>
											</div>
										</div>
										<div class="bar bar4 max">
											<div class="label-container">
												<div class="label">2007</div>
											</div>
											<div class="progress" style="height: 149px">
												<div class="value">690명</div>
											</div>
										</div>
									</div>
								</div>
								<div class="benchmark">최근 4년 기준</div>
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
			<div class="company-infomation-row tab-content hidden" data-section="salary">
				<h2 class="header">연봉 정보</h2>
				<div class="salary-summary-box">
					<div class="summary-item">
					 <div id="salaryCompanyName" class="salary-company-name">대덕인재개발원</div>
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



			<!-- 합격자소서 탭 -->
			<div class="tab-content hidden" data-section="essay">
				<h2>합격자소서</h2>
				<p>합격자소서 탭 전용 컨텐츠를 여기에 추가하세요.</p>
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
					띹잡 기업정보와 NICE평가정보 기업정보를 기반으로 기업 프로필을 제공합니다. 잘못된 정보는 신고해주시면 빠르게 전달하여
					수정 검토하겠습니다.<br>게시된 정보는 무단으로 수집 및 배포할 수 없습니다.
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