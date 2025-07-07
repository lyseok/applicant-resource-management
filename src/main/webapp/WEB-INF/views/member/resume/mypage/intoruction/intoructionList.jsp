<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
	<meta charset="UTF-8">
	<title>자소서 관리</title>
</head>
<body>
	<p class="h1 mb-3 fw-bold">자소서 관리</p>
	<div class="p-3 pt-4 pb-4 bg-violet03 rounded d-flex justify-content-between align-items-center mb-5">
		<p class="fw-bold">✍ 초안 자동생성, 맞춤법 및 표절검사, 마무리 코칭까지 해주는</p>
		<div class="">
			<button type="button" class="btn btn_gray_line h50">파일로 등록</button>
			<a class="btn btn_violet h50 lh50" href="/write/new">자소서 등록하기</a>
		</div>
	</div>
	
	<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 ${introductionList.size() }건</p>
		<div class="TypoBox searchBar">
			<div class="searchBarWrap">
				<label class="searchBarLabel" for="listKeyword">검색어</label>
				<input type="text" id="listKeyword" class="searchBarInput" placeholder="자기소개서 제목, 문항, 내용으로 검색해보세요." maxlength="24" autocomplete="off" class="SearchInput_SearchInput__input__Cg7QD" value="">
			</div>
			<button type="button" class="searchBarBtn">
				<span class="material-symbols-outlined">search</span>
			</button>
		</div>
	</div>
	
	
	<c:if test="${not empty introductionList}">
		<div class="">
			<c:forEach items="${introductionList }" var="introduction">
				<ul>
					<li class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
						<div class="">
							<a class="d-block h4 fw-bold" href="<c:url value="/mypage/intoruction/${introduction.introductionNo}"/>">${introduction.introductionName}</a>
							<p class="text-truncate w800">${introduction.introductionContent}</p>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_violet_line fw-normal">수정하기</a>
							<a class="btn btn_violet_line fw-normal">삭제하기</a>
						</div>
					</li>
				</ul>
			</c:forEach>
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