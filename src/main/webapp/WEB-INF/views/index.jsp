<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<title>띹잡</title>
		<link rel="stylesheet" href="/dist/assets/css/main.css">
		<script defer src="/js/index.js"></script>
	</head>

	<body>
		<nav class="wing" style="top: 304px; bottom: auto;">
			<ul>
				<li><button value="main" class="_ga_quick on">추천공고</button></li>
				<li><button value="banner" class="_ga_quick">주요 채용소식</button></li>
				<li><button value="helper" class="_ga_quick">취업TOOL</button></li>
				<li><a href="javascript:void(0)" target="_blank" class="_ga_quick">고객센터</a></li>
			</ul>
			<button class="btn_top"><span class="_ga_top">TOP</span></button>
		</nav>

		<!-- 최상단 배너영역 -->
		<div id="banner-area">
			<div class="bnr-big">
				<div class="ad-container divAdBnnr_1_0">
					<a class="imgAdBnnr imgAdBnnr_1_0 isSendImpLog" href="javascript:void(0);">
						<img src="https://ads.jobkorea.co.kr/ads/pc/images/202506/4ad75f0d-d413-483e-8a20-ceaa2d93692d.png"
							alt="SK쉴더스 면접 기회 제공 지능형 애플리케이션 개발자 양성과정 4기 모집">
					</a>
				</div>
			</div>

			<div class="bnr-service">
				<!-- 하이테크 아티클 배너 -->
				<div class="swiper-container">
					<ul class="swiper-wrapper">
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">AI 트렌드 배송</p>
								<h3 class="title">오픈AI 국내 인재 채용</h3>
								<p class="text">불붙는 AI 인재 쟁탈전</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">인사이트</p>
								<h3 class="title">연봉, 나만 궁금해요?</h3>
								<p class="text">연봉 협상, 안 하면 손해?!</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">인사이트</p>
								<h3 class="title">좋은 회사 고르려면!</h3>
								<p class="text">이직 시 놓치면 안되는 팁 공개</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">취업 트렌드</p>
								<h3 class="title">면접질문 제대로 파악하려면</h3>
								<p class="text">질문 내용에 집중해 보세요!</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">AI아카이브</p>
								<h3 class="title">AI의 아첨을 피하는 방법</h3>
								<p class="text">AI의 아부에 취하지 않으려면</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">AI 트렌드 배송</p>
								<h3 class="title">오픈AI 국내 인재 채용</h3>
								<p class="text">불붙는 AI 인재 쟁탈전</p>
							</a>
						</li>
						<li class="swiper-slide">
							<a href="javascript:void(0)">
								<p class="subtitle">인사이트</p>
								<h3 class="title">연봉, 나만 궁금해요?</h3>
								<p class="text">연봉 협상, 안 하면 손해?!</p>
							</a>
						</li>
					</ul>
					<div class="btn-control">
						<div class="bnrs-prev" role="button"></div>
						<div class="bnrs-next" role="button"></div>
					</div>
				</div>
				<script>
					document.addEventListener("DOMContentLoaded", () => {
						const mainSec01 = new Swiper(".bnr-service .swiper-container", {
							spaceBetween: 0,
							centeredSlides: true,
							autoplay: {
								delay: 2500,
								disableOnInteraction: false,
							},
							navigation: {
								nextEl: ".bnrs-next",
								prevEl: ".bnrs-next"
							},
						});
					});
				</script>
			</div>

			<div class="bnr-marketing">
				<div class="ad-container divAdBnnr_3_0">
					<a class="imgAdBnnr imgAdBnnr_3_0 isSendImpLog" href="javascript:void(0);">
						<img src="https://ads.jobkorea.co.kr/ads/pc/images/202506/8953018d-9824-47bf-87c9-038a9dd747ee.png"
							alt="AI 서비스기획 9기">
					</a>
				</div>
			</div>

		</div>
		<!-- 최상단 배너영역 -->


		<section id="section_banner" class="main_product">
			<h2 id="h-primary-products" class="blind">주요채용</h2>
			<div class="cont_product prd_platinum">
				<div class="inner_cont">

					<!-- 띠배너 영역 - 여수, 광양 어쩌구 -->
					<div class="cont_banner mb-4" data-aos="fade-up" data-aos-duration="800">
						<img src="/dist/assets/images/lineBnr.png" alt="nestads_ads_iframe_container" class="nest-standby-banner">
					</div>
					<!-- 띠배너 영역 - 여수, 광양 어쩌구 -->

					<div class="z-index3" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1000">
						<div class="top_poduct">
							<strong class="tit_cont mb-3" id="product_platinum">회원님이 꼭 봐야 할 공고 (프리미엄)</strong>
							<!--
						버튼 클릭 시 상품 안내 팝업 뜨게 제작하기
						<button class="btn_product">
							<span class="track_event">
								<span>아이콘 영역</span>
								상품안내
							</span>
						</button>
					 -->
						</div>

						<div id="MainSuper">
							<!-- 공고 리스트시작 >> 회원님이 꼭 봐야 할 공고 (플래티넘) -->
							<!--
							첫번쨰 li는 열려있는 상태 >> 스타일 달라서 반복문얘는 빼고 돌려야함 !!
							플래티넘 공고영역은 채용공고가 최소 8개 이상이어야 레이아웃이 깨지지 않습니다.
						-->
							<ul class="goodsBox observe-job"></ul>

						</div>
					</div>
				</div>
			</div>


			<!-- 
			채용공고 두번째 ui 스타일 (border 적용) - ul태그에 plus가 있냐 없냐로 boader가 붙습니다.
			hover 시 공고가 펼쳐지는 효과는 li 태그에 클래스 option가 있냐 없냐로 나뉘어 집니다.
			처음 2개 li는 hover 효과가 있습니다. 3,4번쨰 li는 마우스 올려도 펼쳐지는 효과가 없습니다.
		-->
			<div class="cont_product prd_prime">
				<div class="inner_cont">
					<div class="z-index2" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1200">
						<div class="top_poduct mb-3">
							<strong class="tit_cont" id="product_prime">최고의 인기 공고 (비지니스)</strong>
							<!-- <button class="btn_product">
							<span class="track_event">
								<span>아이콘영역</span>
								상품안내
							</span>
						</button> -->
						</div>
						<ul class="prd_list plus" id="_primePlus"></ul>
					</div>


					<div class="z-index1" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1400">
						<div class="top_poduct mb-3">
							<strong class="tit_cont" id="product_prime">띹잡 추천 공고 (베이직)</strong>
							<!-- <button class="btn_product">
							<span class="track_event">
								<span>아이콘영역</span>
								상품안내
							</span>
						</button> -->
						</div>
						<!-- 
						채용공고 마지막 ui 스타일 
						hover 시 공고가 펼쳐지는 효과는 li 태그에 클래스 option가 있냐 없냐로 나뉘어 집니다.
						처음 2개 li는 hover 효과가 있습니다. 3,4번쨰 li는 마우스 올려도 펼쳐지는 효과가 없습니다.
					-->
						<ul class="prd_list bottom_list" id="_prime"></ul>
					</div>
				</div>
			</div>


		</section>
		<!-- 
	취업툴안되면 다른걸로라도 여기 ui 살려보기 
	<section id="section_helper" class="cont_helper">
		<h2 id="h-section-helper" class="tit_cont">취업TOOL</h2>
		<ul class="list_helper">
			<li>
				<a href="void:javascript(0)" class="link_helper letter" title="글자수 세기">글자수<br>세기</a>
			</li>
			<li><a href="void:javascript(0)" class="link_helper ai_coaching" title="Ai자소서 코칭">Ai자소서<br>코칭</a>
			</li>
			<li><a href="void:javascript(0)" class="link_helper photo"
				title="사진크기 조정">사진크기<br>조정
			</a></li>
			<li><a href="void:javascript(0)" class="link_helper salary"
				title="연봉 계산기">연봉<br>계산기
			</a></li>
			<li><a href="void:javascript(0)" class="link_helper retire"
				title="퇴직금 계산기">퇴직금<br>계산기
			</a></li>
			<li><a href="void:javascript(0)"
				class="link_helper unemployment" title="실업급여 계산기">실업급여<br>계산기
			</a></li>
			<li><a href="void:javascript(0)" class="link_helper annual"
				title="연차 계산기">연차<br>계산기
			</a></li>
			<li><a href="void:javascript(0)" class="link_helper graduated"
				title="졸업연도 계산기">졸업연도<br>계산기
			</a></li>
			<li><a href="void:javascript(0)" class="link_helper grades"
				title="학점 변환기">학점<br>변환기
			</a></li>
		</ul>
	</section>
	-->
	</body>

	</html>