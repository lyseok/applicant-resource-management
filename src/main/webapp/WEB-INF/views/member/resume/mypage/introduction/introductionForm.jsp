<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
<meta charset="UTF-8">
<c:if test="${not empty introdEdit }">
	<title>자소서 수정</title>
</c:if>
<c:if test="${not empty introdCreate }">
	<title>자소서 등록</title>
</c:if>
<link rel="stylesheet" href="/css/member/resume/introuction.css">

<script type="text/javascript">
	const introductionName = "${introdDetail.introductionName}";
	const introductionContent = "${introdDetail.introductionContent}";
</script>
<script type="text/javascript" src="/js/member/resume/introuction.js"></script>

</head>
<body class="aos_frans_none">

	<%-- <c:if test="${not empty introdDetail}">?</c:if>
   <c:if test="${not empty introdCreate}">?</c:if> --%>

	<div class="introduct_form_wrap">
		<c:if test="${not empty introdEdit }">
			<p class="h1 mb-3 fw-bold">자소서 수정</p>
		</c:if>
		<c:if test="${not empty introdCreate }">
			<p class="h1 mb-3 fw-bold">자소서 등록</p>
		</c:if>
		<form:form modelAttribute="introduction" method="post" action="/mypage/introduction/create">
			<div class="introduct_add_wrap">
				<div class="introduction_fix_wrap">
					<div class="inner">
						<div class="tit_box">
							<label for="title" class="form-label fw-bold required ">자소서명</label>
							<%-- path는 IntroductionListVO 내부의 introductionList[0]에서 IntroductionVO의 introductionName 필드를 참조합니다. --%>
							<form:input path="introductionName" cssClass="form-control" id="title" placeholder="자소서 이름을 입력해주세요." />
							<form:errors path="introductionName" cssClass="text-danger" />
						</div>
						<div class="text-end d-flex justify-content-between gap-1">
							<a class="btn btn_red_line h50" href="/mypage/introduction/list">취소</a>
							<button class="btn btn_violet h50 justify-content-center w140"
								type="submit">등록</button>
						</div>
					</div>
				</div>
				<c:if test="${not empty introdCreate }">
					<div class="introduct_area" id="introduct01">
						<div class="mb-3">
							<label for="question1" class="form-label h5 fw-bold">문항1</label>
							<form:input
								path="introductionQuestionList[0].question"
								cssClass="form-control" id="question1"
								placeholder="지원동기, 입사 후 포부 같은 내용을 입력해주세요." />
							<form:errors
								path="introductionQuestionList[0].question"
								cssClass="text-danger" />
						</div>
						<div class="mb-3">
							<label for="content1" class="form-label fw-bold h5">자소서
								내용</label>
							<form:textarea
								path="introductionQuestionList[0].content"
								cssClass="form-control" id="content1" rows="10" />
							<form:errors
								path="introductionQuestionList[0].content"
								cssClass="text-danger" />
						</div>
					</div>
				</c:if>

				<%-- 수정로직 --%>
				<c:if test="${not empty introdEdit }">
					<input type="hidden" name="introductionNo" value="${introduction.introductionNo}" />
					<c:forEach var="question" items="${introduction.introductionQuestionList}" varStatus="status">
				    <div class="introduct_area mt-4" id="introduct${status.index + 1}">
				      <div class="mb-3">
				        <label class="form-label h5 fw-bold" for="question${status.index + 1}">문항 ${status.index + 1}</label>
				        <form:input path="introductionQuestionList[${status.index}].question"
				                    cssClass="form-control" id="question${status.index + 1}" />
				      </div>
				      <div class="mb-3">
				        <label class="form-label h5 fw-bold"  for="content${status.index + 1}">내용</label>
				        <form:textarea path="introductionQuestionList[${status.index}].content"
				                       cssClass="form-control" id="content${status.index + 1}" rows="10" />
				      </div>
				    </div>
				   </c:forEach>

				</c:if>
			</div>
		</form:form>
		
		
			<div class="fixed_btn_wrap">
					<a class="add"><i class='bx bx-plus'></i> </a>
					<a class="remove" style="display: none;"> <i class='bx bx-minus'></i> </a> 
					<!-- 등록할 때 동작 -->
					<c:if test="${not empty introdCreate}">
						<a class="pager_li" data-area-id="introduct01">01</a>
					</c:if>	
				
					<!-- 수정일떄 동작 -->
					<c:if test="${not empty introdEdit}">
				    <c:forEach var="q" items="${introduction.introductionQuestionList}" varStatus="status">
					    <c:choose>
				        <c:when test="${status.index == 0}">
			            <a class="pager_li active" data-area-id="introduct${status.index + 1}">
			              <c:out value="${status.index + 1 < 10 ? '0' : ''}${status.index + 1}" />
			            </a>
				        </c:when>
				        <c:otherwise>
			            <a class="pager_li" data-area-id="introduct${status.index + 1}">
			              <c:out value="${status.index + 1 < 10 ? '0' : ''}${status.index + 1}" />
			            </a>
				        </c:otherwise>
					    </c:choose>
						</c:forEach>
			
					</c:if>
			</div>		
	</div>

</body>