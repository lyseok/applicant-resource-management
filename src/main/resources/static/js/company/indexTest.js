
(async () => {
	const resp = await axios.get('/ajax/company/statistics/recruitment_status');
	const data = resp.data;

	// Chart.js 기본 설정
	Chart.defaults.font.family =
		'-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif';
	Chart.defaults.color = '#6c757d';
	const color1 = '#7F55B1';
	const color2 = '#8F87F1';
	const color3 = '#C68EFD';
	const color4 = '#E9A5F1';
	const color5 = '#FED2E2';
	const color6 = '#F4F8D3';
	
	document.getElementById('comName').textContent = data.company.comName;
	document.getElementById('comImage').src = data.company.comLogo;

	document.querySelectorAll('.stat-number')[0].textContent = data.recruitmentStatus.TOTAL_APPLICANTS.toLocaleString();
	document.querySelectorAll('.stat-number')[1].textContent = data.recruitmentStatus.DOCUMENT_PASS_COUNT.toLocaleString();
	document.querySelectorAll('.stat-number')[2].textContent = data.recruitmentStatus.FINAL_PASS_COUNT.toLocaleString();
	document.querySelectorAll('.stat-number')[3].textContent = data.recruitmentStatus.ACTIVE_NOTICE_COUNT.toLocaleString();


	document.querySelectorAll('.project-number')[0].textContent = data.talentPool.TOTAL_MEMBERS.toLocaleString();
	document.querySelectorAll('.project-number')[1].textContent = data.talentPool.RECENT_MEMBERS.toLocaleString();
	document.querySelectorAll('.project-number')[2].textContent = data.talentPool.ACTIVE_PROFILES.toLocaleString();
	document.querySelectorAll('.project-number')[3].textContent = data.talentPool.SCRABBED_MEMBERS.toLocaleString();

	
	
	
	// 신규 가입자 추이 차트
	const newMembersMonthLabels = data.newMembers.map(n => n.NEW_MEMBERS);
	const newMembersValues = data.newMembers.map(n => n.NEW_MEMBERS_NUM);
	const newMembersCtx = document.getElementById('newMembersChart');
	if (newMembersCtx) {
		new Chart(newMembersCtx.getContext('2d'), {
			type: 'line',
			data: {
				labels: newMembersMonthLabels,
				datasets: [{
					label: '신규 가입자',
					data: newMembersValues,
					borderColor: '#9d66ff',
					backgroundColor: 'rgba(191, 0, 255, 0.1)',
					borderWidth: 3,
					fill: true,
					tension: 0.4,
				}],
			},
			options: {
				responsive: true,
				maintainAspectRatio: false,
				plugins: { legend: { display: false } },
				scales: {
					y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
					x: { grid: { display: false } }
				}
			}
		});
	}

	const ageLabels = data.age.map(a => a.AGE_GROUP);
	const ageValues = data.age.map(a => a.CNT);
	// 연령대별 분포 도넛 차트
	const ageCtx = document.getElementById('ageDistributionChart');
	if (ageCtx) {
		new Chart(ageCtx.getContext('2d'), {
			type: 'doughnut',
			data: {
				labels: ageLabels,
				datasets: [{
					data: ageValues,
					backgroundColor: [color1, color2, color3, color4],
					borderWidth: 0
				}]
			},
			options: {
				responsive: true,
				maintainAspectRatio: false,
				plugins: {
					legend: {
						position: 'bottom',
						labels: {
							padding: 20,
							usePointStyle: true
						}
					}
				}
			}
		});
	}
	const eduLabels = data.edu.map(e => e.EDU_GROUP);
	const eduValues = data.edu.map(e => e.CNT);
	// 학력별 분포
	const educationCtx = document.getElementById('educationChart');
	if (educationCtx) {
		new Chart(educationCtx.getContext('2d'), {
			type: 'doughnut',
			data: {
				labels: eduLabels,
				datasets: [{
					data: eduValues,
					backgroundColor: [color1, color2, color3, color4],
					borderWidth: 0
				}]
			},
			options: {
				responsive: true,
				maintainAspectRatio: false,
				plugins: {
					legend: {
						position: 'bottom',
						labels: {
							padding: 20,
							usePointStyle: true
						}
					}
				}
			}
		});
	}

	const careerLabels = data.career.map(c => c.CAREER_GROUP);
	const careerValues = data.career.map(c => c.USER_COUNT);
	new Chart(document.getElementById('experienceChart').getContext('2d'), {
		type: 'doughnut',
		data: { labels: careerLabels, 
			datasets: [{ 
				data: careerValues, 
				backgroundColor: [color1, color2, color3, color4], 
				borderWidth: 0 }] },
			options: { 
				responsive: true, 
				maintainAspectRatio: false,
				 plugins: { 
					legend: { 
						position: 'bottom', 
						labels: { 
							padding: 20, 
							usePointStyle: true 
						}
					 } 
				 } 
			 }
		});



	// 성별 분포
	const genderLabels = data.gender.map(g => g.GENDER);
	const genderValues = data.gender.map(g => g.GENDERCOUNT);
	const genderCtx = document.getElementById('genderChart');
	if (genderCtx) {
		new Chart(genderCtx.getContext('2d'), {
			type: 'doughnut',
			data: { labels: genderLabels, datasets: [{ data: genderValues, backgroundColor: [color1, color2], borderWidth: 0 }] },
			options: { 
				responsive: true, 
				maintainAspectRatio: false,
				 plugins: { 
					legend: { 
						position: 'bottom', 
						labels: { 
							padding: 40, 
							usePointStyle: true 
						}
					 } 
				 } 
			 }
		});
	}

	// 보유 기술 스택
	const skillLabels = data.skills.map(s => s.SKILL_NAME);
	const skillValues = data.skills.map(s => s.MEMBER_COUNT);
	const skillsCtx = document.getElementById('skillsChart');
	if (skillsCtx) {
		new Chart(skillsCtx.getContext('2d'), {
			type: 'bar',
			data: {
				labels: skillLabels,
				datasets: [{ label: '보유자 수', data: skillValues, backgroundColor: color1, borderRadius: 4 }]
			},
			options: {
				responsive: true,
				maintainAspectRatio: false,
				plugins: { legend: { display: false } },
				scales: {
					y: { beginAtZero: true, grid: { color: '#f1f3f4' } },
					x: { grid: { display: false } }
				}
			}
		});
	}

	const rankingList = document.querySelector('.ranking-list');
	rankingList.innerHTML = '';
	data.topNotice.forEach((notice, idx) => {
		rankingList.innerHTML += `
					<div class="ranking-item">
						<span class="rank">${idx + 1}</span>
						<span class="job-title">${notice.RECRUITMENT_TITLE}</span>
						<span class="applicants">${notice.APPLICANT_COUNT}명</span>
					</div>
				`;
	});


	const monthLabels = data.passRate.map(p => p.MONTH);
	
	

	const passRates = data.passRate.map(p => Number(p.PASS_RATE) ?? 0);
	const applicantCountsFixed = data.passRate.map(p => {
	    const count = Number(p.APPLICANT_COUNT) ?? 0;
	    return count; // 0도 그대로 표시
	});

	
	const applicantTrendCtx = document.getElementById('applicantTrendChart');
	new Chart(applicantTrendCtx.getContext('2d'), {
	    type: 'bar',
	    data: {
	        labels: monthLabels,
	        datasets: [
	            { 
	                label: '지원자 수', 
	                data: applicantCountsFixed, 
	                backgroundColor: 'rgba(127, 85, 177, 0.6)', 
	                yAxisID: 'y' 
	            },
	            { 
	                label: '합격률 (%)', 
	                data: passRates, 
	                type: 'line', 
	                borderColor: color2, 
	                borderWidth: 3, 
	                pointBackgroundColor: color2,
	                spanGaps: true,    // null 값 건너뛰기
	                yAxisID: 'y1' 
	            }
	        ]
	    },
	    options: {
	        responsive: true,
	        maintainAspectRatio: false,
	        scales: {
	            y: { beginAtZero: true, position: 'left' },
	            y1: { 
					beginAtZero: true,   // 0도 보이게 
					position: 'right', 
					grid: { drawOnChartArea: false } 
	            }
	        },
	        plugins: {
	            tooltip: {
	                callbacks: {
	                    label: function(context) {
	                        const value = context.parsed.y;
							if (value === 0) return '지원자 없음';
							if (context.dataset.label.includes('합격률')) {
								return `${context.dataset.label}: ${value}%`;
							}
							return `${context.dataset.label}: ${value}명`;
	                    }
	                }
	            }
	        }
	    }
	});



	// 달력 클래스
	class Calendar {
		constructor() {
			this.currentDate = new Date();
			this.today = new Date();
			this.init();
		}
		init() { this.render(); this.bindEvents(); }
		bindEvents() {
			document.getElementById('prevMonth').addEventListener('click', () => {
				this.currentDate.setMonth(this.currentDate.getMonth() - 1);
				this.render();
			});
			document.getElementById('nextMonth').addEventListener('click', () => {
				this.currentDate.setMonth(this.currentDate.getMonth() + 1);
				this.render();
			});
			document.getElementById('todayBtn').addEventListener('click', () => {
				this.currentDate = new Date();
				this.render();
			});
		}
		render() {
			const year = this.currentDate.getFullYear();
			const month = this.currentDate.getMonth();
			document.getElementById('calendarTitle').textContent = `${year}년 ${month + 1}월`;
			const grid = document.getElementById('calendarGrid');
			grid.innerHTML = '';
			const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
			weekdays.forEach((day) => {
				const cell = document.createElement('div');
				cell.className = 'calendar-header-cell';
				cell.textContent = day;
				grid.appendChild(cell);
			});
			const firstDay = new Date(year, month, 1);
			const startDate = new Date(firstDay);
			startDate.setDate(startDate.getDate() - firstDay.getDay());
			for (let i = 0; i < 42; i++) {
				const cellDate = new Date(startDate);
				cellDate.setDate(startDate.getDate() + i);
				const cell = document.createElement('div');
				cell.className = 'calendar-cell';
				if (cellDate.getMonth() !== month) cell.classList.add('other-month');
				if (this.isSameDay(cellDate, this.today)) cell.classList.add('today');
				const dateDiv = document.createElement('div');
				dateDiv.className = 'calendar-date';
				dateDiv.textContent = cellDate.getDate();
				cell.appendChild(dateDiv);
				grid.appendChild(cell);
			}
		}
		isSameDay(date1, date2) {
			return date1.getDate() === date2.getDate() &&
				date1.getMonth() === date2.getMonth() &&
				date1.getFullYear() === date2.getFullYear();
		}
	}
	new Calendar();

	// 애니메이션 효과
	const animateNumbers = () => {
		const numbers = document.querySelectorAll('.stat-number, .project-number');
		numbers.forEach(num => {
			const target = parseInt(num.textContent.replace(/,/g, ''));
			let current = 0;
			const increment = target / 50;
			const timer = setInterval(() => {
				current += increment;
				if (current >= target) { current = target; clearInterval(timer); }
				num.textContent = Math.floor(current).toLocaleString();
			}, 20);
		});
	};
	setTimeout(animateNumbers, 500);

	// 차트 컨트롤 버튼 이벤트
	document.querySelectorAll('.chart-controls .btn').forEach((btn) => {
		btn.addEventListener('click', function() {
			this.parentElement.querySelectorAll('.btn').forEach((b) => b.classList.remove('active'));
			this.classList.add('active');
		});
	});
	// 뷰 컨트롤 버튼 이벤트
	document.querySelectorAll('.view-controls .btn').forEach((btn) => {
		btn.addEventListener('click', function() {
			this.parentElement.querySelectorAll('.btn').forEach((b) => { b.classList.remove('active', 'btn-dark'); b.classList.add('btn-outline-secondary'); });
			this.classList.remove('btn-outline-secondary'); this.classList.add('active', 'btn-dark');
		});
	});

})();

















