<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
  <head>
	<title>채용 공고페이지</title>
	<script>
  		const recruitmentNo = "${recruitmentNo}";
  		const userName = "${username}"
	</script>
	<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
	<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
	<link rel="stylesheet" href="/css/member/recruitment/recruitmentNotice.css">
	<script defer src="/js/member/recruitment/recruitmentNotice.js"></script>
<style>
#skill {
    display: flex;
    flex-wrap: wrap;
    gap: 6px;
}

.skill-tag {
    background-color: #8a2be2;
    color: white;
    padding: 4px 10px;
    border-radius: 50px;
    font-size: 13px;
}
.position-tag {
		background: #fff;
    padding: 5px 13px;
    border-radius: 15px;
    border: 1px solid var(--gray80);
    font-weight: 500;
    font-size: 12px;
    color: #333;
}
#recContent img {
    max-width: 100%;
    height: auto;
    display: block;   /* 필요시 줄바꿈 */
    margin: 0 auto;   /* 가운데 정렬 */
}
#comLogo {
    width: 200px;    /* 원하는 가로 */
    height: 100px;   /* 원하는 세로 */
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;    /* 삐져나온 부분 잘라내기 */
    background: #fff;
    border-radius: 8px;  /* 모서리 둥글게 (선택) */
}

</style>
  </head>
  <body>
    <div id="sri_section" class="board_view_wrap">
      <div class="inner">
	      <div id="sri_wrap" class="d-block">
	        <div id="content">
	
	          <div class="wrap_jview">
	            <section class="jview jview-0-51076217">
	              <a class="placeholder" tabindex="-1"></a>
	              <div class="wrap_jv_cont">
	                <div class="wrap_jv_header">
	                  <a class="placeholder" tabindex="-1"></a>
	                  <div class="jv_header">
	                    <div class="title_inner">
	                      
	                      <button type="button" class="btn_jview spr_jview btn_interest scrab_company">
	                        <svg
	                          width="16"
	                          height="16"
	                          viewBox="0 0 16 16"
	                          fill="none"
	                          class="icon_interested"
	                        >
	                          <path
	                            d="M6.02498 12.7861L5.64593 12.4305L5.26661 12.0676L4.9045 11.7145L4.57727 11.3886L4.30315 11.1078L4.09798 10.8878L3.60986 10.3434L3.3016 9.99345L3.02797 9.67542L2.79004 9.38984L2.58897 9.13749L2.42437 8.91736L2.35722 8.8214C2.09522 8.43618 1.89245 8.04622 1.73708 7.6185C1.41094 6.38491 1.4304 5.39882 1.73942 4.62739C2.06107 3.82442 2.56896 3.24401 3.25465 2.87112C3.97841 2.47923 4.74412 2.39639 5.56989 2.63105C6.18765 2.82443 6.71818 3.1964 7.16412 3.7682L7.29598 3.94787L7.58589 4.37863L8.00062 4.99485L8.41545 4.3787L8.71259 3.93737C9.13803 3.32866 9.65597 2.91815 10.2666 2.68825L10.4462 2.62682C11.2606 2.39707 12.0242 2.47999 12.7465 2.87106C13.4323 3.24395 13.9403 3.82439 14.262 4.62745C14.5705 5.39767 14.5909 6.3831 14.2537 7.6522C14.1088 8.04661 13.9058 8.43727 13.6424 8.82404C13.6227 8.85295 13.6015 8.88333 13.5787 8.9152L13.4124 9.13756L13.2113 9.38998L12.9737 9.67507L12.549 10.1656L12.1189 10.6483L12.1177 10.6497L11.8097 10.99L11.569 11.2414L11.2664 11.5467L10.7343 12.0681L10.3555 12.4306L9.97658 12.786L9.61564 13.1169L9.28863 13.4077C9.1893 13.4942 9.09986 13.57 9.0226 13.6331C9.02237 13.6333 9.02214 13.6335 9.02192 13.6336L8.00223 14.461L7.999 14.4634L6.85007 13.5257C6.76132 13.4504 6.66238 13.3642 6.55569 13.2693L6.20886 12.9558C6.20844 12.9554 6.20801 12.955 6.20759 12.9546C6.148 12.9 6.08733 12.844 6.02592 12.787C6.02561 12.7867 6.0253 12.7864 6.02498 12.7861Z"
	                            fill="white"
	                            stroke="#8491A7"
	                          />
	                        </svg>
	                        <span>관심기업</span>
	                      </button>
	                    </div>
	                    <h1 class="tit_job"> 공고 제목 </h1>

								<button type="button" class="btn_big_star btn_scrap"
									aria-pressed="false">
								 <span class="txt_scrap">1</span>
								</button>
								<div class="btn_apply">
									<span class="dday">D-6</span>
									<button class="sri_btn_lg for_btn_event">
										<span class="sri_btn_homepage_apply"></span>입사 지원
									</button>
								</div>
	
						</div>
	                </div>
	                <div class="jv_cont jv_summary">
	                  <h2 class="jv_title blind">핵심 정보</h2>
	                  <div class="cont">
	                    <div class="col">
	                      <dl>
	                        <dt>경력</dt>
	                        <dd id="yearCodeName"><strong>신입·경력 1~10년</strong></dd>
	                      </dl>
	                      <dl>
	                        <dt>학력</dt>
	                        <dd id="education"><strong>대졸(4년제) 이상</strong></dd>
	                      </dl>
	                      <dl>
	                        <dt>직급/직책</dt>
	                        <dd id="position">
	                          <strong>정규직</strong> 수습기간 3개월
	                        </dd>
	                      </dl>
	                    </div>
	                    <div class="col">
	                      <dl>
	                        <dt>급여</dt>
	                        <dd id="salary">면접 후 결정</dd>
	                      </dl>
	                      <dl>
	                        <dt>사용기술</dt>
	                        <dd id="skill"></dd>
	                      </dl>
	                      <dl>
	                        <dt>근무지역</dt>
	                        <dd id="addr">
	                          경기 파주시
	                          <button
	                            type="button"
	                            class="spr_jview btn_jview btn_move_map"
	                          >
	                            <span>지도</span>
	                          </button>
	                        </dd>
	                      </dl>
	                    </div>
	                    <div class="meta">
	                      <span class="salary_desc"></span>
	                      <ul class="list_meta">
	                        <li id="viewRecruit">조회수 <strong>176</strong></li>
	                        <li>
	                          <a class="spr_jview btn_report">
	                            <svg aria-hidden="true" focusable="false" class="ic"><use xlink:href="#icon_report"></use></svg>신고하기
	                          </a>
	                        </li>
	                        <!-- //wmg-31267-->
	                      </ul>
	                    </div>
	                  </div>
	                </div>
	                <div class="jv_cont jv_detail">
	                  <h2 class="jv_title blind">상세요강</h2>
	                  <div class="cont" id="recContent">
	                  </div>
	                </div>
	                
	                <div
	                  class="jv_cont jv_location"
	                >
	                  <a class="placeholder" tabindex="-1"></a>
	                  <h2 class="jv_title">기업 위치</h2>
	                  <div class="cont box">
	                    <address class="address">
	                      <span class="spr_jview txt_adr" id="companyAddr">
	                        <span class="wrap_ic">
	                          <svg class="ic" width="20" height="20" viewBox="0 0 20 20" fill="none">
	                            <path
	                              d="M14.305 10.9376L10.5998 16.6302C10.1775 17.2789 9.21948 17.2542 8.83119 16.5846L5.52495 10.883C4.85419 9.72631 4.5 8.39263 4.5 7.05307C4.5 4.01462 7.00816 1.5 10.0448 1.5C13.0381 1.5 15.5 3.92741 15.5 6.91099C15.5 8.34059 15.0849 9.73943 14.305 10.9376Z"
	                              stroke="#8491A7"
	                            ></path>
	                            <circle cx="10" cy="7" r="2.5" stroke="#8491A7"></circle>
	                          </svg>
	                        </span>
	                        (10857) 경기 파주시 한산로 69
	                      </span>
	                    </address>
	                    <button
	                      type="button"
	                      class="btn_mapview relay_map"
	                      onclick="JobsView.moveMapPage('0', '51076217');"
	                      data-type="open"
	                    >
	                      <svg class="ic" width="24" height="24" viewBox="0 0 24 24" fill="none">
	                        <path d="M8.80769 9.58013L3.30769 11.8718C3.12137 11.9494 3 12.1315 3 12.3333V20.25C3 20.6068 3.36296 20.8488 3.69231 20.7115L8.80769 18.5801C8.93077 18.5288 9.06923 18.5288 9.19231 18.5801L14.7888 20.912C14.9229 20.9679 15.0747 20.9626 15.2047 20.8977L20.7236 18.1382C20.893 18.0535 21 17.8804 21 17.691V9.80902C21 9.43733 20.6088 9.19558 20.2764 9.3618L15.2047 11.8977C15.0747 11.9626 14.9229 11.9679 14.7888 11.912L9.19231 9.58013C9.06923 9.52885 8.93077 9.52885 8.80769 9.58013Z" stroke="#8491A7"></path>
	                        <mask id="path-2-inside-1_371_33951" fill="white">
	                          <path fill-rule="evenodd" clip-rule="evenodd" d="M12.9998 14.4995C12.9998 14.7757 12.776 14.9995 12.4999 14.9995C12.2237 14.9995 11.9999 14.7757 11.9999 14.4995V7.49932C11.9999 7.22319 12.2237 6.99934 12.4999 6.99934C12.776 6.99934 12.9998 7.22319 12.9998 7.49932V14.4995Z"></path>
	                        </mask>
	                        <path fill-rule="evenodd" clip-rule="evenodd" d="M12.9998 14.4995C12.9998 14.7757 12.776 14.9995 12.4999 14.9995C12.2237 14.9995 11.9999 14.7757 11.9999 14.4995V7.49932C11.9999 7.22319 12.2237 6.99934 12.4999 6.99934C12.776 6.99934 12.9998 7.22319 12.9998 7.49932V14.4995Z" fill="black" fill-opacity="0.01" mask="url(#path-2-inside-1_371_33951)"></path>
	                        <path d="M12.9999 14.4995V7.49932H10.9999V14.4995H12.9999ZM11.9998 7.49932V14.4995H13.9998V7.49932H11.9998ZM12.4999 7.99934C12.2237 7.99934 11.9998 7.77547 11.9998 7.49932H13.9998C13.9998 6.6709 13.3283 5.99934 12.4999 5.99934V7.99934ZM12.9999 7.49932C12.9999 7.77547 12.776 7.99934 12.4999 7.99934V5.99934C11.6714 5.99934 10.9999 6.6709 10.9999 7.49932H12.9999ZM12.4999 13.9995C12.776 13.9995 12.9999 14.2234 12.9999 14.4995H10.9999C10.9999 15.328 11.6714 15.9995 12.4999 15.9995V13.9995ZM12.4999 15.9995C13.3283 15.9995 13.9998 15.328 13.9998 14.4995H11.9998C11.9998 14.2234 12.2237 13.9995 12.4999 13.9995V15.9995Z" fill="#8491A7" mask="url(#path-2-inside-1_371_33951)"></path>
	                        <path d="M12.4999 3.49924C13.6044 3.49924 14.4998 4.39459 14.4998 5.49929C14.4998 6.60401 13.6044 7.49935 12.4999 7.49935C11.3953 7.49935 10.4999 6.60401 10.4999 5.49929C10.4999 4.39459 11.3953 3.49924 12.4999 3.49924Z" fill="black" fill-opacity="0.01" stroke="#8491A7"></path>
	                        <path stroke="#8491A7" d="M9 9.5V18.5M15 12V20.5"></path>
	                      </svg>
	                      <span class="spr_jview">지도 보기</span>
	                    </button>
	                    <div
	                      id="mapContainerPop_0"
	                      class="layer_pop_manage layer_map"
	                      data-loaded="n"
	                    >
	                      <h4>지도 보기</h4>
	                      <div class="container_map">
	                        <div class="wrap_mapapi" id="mapArea_0">
	                          <div class="map_view_wrap">
	                            <div id="mapContainer_0" class="map_view">
	                              <div id="mapWrapper_0" class="view">
	                                <div
	                                  id="mapView_0"
	                                  style="width: 100%; height: 100%;"
	                                ></div>
	                                <div
	                                  id="roadView_0"
	                                  style="height:100%; text-align: left;"
	                                ></div>
	                              </div>
	                            </div>
	                            <div class="btn_map_switch" data-seq="0">
	                              <button
	                                type="button"
	                                class="btn_mapview on"
	                                data-type="mapview"
	                                onmousedown="try{n_trackEvent('relay_view', 'map', 'open', '');}catch(e){}"
	                              >
	                                지도
	                              </button>
	                              <button
	                                type="button"
	                                class="btn_skyview"
	                                data-type="skyview"
	                                onmousedown="try{n_trackEvent('relay_view', 'map', 'skyview', '');}catch(e){}"
	                              >
	                                스카이뷰
	                              </button>
	                            </div>
	                            <div class="desc">
	                              <div class="btns">
	                                <button type="button" class="reset">
	                                  지도초기화
	                                </button>
	                                <button
	                                  type="button"
	                                  class="big_map"
	                                  data-type="wideview"
	                                  onmousedown="try{n_trackEvent('relay_view', 'map', 'wideview', '');}catch(e){}"
	                                >
	                                  크게보기
	                                </button>
	                                <button
	                                  type="button"
	                                  class="route"
	                                  data-type="findtheway"
	                                  onmousedown="try{n_trackEvent('relay_view', 'map', 'findtheway', '');}catch(e){}"
	                                >
	                                  길찾기
	                                </button>
	                              </div>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                      <button
	                        type="button"
	                        class="btn_layer_close"
	                        onclick="jQuery(this).closest('.jv_location').removeClass('expand');"
	                      >
	                        <span>닫기</span>
	                      </button>
	                    </div>
	                  </div>
	                </div>
	                
	                <div class="jv_cont jv_howto">
	                	<a class="placeholder" tabindex="-1"></a>
								    <h2 class="jv_title">접수기간 및 방법</h2>
								    <div class="cont box">
								        <div class="status">
								            <div class="info_timer" data-remain-time="3557781"><span class="txt">남은 기간</span><span class="day" id="days">41</span><span class="txt_day">일</span><span class="time" id="times">04:15:54</span></div>
								            <dl class="info_period">
								                <dt>시작일</dt>
								                <dd id="startDate">2025.06.19 00:00</dd>
								                <dt class="end">마감일</dt>
								                <dd id="endDate">2025.07.31 23:59</dd>
								            </dl>
								        </div>
								        <dl class="guide">
								            <dt>지원방법</dt>
								            <dd class="method" id="recruitDesk">홈페이지 지원</dd>
								            </dd>
								        </dl>
								    </div>
									</div>
									
									<div class="jv_cont jv_company company_info_wrap_51076217"><a class="placeholder" tabindex="-1"></a>
									    <div class="jv_title">
									        <h2 class="jv_title_heading">기업정보</h2>
									    </div>
									    <div class="cont box">
									        <div class="wrap_info">
									            <div class="tit_area">
									                <div class="basic_info">
									                    <h3 id="companyh3">
									                    	<button type="button" class="btn_jview_round btn_jview btn_interest scrab_company">
									                        <svg
									                          width="16"
									                          height="16"
									                          viewBox="0 0 16 16"
									                          fill="none"	                          
															  class="icon_interested"
									                        >
									                          <path
									                            d="M6.02498 12.7861L5.64593 12.4305L5.26661 12.0676L4.9045 11.7145L4.57727 11.3886L4.30315 11.1078L4.09798 10.8878L3.60986 10.3434L3.3016 9.99345L3.02797 9.67542L2.79004 9.38984L2.58897 9.13749L2.42437 8.91736L2.35722 8.8214C2.09522 8.43618 1.89245 8.04622 1.73708 7.6185C1.41094 6.38491 1.4304 5.39882 1.73942 4.62739C2.06107 3.82442 2.56896 3.24401 3.25465 2.87112C3.97841 2.47923 4.74412 2.39639 5.56989 2.63105C6.18765 2.82443 6.71818 3.1964 7.16412 3.7682L7.29598 3.94787L7.58589 4.37863L8.00062 4.99485L8.41545 4.3787L8.71259 3.93737C9.13803 3.32866 9.65597 2.91815 10.2666 2.68825L10.4462 2.62682C11.2606 2.39707 12.0242 2.47999 12.7465 2.87106C13.4323 3.24395 13.9403 3.82439 14.262 4.62745C14.5705 5.39767 14.5909 6.3831 14.2537 7.6522C14.1088 8.04661 13.9058 8.43727 13.6424 8.82404C13.6227 8.85295 13.6015 8.88333 13.5787 8.9152L13.4124 9.13756L13.2113 9.38998L12.9737 9.67507L12.549 10.1656L12.1189 10.6483L12.1177 10.6497L11.8097 10.99L11.569 11.2414L11.2664 11.5467L10.7343 12.0681L10.3555 12.4306L9.97658 12.786L9.61564 13.1169L9.28863 13.4077C9.1893 13.4942 9.09986 13.57 9.0226 13.6331C9.02237 13.6333 9.02214 13.6335 9.02192 13.6336L8.00223 14.461L7.999 14.4634L6.85007 13.5257C6.76132 13.4504 6.66238 13.3642 6.55569 13.2693L6.20886 12.9558C6.20844 12.9554 6.20801 12.955 6.20759 12.9546C6.148 12.9 6.08733 12.844 6.02592 12.787C6.02561 12.7867 6.0253 12.7864 6.02498 12.7861Z"
									                            fill="white"
									                            stroke="#8491A7"
									                          />
									                        </svg>
									                        <span>관심기업</span>
									                      </button>
									                   </h3>
									                </div>
									                <div class="logo" id="comLogo"></div>
									            </div>
									            <div class="info_area">
									                <dl>
									                    <dt>대표자명</dt>
									                    <dd id="ceoName">강경인</dd>
									                </dl>
									                <dl>
									                    <dt>기업형태</dt>
									                    <dd id="comType">코스닥, 중소기업, 외부감사법인</dd>
									                </dl>
									                <dl>
									                    <dt>업종</dt>
									                    <dd id="induType">디스플레이 제조용 기계 제조업</dd>
									                </dl>
									                <dl>
									                    <dt>
									                        사원수</dt>
									                    <dd id="comMember">
									                        225 명 <span> (2025년 기준)</span></dd>
									                </dl>
									                <dl>
									                    <dt>설립년도</dt>
									                    <dd id="createYear">
									                        2002년 4월 10일 <span>(업력 24년차) </span></dd>
									                </dl>
									                <dl>
									                    <dt>홈페이지</dt>
									                    <dd id="comUrl"><a href="http://www.yasoled.com" target="_blank">
									                            www.yasoled.com </a></dd>
									                </dl>
									                <dl>
									                    <dt>기업주소</dt>
									                    <dd id="comSumAddr">경기 파주시 탄현면 한산로 69</dd>
									                </dl>
									            </div>
									            <div class="link_area" id="companyLink">
									               <a href="" target="_blank" class="spr_jview btn_jview_new btn_link ga_data_layer"><span>기업리뷰</span></a>
									               <a href="" target="_blank" class="spr_jview btn_jview_new btn_link ga_data_layer"><span>연봉정보</span></a></div>
									            </div>
									    </div>
									</div>
					
	              </div>
	            </section>
	
	          </div>
	          <div class="jv_remote">
		   			<div class="links">
				    	<button type="button" class="spr_jview jv_detail ready on cygg" onclick="scrollToWithOffset('.jv_header', -350)">
				    		<span class="material-symbols-outlined">dvr</span>
		            <span>채용공고</span>
	            </button>
			        <button type="button" class="spr_jview ready location" onclick="scrollToWithOffset('.jv_location', -100)">
		    				<span class="material-symbols-outlined">map</span>
	            	<span>기업 위치</span>
	            </button>
	            <button type="button" class="spr_jview jv_howto ready" onclick="scrollToWithOffset('.jv_howto', -100)">
				    		<span class="material-symbols-outlined">touch_app</span>
				        <span>접수방법</span>
			        </button>
	            <button type="button" class="spr_jview jv_company ready" onclick="scrollToWithOffset('.jv_company', -100)">
		    				<span class="material-symbols-outlined">corporate_fare</span>
	            	<span>기업정보</span>
	            </button>
	            <button type="button" class="spr_jview jv_reference" onmousedown="try{n_trackEvent('relay_view', 'remote_control', 'passdata', '');}catch(e){}">
		            <span>합격자료</span>
	            </button>
            </div>
	        </div>
	        
	        </div>
	      </div>
      </div>
    </div>
    
    <!-- 프로젝트 지원 모달 -->
  <div class="modal fade" id="applicationModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 450px;">
      <div class="modal-content">
        <!-- 헤더 -->
        <div class="modal-header bg-white">
          <h5 class="modal-title fs-5 fw-bold text-success" id="recruitTitle">채용공고 제목</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <!-- 본문 -->
        <div class="modal-body py-4 px-4">

          <!-- 이력서 선택 -->
          <div class="mb-4">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-semibold fs-14 mb-1">선택된 이력서</label>
              <button type="button" class="btn btn_violet_line fs-13 fw-semibold" id="btnShowResumeList">이력서 선택</button>
            </div>
            <!-- 선택된 이력서 카드 -->
            <div id="selectedResumeCard"></div>
            <!-- 이력서 목록 (토글) -->
            <div id="resumeList" class="mt-2 overflow-auto" style="display: none; max-height:220px;"></div>
          </div>

          <!-- 첨부파일 -->
          <div class="mb-3">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-semibold fs-14 mb-1">첨부파일 0건</label>
              <button class="btn btn-outline-secondary btn-sm" type="button">
                <i class="bi bi-plus-lg"></i> 파일추가
              </button>
            </div>
            <div class="text-center py-4 text-secondary bg-white rounded border-2 border-dashed"
              style="font-size: .97em;">
              첨부된 파일이 없습니다.
            </div>
          </div>
        </div>

        <!-- 하단 버튼 -->
        <div class="modal-footer bg-white">
          <button id="btnSaveApplication" class="btn w-100 btn_violet justify-content-center">지원</button>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>
