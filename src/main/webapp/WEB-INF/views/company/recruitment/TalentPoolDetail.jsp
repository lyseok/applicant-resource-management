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
</head>
<body>

<div class="resume-box">
    <h2>${detail.userName}님의 이력서</h2>
    <div class="info">
     <p><strong>${detail.userName }</p>
     <%-- <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p>
     <p><strong>${detail. }</p> --%>
    </div>
</div>

</body>
</html>