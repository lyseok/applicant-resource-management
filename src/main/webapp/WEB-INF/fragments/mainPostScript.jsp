<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script src="/dist/assets/js/plugins/bootstrap.min.js"></script>
<script src="/dist/assets/js/plugins/jquery-3.7.1.min.js"></script>
<script src="/dist/assets/js/plugins/axios.min.js"></script>
<script src="/dist/assets/js/plugins/swiper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>


<script src="/dist/assets/js/gnbView.js"></script>
<script src="/dist/assets/js/wings.js"></script>
<c:if test="${boardCss}">
	<script src="/dist/assets/js/boardView.js"></script>
</c:if>
<c:if test="${searchBar}">
	<script src="/dist/assets/js/search/search.js"></script>
</c:if>
<script src="/dist/assets/js/login/login.js"></script>
<script src="/dist/assets/js/join/member_join.js"></script>
<script src="/dist/assets/js/common.js"></script>
<script src="/js/fragments/mainHeader.js"></script>



<script>
	document.addEventListener("DOMContentLoaded", function(){
  	AOS.init();
	})
</script>