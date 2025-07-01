<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맞춤법 검사기</title>
</head>
<body>
	<div class="wrap_tool">
    <div class="content_tool">
        <section class="wrap_enquiry wrap_checker">
            <form name="spell_form" id="spell_form">
                <input type="hidden" name="temporarily" id="temporarily">
                <h2 class="blind">글자수세기 / 맞춤법 검사 내용 입력</h2>
                <div class="checker_spell">
                    <textarea name="content" id="character_counter_content" cols="70" rows="22" class="checker_input" placeholder="내용을 입력해주세요."></textarea>
                    <div class="checker_preview" id="checker_preview" style="display:none;"></div>
                </div>

                <div class="summary">
                    <p><span>공백 포함</span><strong class="letter" id="current_msglen">00</strong><span>자</span><strong class="byte" id="current_msg_byte">00</strong><span>byte</span></p>
                    <p><span>공백 제외</span><strong class="letter" id="current_msglen_except_blank">00</strong><span>자</span><strong class="byte" id="current_msg_byte_except_blank">0</strong><span>byte</span></p>
                </div>
                <div class="wrap_btns">
                    <button type="button" class="btn_basic2 type03 btn_copy" id="copy_all">전체 복사</button>
                    <button type="button" class="btn_basic2 btn_reset" id="spell_reset">초기화</button>
                </div>
            </form>
        </section>

        <section class="wrap_result">
            <h2 class="blind">글자수세기 / 맞춤법 검사기 결과</h2>
            <div class="title">
                <span class="text">맞춤법 검사</span>
            </div>
            <div class="title_sub" id="title_sub" style="display: none;">
                <span>맞춤법 오류</span><strong id="spell_count">0개</strong>
                <button type="button" class="btn_basic_type02" id="spell_done_all">일괄 수정</button>
            </div>

            <!-- 결과 산출 전  -->
            <div class="result_pre" id="result_pre">
                <span class="text_pre">맞춤법 검사 시작을 눌러주세요.</span>
                <span class="text_pre">최대 <span class="text_info">4000자</span>까지 검사가 가능합니다.</span>
                <span class="text_loading">맞춤법 검사 중입니다.</span>
            </div>
            <!-- 맞춤법 검사 문구 모음   -->
            <div class="checker_result" id="checker_result">

            </div>
            <div class="wrap_btns one">
                <button type="button" class="btn_basic2 type05" id="spell_check">검사 시작</button>
                <button type="button" class="btn_basic2 type05" id="spell_completion" style="display:none">검사 완료</button>
            </div>
        </section>
    </div>
</div>
	
</body>
</html>