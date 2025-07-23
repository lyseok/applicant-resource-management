<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
	<meta charset="UTF-8">
	<title>이력서 관리</title>
   
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
   <link rel="stylesheet" href="/css/member/resume/resume.css" >
   <script type="text/javascript" src="/js/member/resume/resumeList.js" defer></script>
</head>
<body onbeforeunload="showLoading()">
	<p class="h1 mb-3 fw-bold">이력서 관리</p>
	<div class="p-3 pt-4 pb-4 bg-violet03 rounded d-flex justify-content-between align-items-center mb-5">
		<p class="fw-bold">✍ 빠르게 이력서를 등록하고 싶다면?</p>
		<div class="">
			<!-- <button type="button" class="btn btn_gray_line h50">파일로 등록</button> -->
			<a class="btn btn_violet h50 lh50" href="/mypage/resume/create">이력서 등록</a>
		</div>
	</div>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">
			총 
				<c:if test="${not empty resumeList}">${resumeList.size() }</c:if>
				<c:if test="${empty resumeList}">0</c:if>
			건
		</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="이력서 제목으로 검색해보세요." maxlength="24" autocomplete="off" value="">
			</div>
			<%-- <a href="/mypage/resume/search?${resumeNo}" class="searchBarBtn"> --%>
			<a href="javascript:void(0)" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</a>
		</div>
	</div>
	
	
	<c:if test="${not empty resumeList}">
		<div class="">
			<ul>
				<c:forEach items="${resumeList }" var="resume">
					<li class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
						<div class="">
							<a class="d-block h4 fw-bold mb-4" href="<c:url value="/mypage/resume/${resume.RESUME_NO}"/>">${resume.RESUME_NAME}</a>
							<div class="d-flex gap-4 icon_box mb-3">
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${empty resume.CAREER_YN }">
										<i class='bx  bx-education'  ></i> 신입
									</c:if>
									<c:if test="${not empty resume.CAREER_YN }">
										<i class='bx  bx-briefcase-alt'  ></i>경력
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.MILITARY_YN }">
										<i class='bx  bx-face-alt-2'  ></i> 군필
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.LANGUAGE_SKILL_YN }">
										<i class='bx  bx-translate'  ></i> 어학능력 보유
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.MY_LICENSE_CNT }">
										<i class='bx  bx-credit-card-front'  ></i>  보유 자격 ${resume.MY_LICENSE_CNT }개
									</c:if>
								</span>
							</div>
							<div class="">
								<span class="fs-16 text-secondary">${resume.UPDATE_DATE}</span>
							</div>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_gray_line fw-normal" href="/mypage/resume/edit/${resume.RESUME_NO}">수정</a>
							<a class="btn btn_red_line fw-normal" href="" data-bs-toggle="modal" data-bs-target="#deleteResumeModal${resume.RESUME_NO}">삭제</a>
						</div>
						
						<!-- 삭제 확인 모달 -->
						<div class="modal fade" id="deleteResumeModal${resume.RESUME_NO}" tabindex="-1" aria-labelledby="deleteResumeModal${resume.RESUME_NO}Label" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header border-0">
										<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
											<i class="bi bi-exclamation-triangle-fill me-1"></i> 이력서 정보 삭제
										</h1>
										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body text-center py-4">
										<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
										<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
											해당 이력서 정보를 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
										</p>
										<p class="text-secondary mb-0" style="font-size:1.08rem;">
											삭제된 데이터는 복구할 수 없습니다.<br>
											실행 전 반드시 다시 한 번 확인해 주세요.
										</p>
									</div>
									<div class="modal-footer border-0 justify-content-center">
										<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
										<a class="btn btn-danger px-4" href="/mypage/resume/delete/${resume.resumeNo}">삭제</a>
									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="PageBox">
        <span class="BtnType SizeS active">1</span>
        <button class="BtnType SizeS page" data-page="2">2</button>
        <button class="BtnType SizeS page" data-page="3">3</button>
        <button class="BtnType SizeS page" data-page="4">4</button>
        <button class="BtnType SizeS page" data-page="5">5</button>
        <button class="BtnType SizeS page" data-page="6">6</button>
        <button class="BtnType SizeS page" data-page="7">7</button>
        <button class="BtnType SizeS page" data-page="8">8</button>
        <button class="BtnType SizeS page" data-page="9">9</button>
        <button class="BtnType SizeS page" data-page="10">10</button>
        <button data-page="11" class="BtnType SizeS BtnNext btnNext">다음</button>
      </div>
		</div>
		
	</c:if>
	
	
	<c:if test="${empty resumeList}">
		<div class="intoructionList p-6 d-flex flex-column align-items-center gap-2">
			<span>띹잡에 등록된 이력서가 없어요!</span>
			<a class="fw-bold d-flex" href="/mypage/resume/create">
				이력서 등록하러 가기
				<span class="material-symbols-outlined">chevron_right</span>
			</a>
		</div>
	</c:if>
</body>