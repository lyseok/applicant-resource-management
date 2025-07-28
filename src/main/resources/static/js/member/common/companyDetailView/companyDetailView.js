document.addEventListener('DOMContentLoaded', () => {

	const userId = 'testCompany';
	
	axios
		.get('/ajax/member/company_view/testCompany')
		.then((resp) => {
			const company = resp.data;
			console.log(company);
			bindingCompanyImage(company.fileList);
			console.log('체크', document.getElementById('head-com-name'));
			console.log('체크', document.getElementById('head-com-indu'));

			document.getElementById('industryType').textContent = company.industryType || '-';
			document.getElementById('comMem').textContent = company.comMem + '명';
			document.getElementById('comType').textContent = company.comType || '-';
			document.getElementById('comCreateYear').textContent = company.comCreateYear || '-';
			document.getElementById('comCapital').textContent = formatSalary(company.comCapital) || '-';
			document.getElementById('ceoName').textContent = company.ceoName || '-';
			document.getElementById('comMainBiz').textContent = company.comMainBiz || '-';
			document.getElementById('insuranceYn').textContent = company.insuranceYn === 'Y'
				? '국민연금, 건강보험, 고용보험, 산재보험'
				: '-';
			document.getElementById('comUrl').textContent = company.comUrl || '-';
			document.getElementById('comInfo').textContent = company.comInfo || '-';
			document.getElementById('head-com-name').textContent = company.comName;
			document.getElementById('head-com-indu').textContent = company.industryType;
			document.getElementById('salaryCompanyName').textContent = company.comName + '의 평균 연봉';
			document.getElementById('company_bg').src = company.comBackgroundImg;
			document.getElementById('logoImg').src = company.comLogo;

			const addr = company.comAddr || '';
			document.getElementById('comAddr').textContent = addr;
			const span = document.getElementById('companyAddr');

			if (span) span.textContent = addr;
			else console.error('#companyAddr 요소를 찾을 수 없습니다.');

			const btn = document.getElementById('btnOpenNaverMap');
			if (btn) {
				btn.addEventListener('click', () => {
					if (!addr) {
						alert('주소 정보가 없습니다.');
						return;
					}
					const url = 'https://map.naver.com/v5/search/' + encodeURIComponent(addr);
					window.open(url, '_blank');
				});

			}

		})
		.catch((err) => {
			console.error('회사정보 로드 실패', err);
		});

	axios.get(`/ajax/member/company_view/sales/${userId}`).then((resp) => {
		const sales = resp.data;
		const labels = sales.map((s) => s.comSalesYear);
		const data = sales.map((s) => s.comSalesAmount / 100_000_000); // 억 원
		const avgData = sales.map((s) => s.avgSalesAmount / 100_000_000); // 억 원



		const latest = sales[sales.length - 1];
		console.log('킄큭큭', latest);
		document.getElementById('comSales').textContent = formatKRW(latest.comSalesAmount) + '(전년도 기준)'

		new Chart(document.getElementById('salesChart').getContext('2d'), {
			type: 'line',
			data: {
				labels: labels,
				datasets: [
					{
						label: '매출액',
						data: data,
						backgroundColor: 'rgba(153, 102, 255, 0.2)',
						borderColor: 'rgba(153, 102, 255, 1)',
						borderWidth: 2,
						pointBackgroundColor: 'rgba(153, 102, 255, 1)',
						fill: true,
						tension: 0.3,
					},
					{
						label: '업계 평균 매출액',
						data: avgData,
						borderColor: 'rgba(200, 200, 200, 0.6)',
						borderDash: [5, 5],
						fill: false,
						pointRadius: 0,
						tension: 0.1,
						datalabels: {
							display: false,
						},
					},
				],
			},
			options: {
				responsive: false,
				plugins: {
					datalabels: {
						display: true,
						align: 'end',
						offset: 8, // 약간 위로 띄움
						font: { size: 12 },
						formatter: (value) => formatKRW(value * 100_000_000),
					},
					tooltip: {
						callbacks: {
							label: function(ctx) {
								const raw = ctx.raw * 100_000_000;
								if (ctx.dataset.label.includes('평균')) {
									return '업계 평균: ' + formatKRW(raw);
								}
								const growth = sales[ctx.dataIndex].growthRatePercent;
								return `매출액: ${formatKRW(raw)} (작년 대비 ${growth >= 0 ? '+' : ''
									}${growth.toFixed(2)}%)`;
							},
						},
					},
				},
				layout: {
					padding: {
						bottom: 80,
						right: 30,
					},
				},
				scales: {
					y: {
						title: {
							display: true,
							text: '억 원',
							color: '#666',
							font: { size: 14 },
						},
						beginAtZero: true,
					},
				},
			},
			plugins: [ChartDataLabels],
		});

		if (latest) {
			document.getElementById(
				'salesUpdate'
			).textContent = `${latest.comSalesYear}.12 기준`;
			document.getElementById('totalSalesAmount').textContent = formatKRW(
				latest.comSalesAmount
			);

			const growth = latest.growthRatePercent ?? 0;
			const growthColor = growth >= 0 ? 'blue' : 'red';
			const growthArrow = growth >= 0 ? '▲' : '▼';
			document.getElementById(
				'salesYoY'
			).innerHTML = `<span style="color:${growthColor}">${Math.abs(
				growth
			).toFixed(0)}% ${growthArrow}</span>`;
		}
	});

	axios.get(`/ajax/member/company_view/profit/${userId}`).then((resp) => {
		const profit = resp.data;
		console.log(profit);

		const labels = profit.map((p) => p.comProfitYear);
		const data = profit.map((p) => p.comOperatingProfit / 100_000_000);
		const avgData = profit.map((p) => p.avgOperatingProfit / 100_000_000);

		const latest = profit[profit.length - 1];

		new Chart(document.getElementById('profitChart').getContext('2d'), {
			type: 'line',
			data: {
				labels: labels,
				datasets: [
					{
						label: '영업 이익',
						data: data,
						backgroundColor: 'rgba(153, 102, 255, 0.2)',
						borderColor: 'rgba(153, 102, 255, 1)',
						borderWidth: 2,
						pointBackgroundColor: 'rgba(153, 102, 255, 1)',
						fill: true,
						tension: 0.3,
					},
					{
						label: '업계 평균 영업이익',
						data: avgData,
						borderColor: 'rgba(200, 200, 200, 0.6)',
						borderDash: [5, 5],
						fill: false,
						pointRadius: 0,
						tension: 0.1,
						datalabels: {
							display: false,
						},
					},
				],
			},
			options: {
				responsive: false,
				plugins: {
					datalabels: {
						display: true,
						align: 'end',
						offset: 8, // 약간 위로 띄움
						font: { size: 12 },
						formatter: (value) => formatKRW(value * 100_000_000),
					},
					tooltip: {
						callbacks: {
							label: function(ctx) {
								const raw = ctx.raw * 100_000_000;
								if (ctx.dataset.label.includes('평균')) {
									return '업계 평균: ' + formatKRW(raw);
								}
								const growth = profit[ctx.dataIndex].growthRatePercent;
								return `영업 이익: ${formatKRW(raw)} (작년 대비 ${growth >= 0 ? '+' : ''
									}${growth.toFixed(2)}%)`;
							},
						},
					},
				},
				layout: {
					padding: {
						bottom: 80,
						right: 30,
					},
				},
				scales: {
					y: {
						title: {
							display: true,
							text: '억 원',
							color: '#666',
							font: { size: 14 },
						},
						beginAtZero: true,
					},
				},
			},
			plugins: [ChartDataLabels],
		});
		if (latest) {
			document.getElementById(
				'profitUpdate'
			).textContent = `${latest.comProfitYear}.12 기준`;
			document.getElementById('totalOperatingProfit').textContent = formatKRW(
				latest.comOperatingProfit
			);

			const growth = latest.growthRatePercent ?? 0;
			const growthColor = growth >= 0 ? 'blue' : 'red';
			const growthArrow = growth >= 0 ? '▲' : '▼';
			document.getElementById(
				'profitYoY'
			).innerHTML = `<span style="color:${growthColor}">${Math.abs(
				growth
			).toFixed(0)}% ${growthArrow}</span>`;
		}
	});




	axios.get(`/ajax/member/company_view/notice/${userId}`)
		.then(resp => {
			const notices = resp.data;
			console.log(notices);
			renderNotice(notices);
			renderNoticeTab(notices);

			// 1) 총 채용 횟수
			document.querySelector('.recruit-history .total')
				.textContent = `총 ${notices.length}회 채용 중`;

			// 2) 신입/경력 집계
			const counts = notices.reduce((acc, r) => {
				const isNew = r.yearCodeName.trim() === '신입';
				acc[isNew ? '신입' : '경력']++;
				return acc;
			}, { '신입': 0, '경력': 0 });

			// 3) 도넛 차트 생성
			const ctx = document.getElementById('recruitTypeChart').getContext('2d');
			new Chart(ctx, {
				type: 'doughnut',
				data: {
					labels: ['경력', '신입'],
					datasets: [{
						data: [counts['경력'], counts['신입']],
						backgroundColor: ['rgba(153, 102, 255, 0.7)', 'rgba(248, 115, 171, 0.7)']
					}]
				},
				options: {
					cutout: '40%',
					responsive: false,
					plugins: {
						datalabels: {
							color: '#fff',
							formatter: v => `${v}건`
						},
						tooltip: {
							callbacks: {
								label: ctx => `${ctx.label}: ${ctx.parsed}건`
							}
						}
					}
				},

				plugins: [ChartDataLabels]
			});
		})
		.catch(err => {
			console.error('채용 공고 불러오기 실패', err);
		});


	axios.get(`/ajax/member/company_view/top_notice/${userId}`)
		.then(resp => {
			const jobs = resp.data;
			if (!Array.isArray(jobs) || jobs.length === 0) return;

			// 데이터 가공
			const labels = jobs.map(j => j.JOB_NAME);
			const counts = jobs.map(j => j.TOTAL_POSITIONS);

			// 차트 생성
			const ctx = document.getElementById('topJobChart').getContext('2d');
			new Chart(ctx, {
				type: 'bar',
				data: {
					labels,
					datasets: [{
						label: '채용 건수',
						data: counts,
						backgroundColor: 'rgba(124, 58, 237, 0.3)'
					}]
				},
				options: {
					responsive: false,
					maintainAspectRatio: false,
					plugins: {
						datalabels: {
							display: true,
							color: '#333',
							font: { weight: 'bold' },
							anchor: 'end',    // 막대 끝 기준
							align: 'start',   // 막대 위에서 살짝 떨어지게
							offset: -10,      // 여백 확보
							formatter: v => `${v}건`
						},
						tooltip: {
							callbacks: {
								label: ctx => `${ctx.label}: ${ctx.raw}건`
							}
						}
					},
					scales: {

						y: {
							beginAtZero: true,
							title: { display: true, text: '채용 건수' }
						}
					}
				},
				plugins: [ChartDataLabels]
			});
		})
		.catch(err => console.error('많이 뽑은 직무 불러오기 실패', err));


	axios.get(`/ajax/member/company_view/salary/${userId}`)
		.then(resp => {
			const salaries = resp.data;
			console.log('연봉', salaries);

			const salaryAvgExclExec = salaries[0].salaryAvgExclExec;
			document.getElementById('salaryAvgGross').textContent = formatSalary(salaryAvgExclExec);
			document.getElementById('salary-avg').textContent = formatSalary(salaryAvgExclExec) + '(임원제외)';

			// 1) DOM 준비
			const section = document.querySelector('.tab-content[data-section="salary"]');
			const ctx = document.getElementById('salaryChart').getContext('2d');

			// 2) 차트용 데이터 가공 (만원 단위)
			const labels = salaries.map(s => s.codeName);
			const avgData = salaries.map(s => Math.round(parseInt(s.avgByRank, 10)));
			const minData = salaries.map(s => Math.round(parseInt(s.salaryMin, 10)));
			const maxData = salaries.map(s => Math.round(parseInt(s.salaryMax, 10)));

			// 3) 차트 생성
			new Chart(ctx, {
				type: 'line',
				data: {
					labels,
					datasets: [
						{
							label: '직급별 평균연봉(만원)',
							data: avgData,
							borderColor: 'rgba(124, 58, 237, 1)',
							backgroundColor: 'rgba(124, 58, 237, 0.2)',
							pointBackgroundColor: 'rgba(124, 58, 237, 1)',
							borderWidth: 2,
							fill: false,
							tension: 0.3,
						}
					]
				},
				options: {
					responsive: false,
					plugins: {
						datalabels: {
							display: true,
							align: 'top',
							font: {
								size: 11,
								weight: 'bold',
							},
							formatter: (value) => formatSalary(value), // ×10000 제거
							color: '#333',
						},
						tooltip: {
							callbacks: {
								label(ctx) {
									const val = ctx.raw * 10000;
									return `${ctx.dataset.label}: ${formatSalary(val)}`;
								}
							}
						},
						legend: {
							position: 'top',     // 상단에 표시
							align: 'start',      // 왼쪽 정렬 (start: 좌측, center: 가운데, end: 우측)
							labels: { usePointStyle: true }
						}
					},
					scales: {
						y: {
							beginAtZero: true,
							title: { display: true, text: '만원' } // 그대로 만원 단위
						}
					}
				},
				plugins: [ChartDataLabels]

			});



			const barCtx = document.getElementById('salaryRangeChart').getContext('2d');

			new Chart(barCtx, {
				type: 'bar',
				data: {
					labels, // 직급 라벨 (ex. 사원, 주임, 대리, ...)
					datasets: [
						{
							label: '최소 연봉',
							data: minData,
							backgroundColor: 'rgba(248, 115, 171, 0.6)',
						},
						{
							label: '최대 연봉',
							data: maxData,
							backgroundColor: 'rgba(124, 58, 237, 0.6)',
						}
					]
				},
				options: {
					responsive: false,
					plugins: {
						tooltip: {
							callbacks: {
								label(ctx) {
									const val = Math.round(ctx.raw);
									return `${ctx.dataset.label}: ${formatSalary(val)}`;
								}
							}
						},
						legend: {
							position: 'top',
							align: 'start',
							labels: { usePointStyle: true }
						}
					},
					scales: {
						y: {
							beginAtZero: true,
							title: { display: true, text: '만원' } // 단위 그대로 만원
						}
					}
				}
			});

			// 4) (선택) 최신 데이터 기준 업데이트 문구
			const latest = salaries[salaries.length - 1];
			if (latest) {
				document.querySelector('.salary-update').textContent =
					`${latest.createDate.slice(0, 4)}년 ${latest.codeName} 기준`;
			}
		})

		.catch(err => {
			console.error('연봉정보 로드 실패', err);
		});


	function formatKRW(amount) {
		const num = Number(amount);
		console.log("왜", num);
		if (amount == null || isNaN(num)) return '정보 없음';

		const 조 = Math.floor(amount / 1_0000_0000_0000);
		const 억 = Math.floor((amount % 1_0000_0000_0000) / 1_0000_0000);
		const 만 = Math.floor((amount % 1_0000_0000) / 10_000);

		let result = '';
		if (조 > 0) result += `${조.toLocaleString()}조 `;
		if (억 > 0) result += `${억.toLocaleString()}억 `;
		if (조 === 0 && 억 === 0) result += `${만.toLocaleString()}만원`;

		return result.trim();
	}

	function formatDate(dtStr) {
		return dtStr?.slice(0, 10) || '-';
	}


	function formatSalary(salary) {
		salary = Number(salary);
		if (isNaN(salary) || salary === 0) return '면접 후 결정';
		if (salary < 10000) {
			return `${salary.toString()}만원`;
		}
		const eok = Math.floor(salary / 10000);
		const man = salary % 10000;
		return man > 0 ? `${eok}억 ${man.toString()}만원` : `${eok}억원`;
	}



	function renderNotice(notices) {
		const today = new Date();
		today.setHours(0, 0, 0, 0);

		//마감되지 않았고, 종료일이 오늘 이후인 공고만 필터링
		const noFinishNotice = notices.filter(notice => {
			if (notice.recruitFinishYn !== 'N') return false;
			const finishDate = new Date(notice.recruitmentFinishDate.replace(' ', 'T'));
			finishDate.setHours(0, 0, 0, 0);
			return finishDate >= today; // 오늘 이후만 표시
		});

		const tbody = document.getElementById('inProgressTbody');

		if (noFinishNotice.length > 0) {
			tbody.innerHTML = noFinishNotice.map(notice => `
	            <tr class="clickable-row" data-href="/recruit_notice/${notice.recruitmentNo}">
	                <td>${formatDate(notice.recruitmentReceiptStart)} ~ ${formatDate(notice.recruitmentFinishDate)}</td>
	                <td id="recruit-title-main"><strong>${notice.recruitmentTitle}</strong></td>
	                <td>${notice.yearCodeName.trim() || '-'} | ${notice.cityCodeName}</td>
	            </tr>
	        `).join('');
		} else {
			tbody.innerHTML = `
	            <tr>
	                <td colspan="3">진행중인 채용공고가 없습니다.</td>
	            </tr>
	        `;
		}

		tbody.querySelectorAll('.clickable-row').forEach(tr => {
			tr.addEventListener('click', () => {
				window.location.href = tr.dataset.href;
			});
		});
	}


	function renderNoticeTab(notices) {
		const open = notices.filter(n => n.recruitFinishYn === 'N');
		const closed = notices.filter(n => n.recruitFinishYn === 'Y');
		const all = notices;

		const statusTabs = document.querySelector('.status-tabs .tabs');
		statusTabs.querySelector('[data-status="all"]	.count').textContent = all.length;
		statusTabs.querySelector('[data-status="open"]	.count').textContent = open.length;
		statusTabs.querySelector('[data-status="closed"] .count').textContent = closed.length;



		function renderJobFilter(list) {
			const filterBar = document.querySelector('.filter-bar');
			const uniqueJobs = Array.from(new Set(list.map(n => n.jobCodeName))).filter(job => job);

			const primary = uniqueJobs.slice(0, 8);
			const extra = uniqueJobs.slice(8);
			let html = `<button class="filter-btn active" data-filter="all">직무전체</button>`;

			primary.forEach(j => {
				html += `<button class="filter-btn" data-filter="${j}">${j}</button>`;
			});

			if (extra.length) {
				html += `<div class="more-group" style="display:inline-block; position:relative;">
				<button class="filter-btn more">⋯</button>
				<div class="more-dropdown" style="display:none; position:absolute; top:100%; left:0; background:#fff; border:1px solid #ccc; padding:0.5rem;">
					${extra.map(j => `<button class="filter-btn" data-filter="${j}">${j}</button>`).join('')}
				</div>
				</div>`;
			}
			filterBar.innerHTML = html;

			const allBtn = filterBar.querySelector('[data-filter="all"]');
			const jobBtns = filterBar.querySelectorAll('.filter-btn[data-filter]:not(.more)');
			function applyFilter(code) {
				jobBtns.forEach(b => b.classList.toggle('active', b.dataset.filter === code));
				if (code === 'all') {
					renderCards(list);
				} else {
					renderCards(list.filter(n => n.jobCodeName === code));
				}
			}

			allBtn.addEventListener('click', () => applyFilter('all'));
			jobBtns.forEach(btn => btn.addEventListener('click', () => applyFilter(btn.dataset.filter)));

			const moreBtn = filterBar.querySelector('.filter-btn .more');
			const drop = filterBar.querySelector('.more-dropdown');
			if (moreBtn && drop) {
				moreBtn.addEventListener('click', () => drop.style.display = drop.style.display === 'none' ? 'block' : 'none');
				document.addEventListener('click', e => {
					if (!moreBtn.contains(e.target) && !drop.contains(e.target)) drop.style.display = 'none';
				});
			}

			applyFilter('all');
		}

		function renderCards(list) {
			const cardList = document.querySelector('.card-list');
			cardList.innerHTML = list.map(n =>
				`
				    <article class="job-card" data-id="${n.recruitmentNo}">
					<div class="job-card-header">${getDdayLabel(n.recruitmentFinishDate)}</div>
					<h3 class="job-card-title"><strong>${n.recruitmentTitle}</strong></h3>
					<div class="job-card-meta">
						${n.yearCodeName.trim()} ｜ ${n.cityCodeName}
					</div>
					<div class="job-card-tags">
						${n.jobCodeName}${n.totalPositions > 1 ? ` 외 ${n.totalPositions - 1}` : ''}
					</div>
					</article>
				`).join('');


			cardList.querySelectorAll('.job-card').forEach(card => {
				card.addEventListener('click', () => {
					window.location.href = `/recruit_notice/${card.dataset.id}`;
				});
			});
		}

		// 4) 상태탭 클릭
		statusTabs.querySelectorAll('.tab').forEach(tab => {
			tab.addEventListener('click', () => {
				statusTabs.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
				tab.classList.add('active');
				const key = tab.dataset.status;
				if (key === 'all') renderJobFilter(all);
				if (key === 'open') renderJobFilter(open);
				if (key === 'closed') renderJobFilter(closed);
			});
		});

		// 5) 초기: 진행중
		statusTabs.querySelector('[data-status="open"]').click();
	}






	function getDdayLabel(date) {
		if (!date) return '';

		const today = new Date();
		const end = new Date(date.replace(' ', 'T'));
		today.setHours(0, 0, 0, 0);
		end.setHours(0, 0, 0, 0);

		const diffTime = end - today;
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

		if (diffDays > 0) return `D-${diffDays}`;
		if (diffDays === 0) return 'D-DAY';
	}


	//네비 탭 변환 함수 
	const tabs = document.querySelectorAll('.company-nav-item');
	const contents = document.querySelectorAll('.tab-content');

	tabs.forEach(tab => {
		tab.addEventListener('click', function(e) {
			e.preventDefault();
			const key = tab.dataset.tab;
			tabs.forEach(t => t.classList.toggle('active', t === tab));
			contents.forEach(sec => {
				const isTarget = sec.dataset.section === key;
				sec.classList.toggle('hidden', !isTarget);
			});
		});
	});




	function bindingCompanyImage(fileList) {
		const galleryList = document.getElementById('companyGalleryList');
		if (!galleryList || !Array.isArray(fileList)) return;
		galleryList.innerHTML = fileList
			.map((file, idx) => `
	          <li class="company-gallery-list-item thumbnail js-lazy-img">
	            <a href="#company-gallery-viewer" data-category="company" data-index="${idx + 1}" class="js-lazy-img__inner">
	              <img class="attatched" src="${file.filePath}" alt="회사 이미지" onerror="Company.Gallery.fileOnerror(this);">
	              <div class="mask">
	                <div class="mask-header">회사 이미지</div>
	              </div>
	            </a>
	          </li>`
			).join('');
	}
});