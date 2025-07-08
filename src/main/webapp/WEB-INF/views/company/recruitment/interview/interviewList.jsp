<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
	<meta charset="UTF-8">
	<title>면접 목록</title>
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css">
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css">
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
	<script defer src="/js/company/recruitment/interview/interviewList.js"></script>
</head>
<body>
	<p class="h1 mb-3 fw-bold">면접 목록</p>
	<div
		class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="list-size">0</span> 건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label> <input
					type="text" id="listKeyword" class="searchBarInput"
					placeholder="면접 제목으로 검색하세요." maxlength="24"
					autocomplete="off" class="SearchInput_SearchInput__input__Cg7QD"
					value="">
			</div>
			<button type="button" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>

	<div class="Post_post">
		<div class="PostList_postList">
			<ul id='interviewList'>
			
				<div class="p-6 d-flex flex-column align-items-center gap-2">
					<span>띹잡에 등록된 면접이 없어요!</span>
				</div>
				
			</ul>
		</div>
	</div>

	<!-- 수정 확인 모달 -->
	<div class="modal fade" id="editInterviewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-primary" id="exampleModalLabel">
						<i class="bi bi-pencil-square me-1"></i> 면접 정보 수정
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
						면접 정보를 <span style="color:#0d6efd;">수정</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.1rem;">
						변경한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary px-4" id="edit-interview-confirm-btn">수정하기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteInterviewModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 면접 정보 삭제
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
						해당 면접 정보를 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.08rem;">
						삭제된 데이터는 복구할 수 없습니다.<br>
						실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4">삭제하기</button>
				</div>
			</div>
		</div>
	</div>

</body>