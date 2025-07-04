<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- Menu -->
 <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
   <div class="app-brand demo">
     <a href="index.html" class="app-brand-link">
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
       <a href="/mypage" class="menu-link">
         <i class="menu-icon tf-icons bx bx-garage"></i>
         <div data-i18n="Basic">마이 홈</div>
       </a>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">이력서</span></li>
     <li class="menu-item">
       <a href="/mypage/resume/list" class="menu-link menu-toggle">
       	 <i class="menu-icon tf-icons bx bx-building"></i>
         <div data-i18n="Account">이력서 관리</div>
       </a>
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Account">이력서 목록</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Notifications">이력서 등록</div>
           </a>
         </li>
      </ul>
     </li>
     <li class="menu-item">
       <a href="/mypage/resume/list" class="menu-link menu-toggle">
       	 <i class="menu-icon tf-icons bx bx-building"></i>
         <div data-i18n="Account">자소서 관리</div>
       </a>   
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Account">자소서 목록</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Notifications">자소서 등록</div>
           </a>
         </li>
      </ul>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">프로젝트</span></li>
      <li class="menu-item">
       <a href="/mypage/resume/list" class="menu-link menu-toggle">
       	 <i class="menu-icon tf-icons bx bx-building"></i>
         <div data-i18n="Account">프로젝트 관리</div>
       </a>
       <ul class="menu-sub">
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Account">프로젝트 공고관리</div>
           </a>
         </li>
         <li class="menu-item">
           <a href="/company" class="menu-link">
             <div data-i18n="Notifications">프로젝트 지원자 관리</div>
           </a>
         </li>
      </ul>
     </li>
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">맞춤 공고함</span></li>
	 <li class="menu-item">
       <a href="/mypage" class="menu-link">
         <i class="menu-icon tf-icons bx bx-user-search"></i>
         <div data-i18n="Basic">스크랩</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/mypage" class="menu-link">
         <i class="menu-icon tf-icons bx bx-user"></i>
         <div data-i18n="Basic">관심공고</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="/mypage" class="menu-link">
         <i class="menu-icon tf-icons bx bx-user"></i>
         <div data-i18n="Basic">최근 본 공고</div>
       </a>
     </li>
	 <li class="menu-item">
       <a href="javascript:void(0);" class="menu-link">
         <i class="menu-icon tf-icons bx bx-envelope"></i>
         <div data-i18n="Account Settings">받은 제안</div>
       </a>
     </li>
     
     
     
     <li class="menu-header small text-uppercase"><span class="menu-header-text">지원내역</span></li>
	 <li class="menu-item">
       <a href="/mypage" class="menu-link">
         <i class="menu-icon tf-icons bx bx-shopping-bag-alt"></i>
         <div data-i18n="Basic">지원 목록</div>
       </a>
     </li>
     
	<!-- 여기까지 사이드바 작업하는 곳 -->
	<span id="logoutBtn">로그아웃</span>
   </ul>
 </aside>
 <!-- / Menu -->
    