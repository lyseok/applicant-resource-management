<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<title>채용공고 리스트</title>
<link rel="stylesheet" href="/dist/assets/css/search_panel.css">
</head>
<body>


	<%@ include file="/WEB-INF/fragments/searchBar.jsp"%>
	<div id="sri_section" class="layout_wide  has_banner">
		<div id="sri_wrap">
			<div id="content">

				<h1>
					<div class="newcomer_sub_title">실시간 공고</div>
				</h1>

				<!-- 상단 공고 slide -->
				<div class="newcomer_sub_top">
					<p class="curation_tit type1">신입은 필수! 놓치면 손해보는 공고</p>
					<div class="wrap_sub_curation priority_top_list">
						<div class="sub_curation_swiper swiper-container-horizontal">
							<div class="slide_btn_wrap">
								<button type="button" class="slide_btn_type3 prev swiper-prev ga_data_layer swiper-button-disabled">
									<span class="blind">이전</span>
								</button>
								<button type="button" class="slide_btn_type3 next swiper-next ga_data_layer">
									<span class="blind">다음</span>
								</button>
							</div>
							<ul class="sub_curation_list swiper-wrapper">
							
							
							
							
								<!-- 신입은 필수! 놓치면 손해보는 공고 >> 반복문 돌릴영역! 슬라이더 돌리려면 6개 이상 있어야함! -->
								<li class="swiper-slide swiper-slide-visible swiper-slide-active" style="margin-right: 15px;">
									<a href="javascript:void(0)" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-17</span>
												<span class="tag theme major">경기 시흥시</span>
											</div>
											<span class="company_name">(주)코스리거</span>
											<span class="announcement">(주)코스리거 부문별 신입/경력 정규 채용</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>
								<!-- // end 반복문 돌릴영역! -->
								
								
								
								<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
									<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-11</span>
												<span class="tag theme major">지역 넣어주라!</span>
											</div>
											<span class="company_name">회사명</span>
											<span class="announcement">채용공고 제목</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>
								<li class="swiper-slide swiper-slide-visible" style="margin-right: 15px;">
									<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-11</span>
												<span class="tag theme major">지역 넣어주라!</span>
											</div>
											<span class="company_name">회사명</span>
											<span class="announcement">채용공고 제목</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>
								<li class="swiper-slide swiper-slide-visible" style="margin-right: 15px;">
									<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-11</span>
												<span class="tag theme major">지역 넣어주라!</span>
											</div>
											<span class="company_name">회사명</span>
											<span class="announcement">채용공고 제목</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>								
								<li class="swiper-slide swiper-slide-visible" style="margin-right: 15px;">
									<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-11</span>
												<span class="tag theme major">지역 넣어주라!</span>
											</div>
											<span class="company_name">회사명</span>
											<span class="announcement">채용공고 제목</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>
								<li class="swiper-slide swiper-slide-visible" style="margin-right: 15px;">
									<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
										<div class="sub_curation_slide">
											<div class="tag_line">
												<span class="tag d-day">D-11</span>
												<span class="tag theme major">지역 넣어주라!</span>
											</div>
											<span class="company_name">회사명</span>
											<span class="announcement">채용공고 제목</span>
										</div>
									</a>
									<button type="button" class="newcomer_book_mark like_btn">
										<span class="blind">북마크</span>
									</button>
								</li>
							</ul>
							<span class="swiper-notification" aria-live="assertive"
								aria-atomic="true"></span><span class="swiper-notification"
								aria-live="assertive" aria-atomic="true"></span>
						</div>
					</div>
					<script>
						document.addEventListener('DOMContentLoaded', () => {
						  // 1) .priority_top_list 컨테이너 선택
						  const target = document.querySelector('.priority_top_list');
						  if (!target) return;  // 요소 없으면 종료
						
						  // 2) 모든 링크·버튼 요소 수집
						  const subCurationLinks = Array.from(
						    target.querySelectorAll(
						      '.sub_curation_swiper .swiper-slide a, .sub_curation_swiper .swiper-slide button'
						    )
						  );
						
						  // 3) Swiper 컨테이너 요소
						  const swiperContainer = target.querySelector('.sub_curation_swiper');
						  if (!swiperContainer) return;
						
						  // 4) Swiper 인스턴스 생성
						  const swiper = new Swiper(swiperContainer, {
						    slidesPerGroup: 5,
						    slidesPerView: 5,
						    spaceBetween: 15,
						    watchSlidesVisibility: true,
						    navigation: {
						      nextEl: target.querySelector('.sub_curation_swiper .swiper-next'),
						      prevEl: target.querySelector('.sub_curation_swiper .swiper-prev'),
						    },
						    width: 1260,
						    on: {
						      slideChange: () => {
						        // a) 모든 링크·버튼 tabindex="-1"
						        subCurationLinks.forEach(el => el.setAttribute('tabindex', '-1'));
						
						        // b) 보이는 슬라이드 안 요소만 tabindex="0"
						        const visibleSlides = target.querySelectorAll(
						          '.sub_curation_swiper .swiper-slide-visible'
						        );
						        visibleSlides.forEach(slide => {
						          slide.querySelectorAll('a, button')
						               .forEach(el => el.setAttribute('tabindex', '0'));
						        });
						      }
						    },
						  });
						});
					</script>
				</div>
				<!-- // end 상단 공고 slide -->



				<!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->
				<div class="common_recruilt_list">
					<div class="area_title list_total_count">
						<h2>전체 채용정보</h2>
						<span class="total_count"><em>5,891</em>건</span>
					</div>
					<!-- 필터링 영역 start -->
					<div class="list_info">
						<div>
							<div class="InpBox Line rewards">
								<span class="Chk">
									<input type="checkbox" id="filter_ai_head_hunting" value="y">
									<label class="Lbl" for="filter_ai_head_hunting">취업축하금만 보기</label>
								</span>
							</div>
							<div class="InpBox Line">
								<span class="Chk">
									<input type="checkbox" id="show_applied_recruit" value="y">
									<label class="Lbl" for="show_applied_recruit">지원한 공고 표기</label>
								</span>
							</div>
							<div class="InpBox Line">
								<span class="Chk">
									<input type="checkbox" id="filter_quick_apply" value="y">
									<label class="Lbl" for="filter_quick_apply">입사지원만 보기</label>
								</span>
							</div>
							<div class="InpBox Line">
								<span class="Chk">
									<input type="checkbox" id="chk_real_selfintro_fl" value="y">
									<label class="Lbl" for="chk_real_selfintro_fl">자소서 공고만 보기</label>
								</span>
							</div>
						</div>
						<div class="list_select">
							<div class="InpBox">
								<select name="page_count" id="page_count">
									<option value="20" selected="">20개씩</option>
									<option value="30">30개씩</option>
									<option value="50">50개씩</option>
									<option value="100">100개씩</option>
								</select>
							</div>
							<div class="InpBox">
								<select name="sort" id="sort">
									<option value="RL">추천순</option>
									<option value="RD" selected="">최신순</option>
									<option value="MD">수정순</option>
									<option value="EA">마감순</option>
									<option value="AD">지원순</option>
									<option value="RE">조회순</option>
								</select>
							</div>
						</div>
					</div>
					<!-- // 필터링 영역 End  -->
					
					
					<div id="default_list_wrap" style="position: relative">
						<section class="list_recruiting">
							<h2 class="blind">공고리스트</h2>
							
							<!-- list 영역 -->
							<div class="list_body">

								<!-- 채용정보 1줄 영역 - 클래스 list_item가 한세트임 -->
								<div id="rec-51067909" class="list_item">
									<div class="box_item">
										<div class="col company_nm">
											<a href="javascript:void(0)" class="str_tit" target="_blank"> (주)한창이지엠 </a>
											<button type="button" title="관심기업 등록" class="interested_corp">
												<span>관심기업 등록</span>
											</button>
										</div>
										<div class="col notification_info">
											<div class="job_tit">
												<a class="str_tit " id="" title="'25년 한창이지엠 산업안전 관리자 채용" href="javascript:void(0)">
													<span>'25년 한창이지엠 산업안전 관리자 채용</span>
												</a>
												<button type="button" title="스크랩" class="spr_scrap btn_scrap off">
													<span class="blind">스크랩</span>
												</button>
											</div>
											<div class="job_meta">
												<span class="job_sector"> <span>안전보건관리자</span>
												</span>
											</div>
											<div class="job_badge">
												<span class="mActive"> 생산 스크랩 TOP100 </span>
											</div>
										</div>
										<div class="col recruit_info">
											<ul>
												<li><p class="work_place">충남 천안시 서북구 외</p></li>
												<li><p class="career">경력무관 · 정규직</p></li>
												<li><p class="education">대학교(4년)↑</p></li>
												<li><p class="salary">면접 후 협의</p></li>
											</ul>
										</div>
										<div class="col support_info">
											<button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다.">
												<span class="sri_btn_immediately">입사지원</span>
											</button>
											<p class="support_detail">
												<span class="date">~07.18(금)</span>
												<span class="deadlines">18시간 전 등록</span>
											</p>
										</div>
									</div>
									<div class="similar_recruit"></div>
								</div>
								<!-- // end 채용정보 1줄 영역 - 클래스 list_item가 한세트임 -->





								<!-- 채용정보 1줄 영역 - 클래스 list_item가 한세트임 -->
								<div id="rec-51067739" class="list_item">
									<div class="box_item">
										<div class="col company_nm">
											<span class="str_tit">㈜트리포스</span>
											<button type="button" title="관심기업 등록" class="interested_corp">
												<span>관심기업 등록</span>
											</button>
										</div>
										<div class="col notification_info">
											<div class="job_tit">
												<a class="str_tit" id="" title="백엔드, 프론트엔드 소프트웨어 개발자(신입)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067739">
													<span>백엔드, 프론트엔드 소프트웨어 개발자(신입)</span>
												</a>
												<button type="button" title="스크랩" class="spr_scrap btn_scrap off">
													<span class="blind">스크랩</span>
												</button>
											</div>
											<div class="job_meta">
												<span class="job_sector"> <span>앱개발</span><span>웹개발</span><span>Android</span><span>iOS</span><span>Java</span>
												</span>
											</div>
										</div>
										<div class="col recruit_info">
											<ul>
												<li><p class="work_place">경기 성남시 수정구</p></li>
												<li><p class="career">신입 · 정규직 외</p></li>
												<li><p class="education">대학교(4년)↑</p></li>
												<li><p class="salary">면접 후 협의</p></li>
											</ul>
										</div>
										<div class="col support_info">
											<button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다.">
												<span class="sri_btn_immediately">입사지원</span>
											</button>
											<p class="support_detail">
												<span class="date">채용시</span>
												<span class="deadlines">18시간 전 등록</span>
											</p>
										</div>
									</div>
									<div class="similar_recruit"></div>
								</div>
								<!-- // end 채용정보 1줄 영역 - 클래스 list_item가 한세트임 -->

							</div>
						</section>

						<!-- pager 영역 -->
						<div class="PageBox">
							<span class="BtnType SizeS active">1</span>
							<button class="BtnType SizeS page" data-page="2">2</button>
							<button class="BtnType SizeS page" data-page="3">3</button>
							<button class="BtnType SizeS page" data-page="4">4</button>
							<button class="BtnType SizeS page" data-page="5">5</button>
							<button class="BtnType SizeS page" data-page="6">6</button>
							<button class="BtnType SizeS page" data-page="7">7</button>
							<button class="BtnType SizeS page" data-page="8">8</button>
							<button class="BtnType SizeS page" data-page="9">9</button>
							<button class="BtnType SizeS page" data-page="10">10</button>
							<button data-page="11" class="BtnType SizeS BtnNext btnNext">다음</button>
						</div>
						<!-- // end pager 영역 -->
					</div>

				</div>
				<!-- // ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->





					<!-- 하단 인턴 슬라이드 영역 start -->
					<div class="newcomer_sub_bottom">
						<p class="curation_tit type7">신입·인턴 이걸로 끝! BEST 공고</p>
						<div class="wrap_sub_curation priority_rand_list">
							<div class="sub_curation_swiper swiper-container-horizontal">
								<div class="slide_btn_wrap">
									<button type="button" class="slide_btn_type3 prev swiper-prev ga_data_layer swiper-button-disabled">
										<span class="blind">이전</span>
									</button>
									<button type="button" class="slide_btn_type3 next swiper-next ga_data_layer">
										<span class="blind">다음</span>
									</button>
								</div>
								<ul class="sub_curation_list swiper-wrapper">
									
									<!-- 신입·인턴 이걸로 끝! BEST 공고 >> 반복문 돌릴영역! 슬라이더 돌리려면 6개 이상 있어야함! -->
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								
								
									
								
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								
									<li class="swiper-slide swiper-slide-visible swiper-slide-next" style="margin-right: 15px;">
										<a href="javascript:void(0)" target="_blank" class="newcomer_link_view ga_data_layer">
											<div class="sub_curation_slide">
												<div class="tag_line">
													<span class="tag d-day">D-11</span>
													<span class="tag theme major">지역 넣어주라!</span>
												</div>
												<span class="company_name">회사명</span>
												<span class="announcement">채용공고 제목</span>
											</div>
										</a>
										<button type="button" class="newcomer_book_mark like_btn">
											<span class="blind">북마크</span>
										</button>
									</li>
								</ul>
								<span class="swiper-notification" aria-live="assertive"
									aria-atomic="true"></span>
							</div>
						</div>

						<script type="text/javascript">
			        document.addEventListener('DOMContentLoaded', () => {
			            // 1) 타겟 컨테이너 선택
			            const target = document.querySelector('.priority_rand_list');
			            if (!target) return; // 안전 체크

			            // 2) 모든 링크·버튼 모아두기
			            const subCurationLinks = Array.from(
			              target.querySelectorAll(
			                '.sub_curation_swiper .swiper-slide a, .sub_curation_swiper .swiper-slide button'
			              )
			            );

			            // 3) Swiper 인스턴스 생성
			            const swiper = new Swiper('.sub_curation_swiper', {
			              slidesPerGroup: 5,
			              slidesPerView: 5,
			              spaceBetween: 15,
			              watchSlidesVisibility: true,
			              navigation: {
			                nextEl: target.querySelector('.sub_curation_swiper .swiper-next'),
			                prevEl: target.querySelector('.sub_curation_swiper .swiper-prev'),
			              },
			              width: 1260,
			              on: {
			                slideChange: () => {
			                  // 4) 모든 요소 tabindex="-1" 처리
			                  subCurationLinks.forEach(el =>
			                    el.setAttribute('tabindex', '-1')
			                  );
			                  // 5) 보이는 슬라이드 안의 링크·버튼만 tabindex="0" 처리
			                  const visibleSlides = target.querySelectorAll(
			                    '.sub_curation_swiper .swiper-slide-visible'
			                  );
			                  visibleSlides.forEach(slide => {
			                    slide
			                      .querySelectorAll('a, button')
			                      .forEach(el => el.setAttribute('tabindex', '0'));
			                  });
			                },
			              },
			            });
			          });
		        </script>
					</div>
					<!-- // end 하단 인턴 슬라이드 영역 -->
			</div>
		</div>
	</div>


</body>
</html>