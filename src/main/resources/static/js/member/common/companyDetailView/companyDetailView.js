document.addEventListener('DOMContentLoaded', () => {

	const userId = new URLSearchParams(window.location.search).get('no');
	
	axios
		.get(`/ajax/member/company_view/${userId}`)
		.then((resp) => {
			const company = resp.data;
			console.log("com정보", company);
			bindingCompanyImage(company.fileList);
			console.log('체크', document.getElementById('head-com-name'));
			console.log('체크', document.getElementById('head-com-indu'));

			document.getElementById('industryType').textContent = company.induName || '-';
			document.getElementById('comMem').textContent = company.comMem + '명' || '-';
			document.getElementById('comType').textContent = company.comType || '-';
			document.getElementById('comCreateYear').textContent = company.comCreateYear || '-';
			document.getElementById('comCapital').textContent = formatSalary(company.comCapital) || '-';
			document.getElementById('ceoName').textContent = company.ceoName || '-';
			document.getElementById('comMainBiz').textContent = company.comMainBiz || '-';
			document.getElementById('insuranceYn').textContent = company.insuranceYn === 'Y'
				? '국민연금, 건강보험, 고용보험, 산재보험'
				: '-';
			document.getElementById('comUrl').textContent = company.comUrl || '-';
			document.getElementById('com_url').textContent = company.comName ||'-';
			document.getElementById('comInfo').textContent = company.comInfo || '-';
			document.getElementById('head-com-name').textContent = company.comName;
			document.getElementById('head-com-indu').textContent = company.induName || '-';
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
			
			
			
			const homeLink = document.querySelector('.button-home');
			if (homeLink) {
			    homeLink.setAttribute('href', company.comUrl || 'javascript:void(0)');
			    homeLink.setAttribute('target', '_blank'); // 새 탭 열기
			}
			
			similarSalaryRank(company.industryType);
			

		})
		.catch((err) => {
			console.error('회사정보 로드 실패', err);
		});
		
		const favBtn = document.querySelector('.button-dibs');
		 
		   if (favBtn) {
		       // 초기 상태 확인
		       axios.get(`/ajax/member/scrabCompany/${userId}`)
		           .then(resp => {
		               if (resp.data > 0) favBtn.classList.add('on'); // 스크랩 되어있으면 하트 채움
		           });

		       // 클릭 시 토글
		       favBtn.addEventListener('click', async () => {
		           try {
		               if (favBtn.classList.contains('on')) {
		                   await axios.delete(`/ajax/member/scrabCompany/${userId}`);
		                   favBtn.classList.remove('on');
		               } else {
		                   await axios.post(`/ajax/member/scrabCompany/${userId}`);
		                   favBtn.classList.add('on');
		               }
		           } catch (err) {
		               console.error('관심기업 처리 실패', err);
		               alert('처리 중 오류가 발생했습니다.');
		           }
		       });
		   }

	axios.get(`/ajax/member/company_view/sales/${userId}`).then((resp) => {
		const sales = resp.data;
		
		console.log(sales.map(s => s.comSalesAmount));
		console.log(sales.map(s => Number(s.comSalesAmount)));
		const labels = sales.map((s) => s.comSalesYear);
		const data = sales.map((s) => Number(s.comSalesAmount) / 100_000_000); // 억 원
		const avgData = sales.map((s) => Number(s.avgSalesAmount) / 100_000_000); // 억 원



		const latest = sales[sales.length - 1];
			if (latest) {
				const latestAmount = Number(latest.comSalesAmount);
				document.getElementById('comSales').textContent = formatSalary(latestAmount) + '(전년도 기준)';
			}

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
							label: '기업 평균 매출액',
							data: avgData,
							borderColor: 'rgba(200, 200, 200, 0.6)',
							borderDash: [5, 5],
							fill: false,
							pointRadius: 0,
							tension: 0.1,
							datalabels: { display: false },
						},
					],
				},
				 options: {
				        responsive: false,
				        layout: { 
				            padding: { left: 30, right: 80, bottom: 80 } // 왼쪽 패딩 확장
				        },
				        plugins: {
				            datalabels: {
				                display: true,
								align: 'top',      // 포인트 위쪽으로
								anchor: 'end',     // 데이터 포인트 끝 기준
								offset: 4, 
				                font: { size: 10 },
				                formatter: (value) => formatSalary(value * 100_000_000),
				            },
				        },
						 scales: {
						            y: {
						                title: { display: true, text: '금액', color: '#666', font: { size: 14 } },
						                beginAtZero: true,
						                ticks: {
						                    padding: 20,
						                    callback: function(value) {
						                        return formatAxisValue(value * 100000000); 
						                        // 실제 값은 억 단위라 다시 환산
						                    }
						                }
						            }
						        }
						    },
						    plugins: [ChartDataLabels],
						});
			if (latest) {
				document.getElementById('salesUpdate').textContent = `${latest.comSalesYear}.12 기준`;
				document.getElementById('totalSalesAmount').textContent = formatSalary(Number(latest.comSalesAmount));

				const growth = Number(latest.growthRatePercent ?? 0);
				const growthColor = growth >= 0 ? 'blue' : 'red';
				const growthArrow = growth >= 0 ? '▲' : '▼';
				document.getElementById('salesYoY').innerHTML = `<span style="color:${growthColor}">${Math.abs(growth).toFixed(0)}% ${growthArrow}</span>`;
			}
		});
		axios.get(`/ajax/member/company_view/profit/${userId}`).then((resp) => {
			const profit = resp.data;
			const labels = profit.map((p) => p.comProfitYear);
			const data = profit.map((p) => Number(p.comOperatingProfit) / 100_000_000); // 억 원
			const avgData = profit.map((p) => Number(p.avgOperatingProfit) / 100_000_000); // 억 원

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
			                label: '기업 평균 영업이익',
			                data: avgData,
			                borderColor: 'rgba(200, 200, 200, 0.6)',
			                borderDash: [5, 5],
			                fill: false,
			                pointRadius: 0,
			                tension: 0.1,
			                datalabels: { display: false },
			            },
			        ],
			    },
			    options: {
			        responsive: false,
			        layout: { padding: { left: 30, right: 50, bottom: 80 } }, // 왼쪽 여백 확장
			        plugins: {
			            datalabels: {
			                display: true,
							align: 'top',      // 포인트 위쪽으로
							anchor: 'end',     // 데이터 포인트 끝 기준
							offset: 4,          // 포인트와 라벨 간격        // 포인트와 간격
			                font: { size: 10 },
			                formatter: (value) => formatSalary(value * 100_000_000),
			            },
			            tooltip: {
			                callbacks: {
			                    label: function(ctx) {
			                        const raw = ctx.raw * 100_000_000;
			                        if (ctx.dataset.label.includes('평균')) {
			                            return '기업 평균: ' + formatSalary(raw);
			                        }
			                        const growth = Number(profit[ctx.dataIndex].growthRatePercent ?? 0);
			                        return `영업 이익: ${formatSalary(raw)} (작년 대비 ${growth >= 0 ? '+' : ''}${growth.toFixed(2)}%)`;
			                    },
			                },
			            },
			        },
					scales: {
					            y: {
					                title: { display: true, text: '금액', color: '#666', font: { size: 14 } },
					                beginAtZero: true,
					                ticks: {
					                    padding: 20,
					                    callback: function(value) {
					                        return formatAxisValue(value * 100000000);
					                    }
					                }
					            }
					        }
					    },
					    plugins: [ChartDataLabels],
			});

			if (latest) {
				document.getElementById('profitUpdate').textContent = `${latest.comProfitYear}.12 기준`;
				document.getElementById('totalOperatingProfit').textContent = formatSalary(Number(latest.comOperatingProfit));

				const growth = Number(latest.growthRatePercent ?? 0);
				const growthColor = growth >= 0 ? 'blue' : 'red';
				const growthArrow = growth >= 0 ? '▲' : '▼';
				document.getElementById('profitYoY').innerHTML = `<span style="color:${growthColor}">${Math.abs(growth).toFixed(0)}% ${growthArrow}</span>`;
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


		
	axios.get(`/ajax/member/company_view/pass_introduction/${userId}`)

	function formatDate(dtStr) {
		return dtStr?.slice(0, 10) || '-';
	}


	function formatSalary(salary) {
	    salary = Number(salary);
	    if (isNaN(salary) || salary === 0) return '-';

	    const manUnit = 10000;           // 1억 = 1만만원
	    const eokUnit = 10000;           // 억
	    const joUnit = 10000 * 10000;    // 조

	    let resultParts = [];

	    // 조 단위
	    if (salary >= joUnit) {
	        const jo = Math.floor(salary / joUnit);
	        resultParts.push(`${jo.toLocaleString()}조`);
	        salary = salary % joUnit;
	    }

	    // 억 단위
	    if (salary >= eokUnit) {
	        const eok = Math.floor(salary / eokUnit);
	        resultParts.push(`${eok.toLocaleString()}억`);
	        salary = salary % eokUnit;
	    }

	    // 천만원 단위 (0.1억)
	    if (salary > 0) {
	        const cheon = Math.floor(salary / 1000); // 1천만원 단위
	        if (cheon > 0) {
	            resultParts.push(`${cheon * 1000}만원`);
	        }
	    }

	    return resultParts.join(' ');
	}
	
	
	function formatAxisValue(value) {
	    if (value >= 10000 * 10000) { // 조 단위
	        return (value / (10000 * 10000)).toFixed(1) + '조';
	    } else if (value >= 10000) {  // 억 단위
	        return (value / 10000).toFixed(0) + '억';
	    } else { // 억 미만 → 만원 단위
	        return value.toFixed(0) + '만원';
	    }
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

		const ul = document.getElementById('inProgressWrap');

		if (noFinishNotice.length > 0) {
	    ul.innerHTML = noFinishNotice.map(notice => `
	    	<li class="p-4 border-bottom d-flex justify-content-between align-items-center recruit_list gap-5">
	        <div class="d-flex flex-fill align-items-start">
						<div class="d-flex align-items-center viewat_box">
							<h6 class="recruit_comName text-muted">${notice.comName ?? ''}</h6>
						</div>
						<div class="recruit_tit"> 
	          	<h5 class="d-block fs16 fw-bold m-0">${notice.recruitmentTitle}</h5>
	          	<span class="text-muted fs-14">${notice.jobCodeName}</span>
						</div>
	          <div class="recruit_info">
	          	<div class="d-flex align-items-center">
	          		<span class="material-symbols-outlined">distance</span>
	          		<span class="">${notice.cityCodeName}</span>
	        		</div>
							<div class="d-flex align-items-center">
								<span class="material-symbols-outlined">money_bag</span>
								<span class="num_line">${notice.recruitmentSalary}만원</span>
							</div>
							<div class="d-flex align-items-center">
								<span class="material-symbols-outlined">business_center</span>
								<span class="num_line">${notice.yearCodeName.trim() || '-'}</span>
							</div>      	
	          </div>
	        </div>
	        <div class="d-flex flex-column align-items-center gap-2 ">
	        <a class="btn btn_violet review-btn w140 justify-content-center fw-light fs-14" href="/recruit_notice/${notice.recruitmentNo}">공고보기</a>
						<div class="text-end w-100 ">
							<span class="fs-12 text-muted">${formatDate(notice.recruitmentFinishDate)}</span>
						</div>
	        </div>
	      </li>
	    `).join('');
	    
		} else {
			ul.innerHTML = `
        <li>
            진행중인 채용공고가 없습니다.
        </li>
    `;
		}
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
		statusTabs.querySelectorAll('.tab-btn').forEach(tab => {
			tab.addEventListener('click', () => {
				statusTabs.querySelectorAll('.tab-btn').forEach(t => t.classList.remove('active'));
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

	
	
	
	
	function similarSalaryRank(induNo){
		axios.get(`/ajax/member/company_salary/rank/${userId}/${induNo}`)
			 .then(resp => {
				const rank = resp.data;
				console.log(rank);
				
				document.getElementById('salaryCompanyIndu').textContent = rank.INDU_NAME + ' 분야';
				document.getElementById('salaryGrade').textContent = rank.SALARY_RANK + '위';
			 })
	}
	
	
	
	
	
	axios.get(`/ajax/member/company_view/pass_introduction/${userId}`)
		 .then(resp => {
			const essays = resp.data;
			console.log("자소서 체킁", essays);
			renderEssayList(essays);
			setUpEssayFilters(essays);
			document.getElementById('essayCount').textContent = essays.length;
		 }).catch(err => console.error('합격 자소서 못불러왔당', err));

	function renderEssayList(essays){
		const listContainer = document.getElementById('essayCardList');
		
		if (!essays || essays.length === 0) {
		       listContainer.innerHTML = `
		           <div style="padding: 20px; text-align: center; color: #666;">
		               등록된 자소서가 없습니다.
		           </div>
		       `;
		       return;
		   }
		
		listContainer.innerHTML = essays.map((e, idx) => `
			<div class="essay-card clickable" data-index="${idx}">
				<div class="essay-card-title">
					${e.recruitmentTitle}
					<span class="fs-14 opacity-75 fw-500">( ${e.education.highestEducationCodeName || '-'} / ${e.education.departmentCode || '-'} )</span>
				</div>
				<div class="essay-views">
					<span><b>조회수</b> ${Math.floor(Math.random() * 5000) + 1000}</span>
					<span><b>작성일자</b> ${formatDate(e.introduction.introductionCreateDate)}</span>					
				</div>
        	</div>
    	`).join('');


		listContainer.querySelectorAll('.essay-card').forEach(card => {
			card.addEventListener('click', () => {
				const idx = card.dataset.index;
				renderEssayDetail(essays[idx]);
			});
		});
	}

	function renderEssayDetail(essay){
		const detail = document.getElementById('essay-detail');
		const list = document.getElementById('essay-list');
		list.style.display = 'none';
		detail.style.display = 'block';

		detail.innerHTML = `
			<button id="back" class="btn btn_violet_line btn-sm mb-4">← 목록으로</button>
			<div class="essay-detail-header">
				<div class="d-flex gap-3 align-items-end">
					<h2 class="essay-detail-title">${essay.recruitmentTitle}</h2>
					<div class="opacity-75 fs-14">
						( 
						<span>${essay.education.highestEducationCodeName || '-'}</span>
						<span>${essay.education.departmentCode || '-'}</span>
						 )
					</div>
				</div>
				<div class="essay-detail-subinfo">					
					<div class="essay-detail-meta">
						<span class="essay-date">${formatDate(essay.introduction.introductionCreateDate)}</span>
					</div>
				</div>
			</div>
			<h2 class="fs-18 fw-bold mb-3">자소서 항목</h2>
			<div class="essay-questions">
				${essay.introduction.introductionQuestionList.map((q,i) => `<p><b class="q-number">Q${i+1}.</b> ${q.question}</p>`).join('')}
			</div>
			
			<h2 class="fs-18 fw-bold mb-3">합격 자소서</h2>
			<div class="accordion" id="essayAccordion">
				${essay.introduction.introductionQuestionList.map((q,i) => `
				<div class="accordion-item">
					<div class="accordion-header" data-idx="${i}">
						<div class="d-flex">
							<span class="q-number fw-bold">Q${i+1}.</span> <p class="kepp-all">${q.question}</p>
						</div>
						<span class="arrow"></span>
					</div>
					<div class="accordion-body keep-all lh1-8" style="display:none;">${q.content}</div>
				</div>
				`).join('')}
			</div>
		`;

		 detail.querySelectorAll('.accordion-header').forEach(header => {
			header.addEventListener('click', () => {
				const body = header.nextElementSibling;
				const isOpen = body.style.display === 'block';
				body.style.display = isOpen ? 'none' : 'block';
				header.classList.toggle('active', !isOpen);
			});
		});

		document.getElementById('back')
				.addEventListener('click', closeEssayDetail);
	}

	function closeEssayDetail(){
		document.getElementById('essay-detail').style.display = 'none';
		document.getElementById('essay-list').style.display = 'block';
	}

	function setUpEssayFilters(essays){
		const filterContainer = document.getElementById('essayFilters');
		const uniqueJobs = Array.from(new Set(essays.map(e => e.jobCodeName)))

		filterContainer.innerHTML = `<div class="essay-filter-btn active" data-filter="all">직무전체</div>` +
        uniqueJobs.map(j => `<div class="essay-filter-btn" data-filter="${j}">${j}</div>`).join('');

		filterContainer.querySelectorAll('.essay-filter-btn').forEach(btn => {
			btn.addEventListener('click', () => {
				filterContainer.querySelectorAll('.essay-filter-btn').forEach(b => b.classList.remove('active'));
				btn.classList.add('active');
				const filter = btn.dataset.filter;
				const filtered = filter === 'all' ? essays : essays.filter(e => e.jobCodeName === filter);
            	renderEssayList(filtered);
			})
		})
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
		if(fileList != null){
			const galleryBox = document.querySelector(".corporate-gallery");
			galleryBox.classList.add("d-none");
		}
		galleryList.innerHTML = fileList
			.map((file, idx) => `
	          <li class="company-gallery-list-item thumbnail js-lazy-img">
	            <a href="#company-gallery-viewer" data-category="company" data-index="${idx + 1}" class="js-lazy-img__inner">
	              <img class="attatched" src="${file.filePath}" alt="회사 이미지" onerror="Company.Gallery.fileOnerror(this);">
	            </a>
	          </li>`
			).join('');
	}
	
	
	

});