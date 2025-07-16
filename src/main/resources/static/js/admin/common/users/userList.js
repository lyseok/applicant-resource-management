/**
 *
 */
const userTable = document.querySelector("#userTable");
const userTableBody = document.querySelector("#userTableBody");

//전체 리스트가 먼저 나옴
function auserList(){
fetch(`/ajax/admin/common/users`).then((resp) => {
  resp.json().then((rslt) => {
    console.log("회원 나오니? :", rslt);
    console.log("첫 번째 user", rslt[0]);

    rslt.forEach((user) => {
      console.log("유저 거기 있지? : ", user);
      
      const users = [
		  { 
		 	  userId: "userId"
		    , userPassword: "userPassword"
		    , userRole: "userRole"
		    , userWithdrawDate: "userWithdrawDate"
		    , userStatus: "userStatus"
		    , userEnabled: "userEnabled"
		  }
		 ];
		
		users.forEach((user, index) => {
		  const tr = document.createElement("tr");
		
		  // ✅ 체크박스 cell
		  const tdCheck = document.createElement("td");
		  const checkbox = document.createElement("input");
		  checkbox.type = "checkbox";
		  checkbox.className = "form-check-input";
		  checkbox.id = user.userId;
		  checkbox.value = user.userId;
		
		  const label = document.createElement("label");
		  label.htmlFor = user.userId;
		
		  // label 내부에 시각적으로 내용이 없으면 label 의미 없어지므로 optional
		  label.appendChild(checkbox);
		  tdCheck.appendChild(label);
		  tr.appendChild(tdCheck);
		
		  // ✅ 나머지 정보 cell들
		  const fields = [
		    user.userId,
		    user.userPassword,
		    user.userRole,
		    user.userWithdrawDate || "-",
		    user.userStatus,
		    user.userEnabled ? "Y" : "N"
		  ];
		
		  fields.forEach(text => {
		    const td = document.createElement("td");
		    td.textContent = text;
		    tr.appendChild(td);
		  });
		
		  userTableBody.appendChild(tr);
		});

      });
    });
  });
}  //auserList 함수 끝


//옵션이 선택되고 클릭되면
function userbar() {
  const userRole = document.querySelector("#userRole").value;
  const userId = document.querySelector("#userId").value.trim();

  const params = new URLSearchParams();

  if (userRole !== "-1") params.append("userRole", userRole);
  if (userId) params.append("userId", userId);

  fetch(`/ajax/admin/common/users?${params.toString()}`) //userId=corp03&userRole=ROLE_COMPANY
    .then((resp) => resp.json())
    .then((rslt) => {
      console.log("유저들아 나와! :", rslt);

      rslt.forEach((user) => {
        console.log("유저 거기 있지? : ", user);

		userTable.innerHTML = '';  //싹 지우고
		userTable.innerHTML = `
				<thead>
				    <tr>
				      <th>선택</th>
				      <th>아이디</th>
				      <th>비밀번호</th>
				      <th>회원구분</th>
				      <th>탈퇴일자</th>
				      <th>상태</th>
				      <th>활성여부</th>
				    </tr>
				  </thead>
				  <tbody id="userTableBody">
				    <!-- JS에서 동적으로 행 추가 -->
				  </tbody>`;
				  
		const userTableBody = document.querySelector("#userTableBody");
		
		auserList(params.toString());

         //검색 데이터로 채우기
		});
	});
};
//userbar 함수 끝

//<button id="userbar" onclick="userbar()">검색</button>로 함수 추가
const button = document.querySelector("#userbar");
if (button) {
  button.addEventListener("click", userbar);
}
//domcontentloaded 안해도 스크립트 순서대로 작동하니까 되지 않을까?