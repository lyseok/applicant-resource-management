<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
   <meta charset="UTF-8">
   <title>자소서 상세보기</title>
   <link rel="stylesheet" href="/css/member/resume/intoruction.css" >
   
   <script type="text/javascript">
      const introductionName = "${introdDetail.introductionName}";
      const introductionContent = "${introdDetail.introductionContent}";
   </script>
   <script type="text/javascript" src="/js/member/resume/intoruction.js"></script>
</head>
<body>
   
      <div class="introduct_form_wrap">
		<c:if test="${not empty introdDetail }">
		    <p class="h1 mb-3 fw-bold">자소서 상세보기</p>
		</c:if>
	    <div class="introduct_add_wrap">
	        <div class="introduct_area" id="introduct01">
	            <div class="mb-3">
	                <label for="title1" class="form-label h5 fw-bold">자소서 명</label>
	                <p>${introdDetail.introductionName }</p>
	            </div>
	            <div class="mb-3">
	                <label for="question1" class="form-label h5 fw-bold">문항1</label>
	                <p>${introdDetail.introductionQuestion }</p>
	            </div>
	            <div class="mb-3">
	                <label for="content1" class="form-label fw-bold h5 ">자소서 내용</label>
	                <p class="min-ht300">${introdDetail.introductionContent }</p>
	            </div>
	        </div>
	    </div>	    
        <div class="text-end mt-3">
            <a class="btn btn_violet" href="/mypage/intoruction/list">목록</a>
        </div>
      </div>
   
</body>