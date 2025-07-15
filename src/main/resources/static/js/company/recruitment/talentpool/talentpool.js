// ✅ 선택된 모달 데이터 저장용 객체
const modalData = {
  topJob: null,
  job: null,
  career: null,
  location: null,
  edudone: null,
  gedu: null
};

// ✅ 공통 모달 열기 함수
function openModalById(id) {
  const el = document.getElementById(id);
  if (!el) return;
  const modal = bootstrap.Modal.getOrCreateInstance(el);
  modal.show();
}

// ✅ 공통 모달 닫기 함수
function closeModalById(id) {
  const el = document.getElementById(id);
  if (!el) return;
  const modal = bootstrap.Modal.getOrCreateInstance(el);
  modal.hide();
}

// ✅ 버튼 활성화 체크
function toggleNextButton(selectId, btnId) {
  const select = document.getElementById(selectId);
  const btn = document.getElementById(btnId);
  if (!select || !btn) return;

  select.addEventListener("change", () => {
    btn.disabled = select.value === "";
  });
}

// Ajax로 공통코드 불러와서 경력 option 생성
function loadCareerCommonCode() {
  fetch('/ajax/code/cmncodegroup/YEXP')
    .then(res => {
      if (!res.ok) throw new Error('서버 응답 에러');
      return res.json();
    })
    .then(data => {
      const careerSelect = document.getElementById("careerSelect");
      careerSelect.innerHTML = '<option value="">선택</option>';

      data.cmnCodeList.forEach(code => {
        const option = document.createElement('option');
        option.value = code.codeDetailNo;    // 컨트롤러로 보낼 값
        option.textContent = code.codeName;  // 화면에 보여줄 값
        careerSelect.appendChild(option);
      });
    })
    .catch(err => console.error("공통코드 로딩 에러: ", err));
}

// 페이지 로드시 자동 호출
document.addEventListener("DOMContentLoaded", () => {
  loadCareerCommonCode();
});


// 학력 공통코드 불러오기 함수
function loadEducationCommonCode() {
  fetch('http://localhost/ajax/code/cmncodegroup/EDUC')
    .then(res => {
      if (!res.ok) throw new Error('서버 응답 실패');
      return res.json();
    })
    .then(data => {
      const eduSelect = document.getElementById("eduSelect");
      eduSelect.innerHTML = '<option value="">선택</option>';

      data.cmnCodeList.forEach(code => {
        const option = document.createElement("option");
        option.value = code.codeDetailNo;   // Controller로 넘길 값
        option.textContent = code.codeName; // 사용자에게 보이는 텍스트
        eduSelect.appendChild(option);
      });
    })
    .catch(err => console.error("학력 코드 로딩 실패:", err));
}

document.addEventListener("DOMContentLoaded", () => {
  loadCareerCommonCode();       // 기존 경력
  loadEducationCommonCode();    // 🔥 학력 코드 호출 추가
  // ...기타 toggleNextButton 등
});

// 졸업 여부 공통코드 불러오기
function loadGraduateStatusCommonCode() {
  fetch('http://localhost/ajax/code/cmncodegroup/GRAD')
    .then(res => {
      if (!res.ok) throw new Error('서버 응답 실패');
      return res.json();
    })
    .then(data => {
      const gradSelect = document.getElementById("geduSelect");
      gradSelect.innerHTML = '<option value="">선택</option>';

      data.cmnCodeList.forEach(code => {
        const option = document.createElement("option");
        option.value = code.codeDetailNo;   // 컨트롤러에 전달될 값
        option.textContent = code.codeName; // 사용자에게 보이는 값
        gradSelect.appendChild(option);
      });
    })
    .catch(err => console.error("졸업 여부 코드 로딩 실패:", err));
}

document.addEventListener("DOMContentLoaded", () => {
  loadCareerCommonCode();
  loadEducationCommonCode();
  loadGraduateStatusCommonCode();  // ✅ 졸업여부 호출 추가

  toggleNextButton("positionSelect", "nextTopJob");
  toggleNextButton("jobSelect", "nextJob");
  toggleNextButton("careerSelect", "nextCareer");
  toggleNextButton("locationSelect", "nextLocation");
  toggleNextButton("eduSelect", "nextEdu");
  toggleNextButton("geduSelect", "nextGedu");
});

document.getElementById("nextGedu")?.addEventListener("click", () => {
  modalData.gedu = document.getElementById("geduSelect").value;
  closeModalById("select-gedu");

  // ✅ 선택한 값들 hidden input에 반영
  document.getElementById("hiddenTopJob").value = modalData.topJob || '';
  document.getElementById("hiddenJob").value = modalData.job || '';
  document.getElementById("hiddenCareer").value = modalData.career || '';
  document.getElementById("hiddenLocation").value = modalData.location || '';
  document.getElementById("hiddenEdu").value = modalData.edudone || '';
  document.getElementById("hiddenGedu").value = modalData.gedu || '';

  // 선택 요약 보여주기
  updateSelectedFilterSummary();

  console.log("🎯 최종 선택 완료: ", modalData);
});



document.addEventListener("DOMContentLoaded", () => {

  // 각 select의 다음 버튼 활성화 감지
  toggleNextButton("positionSelect", "nextTopJob");
  toggleNextButton("jobSelect", "nextJob");
  toggleNextButton("careerSelect", "nextCareer");
  toggleNextButton("locationSelect", "nextLocation");
  toggleNextButton("eduSelect", "nextEdu");
  toggleNextButton("geduSelect", "nextGedu");

  // 다음 버튼 클릭 시 다음 모달로 이동
  document.getElementById("nextTopJob")?.addEventListener("click", () => {
    modalData.topJob = document.getElementById("positionSelect").value;
    closeModalById("select-top-job");
    openModalById("select-job");
  });

  document.getElementById("nextJob")?.addEventListener("click", () => {
    modalData.job = document.getElementById("jobSelect").value;
    closeModalById("select-job");
    openModalById("select-career");
  });

  document.getElementById("nextCareer")?.addEventListener("click", () => {
    modalData.career = document.getElementById("careerSelect").value;
    closeModalById("select-career");
    openModalById("select-location");
  });

  document.getElementById("nextLocation")?.addEventListener("click", () => {
    modalData.location = document.getElementById("locationSelect").value;
    closeModalById("select-location");
    openModalById("educationModal");
  });

  document.getElementById("nextEdu")?.addEventListener("click", () => {
    modalData.edudone = document.getElementById("eduSelect").value;
    closeModalById("educationModal");
    openModalById("select-gedu");
  });

  document.getElementById("nextGedu")?.addEventListener("click", () => {
    modalData.gedu = document.getElementById("geduSelect").value;
    closeModalById("select-gedu");
    // 🔚 최종 단계 (필요시 여기서 submit 또는 추가 동작)
    console.log("🎯 최종 선택 완료: ", modalData);
  });

  // 필터 초기화
  document.getElementById("resetFilter")?.addEventListener("click", () => {
    document.getElementById("filterForm").reset();
    Object.keys(modalData).forEach(k => modalData[k] = null);
  });

});
