<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0" />
	<title>연봉 정보 목록</title>
	<script src="/js/member/community/companySalary/companySalaryList.js"></script>
	<style>
	
	select:focus,select:focus-visible { border-color: var(--violet70); box-shadow: 0 0 0 2px rgba(124,58,237,0.2); outline: none; border-radius:5px !important; outline:none; border:none; }
	select:hover { border-color: var(--violet70); }
	
	/* 검색창 */
	.TypoBox.searchBar{width:449px !important;}
	
	
	.autocomplete-list {
	    position: absolute; top: 100%; left: 0; right: 0;
	    background: #fff; border: var(--border); border-top: none;
	    border-radius: 0 0 var(--radius) var(--radius);
	    max-height: 200px; overflow-y: auto; z-index: 10; list-style: none;
	}
	.autocomplete-list li { padding: .5rem 1rem; cursor: pointer; }
	.autocomplete-list li:hover { background: var(--gray100); }
	
	.salary-bg{
		background: rgb(239 229 255 / 0.5);
    padding: 50px 0 0;
    margin-bottom: 30px;
   }
	/* 연봉 슬라이더 */
	.salary-filter {
		display:flex;
		flex-wrap:wrap;
	  margin-bottom: 40px;
	  gap:50px;
	  background:#fff;
	  padding:30px;
	  border-radius:10px;
	}
	.salary-filter > div{width:calc((100% - 50px) / 2);}
	
	.salary-filter label { font-weight: bold; display: block; margin-bottom: 10px; }
	.salary-controls { display: flex; align-items: center; gap: 3rem; position: relative; }
	.slider-container { position: relative; height: 16px; margin:0 12px; width:calc(100% - 647px);}
	.slider-track { position: absolute; top: 50%; left: 0; right: 0; height: 4px; background: var(--gray200); transform: translateY(-50%); border-radius: 2px; }
	.slider-range { position: absolute; top: 50%; height: 4px; background: var(--violet70); transform: translateY(-50%); border-radius: 2px; }
	.handle { position: absolute; top: 50%; width: 24px; height: 24px; background: #fff; border: 2px solid var(--violet70); border-radius: 50%; transform: translate(-50%, -50%); cursor: grab; }
	.handle span { position: absolute; bottom:-34px; left: 50%; transform: translateX(-50%);  padding: 4px 13px; font-size: 11px; white-space: nowrap; color:var(--violet90); border:1px solid var(--violet40)}
	.slider-select-wrap{width:575px;}
	
	
	/* 리스트 */
	.company-count{border-bottom:1px solid #ddd;}
	.list { display:flex; flex-wrap:wrap; gap:20px;}
	.item { display: flex; justify-content: space-between; align-items: center; padding: 3rem 0; border: 1px solid #ddd; padding:20px; border-radius:10px; cursor: pointer; flex-basis:calc((100% - 20px) / 2); flex-grow:1;}
	.info { display: flex; align-items: center; gap: 1rem; }
	.logo { width: 48px; height: 48px; object-fit: contain; background: #eee; }
	.text { display: flex; flex-direction: column; }
	.name { font-weight: bold; }
	.meta { font-size: .9rem; color: var(--gray500); }
	.salary { }
	.no-item { padding: 1rem; text-align: center; color: var(--gray500); font-size: 0.95rem; }
	
	</style>
</head>
<body class="n_inner">
	
	 <h1 class="h1 mb-3 fw-bold  mw-1260 mx-auto">연봉정보</h1>
   <!-- 동적 타이틀 -->   	
   	<div class="salary-bg">
			<div class="salary-filter mw-1260 mx-auto">
		      
		    <!-- 기업 규모 -->
				<div class="filter-group">
		    	<label>기업 규모</label>
					<div class="d-flex gap-3">
			    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1 fw-semibold">고등학교</button>
			    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1 fw-semibold">대학 2,3년제</button>
			    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1 fw-semibold">대학 4년제</button>
			    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1 fw-semibold">대학원</button>
			    </div>
			  </div>
					
					
		      <!-- 업종 전체 -->
		      <div>		      	
		      	<label>업종</label>
		      <select id="industry-select" class="Select_root Select_size38 w100p fs-14 ps-2 h48" name="selectName">
		          <option value="">업종 전체</option>
		          <option value="it">IT/인터넷</option>
		          <option value="finance">금융</option>
		          <option value="manufacturing">제조업</option>
		          <option value="service">서비스업</option>
		      </select>
		      </div>
		      
		      
		  	<!-- 연봉 슬라이더 -->
		  	<div class="w100p"> 
		       <label>연봉</label>
		       <div class="salary-controls">
		           <div class="slider-container">
		               <div class="slider-track"></div>
		               <div class="slider-range" id="slider-range"></div>
		               <div class="handle" id="min-handle"><span id="min-label" class="badge-tag">0만원</span></div>
		               <div class="handle" id="max-handle"><span id="max-label" class="badge-tag">1억원↑</span></div>
		           </div>
		       </div>
		     </div>
		   </div>
	   </div>
   
			<div class="d-flex justify-content-between align-items-end border-bottom pb-2 mb-4 mw-1260 mx-auto">
				<div id="result_total_count"></div>
				<div class="d-flex justify-content-end gap-3">
					<!-- 정렬순서 -->
		      <select id="sort-select" class="Select_root Select_size38 w140 fs-14 ps-2 h48" name="selectName">
		          <option value="default">회사명순</option>
		          <option value="high">연봉 높은순</option>
		          <option value="low">연봉 낮은순</option>
		      </select>	
		      
					<!-- 검색창 -->
					<div class="TypoBox searchBar ">
						<div class="searchBarWrap border">
							<label class="searchBarLabel" for="listKeyword">검색어</label>
							<input type="text" id=search-input class="searchBarInput" placeholder="기업명으로 검색" autocomplete="off" value="">
			          <ul id="autocomplete-list" class="autocomplete-list"></ul>
						</div>
						
						<a href="javascript:void(0)" class="searchBarBtn">
							<span class="material-symbols-outlined">search</span>
						</a>
					</div>
				</div>
			</div>

   <!-- 결과 리스트 -->
   <ul id="salary-list" class="list mw-1260 mx-auto"></ul>
   <!-- 페이징 -->
   <div class="PageBox   mw-1260 mx-auto"></div>
</body>
