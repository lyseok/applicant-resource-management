/**
 *
 */
const aboardForm = document.querySelector("#aboardForm");
//<select id="boardTypeCode">..</select>
//엘리먼트(object)     <body>다큐먼트</body> 그 안에 들어있는 id속성의 값이 boardTypeCode인 엘리먼트
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroupNo = document.querySelector("#codeGroupNo");
const memType = document.querySelector("#memType");

function aform(no, type) {
  const aboardForm = document.querySelector("#aboardForm");
  const boardTypeCode = document.querySelector("#boardTypeCode");

  // 옵션 생성 먼저
  fetch(`/ajax/code/cmncodegroup/BRDD`).then((resp) => {
    resp.json().then((rslt) => {
      rslt.cmnCodeList.map((v, i) => {
        //vo[1], vo[2] 만 넣어야 함
        if (i > 0) {
          let option = document.createElement("option");
          option.value = v.codeDetailNo;
          option.innerHTML = v.codeName;
          boardTypeCode.appendChild(option);
        }
      });
    });
  });

  // 옵션 동적 변경
  boardTypeCode.onchange = function () {
    //첫 선택이 공지사항이 아니면
    if (boardTypeCode.value !== "BRDD-003") {
      codeGroupNo.disabled = false; //두번째 선택지 풀어주고
      //두번째 선택지 옵션 값 넣어주는 비동기 호출
      fetch(`/ajax/code/cmncodegroup/${codeGroupNo.value}`)
      .then((resp) => {
        console.log("resp.json() : ", resp.json());

        resp.json().then((rslt) => {

          rslt.cmnCodeList.forEach((vo) => {
            let option = document.createElement("option");
            option.value = vo.codeDetailNo;
            option.innerHTML = vo.codeName;

            memType.appendChild(option);
          });
          // 선택된 타입에 따라 FAQ 옵션 세팅
          //첫번째 선택이 faq(자식 많음)이면서 두번째 옵션에 값이 존재하면(CFAQ, UFAQ)
          if (
            rslt.boardTypeCode === "BRDD-002" &&
            rslt.cmnCodeGroupVOList.length > 0
          ) {
            codeGroupNo.disabled = false;
            codeGroupNo.value = rslt.cmnCodeGroupVOList[0].codeGroupNo;

            fetch(`/ajax/code/cmncodegroup/${codeGroupNo.value}`)
              .then((resp) => resp.json())
              .then((rslt2) => {
                rslt2.cmnCodeList.forEach((vo) => {
                  let option = document.createElement("option");
                  option.value = vo.codeDetailNo;
                  option.innerHTML = vo.codeName;
                  memType.appendChild(option);
                });

                memType.disabled = false;
                memType.value =
                  rslt.cmnCodeGroupVOList[0].cmnCodeList[0].codeDetailNo;
              });
          }
        });
      });

      //두번째 선택지에 따라 바뀌는 함수 생성
      codeGroupNo.onchange = function () {
        //두번째 선택지가 기본값이면
        if (codeGroupNo.value === "-1") {
          //첫번째 option 엘리먼트 선택
          //(세번째 선택지가 기본값으로 고정되고 못 쓰게)
          memType.selectedIndex = 0;
          memType.disabled = true;
        } else {
          //두번째 선택지가 결정돼있으면 세번째 선택지 풀어주고
          memType.disabled = false;

          //세번째 선택지가 들어있는데 두번째 선택지는 기본값이면
          if (memType.length != 0 || codeGroupNo == -1) {
            //세번째 선택지 싹 지우고
            memType.innerHTML = "";
            //옵션 태그를 다시 만들어준 다음
            memOpt = document.createElement("option");
            memOpt.value = "-1";
            memOpt.textContent = "--선택--";
            memType.appendChild(memOpt);
            //기본값 옵션태그가 선택되게
            memType.value = "-1";
          }
        }
      }; //end codeGroupNo.onchange
    } else {
      //첫 선택이 공지사항이면 다 꺼둠
      codeGroupNo.disabled = true;
      codeGroupNo.value = "-1";
      memType.disabled = true;
      memType.value = "-1";
    }
  };

  //수정이면(no, type이 있으면)
  if ((no, type)) {
    // 수정일 경우
    fetch(`/ajax/admin/board/admin_board/detail/${no}`)
      .then((resp) => resp.json())
      .then((data) => {
        // data 값으로 form input 채우기
        aboardForm.boardTitle.value = data.boardTitle;
        aboardForm.boardContent.value = data.boardContent;
        aboardForm.userId.value = data.userId;

        boardTypeCode.onchange(type);

        if (no && type) {
          boardData.boardNo = no;
          boardData.boardTypeCode = type;
        }

        boardTypeCode.value = type;

        const url = no
          ? `/ajax/admin/board/admin_board/detail/${no}` // 수정
          : `/ajax/admin/board/admin_board/${type}`; // 등록

        fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(boardData),
        }).then((resp) => {
          resp.json().then((rslt) => {
            console.log("글자", rslt.ok);
          });
        });
      });
  }
}

aform(); //만든 다음 마지막으로 호출!

aboardForm.onsubmit = function (e) {
  e.preventDefault();
  //JSON Object
  //JavaScript Object Notation => {"키":값}
  /*
		1.할아버지 [AdminBoardVO] : userId, boardTypeCode, boardTitle, boardContent
		2.첫째 아빠 [AdminBoardVO.cmnCodeGroupVOList[0]] : codeGroupNo
		3.첫째 딸 [AdminBoardVO.cmnCodeGroupVOList[0].cmnCodeList[0]] : codeDetailNo(=memType)
		*/
  const boardData = {
    boardTitle: aboardForm.boardTitle.value,
    boardContent: aboardForm.boardContent.value,
    userId: aboardForm.userId.value,
    boardTypeCode: boardTypeCode.value,
    cmnCodeGroupVOList: [
      {
        codeGroupNo: codeGroupNo.value,
        cmnCodeList: [{ codeDetailNo: memType.value }],
      },
    ],
  };
};

window.aform = aform; //전역 함수로 한번더 선언
