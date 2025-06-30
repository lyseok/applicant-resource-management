<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<head>
	<title>로그인</title>
	<link rel="stylesheet" href="/dist/assets/css/login/login.css" >
</head>
<body>
	<div id="login">
		<div id="container">
		    <div class="secLogin">
		        <div class="logWrap" id="loginFormWrite">
		            <h2 class="blind">로그인</h2>
		            <form name="form" id="login-form" method="post" action="/Login/Login.asp" onsubmit="return login_send();">
		                <fieldset class="login-form">
		                    <legend class="blind">로그인 입력폼</legend>
		                    <input type="hidden" name="re_url" id="re_url" value="/">
		                    <input type="hidden" name="idx" value="">
		                    <input type="hidden" name="Div" value="">
		                    <input type="hidden" name="BNo" value="">
		                    <input type="hidden" id="IP_ONOFF" name="IP_ONOFF" value="Y">
		                    <input type="hidden" name="Login_Stat" id="Login_Stat" value="">
		                    <input type="hidden" name="LoginPage" value="/Login/Login_Tot.asp">
		                    <input type="hidden" name="returnHost" id="returnHost" value="http://www.jobkorea.co.kr">
		                    <input type="hidden" name="jkwww_host" id="jkwww_host" value="https://www.jobkorea.co.kr">
		                    <input type="hidden" name="m_type" id="m_type" value="">
		                    <input type="hidden" name="NaverReferReURL_Stat" id="NaverReferReURL_Stat" value="">
		                    <input type="hidden" name="DB_Name" id="DB_Name" value="GG">
		                    <input type="hidden" name="ignoreSession" id="ignoreSession" value="">
		                    <input type="hidden" name="CapchaCheckUseTime" id="CapchaCheckUseTime" value="False">
		                    <input type="hidden" name="TargetDate" id="TargetDate" value="">
		                    <!-- 회원선택-->
		                    <section class="login-tab">
		                        <ul id="devMemTab">
		                            <li class="on" role="tab" data-tab="tab1"><a href="#" data-m-type="M">개인회원</a></li>
		                            <li role="tab" data-tab="tab2"><a href="#" data-m-type="Co">기업회원</a></li>
		                        </ul>
		                    </section>
		
		                    <!-- 회원선택 - 기업회원 <-> 서치펌 회원-->
		                    <section class="login-switch devHide" id="devCoTab">
		                        <span class="login-member-switch">
		                            <input type="checkbox" id="btnCorpMemberType" name="" class="skip">
		                            <label for="btnCorpMemberType" class="button-smartfilter mtuSpImgBefore">
		                                <p class="text-switch">서치펌회원</p>
		                            </label>
		                        </span>
		                    </section>
		
		                    <!-- 로그인 입력 폼 - 에러 처리 <div class="row-input error"> -->
		                    <section class="login-input ">
		                        <label for="M_ID" id="lb_id" class="label-form label-id"></label>
		                        <!--<input type="text" class="inpTxt  input-id" placeholder="아이디" name="M_ID" id="M_ID" size="16" maxlength="20" title="아이디 입력" style="ime-mode:inactive;" value="" required>-->
		                        <input type="text" class="inpTxt  input-id" placeholder="NO.1 띹잡 통합 ID" name="M_ID" id="M_ID" size="16" maxlength="20" title="아이디 입력" style="ime-mode:inactive;" value="" required="">
		                        <label for="M_PWD" id="lb_pw" class="label-form label-password"><span class="material-symbols-outlined">lock</span></label>
		                        <input type="password" class="inpTxt input-password" placeholder="비밀번호" name="M_PWD" id="M_PWD" size="16" title="비밀번호 입력" required="">
		                        <button type="submit" class="login-button">로그인</button>
		                        <div class="text-error"></div>
		                        <!-- CapsLock 레이어 - <div class="login-capslock" 에 on 클래스 추가 시 활성화 -->
		                        <div class="login-capslock" id="ipNotice">
		                            <em>Caps Lock</em>이 켜져 있습니다.<span class="mainIcn mainIcnArrUp"></span>
		                        </div>
		                    </section>
		
		                    <!-- 로그인 유지, IP 보안 ON/OFF -->
		                    <section class="login-convenience">
		                        <div class="login-convenience-keep">
		                            <div class="keep-id" style="display:  ">
		                                <input type="checkbox" name="lb_idSave" id="lb_idSave" value="Y" class="skip">
		                                <label for="lb_idSave">로그인 유지</label>
		                            </div>
		                        </div>
		                        <!-- <span class="txtBar"> | </span> -->
		                        <div class="login-convenience-security">
		                            <a href="javascript:void(0)" class="devSecPop">IP보안</a>
		                            <button type="button" id="btnSecurity" class="security-button on" onclick="ipCheckSetting();setIPUI();"><span class="setOn">ON</span></button>
		                        </div>
		                    </section>
		
		                    <!--// 그림문자 입력폼 -->
		                    <section class="login-captcha devLoginImgTxt devHide">
		                        <div class="captcha">
		                            <span class="captcha-image"><img id="imgCaptcha" src="/login/captcha.asp" width="198" height="40" alt="그림문자"></span>
		                            <a href="javascript:void(0)" class="captcha-image-change" onclick="RefreshImage('imgCaptcha')"><span class="skip">새로고침</span></a>
		                        </div>
		                        <label for="gtxt" class="blind">그림문자</label>
		                        <input type="text" class="inpTxt" name="gtxt" id="gtxt" size="16" title="그림문자 입력" autocomplete="off" placeholder="위 문자를 보이는 대로 입력해 주세요.">
		                        <!--[개발] end 추가하면 배경텍스트 사라짐 -->
		                    </section>
		                    <!-- 그림문자 입력폼 //-->
		
		
		                    <section class="login-social">
		                        <ul>
		                            <li><a href="javascript:;" id="btnNvLogin" class="naver" onclick="_LA.EVT('4287')">네이버 로그인</a></li>
		                            <li><a href="javascript:;" id="btnKaLogin" class="kakao" onclick="_LA.EVT('4286')">카카오 로그인</a></li>
		                            <li><a href="javascript:;" id="btnFbLogin" class="facebook" onclick="facebookLogin(); _LA.EVT('4285')">페이스북 로그인</a></li>
		                            <li><a href="javascript:;" id="btnGlLogin" class="google" onclick="_LA.EVT('4288')"></a></li>
		                            <li><a href="javascript:;" id="btnApLogin" class="apple">애플 로그인</a></li>
		                        </ul>
		                    </section>
		                    <section class="login-join">
		                        <a href="https://www.jobkorea.co.kr/Login/Search/search_id.asp" target="_new">아이디 찾기</a>
		                        <a href="https://www.jobkorea.co.kr/Login/Search/search_pwd.asp" target="_new">비밀번호 찾기</a>
		                        <a id="link_regist" href="https://www.jobkorea.co.kr/Join/M_Regist" target="_new" data-co="https://www.jobkorea.co.kr/Join/GI_Regist" data-gg="https://www.jobkorea.co.kr/Join/M_Regist">회원가입
		                            <div class="div-tooltip-msg"><strong>하나로 통하는 No.1 통합회원 가입</strong><br>2024 K-BPI 취업/알바 포털 1위</div>
		                        </a>
		                    </section>
		                </fieldset>
		            </form>
		        </div>
		        <div class="adBan">
		            <h2 class="skip">광고</h2>
		            <div class="ad-container divAdBnnr_14_0" style="width:325px; height:310px; background-color:#ffffff;">
		                <a class="imgAdBnnr imgAdBnnr_14_0" href="javascript:;" data-campaignid="188" data-campaignproductid="751" data-space="loginRight" data-urlpath="/Login/Login_Tot.asp" data-linkurl="https://www.jobkorea.co.kr/Theme/lgepartners" data-linktype="NEW_WINDOW" data-gender="" data-age="0" data-pagetypecode="14">
		
		                    <img src="https://ads.jobkorea.co.kr/ads/pc/{service}/202505/66d2638e-6762-43c5-82d7-24f81cf53336.png" alt="LG전자 협력회사 온라인 채용관" onerror="adBnnrImageOnError_14(this, 14, 0)">
		                </a>
		            </div>
		
		        </div><!-- AD //-->
		    </div><!-- // content -->
		
		    
		</div>
	</div>
</body>
</html>