console.log('kakao 객체:', window.kakao);

document.addEventListener('DOMContentLoaded', function() {

	axios
		.get('/ajax/member/company_view/testCompany')
		.then((resp) => {
			const company = resp.data;
			console.log(company);

			document.getElementById('industryType').textContent =
				company.industryType || '-';
			document.getElementById('comMem').textContent = company.comMem + '명';
			document.getElementById('comType').textContent = company.comType || '-';
			document.getElementById('comCreateYear').textContent =
				company.comCreateYear || '-';
			document.getElementById('comCapital').textContent =
				formatKRW(company.comCapital) || '-';
			document.getElementById('ceoName').textContent = company.ceoName || '-';
			document.getElementById('comMainBiz').textContent =
				company.comMainBiz || '-';
			document.getElementById('insuranceYn').textContent =
				company.insuranceYn === 'Y'
					? '국민연금, 건강보험, 고용보험, 산재보험'
					: '-';
			document.getElementById('comUrl').textContent = company.comUrl || '-';

			document.getElementById('comInfo').textContent = company.comInfo || '-';
			const addr = company.comAddr || '';
			document.getElementById('companyAddr').textContent = addr;

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

	axios.get('/ajax/member/company_view/sales/testCompany').then((resp) => {
		const sales = resp.data;
		const labels = sales.map((s) => s.comSalesYear);
		const data = sales.map((s) => s.comSalesAmount / 100_000_000); // 억 원
		const avgData = sales.map((s) => s.avgSalesAmount / 100_000_000); // 억 원

		const latest = sales[sales.length - 1];

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

	axios.get('/ajax/member/company_view/profit/testCompany').then((resp) => {
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




	axios.get('/ajax/member/company_view/notice/testCompany')
		.then(resp => {
			const notices = resp.data;
			console.log(notices);
			renderNotice(notices);

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



	function formatKRW(amount) {
		if (amount == null || isNaN(amount)) return '정보 없음';

		const 조 = Math.floor(amount / 1_0000_0000_0000);
		const 억 = Math.floor((amount % 1_0000_0000_0000) / 1_0000_0000);
		const 만 = Math.floor((amount % 1_0000_0000) / 10_000);

		let result = '';
		if (조 > 0) result += `${조.toLocaleString()}조 `;
		if (억 > 0) result += `${억.toLocaleString()}억 `;
		if (조 === 0 && 억 === 0) result += `${만.toLocaleString()}만원`;

		return result.trim();
	}




	function renderNotice(notices) {
		const noFinishNotice = notices.filter(notice => notice.recruitFinishYn === 'N');
		const tbody = document.getElementById('inProgressTbody');

		if (noFinishNotice.length > 0) {
			// 각 항목 tr 생성
			tbody.innerHTML = noFinishNotice.map(notice => `
        <tr class="clickable-row" data-href="/recruit_notice/${notice.recruitmentNo}">
          <td>${formatDate(notice.recruitmentReceiptStart)} ~ ${formatDate(notice.recruitmentFinishDate)}</td>
          <td><strong>${notice.recruitmentTitle}</strong></td>
          <td>${notice.yearCodeName.trim() || '-'} | ${notice.cityCodeName}</td>
        </tr>
      `).join('');
		} else {
			// 데이터 없을 때 메시지
			tbody.innerHTML = `
        <tr>
          <td colspan="3">진행중인 채용공고가 없습니다.</td>
        </tr>
      `;
		}

		// ← 여기서 동적으로 생성된 tr에 리스너를 붙입니다
		tbody.querySelectorAll('.clickable-row').forEach(tr => {
			tr.addEventListener('click', () => {
				window.location.href = tr.dataset.href;
			});
		});
	}





	function formatDate(dtStr) {
		return dtStr?.slice(0, 10) || '-';
	}



});
