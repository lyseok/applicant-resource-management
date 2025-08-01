document.addEventListener("DOMContentLoaded", () => {
	const urlParams = new URLSearchParams(window.location.search);
	const company = urlParams.get('company');
	const chartInstances = {}; // canvasId -> Chart 객체 저장

	// 기업 정보 호출
	axios.get(`/ajax/member/company_review/info/${company}`)
		.then(resp => {
			const data = resp.data;

			// 기업 정보 텍스트 삽입
			document.getElementById("companyLogo").src = data.COMPANY_LOGO || "/img/default-logo.png";
			document.getElementById("companyName").textContent = data.COMPANY_NAME || "-";
			document.getElementById("reviewCount").textContent = data.TOTAL_REVIEW_COUNT || 0;
			document.getElementById("comIndu").textContent = data.INDU_NAME || '-';
			document.getElementById("comSize").textContent = data.COM_SIZE_NAME || '-';
			document.getElementById("comMem").textContent = '직원 수 ' + data.COM_MEM + '명' || '-';
			document.getElementById("ceoName").textContent = data.CEO_NAME || '-';

			// 비율 계산
			const total = data.TOTAL_REVIEW_COUNT || 0;
			const working = data.WORKING_COUNT || 0;
			const notWorking = data.NOT_WORKING_COUNT || 0;
			const workingPercent = total > 0 ? (working / total * 100).toFixed(1) : 0;
			const notWorkingPercent = total > 0 ? (notWorking / total * 100).toFixed(1) : 0;

			// 텍스트 삽입
			document.getElementById("workingPercent").textContent = `${workingPercent}%`;
			document.getElementById("notWorkingPercent").textContent = `${notWorkingPercent}%`;
			document.getElementById("totalRespondentsText").innerHTML = `총 <b class="fw-bold fs-5 text-violet90">${total}</b>명의 답변입니다.`;

			// 반원 차트 (현/전직 비율)
			const ctx = document.getElementById('employeeStatusChart').getContext('2d');
			new Chart(ctx, {
				type: 'doughnut',
				data: {
					datasets: [{
						data: [working, notWorking],
						backgroundColor: ['#aca7ff', '#5d3fff'], // cherry-pie 계열
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
		});

	// 탭 전환 처리
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

	// 상세 리뷰 데이터 조회 및 차트 렌더링
	axios.get(`/ajax/member/company_review/detail/${company}`)
		.then(resp => {
			const data = resp.data;

			// 전체 평균 차트
			initOrUpdateChart("overallAvgChart", data.overallAvg);
			document.getElementById("overallAvgText").textContent = data.overallAvg.toFixed(1);

			// 전체 질문별 평균
			renderStaticCharts("questionCharts", data.questionAvgList, "q");

			// 직무 버튼 렌더링
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

			// 직무 개수 텍스트
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
					requestAnimationFrame(() => {
						const canvasId = `job-chart-${idx}`;
						initOrUpdateChart(canvasId, q.avgScore);
						const canvasEl = document.querySelector(`#${canvasId}`);
						if (canvasEl) {
							const span = canvasEl.parentElement.querySelector(".chart-info .avg span");
							if (span) span.textContent = q.avgScore.toFixed(1);
						}
					});
				});
			}
		});

	// 질문별 차트 렌더링
	function renderStaticCharts(containerId, dataList, prefix) {
		const container = document.getElementById(containerId);
		container.innerHTML = "";
		dataList.forEach((q, idx) => {
			const card = document.createElement("div");

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
				<p class="fs-6 fw-semibold total_count">
					<span class="fw-bold fs-16 text-violet90 avg"><span>${q.avgScore.toFixed(1)}</span></span>점
				</p>`;
			card.appendChild(info);
			container.appendChild(card);

			initOrUpdateChart(canvasId, q.avgScore);
		});
	}

	// 차트 생성 또는 갱신
	function initOrUpdateChart(canvasId, score) {
		const canvas = document.getElementById(canvasId);
		if (!canvas) return;

		if (chartInstances[canvasId]) {
			chartInstances[canvasId].data.datasets[0].data = [score, 5 - score];
			chartInstances[canvasId].data.datasets[0].backgroundColor = [getColor(score), '#eaeaea'];
			chartInstances[canvasId].update();
		} else {
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
					animation: { duration: 300 },
					plugins: {
						legend: { display: false }
					}
				}
			});
		}
	}

	// 점수별 색상 (cherry-pie 계열, 강한 대비)
	function getColor(score) {
		if (score < 1) return '#ecdcff';   // 아주 연한 보라
		if (score < 2) return '#d0b2ff';   // 연보라
		if (score < 3) return '#6900ff';   // 진한 보라
		if (score < 4) return '#5700b4';   // 짙은 보라
		if (score < 4.5) return '#460095'; // 더 짙은 보라
		return '#290052';                 // 거의 검보라 (최상 점수)
	}
});
