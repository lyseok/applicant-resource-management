<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<title>시험 관리</title>
	<style>
	
		.exam-container {
	      display: flex;
	      justify-content: space-between;
	      align-items: center;
	      margin-bottom: 16px;
	    }
		
		
		   
			#examList {
			  display: flex;
			  flex-wrap:wrap;
			  gap: 20px;
			}
	
	   .exam-card {
			  display: flex;              
			  align-items: center;          
			  width: calc((100% - 20px) / 2);
			  background: #fff;
			  border: 1px solid #e0e0e0;
			  border-radius: 8px;
			  padding: 16px;
			  cursor:pointer;
			}
			
		.exam-name {
		  flex: 1;                      
		  font-size: 1.1rem;
		  font-weight: 600;
		  margin-bottom: 0;            
		  color: #333;
		}
	    .exam-card:hover {
	      transform: translateY(-4px);
	      box-shadow: 0 4px 8px rgba(0,0,0,0.15);
	    }
	
	  
	    .exam-date {
	      font-size: 0.9rem;
	      color: #666;
	    }
	    
	
	    
	    #examCount {
	      margin-top: 16px;
	      font-size: 1rem;
	      font-weight: bold;
	      color: #444;
	    }
	
	   
	    .btn_violet {
	      background-color: #7c3aed;
	      color: #fff;
	      border: none;
	      border-radius: 6px;
	      padding: 8px 16px;
	      font-size: 0.95rem;
	      cursor: pointer;
	      transition: background-color 0.2s;
	    }
	    .btn_violet:hover {
	      background-color: #6b21a8;
	    }
	    
	    .btn_delete {
	    background-color: #e53e3e;
	    color: #fff;
	    border: none;
	    border-radius: 4px;
	    padding: 4px 8px;
	    font-size: 0.85rem;
	    cursor: pointer;
	    transition: background-color 0.2s;
	  }
	  .btn_delete:hover {
	    background-color: #c53030;
	  }
	  </style>
	
	<script defer src ="/js/company/recruitment/companyExam/companyExamList.js"></script>
</head>
<body>
	<div class="d-flex justify-content-between align-items-center ">
	  <!-- 제목 -->
	  <h2 class="h2 mb-3 fw-bold">시험 목록</h2>
	</div>
	
	<div class="d-flex justify-content-between align-items-end pb-2 border-bottom mb-2">
		<p class="fs-14">
			총 <span id="examCount" class="fw-bold">24</span>건
		</p>
			
		<div class="d-flex gap-2">				
			<!-- 검색창 -->
			<div class="TypoBox searchBar">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="listKeyword">검색어</label>
					<input type="text" id="search-title" class="searchBarInput" placeholder="시험명으로 검색" maxlength="24" autocomplete="off" value="">
				</div>
				
				<a href="javascript:void(0)" class="searchBarBtn">
					<span class="material-symbols-outlined">search</span>
				</a>
			</div>
			
			<!-- 글쓰기 버튼 -->
		  <a href="/company/company_exam/form" class="btn btn_violet">시험 등록</a>
		</div>
	</div>
	

  <div id="examList"></div>
  
  <div class="PageBox">
  	<span class="BtnType SizeS active">1</span>
  	<!-- <button class="BtnType SizeS page" data-page="2">2</button>
  	<button class="BtnType SizeS page" data-page="3">3</button>
  	<button data-page="2" class="BtnType SizeS BtnNext btnNext">다음</button> -->
  </div>
	
 <!-- 삭제 확인 모달 -->
  <div class="modal fade" id="deleteExamModal" tabindex="-1" aria-labelledby="deleteExamLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header border-0">
          <h5 class="modal-title text-danger" id="deleteExamLabel">
            <i class="bi bi-exclamation-triangle-fill me-1"></i> 시험 삭제
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
        </div>
        <div class="modal-body text-center py-4">
          <div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
          <p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
            이 시험을 <span class="text-danger">정말 삭제</span>하시겠습니까?
          </p>
          <p class="text-secondary mb-0" style="font-size:1.08rem;">
            삭제된 데이터는 복구할 수 없습니다.<br>
            실행 전 반드시 다시 한 번 확인해 주세요.
          </p>
        </div>
        <div class="modal-footer border-0 justify-content-center">
          <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
          <button type="button" id="confirmDeleteBtn" class="btn btn-danger px-4">삭제하기</button>
        </div>
      </div>
    </div>
  </div>

</body>