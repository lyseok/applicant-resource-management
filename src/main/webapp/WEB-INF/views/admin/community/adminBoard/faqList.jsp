<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

<!-- 일단은 사이드바 수정 안해서 UFAQ로 직접 쳐서 들어가야 함(-U1 붙이지 말고) -->  
  <div id="faqno">
    <p class="h4">자주 묻는 질문 탭 선택</p>
    <button type="button" class="faq-tab" data-group="UFAQ">일반회원</button>
    <button type="button" class="faq-tab" data-group="CFAQ">기업회원</button>
    <button type="button" class="faq-tab" data-group="ALL">전체보기</button>
  </div>

  <ul id="faqList"></ul>

<script>
    document.addEventListener("DOMContentLoaded", () => {
      const urlParams = new URLSearchParams(window.location.search);
      let currentGroup = urlParams.get("type") || "UFAQ"; // 기본값 UFAQ

      loadFaqGroup(currentGroup);

      // 버튼 클릭 시
      document.querySelectorAll(".faq-tab").forEach(btn => {
        btn.addEventListener("click", () => {
          currentGroup = btn.dataset.group;
          loadFaqGroup(currentGroup);
        });
      });
    });

    const groupMap = {
      UFAQ: ["UFAQ-U1", "UFAQ-U2", "UFAQ-U3", "UFAQ-U4", "UFAQ-U5", "UFAQ-U6", "UFAQ-U7", "UFAQ-U8", "UFAQ-U9"],
      CFAQ: ["CFAQ-C1", "CFAQ-C2", "CFAQ-C3", "CFAQ-C4", "CFAQ-C5", "CFAQ-C6", "CFAQ-C7", "CFAQ-C8", "CFAQ-C9", "CFAQ-C10", "CFAQ-C11"]
    };

    function loadFaqGroup(groupCode) {
      const faqList = document.querySelector("#faqList");
      faqList.innerHTML = "<li>불러오는 중...</li>";

      let codes = [];
      if (groupCode === "ALL") {
        codes = [...groupMap.UFAQ, ...groupMap.CFAQ];
      } else {
        codes = groupMap[groupCode] || [];
      }

      const fetches = codes.map(code =>
        fetch(`/ajax/admin/board/admin_board/${code}`).then(resp => resp.json())
      );

      Promise.all(fetches)
        .then(results => {
          const flatList = results.flat();
          if (flatList.length === 0) {
            faqList.innerHTML = "<li>게시글이 없습니다.</li>";
            return;
          }

          let html = "";
          flatList.forEach(item => {
            html += `
              <li>
                <strong>[${item.boardTypeCode}]</strong>
                <a href="/admin/board/admin_board/detail?no=${item.boardNo}&type=${item.boardTypeCode}">
                  ${item.boardTitle}
                </a>
                <div>${item.boardWriteDate}</div>
              </li>`;
          });
          faqList.innerHTML = html;
        })
        .catch(err => {
          faqList.innerHTML = "<li>불러오기 실패</li>";
          console.error(err);
        });
    }
  </script>
</body>

