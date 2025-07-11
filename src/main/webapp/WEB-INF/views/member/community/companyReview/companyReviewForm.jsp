<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="card">
  <div class="card-body">
     <p class="h2">리뷰를 등록해주세요!</p>
  </div>
</div>

 
<c:set var="options" value="${['매우 아니다','아니다','보통','그렇다','매우 그렇다']}" />


<form action="${pageContext.request.contextPath}/member/company_review" method = "post">
	<input type = "hidden" name = "comId" value = "${career.comId }"/>
	<input type = "hidden" name = "jobCode" value = "${career.jobCode }"/>
	<input type = "hidden" name = "workingYn" value = "${career.tenure }"/>
	<input type="hidden" name="companyReviewStatus" value="N" />

	<c:set var="options" value="${['매우 아니다','아니다','보통','그렇다','매우 그렇다']}" />
  <c:forEach items="${questionList}" var="q" varStatus="ls">
    <div class="card mb-3">
      <div class="card-body">
        <p class="h5">${ls.index + 1}. ${q.codeName}</p>
        <div class="d-flex">
          <c:forEach var="opt" items="${options}" varStatus="os">
            <div class="form-check form-check-inline">
              <input class="form-check-input"
                     type="radio"
                     name="companyReviewQuestion[${ls.index}].companyReviewScore"
                     id="q${ls.index}_opt${os.index}"
                     value="${os.index + 1}"
                     required/>
              <label class="form-check-label" for="q${ls.index}_opt${os.index}">
                ${opt}
              </label>
            </div>
          </c:forEach>
        </div>
        <input type="hidden"
               name="companyReviewQuestion[${ls.index}].reviewSubjectCode"
               value="${q.codeDetailNo}" />
      </div>
    </div>
  </c:forEach>
	
	
	<div>
		<label>한 줄 리뷰</label>
		<input type = "text" name = "companyReviewOneLine" required>  
	</div>
	
	<c:url var="homeUrl" value="/member/company_review" />

		<div class="card">
		  <div class="card-body">
		  
		      <button type="submit">
		       <a class="btn btn_violet">리뷰 등록</a>
		       </button>
		         <button type="button">
				<a href = "${homeUrl}" class="btn btn_gray_line">돌아가기</a>
		     	</button>
		    
		  </div>
		</div>
	 
</form>


</body>
</html>