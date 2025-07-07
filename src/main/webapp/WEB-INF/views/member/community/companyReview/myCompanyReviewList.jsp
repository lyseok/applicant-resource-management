<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
      <style>

    .review-card-body {
      display: flex;
      align-items: center;
      padding: 1rem;
    }
 
    .delete-btn {
      margin-left: auto;
      background-color: #dc3545;
      color: #fff;
      border: none;
      padding: 0.25rem 0.5rem;
      font-size: 0.875rem;
      cursor: pointer;
      border-radius: 0.25rem;
    }
    .delete-btn:hover {
      background-color: #c82333;
    }
  </style>

</head>
<body>
  <div >
    <p class="h1">${member.memName } 님의 기업 리뷰 </p>
   
  </div>

 <c:forEach items="${myReviewList}" var="mList">
  <div class="card">
    <div class="card-body review-card-body">
      <span>${mList.company.comName}</span>
      <button type="button" class="delete-btn">
        삭제
      </button>
    </div>
  </div>
</c:forEach>
   
</body>
</html>