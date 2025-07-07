<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
	<title>기업리뷰</title>
	 <style>
    .career-card {
      cursor: pointer;
      border: 2px solid transparent;
      transition: border-color .2s, background-color .2s;
    }
    .career-card.active {
      border-color: #6f42c1;           
      background-color: #f3e8ff;      
    }
    #submitBtn:disabled {
      opacity: .5;
      cursor: not-allowed;
    }
  </style>
</head>
<body>



${resumes }



<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand">리뷰가 궁금한 기업을 검색해보세요!</a>
    <form class="d-flex">
      <input class="form-control me-2" type="search" placeholder="기업명" aria-label="Search">
      <button class="btn btn-outline-success" type="submit">검색</button>
    </form>
  </div>
</nav>
  
	<ul>
	  <c:forEach items="${companyList}" var="company">
	    <li style="list-style:none; margin-bottom:10px;">
	      <a  href="<c:url value='/member/companyReview/detail/${company.userId}'/>"
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
	
	
	

<c:url var="myReviewUrl" value="/member/companyReview/myReview" />

	<div class="card">
	  <div class="card-body">
	  
	      <button type="button" class="btn btn_violet" data-bs-toggle="modal" data-bs-target="#resumeModal">
	        리뷰 등록하기
	      </button>
	       <button type="button">
	        <a class="btn btn_gray_line" href="${myReviewUrl }">My 리뷰 </a>
	      </button>
	  </div>
	</div>
	

    
<div class="modal fade" id="resumeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="resumeModalLabel">근무했던 기업 중 하나를 선택해주세요</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
     
     	 <div class="modal-body">
     
			<div>
				  <c:forEach items="${resumes}" var="resume">
				    <c:forEach items="${resume.careerList}" var="career">
				      <c:if test="${not empty career.careerNo and not empty career.company}">
				        <div class="card mb-2 career-card" data-careerno="${career.careerNo }">
				          <div class="card-body">
				            	<strong>${career.company.comName}</strong><br>
				            	${career.startWorkDate } - ${career.retireDate }
				          </div>
				        </div>
				      </c:if>
				    </c:forEach>
				  </c:forEach>
				</div>
         </div>
  
  
  <c:url var="formUrl" value="/member/companyReview/form" />
 
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        
        <a class="btn btn_violet" href="${formUrl }">리뷰 등록하러 가기</a>
        <button id = "submitBtn" type = "button" class = "btn btn_violet">등록하러가기</button>
      </div>
    </div>
  </div>
</div>
	

 <script>
    (function() {
      const cards    = document.querySelectorAll('.career-card');
      const submit   = document.getElementById('submitBtn');
      const baseUrl  = '${formUrl}';
      let selectedNo = null;

      cards.forEach(card => {
        card.addEventListener('click', () => {

          cards.forEach(c => c.classList.remove('active'));
   
          card.classList.add('active');
          
          selectedNo = card.dataset.careerno;
          submit.disabled = false;
        });
      });

      submit.addEventListener('click', () => {
        if (selectedNo) {
      
          window.location.href = baseUrl + "/" +  encodeURIComponent(selectedNo);
        }
      });
    })();
  </script>


</body>