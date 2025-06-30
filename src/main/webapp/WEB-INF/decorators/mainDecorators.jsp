<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<!-- [Head] start -->

<head>
  <title><sitemesh:write property="title" /></title>
  
  <!-- [Meta] -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="띹띹이들 최종 프로젝트">
  <meta name="keywords" content="띹잡, Bootstrap 5, ATS, PMS">
  <meta name="author" content="CodedThemes">
  <sitemesh:write property="head" />
 
  <%@ include file="/WEB-INF/fragments/mainPreStyle.jsp" %>
  
	<c:if test="${not empty message }">
		<script>
			alert('${message}');
		</script>
	</c:if>
	
	
</head>
<!-- [Head] end -->
<!-- [Body] Start -->

<body>

<!-- [ Header Topbar ] start -->
<%@ include file="/WEB-INF/fragments/mainHeader.jsp" %>
<!-- [ Header ] end -->



  <!-- [ Main Content ] start -->
  <div class="inner">
    	<sitemesh:write property="body"/>
  </div>
  <!-- [ Main Content ] end -->
  <%@ include file="/WEB-INF/fragments/mainFooter.jsp" %>

  <%@ include file="/WEB-INF/fragments/mainPostScript.jsp" %>
</body>
<!-- [Body] end -->

</html>