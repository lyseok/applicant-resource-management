<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<head>
	<title>로그인</title>
	<link rel="stylesheet" href="/dist/assets/css/login/login.css">
	<script defer src="/js/login/loginForm.js"></script>
</head>


<body>
	<div id="login">
		<div id="container">
			<div class="secLogin">
				<div class="logWrap" id="loginFormWrite">
					<h2 class="blind">로그인</h2>
					<form name="form" id="login-form" method="post">
						<fieldset class="login-form">
							<legend class="blind">로그인 입력폼</legend>

							<!-- 회원선택-->
							<section class="login-tab">
								<ul id="devMemTab">
									<li class="on" role="tab" data-tab="tab1"><a href="#" data-m-type="M">개인회원</a></li>
									<li role="tab" data-tab="tab2"><a href="#" data-m-type="Co">기업회원</a></li>
								</ul>
							</section>

							<!-- 로그인 입력 폼 - 에러 처리 <div class="row-input error"> -->
							<section class="login-input ">
								<label for="username" id="lb_id" class="label-form label-id"></label>
								<input type="text" class="inpTxt  input-id" placeholder="DDIT 띹잡 ID" name="username" id="username"
									size="16" maxlength="20" title="아이디 입력" style="ime-mode:inactive;" value="" required="">
								<label for="password" id="lb_pw" class="label-form label-password"><span
										class="material-symbols-outlined">lock</span></label>
								<input type="password" class="inpTxt input-password" placeholder="비밀번호" name="password" id="password"
									size="16" title="비밀번호 입력" required="">
								<button type="submit" class="login-button">로그인</button>
								<div class="text-error"></div>
								<!-- CapsLock 레이어 - <div class="login-capslock" 에 on 클래스 추가 시 활성화 -->
								<div class="login-capslock" id="ipNotice">
									<em>Caps Lock</em>이 켜져 있습니다.<span class="mainIcn mainIcnArrUp"></span>
								</div>
								
								<div class="btn btn-secondary" id='mem-auth'>일반회원</div>
								<div class="btn btn-secondary" id="comp-auth">기업회원</div>
								<div class="btn btn-secondary" id="admin-auth">관리자</div>
							</section>

							<section class="login-social">
								<ul>
									<li><a href="javascript:;" id="btnNvLogin" class="naver" onclick="_LA.EVT('4287')">네이버 로그인</a></li>
									<li><a href="javascript:;" id="btnKaLogin" class="kakao" onclick="_LA.EVT('4286')">카카오 로그인</a></li>
									<li><a href="javascript:;" id="btnGlLogin" class="google" onclick="_LA.EVT('4288')"></a></li>
								</ul>
							</section>
							<section class="login-join">
								<a href="https://www.jobkorea.co.kr/Login/Search/search_id.asp" target="_new">아이디 찾기</a>
								<a href="https://www.jobkorea.co.kr/Login/Search/search_pwd.asp" target="_new">비밀번호 찾기</a>
								<a id="link_regist" href="/member_signup" target="_new"
									data-co="https://www.jobkorea.co.kr/Join/GI_Regist"
									data-gg="https://www.jobkorea.co.kr/Join/M_Regist">회원가입
								</a>
							</section>
						</fieldset>
					</form>
				</div>
				<div class="adBan">
					<h2 class="skip">광고</h2>
					<div class="ad-container divAdBnnr_14_0" style="width:325px; height:310px; background-color:#ffffff;">
						<a class="imgAdBnnr imgAdBnnr_14_0" href="javascript:;" data-campaignid="188" data-campaignproductid="751"
							data-space="loginRight" data-urlpath="/Login/Login_Tot.asp"
							data-linkurl="https://www.jobkorea.co.kr/Theme/lgepartners" data-linktype="NEW_WINDOW" data-gender=""
							data-age="0" data-pagetypecode="14">

							<img src="https://ads.jobkorea.co.kr/ads/pc/{service}/202505/66d2638e-6762-43c5-82d7-24f81cf53336.png"
								alt="LG전자 협력회사 온라인 채용관" onerror="adBnnrImageOnError_14(this, 14, 0)">
						</a>
					</div>

				</div>
				<!-- AD //-->
			</div><!-- // content -->

		</div>
	</div>
</body>