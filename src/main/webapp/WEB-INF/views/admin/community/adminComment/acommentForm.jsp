<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>띹잡 고객센터 | 댓글 등록</title>
	<script src="/js/admin/community/adminComment/acommentForm.js"></script>
</head>

<body>


<p class="h4">문의사항 답글 입력 폼</p><br>

	<!-- 등록 -->
	<c:set var="actionPath" value="/admin/community/adminComment/acommentForm/insert" />
	<!-- 수정 -->
	<c:if test="${not empty acomment.boardCommentNo}">
		<c:set var="actionPath" value="/admin/community/adminComment/acommentForm/update" />
	</c:if>
	<!-- 등록 수정 둘다 여기로 옴 -->
	<form:form action="${actionPath}" method="post" modelAttribute="acomment">

	<%-- 화면엔 없지만 값이 넘어올 부분 --%>
	<input type="hidden" name="boardCommentNo" value="${acomment.boardCommentNo}">
	<input type="hidden" name="boardNo" value="${acomment.boardNo}">
	<input type="hidden" name="boardWriteDate" value="${acomment.boardWriteDate}">
	<input type="hidden" name="boardDeleteDate" value="${acomment.boardDeleteDate}">
	<input type="hidden" name="boardCommentStatus" value="${acomment.boardCommentStatus}">
	
		<ul>
			<li>
				<div>
					<label>이메일</label>
				</div>
				<div>
					<span value="${acomment.users.memEmail }" readonly="readonly"></span>
					<!-- 기업회원이면 value가 다르게 -->
					<span value="${acomment.users.comEmail }" readonly="readonly"></span>
				</div>
				<div>
					<label>회원</label>
				</div>
				<div>
					<span value="${acomment.userId }" readonly="readonly"></span>
				</div>
				<div>
					<label>회원구분</label>
				</div>
				<div>
					<input type="radio" name="member" value="${acomment.users.userRole }" readonly="readonly" checked>
					개인
					<input type="radio" name="member" value="${acomment.users.userRole }" readonly="readonly" disabled>
					기업
				</div>
				<div>
					<label>회원 문의종류</label>
					<input type="text" value="${acomment.boardCommentContent }" readonly>
					<label>회원 문의내용</label>
					<span value="${acomment.boardCommentContent }" readonly></span>
				</div>
				<div>
					<label>내 문의답글</label>
					<textarea name="boardCommentContent" row="6" cols="60"></textarea>
				</div>
			</li>
		</ul>

	


			<!-- 등록 확인 모달 -->
			<div class='btn btn_gray_line' data-bs-toggle="modal" data-bs-target="#adacFM">등록</div>
			<div class="modal fade" id="adacFM" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header border-0">
							<h1 class="modal-title fs-5 fw-bold text-primary" id="exampleModalLabel">
								<i class="bi bi-pencil-square me-1"></i>문의 등록
							</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body text-center py-4">
							<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
							<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
								문의를 <span style="color:#0d6efd;">등록</span>하시겠습니까?
							</p>
							<p class="text-secondary mb-0" style="font-size:1.1rem;">
								등록한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
							</p>
						</div>
						<div class="modal-footer border-0 justify-content-center">
							<button type="button" class="btn btn-outline-secondary px-4"
								data-bs-dismiss="modal">취소</button>
							<!-- 등록 버튼 -->
							<button type="button" class="btn btn-primary px-4" id="acommentSV">등록하기</button>
						</div>
					</div>
				</div>
			</div>

	</form:form>

</body>