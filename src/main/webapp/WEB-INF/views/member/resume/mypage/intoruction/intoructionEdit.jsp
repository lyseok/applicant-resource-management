<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
   <meta charset="UTF-8">
   <title>자소서 수정</title>
   <link rel="stylesheet" href="/css/member/resume/intoruction.css" >
   
   <script type="text/javascript">
      const introductionName = "${introdDetail.introductionName}";
      const introductionContent = "${introdDetail.introductionContent}";
   </script>
   <script type="text/javascript" src="/js/member/resume/intoruction.js"></script>
   
</head>
<body>
   
      <div class="introduct_form_wrap">
	    <p class="h1 mb-3 fw-bold">자소서 수정</p>
         <form:form modelAttribute="introduction" method="post">
		    <div class="introduct_add_wrap">
		        <div class="introduct_area" id="introduct01">
		            <div class="mb-3">
		                <label for="title1" class="form-label h5 fw-bold">자소서 명</label>
		                <form:input path="introductionName" cssClass="form-control" id="title1" placeholder="자소서 이름을 입력해주세요."/>
		                <form:errors path="introductionName" cssClass="text-danger"/>
		            </div>
		            <div class="mb-3">
		                <label for="question1" class="form-label h5 fw-bold">문항1</label>
		                <form:input path="introductionQuestion" cssClass="form-control" id="question1" placeholder="지원동기, 입사 후 포부 같은 내용을 입력해주세요."/>
		                <form:errors path="introductionQuestion" cssClass="text-danger"/>
		            </div>
		            <div class="mb-3">
		                <label for="content1" class="form-label fw-bold h5">자소서 내용</label>
		                <form:textarea path="introductionContent" cssClass="form-control" id="content1" rows="10"/>
		                <form:errors path="introductionContent" cssClass="text-danger"/>
		            </div>
		            <div class="mb-3">
		                <h5 class="form-label fw-bold h5">코칭</h5>
		                <a href="javascript:void(0)">맞춤법 검사기</a>
		            </div>
		        </div>
		    </div>
	         <div class="text-end mt-3">
	            <button class="btn btn_violet" type="submit">작성완료</button>
	         </div>
		  </form:form>
         <div class="fixed_btn_wrap">
            <a class="add"><i class='bx bx-plus'></i> </a>
            <a class="remove"><i class='bx bx-minus'></i> </a>
            <a class="pager_li" data-area-id="introduct01">01</a>
         </div>
      </div>
   
</body>