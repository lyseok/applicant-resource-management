<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!-- Menu -->
  <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
      <a href="/company" class="app-brand-link">
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
        <a href="/company" class="menu-link">
          <i class="menu-icon tf-icons bx bx-garage"></i>
          <div data-i18n="Basic">관리 홈</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="javascript:void(0);" class="menu-link menu-toggle">
          <i class="menu-icon tf-icons bx bx-building"></i>
          <div data-i18n="Account Settings">기업정보관리</div>
        </a>
        <ul class="menu-sub">
          <li class="menu-item">
            <a href="/company/company_management" class="menu-link">
              <div data-i18n="Account">기본정보관리</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="/company/business_registration" class="menu-link">
              <div data-i18n="Notifications">사업자등록증</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="/company/salary_management" class="menu-link">
              <div data-i18n="Notifications">연봉관리</div>
            </a>
          </li>
        </ul>
      </li>
      <li class="menu-item">
        <a href="/company" class="menu-link">
          <i class="menu-icon tf-icons bx bx-calendar-alt"></i>
          <div data-i18n="Basic">일정관리</div>
        </a>
      </li>
      <li class="menu-header small text-uppercase"><span class="menu-header-text">채용관리</span></li>

      <li class="menu-item">
        <a href="/company/recruit_notice/list" class="menu-link">
          <i class="menu-icon tf-icons bx bx-file-detail"></i>
          <div data-i18n="Basic">공고관리</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/recruit_applicant/list" class="menu-link">
          <i class="menu-icon tf-icons bx bx-file-detail"></i>
          <div data-i18n="Basic">채용관리</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/company_exam" class="menu-link">
          <i class="menu-icon tf-icons bx bx-book-open"></i>
          <div data-i18n="Basic">시험관리</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/interview" class="menu-link">
          <i class="menu-icon tf-icons bx bx-user-voice"></i>
          <div data-i18n="Basic">면접관리</div>
        </a>
      </li>

      <li class="menu-header small text-uppercase  off"><span class="menu-header-text">인재풀</span></li>

      <li class="menu-item">
        <a href="/company/talentpool" class="menu-link off">
          <i class="menu-icon tf-icons bx bx-user-search"></i>
          <div data-i18n="Basic">인재검색</div>
          <i class='bx  bx-lock-keyhole'  ></i> 
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/talentpool/scrab" class="menu-link off">
          <i class="menu-icon tf-icons bx bx-user"></i>
          <div data-i18n="Basic">인재관리</div>
          <i class='bx  bx-lock-keyhole'  ></i> 
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/mail/template" class="menu-link off">
          <i class="menu-icon tf-icons bx bx-envelope"></i>
          <div data-i18n="Basic">메일/알림</div>
          <i class='bx  bx-lock-keyhole'  ></i> 
        </a>
      </li>
      <li class="menu-header small text-uppercase"><span class="menu-header-text">상품관리</span></li>

      <li class="menu-item">
        <a href="javascript:void(0);" class="menu-link menu-toggle">
          <i class="menu-icon tf-icons bx bx-dock-top"></i>
          <div data-i18n="Account Settings">상품이용관리</div>
        </a>
        <ul class="menu-sub">
          <li class="menu-item">
            <a href="/company/payment/main" class="menu-link">
              <div data-i18n="Account">나의 상품보기</div>
            </a>
          </li>
        </ul>
        <ul class="menu-sub">
          <li class="menu-item">
            <a href="/company/log/list" class="menu-link">
              <div data-i18n="Account">사용내역 보기</div>
            </a>
          </li>
        </ul>
      </li>

      <li class="menu-item">
        <a href="/company/payment/done/products" class="menu-link">
          <i class="menu-icon tf-icons bx bx-credit-card-alt"></i>
          <div data-i18n="Basic">결제내역조회</div>
        </a>
      </li>

      <li class="menu-header small text-uppercase"><span class="menu-header-text">고객센터</span></li>

      <li class="menu-item">
        <a href="/company/board/admin_board?type=BRDD-003" class="menu-link">
          <i class="menu-icon tf-icons bx bx-envelope-open"></i>
          <div data-i18n="Basic">공지사항</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/board/admin_board?type=BRDD-002" class="menu-link">
          <i class="menu-icon tf-icons bx bx-clipboard-detail"></i>
          <div data-i18n="Basic">FAQ게시판</div>
        </a>
      </li>
      <li class="menu-item">
        <a href="/company/board/admin_board?type=BRDD-001" class="menu-link">
          <i class="menu-icon tf-icons bx bx-message-question-mark"></i>
          <div data-i18n="Basic">문의게시판</div>
        </a>
      </li>
      <!-- 여기까지 사이드바 작업하는 곳 -->
    </ul>
  </aside>
  <!-- / Menu -->