/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
  const aboardTC = document.querySelector("#aboardTC"); // 게시판 유형
  const faqTC = document.querySelector("#faqTC"); // 회원 유형
  const subTC = document.querySelector("#subTC"); // 질문 유형
  const aboardSV = document.querySelector("#aboardSV"); // 등록 버튼

  // 게시판 유형 선택 → FAQ만 회원/질문 유형 사용 가능
  aboardTC.addEventListener("change", function () {
    const isFaq = this.value === "BRDD-002";

    faqTC.value = "";
    faqTC.disabled = !isFaq;

    subTC.value = "";
    subTC.disabled = true;
    subTC
      .querySelectorAll("option:not(:first-child)")
      .forEach((opt) => opt.remove());
  });

  // 회원 유형 선택 → 질문 유형 목록 GET
  faqTC.addEventListener("change", function () {
    const selected = this.value;

    subTC.value = "";
    subTC.disabled = true;
    subTC
      .querySelectorAll("option:not(:first-child)")
      .forEach((opt) => opt.remove());

    if (!selected) return;

    fetch(`/ajax/admin/community/adminBoard/cmncodegroup/${selected}`)
      .then((res) => res.json())
      .then((group) => {
        const codeList = group.cmnCodeList;
        if (!Array.isArray(codeList)) return;

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

  // 저장 버튼 클릭 → 등록 또는 수정
  aboardSV.addEventListener("click", (e) => {
    e.preventDefault();

    // 요소들
    const boardTitleEl = document.querySelector('[data-field="boardTitle"]');
    const boardContentEl = document.querySelector(
      '[data-field="boardContent"]'
    );
    const boardNo = document.querySelector("input[name='boardNo']")?.value;
    const isEdit = Boolean(boardNo);
    const boardTypeCodeEl = subTC.value || faqTC.value || aboardTC.value;

    const boardTitle = boardTitleEl.value.trim();
    const boardContent = boardContentEl.value.trim();

    // 기존 에러 메시지 제거
    document.querySelectorAll(".invalid-feedback").forEach((el) => el.remove());
    document
      .querySelectorAll(".is-invalid")
      .forEach((el) => el.classList.remove("is-invalid"));

    let hasError = false;

    // 유틸: 에러 표시
    function showError(el, message) {
      el.classList.add("is-invalid");
      const feedback = document.createElement("div");
      feedback.className = "invalid-feedback";
      feedback.textContent = message;
      el.insertAdjacentElement("afterend", feedback);
    }


    // 개별 검증
    if (!boardTitle) showError(boardTitleEl, "제목을 입력해주세요.");
    if (!boardContent) showError(boardContentEl, "내용을 입력해주세요.");
    if (!boardTypeCode) showError(boardTypeCodeEl, "글 유형을 선택해주세요.");

    if (aboardTC.value=="") showError(aboardTC, "게시판 유형을 선택해주세요.");
    if (!faqTC.disabled && !faqTC.value) showError(faqTC, "회원 유형을 선택해주세요.");
    if (!subTC.disabled && !subTC.value) showError(subTC, "질문 유형을 선택해주세요.");

    if (hasError) return;

    // 저장 요청
    const method = isEdit ? "PUT" : "POST";
    const url = isEdit
      ? `/ajax/admin/community/adminBoard/${boardTypeCode}/${boardNo}`
      : `/ajax/admin/community/adminBoard/${boardTypeCode}`;

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        boardTitle,
        boardContent,
        boardTypeCode,
        ...(isEdit && { boardNo }),
      }),
    })
      .then((res) => {
        if (!res.ok) throw new Error("저장 실패");
        return res.json();
      })
      .then((data) => {
        const newBoardNo = boardNo || data.boardNo;
        alert(isEdit ? "수정되었습니다." : "등록되었습니다.");
        location.href = `/admin/community/adminBoard/aboardDetail?boardNo=${newBoardNo}`;
      })
      .catch((err) => {
        console.error("저장 중 오류:", err);
      });
  });
});
