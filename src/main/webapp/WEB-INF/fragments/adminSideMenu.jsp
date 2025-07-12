<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- Menu -->
 <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
   <div class="app-brand demo">
     <a href="/admin" class="app-brand-link">
       <span class="app-brand-logo demo">
         <img src="/dist/assets/images/logo.png" alt="logo">
       </span>
     </a>

     <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
       <i class="bx bx-chevron-left bx-sm align-middle"></i>
     </a>
   </div>

   <div class="menu-inner-shadow"></div>

   <ul class="menu-inner py-1">
   
	<!-- 여기부터 사이드바 작업하는 곳 -->
	
	<li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-garage"></i>
         <div data-i18n="Basic">관리 홈</div>
       </a>
     </li>
	<li class="menu-item">
       <a href="javascript:void(0);" class="menu-link menu-toggle">
         <i class="menu-icon tf-icons bx bx-file-detail"></i>
         <div data-i18n="Account Settings">채용관리</div>
       </a>
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Account">공고관리</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Notifications">지원자관리</div>
           </a>
         </li>
       </ul>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">회원관리</span></li>
     
     <li class="menu-item">
       <a href="/admin/common/users?userRole=ROLE_USER" class="menu-link">
         <i class="menu-icon tf-icons bx bx-user"></i>
         <div data-i18n="Basic">일반회원</div>
       </a>
     </li>
     <li class="menu-item">
       <a href="/admin/common/users?userRole=ROLE_COMPANY" class="menu-link">
         <i class="menu-icon tf-icons bx bx-building"></i>
         <div data-i18n="Basic">기업회원</div>
       </a>
     </li>
     <li class="menu-item">
       <a href="/admin/common/users?userRole=ROLE_ADMIN" class="menu-link">
         <i class="menu-icon tf-icons bx bx-lock-keyhole-open"></i>
         <div data-i18n="Basic">관리자</div>
       </a>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">사이트관리</span></li>
     
	 <li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-menu-select"></i>
         <div data-i18n="Basic">메뉴관리</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-folder-code"></i>
         <div data-i18n="Basic">공통코드관리</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="javascript:void(0);" class="menu-link menu-toggle">
         <i class="menu-icon tf-icons bx bx-chart-trend"></i>
         <div data-i18n="Account Settings">통계관리</div>
       </a>
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Account">직무통계</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Notifications">연봉통계</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Connections">태그통계</div>
           </a>
         </li>
       </ul>
     </li>
	
     <li class="menu-header small text-uppercase"><span class="menu-header-text">게시판관리</span></li>
     
	 <li class="menu-item">
       <a href="/ajax/company/board/admin_board/BRDD-003" class="menu-link">
         <i class="menu-icon tf-icons bx bx-envelope-open"></i>
         <div data-i18n="Basic">공지사항</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/ajax/company/board/admin_board/BRDD-002" class="menu-link">
         <i class="menu-icon tf-icons bx bx-clipboard-detail"></i>
         <div data-i18n="Basic">FAQ게시판</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/ajax/company/board/admin_board/BRDD-001" class="menu-link">
         <i class="menu-icon tf-icons bx bx-message-question-mark"></i>
         <div data-i18n="Basic">문의게시판</div>
       </a>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">관리</span></li>
     
	 <li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-collection"></i>
         <div data-i18n="Basic">프로젝트 관리</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-collection"></i>
         <div data-i18n="Basic">이력서 관리</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/admin" class="menu-link">
         <i class="menu-icon tf-icons bx bx-collection"></i>
         <div data-i18n="Basic">자기소개서 관리</div>
       </a>
     </li>
     
     
    
     
     <li class="menu-item">
       <a href="javascript:void(0);" class="menu-link menu-toggle">
         <i class="menu-icon tf-icons bx bx-dock-top"></i>
         <div data-i18n="Account Settings">요청관리</div>
       </a>
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Account">면접후기관리</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Notifications">기업리뷰관리</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/admin" class="menu-link">
             <div data-i18n="Connections">신고관리</div>
           </a>
         </li>
       </ul>
     </li>
	<!-- 여기까지 사이드바 작업하는 곳 -->
     <span id="logoutBtn">로그아웃</span>
     
   </ul>
 </aside>
 <!-- / Menu -->
    