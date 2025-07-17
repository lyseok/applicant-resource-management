<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="sri_header" class="main bubble">
    <div class="wrap_header">
        <h1>
            <a href="/" class="bi" aria-label="띹잡" data-nav-track="ga_lead|main-gnb|global_menu|ci">
                <span class="frame"></span>
            </a>
        </h1>
        <div class="search">
            <form>
            	<div id="btn_search" class="btn_search" >
            		<div class="custom-select">
	            		<select name="search_sel">
	            			<option>직업별</option>
	            			<option>지역별</option>
	            		</select>
	            		<div class="select-styled">
	            			<span>선택</span>
	            		</div>
	            		<ul class="select-options">
					    	<li data-value="Strawberries">직업별</li>
					    	<li data-value="Lemon">지역별</li>
	            		</ul>
					</div>
            		<input type="text" placeholder="나에게 딱 맞는 커리어만 매치, 띹잡!" class="keyword static" />
            		<input type="text" placeholder="띹잡, 커리어!" class="keyword fixed" />
	                <span class="material-symbols-outlined search_icon">search</span>
                </div>
            </form>
        </div>
        <div class="utility">
            <div class="sign">
            
            
	            <sec:authorize access="isAuthenticated()">
	            	<%-- 로그인이 되어 있을때 --%>
	                <button type="button" class="btn_sign signin member_btn">
									    <span class="user_photo">
									      <span id="gnb_personnal_photo_area_span" class="img"></span>
									    </span>
							        <span class="user_name" id="user_name"><!-- 회원이름 들어가는 곳 --></span>
									    <span class="material-symbols-outlined">arrow_drop_down</span>
									</button>
	                <div class="layer_member" id="displayMemBtn" style="display:none;">
			            <ul>
			                <li>
			                    <a href="javascript:void(0)">
			                        <span class="material-symbols-outlined">settings</span> 
			                        <span class="txt">계정정보 설정</span>
			                    </a>
			                </li>
			                <li>
			                    <a href="javascript:void(0)">
			                        <span class="material-symbols-outlined">mail</span> 
			                        <span class="txt">메일관리</span>
			                    </a>
			                </li>
			                <li>
			                    <a href="javascript:void(0)">
			                        <span class="material-symbols-outlined">lock</span> 
			                        <span class="txt">비밀번호 변경</span>
			                    </a>
			                </li>
			                <li>
			                    <a href="/mypage">
			                        <span class="material-symbols-outlined">person</span> 
			                        <span class="txt">마이페이지</span>
			                    </a>
			                </li>
			                <li>
			                    <div class="logoutBtn">
			                        <a href="javascript:void(0)">
			                            <span class="material-symbols-outlined">logout</span> 
			                            <span class="txt logoutBtn">로그아웃</span>
			                        </a>
			                    </div>
			                </li>
			            </ul>
			        </div>
	                <hr>
	                <span class="btn_sign signin logoutBtn">로그아웃</span>
				</sec:authorize>
				
				<sec:authorize access="!isAuthenticated()">
	            	<%-- 로그인이 되어 있지 않을때 --%>
	                <a href="/login" class="btn_sign signin">로그인</a>
	                <hr>
	                <a href="/member_signup" class="btn_sign signup">회원가입</a>
		            <div class="wrap_service">
		                <button class="btn_service" type="button" aria-expanded="false">
		                    기업서비스
		                    <span class="material-symbols-outlined">keyboard_arrow_down</span>
		                </button>
		                <div class="layer_member">
		                    <ul class="services">
		                        <li class="half_col">
		                            <a href="/login">로그인</a>
		                            <a href="/companysignup" data-nav-track="ga_lead|main-gnb|layer_comp_service|company_join">회원가입</a>
		                        </li>
		                        <li><a href="javascript:void(0)">기업홈</a></li>
		                        <li><a href="javascript:void(0)">공고 등록</a></li>
		                        <li><a href="javascript:void(0)">지원자 관리</a></li>
		                        <li><a href="javascript:void(0)">인재풀</a></li>
		                        <li><a href="javascript:void(0)">스마트 리크루터</a></li>
		                        <li><a href="javascript:void(0)">인적성 · 평가도구</a></li>
		                        <li><a href="javascript:void(0)">HR매거진</a></li>
		                        <li><a href="javascript:void(0)">채용상품</a></li>
		                        <li><a href="javascript:void(0)">띹잡 비즈니스</a></li>
		                    </ul>
		                </div>
		            </div>
				</sec:authorize>

            </div>
        </div>
        <div class="navigation">
            <button type="button" class="btn_menu" id="btn_menu">
                <span class="material-symbols-outlined">notes</span>
                <svg aria-hidden="true" focusable="false">
                    <use xlink:href="#svg_gnb_total_on"></use>
                </svg>
                <span class="blind">전체메뉴</span>
            </button>
            <div class="wrap_gnb">
                <div class="major recruit">
                    <a class="depth1" href="/board/basic">
                        <span class="txt">채용정보</span>
                    </a>
                    <ul class="depth2">
                        <li>
                            <a href="javascript:void(0)">
                                <span class="txt">지역별</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">
                                <span class="txt">직업별</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">
                                <span class="txt">인기공고</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="javascript:void(0)">
                        <span class="txt">신입·인턴</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="/member/realTimeRecruitment"><span class="txt">실시간 공고</span></a></li>
                        <li><a href="/member/newEmployee"><span class="txt">신입연봉</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="javascript:void(0)">
                        <span class="txt">기업·연봉</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="/member/company_review"><span class="txt">기업리뷰</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉정보</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">면접후기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">합격 자소서</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="javascript:void(0)">
                        <span class="txt">프로젝트</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="/board/project"><span class="txt">주제별</span></a></li>
                        <li><a href="/board/project"><span class="txt">태그별</span></a></li>                    
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="/member/board/commu_board?type=CATE-003">
                        <span class="txt">커뮤니티</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="/member/board/commu_board?type=CATE-001"><span class="txt">취업준비</span></a></li>
                        <li><a href="/member/board/commu_board?type=CATE-002"><span class="txt">회사문화</span></a></li>
                        <li><a href="/member/board/commu_board?type=CATE-003"><span class="txt">자유게시판</span></a></li>
                    </ul>
                </div>
                <div class="major">
	                <a class="depth1" href="javascript:void(0)">
	                    <span class="txt">취업TOOL</span>
	                </a>
	                <ul class="depth2">
                        <li><a href="/spelling"><span class="txt">맞춤법 검사기</span></a></li>
                        <li><a href="/count_text"><span class="txt">글자 수 세기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉계산</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실수령액 계산기</span></a></li>
                        <li><a href="/countyear"><span class="txt">연차계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실업급여계산기</span></a></li>
	                </ul>
	            </div>
                <div class="major">
	                <a class="depth1" href="javascript:void(0)">
	                    <span class="txt">고객센터</span>
	                </a>
	                <ul class="depth2">
                        <li><a href="/member/board/commu_board?type=BRDD-003"><span class="txt">공지사항</span></a></li>
                        <li><a href="/member/board/commu_board?type=BRDD-001"><span class="txt">문의게시판</span></a></li>
	                </ul>
	            </div>
            </div>
            
            <div class="familysite">
                <a class="item" href="javascript:void(0)" rel="noreferrer">
                    <span class="txt">인기 직업순위 들어갈지도~?</span>
                </a>
            </div>
        </div>
    </div>
    <div class="navi_total">
        <div id="sri_index">
            <div class="panel_menu menu">
                <div class="major recruit">
                    <span class="copy">공고를 찾는다면</span>
                    <span class="depth1"><span class="txt">채용정보</span></span>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">지역별</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">직업별</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">인기공고</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">신입·인턴</span></span>
                    <ul class="depth2">
                        <li><a href="/member/realTimeRecruitment"><span class="txt">실시간 공고</span></a></li>
                        <li><a href="/member/newEmployee"><span class="txt">신입연봉</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">기업·연봉</span></span>
                    <ul class="depth2">
                        <li><a href="/member/company_review"><span class="txt">기업리뷰</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉정보</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">면접후기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">합격 자소서</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">프로젝트</span></span>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">주제별</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">태그별</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">커뮤니티</span></span>
                    <ul class="depth2">
                        <li><a href="/member/board/commu_board?type=CATE-001" class="new"><span class="txt">취업준비</span></a></li>
                        <li><a href="/member/board/commu_board?type=CATE-002"><span class="txt">회사문화</span></a></li>
                        <li><a href="/member/board/commu_board?type=CATE-003"><span class="txt">자유게시판</span></a></li>
                    </ul>
                </div>
                <div class="minor">
                    <ul class="items">
                        <li><a href="/member/board/admin_board?type=BRDD-002"><span class="material-symbols-outlined">headset_mic</span>고객센터</a></li>
                        <li><a href="/member/board/admin_board?type=BRDD-003&tab=notice"><span class="material-symbols-outlined">campaign</span>공지사항</a></li>
                        <li><a href="/member/board/admin_board?type=BRDD-003&tab=event"><span class="material-symbols-outlined">celebration</span>이벤트</a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">취업TOOL</span></span>
                    <ul class="depth2">
                        <li><a href="/spelling" class="new"><span class="txt">맞춤법 검사기</span></a></li>
                        <li><a href="/count_text"><span class="txt">글자 수 세기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉계산</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실수령액 계산기</span></a></li>
                        <li><a href="/countyear"><span class="txt">연차계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실업급여계산기</span></a></li>
                    </ul>
                </div>
                <div class="personalize">
                    <a href="/zf_user/member/persons/main" class="myhome">MY<svg class="arrow" aria-hidden="true" focusable="false">
                            <use xlink:href="#svg_gnb_profile_bracket"></use>
                        </svg></a>
                    <ul class="items">
						<li><a href="/mypage/resume/list" data-nav-track="ga_lead|main-gnb|total_menu|resume-manage">이력서 관리</a></li>
						<li><a href="/member/mypage/scrab_recruit" data-nav-track="ga_lead|main-gnb|total_menu|scrap">스크랩</a></li>
						<li><a href="/zf_user/persons/apply-status-list" data-nav-track="ga_lead|main-gnb|total_menu|apply_list">지원현황</a></li>
						<li><a href="/zf_user/jobs/read-jobs" data-nav-track="ga_lead|main-gnb|total_menu|recent">최근본</a></li>
                    </ul>
                </div>
            </div>
            
        </div>
    </div>
    
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // 버튼과 레이어 변수 저장
        const memberBtn = document.querySelector('.member_btn');
        const memberLayer = document.querySelector('.layer_member');

        // 멤버 버튼 클릭 이벤트
        memberBtn.addEventListener('click', function(event) {
            event.stopPropagation();
            const isOpen = memberLayer.style.display === 'block';
            // 토글
            memberLayer.style.display = isOpen ? 'none' : 'block';
        });

        // 외부 클릭시 닫기 (버튼/레이어 아닌 부분 클릭시)
        document.addEventListener('click', function(event) {
            if (!memberBtn.contains(event.target) && !memberLayer.contains(event.target)) {
                memberLayer.style.display = 'none';
            }
        });
        axios.get('/ajax/userinfo')
            .then(res => {
            const data = res.data;
            let name = '비회원';
            if (data.userType === 'company') name = data.userName;
            else if (data.userType === 'admin') name = '관리자';
            else if (data.userType === 'member') name = data.userName;
            document.getElementById('user_name').textContent = name;
        });
    });

    const logoutBtnEls = document.querySelectorAll('.logoutBtn');

    logoutBtnEls.forEach(btn => {
        btn.addEventListener('click', () => {
            console.log('test');
            axios.post("/common/auth/revoke", {}, {
                withCredentials: true
            }).then(resp => location.href = "/");
        });
    });

    </script>
</header>

