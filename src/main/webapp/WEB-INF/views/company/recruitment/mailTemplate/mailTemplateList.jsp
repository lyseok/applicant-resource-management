<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<title>이력서 관리</title>

		<link rel="stylesheet" href="/css/member/resume/resume.css">
		<script defer src="/js/company/recruitment/mailTemplate/mailTemplateList.js"></script>
	</head>

	<body onbeforeunload="showLoading()">
		<div class="d-flex justify-content-between">
			<p class="h1 mb-3 fw-bold">템플릿 관리</p>
		</div>
		<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
			<p class="fs-14">
				총
				<span id="temCnt"></span>
				건
			</p>

			<button class="btn btn_violet h50" style="display: block;width: 100px;" id="addTemModal">템플릿 추가</button>
		</div>

		<div id="resumeListContainer">
		</div>

		<!-- 템플릿 등록 모달 -->
		<div class="modal fade" id="templateModal" tabindex="-1" aria-labelledby="templateModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title fw-bold" id="templateModalLabel">메일 템플릿 등록</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
					</div>
					<div class="modal-body">
						<form id="templateForm">
							<div class="mb-3">
								<label for="templateTitle" class="form-label fw-semibold">제목</label>
								<input type="text" class="form-control" id="templateTitle" placeholder="템플릿 제목을 입력하세요" required />
							</div>
							<div class="mb-3">
								<label for="templateContent" class="form-label fw-semibold">내용</label>
								<textarea class="form-control" id="templateContent" rows="6" placeholder="템플릿 내용을 입력하세요"
									required></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer justify-content-end">
						<button type="button" class="btn btn_gray_line" data-bs-dismiss="modal">취소</button>
						<button type="submit" class="btn btn_violet_line" form="templateForm">등록</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 템플릿 상세조회 모달 -->
		<div class="modal fade" id="templateDetailModal" tabindex="-1" aria-labelledby="templateDetailModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title fw-bold" id="templateDetailModalLabel">메일 템플릿 상세</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
					</div>
					<div class="modal-body">
						<h4 id="detailTemTitle" class="fw-bold mb-3"></h4>
						<div id="detailTemContent" class="border p-3 rounded" style="min-height: 200px; white-space: pre-wrap;">
						</div>
						<p class="text-muted fs-14 mt-3" id="detailCreateDate"></p>
					</div>
					<div class="modal-footer justify-content-end">
						<button type="button" class="btn btn-outline-secondary" id="editTemplateBtn">수정</button>
						<button type="button" class="btn btn-danger" id="deleteTemplateBtn">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</body>