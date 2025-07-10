<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<title>띹잡 고객센터 | 게시글 목록</title>
<%-- 	<script src="/js/admin/community/adminBoard/aboardList.js"></script> --%>
<style>

</style>
</head>
<body>

	<p class="h3">고객센터</p>
	
	
	<ul class="nav nav-tabs" style="background:var(—violet60)">
	  <li class="nav-item">
	    <a class="nav-link active" aria-current="page" 
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ" onclick="selTab(0)">개인회원</a>
	  </li>
	  <li class="nav-item" style="z-index: 1;">  <!-- default는 개인회원보다 뒤로 가 있음 -->
	    <a class="nav-link active" aria-current="page"
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=CFAQ" onclick="selTab(1)">기업회원</a>
	  </li>
	</ul>
<!-- 개인회원 탭 클릭 시 -->	
	<ul id="memberT"  class="nav nav-pills nav-fill">
	  <li class="nav-item">
	    <a class="nav-link" aria-current="page" 
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ-U1">이력서등록/관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ-U2">회원정보/아이디/비밀번호</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ-U3">입사지원/관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link"
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ-U4">채용정보 검색/관리</a>
	  </li>
	</ul>
<!-- 기업회원 탭 클릭 시 -->
	<ul id="companyT" class="nav nav-pills nav-fill" style="display: none;">  <!-- default는 개인회원보다 뒤로 가 있음 -->
	  <li class="nav-item">
	    <a class="nav-link" aria-current="page" 
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=CFAQ-U1">채용정보 등록/관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/admin/community/adminBoard/aboardList/type?boardTypeCode=CFAQ-U2">유료서비스/결제</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="/admin/community/adminBoard/aboardList/type?boardTypeCode=CFAQ-U3">인재풀</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link"
	    href="/admin/community/adminBoard/aboardList/type?boardTypeCode=CFAQ-U4">회원,기업정보/아이디/비밀번호</a>
	  </li>
	</ul>
	
<!-- 개인회원 탭 다음에, 이력서 등록/관리 클릭 시 -->
<p class="h4">이력서 등록/관리</p>
<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        Q1. 이력서 등록 방법은?
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse collapse" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>This is the first item’s accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It’s also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        Q2. 이력서 수정 방법은?
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>This is the second item’s accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It’s also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        Q3. 이력서 삭제 방법은?
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>This is the third item’s accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It’s also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
      </div>
    </div>
  </div>
</div>

	<%-- <table class="table">
	<thead>
		<tr>
			<th>게시글 번호</th>
			<th>사용자 ID</th>
			<th id="aboardTypeCode">
				<span id="faqTypeCode">게시판 유형 코드</span>
			</th>
			<th>제목</th>
			<th>등록일시</th>
			<th>내용</th>
			<th>삭제일시</th>
			<th>조회수</th>
			<th>게시글 상태</th>
			<th>댓글 수</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty aboardList}">
			<c:forEach items="${aboardList}" var="aboard">
				<c:url value="/admin/community/adminBoard/aboardDetail" var="detailURL">
					<c:param name="boardNo" value="${aboard.boardNo }"/>
				</c:url>
				<c:url value="/admin/community/adminBoard/aboardList/type" var="typeURL">
					<c:param name="boardTypeCode" value="${aboard.boardTypeCode }"/>
				</c:url>
				<tr>
					<td>
					<a href="${detailURL}">${aboard.boardNo}</a>
					</td>
					<td>${aboard.users.userId}</td>  <!-- has A 관계 -->					
					<td>
					<a href="${typeURL}">${aboard.boardTypeCode}</a>
					</td>
					<td>${aboard.boardTitle}</td>
					<td>${aboard.boardWriteDate}</td>
					<td>${aboard.boardContent}</td>
					<td>${aboard.boardDeleteDate}</td>
					<td>${aboard.boardPostHit}</td>
					<td>${aboard.boardStatus}</td>
					<td>${aboard.adminCommentList.size() }</td>  <!-- has Many 관계 -->
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty aboardList }">
			<tr>
				<td colspan="10">게시글 없음.</td>
			</tr>
		</c:if>		
	</tbody>
</table> --%>
<script>
	const memberT = document.querySelector("#memberT");
	const companyT = document.querySelector("#companyT");
	function selTab(pNum){
		event.preventDefault(); // a link 동작 막기
		if(!pNum){
		///admin/community/adminBoard/aboardList/type?boardTypeCode=UFAQ
			fetch("/ajax/admin/adminBoard/UFAQ").then(resp=>{
				resp.json().then(rslt=>{
					console.log("체킁: ",rslt);
				})
			})

			memberT.style.display="flex";
			companyT.style.display="none";
		}else {
			memberT.style.display="none";
			companyT.style.display="flex";
		}
	}
</script>
</body>
