<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
	<title>띹잡 관리자 페이지 | 관리자게시판</title>
	<link rel="stylesheet" href="/dist/assets/css/board/jop_view.css" >
</head>
<body>

	<sec:authentication property="principal.realUser.userId" var="userId"/>
	<input type="hidden" id="userIdHidden" value="${userId}">

	<p class="h4" style="display: none;" id="listTitle">게시글 목록</p>
	
	<!-- 회원 탭 버튼 -->
	<div id="memTypeBtn"></div><br>
	
	<!-- 새 글 등록 버튼 -->
	<div id="formBtn"></div><br>

	<!-- 게시글 리스트 -->
	<div id="detTitle"></div>
	<ul id="aboardList">
		<input type="hidden" id="typeHidden" value="${type}">
	</ul>
	
	<!-- 게시글 상세 -->
	<div id="aboardDetail"></div>
	<div id="allBtns"></div>
	<br>
	
	<!-- 답글 리스트 -->
	<div id="acommentListContainer"></div>

	<!-- 답글 폼 -->
	<div id="acommentFormContainer"></div>	
	
	<!-- 등록 폼 미리 숨겨놓기 -->
	  <form id="aboardForm" style="display: none;">
	  	<p class="h4" id="formTitle">관리자 게시판</p><br>
	  	<input type="hidden" id="noHidden" value="${aboard.boardNo}">
	    
	    <label>게시판 유형 코드</label>
	    <select id="boardTypeCode">
	      <option value="-1">--선택--</option>
	    </select>
	    
	    <select id="codeGroup" disabled>
	      <option value="-1">--선택--</option>
	    </select>
	    
	    <select id="memType" disabled>
	      <option value="-1">--선택--</option>
	    </select>
	    
	    <br>
	    <input type="text" name="boardTitle" placeholder="제목">
	    <label>작성자 아이디: </label>
		<input type="text" name="userId" value="${userId}" disabled><br>
		<textarea name="boardContent" placeholder="내용" rows="6" cols="60"></textarea>
	    
	    <button type="submit">등록</button>
	  </form>
	  
	 <!-- 모달 폼 미리 숨겨놓기 -->
	 <!-- 삭제 모달 -->
	 <!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 게시글 삭제
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
				<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
					해당 게시글을 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
				</p>
				<p class="text-secondary mb-0" style="font-size:1.08rem;">
						삭제된 데이터는 복구할 수 없습니다.<br>
						실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4">삭제</button>
				</div>
			</div>
		</div>
	</div>
	
<script src="/js/admin/community/adminBoard/aboardList.js"></script>
<script src="/js/admin/community/adminBoard/aboardDetail.js"></script>
<script src="/js/admin/community/adminBoard/aboardForm.js"></script>
</body>
