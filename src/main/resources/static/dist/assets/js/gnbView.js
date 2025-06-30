document.addEventListener('DOMContentLoaded', function() {
    const totalMenuBtn = document.getElementById("btn_menu"); // btn_menu 클래스를 가진 버튼의 ID를 정확히 지정하세요.
    const naviTotal = document.querySelector('.navi_total');
    const sriHeader = document.getElementById('sri_header'); // 헤더 요소 (외부 클릭 감지에 사용)

    // 메뉴 내 첫 번째/마지막 포커스 가능한 요소를 찾기 (키보드 접근성 위함)
    // 실제 HTML 구조에 따라 정확하게 요소를 선택해야 합니다.
    const firstFocusableInTotalMenu = naviTotal ? naviTotal.querySelector('a, button, input, select, textarea, [tabindex]:not([tabindex="-1"])') : null;
    const allFocusableInTotalMenu = naviTotal ? Array.from(naviTotal.querySelectorAll('a, button, input, select, textarea, [tabindex]:not([tabindex="-1"])')) : [];
    const lastFocusableInTotalMenu = allFocusableInTotalMenu.length > 0 ? allFocusableInTotalMenu[allFocusableInTotalMenu.length - 1] : null;


    if (totalMenuBtn && naviTotal && sriHeader) {

        /**
         * 전체 메뉴를 여는 함수
         */
        const openTotalMenu = () => {
            totalMenuBtn.setAttribute('aria-expanded', 'true');
            naviTotal.classList.add('expanded');
            // 메뉴가 열렸을 때 첫 번째 포커스 가능한 요소로 포커스 이동
            if (firstFocusableInTotalMenu) {
                firstFocusableInTotalMenu.focus();
            }
            console.log("메뉴가 열렸습니다. GA 이벤트 추적: ga_lead|main-gnb|total_menu|open");
            // 실제 GA 코드 (주석 해제 후 사용):
            // n_trackEvent('ga_lead', 'main-gnb', 'total_menu', 'open');
            // window.dataLayer.push({event: 'ga_lead', category: 'main-gnb', 'event-flow': 'total_menu', 'event-label': 'open'});
        };

        /**
         * 전체 메뉴를 닫는 함수
         */
        const closeTotalMenu = () => {
            totalMenuBtn.setAttribute('aria-expanded', 'false');
            naviTotal.classList.remove('expanded');
            console.log("메뉴가 닫혔습니다. GA 이벤트 추적: ga_lead|main-gnb|total_menu|close");
            // 실제 GA 코드 (주석 해제 후 사용):
            // n_trackEvent('ga_lead', 'main-gnb', 'total_menu', 'close');
            // window.dataLayer.push({event: 'ga_lead', category: 'main-gnb', 'event-flow': 'total-menu', 'event-label': 'close'});
        };

        // 1. `btn_menu` 클릭 이벤트
        totalMenuBtn.addEventListener('click', function() {
            const isExpanded = totalMenuBtn.getAttribute('aria-expanded') === 'true';
            if (isExpanded) {
                closeTotalMenu();
                // 메뉴가 닫힐 때 버튼으로 포커스 복귀
                totalMenuBtn.focus();
            } else {
                openTotalMenu();
            }
        });

        // 2. 메뉴 외부 클릭 시 닫히는 로직
        document.addEventListener('click', function(event) {
            // 클릭된 요소가 `sri_header` 내부에 있는지 확인하여 메뉴 관련 요소가 아닌 경우 닫음
            // `closest`를 사용하여 클릭된 요소의 부모 중 `sri_header`가 있는지 확인
            if (!sriHeader.contains(event.target) && naviTotal.classList.contains('expanded')) {
                closeTotalMenu();
            }
        });

        // 3. 키보드 접근성 로직 (Escape 키)
        document.addEventListener('keydown', function(event) {
            // Escape 키 (keyCode 27)를 눌렀고 메뉴가 열려 있다면
            if (event.keyCode === 27 && naviTotal.classList.contains('expanded')) {
                closeTotalMenu();
                // 메뉴를 닫은 후, 메뉴를 열었던 버튼으로 포커스 이동
                totalMenuBtn.focus();
                event.preventDefault(); // 기본 동작 방지 (예: 브라우저 뒤로가기)
            }

            // Tab 키 포커스 관리 (메뉴 내에서만 순환하도록)
            if (event.keyCode === 9 && naviTotal.classList.contains('expanded')) {
                // Shift + Tab을 눌렀고 포커스가 메뉴의 첫 번째 요소에 있다면, 버튼으로 이동
                if (event.shiftKey) {
                    if (document.activeElement === firstFocusableInTotalMenu) {
                        totalMenuBtn.focus();
                        event.preventDefault();
                    }
                }
                // Tab을 눌렀고 포커스가 메뉴의 마지막 요소에 있다면, 첫 번째 요소로 이동
                else {
                    if (document.activeElement === lastFocusableInTotalMenu) {
                        firstFocusableInTotalMenu.focus();
                        event.preventDefault();
                    }
                }
            }
        });
    } else {
        console.warn("필수 DOM 요소를 찾을 수 없습니다: totalMenuBtn, naviTotal, 또는 sriHeader");
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const utilMenuBtn = document.querySelector('#sri_header .wrap_service .btn_service');
    const wrapService = document.querySelector('#sri_header .wrap_service'); // btn_service를 포함하는 부모 요소

    if (utilMenuBtn && wrapService) {

        /**
         * 서비스 메뉴 버튼의 상태를 토글하는 함수
         */
        const toggleServiceMenuState = () => {
            const isExpanded = utilMenuBtn.getAttribute('aria-expanded') === 'true';

            // aria-expanded 속성 값 토글
            utilMenuBtn.setAttribute('aria-expanded', !isExpanded);
            // btn_service 자체에 'expanded' 클래스 토글 (UI 상태 시각화용)
            utilMenuBtn.classList.toggle('expanded');
            // wrap_service (또는 메뉴 컨테이너)에 'expanded' 클래스를 토글하여 메뉴 표시/숨김 제어
            wrapService.classList.toggle('expanded'); // <-- utilMenu 대신 이 요소를 제어

            console.log(`서비스 메뉴가 ${isExpanded ? '닫혔습니다' : '열렸습니다'}.`);
            // GA 추적 (필요하다면 주석 해제):
            // n_trackEvent('ga_lead', 'main-gnb', 'service_menu', isExpanded ? 'close' : 'open');
            // window.dataLayer.push({event: 'ga_lead', category: 'main-gnb', 'event-flow': 'service_menu', 'event-label': isExpanded ? 'close' : 'open'});
        };

        // 1. `btn_service` 클릭 이벤트
        utilMenuBtn.addEventListener('click', toggleServiceMenuState);

        // 2. 메뉴 외부 클릭 시 닫히는 로직
        document.addEventListener('click', function(event) {
            // 클릭된 요소가 `wrap_service` 영역 밖이고, 메뉴가 열려 있다면 닫음
            if (!wrapService.contains(event.target) && utilMenuBtn.classList.contains('expanded')) {
                // 메뉴 닫는 로직 실행
                utilMenuBtn.setAttribute('aria-expanded', 'false');
                utilMenuBtn.classList.remove('expanded');
                wrapService.classList.remove('expanded');
                console.log("서비스 메뉴가 외부 클릭으로 닫혔습니다.");
            }
        });

        // 3. 키보드 접근성 로직 (Escape 키)
        document.addEventListener('keydown', function(event) {
            // Escape 키 (keyCode 27)를 눌렀고 메뉴가 열려 있다면
            if (event.keyCode === 27 && utilMenuBtn.classList.contains('expanded')) {
                // 메뉴 닫는 로직 실행
                utilMenuBtn.setAttribute('aria-expanded', 'false');
                utilMenuBtn.classList.remove('expanded');
                wrapService.classList.remove('expanded');
                utilMenuBtn.focus(); // 메뉴 닫은 후 버튼으로 포커스 복귀
                console.log("서비스 메뉴가 Escape 키로 닫혔습니다.");
                event.preventDefault(); // 기본 동작 방지
            }
        });

        // 참고: utilMenu를 사용하지 않으므로, focusin/focusout을 통한 포커스 트랩 로직은 제외되었습니다.
        // 만약 메뉴 내용에 포커스 가능한 요소가 있고 Tab 키 접근성이 필요하다면,
        // 해당 메뉴 컨테이너 요소(.wrap_service 또는 그 내부의 실제 메뉴 리스트)에 대한
        // focusin/focusout 및 Tab 키 로직을 별도로 구현해야 합니다.

    } else {
        console.warn("필수 DOM 요소를 찾을 수 없습니다: utilMenuBtn 또는 wrapService");
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // 현재 페이지의 URL을 가져옵니다.
    const path = window.location.pathname.toLowerCase();

    // URL에 'index.jsp' 문자열이 포함되어 있는지 확인합니다.
    // 대소문자 구분을 없애기 위해 toLowerCase()를 사용합니다.
    if (path === '/' || path.endsWith('/index.jsp')) {
        // body 요소에 'index-page' ID를 추가합니다.
        document.body.id = 'sri_main';
        console.log("현재 페이지가 index.jsp이므로 <body>에 'index-page' ID가 추가되었습니다.");
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // 'main' 클래스와 'bubble' 클래스를 모두 가진 모든 요소를 선택합니다.
    const sriHeaderElement  = document.getElementById('sri_header');
    const sriMainElement = document.getElementById('sri_main'); // 'sri_main' ID를 가진 요소

    // 스크롤 임계값 (98px)
    const SCROLL_THRESHOLD = 98;

    /**
     * 특정 요소들에 'fixed' 클래스를 추가하는 함수
     */
    const addFixedClasses = () => {
	    if (sriHeaderElement) { // null 체크 추가
	        sriHeaderElement.classList.add('fixed');
	    }
	    if (sriMainElement) {
	        sriMainElement.classList.add('fixed');
	    }
	};

    /**
     * 특정 요소들에서 'fixed' 클래스를 제거하는 함수
     */
    const removeFixedClasses = () => {
	    if (sriHeaderElement) { // null 체크 추가
	        sriHeaderElement.classList.remove('fixed');
	    }
	    if (sriMainElement) {
	        sriMainElement.classList.remove('fixed');
	    }
	};

    /**
     * 스크롤 이벤트 핸들러
     */
    const handleScroll = () => {
        // 현재 스크롤 위치 (세로)
        const scrollY = window.scrollY || window.pageYOffset;
        /*console.log("현재 스크롤 위치", scrollY);*/

        // 스크롤 위치가 임계값보다 크면 'fixed' 클래스 추가 (아직 없으면)
        if (scrollY > SCROLL_THRESHOLD) {
            // sriMainElement가 없거나, 또는 sriMainElement가 있지만 'fixed' 클래스가 없을 때만 추가
            if (!sriMainElement || !sriMainElement.classList.contains('fixed')) {
                addFixedClasses();
            }
        }
        // 스크롤 위치가 임계값보다 작거나 같으면 'fixed' 클래스 제거 (이미 있으면)
        else { // scrollY <= SCROLL_THRESHOLD (여기에는 scrollY === 0 포함)
            // sriMainElement가 존재하고, 'fixed' 클래스가 있을 때만 제거
            if (sriMainElement && sriMainElement.classList.contains('fixed')) {
                removeFixedClasses();
            }
            if(sriHeaderElement.classList.contains('fixed')){{
                removeFixedClasses();				
			}}
            
        }
    };

    // 스크롤 이벤트 리스너 등록
    window.addEventListener('scroll', handleScroll);

    // 페이지 로드 시 초기 상태 확인 (새로고침 시 스크롤 위치 유지될 수 있으므로)
    handleScroll();
});



