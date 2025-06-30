<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header id="sri_header" class="main bubble">
    <div class="wrap_header">
        <h1>
            <a href="/" class="bi" aria-label="띹잡" data-nav-track="ga_lead|main-gnb|global_menu|ci">
                <span class="frame">
                </span>
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
            		<input type="text" placeholder="커리어 매치, 띹잡!" class="keyword fixed" />
	                <span class="material-symbols-outlined search_icon">search</span>
                </div>
            </form>
        </div>
        <div class="utility">
            <div class="sign">
                <a href="/login" class="btn_sign signin">로그인</a>
                <hr>
                <a href="/signUp" class="btn_sign signup">회원가입</a>
            </div>
            <div class="wrap_service">
                <button class="btn_service" type="button" aria-expanded="false">
                    기업서비스
                    <span class="material-symbols-outlined">keyboard_arrow_down</span>
                </button>
                <div class="layer_member">
                    <ul class="services">
                        <li class="half_col">
                            <a href="/login">로그인</a>
                            <a href="/signUp/corp" data-nav-track="ga_lead|main-gnb|layer_comp_service|company_join">회원가입</a>
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
                        <li><a href="javascript:void(0)"><span class="txt">실시간 공고</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">신입연봉</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="javascript:void(0)">
                        <span class="txt">기업·연봉</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">기업리뷰</span></a></li>
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
                        <li><a href="javascript:void(0)"><span class="txt">주제별</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">태그별</span></a></li>                    
                    </ul>
                </div>
                <div class="major">
                    <a class="depth1" href="javascript:void(0)">
                        <span class="txt">커뮤니티</span>
                    </a>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">취업준비</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">회사문화</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">자유게시판</span></a></li>
                    </ul>
                </div>
                <div class="major">
	                <a class="depth1" href="javascript:void(0)">
	                    <span class="txt">취업TOOL</span>
	                </a>
	                <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">맞춤법 검사기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">글자 수 세기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉계산</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실수령액 계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연차계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실업급여계산기</span></a></li>
	                </ul>
	            </div>
                <div class="major">
	                <a class="depth1" href="javascript:void(0)">
	                    <span class="txt">고객센터</span>
	                </a>
	                <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">공지사항</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">문의게시판</span></a></li>
	                </ul>
	            </div>
            </div>
            
            <div class="familysite">
                <a class="item" href="javascript:void(0)" target="_blank" rel="noreferrer">
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
                        <li><a href="javascript:void(0)"><span class="txt">실시간 공고</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">신입연봉</span></a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">기업·연봉</span></span>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)"><span class="txt">기업리뷰</span></a></li>
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
                        <li><a href="javascript:void(0)" class="new"><span class="txt">취업준비</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">회사문화</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">자유게시판</span></a></li>
                    </ul>
                </div>
                <div class="minor">
                    <ul class="items">
                        <li><a href="javascript:void(0)"><span class="material-symbols-outlined">headset_mic</span>고객센터</a></li>
                        <li><a href="javascript:void(0)"><span class="material-symbols-outlined">campaign</span>공지사항</a></li>
                        <li><a href="javascript:void(0)"><span class="material-symbols-outlined">celebration</span>이벤트</a></li>
                    </ul>
                </div>
                <div class="major">
                    <span class="depth1"><span class="txt">취업TOOL</span></span>
                    <ul class="depth2">
                        <li><a href="javascript:void(0)" class="new"><span class="txt">맞춤법 검사기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">글자 수 세기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연봉계산</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실수령액 계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">연차계산기</span></a></li>
                        <li><a href="javascript:void(0)"><span class="txt">실업급여계산기</span></a></li>
                    </ul>
                </div>
                <div class="personalize">
                    <a href="/zf_user/member/persons/main" class="myhome" data-nav-track="ga_lead|main-gnb|total_menu|myhome">MY<svg class="arrow" aria-hidden="true" focusable="false">
                            <use xlink:href="#svg_gnb_profile_bracket"></use>
                        </svg></a>
                    <ul class="items">
                        <li><a href="/zf_user/resume/resume-manage" data-nav-track="ga_lead|main-gnb|total_menu|resume-manage">이력서 관리</a></li>
                        <li><a href="/zf_user/persons/scrap-recruit" data-nav-track="ga_lead|main-gnb|total_menu|scrap">스크랩</a></li>
                        <li><a href="/zf_user/persons/apply-status-list" data-nav-track="ga_lead|main-gnb|total_menu|apply_list">지원현황</a></li>
                        <li><a href="/zf_user/jobs/read-jobs" data-nav-track="ga_lead|main-gnb|total_menu|recent">최근본</a></li>
                    </ul>
                </div>
            </div>
            
        </div>
    </div>
</header>

