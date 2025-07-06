<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- [Favicon] icon -->
<link rel="icon" href="${pageContext.request.contextPath }/dist/assets/images/favicon.png" type="image/x-icon"> <!-- [Google Font] Family -->

<!-- plugins  -->
<link rel="stylesheet" href="/dist/assets/css/plugins/bootstrap.min.css" >
<link rel="stylesheet" href="/dist/assets/css/plugins/swiper.min.css" >

<!-- fonts -->
<link rel="stylesheet" href="/dist/assets/css/fonts/pretendard.css" >
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" rel="stylesheet" />



<c:if test="${boardCss}">
	<!-- 서브페이지 -->
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/boardReset.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/jobs_recruit.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/newcomer.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/common.css">
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css" >
	<link rel="stylesheet" href="/dist/assets/css/board/jop_view.css" >
</c:if>

<!-- layout -->
<link rel="stylesheet" href="/dist/assets/css/header.css" >
<link rel="stylesheet" href="/dist/assets/css/header_gnb.css" >
<link rel="stylesheet" href="/dist/assets/css/footer.css" >
<link rel="stylesheet" href="/dist/assets/css/layout.css" >
<link rel="stylesheet" href="/dist/assets/css/pattern.css" />
<link rel="stylesheet" href="/dist/assets/css/component.css" />

<link rel="stylesheet" href="/dist/assets/css/common.css" >


<link rel="stylesheet" href="/dist/dashboard/assets/vendor/css/dashHeader.css"/>