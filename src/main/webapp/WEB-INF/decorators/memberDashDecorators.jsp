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
  		<%@ include file="/WEB-INF/fragments/memberSideMenu.jsp" %>
        <div class="layout-page">
			
          <div class="content-wrapper">
        	<%@ include file="/WEB-INF/fragments/dashHeader.jsp" %>
            <div class="container-xxl flex-grow-1 container-p-y">
              <!-- Layout Demo -->
              <div class="layout-demo-wrapper" data-aos="fade-down" data-aos-duration="800">
    				<sitemesh:write property="body"/>
    		  </div>
    		</div>
    	  </div>          
        </div>        
      </div>
    </div>
	
  <%@ include file="/WEB-INF/fragments/dashPostScript.jsp" %>
  <%@ include file="/WEB-INF/fragments/loading.jsp" %>
<script>
  axios.get('/ajax/userinfo')
    .then(res => {
      const data = res.data;
      let name = '비회원';
      if (data.userType === 'company') name = data.userName;
      else if (data.userType === 'admin') name = '관리자';
      else if (data.userType === 'member') name = data.userName;
      document.getElementById('user_name').textContent = name;
  });
</script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    AOS.init();
 // 강제로 스크롤 트리거 유도
    setTimeout(() => {
      AOS.refresh(); // 요소 위치 다시 계산
      window.scrollBy(0, 1); // 스크롤 이벤트 강제 트리거
      window.scrollBy(0, -1);
    }, 300);
  });
</script>
</body>
</html>