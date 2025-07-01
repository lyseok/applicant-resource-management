<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연봉 계산기</title>
</head>
<body>
<div class="wrap_tool">
    <div class="content_tool">
        <section class="wrap_enquiry">
            <h2 class="blind">연봉 관련 정보 입력</h2>
            <div class="enquiry">
                <h3 class="title">필수 입력</h3>
                <div class="wrap_column">
                    <div class="column">
                        <div class="form_title">급여 기준</div>
                        <span class="inpRdoSw colorGray sizeL">
                            <span class="inOption narrow">
                                <input type="radio" id="total_salary" name="salaryType" checked="">
                                <label for="total_salary" class="lbl">연봉</label>
                            </span>
                            <span class="inOption narrow">
                                <input type="radio" id="salary" name="salaryType" onmousedown="try{n_trackEvent('helper-tool', 'click', 'salary_month', '');}catch(e){}">
                                <label for="salary" class="lbl">월급</label>
                            </span>
                        </span>
                    </div>
                    <div class="column">
                        <div class="form_title">퇴직금</div>
                        <span class="inpRdoSw colorGray sizeL">
                            <span class="inOption narrow">
                                <input type="radio" id="addition" name="severancePay" checked="">
                                <label for="addition" class="lbl">별도</label>
                            </span>
                            <span class="inOption narrow">
                                <input type="radio" id="include" name="severancePay" onmousedown="try{n_trackEvent('helper-tool', 'click', 'salary_retire', '');}catch(e){}">
                                <label for="include" class="lbl">포함</label>
                            </span>
                        </span>
                    </div>
                </div>
                <div class="form_title" id="title_salary">연봉</div>
                <div class="form_salary same">
                    <span class="sri_input2 large right"><input type="text" id="pay" placeholder="0" title="연봉" value="" maxlength="12"></span>
                    <span class="sri_input2_text">원</span>
                    <div class="control">
                        <p class="kor">
                            <em id="trans_price"></em>원
                        </p>
                        <p class="btn">
                            <button type="button" class="btn_basic_type02" id="salary_add_1000">+1000만</button>
                            <button type="button" class="btn_basic_type02" id="salary_add_100">+100만</button>
                            <button type="button" class="btn_basic_type02" id="salary_add_10">+10만</button>
                        </p>
                    </div>
                </div>
            </div>
            <div class="enquiry optional">
                <h3 class="title">선택 입력</h3>
                <div class="wrap_column">
                    <div class="column">
                        <div class="form_title">
                            <label for="familyNum">부양 가족 수 (본인포함)</label>
	                        <div class="toolTipWrap">
	                            <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
	                            <div class="toolTip" style="width:402px">
	                                <span class="tail tail_bottom_left"></span>
	                                <div class="toolTipCont txtLeft">
	                                    <p class="txt">
	                                        기본공제대상자(본인 포함)에 해당하는 부양가족의 수를 1 이상 입력합니다.<br>
	                                        단, 연간 소득 금액이 100만원을 초과하는 경우에는 해당되지 않습니다.
	                                    </p>
	                                </div>
	                            </div>
	                        </div>
                        </div>
                        <div class="form_field">
                                        <span class="sri_input2 spin_button number">
                                            <button type="button" class="minus" id="dependent_btn">-</button>
                                            <input type="text" id="dependent" value="1" maxlength="2">
                                            <button type="button" class="plus" id="dependent_btn">+</button>
                                        </span>
                            <span class="sri_input2_text">명</span>
                        </div>
                    </div>
                    <div class="column">
                        <div class="form_title">
                            <label for="childNum">20세 이하 자녀수</label>
	                        <div class="toolTipWrap">
		                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                        <div class="toolTip" style="width:340px">
			                        <span class="tail tail_bottom_left"></span>
	                                <div class="toolTipCont txtLeft">
	                                    <p class="txt">
	                                        기본공제대상자에 해당하는 20세 이하의 자녀수를 선택합니다.<br>
	                                        단, 20세 이하의 자녀이더라도 연간 소득 금액이 100만원을<br>초과하는 경우에는 입력하지 않습니다.
	                                    </p>
	                                </div>
	                            </div>
	                        </div>
                        </div>
                        <div class="form_field">
                                        <span class="sri_input2 spin_button number">
                                            <button type="button" class="minus" id="under_twenty_btn">-</button>
                                            <input type="text" id="under_twenty" value="0" maxlength="2">
                                            <button type="button" class="plus" id="under_twenty_btn">+</button>
                                        </span>
                            <span class="sri_input2_text">명</span>
                        </div>
                    </div>
                </div>
                <div class="form_title">
                    <label for="nonTax">비과세액</label>
	                <div class="toolTipWrap">
	                    <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                <div class="toolTip" style="width:502px">
			                <span class="tail tail_bottom_left"></span>
			                <div class="toolTipCont txtLeft">
	                            <p class="txt">
	                                급여액 증에 세금공제를 하지 않은 금액으로, 본 계산기는 식대 20만원으로 설정되어 있습니다.<br>비과세액을 알고 계신 경우 직접 입력 가능합니다.<br><br>
	                                실비변상적인 급여, 비과세되는 식사대, 출산·보육수당, 기타 비과세 되는 소득, 국외근로소득,<br>생산직 근로자 등의 야간근로수당 외국인 근로자에 대한 과세특례 등이 해당됩니다.
	                            </p>
	                        </div>
	                    </div>
	                </div>
                </div>
                <div class="form_field">
                                <span class="sri_input2 right">
                                    <input type="text" id="tax_free" placeholder="0" value="200,000" maxlength="13">
                                </span>
                    <span class="sri_input2_text">원</span>
                </div>
            </div>
        </section>
        <section class="wrap_result">
            <h2 class="blind">연봉 계산기 결과</h2>
            <div class="title wide">
                <span class="text">월 예상 실수령액</span>
	            <div class="toolTipWrap">
	                <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
	                <div class="toolTip" style="margin-left:-166px;width:290px">
	                    <span class="tail tail_bottom_center"></span>
	                    <div class="toolTipCont txtLeft">
	                        <p class="txt">
	                            월 급여액에서 공제액 합계를 제외한 금액입니다.
	                        </p>
	                    </div>
	                </div>
	            </div>
                <span class="result_value" id="after_tax_income"><strong>0</strong>원</span>
            </div>
            <div class="result_list">
                <dl class="summary">
                    <dt>
                        한 달 기준 공제액
                    </dt>
                </dl>
                <dl>
                    <dt>
                        국민연금
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:305px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    과세금액의 4.5%를 공제하며, 비과세금액이 있을 경우<br>비과세액을 제외한 과세금액에서만 세액이 공제됩니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="national_pension" id="national_pension"><span>0</span>원</dd>
                </dl>
                <dl>
                    <dt>
                        건강보험
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:305px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    과세금액의 3.545%를 공제하며, 비과세금액이 있을 경우<br>비과세액을 제외한 과세금액에서만 세액이 공제됩니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="health_insurance" id="health_insurance"><span>0</span>원</dd>
                </dl>
                <dl>
                    <dt>
                        장기요양
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:202px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    건강보험 금액의 12.95%를 공제합니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="longterm_care_insurance" id="longterm_care_insurance"><span>0</span>원</dd>
                </dl>
                <dl>
                    <dt>
                        고용보험
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:295px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    과세금액의 0.9%를 공제하며, 비과세금액이 있을 경우<br>비과세액을 제외한 과세금액에서만 세액이 공제됩니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="unemployment_insurance" id="unemployment_insurance"><span>0</span>원</dd>
                </dl>
                <dl>
                    <dt>
                        소득세
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:260px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    부양가족수와 20세 이하 자녀수에 따라,<br>국세청의 근로소득 간이세액표 자료를 기준으로<br>공제됩니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="income_tax" id="income_tax"><span>0</span>원</dd>
                </dl>
                <dl>
                    <dt>
                        지방소득세
	                    <div class="toolTipWrap">
	                        <button type="button" class="icon_tooltip" onmouseover="Saramin.favorTooltip(this, 'on');" onmouseout="Saramin.favorTooltip(this, 'off');" onfocus="Saramin.favorTooltip(this, 'on');" onblur="Saramin.favorTooltip(this, 'off');"><span class="hidden">툴팁</span></button>
		                    <div class="toolTip" style="width:160px">
			                    <span class="tail tail_bottom_left"></span>
			                    <div class="toolTipCont txtLeft">
	                                <p class="txt">
	                                    소득세의 10%를 공제합니다.
	                                </p>
	                            </div>
	                        </div>
	                    </div>
                    </dt>
                    <dd class="residence_tax" id="residence_tax"><span>0</span>원</dd>
                </dl>
                <dl class="summary">
                    <dt>
                        공제액 합계
                    </dt>
                    <dd class="total_tax_deduction" id="total_tax_deduction"><span>0</span>원</dd>
                </dl>
            </div>
            <div class="wrap_btns">
                <button type="button" class="btn_basic2 type03 btn_copy" id="btn_calc_copy">결과 복사</button>
                <button type="button" class="btn_basic2 btn_reset" id="btn_reset">초기화</button>
            </div>
        </section>
    </div>
</div>
</body>
</html> --%>