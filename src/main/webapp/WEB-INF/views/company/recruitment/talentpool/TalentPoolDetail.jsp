<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.userName}님의 이력서</title>
<style>
    body { font-family: 'Noto Sans KR'; margin: 20px; }
    .resume-box { border: 1px solid #ccc; padding: 20px; border-radius: 10px; max-width: 800px; margin: auto; background: #fff; }
    h2 { border-bottom: 1px solid #ddd; padding-bottom: 10px; }
    .info { margin-top: 10px; line-height: 1.7; }
</style>
<script src="/js/company/recruitment/talentpool/joboffer.js" ></script>
</head>
<body>
<c:if test="${not empty detail}">
<div class="resume-box">
    <h2>${detail.userName}님의 이력서</h2>			
   
    <button onclick="">저장</button>
   
   <div class="user-card" data-user-email="${detail.email }">
  채*전
</div>
    
    
    <div class="info">
     <p><strong>이름 : ${detail.userName }</strong></p>
     <p><strong>생년월일 : ${detail.birth }</strong></p>
     <p><strong>이메일 : ${detail.email }</strong></p>
     <p><strong>전화번호 : ${detail.tel }</strong></p>
    <p><strong> 보유기술 : 
     <c:forEach var="skill" items="${detail.mySkillList }" varStatus="status">
 		<span>
 		<c:if test="${!status.first }"> / </c:if> ${skill.mySkillName }</span>
     </c:forEach>
     </strong></p>
       
     <strong>직무 : <c:forEach var="job" items="${detail.careerList}" varStatus="status">
     <span><c:if test="${!status.first }"></c:if> ${job.jobCode }</span>
     재직기간 : ${job.startWorkDate } ~ ${job.retireDate }	(${job.careerYear} 년)</br>
     </c:forEach>  </strong>
  	 <p><strong>주소 : ${detail.address}</strong></p>
  
  	
    <strong> 보유 자격증 : <c:forEach var="licence" items="${detail.myLicenseList}" varStatus="status">
		<span><c:if test="${!status.first}">/</c:if>${licence.resumeNo }</span>
     </c:forEach></strong>   
     <%-- <p><strong>${detail. }</p>
     <p><strong>${detail. }</p> --%>  
    </div>
    <hr/>
    <a href="/talentpool/list">
    <button>목록</button> </a>
</div>
</c:if>
<c:if test="${empty detail}">
	값이 없다.
</c:if>

</body>
</html>