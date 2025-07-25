/* */
let itrdNum = 1;
let html = "";
// remove 시 필요 - 현재 활성화된 자소서 영역의 ID를 저장
// 초기값은 DomContentLoaded에서 첫 자소서 영역이 있다면 그 ID로 설정됩니다.
let removeAreaId = 'introduct01'; // 초기값은 HTML에 'introduct01'이 있다고 가정하고 시작

/* 카운트 숫자가 10 이하일 경우 앞에 0을 붙임 */
function fomatNumber(num) {
	return num < 10 ? '0' + num : String(num);
}

/* 스크롤 이동 부드럽게 - 스크롤이동 로직 분리, 재사용성 ↑ */
// 이 함수는 DOMContentLoaded 밖으로 이동하여 전역적으로 정의됩니다.
function setupPagerClickListener(pagerElement) {
	pagerElement.addEventListener('click', function(e) {
		e.preventDefault();

		// 모든 pager_li에서 'active' 클래스 제거
		document.querySelectorAll('.pager_li').forEach(p => {
			p.classList.remove('active');
		});

		// 클릭된 페이저에 'active' 클래스 추가
		this.classList.add('active');

		const targetId = this.dataset.areaId;
		removeAreaId = targetId; // removeAreaId를 현재 클릭된 버튼의 ID로 업데이트합니다

		const areaElement = document.getElementById(targetId);
		if (areaElement) {
			const headerOffset = 100; // 고정된 헤더의 높이 (px)
			const elementPosition = areaElement.getBoundingClientRect().top;
			const offsetPosition = elementPosition + window.scrollY - headerOffset;

			window.scrollTo({
				top: offsetPosition,
				behavior: 'smooth'
			});
		} else {
			console.warn(`[스크롤 오류] ID "${targetId}"를 가진 요소를 찾을 수 없습니다.`);
		}
	});
}


document.addEventListener("DOMContentLoaded", function() {
	const btnWrap = document.querySelector(".fixed_btn_wrap");
	const addBtn = btnWrap.querySelector(".add");
	const removeBtn = btnWrap.querySelector(".remove");
	const inrdForm = document.querySelector(".introduct_form");
	const inrdAddWrap = document.querySelector(".introduct_add_wrap");

	// 페이지 로드 시 초기 자소서 영역 및 페이저 설정
	const initialAreas = document.querySelectorAll('.introduct_area');
	const initialPagers = document.querySelectorAll('.pager_li');

	if (initialAreas.length > 0) {
		// 초기 자소서 영역이 있다면 itrdNum을 해당 개수로 설정
		itrdNum = initialAreas.length;
		// 첫 번째 자소서 영역과 페이저를 활성 상태로 설정
		initialAreas[0].classList.add('active-form'); // 필요하다면 활성 폼에 클래스 추가
		initialPagers[0].classList.add('active');
		removeAreaId = initialPagers[0].dataset.areaId; // removeAreaId 초기화

		// 모든 초기 페이저에 이벤트 리스너 붙이기
		initialPagers.forEach(link => {
			setupPagerClickListener(link);
		});
	} else {
		// 초기 자소서 영역이 없다면 itrdNum을 0으로 설정하고 remove 버튼 숨김
		itrdNum = 0;
		removeBtn.style.display = "none";
	}

	// remove 버튼의 초기 가시성 설정 (itrdNum에 따라)
	if (itrdNum > 1) {
		removeBtn.style.display = "block";
	} else {
		removeBtn.style.display = "none";
	}


	addBtn.addEventListener("click", (e) => {
		e.preventDefault();
		
		if (itrdNum >= 10) {
		  alert("자소서는 최대 10개까지 등록할 수 있습니다.");
		  return;
		}

    const updatedAreas = document.querySelectorAll('.introduct_area');
    const currentIdx = updatedAreas.length;     // name 인덱스
    const labelNum = currentIdx + 1;           // 라벨 표시용 (1부터)
    const totalNum = fomatNumber(labelNum);    // 01, 02 형식
    const padded = labelNum < 10 ? '0' + labelNum : labelNum;

    html = `
      <div class="mb-3">
        <label for="question${padded}" class="form-label h5 fw-bold">문항 ${padded}</label>
        <input type="text" id="question${padded}" name="introductionQuestionList[${currentIdx}].question" class="form-control" placeholder="지원동기, 입사 후 포부 등을 입력해주세요.">
      </div>
      <div class="mb-3">
        <label for="content${padded}" class="form-label h5 fw-bold">내용</label>
        <textarea id="content${padded}" name="introductionQuestionList[${currentIdx}].content" rows="10" class="form-control" placeholder="문항에 대한 상세내용을 입력해주세요."></textarea>
      </div>`;

		/* 폼 추가 코드 */
		const tempDiv = document.createElement('div');
		tempDiv.classList.add("introduct_area", "mt-4");
		const areaId = tempDiv.id = `introduct${totalNum}`;
		tempDiv.innerHTML = html;
		inrdAddWrap.append(tempDiv);

		/* 사이드 버튼 추가 코드 */
		const pager = document.createElement("a");
		pager.dataset.areaId = areaId;
		pager.innerText = totalNum;
		pager.classList.add("pager_li");

		// 모든 기존 pager_li에서 active 클래스 제거
		document.querySelectorAll('.pager_li').forEach(p => p.classList.remove('active'));
		// 새로 생성된 pager에 active 클래스 추가
		pager.classList.add('active');
		// removeAreaId를 새로 추가된 영역의 ID로 업데이트
		removeAreaId = areaId;

		// 새로 생성된 페이저에만 이벤트 리스너 붙이기
		setupPagerClickListener(pager);
		btnWrap.append(pager);

		// 스크롤 이동
		window.scrollTo({
			top: document.body.scrollHeight,
			behavior: 'smooth'
		});

		// 자소서가 1개 이상이면 remove 버튼 보이게
		if (itrdNum > 0) {
			removeBtn.style.display = "block";
		}
		
  	reindexForms();  // ✅ 추가된 폼 포함해서 동기화
	});


	/* remove - 버튼 클릭 시 동작 */
	removeBtn.addEventListener("click", (e) => {
		e.preventDefault();


		// ✅ 최소 1개는 남기도록 처리
		if (itrdNum <= 1) {
			alert("자소서는 최소 1개 이상 있어야 합니다.");
			return;
		}
		
		// 삭제확인 모달 띄우기
	  pendingRemoveAreaId = removeAreaId; // 현재 선택된 폼 ID를 임시 저장
	  const deleteModal = new bootstrap.Modal(document.getElementById('deleteItroductionModal'));
	  deleteModal.show(); // 모달 열기
	});


});


function reindexForms() {
  const areas = document.querySelectorAll('.introduct_area');
  const pagers = document.querySelectorAll('.pager_li');
  
  itrdNum = areas.length;

  areas.forEach((area, idx) => {
    const labelNum = idx + 1;
    const padded = labelNum < 10 ? '0' + labelNum : labelNum;
    const newAreaId = `introduct${padded}`;
    area.id = newAreaId;

    const qInput = area.querySelector('input[name*="question"]');
    const cInput = area.querySelector('textarea[name*="content"]');
    const qLabel = area.querySelector('label[for^="question"]');
    const cLabel = area.querySelector('label[for^="content"]');

    if (qInput) {
      qInput.name = `introductionQuestionList[${idx}].question`;
      qInput.id = `question${padded}`;
      if (qLabel) {
        qLabel.setAttribute("for", `question${padded}`);
        qLabel.textContent = `문항 ${padded}`;
      }
    }

    if (cInput) {
      cInput.name = `introductionQuestionList[${idx}].content`;
      cInput.id = `content${padded}`;
      if (cLabel) {
        cLabel.setAttribute("for", `content${padded}`);
      }
    }
  });

  // 🔁 페이저 재정렬
  pagers.forEach((pager, idx) => {
    const padded = idx + 1 < 10 ? '0' + (idx + 1) : (idx + 1);
    const areaId = `introduct${padded}`;
    pager.innerText = padded;
    pager.dataset.areaId = areaId;
  });

  // ✅ removeAreaId는 마지막 영역 기준으로 설정
  if (areas.length > 0) {
    const last = areas[areas.length - 1];
    removeAreaId = last.id;
  }
}


document.addEventListener('DOMContentLoaded', function () {
	// 전송 시 자소서 명 입력 안했을 경우 전송 안되게 ! 
  const form = document.getElementById("introduction");
  form.addEventListener("submit", function (e) {
      // 자소서명
      const title = document.getElementById("title").value.trim();
      if (!title) {
          alert("자소서명을 입력해주세요.");
          document.getElementById("title").focus();
          e.preventDefault();
          return;
      }

      // 문항 + 내용 체크
      const questions = document.querySelectorAll("input[name^='introductionQuestionList'][name$='.question']");
      const contents = document.querySelectorAll("textarea[name^='introductionQuestionList'][name$='.content']");

      for (let i = 0; i < questions.length; i++) {
          const q = questions[i].value.trim();
          const c = contents[i].value.trim();

          if (!q) {
              alert(`문항 ${i + 1}을 입력해주세요.`);
              questions[i].focus();
              e.preventDefault();
              return;
          }

          if (!c) {
              alert(`문항 ${i + 1}의 내용을 입력해주세요.`);
              contents[i].focus();
              e.preventDefault();
              return;
          }
      }
  });
	
	// 모달 삭제 시 컨펌 !
	document.querySelector('#deleteItroductionModal .btn-danger').addEventListener('click', function () {
	  if (!pendingRemoveAreaId) return;
	
	  // 1. 삭제할 자소서 영역과 페이저 찾기
	  const removeElement = document.getElementById(pendingRemoveAreaId);
	  const pagerToRemove = document.querySelector(`.pager_li[data-area-id="${pendingRemoveAreaId}"]`);
		const removeBtn = document.querySelector(".remove");
	
	  if (removeElement) removeElement.remove();
	  if (pagerToRemove) pagerToRemove.remove();
	
	  // 2. 카운트 감소
	  itrdNum--;
	
	  // 3. 폼 및 페이저 재정렬
	  reindexForms();
	
	  // 4. 마지막 영역 기준으로 removeAreaId 업데이트 + active 클래스 처리
	  const remainingAreas = document.querySelectorAll('.introduct_area');
	  if (remainingAreas.length > 0) {
	    const newActiveArea = remainingAreas[remainingAreas.length - 1];
	    removeAreaId = newActiveArea.id;
	
	    // 모든 페이저에서 active 제거
	    document.querySelectorAll('.pager_li').forEach(p => p.classList.remove('active'));
	
	    // 마지막 페이저에 active 클래스 추가
	    const newActivePager = document.querySelector(`.pager_li[data-area-id="${removeAreaId}"]`);
	    if (newActivePager) newActivePager.classList.add('active');
	
	    // 마지막 영역으로 스크롤 이동
	    newActiveArea.scrollIntoView({ behavior: 'smooth', block: 'start' });
	  } else {
	    removeAreaId = null;
	  }
		console.log("나니고레/????", itrdNum)
	  // 5. 삭제 버튼 숨기기 조건
	  if (itrdNum <= 1) {
	    removeBtn.style.display = "none";
	  }
	
	  // 6. 모달 닫기 + 초기화
	  const modal = bootstrap.Modal.getInstance(document.getElementById('deleteItroductionModal'));
	  modal.hide();
	  pendingRemoveAreaId = null;
	});
});



