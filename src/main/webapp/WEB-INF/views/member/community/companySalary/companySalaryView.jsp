<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0" />
	<title>연봉 정보 목록</title>
	<script src="/js/member/community/companySalary/companySalaryList.js"></script>
	<style>
	:root {
	    --violet40: #ede9fe;
	    --violet70: #7c3aed;
	    --gray50: #fafafa;
	    --gray100: #f5f5f5;
	    --gray200: #e5e5e5;
	    --gray500: #6b7280;
	    --gray700: #374151;
	    --border: 1px solid #d1d5db;
	    --radius: 8px;
	    --shadow: 0 2px 6px rgba(0,0,0,0.05);
	}
	
	
	
	/* select 공통 */
	select {
	    appearance: none;
	    -webkit-appearance: none;
	    -moz-appearance: none;
	    padding: .75rem 2.5rem .75rem 1rem;
	    border: var(--border);
	    border-radius: var(--radius);
	    background: #fff
	        url("data:image/svg+xml;charset=UTF-8,%3Csvg fill='%236b7280' height='20' viewBox='0 0 24 24' width='20' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M7 10l5 5 5-5z'/%3E%3C/svg%3E")
	        no-repeat right 0.75rem center;
	    background-size: 1rem;
	    font-size: 1rem;
	    color: var(--gray700);
	    height: 48px;
	    cursor: pointer;
	    min-width: 150px;
	    transition: border-color 0.2s, box-shadow 0.2s;
	}
	select:focus,select:focus-visible { border-color: var(--violet70); box-shadow: 0 0 0 2px rgba(124,58,237,0.2); outline: none; border-radius:5px !important; outline:none; border:none; }
	select:hover { border-color: var(--violet70); }
	
	/* 검색창 */
	.search-box { position: relative; flex: 1; min-width: 250px; }
	.search-box input {
	    width: 100%; padding: .75rem 1rem; border: var(--border); border-radius: var(--radius);
	    font-size: 1rem; height: 48px;
	}
	.autocomplete-list {
	    position: absolute; top: 100%; left: 0; right: 0;
	    background: #fff; border: var(--border); border-top: none;
	    border-radius: 0 0 var(--radius) var(--radius);
	    max-height: 200px; overflow-y: auto; z-index: 10; list-style: none;
	}
	.autocomplete-list li { padding: .5rem 1rem; cursor: pointer; }
	.autocomplete-list li:hover { background: var(--gray100); }
	
	/* 연봉 슬라이더 */
	.salary-filter {
		display:flex;
		flex-wrap:wrap;
	  margin-bottom: 2rem;
	  gap:35px;
	}
	.salary-filter > div{width:calc((100% - 35px) / 2);}
	
	.salary-filter label { font-weight: bold; display: block; margin-bottom: 10px; }
	.salary-controls { display: flex; align-items: center; gap: 3rem; position: relative; }
	.slider-container {width:calc(100% - 24px); flex: 1; position: relative; height: 16px; margin:0 12px;}
	.slider-track { position: absolute; top: 50%; left: 0; right: 0; height: 4px; background: var(--gray200); transform: translateY(-50%); border-radius: 2px; }
	.slider-range { position: absolute; top: 50%; height: 4px; background: var(--violet70); transform: translateY(-50%); border-radius: 2px; }
	.handle { position: absolute; top: 50%; width: 24px; height: 24px; background: #fff; border: 2px solid var(--violet70); border-radius: 50%; transform: translate(-50%, -50%); cursor: grab; }
	.handle span { position: absolute; bottom:-34px; left: 50%; transform: translateX(-50%);  padding: 4px 13px; font-size: 11px; white-space: nowrap; color:var(--violet90); border:1px solid var(--violet40)}
	
	/* 리스트 */
	.list { list-style: none; margin: 0; padding: 0; }
	.item { display: flex; justify-content: space-between; align-items: center; padding: 3rem 0; border-bottom: var(--border); cursor: pointer; }
	.info { display: flex; align-items: center; gap: 1rem; }
	.logo { width: 48px; height: 48px; object-fit: contain; background: #eee; }
	.text { display: flex; flex-direction: column; }
	.name { font-weight: bold; }
	.meta { font-size: .9rem; color: var(--gray500); }
	.salary { font-weight: bold; }
	.no-item { padding: 1rem; text-align: center; color: var(--gray500); font-size: 0.95rem; }
	
	</style>
</head>
<body>
	
	 <h1 class="h1 mb-3 fw-bold">연봉정보</h1>
   <!-- 동적 타이틀 -->
   <div class="fs-14 mb-5" id="salary-title">0개 기업의 연봉이 등록되어 있습니다.</div>
   	
		<div class="salary-filter w100p">
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
	           <div class="d-flex justify-content-between align-items-center gap-3">
		           <select id="min-select" class="Select_root Select_size38 ps-2 fs-14">
		               <option value="0">0만원 ~</option>
		               <option value="1000">1,000만원</option>
		               <option value="2000">2,000만원</option>
		               <option value="3000">3,000만원</option>
		           </select>
		           <span class="fs-14 fw-semibold"> ~ </span>
		           <select id="max-select" class="Select_root Select_size38 ps-2 fs-14">
		               <option value="10000">1억원 이상</option>
		               <option value="9000">9,000만원</option>
		               <option value="8000">8,000만원</option>
		           </select>
	           </div>
	       </div>
	     </div>
	      
	    <!-- 기업 규모 -->
			<div class="filter-group">
	    	<label>기업 규모</label>
				<div class="d-flex gap-3">
		    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1">고등학교</button>
		    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1">대학 2,3년제</button>
		    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1">대학 4년제</button>
		    	<button class="border rounded px-4 py-3 bg-white fs-14 lh1">대학원</button>
		    </div>
		  </div>
				
				
	      <!-- 업종 전체 -->
	      <div>		      	
	      	<label>기업 규모</label>
	      <select id="industry-select" class="Select_root Select_size38 w100p fs-14 ps-2" name="selectName">
	          <option value="">업종 전체</option>
	          <option value="it">IT/인터넷</option>
	          <option value="finance">금융</option>
	          <option value="manufacturing">제조업</option>
	          <option value="service">서비스업</option>
	      </select>
	      </div>
	   </div>
   
			<div class="d-flex justify-content-end gap-2">
				<!-- 정렬순서 -->
	      <select id="sort-select" class="w140 fs-14 ps-2" name="selectName">
	          <option value="default">회사명순</option>
	          <option value="high">연봉 높은순</option>
	          <option value="low">연봉 낮은순</option>
	      </select>	
	      
				<!-- 검색창 -->
				<div class="TypoBox searchBar">
					<div class="searchBarWrap">
						<label class="searchBarLabel" for="listKeyword">검색어</label>
						<input type="text" id=search-input class="searchBarInput" placeholder="기업명으로 검색" autocomplete="off" value="">
		          <ul id="autocomplete-list" class="autocomplete-list"></ul>
					</div>
					
					<a href="javascript:void(0)" class="searchBarBtn">
						<span class="material-symbols-outlined">search</span>
					</a>
				</div>
			</div>

   <!-- 결과 리스트 -->
   <ul id="salary-list" class="list"></ul>
   <!-- 페이징 -->
   <div class="PageBox" style="text-align:center; margin:2rem 0;"></div>
</body>
