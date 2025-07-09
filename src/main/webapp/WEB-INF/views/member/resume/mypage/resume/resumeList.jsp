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
<body>
	<p class="h1 mb-3 fw-bold">이력서 관리</p>
	<div class="p-3 pt-4 pb-4 bg-violet03 rounded d-flex justify-content-between align-items-center mb-5">
		<p class="fw-bold">✍ 빠르게 이력서를 등록하고 싶다면?</p>
		<div class="">
			<!-- <button type="button" class="btn btn_gray_line h50">파일로 등록</button> -->
			<a class="btn btn_violet h50 lh50" href="/mypage/intoruction/create">이력서 등록</a>
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
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="자기소개서 제목, 문항, 내용으로 검색해보세요." maxlength="24" autocomplete="off" value="">
			</div>
			<a href="/mypage/intoruction/search?${resumeNo}" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</a>
		</div>
	</div>
	
	
	<c:if test="${not empty resumeList}">
		<div class="">
			<c:forEach items="${resumeList }" var="resume">
				<ul>
					<li class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
						<div class="">
							<a class="d-block h4 fw-bold mb-4" href="<c:url value="/mypage/resume/${resume.resumeNo}"/>">${resume.resumeName}</a>
							<div class="d-flex gap-5 icon_box mb-3">
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${empty resume.careerList[0] }">
										<i class='bx  bx-education'  ></i> 신입
									</c:if>
									<c:if test="${not empty resume.careerList[0] }">
										<i class='bx  bx-briefcase-alt'  ></i>경력
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.militaryList[0] }">
										<i class='bx  bx-face-alt-2'  ></i> 군필
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.languageSkillList[0] }">
										<i class='bx  bx-translate'  ></i> 어학능력 보유
									</c:if>
								</span>
								<span class="d-flex gap-2 align-items-center h6 light text-secondary mb-0">
									<c:if test="${not empty resume.myLicenseList[0] }">
										<i class='bx  bx-translate'  ></i> 보유 자격 ${resume.languageSkillList.size() }개
									</c:if>
								</span>
							</div>
							<div class="fs-14 text-light">
								<span>${resume.updateDate}</span>
							</div>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_gray_line fw-normal" href="<c:url value="/mypage/intoruction/edit/${resume.resumeNo}"/>">수정</a>
							<a class="btn btn_red_line fw-normal" href="<c:url value="/mypage/intoruction/delete/${resume.resumeNo}"/>" onclick="return confirmDelete();">삭제</a>
						</div>
					</li>
				</ul>
			</c:forEach>
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