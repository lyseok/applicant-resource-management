<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="jakarta.tags.core" prefix="c" %>
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

			<head>
				<title>띹잡 고객센터 | 게시글 상세 입력</title>
				<script src="/js/admin/community/aboardForm.js"></script>
			</head>

			<body>

				<p class="h4">관리자 게시글 입력 폼</p><br>

				<!-- 등록 -->
				<c:set var="actionPath" value="/admin/community/adminBoard/aboardForm/insert" />
				<!-- 수정 -->
				<c:if test="${not empty aboard.boardNo}">
					<c:set var="actionPath" value="/admin/community/adminBoard/aboardForm/update" />
				</c:if>
				<!-- 등록 수정 둘다 여기로 옴 -->
				<form:form action="${actionPath}" method="post" modelAttribute="aboard">

					<!-- 게시판 유형 선택 -->
					<div type="hidden" name="boardTypeCode" value="${aboard.boardTypeCode}">
						<%-- 유형이 이미 있으면 그 값이 들어가게 --%>
							<div class="input-group mb-3">
								<label class="input-group-text">게시판 유형</label>
								<select class="form-select" id="aboardTC">
									<option selected value="">-- 선택 --</option>
									<option value="BRDD-002">자주 묻는 질문</option>
									<option value="BRDD-003">공지사항</option>
									<option value="BRDD-001">문의사항</option>
								</select>
							</div>

							<div class="input-group mb-3">
								<label class="input-group-text">회원 유형</label>
								<select class="form-select" id="faqTC" disabled>
									<option selected value="">-- 선택 --</option>
									<option value="UFAQ">일반회원</option>
									<option value="CFAQ">기업회원</option>
								</select>
							</div>

							<div class="input-group mb-3">
								<label class="input-group-text">질문 유형</label>
								<select class="form-select" id="subTC" name="boardTypeCode" disabled>
									<option selected value="">-- 선택 --</option>
								</select>
							</div>
					</div>

					<%-- 화면엔 없지만 값이 넘어올 부분 --%>
						<input type="hidden" name="boardNo" value="${aboard.boardNo}">
						<input type="hidden" name="userId" value="${aboard.userId}">
						<input type="hidden" name="boardWriteDate" value="${aboard.boardWriteDate}">
						<input type="hidden" name="boardDeleteDate" value="${aboard.boardDeleteDate}">
						<input type="hidden" name="boardPostHit" value="${aboard.boardPostHit}">
						<input type="hidden" name="boardStatus" value="${aboard.boardStatus}">

						<!-- 제목 -->
						<div class="mb-3">
							<label for="aboardTitle" class="form-label">제목</label>
							<input type="text" class="form-control" id="aboardTitle" data-field="boardTitle"
								placeholder="제목을 이곳에 입력" path="boardTitle" name="boardTitle"
								value="${aboard.boardTitle}" />
							<div class="invalid-feedback">

							</div>
						</div>

						<!-- 내용 -->
						<div class="mb-3">
							<label for="aboardContent" class="form-label">내용</label>
							<textarea class="form-control" id="aboardContent" data-field="boardContent" rows="6"
								cols="60" placeholder="내용을 이곳에 입력" path="boardContent"
								name="boardContent">${aboard.boardContent}</textarea>
							<div class="invalid-feedback">

							</div>
						</div>

						<!-- 등록 확인 모달 -->
						<div class='btn btn_gray_line' data-bs-toggle="modal" data-bs-target="#adabFM">등록</div>
						<div class="modal fade" id="adabFM" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header border-0">
										<h1 class="modal-title fs-5 fw-bold text-primary" id="exampleModalLabel">
											<i class="bi bi-pencil-square me-1"></i> 관리자 게시글 등록
										</h1>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body text-center py-4">
										<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
										<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
											${aboard.codeName }를(을) <span style="color:#0d6efd;">등록</span>하시겠습니까?
										</p>
										<p class="text-secondary mb-0" style="font-size:1.1rem;">
											등록한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
										</p>
									</div>
									<div class="modal-footer border-0 justify-content-center">
										<button type="button" class="btn btn-outline-secondary px-4"
											data-bs-dismiss="modal">취소</button>
										<!-- 등록 버튼 -->
										<button type="button" class="btn btn-primary px-4" id="aboardSV">등록하기</button>
									</div>
								</div>
							</div>
						</div>

				</form:form>

			</body>