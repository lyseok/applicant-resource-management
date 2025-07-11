<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>띹잡 고객센터 | 댓글 등록</title>
	<script src="/js/member/community/acommentForm.js"></script>
</head>

<body>


<p class="h4">문의사항 답글 입력 폼</p><br>

	<!-- 등록 -->
	<c:set var="actionPath" value="/member/community/adminComment/acommentForm/insert" />
	<!-- 수정 -->
	<c:if test="${not empty acomment.boardCommentNo}">
		<c:set var="actionPath" value="/member/community/adminComment/acommentForm/update" />
	</c:if>
	<!-- 등록 수정 둘다 여기로 옴 -->
	<form:form action="${actionPath}" method="post" modelAttribute="acomment">

	


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
				<label for="macmCT" class="form-label">내 문의답글</label>
				<%-- 문의사항 답글은 제목이 없음, 각 문의 게시글에 내용만 등록 --%>	
				<%-- 회원 본인의 문의에 대한 관리자 답글에 대한 회원의 답글 --%>
				<div class="col-md-6">
				  <textarea class="form-control" id="macmCT" placeholder="문의종류 선택 후 내용을 입력해주세요"
				   data-field="boardCommentContent" rows="6" cols="60" path="boardCommentContent">
				</div>
				<div class="invalid-feedback"></div>
			</li>
		</ul>
	</fieldset>
	  <div class="col-12">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" id="macmChk">
	      <label class="form-check-label" for="gridCheck">
	        개인정보 제공에 동의합니다
	      </label>
	    </div>
	  </div>
	
	</form>	
		


			<!-- 등록 확인 모달 -->
			<div class='btn btn_gray_line' data-bs-toggle="modal" data-bs-target="#madacFM">등록</div>
			<div class="modal fade" id="madacFM" tabindex="-1" aria-labelledby="exampleModalLabel"
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
							<button type="button" class="btn btn-primary px-4" id="macmSV">등록하기</button>
						</div>
					</div>
				</div>
			</div>

	</form:form>

</body>