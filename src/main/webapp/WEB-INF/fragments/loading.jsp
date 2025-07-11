<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 로딩 스피너 (기본은 숨김) -->
<div id="loadingSpinner" class="position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center"
    style="background:rgba(255,255,255,0.5); z-index:1055; display:none !important; ">
  <div class="text-center">
    <div class="spinner-border text-success mb-3" style="width: 4rem; height: 4rem;" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="fw-bold fs-4 text-success">로딩중입니다...</div>
  </div>
</div>