<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><sitemesh:write property="title" /></title>	
	<%@ include file="/WEB-INF/fragments/dashPreStyle.jsp" %>
	
	
	<sitemesh:write property="head" />
</head>
<body>
    <div class="layout-wrapper layout-content-navbar layout-menu-fixed">
      <div class="layout-container">
        <!-- Layout container -->
  		<%@ include file="/WEB-INF/fragments/companySideMenu.jsp" %>
        <div class="layout-page">
			
          <div class="content-wrapper">
        	<%@ include file="/WEB-INF/fragments/dashheader.jsp" %>
            <div class="container-xxl flex-grow-1 container-p-y">
              <!-- Layout Demo -->
              <div class="layout-demo-wrapper">
    				<sitemesh:write property="body"/>
    		  </div>
    		</div>
    	  </div>          
        </div>        
      </div>
    </div>
	
  <%@ include file="/WEB-INF/fragments/dashPostScript.jsp" %>
</body>
</html>