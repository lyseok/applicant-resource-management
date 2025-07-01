<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	리뷰를 남겨주세요
 	

<div class="d-flex">
  <!-- form-check-inline 대신 flex-column을 사용해 세로 정렬, align-items-center로 가운데 정렬 -->
  <div class="form-check flex-column align-items-center me-3">
    <input class="form-check-input" type="radio" name="flexRadioDefault" id="radio1">
    <label class="form-check-label mt-1" for="radio1">
      매우 아니다
    </label>
  </div>

  <div class="form-check flex-column align-items-center me-3">
    <input class="form-check-input" type="radio" name="flexRadioDefault" id="radio2" checked>
    <label class="form-check-label mt-1" for="radio2">
      아니다
    </label>
  </div>

  <div class="form-check flex-column align-items-center me-3">
    <input class="form-check-input" type="radio" name="flexRadioDefault" id="radio3">
    <label class="form-check-label mt-1" for="radio3">
      보통
    </label>
  </div>

  <div class="form-check flex-column align-items-center me-3">
    <input class="form-check-input" type="radio" name="flexRadioDefault" id="radio4">
    <label class="form-check-label mt-1" for="radio4">
      그렇다
    </label>
  </div>

  <div class="form-check flex-column align-items-center">
    <input class="form-check-input" type="radio" name="flexRadioDefault" id="radio5">
    <label class="form-check-label mt-1" for="radio5">
      매우 그렇다
    </label>
  </div>
</div>


</body>
</html>