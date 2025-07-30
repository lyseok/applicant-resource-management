<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
	<meta charset="UTF-8">
	<title>자소서 관리</title>
   
	<c:if test="${not empty message }">
		<script>
			alert('${message}');
		</script>
	</c:if>
	<%-- 에러 메시지를 표시하려면 다음과 같이 추가해야 합니다. --%>
	<c:if test="${not empty error }">
	    <script>
	        alert('${error}'); // 'error' FlashAttribute를 확인
	    </script>
	</c:if>
   <script type="text/javascript" src="/js/member/resume/introuctionList.js" defer></script>
</head>
<body>
	<p class="h1 mb-3 fw-bold">자소서 관리</p>
	<div class="p-3 pt-4 pb-4 bg-violet03 rounded d-flex justify-content-between align-items-center mb-5">
		<p class="fw-bold">✍ 기업 맞춤으로 작성하는 자기소개서</p>
		<div class="">
			<!-- <button type="button" class="btn btn_gray_line h50">파일로 등록</button> -->
			<a class="btn btn_violet h50 lh50" href="/mypage/introduction/create">자소서 등록</a>
		</div>
	</div>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 ${totalCount}건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="자기소개서 제목, 문항, 내용으로 검색해보세요." maxlength="24" autocomplete="off" value="">
			</div>
			<a href="/mypage/introduction/search?${introductionNo}" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</a>
		</div>
	</div>
	
	
	<c:if test="${not empty introductionList}">
		<div class="">
			<ul>
				<c:forEach items="${introductionList }" var="introduction">
					<li class="py-4 border-bottom d-flex justify-content-between align-items-center">
						<div class="">
							<a class="d-block h4 fw-bold" href="/mypage/introduction/edit/${introduction.introductionNo}">${introduction.introductionName}</a>
							<p class="text-truncate w800">${introduction.introductionQuestionList.introductionContent}</p>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_violet_line fw-normal" href="<c:url value="/mypage/introduction/edit/${introduction.introductionNo}"/>">수정</a>
							<a class="btn btn_red_line fw-normal btn-delete" data-bs-toggle="modal" data-bs-target="#deleteIntroductionModal"  data-intro-no="${introduction.introductionNo}">삭제</a>
						</div>
					</li>
				</c:forEach>
			</ul>
				
				<!-- 삭제 확인 모달 -->
				<div class="modal fade" id="deleteIntroductionModal" tabindex="-1" aria-labelledby="deleteIntroductionModalLabel${introduction.introductionNo}" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header border-0">
								<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
									<i class="bi bi-exclamation-triangle-fill me-1"></i> 자소서 정보 삭제
								</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body text-center py-4">
								<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
								<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
									자소서를 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
								</p>
								<p class="text-secondary mb-0" style="font-size:1.08rem;">
									삭제된 데이터는 복구할 수 없습니다.<br>
									실행 전 반드시 다시 한 번 확인해 주세요.
								</p>
							</div>
							<div class="modal-footer border-0 justify-content-center">
								<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
								<a class="btn btn-danger px-4" id="confirmDeleteBtn">삭제</a>
							</div>
						</div>
					</div>
				</div>
			
			
			<c:if test="${not empty totalPages}">
				<div class="PageBox">
				    <c:if test="${currentPage > 1}">
				        <a class="BtnType SizeS BtnPrev" href="?page=${currentPage - 1}">이전</a>
				    </c:if>
				    <c:forEach begin="1" end="${totalPages}" var="i">
		    	    <c:choose>
				        <c:when test="${i == currentPage}">
			            <span class="BtnType SizeS active">${i}</span>
				        </c:when>
				        <c:otherwise>
				            <a href="?page=${i}" class="BtnType SizeS page">${i}</a>
				        </c:otherwise>
				    	</c:choose>
				    </c:forEach>
				    <c:if test="${currentPage < totalPages}">
				        <a class="BtnType SizeS BtnNext btnNext" href="?page=${currentPage + 1}">다음</a>
				    </c:if>
				</div>
			</c:if>
		</div>
	</c:if>
	
	
	<c:if test="${empty introductionList}">
		<div class="intoructionList p-6 d-flex flex-column align-items-center gap-2">
			<span>띹잡에 등록된 자소서가 없어요!</span>
			<a class="fw-bold d-flex" href="/write/new">
				자소서 등록하러 가기
				<span class="material-symbols-outlined">chevron_right</span>
			</a>
		</div>
	</c:if>
</body>