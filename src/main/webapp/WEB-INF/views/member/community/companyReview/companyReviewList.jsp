<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
	<title>기업리뷰</title>
	<link rel="stylesheet" href="/dist/assets/css/main.css" >
</head>
<body>

<h1>전체 기업 리뷰</h1>


<hr>

<ul>
	  <c:forEach items="${companyList}" var="company">
	    <li style="list-style:none; margin-bottom:10px;">
	      <a href="<c:url value='/companyReview/detail/${company.userId}'/>"
	         style="text-decoration:none; color:inherit;">
	        <div class="card">
	          <div class="card-body">
	            <h5 class="card-title">${company.comName}의 리뷰</h5>
	            <p class="card-text">${company.comInfo}</p>
	          </div>
	          
	          
	        </div>
	      </a>
	    </li>
	  </c:forEach>
</ul>

	
	
	
	
<c:url var="insertUrl" value="/companyReview/insert" />

	<div class="card">
	  <div class="card-body">
	  
	      <button type="button" class="btn btn-primary btn-sm">
	        <a href="${insertUrl }">리뷰 등록하러 가기</a>
	      </button>
	    
	  </div>
	</div>
	
</body>