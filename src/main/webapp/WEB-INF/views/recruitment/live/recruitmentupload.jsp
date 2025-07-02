<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 공고</title>
</head>
<body>
	<c:forEach var="notice" items="${recruitmentList }">
	<li>
	<strong>${recruitment_notice.recruitment_no}</strong>	
	<span>${recruitment_notice.recruitment_startdate}</span>
	</li>
	</c:forEach>
</body>
</html>