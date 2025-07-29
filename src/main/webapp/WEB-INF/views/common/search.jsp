<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


  <head>
    <title>공고 검색 리스트</title>
    <link rel="stylesheet" href="/dist/assets/css/search_panel.css">
    <script defer src="/js/common/searchNotice.js"></script>
  </head>

  <body>

    <div id="sri_section" class="layout_wide  has_banner">
      <div id="sri_wrap">
        <div id="content">

          <h1>
            <div class="newcomer_sub_title">신입 연봉 공고</div>
          </h1>

          <!-- ★★★★★★★★★★★★ 일반 게시글은 여기서부터 참고 ★★★★★★★★★★★★ -->
          <div class="common_recruilt_list">
            <!-- 필터링 영역 start -->
            <div class="list_info">
              <div class="area_title list_total_count">
                <h2>신입 연봉 채용정보</h2>
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