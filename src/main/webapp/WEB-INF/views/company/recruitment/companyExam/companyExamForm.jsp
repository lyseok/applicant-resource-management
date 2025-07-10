<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script defer src = "/js/company/recruitment/companyExam/createCompanyExam.js"></script>

  <style>
    /* 전체 컨테이너 */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f5f7fa;
      color: #333;
      padding: 24px;
    }
    .container {
      max-width: 800px;
      margin: 0 auto;
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.05);
      padding: 32px;
    }

    /* 제목 */
    h1 {
      margin-bottom: 24px;
      font-size: 1.8rem;
      text-align: center;
      color: #4a4a4a;
    }

    /* 카드 스타일 */
    .card {
      background: #fff;
      border: 1px solid #e0e0e0;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.05);
      padding: 16px;
      margin-bottom: 24px;
    }

    /* 레이블 + 입력 */
    .card label {
      display: flex;
      flex-direction: column;
      font-weight: 500;
      margin-bottom: 12px;
    }
    .card input[type="text"],
    .card textarea {
      margin-top: 6px;
      padding: 8px 12px;
      border: 1px solid #ccc;
      border-radius: 6px;
      resize: vertical;
      font-size: 1rem;
      width: 100%;
      box-sizing: border-box;
    }
    .card textarea {
      min-height: 80px;
    }

    /* 문항 블록 */
    .option-row {
      display: flex;
      align-items: center;
      gap: 12px;
      margin-top: 8px;
    }
    .option-row input[type="text"] {
      flex: 1;
    }
    .option-row label {
      font-size: 0.9rem;
    }

    /* 버튼 공통 */
    .btn {
      background-color: #7c3aed;
      color: #fff;
      border: none;
      border-radius: 6px;
      padding: 10px 20px;
      font-size: 1rem;
      cursor: pointer;
      transition: background-color 0.2s, transform 0.1s;
      margin-right: 8px;
    }
    .btn:hover {
      background-color: #6b21a8;
      transform: translateY(-1px);
    }
    .btn:active {
      transform: translateY(0);
    }

    /* 보조 버튼 (문항/보기 추가) */
    .btn-secondary {
      background-color: #4a5568;
    }
    .btn-secondary:hover {
      background-color: #2d3748;
    }

    /* 컨트롤 그룹 */
    .actions {
      text-align: center;
      margin-top: 32px;
    }
    
    
    .delete-question-btn,
	.delete-opt-btn {
  background: transparent;
  border: none;
  color: #e53e3e;
  font-size: 1.2rem;
  line-height: 1;
  cursor: pointer;
  padding: 0 0.5rem;
}

/* hover 시 색 조금 진하게 */
.delete-question-btn:hover,
.delete-opt-btn:hover {
  color: #c53030;
}
  </style>

</head>
<body>
	<div  id="container" class="container" data-exam-id="${examNo}" >
		    <h1> ${empty examNo ? '시험 생성' : '시험 수정'} </h1>
		    <div class="card">
		      <label>
		        시험명
		        <input type="text" id="comExamName" placeholder="시험명을 입력하세요" />
		        <span id="comExamNameError" class="text-danger small"></span>
		      </label>
		    </div>
		
		    <div id="questionContainer"></div>
		 
		    <div class="actions">
		      <button id="addQuestionBtn" class="btn-secondary btn">+ 문제 추가</button>
		    </div>
		
		    <div class="actions">
		      <button id="submitAllBtn" class="btn">${empty examNo ? '시험 생성' : '시험 수정'} </button>
		      <button id="exitBtn" class="btn">목록</button>
		    </div>
	 </div>
	 
	 
	 
	 
	 
	<!-- 시험 수정 확인 모달 -->
	<div class="modal fade" id="examEditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-primary" id="exampleModalLabel">
						<i class="bi bi-pencil-square me-1"></i> 시험 수정
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
						시함 <span style="color:#0d6efd;">수정</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.1rem;">
						변경한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary px-4">수정하기</button>
				</div>
			</div>
	</div>
	
	
	
	<!-- 추가 확인 모달 -->
<div class="modal fade" id="addExamModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header border-0">
                <h1 class="modal-title fs-5 fw-bold text-success" id="addModalLabel">
                    <i class="bi bi-plus-circle-fill me-1"></i> 시험 추가
                </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center py-4">
                <div style="font-size:2.4rem; color:#28a745; margin-bottom:10px;">🟢</div>
                <p class="fs-5 mb-2 fw-semibold" style="color:#218838;">
                    새로운 시험 <span style="color:#28a745;">추가</span>하시겠습니까?
                </p>
                <p class="text-secondary mb-0" style="font-size:1.08rem;">
                    입력한 정보가 등록됩니다.<br>
                    실행 전 내용을 다시 한 번 확인해 주세요.
                </p>
            </div>
            <div class="modal-footer border-0 justify-content-center">
                <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-success px-4">추가하기</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>