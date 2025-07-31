document.addEventListener("DOMContentLoaded", () => {
	const urlParams = new URLSearchParams(window.location.search);
	const company = urlParams.get('company');
	const chartInstances = {}; // canvasId -> Chart 객체 저장


	axios.get(`/ajax/member/company_review/info/${company}`)
		.then(resp => {
			const data = resp.data;
			// 기업 정보
			document.getElementById("companyLogo").src = data.COMPANY_LOGO || "/img/default-logo.png";
			document.getElementById("companyName").textContent = data.COMPANY_NAME || "-";
			document.getElementById("reviewCount").textContent = data.TOTAL_REVIEW_COUNT || 0;

			// 비율 계산
			const total = data.TOTAL_REVIEW_COUNT || 0;
			const working = data.WORKING_COUNT || 0;
			const notWorking = data.NOT_WORKING_COUNT || 0;
			const workingPercent = total > 0 ? (working / total * 100).toFixed(1) : 0;
			const notWorkingPercent = total > 0 ? (notWorking / total * 100).toFixed(1) : 0;

			// DOM 업데이트
			document.getElementById("workingPercent").textContent = `${workingPercent}%`;
			document.getElementById("notWorkingPercent").textContent = `${notWorkingPercent}%`;
			document.getElementById("totalRespondentsText").innerHTML = `총 <b class="fw-bold fs-5 text-violet90">${total}</b>명의 답변입니다.`;

			// 반원 차트
			const ctx = document.getElementById('employeeStatusChart').getContext('2d');
			new Chart(ctx, {
				type: 'doughnut',
				data: {
					datasets: [{
						data: [working, notWorking],
						backgroundColor: ['#1abc9c', '#6a5acd'],
						borderWidth: 0
					}]
				},
				options: {
					rotation: -90,
					circumference: 180,
					cutout: '60%',
					plugins: { legend: { display: false } }
				}
			});
		})

	// === 탭 전환 ===
	const tabButtons = document.querySelectorAll(".tab-btn");
	const tabContents = document.querySelectorAll(".tab-content");
	tabButtons.forEach(btn => {
		btn.addEventListener("click", () => {
			tabButtons.forEach(b => b.classList.remove("active"));
			tabContents.forEach(c => c.classList.remove("active"));
			btn.classList.add("active");
			const target = document.getElementById(`tab-${btn.dataset.tab}`);
			if (target) target.classList.add("active");
		});
	});

	axios.get(`/ajax/member/company_review/detail/${company}`)
		.then(resp => {
			const data = resp.data;

			// --- 전체 평균 ---
			initOrUpdateChart("overallAvgChart", data.overallAvg);
			document.getElementById("overallAvgText").textContent = data.overallAvg.toFixed(1);

			// --- 질문별 차트 (전체) ---
			renderStaticCharts("questionCharts", data.questionAvgList, "q");

			// --- 직무 필터 버튼 ---
			const jobBtns = document.getElementById("topJobButtons");
			jobBtns.innerHTML = "";
			data.topJobStatsList.forEach((job, idx) => {
				const btn = document.createElement("button");
				btn.classList.add("filter-btn", "btn", "lh1", "badge-tag", "py-2", "px-4", "text-secondary", "fw-bold", "fs-14");
				btn.textContent = job.topJobName;
				if (idx === 0) btn.classList.add("active");
				btn.addEventListener("click", (e) => {
					document.querySelectorAll("#topJobButtons .filter-btn").forEach(b => b.classList.remove("active"));
					e.currentTarget.classList.add("active");
					updateTopJobCharts(job);
				});
				jobBtns.appendChild(btn);
			});

			// 직무 개수 표시
			document.getElementById("topJobCountInfo").textContent = `${data.topJobStatsList.length}`;

			// 첫 직무 초기화
			if (data.topJobStatsList.length > 0) {
				renderStaticCharts("topJobQuestionCharts", data.topJobStatsList[0].questionAvgList, "job");
				updateTopJobCharts(data.topJobStatsList[0]);
			}

			function updateTopJobCharts(job) {
				initOrUpdateChart("topJobAvgChart", job.topJobOverallAvg);
				document.getElementById("topJobAvgText").textContent = job.topJobOverallAvg.toFixed(1);

				job.questionAvgList.forEach((q, idx) => {
					requestAnimationFrame(() => { // DOM 렌더링 이후 실행
						const canvasId = `job-chart-${idx}`;
						initOrUpdateChart(canvasId, q.avgScore);
						const canvasEl = document.querySelector(`#${canvasId}`);
						if (canvasEl) { // DOM 존재할 때만 실행
							const span = canvasEl.parentElement.querySelector(".chart-info .avg span");
							if (span) span.textContent = q.avgScore.toFixed(1);
						}
					});
				});
			}
		});

	// === 최초 한 번만 DOM 생성 ===
	function renderStaticCharts(containerId, dataList, prefix) {
		const container = document.getElementById(containerId);
		container.innerHTML = "";
		dataList.forEach((q, idx) => {
			const card = document.createElement("div");
			/*card.classList.add("");*/

			const canvasId = `${prefix}-chart-${idx}`;
			const canvas = document.createElement("canvas");
			canvas.id = canvasId;
			canvas.width = 120;
			canvas.height = 120;
			card.appendChild(canvas);

			const info = document.createElement("div");
			info.classList.add("chart-info");
			info.innerHTML = `
        <p class="fs-18 fw-semibold lh1-4">${q.reviewSubjectName}</p>
        <p class="fs-6 fw-semibold total_count"><span class="fw-bold fs-16 text-violet90">${q.avgScore.toFixed(1)}</span>점</p>
      `;
			card.appendChild(info);
			container.appendChild(card);

			initOrUpdateChart(canvasId, q.avgScore);
		});
	}

	// === Chart.js 생성/갱신 ===
	function initOrUpdateChart(canvasId, score) {
		const canvas = document.getElementById(canvasId);
		if (!canvas) return;
		if (chartInstances[canvasId]) {
			// 데이터 갱신
			chartInstances[canvasId].data.datasets[0].data = [score, 5 - score];
			chartInstances[canvasId].data.datasets[0].backgroundColor = [getColor(score), '#eaeaea'];
			chartInstances[canvasId].update();
		} else {
			// 새로 생성
			const ctx = canvas.getContext("2d");
			chartInstances[canvasId] = new Chart(ctx, {
				type: 'doughnut',
				data: {
					datasets: [{
						data: [score, 5 - score],
						backgroundColor: [getColor(score), '#eaeaea']
					}]
				},
				options: {
					cutout: '50%',
					animation: { duration: 300 }, // 부드러운 전환
					plugins: { legend: { display: false } }
				}
			});
		}
	}

	// === 점수 색상 ===
	function getColor(score) {
		if (score < 2) return '#e74c3c';    // 빨강
		if (score < 4) return '#f1c40f';    // 노랑
		return '#2ecc71';                   // 초록
	}
});
