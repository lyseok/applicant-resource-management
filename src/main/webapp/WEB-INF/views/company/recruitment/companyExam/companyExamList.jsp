<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	.exam-container {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 16px;
    }
	
	
	    /* 그리드 레이아웃 */
		#examList {
		  display: flex;
		  flex-direction: column;
		  gap: 16px;
		}

	   .exam-card {
	  display: flex;                /* ← 추가 */
	  align-items: center;          /* 세로 가운데 정렬 */
	  width: 100%;
	  box-sizing: border-box;
	  background: #fff;
	  border: 1px solid #e0e0e0;
	  border-radius: 8px;
	  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	  padding: 16px;
	  transition: transform 0.2s, box-shadow 0.2s;
	}
		
	.exam-name {
	  flex: 1;                      /* ← 추가: 남은 공간을 이름이 차지 */
	  font-size: 1.1rem;
	  font-weight: 600;
	  margin-bottom: 0;             /* margin-bottom 대신 간격이 필요하면 padding으로 조절 */
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
    

    /* 시험 건수 */
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
	<div class="exam-container">
	    <div>${user.comName}님이 등록하신 시험입니다.</div>
	    <a href="/company/companyExam/form">
	      <button class="btn_violet">시험 등록하기</button>
	    </a>
   </div>

  <div id="examCount"></div>
  <div id="examList"></div>
	
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
</html>