<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
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

<c:forEach items="${questionList}" var="q" varStatus="loop">
  <div class="card mb-3">
    <div class="card-body">

      <p class="h5">${loop.index + 1}. ${q.codeName}</p>
      <div class="d-flex">
        <c:forEach var="opt" items="${options}" varStatus="os">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="answer${loop.index}" id="q${loop.index}_opt${os.index}" value="${os.index + 1}"/>
            <label class="form-check-label" for="q${loop.index}_opt${os.index}" >
              ${opt}
            </label>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</c:forEach>



<c:url var="homeUrl" value="/member/companyReview" />
<c:url var="submitUrl" value="/member/companyReview/insert" />

	<div class="card">
	  <div class="card-body">
	  
	      <button type="button">
	       <a class="btn btn_violet">리뷰 등록</a>
	       </button>
	         <button type="button">
			<a href = "${homeUrl}" class="btn btn_gray_line">돌아가기</a>
	     	</button>
	    
	  </div>
	</div>
</body>
</html>