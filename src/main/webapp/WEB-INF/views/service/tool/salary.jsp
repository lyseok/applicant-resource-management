<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연봉 계산기</title>
<script src="/dist/dashboard/js/salary_report.js"></script>
</head>
<body>

<div class="container">
  <div class="left">
    <div class="section">
      <h3>필수 입력</h3>
      <label>급여 기준</label>
      <div class="btn-group">
        <button id="">연봉</button>
        <button id="">월급</button>
      </div>
      <br/>
      <label>퇴직금</label>
      <div class="btn-group">
        <button id="">별도</button>
        <button id="">포함</button>
      </div>
      <br/>
      <label>연봉</label>
      <br/>
      <input type="text" id="y" />
      <br/>
      <div class="btn-group">
      
        <button />+1000만 <br/>
       
        <button>+100만</button> <br/>
      
        <button>+10만</button>  <br/>
      </div>
    </div>

    <div class="section">
      <h3>선택 입력</h3>
      <label>부양가족 수 (본인 포함)</label>
      <div class="counter">
        <button id="minous">-</button>
        <span><input type="text" id="numFamily"></span>
        <button id="plus">+</button>
      </div>

      <label>20세 이하 자녀 수</label>
      <div class="counter">
        <button id="minous">-</button>
        <span><input type="text" readonly="readonly"></span>
        <button id="plus">+</button>
      </div>

      <label>비과세액</label>
      <br/>
      <input type="text" id="nonTax"/> 원
    </div>
  </div>

  <div class="right">
    <h3>월 예상 실수령액</h3>
    <div>
    	<input type="text" readonly="readonly" id="afterTax" >원
    </div>

    <ul class="deductions">
      <li><span>국민연금</span><span id="pension">원</span></li>
      <li><span>건강보험</span><span id="health"></span></li>
      <li><span>장기요양</span><span id="care"></span></li>
      <li><span>고용보험</span><span id="hire"></span></li>
      <li><span>소득세</span><span id="incomeTax"></span></li>
      <li><span>지방소득세</span><span id="incomeTaxLocal"></span></li>
      <li class="total"><span>공제액 합계</span><span id="totalTax"></span></li>
    </ul>

    <div class="bottom-buttons">
      <button>결과 복사</button>
      <button>초기화</button>
    </div>
  </div>
</div>

</body>
</html>