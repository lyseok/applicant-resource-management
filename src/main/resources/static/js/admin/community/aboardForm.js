/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
  const aboardTC = document.querySelector("#aboardTC");
  const faqTC = document.querySelector("#faqTC");
  const subTC = document.querySelector("#subTC");

  // 1차 선택
  aboardTC.addEventListener("change", function () {
    faqTC.value = "";
    faqTC.disabled = this.value !== "BRDD-002";

    subTC.value = "";
    subTC.disabled = true;
    subTC
      .querySelectorAll("option:not(:first-child)")
      .forEach((opt) => opt.remove());
  });

  // 2차 선택 → 질문 유형 코드 가져오기
  faqTC.addEventListener("change", function () {
    const selected = this.value;

    subTC.value = "";
    subTC
      .querySelectorAll("option:not(:first-child)")
      .forEach((opt) => opt.remove());

    if (!selected) {
      subTC.disabled = true;
      return;
    }

    // 질문 유형 목록 요청
    fetch(`/ajax/admin/community/adminBoard/cmncodegroup/${selected}`)
      .then((res) => res.json())
      .then((group) => {
        const codeList = group.cmnCodeList; // <- 여기서 바로 꺼냄
        codeList.forEach((code) => {
          const option = document.createElement("option");
          option.value = code.codeDetailNo;
          option.textContent = code.codeName;
          subTC.appendChild(option);
        });
        subTC.disabled = false;
      })
      .catch((err) => {
        console.error("질문유형 로딩 오류:", err);
        subTC.disabled = true;
      });
  });
});
