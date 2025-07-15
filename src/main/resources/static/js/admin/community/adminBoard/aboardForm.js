/**
 *
 */
const aboardForm = document.querySelector("#aboardForm");
//<select id="boardTypeCode">..</select>
//엘리먼트(object)     <body>다큐먼트</body> 그 안에 들어있는 id속성의 값이 boardTypeCode인 엘리먼트
const boardTypeCode = document.querySelector("#boardTypeCode");
const codeGroupNo = document.querySelector("#codeGroupNo");
const memType = document.querySelector("#memType");

function aform(no) {
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

  if (no) {
    // 수정일 경우
    fetch(`/ajax/admin/board/admin_board/detail/${no}`)
      .then((resp) => resp.json())
      .then((data) => {
        // data 값으로 form input 채우기
        aboardForm.boardTitle.value = data.boardTitle;
        aboardForm.boardContent.value = data.boardContent;
        aboardForm.userId.value = data.userId;
        boardTypeCode.value = data.boardTypeCode;

        // 옵션 동적 변경
        boardTypeCode.onchange = function () {
          if (boardTypeCode.value === "BRDD-002") {
            codeGroupNo.disabled = false;
            codeGroupNo.onchange = function () {
              if (codeGroupNo.value === "-1") {
                //첫번째 option 엘리먼트 선택
                memType.selectedIndex = 0;
                memType.disabled = true;
              } else {
                memType.disabled = false;

                if (memType.length != 0 || codeGroupNo == -1) {
                  memType.innerHTML = "";

                  memOpt = document.createElement("option");
                  memOpt.value = "-1";
                  memOpt.textContent = "--선택--";
                  memType.appendChild(memOpt);

                  memType.value = "-1";
                }

                fetch(`/ajax/code/cmncodegroup/${codeGroupNo.value}`).then(
                  (resp) => {
                    resp.json().then((rslt) => {
                      rslt.cmnCodeList.forEach((vo) => {
                        let option = document.createElement("option");
                        option.value = vo.codeDetailNo;
                        option.innerHTML = vo.codeName;

                        memType.appendChild(option);
                      });
                    });
                  }
                );
              }
            }; //end codeGroupNo.onchange
          } else {
            codeGroupNo.disabled = true;
            codeGroupNo.value = "-1";
            memType.disabled = true;
            memType.value = "-1";
          }
        };

        // 선택된 타입에 따라 FAQ 옵션 세팅
        if (
          data.boardTypeCode === "BRDD-002" &&
          data.cmnCodeGroupVOList.length > 0
        ) {
          codeGroupNo.disabled = false;
          codeGroupNo.value = data.cmnCodeGroupVOList[0].codeGroupNo;

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
                data.cmnCodeGroupVOList[0].cmnCodeList[0].codeDetailNo;
            });
        }
      });

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

      if (no) boardData.boardNo = no;

      const url = no
        ? `/ajax/admin/board/admin_board/detail/${no}` // 수정
        : `/ajax/admin/board/admin_board/${boardTypeCode.value}`; // 등록

      fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(boardData),
      }).then((resp) => {
        resp.json().then((rslt) => {
          console.log("글자", rslt.ok);
        });
      });
    };
    //여기까지 옵션 생성
  }
  aform(no); //만든 다음 마지막으로 호출!
}

window.aform = aform; //전역 함수로 한번더 선언
