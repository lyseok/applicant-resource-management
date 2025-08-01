<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8">
	<title>면접 관리</title>
	<script defer src="/js/member/recruitment/interview/interviewList.js" ></script>
	<style>
		li[data-notice]:hover {
			cursor: pointer;
			transition: background 0.2s, box-shadow 0.2s;
		}

        /* 부모 박스의 너비 지정(최대 버튼 길이만큼) */
        .btn-box {
        width: 230px;  /* 필요에 따라 200~250px 조절 */
        }

        .w-review {
            width: 100%;
            min-width: 120px;
            max-width: 230px;
            text-align: center;
            margin: 0;
            box-sizing: border-box;
            /* ↓↓↓ 이 두 줄 추가! */
            display: flex;
            align-items: center;
            justify-content: center;
            /* ↓↓↓ 이 줄로 버튼 세로높이 고정시 부드럽게 가운데 정렬됨 */
        }
	</style>
</head>

<body>
	<p class="h1 mb-3 fw-bold">면접 관리</p>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="list-count" class="fw-bold"></span>건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="기업명으로 검색하세요" maxlength="24" autocomplete="off" value="">
			</div>
			<button type="button" class="searchBarBtn" id="searchBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>
	
	<!-- 리스트 렌더링 영역 -->
	<div id="interviewListArea"></div>

	<!-- 페이지네이션 렌더링 영역 -->
	<div id="pageBox" class="PageBox"></div>

<!-- 화상채팅 접속 확인 모달 -->
<div class="modal fade custom_confirm_modal" id="joinInterviewModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header border-0">
                <h1 class="modal-title fs-5 fw-bold text-success" id="addModalLabel">
                    <i class="bi bi-plus-circle-fill me-1"></i> 화상 면접 접속
                </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center py-4">
                <div style="font-size:2.4rem; color: var(--violet80); margin-bottom:10px;">🟪</div>
                <p class="fs-5 mb-2 fw-semibold" style="color:var(--violet80);">
                    화상 면접 방에 <span style="color:var(--violet110)">입장</span>하시겠습니까?
                </p>
                <p class="text-secondary mb-0" style="font-size:1.08rem;">
                    생성된 화상면접 방으로 이동합니다.<br>
                    실행 전 정보를 확인해 주세요.
                </p>
            </div>
            <div class="modal-footer border-0 justify-content-center">
                <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn_violet px-4" id="startInterviewBtn">접속</button>
            </div>
        </div>
    </div>
</div>

<div id="interviewAlert" class="alert alert-warning alert-dismissible fade show"
    role="alert" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%,-50%); z-index:20000; min-width:260px;">
  <span id="interviewAlertMsg"></span>
  <button type="button" class="btn-close" onclick="document.getElementById('interviewAlert').style.display='none'"></button>
</div>


<!-- 리뷰 작성 이동 확인 모달 -->
<div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header border-0">
				<h1 class="modal-title fs-5 fw-bold text-success" id="reviewModalLabel">
					<i class="bi bi-pencil-square me-1"></i> 면접 리뷰 
				</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body text-center py-4">
				<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
				<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
					면접 리뷰를  <span style="color:var(--violet80);">작성 </span>하시겠습니까?
				</p>
				<p class="text-secondary mb-0" style="font-size:1.1rem;">
					작성한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
				</p>
			</div>
			<div class="modal-footer border-0 justify-content-center">
				<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
				<button type="button" class="btn btn_violet px-4" id="writeReviewBtn">작성하기</button>
			</div>
		</div>
	</div>
</div>

</body>