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

<ul class="nav">
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="#">전체</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">조직문화</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">복지</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">워라벨</a>
  </li>
</ul>
	
   
   <ul>
        <c:forEach items="${list}" var="review">
            <li style="list-style:none; margin-bottom:10px;">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">${review.companyReviewContent}</p>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
	
</body>
</html>