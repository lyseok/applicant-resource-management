<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <title>채용 단계별 지원자 관리</title>
    <script>
  		const recruitmentNo = "${recruitmentNo}";
		</script>
    <script defer src="/js/company/recruitment/applicantRecord/applicantRecordList.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.css">
    <script src="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.js"></script>
    <style>

th, td {
	padding: 4px 6px !important;
}

.table{border-radius:10px;}
.table > :not(:first-child){border-top-width:1px;}
.table th{background:var(--violet10);}

.form-label{
	font-size:14px;
	font-weight: 600;
	margin-bottom: 10px;
	color: #333;
}
/* 경력 슬라이더 noUiSlider 커스텀 */
#careerSlider .noUi-base {
	height: 6px;
	/* 더 얇게 */
	background: #e9ecef;
	width:calc(100% - 8px);
}
.noUi-target{
	background:none;
	border:none;
	box-shadow:none;
}
#careerSlider .noUi-connect {
 position: absolute;
  top: 50%;
  transform: translateY(-50%);
  height: 2px;
  background-color: var(--violet70);
}

#careerSlider .noUi-handle {
	width: 16px;
	height: 16px;
	border-radius: 50%;
	background: var(--violet70);
	border: none;
	box-shadow: none;
	top: -50%;
	cursor: grab;
	transition: border 0.2s;
  transform: translateY(-5px);
}
#careerSlider .noUi-handle-upper{
	transform: translate(-100%, -50%);
}
.noUi-handle:after, .noUi-handle:before{
	display:none;
}
#careerSlider .noUi-handle:hover {
	border: 2.5px solid #296ed7;
}
#careerSlider .noUi-tooltip {
	font-size: 12px;
	border-radius: 4px;
	padding: 1px 7px;
	bottom:-30px
}
#careerSlider .noUi-value {
	font-size: 12px;
	color: #296ed7;
	font-weight: 600;
	top: -22px;
	/* 필요시 조절 */
}


.nav-tabs .nav-item .nav-link {
  border-radius: 20px;
  border: 1px solid #ddd;
  font-size:14px;
}
.nav-tabs .nav-item .nav-link:not(.active){
	background:#fff;
	
}
.nav-tabs .nav-item .nav-link.active{
	color:var(--violet80);
	border:1px solid var(--violet80);
  font-weight: 600;
}
.w_calc100_m32_3{
	width:calc((100% - 32px) / 3);
}

.modal.show {
  display: block;
  z-index: 2000 !important;
}

.modal-backdrop.show {
  z-index: 1990 !important;
}
</style>
  </head>

  <body>
      <p class="h1 mb-4 fw-bold">채용 단계별 지원자 관리</p>

      <!-- 필터 영역 -->
      <!-- 필터 1줄차: 경력/어학/전공/자격증 -->          
	    <div class="d-flex gap-3">
	     <!-- 어학 -->
	     <div class="w_calc100_m32_3">
	       <label for="languageFilter" class="form-label mb-0 me-2">어학</label>
	       <select class="form-select fs-14" id="languageFilter"></select>
	     </div>
	     <!-- 전공 -->
	     <div class="w_calc100_m32_3">
	       <label for="majorFilter" class="form-label mb-0 me-2">전공</label>
	       <select class="form-select fs-14" id="majorFilter"></select>
	     </div>
	     <!-- 자격증 -->
	     <div class="w_calc100_m32_3">
	       <label for="certFilter" class="form-label mb-0 me-2">자격증</label>
	       <select class="form-select fs-14" id="certFilter"></select>
	     </div>
	    </div>

			<div class="d-flex gap-3 mt-3 mb-5">    
	      <!-- 진행상태  -->
	      <div class="w_calc100_m32_3">
	        <label class="form-label">진행상태</label>	    
	        <ul class="nav nav-tabs border-0 gap-2"></ul>
	      </div>
	      <!-- 보유기술 -->
	      <div class="w_calc100_m32_3">
	        <label class="form-label">보유기술</label>
	        <div id="skillTagFilter" class="d-flex flex-wrap gap-1">
	          <!-- JS에서 태그형 생성 -->
	        </div>
	      </div>
	      
	      <!-- 경력(년) + 슬라이더 한 줄 -->
	      <div class="w_calc100_m32_3">
	        <label class="form-label">경력(년)</label>
	        <div id="careerSlider"></div>
	        <div id="careerRangeText" style="font-weight:500; color:#296ed7; font-size:12px; margin-bottom:0; margin-left:6px;"></div>
	      </div>
      </div>
      

      <!-- 정렬 + 상태변경 -->
      <div class="d-flex mb-2 align-items-center justify-content-between">
	      <div class="d-flex gap-1 flex-column align-items-start" id="stepActionContainer">
	        <button class="btn btn_violet fs-14" id="stepActionBtn" onclick="closeStep()">단계 변경</button>
	        <span class="fs-14 fw-500 text-muted">합격선택 체크 후 단계변경 버튼 클릭 시 다음 단계로 넘어갑니다.</span>
	      </div>
	      
      	<div class="d-flex gap-2">
	        <div class="d-flex flex-wrap gap-1">
	        	<select id="sortSelect" class="form-select form-select-sm w-auto" onchange="sortTable(this.value)">
						  <option value="">정렬 선택</option>
						  <option value="career">경력순</option>
						  <option value="score">점수순</option>
						</select>
							        	
		        <!-- <button class="btn btn-outline-secondary btn-sm" onclick="sortTable('career')">경력순</button>
		        <button class="btn btn-outline-secondary btn-sm" onclick="sortTable('score')">점수순</button> -->
	        </div>
	      	<!-- 3번째 줄: 검색 -->
	      	<div class="d-flex justify-content-end gap-5">
						<div class="TypoBox searchBar searchBar2 border border-dark">
			        <div class="searchBarWrap">
			          <label class="searchBarLabel" for="listKeyword">검색어</label>
			          <input type="text" class="searchBarInput" id="searchInput" placeholder="지원자 이름 검색">
			        </div>
			
			        <div class="btn_box">
			          <a class="searchBarBtn" id="searchBtn" type="button">
			            <span class="material-symbols-outlined">search</span>
			          </a>
			          <button class="" id="resetFilters" type="button">
			            <span class="material-symbols-outlined fw-300">close</span>
			          </button>
			        </div>
		        </div>	              
		    	</div>
		    	</div>
		        
      </div>


      <!-- 테이블 -->
      <div class="tab-pane fade show active" id="step1" role="tabpanel">
        <table class="table align-middle text-center table-layout-fixed fs-14" id="mainTable">
        	<colgroup>
        		<col width="70px">
        		<col width="160px">
        		<col width="53px">
        		<col width="150px">
        		<col width="auto">
        		<col width="auto">
        		<col width="70px">
        		<col width="70px">
        		<col width="130px">
        		
        	</colgroup>
          <thead>
            <tr></tr> <!-- JS에서 컬럼 삽입 -->
          </thead>
          <tbody>
            <!-- JS에서 데이터 렌더링 -->
          </tbody>
        </table>
      </div>
    
    <!-- 모달: 입사예정일 변경 -->
	<div class="modal fade" id="hireDateModal" tabindex="-1" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header border-0">
	        <h5 class="modal-title fs-5 fw-bold text-success">입사 예정일 변경</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	      <div class="modal-body  text-center py-4">
	        <input type="date" id="hireDatePicker" class="form-control" />
	      </div>
	      <div class="modal-footer border-0 justify-content-center">
	        <button type="button" class="btn btn-secondarybtn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn_violet px-4" id="confirmHireDateBtn">확인</button>
	      </div>
	    </div>
	  </div>
   
  </body>