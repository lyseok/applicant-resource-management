<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<meta charset="UTF-8">
<title>띹잡 마이페이지 | 관심공고 리스트</title>

<c:if test="${not empty error }">
	<script>
		alert('${error}');
	</script>
</c:if>
<script type="text/javascript"
	src="/js/member/common/mypage/scrab/scrabRecruit/srecruitList.js" defer></script>
</head>
<body>

	<p class="h1 mb-3 fw-bold">스크랩한 공고</p>

	<div
		class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 ${srecruitList.size()}건</p>
		<div class="TypoBox searchBar">
			<form method="get" action="">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="listKeyword">검색어</label> <input
						type="text" id="listKeyword" name="keyword" class="searchBarInput"
						placeholder="관심 공고를 검색해보세요" maxlength="24" autocomplete="off"
						value="${keyword}">
				</div>
				<button type="submit" class="searchBarBtn">
					<span class="material-symbols-outlined">search</span>
				</button>
			</form>
		</div>

	</div>

	<c:if test="${not empty srecruitList}">
		<div>
			<c:forEach items="${srecruitList}" var="srecruit">
				<ul>
					<li
						class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
						<div>
							<!-- 회사명 + 공고제목 -->
							<a class="d-block h4 fw-bold"
								href="<c:url value='/recruit_notice/${srecruit.recruitment.recruitmentNo}'/>">
								${srecruit.recruitment.comName} -
								${srecruit.recruitment.recruitmentTitle} </a>
							<!-- 공고 내용 일부 or 급여 등 -->
							<p class="text-truncate w800">급여:
								${srecruit.recruitment.recruitmentSalary}만원 | 마감일:
								${srecruit.recruitment.recruitmentFinishDate}</p>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_red_line fw-normal" href="javascript:void(0)"
								onclick="deleteRecruit('${srecruit.recruitment.recruitmentNo}')">
								삭제 </a>

						</div>
					</li>
				</ul>
			</c:forEach>

			<!-- 페이징 -->

			<!-- 필요 시 추가 -->
		</div>
		</div>
	</c:if>

	<c:if test="${empty srecruitList}">
		<p>관심 공고가 없습니다. 등록해보세요!</p>
		<br>
		<a href="/member/recruitment/recruitmentNotice" class="item"> <strong
			class="title">나에게 맞는 공고가<br>보고싶다면?
		</strong> <span class="txt link">추천 공고 보러가기</span>
		</a>
	</c:if>
	<c:if test="${not empty totalPages}">
		<nav class="mt-4">
			<ul class="pagination justify-content-center">
				<c:forEach begin="1" end="${totalPages}" var="i">
					<li class="page-item ${i == currentPage ? 'active' : ''}"><a
						class="page-link" href="?page=${i}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
			</ul>
		</nav>
	</c:if>


	<script
		src="/js/member/common/mypage/scrab/scrabRecruitmentNotice/srecruitmentNotice.js"></script>
</body>
