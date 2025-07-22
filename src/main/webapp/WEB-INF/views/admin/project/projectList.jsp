<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
	<meta charset="UTF-8">
	<title>프로젝트 목록</title>
	<script defer type="text/javascript" src="/js/project/project/projectList.js"></script>
		<link rel="stylesheet" href="/dist/assets/css/board/postList.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css" >
		<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css" >
	
</head>
<body>
	<p class="h1 mb-3 fw-bold">프로젝트 목록</p>
	
<div class="Post_post">
    <div class="PostList_postList">
        <ul>
            <li class="PostList_postItem">
            		<a class="PostList_link" href="/feed/view/90616">
                    <div class="PostList_post">
                        <div class="ListItem_post">
                            <div class="ListItem_post_item">
                                <h3 class="ListItem_post_title">취준 준비하는 학생입니다.</h3>
                                <div class="ListItem_post_content">서울 4년제 하위권 대학에서 무역 전공하고 있습니다. 취준을 준비하면서 고민이 많아 몇가지 여쭤봅니다. 성적은 2.8 정도로 매우 낮습니다. 학교 자체가 1학년 때 학과가 없이 생활하다가 2학년 때 학과를 선택하는건데 이 과정에서 흥미없는 부분을 골랐다가 마음을 잘 못잡고 성적이 무너졌습니다. 그래도 다시 2학년 말에 마음 다잡고 시작했지만 이미 벌어진 지식의 격차가 좀 처럼 줄지 않더리고요.. 그래서 결국 성적이 처참해졌습니다 ..&lt;경력&gt;학교 연계 인턴쉽 중소기업 6개월 - 디지털 마케팅 직무&lt;자격증&gt;국제무역사 1급무역영어 1급토익 800점대MOS 자격증유통관리사처음부터 대기업 갈 생각 없습니다. 그래도 중갼기업 정도는 가고 싶은데 가능할까요??</div>
                            </div>
                        </div>
                        <div class="ListItem_info">
                            <div class="ListItem_profile">
                                <div class="ListItem_profile_pic"><img src="https://www.saraminimage.co.kr/sri/career-service/profile/character02.png" alt="프로필 이미지"></div>
                                <div class="ListItem_profile_info">
                                    <div class="ListItem_profile_infoDiv">
                                        <div class="ListItem_profile_nickName">꼬끼오끼기</div>
                                    </div>
                                    <div class="ListItem_profile_infoDiv">
                                        <div class="ListItem_profile_jobInfo">
                                            <div>영업·판매·무역</div>
                                            <div>신입</div>
                                            <div class="ListItem_dotDivider"></div>
                                            <div class="ListItem_profile_date">2시간 전</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="ListItem_profile_postInfoList">
                                <div class="ListItem_profile_postInfoItem"><span>좋아요</span><strong>0</strong></div>
                                <div class="ListItem_profile_divider"></div>
                                <div class="ListItem_profile_postInfoItem"><span>댓글</span><strong>0</strong></div>
                                <div class="ListItem_profile_divider"></div>
                                <div class="ListItem_profile_postInfoItem"><span>조회</span><strong>29</strong></div>
                            </div>
                        </div>
                    </div>
                </a>
           </li>
        </ul>
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