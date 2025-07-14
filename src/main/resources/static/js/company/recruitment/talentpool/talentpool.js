/*function bindUserCardClick() {
  document.querySelectorAll('.user-card').forEach(card => {
	card.addEventListener('click', (e) => {
	  const userId = card.dataset.userId;
	  if (userId) {
		location.href = '/talentpool/detail/' + userId;
	  } else {
		console.warn("❗ userId 값이 없음");
	  }
	});
  });
}

document.addEventListener("DOMContentLoaded", () => {
  bindUserCardClick(); // ✅ 초기 바인딩

  const form = document.getElementById("filterForm");
  const talentListWrapper = document.getElementById("talent-list-wrapper");

  form.addEventListener("submit", (e) => {
	e.preventDefault();
	const formData = new FormData(form);

	fetch("/talentpool/filter", {
	  method: "POST",
	  body: formData
	})
	  .then(res => res.text())
	  .then(html => {
		talentListWrapper.innerHTML = html;
		bindUserCardClick(); // ✅ 갱신된 DOM에 다시 바인딩
	  })
	  .catch(err => {
		console.error("🚨 검색 실패:", err);
		talentListWrapper.innerHTML = "<p style='color:red;'>검색 실패</p>";
	  });
  });
});
*/// 📌 유저 카드 클릭 시 상세 페이지 이동

// 📌 유저 카드 클릭 시 상세 페이지 이동
function bindUserCardClick() {
  document.querySelectorAll('.user-card').forEach(card => {
    card.addEventListener('click', () => {
      const userId = card.dataset.userId;
      if (userId) {
        location.href = `/company/talentpool/detail/${userId}`;
      } else {
        console.warn("❗ userId 값이 없습니다. card:", card);
      }
    });
  });
}

// 🔁 직군 선택 시 직무 필터링
function bindTopJobToJobSelect() {
  const positionSelect = document.getElementById("positionSelect");
  const jobSelect = document.getElementById("jobSelect");
  if (!positionSelect || !jobSelect) return;

  const allJobOptions = Array.from(jobSelect.querySelectorAll("option")).slice(1); // "선택" 제외

  positionSelect.addEventListener("change", () => {
    const selectedTopJobCode = positionSelect.value;
    jobSelect.innerHTML = '<option value="">선택</option>';

    allJobOptions.forEach(option => {
      if (option.dataset.topJobCode === selectedTopJobCode) {
        jobSelect.appendChild(option);
      }
    });
  });
}

// 🔁 필터 초기화 버튼
function bindResetButton() {
  const resetBtn = document.getElementById("resetFilter");
  const form = document.getElementById("filterForm");
  const talentListWrapper = document.getElementById("talent-list-wrapper");

  if (!resetBtn || !form || !talentListWrapper) return;

  resetBtn.addEventListener("click", () => {
    form.reset();

    fetch("/company/talentpool/filter", {
      method: "POST",
      body: new FormData(form)
    })
      .then(res => res.text())
      .then(html => {
        talentListWrapper.innerHTML = html;
        bindUserCardClick(); // DOM 갱신 후 재바인딩
      })
      .catch(err => {
        console.error("🚨 초기화 중 오류:", err);
        talentListWrapper.innerHTML = "<p style='color:red;'>초기화 실패</p>";
      });
  });
}

// 📌 일반 필터 검색
function handleFilterSubmit() {
  const form = document.getElementById("filterForm");
  const talentListWrapper = document.getElementById("talent-list-wrapper");
  if (!form || !talentListWrapper) return;

  form.addEventListener("submit", function (e) {
    e.preventDefault();
    const formData = new FormData(form);

    fetch("/company/talentpool/filter", {
      method: "POST",
      body: formData
    })
      .then(res => res.text())
      .then(html => {
        talentListWrapper.innerHTML = html;
        bindUserCardClick(); // 검색 후 카드 클릭 재바인딩
      })
      .catch(err => {
        console.error("🚨 필터링 요청 중 오류:", err);
        talentListWrapper.innerHTML = "<p style='color:red;'>검색 실패</p>";
      });
  });
}

document.addEventListener("DOMContentLoaded", function () {
	const form = document.getElementById("filterForm");
	const talentListWrapper = document.getElementById("talent-list-wrapper");

	form.addEventListener("submit", function (e) {
		e.preventDefault();
		const formData = new FormData(form);

		fetch("/company/talentpool/filter", {
			method: "POST",
			body: formData
		})
			.then(res => res.text())
			.then(html => {
				talentListWrapper.innerHTML = html;
			})
			.catch(err => {
				console.error("오류 발생:", err);
				talentListWrapper.innerHTML = "<p style='color:red;'>검색 실패</p>";
			});
	});
});

// 상단필터 검색
function bindAdvancedSearch() {
  const searchBtn = document.querySelector("#btn btn_violet");
  const talentListWrapper = document.getElementById("talent-list-wrapper");

  if (!searchBtn || !talentListWrapper) return;

  searchBtn.addEventListener("click", function (e) {
    e.preventDefault();

    const license = document.querySelector("input[name='license']").value.trim();
    const skillName = document.querySelector("input[name='skillName']").value.trim();

    const formData = new FormData();
    formData.append("license", license);
    formData.append("skillName", skillName);

    fetch("company/talentpool/higtSearch", {
      method: "POST",
      body: formData
    })
      .then(res => res.text())
      .then(html => {
        talentListWrapper.innerHTML = html;
        bindUserCardClick(); // 🔁 새 DOM에도 클릭 이벤트 바인딩
      })
      .catch(err => {
        console.error("🚨 고급 검색 오류:", err);
        talentListWrapper.innerHTML = "<p style='color:red;'>검색 실패</p>";
      });
  });
}



// ✅ 초기 실행
document.addEventListener("DOMContentLoaded", () => {
  bindUserCardClick();       // 유저카드 클릭 이벤트
  handleFilterSubmit();      // 필터 폼 제출
  bindResetButton();         // 필터 초기화 버튼
  bindTopJobToJobSelect();   // 직군 → 직무 연결
  bindAdvancedSearch();      // 고급 검색 버튼
});


axios.get('/ajax/code/cmncodegroup/YEXP')  // 데이터 요청 URL을 실제로 변경
  .then(function (response) {
    // 서버로부터 받은 응답 데이터가 'cmnCodeList' 내에 있다는 가정
    populateCareerSelect(response.data.cmnCodeList);
  })
  .catch(function (error) {
    console.error('Error fetching data: ', error);
  });

// 받은 데이터로 <select> 태그에 <option> 추가하기
function populateCareerSelect(data) {
  const yearSelect = document.getElementById('yearSel');
  
  // 데이터 배열을 순회하며 <option> 추가
  data.forEach(item => {
    const option = document.createElement('option');
    option.value = item.codeDetailNo;  // value는 codeDetailNo
    option.textContent = item.codeName;  // 화면에 표시될 텍스트는 codeName

    yearSelect.appendChild(option);  // <select> 태그에 <option> 추가
  });
}

axios.get('/ajax/code/cmncodegroup/EDUC')  // 데이터 요청 URL을 실제로 변경
  .then(function (response) {
    // 서버로부터 받은 응답 데이터가 'cmnCodeList' 내에 있다는 가정
    populateEducationSelect(response.data.cmnCodeList);
  })
  .catch(function (error) {
    console.error('Error fetching data: ', error);
  });

// 받은 데이터로 <select> 태그에 <option> 추가하기
function populateEducationSelect(data) {
  const educationSelect = document.getElementById('finedu');
  
  // 데이터 배열을 순회하며 <option> 추가
  data.forEach(item => {
    const option = document.createElement('option');
    option.value = item.codeDetailNo;  // value는 codeDetailNo
    option.textContent = item.codeName;  // 화면에 표시될 텍스트는 codeName

    educationSelect.appendChild(option);  // <select> 태그에 <option> 추가
  });
}

// Axios로 데이터 요청
axios.get('/ajax/code/cmncodegroup/GRAD')  // 데이터 요청 URL을 실제로 변경
  .then(function (response) {
    // 서버로부터 받은 응답 데이터가 'cmnCodeList' 내에 있다는 가정
    populateGraduationSelect(response.data.cmnCodeList);
  })
  .catch(function (error) {
    console.error('Error fetching data: ', error);
  });

// 받은 데이터로 <select> 태그에 <option> 추가하기
function populateGraduationSelect(data) {
  const graduationSelect = document.getElementById('edustatus');
  
  // 데이터 배열을 순회하며 <option> 추가
  data.forEach(item => {
    const option = document.createElement('option');
    option.value = item.codeDetailNo;  // value는 codeDetailNo
    option.textContent = item.codeName;  // 화면에 표시될 텍스트는 codeName

    graduationSelect.appendChild(option);  // <select> 태그에 <option> 추가
  });
}
