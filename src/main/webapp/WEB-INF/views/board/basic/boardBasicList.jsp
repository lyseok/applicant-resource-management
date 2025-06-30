<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<title>채용공고 리스트</title>
	<link rel="stylesheet" href="/dist/assets/css/search_panel.css" >
</head>
<body>

	
  <%@ include file="/WEB-INF/fragments/searchBar.jsp" %>
	<div id="sri_section" class="layout_wide  has_banner">
        <div id="sri_wrap">
            <div id="content">
                
				<h1>
				    <div class="newcomer_sub_title">실시간 공고</div>
				</h1>
				
				<!-- 상단 공고 slide -->
				<div class="newcomer_sub_top">
				    <p class="curation_tit type1">
				        신입은 필수! 놓치면 손해보는 공고</p>
				    <div class="wrap_sub_curation priority_top_list">
				        <div class="sub_curation_swiper swiper-container-horizontal">
				            <div class="slide_btn_wrap">
				                <button type="button" class="slide_btn_type3 prev swiper-prev ga_data_layer swiper-button-disabled" data-ga_data_layer="ga_lead|public-recruit|noti1|pre" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="true"><span class="blind">이전</span>
				                </button>
				                <button type="button" class="slide_btn_type3 next swiper-next ga_data_layer" data-ga_data_layer="ga_lead|public-recruit|noti1|next" tabindex="0" role="button" aria-label="Next slide" aria-disabled="false"><span class="blind">다음</span>
				                </button>
				            </div>
				            <ul class="sub_curation_list swiper-wrapper">
				                <li class="swiper-slide swiper-slide-visible swiper-slide-active" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=50955221&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|1">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-17</span>
				                                <span class="tag theme major">경기 시흥시</span>
				                            </div>
				                            <span class="company_name">(주)코스리거</span>
				                            <span class="announcement">(주)코스리거 부문별 신입/경력 정규 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-50955221" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50955221">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide swiper-slide-visible swiper-slide-next" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51060631&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|2">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">경기 성남시 분당구</span>
				                            </div>
				                            <span class="company_name">스노우(주)</span>
				                            <span class="announcement">[SNOW] 재무 지원 담당 (계약직)</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51060631" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51060631">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51061368&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|3">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">경기 성남시 분당구</span>
				                            </div>
				                            <span class="company_name">(주)카카오엔터프라이즈</span>
				                            <span class="announcement">[IaaS개발실] KakaoCloud 컴퓨팅 서비스 개발자 영입</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51061368" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51061368">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51060541&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|4">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-12</span>
				                                <span class="tag theme major">전국</span>
				                            </div>
				                            <span class="company_name">대한무역투자진흥공사</span>
				                            <span class="announcement">2025년 3기 체험형 청년인턴 모집</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51060541" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51060541">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51061339&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|5">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-10</span>
				                                <span class="tag theme major">서울 성동구</span>
				                            </div>
				                            <span class="company_name">(주)에스엠엔터테인먼트</span>
				                            <span class="announcement">영상 플랫폼 유통 및 프로모션 담당자 채용 (신입/경력)</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51061339" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51061339">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51060685&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|6">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-4</span>
				                                <span class="tag theme major">경기 성남시 분당구</span>
				                            </div>
				                            <span class="company_name">네이버클라우드(주)</span>
				                            <span class="announcement">[NAVER Cloud] HR - 채용운영 지원 (체험형 인턴)</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51060685" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51060685">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51060612&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|7">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-10</span>
				                                <span class="tag theme major">경북 안동시</span>
				                            </div>
				                            <span class="company_name">에스케이플라즈마(주)</span>
				                            <span class="announcement">SK플라즈마 생산관리 신입(전환형 인턴) 모집</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51060612" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51060612">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51062420&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|8">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-5</span>
				                                <span class="tag theme major">울산 중구</span>
				                            </div>
				                            <span class="company_name">근로복지공단</span>
				                            <span class="announcement">공무직(사무원) 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51062420" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51062420">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51052361&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|9">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">서울 중구</span>
				                            </div>
				                            <span class="company_name">(주)피클플러스</span>
				                            <span class="announcement">CS 매니저 (채팅형) 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51052361" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51052361">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51051466&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|10">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-5</span>
				                                <span class="tag theme major">경기 김포시</span>
				                            </div>
				                            <span class="company_name">(주)스킨팜</span>
				                            <span class="announcement">품질관리(화장품 R&amp;D연구소) 및 영업사원 신입/경력 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51051466" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51051466">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51061963&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|11">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-12</span>
				                                <span class="tag theme major">광주 동구</span>
				                            </div>
				                            <span class="company_name">전남대학교병원</span>
				                            <span class="announcement">2025년 6월 직원(지원직) 공개채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51061963" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51061963">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51023885&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|12">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">서울 강남구</span>
				                            </div>
				                            <span class="company_name">에빈코리아(유)</span>
				                            <span class="announcement">경영지원/K뷰티상품기획/구매개발/해외온라인(아마존,그외)/영상PD 및 작가/온라인MD 및 마케팅</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51023885" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51023885">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51062792&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|13">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-13</span>
				                                <span class="tag theme major">전국</span>
				                            </div>
				                            <span class="company_name">한국교통안전공단</span>
				                            <span class="announcement">2025년 제3차 기간제근로자 통합채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51062792" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51062792">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51062032&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|14">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-7</span>
				                                <span class="tag theme major">경기 화성시</span>
				                            </div>
				                            <span class="company_name">(주)동진쎄미켐</span>
				                            <span class="announcement">[6월 정기] 동진쎄미켐 생산직 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51062032" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51062032">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51062197&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|15">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-7</span>
				                                <span class="tag theme major">경기 화성시</span>
				                            </div>
				                            <span class="company_name">(주)동진쎄미켐</span>
				                            <span class="announcement">[6월 정기]동진쎄미켐 각 부문 신입/경력 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51062197" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51062197">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=50848273&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|16">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">내일마감</span>
				                                <span class="tag theme major">서울 강남구</span>
				                            </div>
				                            <span class="company_name">아파트멘터리(주)</span>
				                            <span class="announcement">아파트멘터리(주) 부문별 경력직 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-50848273" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50848273">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=50670332&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|17">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">경기 군포시</span>
				                            </div>
				                            <span class="company_name">(주)유엔에이엔지니어링</span>
				                            <span class="announcement">신입 및 경력직원 채용공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-50670332" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50670332">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51054870&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|18">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-8</span>
				                                <span class="tag theme major">부산전체</span>
				                            </div>
				                            <span class="company_name">동국씨엠(주)</span>
				                            <span class="announcement">[동국씨엠 부산공장] 특급 소방안전관리자 채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51054870" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51054870">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51052467&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|19">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">서울 중구</span>
				                            </div>
				                            <span class="company_name">한국생명존중희망재단</span>
				                            <span class="announcement">[제2025-3호] 제3차 직원 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51052467" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51052467">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51052392&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|20">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-12</span>
				                                <span class="tag theme major">서울 금천구</span>
				                            </div>
				                            <span class="company_name">한국해양조사협회</span>
				                            <span class="announcement">2025년도 제2차 직원채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51052392" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51052392">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51051497&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|21">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-17</span>
				                                <span class="tag theme major">부산 영도구</span>
				                            </div>
				                            <span class="company_name">한국해양수산개발원</span>
				                            <span class="announcement">2025년 제1차 정규직 연구직 석사 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51051497" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51051497">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51050258&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|22">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-6</span>
				                                <span class="tag theme major">대전 유성구</span>
				                            </div>
				                            <span class="company_name">국립중앙과학관</span>
				                            <span class="announcement">제6회 기간제 근로자 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51050258" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51050258">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51050074&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|23">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-4</span>
				                                <span class="tag theme major">경기 성남시 분당구</span>
				                            </div>
				                            <span class="company_name">(주)아이디스</span>
				                            <span class="announcement">2025년 2분기 집중채용 (신입 및 경력) [홀딩스-IT, 국내사업 등</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51050074" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51050074">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51049864&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|24">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">진행예정</span>
				                                <span class="tag theme major">전국</span>
				                            </div>
				                            <span class="company_name">한국방송공사</span>
				                            <span class="announcement">2025년 KBS 신입직원 공개채용</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51049864" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51049864">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51049679&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|25">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-13</span>
				                                <span class="tag theme major">경기 포천시</span>
				                            </div>
				                            <span class="company_name">포천도시공사</span>
				                            <span class="announcement">2025년 제2회 직원 공개채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51049679" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51049679">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51037538&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|26">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-11</span>
				                                <span class="tag theme major">서울 종로구</span>
				                            </div>
				                            <span class="company_name">해커스교육그룹</span>
				                            <span class="announcement">[신입] 어학원 운영 및 마케팅 (종로)</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51037538" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51037538">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51038137&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|27">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-4</span>
				                                <span class="tag theme major">경기 성남시 분당구</span>
				                            </div>
				                            <span class="company_name">(주)아이디스</span>
				                            <span class="announcement">2025년 2분기 집중채용 (신입 및 경력) [연구소]</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51038137" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51038137">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51040029&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|28">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-8</span>
				                                <span class="tag theme major">부산 강서구</span>
				                            </div>
				                            <span class="company_name">한국마사회</span>
				                            <span class="announcement">2025년 전임직(무기계약직) 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51040029" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51040029">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51038578&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|29">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">D-8</span>
				                                <span class="tag theme major">서울전체</span>
				                            </div>
				                            <span class="company_name">(재)한국장기조직기증원</span>
				                            <span class="announcement">2025년 제2차 직원 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51038578" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51038578">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				                <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                    <a href="/zf_user/jobs/relay/view?rec_idx=51037912&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti1')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti1|30">
				                        <div class="sub_curation_slide">
				                            <div class="tag_line">
				                                <span class="tag d-day">18시마감</span>
				                                <span class="tag theme major">경기 과천시</span>
				                            </div>
				                            <span class="company_name">중앙선거관리위원회</span>
				                            <span class="announcement">기획국 기간제근로자 채용 공고</span>
				                        </div>
				                    </a>
				                    <button type="button" class="newcomer_book_mark like_btn scrap-51037912" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51037912">
				                        <span class="blind">북마크</span>
				                    </button>
				                </li>
				            </ul>
				            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span><span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
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
				
				
				<div class="common_recruilt_list">
				    <div class="area_title list_total_count">
				        <h2>전체 채용정보</h2>
				        <span class="total_count"><em>5,891</em>건</span>
				    </div>
				    <div class="list_info">
				        <div>
				            <div class="InpBox Line rewards">
				                <span class="Chk">
				                    <input type="checkbox" id="filter_ai_head_hunting" value="y">
				                    <label class="Lbl" for="filter_ai_head_hunting">취업축하금만 보기
				                        <svg>
				                            <use xlink:href="#rec_title_tag_emoji_reward"></use>
				                        </svg>
				                    </label>
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
				    <div id="default_list_wrap" style="position:relative">
				        <section class="list_recruiting">
				            <h2 class="blind">공고리스트</h2>
				            <div class="list_body">
				
				                <div id="rec-51072452" class="list_item effect">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <span class="str_tit">한국중부발전(주)</span>
				                            <button type="button" csn="UlU3UmlWNmplQnB4LzI1K1RWTXBVZz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> <span class="main_corp" title="한국전력공사그룹">한국전력공사그룹</span>
				                            <span class="info_stock" title="대기업">대기업</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit new" id="rec_link_51072452" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="2025년도 제2차 4직급 신입직원 채용공고" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51072452" target="_blank" onmousedown=""><span>2025년도 제2차 4직급 신입직원 채용공고</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51072452" imgtype="button" class="spr_scrap btn_scrap scrap-51072452 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>기술지원</span><span>데이터분석가</span><span>데이터엔지니어</span><span>보안컨설팅</span><span>유지보수</span> 외 </span>
				                            </div>
				                            <div class="job_badge">
				                                <span class="hot">
				                                    <svg>
				                                        <use xlink:href="#rec_title_tag_emoji_hot"></use>
				                                    </svg>
				                                    오늘 뜬 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">서울전체 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <p class="support_detail">
				                                <span class="date">~07.03(목)</span>
				                                <span class="deadlines">3시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
								<!-- 새로 등록된 글 여부에 따라 해당 div 출력해줌 -->
				                <div class="today_done">
				                    <span class="today_txt">
				                        오늘 업데이트된 공고 여기까지
				                    </span>
				                </div>
								<!-- // end 새로 등록된 글 여부에 따라 해당 div 출력해줌 -->
				                
				                
				                
				                
				                
				                <!-- 채용정보 1줄 영역 - 클래스 list_item가 한세트임 -->
				                <div id="rec-51068201" class="list_item">
				                    <div class="box_item">
				                        <div class="col company_nm">
				                            <a href="javascript:void(0)" class="str_tit" target="_blank">(주)신동양기업 </a>
				                            <button type="button" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off">
				                           		<span>관심기업 등록</span>
				                            </button>
				                            <span class="main_corp" title="오엠알오그룹">오엠알오그룹</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51068201" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="안전관리자 모집 (더 운정 현장)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51068201" target="_blank" onmousedown=""><span>안전관리자 모집 (더 운정 현장)</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51068201" imgtype="button" class="spr_scrap btn_scrap scrap-51068201 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>안전관리자</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 파주시</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="접수예정" onclick="try{quickApplyForm('51075153','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}">
				                            	<span class="sri_btn_tobe_apply">접수예정</span>
				                            </button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">16시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				                <!-- // end 채용정보 1줄 영역 -->
				                
				                
				                
				
				                <div id="rec-51068122" class="list_item">
				                    <div class="box_item">
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=Sno4OWdrUG4rUWJrZ0tDUyt2YWNzUT09" class="str_tit" target="_blank">
				                                지에스지엠(주) </a>
				                            <button type="button" csn="Sno4OWdrUG4rUWJrZ0tDUyt2YWNzUT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51068122" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="온라인쇼핑몰 주문/발주/CS  신입/경력 모집" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51068122" target="_blank" onmousedown=""><span>온라인쇼핑몰 주문/발주/CS 신입/경력 모집</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51068122" imgtype="button" class="spr_scrap btn_scrap scrap-51068122 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>영업관리</span>
				                                    <span>영업지원</span>
				                                    <span>쇼핑몰관리</span>
				                                    <span>수/발주</span>
				                                    <span>정산관리</span> 외
				                                </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">서울 강남구</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학(2,3년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        
				                        <!-- 채용공고 상태에 따라 해당영역 ui 동적으로 출력해줘야 함 -->
				                        <div class="col support_info">
				                            <p class="support_detail">
				                                <span class="date">D-3</span>
				                                <span class="deadlines">16시간 전 등록</span>
				                            </p>
				                        </div>
				                        <!-- 채용공고 상태에 따라 해당영역 ui 동적으로 출력해줘야 함 -->
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067909" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=NlZXUU5ZRkxaTzhVL2lLS1N4bkJqQT09" class="str_tit" target="_blank">
				                                (주)한창이지엠 </a>
				                            <button type="button" csn="NlZXUU5ZRkxaTzhVL2lLS1N4bkJqQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067909" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="'25년 한창이지엠 산업안전 관리자 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067909" target="_blank" onmousedown=""><span>'25년 한창이지엠 산업안전 관리자 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067909" imgtype="button" class="spr_scrap btn_scrap scrap-51067909 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>안전보건관리자</span> </span>
				                            </div>
				                            <div class="job_badge">
				                                <span class="mActive">
				                                    생산 스크랩 TOP100 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">충남 천안시 서북구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067909','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067739" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <span class="str_tit">㈜트리포스</span>
				                            <button type="button" csn="WFFVWVdrVU13ejBtbmtYaXZNZEFHQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067739" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="백엔드, 프론트엔드 소프트웨어 개발자(신입)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067739" target="_blank" onmousedown=""><span>백엔드, 프론트엔드 소프트웨어 개발자(신입)</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067739" imgtype="button" class="spr_scrap btn_scrap scrap-51067739 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>앱개발</span><span>웹개발</span><span>Android</span><span>iOS</span><span>Java</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 성남시 수정구</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 정규직 외</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067739','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">채용시</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067612" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=OWdoY1FVa1pCRm9oRW1OS2N1blFBQT09" class="str_tit" target="_blank">
				                                우신금속 </a>
				                            <button type="button" csn="OWdoY1FVa1pCRm9oRW1OS2N1blFBQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> <span class="main_corp" title="우신금속그룹">우신금속그룹</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067612" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="알루미늄 합금 제조 구매 관리직 신입 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067612" target="_blank" onmousedown=""><span>알루미늄 합금 제조 구매 관리직 신입 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067612" imgtype="button" class="spr_scrap btn_scrap scrap-51067612 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>영업관리</span><span>구매관리</span><span>자재구매</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">울산 울주군</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067612','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067584" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=Y1FNTVJXd3hlZ0dGbHo5ek9VZTFqQT09" class="str_tit" target="_blank">
				                                와이시피(주) </a>
				                            <button type="button" csn="Y1FNTVJXd3hlZ0dGbHo5ek9VZTFqQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> <span class="main_corp" title="와이시피그룹">와이시피그룹</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067584" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="생산기술팀 사원 모집(와이시피특수강)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067584" target="_blank" onmousedown=""><span>생산기술팀 사원 모집(와이시피특수강)</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067584" imgtype="button" class="spr_scrap btn_scrap scrap-51067584 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>생산관리</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">부산 강서구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067584','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067575" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=UjA0Wkl4YjlGRGFERVZZQUJhVHNlQT09" class="str_tit" target="_blank">
				                                (주)신테카바이오 </a>
				                            <button type="button" csn="UjA0Wkl4YjlGRGFERVZZQUJhVHNlQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> <span class="info_stock" title="코스닥">코스닥</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067575" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="사업개발 및 경영지원 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067575" target="_blank" onmousedown=""><span>사업개발 및 경영지원 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067575" imgtype="button" class="spr_scrap btn_scrap scrap-51067575 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>데이터분석가</span><span>데이터엔지니어</span><span>유지보수</span><span>딥러닝</span><span>머신러닝</span> 외 </span>
				                            </div>
				                            <div class="job_badge">
				                                <span class="hot">
				                                    <svg>
				                                        <use xlink:href="#rec_title_tag_emoji_hot"></use>
				                                    </svg>
				                                    인기있는 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">대전 유성구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067575','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.17(목)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067558" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=QS9EVnp6cGs1emI3YzVMQWNqcUMvUT09" class="str_tit" target="_blank">
				                                광주과학기술원 </a>
				                            <button type="button" csn="QS9EVnp6cGs1emI3YzVMQWNqcUMvUT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067558" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="광주과학기술원 연구지원팀 행정지원직 채용 공고" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067558" target="_blank" onmousedown=""><span>광주과학기술원 연구지원팀 행정지원직 채용 공고</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067558" imgtype="button" class="spr_scrap btn_scrap scrap-51067558 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>사무행정</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">광주 북구</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 계약직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067558','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~06.30(월)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067531" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=U3ZEUE03MmhHc1NXQkdSMmxqRE0wdz09" class="str_tit" target="_blank">
				                                (주)대산 </a>
				                            <button type="button" csn="U3ZEUE03MmhHc1NXQkdSMmxqRE0wdz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067531" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="[안양] 건축자재 물류팀 모집(지게차운전기능사 소지 우대)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067531" target="_blank" onmousedown=""><span>[안양] 건축자재 물류팀 모집(지게차운전기능사 소지 우대)</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067531" imgtype="button" class="spr_scrap btn_scrap scrap-51067531 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>전동지게차</span><span>지게차</span><span>물류관리</span><span>자재관리</span><span>재고관리</span> 외 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 안양시 동안구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067531','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.31(목)</span>
				                                <span class="deadlines">18시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				                
				                <div id="rec-51067384" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=dW4raTVnVTcraVBlMlZicVJhS1NKdz09" class="str_tit" target="_blank">
				                                (주)알파로보틱스 </a>
				                            <button type="button" csn="dW4raTVnVTcraVBlMlZicVJhS1NKdz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067384" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="재고괸리&amp;amp;구매관리 모집" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067384" target="_blank" onmousedown=""><span>재고괸리&amp;구매관리 모집</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067384" imgtype="button" class="spr_scrap btn_scrap scrap-51067384 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>구매관리</span><span>자재관리</span><span>재고관리</span><span>창고관리</span><span>검품/검수</span> 외 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">부산 기장군</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067384','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067375" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=eTFtZnU0QU40dVpldW5uNjFIaWZaZz09" class="str_tit" target="_blank">
				                                (주)동신툴피아 </a>
				                            <button type="button" csn="eTFtZnU0QU40dVpldW5uNjFIaWZaZz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067375" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="재무(회계)팀 신입사원 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067375" target="_blank" onmousedown=""><span>재무(회계)팀 신입사원 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067375" imgtype="button" class="spr_scrap btn_scrap scrap-51067375 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>관리회계</span><span>기업회계</span><span>외부감사</span><span>재무기획</span><span>재무제표</span> 외 </span>
				                            </div>
				                            <div class="job_badge">
				                                <span class="mActive">
				                                    회계·세무·재무 지원 TOP100 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">서울 금천구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 계약직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067375','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">채용시</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067197" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=SmFpeDRCK1Q2akM0RkxzMHQyMDhuQT09" class="str_tit" target="_blank">
				                                (주)엔코스 </a>
				                            <button type="button" csn="SmFpeDRCK1Q2akM0RkxzMHQyMDhuQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067197" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="[엔코스] 품질관리 부자재 검수 인재채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067197" target="_blank" onmousedown=""><span>[엔코스] 품질관리 부자재 검수 인재채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067197" imgtype="button" class="spr_scrap btn_scrap scrap-51067197 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>품질검사원</span><span>품질분석</span><span>품질관리</span><span>품질보증</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 오산시</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067197','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067196" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=eTFtZnU0QU40dVpldW5uNjFIaWZaZz09" class="str_tit" target="_blank">
				                                (주)동신툴피아 </a>
				                            <button type="button" csn="eTFtZnU0QU40dVpldW5uNjFIaWZaZz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067196" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="경영지원부 인사총무팀 신입 및 경력사원 모집" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067196" target="_blank" onmousedown=""><span>경영지원부 인사총무팀 신입 및 경력사원 모집</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067196" imgtype="button" class="spr_scrap btn_scrap scrap-51067196 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>수행비서</span><span>총무</span><span>경영지원</span><span>면접/인터뷰</span><span>온보딩</span> 외 </span>
				                            </div>
				                            <div class="job_badge">
				                                <span class="mActive">
				                                    인사·노무·HRD 지원 TOP100 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">서울 금천구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 계약직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067196','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">채용시</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067176" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=QjAvWTRjdksycXVIUjFoWFB6cmxYQT09" class="str_tit" target="_blank">
				                                (주)제이오텍 </a>
				                            <button type="button" csn="QjAvWTRjdksycXVIUjFoWFB6cmxYQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067176" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="(주)제이오텍 완제품조립 신입/경력 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067176" target="_blank" onmousedown=""><span>(주)제이오텍 완제품조립 신입/경력 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067176" imgtype="button" class="spr_scrap btn_scrap scrap-51067176 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>단순생산직</span><span>조립</span><span>생산</span><span>제조</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">대전 유성구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">고졸↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067176','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~06.30(월)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067161" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=SmFpeDRCK1Q2akM0RkxzMHQyMDhuQT09" class="str_tit" target="_blank">
				                                (주)엔코스 </a>
				                            <button type="button" csn="SmFpeDRCK1Q2akM0RkxzMHQyMDhuQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067161" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="[엔코스] 품질관리 반제품 QC담당자 채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067161" target="_blank" onmousedown=""><span>[엔코스] 품질관리 반제품 QC담당자 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067161" imgtype="button" class="spr_scrap btn_scrap scrap-51067161 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>품질검사원</span><span>품질분석</span><span>품질관리</span><span>품질보증</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 오산시</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">대학교(4년)↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067161','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.18(금)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067045" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=QXl2ZmhpUWdSUGo5ZnZaRllNSG10dz09" class="str_tit" target="_blank">
				                                (주)피유시스 </a>
				                            <button type="button" csn="QXl2ZmhpUWdSUGo5ZnZaRllNSG10dz09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067045" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="(주)피유시스 2025년도  공개채용(R&amp;amp;D)" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067045" target="_blank" onmousedown=""><span>(주)피유시스 2025년도 공개채용(R&amp;D)</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067045" imgtype="button" class="spr_scrap btn_scrap scrap-51067045 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>연구원</span><span>R&amp;D</span><span>R&amp;D기획</span><span>고분자</span><span>기술연구</span> 외 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경기 파주시</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067045','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~07.17(목)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51067015" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=dnBndHNxaUp2dW9ZYmM1eU1tbmZNUT09" class="str_tit" target="_blank">
				                                한선엔지니어링주식회사 </a>
				                            <button type="button" csn="dnBndHNxaUp2dW9ZYmM1eU1tbmZNUT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> <span class="main_corp" title="한국선재그룹">한국선재그룹</span>
				                            <span class="info_stock" title="코스닥">코스닥</span>
				                        </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51067015" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="생산팀 전조작업자 구인" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51067015" target="_blank" onmousedown=""><span>생산팀 전조작업자 구인</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51067015" imgtype="button" class="spr_scrap btn_scrap scrap-51067015 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>금속</span><span>사상/래핑</span><span>후처리</span> </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">부산 강서구</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 정규직 외</p>
				                                </li>
				                                <li>
				                                    <p class="education">고졸↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51067015','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">채용시</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51066896" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=Mkd1Q25YMXdxMXgrS05yU2lUdmt5UT09" class="str_tit" target="_blank">
				                                (주)영유통 </a>
				                            <button type="button" csn="Mkd1Q25YMXdxMXgrS05yU2lUdmt5UT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51066896" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="한국필립모리스 궐련형 전자담배(IQOS) 홍보 및 판매" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51066896" target="_blank" onmousedown=""><span>한국필립모리스 궐련형 전자담배(IQOS) 홍보 및 판매</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51066896" imgtype="button" class="spr_scrap btn_scrap scrap-51066896 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>판매직</span><span>영업마케팅</span><span>영업판촉</span><span>잡화판매</span><span>매장관리</span> 외 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">대구 달서구 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">경력무관 · 계약직</p>
				                                </li>
				                                <li>
				                                    <p class="education">학력무관</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51066896','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">~06.30(월)</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
				
				                <div id="rec-51066888" class="list_item">
				                    <div class="box_item">
				
				
				                        <div class="col company_nm">
				                            <a href="/zf_user/company-info/view-inner-recruit?csn=bGhPbmo1VTdZYkVvNG1CbUM5S0FLQT09" class="str_tit" target="_blank">
				                                (주)케이이씨 </a>
				                            <button type="button" csn="bGhPbmo1VTdZYkVvNG1CbUM5S0FLQT09" title="관심기업 등록" del_fl="n" aria-pressed="false" class="interested_corp" onclick="try{Saramin.btnJob('favor', this, '', 'list');}catch(e){}" first_nudge="off"><span>관심기업 등록</span></button> </div>
				                        <div class="col notification_info">
				                            <div class="job_tit">
				                                <a class="str_tit " id="rec_link_51066888" onclick="s_trackApply(this, 'public-recruit', 'generic');" title="(주)케이이씨  문경공장 PC 생산관리,품질관리직  채용" href="/zf_user/jobs/relay/view?view_type=public-recruit&amp;rec_idx=51066888" target="_blank" onmousedown=""><span>(주)케이이씨 문경공장 PC 생산관리,품질관리직 채용</span></a> <button type="button" onclick="Saramin.btnJob('scrap',this,'','list');" title="스크랩" scraped="n" rec_idx="51066888" imgtype="button" class="spr_scrap btn_scrap scrap-51066888 off"><span class="blind">스크랩</span></button> </div>
				                            <div class="job_meta">
				                                <span class="job_sector">
				                                    <span>생산관리</span><span>건축가</span><span>건축기사</span><span>철근콘크리트</span><span>품질관리</span> 외 </span>
				                            </div>
				                        </div>
				                        <div class="col recruit_info">
				                            <ul>
				                                <li>
				                                    <p class="work_place">경북 문경시 외</p>
				                                </li>
				                                <li>
				                                    <p class="career">신입 · 경력 · 정규직</p>
				                                </li>
				                                <li>
				                                    <p class="education">고졸↑</p>
				                                </li>
				                                <li>
				                                    <p class="salary">
				                                        면접 후 협의 </p>
				                                </li>
				                            </ul>
				                        </div>
				                        <div class="col support_info">
				                            <button class="sri_btn_md" title="클릭하면 입사지원할 수 있는 창이 뜹니다." onclick="try{quickApplyForm('51066888','','t_category=public-recruit&amp;t_content=generic', ''); return false;} catch (e) {}; return false;" onmousedown="try{n_trackEvent('apply','list','quick_apply');}catch(e){}"><span class="sri_btn_immediately">입사지원</span></button>
				                            <p class="support_detail">
				                                <span class="date">채용시</span>
				                                <span class="deadlines">19시간 전 등록</span>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="similar_recruit"></div>
				                </div>
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
				    
				    
				    
				    
				    
				    <!-- 하단 인턴 슬라이드 영역 start -->
				    <div class="newcomer_sub_bottom">
				        <p class="curation_tit type7">
				            신입·인턴 이걸로 끝! BEST 공고</p>
				        <div class="wrap_sub_curation priority_rand_list">
				            <div class="sub_curation_swiper swiper-container-horizontal">
				                <div class="slide_btn_wrap">
				                    <button type="button" class="slide_btn_type3 prev swiper-prev ga_data_layer swiper-button-disabled" data-ga_data_layer="ga_lead|public-recruit|noti4|pre" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="true"><span class="blind">이전</span>
				                    </button>
				                    <button type="button" class="slide_btn_type3 next swiper-next ga_data_layer" data-ga_data_layer="ga_lead|public-recruit|noti4|next" tabindex="0" role="button" aria-label="Next slide" aria-disabled="false"><span class="blind">다음</span>
				                    </button>
				                </div>
				                <ul class="sub_curation_list swiper-wrapper">
				                    <li class="swiper-slide swiper-slide-visible swiper-slide-active" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=50884875&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|1">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-2</span>
				                                    <span class="tag theme major">경기 화성시</span>
				                                </div>
				                                <span class="company_name">엠케이피(주)</span>
				                                <span class="announcement">2025년 각 부문 신입 / 경력 공개 채용</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-50884875" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50884875">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide swiper-slide-visible swiper-slide-next" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=50824507&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|2">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-29</span>
				                                    <span class="tag theme major">경기 성남시 중원구</span>
				                                </div>
				                                <span class="company_name">(주)오아시스</span>
				                                <span class="announcement">[취업보너스 200/월 최대 600] 오아시스 성남 센터 현장직 모집</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-50824507" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50824507">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=50824470&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|3">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-29</span>
				                                    <span class="tag theme major">경기 의왕시</span>
				                                </div>
				                                <span class="company_name">(주)오아시스</span>
				                                <span class="announcement">[취업보너스 200/월 최대 600] 오아시스 의왕 센터 현장직 모집</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-50824470" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50824470">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=51026259&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|4">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">오늘마감</span>
				                                    <span class="tag theme major">서울 영등포구</span>
				                                </div>
				                                <span class="company_name">하나증권(주)</span>
				                                <span class="announcement">투자금융실(대체투자/인수금융) 경력직 인재 채용</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-51026259" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51026259">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide swiper-slide-visible" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=50945279&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|5">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">내일마감</span>
				                                    <span class="tag theme major">경기 여주시</span>
				                                </div>
				                                <span class="company_name">보광개발(주)</span>
				                                <span class="announcement">[이포 컨트리클럽] 경영지원팀(프론트/총무) 채용</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-50945279" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50945279">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=51016079&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|6">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-22</span>
				                                    <span class="tag theme major">서울 강동구</span>
				                                </div>
				                                <span class="company_name">(주)에이블코퍼레이션</span>
				                                <span class="announcement">[주 3.5일근무]전지점 피부관리사 특별채용(워라벨/인센티브/빠른승진)</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-51016079" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51016079">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=51002334&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|7">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-6</span>
				                                    <span class="tag theme major">경기 안성시</span>
				                                </div>
				                                <span class="company_name">덕산실업(주)</span>
				                                <span class="announcement">사세 확장에 따른 직원 채용</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-51002334" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="51002334">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                    <li class="swiper-slide" style="width: 240px; margin-right: 15px;">
				                        <a href="/zf_user/jobs/relay/view?rec_idx=50954426&amp;view_type=public-recruit" target="_blank" class="newcomer_link_view ga_data_layer" onclick="try{s_trackApply(this, 'public-recruit', 'noti4')}catch(e){};" data-ga_data_layer="ga_lead|public-recruit|noti4|8">
				                            <div class="sub_curation_slide">
				                                <div class="tag_line">
				                                    <span class="tag d-day">D-75</span>
				                                    <span class="tag theme major">대구 중구</span>
				                                </div>
				                                <span class="company_name">(주)아정네트웍스</span>
				                                <span class="announcement">아정당 대구 주간 CS인바운드 매니저(주6,5일) 4280+a</span>
				                            </div>
				                        </a>
				                        <button type="button" class="newcomer_book_mark like_btn scrap-50954426" onclick="Saramin.btnJob('scrap',this,'','live-list');" scraped="n" rec_idx="50954426">
				                            <span class="blind">북마크</span>
				                        </button>
				                    </li>
				                </ul>
				                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
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
	</div>
	

</body>
</html>