<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Helpers -->
<script src="/dist/dashboard/assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="/dist/dashboard/assets/js/config.js"></script>
<script src="/dist/assets/js/plugins/jquery-3.7.1.min.js"></script>
<script src="/dist/assets/js/plugins/axios.min.js"></script>
<script src="/dist/dashboard/assets/vendor/libs/popper/popper.js"></script>
<script src="/dist/dashboard/assets/vendor/js/bootstrap.js"></script>
<!-- <script src="https://unpkg.com/perfect-scrollbar@1.5.8/dist/perfect-scrollbar.min.js"></script> -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const menuInnerElement = document.getElementById('my-menu-inner'); // 올바른 ID 또는 클래스 선택

        if (menuInnerElement) {
            new PerfectScrollbar(menuInnerElement, {
                wheelPropagation: false,
                // 필요하다면 다른 옵션 추가: suppressScrollX: true, etc.
            });
            console.log('PerfectScrollbar initialized for #my-menu-inner'); // 디버깅용
        } else {
            console.log('Element #my-menu-inner not found.'); // 디버깅용
        }
    });
</script>

<script src="/dist/dashboard/assets/vendor/js/menu.js"></script>
<!-- endbuild -->
<!-- <script src="/dist/dashboard/assets/vendor/libs/masonry/masonry.js"></script> -->
<!-- Main JS -->
<script src="/dist/dashboard/assets/js/main.js"></script>

<script src="/js/fragments/dashCommon.js"></script>
<script src="/js/fragments/dashHeader.js"></script>

<!-- Page JS -->
<!-- Place this tag in your head or just before your close body tag, -->
<script async defer src="https://buttons.github.io/buttons.is"></script>