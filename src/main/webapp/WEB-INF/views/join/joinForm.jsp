<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<title>개인 회원가입</title>
	<link rel="stylesheet" href="/css/member/common/joinForm.css" >
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="/js/member/common/joinForm.js"></script>
</head>
<body>
<div id="join_wrapper" class="member_cate">
	<fieldset>
    	<h3 class="tit_join_member">띹잡 통합 개인회원 가입</h3>
	    <div class="cont_division">
	        <div class="write_base">
	            <!-- 아이디 -->
	            <div class="item">
	                <label for="id" class=""><strong>아이디</strong></label>
	                <div class="input_collect TypoBox defalt" style="display: flex; align-items: center; gap: 10px;">
	                    <input name="userId" id="id" type="text" class="Typo SizeL defalt" maxlength="20" style="ime-mode:disabled" autocapitalize="off" autocomplete="off" placeholder="4~20자리 / 영문, 숫자, 특수문자 '_'사용가능">
	                </div>
	                <button type="button" id="id_duplicate" class="BtnType SizeL defalt btn_cert_pop"><span>중복 확인</span></button>
	                <div class="alert_box">
        				<p class="alert_column good_txt" id="idCheckMsg2" style="display:none">사용 가능한 아이디입니다.</p>
        				<p class="msgInvalid" id="idCheckMsg1" style="display: none;">이미 사용 중인 아이디입니다.</p>
	                	<p class="alert_column focus_txt" id="idFocusMsg" style="display: none; color:red;">4 ~ 20자의 영문, 숫자와 특수문자'_'만 사용해주세요.</p>
    				</div>
	            </div>
	
	            <!-- 비밀번호 -->
	            <div class="item">
	                <label for="password1"><strong>비밀번호</strong></label>
	                <div class="TypoBox pass_box">
	                    <input autocapitalize="off" name="userPassword" class="Typo SizeL defalt" id="password1" type="password" maxlength="16" autocomplete="off" placeholder="8~16자리/영문 대소문자, 숫자, 특수문자 조합">
	                    <button type="button" toggle="#password1" id="masking_password" class="toggle_password field_eye on" style="display: none;"></button>
	                </div>
	
	                <p class="alert_column focus_txt" id="password1FocusMsg" style="display:none">8~16자리 영문 대소문자, 숫자, 특수문자 중 3가지 이상 조합으로 만들어주세요.</p>
	                <!-- focus 시 텍스트 -->
	                <em class="msgInvalid" id="password1_warning_txt" style="display:none"><span>8~16자리 영문 대소문자, 숫자, 특수문자 중 3가지 이상 조합으로 만들어주세요.</span></em>
	                <p class="alert_column good_txt" id="password1_good_txt" style="display:none"></p>
	                <p class="pass_safety" id="pw_strnegth_level" style="display:none"></p>
	            </div>
	
	            <input type="hidden" name="cellnum" id="cellnum">
	            <input type="hidden" name="email_id" id="email_id">	
	
	            
	            <!-- 이메일 인증 -->
	            <div class="item identify_mail" >
	                <label for="sms_email_id"><strong>이메일</strong></label>
	                
	                <div class="input_collect TypoBox defalt">
	                    <input type="hidden" name="mail_confirm_complete" value="n" id="mail_confirm_complete">
	                    <input type="text" id="mail_email_id" name="memEmail" style="ime-mode:inactive" class="Typo SizeL join_input defalt" placeholder="email@dditjob.or.kr" autocomplete="one-time-code">
	                </div>
	                <button type="button" id="mail_send_code" class="BtnType SizeL defalt btn_cert_pop"><span>인증요청</span></button>
	                <button type="button" id="mail_re_send_code" class="BtnType SizeL colorBlueReverse" style="display:none" onclick="sendCodeAction()"><span>재발송</span></button>
	                <!-- 자동리스트 영역 -->
	                <ul class="auto_list_area email_list ScrollBar">
	                    <li class="auto_list"><a href="javascript:;" onclick="return false;" class="email_domain"><strong class="txt_input"></strong>@naver.com</a></li>
	                    <li class="auto_list"><a href="javascript:;" onclick="return false;" class="email_domain"><strong class="txt_input"></strong>@gmail.com</a></li>
	                    <li class="auto_list"><a href="javascript:;" onclick="return false;" class="email_domain"><strong class="txt_input"></strong>@daum.net</a></li>
	                    <li class="auto_list"><a href="javascript:;" onclick="return false;" class="email_domain"><strong class="txt_input"></strong>@nate.com</a></li>
	                    <li class="auto_list"><a href="javascript:;" onclick="return false;" class="email_domain"><strong class="txt_input"></strong>@outlook.com</a></li>
	                </ul>
	                <em class="msgInvalid" id="mail_msg_email1" name="msg_email1" style="display:none">이메일 주소를 입력해주세요.</em>
	
	                <div class="input_collect " id="mail_confirm_wrap" style="display: none;">
	                    <label for="sms_code" class="blind">인증번호 입력</label>
	                    <div class="TypoBox email_box">
	                        <input type="number" id="email_code" name="email_code" placeholder="인증번호를 입력해주세요" class="Typo SizeL join_input defalt" maxlength="6" pattern="\d*" autocomplete="on">
	                    </div>
	
	                    <button type="button" class="BtnType SizeL defalt confirm-action person ga_data_layer" data-ga_data_layer="ga_lead|member_join|join_pc|step_1" disabled=""><span>확인</span></button>
	                </div>
	                <em class="msgInvalid" id="confirm_remain_mail_time_area" style="display: none;"><span>남은 시간 03:00</span></em>
	                <em class="msgInvalid" id="email_confirm_msg" style="display:none"><span>인증번호가 일치하지 않습니다. 다시 확인해 주세요.</span></em>
	                <p class="alert_column good_txt" id="mail_msg_good" style="display:none">인증이 완료되었습니다.</p>
	            </div>
	
	
	            <!-- 이름 -->
	            <div class="item identify_mail">
	                <label for="user_nm"><strong>이름</strong></label>
	                <div class="TypoBox">
	                    <input type="text" name="memName" id="user_nm" class="Typo SizeL defalt inp_user_nm" style="ime-mode:active" autocapitalize="off" placeholder="이름 입력" autocomplete="one-time-code">
	                </div>
	                <em class="msgInvalid" id="user_nm_msg" name="user_nm_msg" style="display:none">이름은 필수 입력 정보 입니다.</em>
	            </div>
	            
	            <div class="item identify_mail">
	                <label for="addr1"><strong>주소</strong></label>
	                <div class="input_collect TypoBox defalt">
	                    <input type="text" name="memAdd1" id="memAdd1" class="Typo SizeL defalt inp_user_nm" placeholder="기본 주소" style="ime-mode:active" autocapitalize="off" placeholder="" autocomplete="one-time-code">	                    
	                </div>
	                <button type="button" id="btn_add1_search" class="BtnType SizeL defalt btn_cert_pop"><span>주소 찾기</span></button>
	                <em class="msgInvalid" id="user_nm_msg" name="user_nm_msg" style="display:none">이름은 필수 입력 정보 입니다.</em>
	            </div>
	            
	             <div class="item identify_mail">
	                <label for="user_nm"><strong>상세주소</strong></label>
	                <div class="TypoBox">
	                    <input type="text" name="memAdd2" id="memAdd2" class="Typo SizeL defalt inp_user_nm" style="ime-mode:active" autocapitalize="off" placeholder="상세 주소를 입력하세요" autocomplete="one-time-code">
	                </div>
	                <em class="msgInvalid" id="user_nm_msg" name="user_nm_msg" style="display:none">이름은 필수 입력 정보 입니다.</em>
	            </div>
	
	            <!-- 생년월일 -->
	            <div class="item identify_mail">
	                <label for="birth_date"><strong>생년월일</strong></label>
	                <div class="TypoBox">
	                    <input type="date" name="memBir" id="birth_date" maxlength="8" autocapitalize="off" placeholder="YYYYMMDD" class="Typo SizeL defalt inp_birth_date" autocomplete="one-time-code">
	                </div>
	                <em class="msgInvalid" id="cyr_msg" style="display:none">
	                    <span>YYYYMMDD 입력 형식을 확인해주세요.</span>
	                </em>
	                <div class="wrap_collection_basis">
	                    <button type="button" class="btn_link btn_birth_date_msg" aria-describedby="tootip_content_email" style="cursor:pointer;display:none" aria-expanded="false">만 15세 이상인지 확인이 필요해요
	                    </button>
	                    <!--툴팁 노출 시 aria-expanded="ture"로 변경-->
	                    <div class="TipBox social_tooltip Click" id="tootip_content_email">
	                        <div class="TipCont BottomCenter collectionBasisContents">
	                            <span class="Tail"><svg class="icon_tail">
	                                    <use xlink:href="#TipBox_tail"></use>
	                                </svg></span>
	                            <div class="TipTxt">
	                                취직 최저 연령에 대한 제한<br>
	                                (근로기준법 제 64조 1항)
	                            </div>
	                            <button type="button" class="BtnClose btnClose btn_close_birth_date_msg">
	                                <span class="blind">닫기</span></button>
	                        </div>
	                    </div>
	                </div>
	            </div>	            	       
	        </div>
	    </div>
	    <!-- 약관 -->
	    <div class="cont_division">
	        <span class="terms_agree"><strong>약관</strong></span>
	        <div class="agree_box">
	            <ul class="agree_article">
	                <li>
	                    <div class="InpBox">
	                        <span class="Chk SizeL">
	                            <input type="checkbox" class="check_mail" id="agreeAllPersonal">
	                            <label class="check_all check_off Lbl" for="agreeAllPersonal">
	                                <strong class="all_agree">전체 동의</strong>
	                            </label>
	                            <input type="hidden" name="hidden_check_all" value="0" id="hidden_check_all">
	                        </span>
	                    </div>
	                    <p class="agree_txt">위치기반 서비스 이용약관(선택), 마케팅 정보 수신 동의(이메일,SMS/MMS)(선택) 동의를 포함합니다.</p>
	                </li>
	            </ul>
	            <ul class="agree_article depth2">
	                <li>
	                    <div class="agree_desc">
	                        <div class="InpBox">
	                            <span class="Chk">
	                                <input type="checkbox" id="agree_rule1">
	                                <label class="Lbl check_custom check_off" for="agree_rule1">
	                                    <span><strong>(필수) 개인회원 약관에 동의</strong></span>
	                                </label>
	                            </span>
	
	                        </div>
	                    </div>
	                    <a href="/zf_user/help/terms-of-service/person" class="view_indetail" target="_blank">
	                        <span class="blind">개인회원 약관 상세보기</span>
	                    </a>
	                </li>
	                <li>
	                    <div class="agree_desc">
	                        <div class="InpBox">
	                            <span class="Chk">
	                                <input type="checkbox" id="agree_take1">
	                                <label class="Lbl check_custom check_off" for="agree_take1">
	                                    <span><strong>(필수) 개인정보 수집 및 이용에 동의</strong></span>
	                                </label>
	                            </span>
	
	                        </div>
	                    </div>
	                    <a id="popupClausePrivacyPerson" href="/zf_user/popup/privacy-policy?category=person_privacy_req" class="view_indetail popup_clause_open" target="_blank">
	                        <span class="blind">개인정보 수집 및 이용에 동의 약관 상세보기</span>
	                    </a>
	                </li>
	                <li>
	                    <div class="agree_desc">
	                        <div class="InpBox">
	                            <span class="Chk">
	                                <input type="checkbox" class="locationBased" id="location_based_fl" name="location_based_fl" value="y">
	                                <label class="Lbl check_custom check_off" for="location_based_fl">
	                                    <span>(선택) 위치기반서비스 이용약관에 동의</span>
	                                </label>
	                            </span>
	
	                        </div>
	                    </div>
	                    <a id="popupTermsAgreeLb" href="/zf_user/help/terms-of-service/location-based" class="view_indetail popup_clause_open" target="_blank">
	                        <span class="blind">위치기반서비스 이용약관에 동의 상세보기</span>
	                    </a>
	                </li>
	                <li>
	                    <div class="agree_desc">
	                        <div class="InpBox">
	                            <span class="Chk">
	                                <input type="checkbox" class="check_mail" id="termsAgree" name="termsAgree" value="y">
	                                <label class="Lbl check_custom check_off" for="termsAgree">
	                                    <span>(선택) 마케팅 정보 수신 동의 - 이메일</span>
	                                </label>
	                            </span>
	                        </div>
	                    </div>
	                    <a id="popupEmailReceive" href="/zf_user/popup/privacy-policy?category=person_email_opt" class="view_indetail popup_clause_open" target="_blank">
	                        <span class="blind">마케팅 정보 수신 동의 - 이메일 약관 상세보기</span>
	                    </a>
	                </li>
	                <li>
	                    <div class="agree_desc">
	                        <div class="InpBox">
	                            <span class="Chk">
	                                <input type="checkbox" id="sms_receive_fl" name="sms_receive_fl" value="y">
	                                <label class="Lbl check_custom check_off" for="sms_receive_fl">
	                                    <span>(선택) 마케팅 정보 수신 동의 - SMS/MMS</span>
	                                </label>
	                            </span>
	
	                        </div>
	                    </div>
	                    <a id="popupSmsReceive" href="/zf_user/popup/privacy-policy?category=person_sms_opt" class="view_indetail popup_clause_open" target="_blank">
	                        <span class="blind">마케팅 정보 수신 동의 - SMS/MMS 약관 상세보기</span>
	                    </a>
	                </li>
	            </ul>
	        </div>
	    </div>
	
	    <!-- 안내 -->
	    <div class="cont_noti">
	        <strong class="tit">안내</strong>
	        <ul>
	            <li>띹잡 회원으로 가입하시면 하나의 ID로 띹잡이 제공하는 모든 서비스를 이용하실 수 있습니다.</li>
	        </ul>
	    </div>
	
	    <div class="btn_join">
	        <button type="button" id="btn_submit" class="inp_join BtnType SizeL defalt ga_data_layer btn_input_complete" data-ga_data_layer="ga_lead|member_join|join_pc|step_2" disabled="disabled">회원가입 완료</button>
	    </div>
	</fieldset>
</div>
</body>
</html>