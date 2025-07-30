<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<meta charset="UTF-8">
<title>띹잡 마이페이지 | 관심기업 리스트</title>

<%-- 에러 메시지를 표시하려면 다음과 같이 추가해야 합니다. --%>
<c:if test="${not empty error }">
	<script>
		alert('${error}'); // 'error' FlashAttribute를 확인
	</script>
</c:if>
<script type="text/javascript"
	src="/js/member/common/mypage/scrab/scrabCompany/scompanyList.js" defer></script>
</head>
<body>

	<p class="h1 mb-3 fw-bold">관심기업</p>

	<div
		class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 ${SCompany.size() }건</p>
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
	<%-- 관심기업 존재 여부에 따른 분기 --%>
	<c:if test="${not empty SCompany}">
		<div class="">
			<c:forEach items="${SCompany }" var="scompany">
				<ul>
					<li
						class="pt-5 pb-5 border-bottom d-flex justify-content-between align-items-center">
						<div class="">
							<a class="d-block h4 fw-bold"
								href="<c:url value="/member/company_view?no=${scompany.companyId}"/>">
								${scompany.company.comName} </a>
							<p class="text-truncate w800">${scompany.company.comInfo}</p>
						</div>
						<div class="d-flex gap-1">
							<a class="btn btn_red_line fw-normal" href="javascript:void(0);"
								onclick="deleteCompany('${scompany.companyId}')"> 삭제 </a>

						</div>
					</li>
				</ul>
			</c:forEach>

		</div>
	</c:if>
	<c:if test="${empty SCompany}">
		<p>관심 기업이 없습니다, 등록해보세요!</p>
		<br>
		<a href="/member/recruitment/recruitmentNotice" class="item"
			onmousedown=""> <strong class="title">나에게 맞는 공고가<br>보고싶다면?
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
	<%-- 스크랩(개수)/관심기업(개수) --%>
	<%-- 
	<ul>
		<li>
			<a href="/member/common/mypage/scrab/scrabRecruitment/srecruitList">
				스크랩 공고<span><script src="/js/member/common/mypage/scrab/scrabRecruitment/srecruitList.js" type="module"></script></span>
			</a>
		</li>
		<li>
			<a href="/member/common/mypage/scrab/scrabCompany/scompanyList">
				관심기업<span>${scompanyList.size()}</span>
			</a>
		</li>
	</ul>
	--%>
	<%-- 목록 관리, 검색 바 --%>
	<%-- 현재 시점과 채용 시점 비교용 --%>
	<%--
	<c:set var="now" value="${pageContext.request.time}" />
		
	<ul>
        <li class="">
    --%>
	<%-- 화면엔 없지만 값이 넘어올 부분 --%>
	<%--
	<input type="hidden" name="scrabCompanyDate" value="${scompany.scrabCompanyDate}">    	
	<input type="hidden" name="userId" value="${scompany.userId}">
	--%>
	<%-- 관심기업 존재 여부에 따른 분기 --%>
	<%-- 
	<c:if test="${not empty scompany}">
            <div class="item fixed" value="">
                <div class="image">
                <div class="link_area">
                    <a href="/member/common/mypage/scrab/scrabCompany/scompanyDetail?companyId=${scompany.companyId}" 
                    class="title" onmousedown="">${scompany.companyId}</a>
                    	<c:if test="${scompany.recruitmentNotice.recruitmentFinishDate le now}">
                        <p>채용 중<b>${scompany.scompanyList.size()}</b>건</p>
                        </c:if>
                    	<c:if test="${scompany.recruitmentNotice.recruitmentFinishDate lt now}">
                        <p>채용 마감<b>${scompany.scompanyList.size()}</b>건</p>
                        </c:if>
                    </p>
                    </div>
                    </div>
                <button type="button" class="company_link" onmousedown="">
                    <span class="blind"><a href="/member/common/mypage/scrab/scrabCompany/scompanyDetail?companyId=${scompany.companyId}"></a></span>
                </button>
                <button type="button" class="memo_area empty" value="" onmousedown="">
                    기업에 관련된 내용을 메모해보세요.
                </button>
                <div class="menu_box">
                    <button type="button" class="more_btn" onclick=""><span class="blind">메뉴 더보기</span></button>
                    <ul class="menu_list">
                        <li>
                            <button type="button" class="menu_option alarm" value="" onmousedown="">
                                채용 알림 끄기
                            </button>
                        </li>
                        <li><button type="button" class="menu_option delete" value="" onmousedown="">삭제</button></li>
                    </ul>
                </div>
            </div>
        </li>
    </c:if>	
	<li class="empty">
	<c:if test="${empty scompany}">
		<p>관심 기업이 없습니다, 등록해보세요!</p>
            <a href="/member/recruitment/recruitmentNotice" class="item" onmousedown="">
                <strong class="title">나에게 맞는 공고가<br>보고싶다면?</strong>
                <span class="txt link">추천 공고 보러가기</span>
            </a>
	</c:if>
    </li>
	</ul>
	--%>
	</div>
	<script
		src="/js/member/common/mypage/scrab/scrabCompany/scompanyList.js"></script>

</body>
