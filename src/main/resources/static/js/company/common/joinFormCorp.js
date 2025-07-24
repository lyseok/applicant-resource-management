/**
 * 
 */
const brInput = document.getElementById("brNumber");
const msg = document.getElementById("msg_corp_code");
const companyArea = document.getElementById("area_input_company");

const serviceKey = "%2BJC5tK7N7UE0GM%2FpoYcJBHIIb%2FuzMV33idwD93rG%2BSZGxM5fQNNsVsnEKSeA8HatKJ%2FicIZeQWsUnUE2E65CmA%3D%3D";

brInput.addEventListener("input", function () {
  let val = brInput.value.replace(/\D/g, ""); // 숫자만

  // 하이픈 자동 추가
  if (val.length <= 3) {
    brInput.value = val;
  } else if (val.length <= 5) {
    brInput.value = `${val.slice(0, 3)}-${val.slice(3)}`;
  } else if (val.length <= 10) {
    brInput.value = `${val.slice(0, 3)}-${val.slice(3, 5)}-${val.slice(5)}`;
  }

   // 조건에 따라 메시지 표시
  if (val.length === 10) {
    msg.innerText = "";
    checkBizNumber(val);
  } else {
    msg.innerText = "사업자등록번호 10자리를 정확히 입력해 주세요.";
    msg.style.color = "red";
    companyArea.style.display = "none";
  }
});

function checkBizNumber(b_no) {
  const payload = { b_no: [b_no] };

  fetch(`https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${serviceKey}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
    },
    body: JSON.stringify(payload)
  })
  .then(res => res.json())
  .then(data => {
    const result = data?.data?.[0];
    if (result?.b_stt === "계속사업자") {
      msg.innerText = "✅ 유효한 사업자번호입니다.";
      msg.style.color = "green";
      companyArea.style.display = "block";
    } else {
      msg.innerText = "❌ 유효하지 않은 사업자번호입니다.";
      msg.style.color = "red";
      companyArea.style.display = "none";
    }
  })
  .catch(err => {
    msg.innerText = "❗ API 요청 실패";
    msg.style.color = "red";
    console.error("사업자번호 검증 실패", err);
    companyArea.style.display = "none";
  });
}