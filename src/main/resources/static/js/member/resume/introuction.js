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
		itrdNum++;
		console.log(`현재 자소서 개수 (itrdNum): ${itrdNum}`);
		const totalNum = fomatNumber(itrdNum);


		// 기존 HTML 폼 구조에 맞춰 introductionList[인덱스]를 사용합니다.
		// 자소서 명, 문항, 내용 필드의 인덱스는 itrdNum - 1로 해야 정확합니다.
		// 왜냐하면 itrdNum은 1부터 시작하고 배열 인덱스는 0부터 시작하기 때문입니다.
		// 예를 들어, 첫 번째 추가되는 자소서는 itrdNum이 2가 되지만 (기존 1 + 추가 1),
		// 배열 인덱스는 [1]이 되어야 합니다.
		const currentIdx = itrdNum - 1; // 여기를 수정

		html = `<div class="mb-3">
              <label for="question${currentIdx}" class="form-label h5 fw-bold">문항 ${itrdNum}</label>
              <input type="text" class="form-control" id="question${currentIdx}" name="introductionQuestionList[${currentIdx}].question" placeholder="지원동기, 입사 후 포부 같은 내용을 입력해주세요.">
            </div>
            <div class="mb-3">
              <label for="content${currentIdx}" class="form-label fw-bold h5">내용</label>
              <textarea class="form-control" id="content${currentIdx}" name="introductionQuestionList[${currentIdx}].content" rows="10" placeholder="여기에 새로운 자소서 내용이 들어갑니다"></textarea>
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
	});


	/* remove - 버튼 클릭 시 동작 */
	removeBtn.addEventListener("click", (e) => {
		e.preventDefault();


		// ✅ 최소 1개는 남기도록 처리
		if (itrdNum <= 1) {
			alert("자소서는 최소 1개 이상 있어야 합니다.");
			return;
		}

		// 1. 현재 활성화된 자소서 영역 찾기
		const removeElement = document.getElementById(removeAreaId);

		if (removeElement) {
			// 2. DOM에서 해당 자소서 영역 제거
			inrdAddWrap.removeChild(removeElement);

			// 3. 해당 자소서 영역에 연결된 사이드 버튼도 제거
			const pagerToRemove = document.querySelector(`.pager_li[data-area-id="${removeAreaId}"]`);
			if (pagerToRemove) {
				btnWrap.removeChild(pagerToRemove);
			}

			// 4. itrdNum 감소 (0보다 작아지지 않도록 방지)
			if (itrdNum > 0) {
				itrdNum--;
			}

			// 5. 남은 자소서 영역이 있다면, 마지막 영역으로 removeAreaId 업데이트 및 스크롤
			const remainingAreas = document.querySelectorAll('.introduct_area');
			if (remainingAreas.length > 0) {
				const newActiveArea = remainingAreas[remainingAreas.length - 1]; // 남은 것 중 마지막
				removeAreaId = newActiveArea.id; // removeAreaId를 새로운 활성 영역의 ID로 업데이트

				// 모든 pager_li에서 active 클래스 제거
				document.querySelectorAll('.pager_li').forEach(p => p.classList.remove('active'));
				// 새로운 활성 영역에 해당하는 사이드 버튼에 active 클래스 적용
				const newActivePager = document.querySelector(`.pager_li[data-area-id="${removeAreaId}"]`);
				if (newActivePager) {
					newActivePager.classList.add('active');
				}

				// 새로운 활성 영역으로 스크롤 (부드럽게)
				newActiveArea.scrollIntoView({
					behavior: 'smooth',
					block: 'start'
				});
			} else { // 모든 자소서 영역이 제거된 경우
				removeAreaId = null; // 활성화된 영역 없음
			}
			const updatedAreas = document.querySelectorAll('.introduct_area');
			updatedAreas.forEach((area, idx) => {
				const newIndex = idx;
				const newNum = fomatNumber(newIndex + 1);
				const newAreaId = `introduct${newNum}`;
				area.id = newAreaId;

				const questionInput = area.querySelector('input[name^="introductionQuestionList"]');
				const contentTextarea = area.querySelector('textarea[name^="introductionQuestionList"]');
				const questionLabel = area.querySelector(`label[for^="question"]`);

				

				if (questionInput) {
					questionInput.name = `introductionQuestionList[${newIndex}].question`;
					questionInput.id = `question${newNum}`;
					area.querySelector(`label[for^="question"]`).setAttribute("for", `question${newNum}`);
					questionLabel.textContent = `문항 ${newNum}`; // ✅ 텍스트 갱신					
				}

				if (contentTextarea) {
					contentTextarea.name = `introductionQuestionList[${newIndex}].content`;
					contentTextarea.id = `content${newNum}`;
					area.querySelector(`label[for^="content"]`).setAttribute("for", `content${newNum}`);
				}

				// ✅ removeAreaId 재설정: 항상 마지막 항목으로
				if (updatedAreas.length > 0) {
				  const lastArea = updatedAreas[updatedAreas.length - 1];
				  removeAreaId = lastArea.id; // <== 핵심
				}

			});

			// 🔁 페이저 순서 및 data-area-id 재설정
			const updatedPagers = document.querySelectorAll('.pager_li');
			updatedPagers.forEach((pager, idx) => {
				const newNum = fomatNumber(idx + 1);
				const newAreaId = `introduct${newNum}`;
				pager.innerText = newNum;
				pager.dataset.areaId = newAreaId;
			});


			// 6. itrdNum이 0개일 경우 removeBtn 숨기기
			if (itrdNum === 1) {
				removeBtn.style.display = "none";
			}
			console.log("Current itrdNum after remove:", itrdNum);
		} else {
			// 이 메시지는 removeAreaId가 잘못되었을 때만 발생해야 합니다.
			console.warn(`[제거 오류] 현재 활성화된 자소서 영역 (ID: ${removeAreaId})을 찾을 수 없습니다.`);
		}
	});


});





