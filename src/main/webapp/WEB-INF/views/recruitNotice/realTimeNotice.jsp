<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <title>채용공고 리스트</title>
    <link rel="stylesheet" href="/dist/assets/css/search_panel.css">
    <script defer src="/js/recruitNotice/realTimeNotice.js"></script>
  </head>

  <body>

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

                </ul>
                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span><span
                  class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
              </div>
            </div>
          </div>
          <!-- end 상단 공고 slide -->

          <!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->
          <div class="common_recruilt_list">
            <!-- 필터링 영역 start -->
            <div class="list_info">
              <div class="area_title list_total_count">
                <h2>전체 채용정보</h2>
                <span class="total_count"><em id="notice_cnt"></em>건</span>
              </div>
              <div class="list_select">
                <div class="InpBox">
                  <select name="page_count" id="page_count">
                    <option value="10">10개씩</option>
                    <option value="20" selected>20개씩</option>
                    <option value="30">30개씩</option>
                  </select>
                </div>
                <div class="InpBox">
                  <select name="sort" id="sort">
                    <option value="RL">스크랩순</option>
                    <option value="RD" selected="">최신순</option>
                    <option value="EA">마감순</option>
                    <option value="RE">조회순</option>
                  </select>
                </div>
              </div>
            </div>
            <!-- 필터링 영역 End  -->

            <div id="default_list_wrap" style="position: relative">
              <section class="list_recruiting">
                <h2 class="blind">공고리스트</h2>

                <!-- list 영역 -->
                <div class="list_body">
                </div>
              </section>

              <!-- pager 영역 -->
              <div class="PageBox">

              </div>
              <!-- end pager 영역 -->
            </div>

          </div>
          <!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->
        </div>
      </div>


  </body>

  </html>