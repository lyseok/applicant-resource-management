<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<head>
	<title>기업 회원가입</title>
	<link rel="stylesheet" href="/dist/assets/css/join/member_join.css" >
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="/js/company/common/joinFormCorp.js"></script>
</head>
<body>
	<div id="join_wrapper" class="member_cate">
		<fieldset>
			<form:form modelAttribute="member" method="post">
		    <h3 class="tit_join_member">띹잡 통합 기업회원 가입</h3>
		
		    <div class="write_base">
		        <!-- 사업자등록번호 -->
		        <div class="item">
		            <label for="corp_code" class=""><strong>사업자등록번호</strong></label>
		            <div class="TypoBox">
		                <input name="brNumber" id="brNumber" type="text" class="Typo SizeL defalt" maxlength="12" autocapitalize="off" autocomplete="off" placeholder="사업자 등록번호 직접 입력 (10자리)">
		            </div>
		            <p class="alert_column good_txt" id="msg_corp_code"></p>
		        </div>
		    </div>
		
		
		    <!-- 기업정보 입력 영역 -->
		    <div id="area_input_company" style="display: none;">
		        <div class="write_base">
		            <!-- 기업명 -->
		            <div class="item">
		                <label for="company_nm" class=""><strong>기업명</strong></label>
		                <div class="TypoBox">
		                    <input name="comName" id="comName" type="text" class="Typo SizeL defalt" maxlength="25" onmousedown="pushDataLayer('ga_lead', 'company_join', 'company_nm_input', 'click_company_nm');" autocapitalize="off" autocomplete="off" placeholder="기업명 입력">
		                </div>
		                <em class="msgInvalid" id="msg_company_nm" style="display: none;">필수 정보입니다.</em>
		            </div>
		
		            <!-- 대표자 -->
		            <div class="item">
		                <label for="ceo_nm" class=""><strong>대표자</strong></label>
		                <div class="TypoBox">
		                    <input name="ceoName" id="ceoName" type="text" class="Typo SizeL defalt" maxlength="50" onmousedown="pushDataLayer('ga_lead', 'company_join', 'ceo_nm_input', 'click_ceo_nm')" autocapitalize="off" autocomplete="off" placeholder="예시) 이윤석 외 5명">
		                </div>
		                <em class="msgInvalid" id="msg_ceo_nm" style="display: none;">필수 정보입니다.</em>
		            </div>
		
		            <!-- 회사 주소 -->
		            <div class="item adress_column" id="address_area">
						<label for="addr1"><strong>주소</strong></label>
						<div class="input_collect TypoBox defalt">
							<input type="text" name="comAddr1" id="comAdd1"
								class="Typo SizeL defalt inp_user_nm"
								value="${company.comAddr1 }" placeholder="기본 주소"
								style="ime-mode: active" autocapitalize="off" placeholder=""
								autocomplete="one-time-code">
						</div>
						<button type="button" id="btn_add1_search"
							class="BtnType SizeL defalt btn_cert_pop">
							<span>주소 찾기</span>
						</button>
						<em class="msgInvalid" id="user_nm_msg" name="user_nm_msg"
							style="display: none">이름은 필수 입력 정보 입니다.</em>
					</div>
						
					<div class="item adress_column" id="address_area">
							<label for="user_nm"><strong>상세주소</strong></label>
							<div class="TypoBox">
								<input type="text" name="Add2" id="memAdd2"
									class="Typo SizeL defalt inp_user_nm"
									value="${company.comAddr2 }" style="ime-mode: active"
									autocapitalize="off" placeholder="상세 주소를 입력하세요"
									autocomplete="one-time-code">
							</div>
							<em class="msgInvalid" id="user_nm_msg" name="user_nm_msg"
								style="display: none">이름은 필수 입력 정보 입니다.</em>
						</div>	
		
		            <!-- 업종 -->
		            <div class="cont_division" id="job_category_area" >
		                <strong id="jobCategory" class="cont_tit">업종</strong>
		
		                <div class="flexbox row_reverse" id="industry_category">
		                    <input type="hidden" name="industry_code" id="industry_code" value="">
		                    <input type="hidden" name="industry_keyword" id="industry_keyword" value="">
		                    <button type="button" name="btn_desire_industry" class="BtnType SizeL btn_job_category" onmousedown="pushDataLayer('ga_lead', 'company_join', 'busi_cond_nm_input', 'click_busi_cond_nm_input');" aria-haspopup="true" aria-expanded="false" aria-labelledby="jobCategory" data-api_id="desire_industry">
		                        선택
		                    </button>
		                    <div id="industry_selected_area" class="form_typobox area_job_category">
		                        <span class="form_placeholder" aria-hidden="true">업종선택</span>
		                    </div>
		                </div>
		                <em class="msgInvalid" id="msg_industry_category" style="display: none;">업종을 선택하세요</em>
		
		                <!-- 업종 레이어 -->
		                <div class="layer_desire_industry" id="layer_desire_industry" style="display:none;">
		                    <div class="layer_pop_manage layer_hope layer_hope_industry" data-layer_id="desire_industry">
		                        <div class="layer_manage_wrap">
		                            <h4>업종 선택</h4>
		                            <div class="area_search_job">
		                                <label class="title_search_job" for="search_industry">빠른 업종 검색</label>
		                                <input type="text" class="sri_input" data-api_type="auto" data-api_id="desire_industry">
		                            </div>
		                            <p class="txt">※ 업종- 1개만 선택가능 / 키워드-업종당 최대 5개까지 선택 가능</p>
		                            <fieldset>
		                                <legend>업종 선택</legend>
		                                <div class="area_table_scroll list_jobs">
		                                    <ul class="list_jobs_category"></ul>
		                                </div>
		                            </fieldset>
		
		                            <div class="list_job_check">
		                                <ul></ul>
		                            </div>
		
		                            <div class="bottom_btn_wrap">
		                                <button type="button" class="btn_basic_type05 btn_save">완료</button>
		                                <button type="button" class="btn_basic_type01 btn_close">취소</button>
		                                <button type="button" class="btn_basic_type03 btn_reset">초기화</button>
		                            </div>
		
		                            <button type="button" class="btn_layer_close btn_close"><span>닫기</span></button>
		                        </div>
		                    </div>
		                </div>
		            </div>
		
		            <!-- 개업일 -->
		            <div class="item" id="open_date_wrap" >
		                <label for="open_date" class=""><strong>설립년도</strong></label>
		                <div class="TypoBox">
		                    <input name="comCreateYear" id="comCreateYear" type="text" class="Typo SizeL defalt" maxlength="8" onmousedown="pushDataLayer('ga_lead', 'company_join', 'establish_year_input', 'click_establish_year');" autocapitalize="off" autocomplete="off" placeholder="개업일 입력 (YYYYMMDD)">
		                </div>
		                <em class="msgInvalid" id="msg_open_date" style="display: none;">올바른 날짜입력 형식이 아닙니다.</em>
		            </div>
		        </div>				 		        
		
		        <!-- 이메일 인증 -->
	            <div class="item identify_mail" >
	                <label for="sms_email_id"><strong>이메일</strong></label>
	                
	                <div class="input_collect TypoBox defalt">
	                    <input type="hidden" name="mail_confirm_complete" value="n" id="mail_confirm_complete">
	                    <input type="text" id="mail_email_id" name="memEmail" value="${company.comEmail }" style="ime-mode:inactive" class="Typo SizeL join_input defalt" placeholder="email@dditjob.or.kr" autocomplete="one-time-code">
	                </div>
	                <button type="button" id="mail_send_code" class="BtnType SizeL defalt btn_cert_pop"><span>인증요청</span></button>
	                <button type="button" id="mail_re_send_code" class="BtnType SizeL colorBlueReverse" style="display:none" onclick="sendCodeAction()"><span>재발송</span></button>

	                <em class="msgInvalid" id="mail_msg_email1" name="msg_email1" style="display:none">이메일 주소를 입력해주세요.</em>
	               <form:errors cssClass="text-danger error-message" path="memEmail"/>
	
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
		
		            <!-- 아이디 -->
		            <div class="item">
		                <label for="userId" class=""><strong>아이디</strong></label>
		                <div class="TypoBox">
		                    <input name="id" id="id" type="text" class="Typo SizeL defalt" maxlength="20" autocapitalize="off" autocomplete="off" placeholder="4~20자리 / 영문, 숫자, 특수문자'_' 사용 가능">
		                </div>
		                <button type="button" id="id_duplicate" class="BtnType SizeL defalt btn_cert_pop"><span>중복 확인</span></button>
		                <p class="alert_column focus_txt" id="idFocusMsg" style="display:none">4 ~ 20자의 영문, 숫자와 특수문자 '_'만 사용 가능</p>
		                <p class="msgInvalid" id="idCheckMsg1" style="display: none;">4 ~ 20자의 영문, 숫자와 특수문자 '_'만 사용해 주세요.</p>
		                <p class="alert_column good_txt" id="idCheckMsg2" style="display: none;">사용가능한 ID입니다.</p>
		            </div>
		
		            <!-- 패스워드 -->
		            <div class="item">
		                <label for="password1" class="on"><strong>패스워드</strong></label>
		                <div class="TypoBox pass_box">
		                    <input autocapitalize="off" name="password1" class="Typo SizeL defalt" id="password1" type="password" maxlength="16" autocomplete="off" placeholder="8~16자리/영문 대소문자, 숫자, 특수문자 조합">
		                    <button type="button" toggle="#password1" id="masking_password" class="toggle_password field_eye on" style="display: none;"></button>
		                </div>
		                <form:errors cssClass="text-danger" path="userPassword" />
		                <p class="alert_column focus_txt" id="password1FocusMsg" style="display:none">8~16자리 영문
		                    대소문자, 숫자, 특수문자 중 3가지 이상 조합으로 만들어주세요.</p>
		                <!-- focus 시 텍스트 -->
		                <em class="msgInvalid" id="password1_warning_txt" style="display:none"><span>8~16자리 영문 대소문자, 숫자, 특수문자 중 3가지 이상 조합으로 만들어주세요.</span></em>
		                <p class="alert_column good_txt" id="password1_good_txt" style="display:none"></p>
		                <p class="pass_safety" id="pw_strnegth_level" style="display:none"></p>
		
		            </div>
		       </div>				
			
		    <div class="btn_join">
		        <button type="submit" id="btn_submit" class="inp_join BtnType SizeL defalt ga_data_layer btn_input_complete" data-ga_data_layer="ga_lead|member_join|join_pc|step_2" disabled="disabled">회원가입 완료</button>		     
		    </div>
		</form:form>
		</fieldset>
	</div>
</body>
</html>