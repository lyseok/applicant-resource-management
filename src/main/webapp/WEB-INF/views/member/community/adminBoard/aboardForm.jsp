<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<p class="h4">개인회원 문의사항 등록</p><br>

	<!-- 등록 -->
	<c:set var="actionPath" value="/member/community/adminBoard/aboardForm/insert" />
	<!-- 수정 -->
	<c:if test="${not empty aboard.boardNo}">
		<c:set var="actionPath" value="/member/community/adminBoard/aboardForm/edit" />
	</c:if>
	<!-- 등록 수정 둘다 여기로 옴 -->
	<form:form action="${actionPath}" method="post" modelAttribute="aboard">

	


	<form class="row g-3">
	<fieldset>
	<legend>이메일 문의 입력 폼</legend>
	<p>평일 09시부터 17시까지 문의하신 내용은 당일 답변해드리고 있으며,<br>
	17시 이후에 문의하신 내용은 다음날에 답변, 주말에 문의하신 내용은 그 다음주 월요일에 답변해 드립니다.
	</p>
		<ul>
			<li>
				<div class="col-md-6">
				    <label for="inputEmail4" class="form-label">이메일</label>
				    <input type="email" class="form-control" id="inputEmail4">
			  	</div>
				<div>
					<input type="text" value="${principal.users.memEmail }" readonly="readonly">
					<!-- 기업회원이면 value가 다르게 -->
					<input type="text" value="${principal.users.comEmail }" readonly="readonly">
				</div>
				<div>
					<label>구분</label>
				</div>
				<div>
					<input type="radio" name="member" value="${principal.users.userRole }" readonly="readonly" checked>
					개인
					<input type="radio" name="member" value="${principal.users.userRole }" readonly="readonly" disabled>
					기업
				</div>
					  
				<div class="col-md-4">
				  <label for="boardContent" class="form-label">문의종류</label>
				  <label class="visually-hidden" for="">Preference</label>
				  <select class="form-select" id="">
				   	<option selected>--선택--</option>
				   	<option value="">최저임금 위반/불량 기업/공고 신고</option>
					<option value="">오류 신고</option>
					<option value="">이력서 문의</option>
					<option value="">입사지원 문의</option>
					<option value="">검색 문의</option>
					<option value="">회원가입/탈퇴/ID/PW</option>
					<option value="">공고 문의</option>
					<option value="">지원자관리 문의</option>
					<option value="">이메일/알림 문의</option>
					<option value="">결제/유료 상품 문의</option>
					<option value="">결체취소 신청</option>
					<option value="">연봉정보 수정요청</option>
					<option value="">인적성검사 문의</option>
					<option value="">기업정보</option>
					<option value="">제안사항</option>
					<option value="">멘토링매치</option>
					<option value="">장기 미접속 차단 해제 요청</option>
					<option value="">해외 로그인 차단 해제 요청</option>
					<option value="">인적성검사 결제/환불 문의</option>
					<option value="">기타</option>
				  </select>
				</div>
	  

					<div class="col-md-6">
					  <label for="" class="form-label">내용</label>
					  <input type="text" class="form-control" id="" data-field="boardTitle" path="boardTitle">
					</div>
					<div class="col-md-6">
					  <label for="" class="form-label">내용</label>
					  <textarea class="form-control" id="" placeholder="문의종류 선택 후 내용을 입력해주세요"
					   data-field="boardContent" rows="6" cols="60" path="boardContent">
					</div>
					<div class="invalid-feedback"></div>
					
			</li>
		</ul>
	</fieldset>
	  <div class="col-12">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" id="">
	      <label class="form-check-label" for="gridCheck">
	        개인정보 제공에 동의합니다
	      </label>
	    </div>
	  </div>
	
	</form>	
		


			<!-- 등록 확인 모달 -->
			<div class='btn btn_gray_line' data-bs-toggle="modal" data-bs-target="#madbFM">등록</div>
			<div class="modal fade" id="madbFM" tabindex="-1" aria-labelledby="exampleModalLabel"
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
							<button type="button" class="btn btn-primary px-4" id="madbSV">등록하기</button>
						</div>
					</div>
				</div>
			</div>

	</form:form>

</body>