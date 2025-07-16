/**
 *
 */
const auserList = document.querySelector("#auserList");

//리스트가 먼저 나옴
fetch(`/ajax/admin/common/users`).then((resp) => {
  resp.json().then((rslt) => {
    console.log("회원 나오니? :", rslt);
    console.log("첫 번째 user", rslt[0]);

    rslt.forEach((user) => {
      console.log("유저 거기 있지? : ", user);

      const labels = [
        "userId",
        "userPassword",
        "userRole",
        "userWithdrawDate",
        "userStatus",
        "userEnabled",
      ];

      user.forEach((value, index) => {
        let p = document.createElement("p");

        let displayValue =
          value !== null && value !== undefined ? value : "값 없음";
        p.textContent = labels[index] + ": " + displayValue;
        auserList.appendChild(p);
      });
    });
  });
});

//옵션이 선택되고 클릭되면
function userbar() {
  if (userRole != "-1") {
    auserList.innerHTML = ""; //기존 회원 리스트 지우고
    fetch(`/ajax/admin/common/users?userRole=${userRole}`).then((resp) => {
      resp.json().then((rslt) => {
        console.log("유저 역할은? :", rslt);
        console.log("검색한 역할", rslt[0]);

        rslt.forEach((user) => {
          console.log("유저 거기 있지? : ", user);

          const labels = [
            "userId",
            "userPassword",
            "userRole",
            "userWithdrawDate",
            "userStatus",
            "userEnabled",
          ];

          user.forEach((value, index) => {
            let p = document.createElement("p");

            let displayValue =
              value !== null && value !== undefined ? value : "값 없음";
            p.textContent = labels[index] + ": " + displayValue;
            auserList.appendChild(p);
          });
        });
      });
    });
  }

  //입력이 있으면, 입력 없어도 회원 역할만 넘어가면 위 fetch만 탐
  if (userId != null || userId != "") {
    fetch(`/ajax/admin/common/users/{userId}`).then((resp) => {
      resp.json().then((rslt) => {
        console.log("유저 아이디는 하나! :", rslt);
		console.log("검색한 역할", rslt[0]);

        rslt.forEach((user) => {
          console.log("유저 거기 있지? : ", user);

          const labels = [
            "userId",
            "userPassword",
            "userRole",
            "userWithdrawDate",
            "userStatus",
            "userEnabled",
          ];

          user.forEach((value, index) => {
            let p = document.createElement("p");

            let displayValue =
              value !== null && value !== undefined ? value : "값 없음";
            p.textContent = labels[index] + ": " + displayValue;
            auserList.appendChild(p);
          });
        });
      });
    });
  }
}  //userbar 함수 끝
