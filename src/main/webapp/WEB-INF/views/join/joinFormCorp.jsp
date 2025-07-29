<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
    <title>기업 회원가입</title>
    <link rel="stylesheet" href="/dist/assets/css/join/member_join.css">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script defer src="/js/company/common/joinFormCorp.js"></script>
<style>
.industry-section {
    margin-bottom: 16px;
}
.industry-title {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 8px;
}
.industry-options {
    display: flex;
    flex-wrap: wrap;
    gap: 10px 20px;
}
.industry-option {
    display: flex;
    align-items: center;
    cursor: pointer;
    font-size: 14px;
}
.industry-option input {
    margin-right: 6px;
}
.alert_box{
	clear:both;
}
</style>
</head>
<body>
<div id="join_wrapper" class="member_cate">
    <fieldset>
        <!-- **fieldset 내부에 form:form 시작** -->
        <form:form modelAttribute="company" method="post">
            <h3 class="tit_join_member">띹잡 통합 기업회원 가입</h3>

            <!-- 사업자등록번호 -->
            <div class="write_base">
                <div class="item">
                    <label for="brNumber"><strong>사업자등록번호</strong></label>
                    <div class="TypoBox">
                        <input name="business.brNumber" id="brNumber" type="text" class="Typo SizeL defalt" maxlength="12" value="${company.business.brNumber}" 
                               autocapitalize="off" autocomplete="off" placeholder="사업자 등록번호 직접 입력 (10자리)">
                    </div>
                    <p class="alert_column good_txt" id="msg_corp_code"></p>
                </div>
            </div>

            <!-- 기업정보 입력 영역 (사업자 검증 후 표시) -->
            <div id="area_input_company" style="display:none;">
                <div class="write_base">
                    <!-- 기업명 -->
                    <div class="item">
                        <label for="comName"><strong>기업명</strong></label>
                        <div class="TypoBox">
                            <input name="comName" id="comName" value="${company.comName}" type="text" class="Typo SizeL defalt" maxlength="25" 
                                   placeholder="기업명 입력">
                        </div>
                        <em class="msgInvalid" id="msg_company_nm" style="display:none;">필수 정보입니다.</em>
                        <form:errors cssClass="text-danger" path="comName" />
                    </div>

                    <!-- 대표자 -->
                    <div class="item">
                        <label for="ceoName"><strong>대표자</strong></label>
                        <div class="TypoBox">
                            <input name="ceoName" id="ceoName" value="${company.ceoName}" type="text" class="Typo SizeL defalt" maxlength="50" 
                                   placeholder="예시) 이윤석 외 5명">
                        </div>
                        <em class="msgInvalid" id="msg_ceo_nm" style="display:none;">필수 정보입니다.</em>
                        <form:errors cssClass="text-danger" path="ceoName" />
                    </div>
                    
                    <!-- 대표 전화번호 -->
                    <div class="item">
                        <label for="comNum"><strong>대표자 번호</strong></label>
                        <div class="TypoBox">
                            <input name="comNum" id="comNum" value="${company.comNum}" type="text" class="Typo SizeL defalt" maxlength="11" 
                                   placeholder="-를 제외한 번호를 입력해 주세요">
                        </div>
                        <em class="msgInvalid" id="msg_ceo_nm" style="display:none;">필수 정보입니다.</em>
                        <form:errors cssClass="text-danger" path="comNum" />
                    </div>

                    <!-- 주소 -->
                    <div class="item adress_column">
                        <label for="comAddr1"><strong>주소</strong></label>
                        <div class="input_collect TypoBox defalt">
                            <input type="text" name="comAddr1" id="comAddr1" value="${company.comAddr1 }" class="Typo SizeL defalt" placeholder="기본 주소">
                        </div>
                        <button type="button" id="btn_add1_search" class="BtnType SizeL defalt btn_cert_pop"><span>주소 찾기</span></button>
                    </div>

                    <div class="item adress_column">
                        <label for="comAddr2"><strong>상세주소</strong></label>
                        <div class="TypoBox">
                            <input type="text" name="comAddr2" id="comAddr2" value="${company.comAddr2 }" class="Typo SizeL defalt" placeholder="상세 주소 입력">
                        </div>
                    </div>

                    <!-- 업종 -->
                    <div class="cont_division" id="job_category_area">
                        <strong id="jobCategory" class="cont_tit">업종</strong>
                        <div class="flexbox row_reverse" id="industry_category">
                            <input type="hidden" name="industryType" id="industry_code">
                            <button type="button" name="btn_desire_industry" class="BtnType SizeL btn_job_category">
                                선택
                            </button>
                           <div id="industry_selected_area" class="form_typobox area_job_category">
							</div>
                        </div>
                        <em class="msgInvalid" id="msg_industry_category" style="display:none;">업종을 선택하세요</em>
                        <!-- 업종 레이어 -->
                        <div class="layer_desire_industry" id="layer_desire_industry" style="display:none;">
                            <div class="layer_pop_manage layer_hope layer_hope_industry">
                                <div class="layer_manage_wrap">
                                    <h4>업종 선택</h4>
                                    <div class="area_search_job">
                                        <label for="search_industry">빠른 업종 검색</label>
                                        <input type="text" class="sri_input" id="search_industry">
                                    </div>
                                    <p class="txt">※ 업종- 1개만 선택가능</p>
                                    <fieldset>
                                        <legend>업종 선택</legend>
                                        <div class="area_table_scroll list_jobs">
												<div class="industry-groups">
													<!-- JS로 대분류+중분류 라디오 렌더링 -->
												</div>
											</div>
                                    </fieldset>
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

                    <!-- 설립년도 -->
                    <div class="item">
                        <label for="comCreateYear"><strong>설립년도</strong></label>
                        <div class="TypoBox">
                            <select id="comCreateYear" name="comCreateYear" class="Typo SizeL defalt">
                                <option value="">선택</option>
							</select>
                        </div>
                    </div>

						<!-- 이메일 인증 -->
						<div class="item identify_mail">
							<label for="sms_email_id"><strong>이메일</strong></label>
							<div class="input_collect TypoBox defalt">
								<input type="hidden" name="mail_confirm_complete" value="n"
									id="mail_confirm_complete"> <input type="text"
									id="mail_email_id" name="comEmail" value="${company.comEmail }"
									class="Typo SizeL join_input defalt"
									placeholder="email@dditjob.or.kr" autocomplete="one-time-code">
							</div>
							<button type="button" id="mail_send_code"
								class="BtnType SizeL defalt btn_cert_pop">
								<span>인증요청</span>
							</button>
							<button type="button" id="mail_re_send_code"
								class="BtnType SizeL colorBlueReverse" style="display: none"
								onclick="sendCodeAction()">
								<span>재발송</span>
							</button>

							<!-- 에러 메시지 -->
							<em class="msgInvalid" id="mail_msg_email1" style="display: none">이메일
								주소를 입력해주세요.</em>
							<form:errors cssClass="text-danger error-message" path="comEmail" />

							<!-- 인증번호 입력 -->
							<div class="input_collect " id="mail_confirm_wrap"
								style="display: none;">
								<label for="email_code" class="blind">인증번호 입력</label>
								<div class="TypoBox email_box">
									<input type="number" id="email_code" name="email_code"
										placeholder="인증번호를 입력해주세요"
										class="Typo SizeL join_input defalt" maxlength="6"
										pattern="\d*" autocomplete="on">
								</div>
								<button type="button"
									class="BtnType SizeL defalt confirm-action" disabled>
									<span>확인</span>
								</button>
							</div>

							<!-- 남은 시간 / 실패 메시지 / 성공 메시지 -->
							<em class="msgInvalid" id="confirm_remain_mail_time_area"
								style="display: none;"> <span>남은 시간 03:00</span>
							</em> <em class="msgInvalid" id="email_confirm_msg"
								style="display: none"> <span>인증번호가 일치하지 않습니다. 다시 확인해
									주세요.</span>
							</em>
							<p class="alert_column good_txt" id="mail_msg_good"
								style="display: none">인증이 완료되었습니다.</p>
						</div>

						<div class="item">
							<label for="id" class=""><strong>아이디</strong></label>
							<div class="input_collect TypoBox defalt"
								style="display: flex; align-items: center; gap: 10px;">
								<input name="userId" id="id" type="text" class="Typo SizeL defalt" value="${company.userId }"
									maxlength="20" style="ime-mode: disabled" autocapitalize="off"
									autocomplete="off" placeholder="4~20자리 / 영문, 숫자, 특수문자 '_'사용가능">
							</div>
							<button type="button" id="id_duplicate" class="BtnType SizeL defalt btn_cert_pop">
								<span>중복 확인</span>
							</button>
							<div class="alert_box">
								<p class="alert_column focus_txt" id="idFocusMsg" style="display: none; color: red;">4 ~ 20자의 영문, 숫자와 특수문자'_'만 사용해주세요.</p>
								<p class="alert_column good_txt" id="idCheckMsg2" style="display: none">사용 가능한 아이디입니다.</p>
								<p class="msgInvalid" id="idCheckMsg1" style="display: none;">이미 사용 중인 아이디입니다.</p>
								<form:errors cssClass="text-danger" path="userId" />
							</div>
						</div>

						<!-- 패스워드 -->
						<div class="item">
							<label for="password1"><strong>비밀번호</strong></label>
							<div class="TypoBox pass_box">
								<input autocapitalize="off" name="userPassword"
									class="Typo SizeL defalt" id="password1" type="password"
									value="${company.userPassword }" maxlength="16"
									autocomplete="off" placeholder="8~16자리/영문 대소문자, 숫자, 특수문자 조합">
								<button type="button" toggle="#password1" id="masking_password"
									class="toggle_password field_eye on" style="display: none;"></button>
							</div>

							<form:errors cssClass="text-danger" path="userPassword" />
							<p class="alert_column focus_txt" id="password1FocusMsg"
								style="display: none">8~16자리 영문 대소문자, 숫자, 특수문자 중 3가지 이상 조합으로
								만들어주세요.</p>
							<!-- focus 시 텍스트 -->
							<em class="msgInvalid" id="password1_warning_txt"
								style="display: none"><span>8~16자리 영문 대소문자, 숫자, 특수문자
									중 3가지 이상 조합으로 만들어주세요.</span></em>
							<p class="alert_column good_txt" id="password1_good_txt"
								style="display: none"></p>
							<p class="pass_safety" id="pw_strnegth_level"
								style="display: none"></p>

							<div class="item">
								<label for="password2"><strong>패스워드 확인</strong></label>
								<div class="TypoBox pass_box">
									<input name="userPasswordConfirm" id="password2"
										type="password" class="Typo SizeL defalt" maxlength="16"
										placeholder="비밀번호를 다시 입력해주세요">
								</div>
								<em class="msgInvalid" id="msg_password_match"
									style="display: none; color: red;">비밀번호가 일치하지 않습니다.</em>
								<p class="alert_column good_txt" id="msg_password_good" style="display:none; color:green;">비밀번호가 일치합니다.</p>
							</div>
						</div>
					</div>

            <!-- **폼 내부에 버튼 배치** -->
            <div class="btn_join">
                <button type="submit" id="btn_submit" class="inp_join BtnType SizeL defalt btn_input_complete" disabled>회원가입 완료</button>
            </div>
        </form:form>
        <!-- **form:form 닫기** -->
    </fieldset>
</div>
</body>
</html>