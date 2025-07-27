let comboChart;

document.addEventListener("DOMContentLoaded", function() {
	// 1. 좌측 도넛: 판매된 상품 비율
	 new Chart(document.getElementById('productChart'), {
		type: 'doughnut',
		data: {
			labels: productLabels,
			datasets: [{
				data: productData,
				backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40']
			}]
		},
		options: {
			responsive: true,
			plugins: { title: { display: true, text: '판매된 상품 비율' } }
		}
	});

	// 2. 우측 도넛: 구독상품 & 구독자 비율
	 new Chart(document.getElementById('subscriptionChart'), {
		type: 'doughnut',
		data: {
			labels: subscriptionLabels,
			datasets: [{
				data: subscriptionData,
				backgroundColor: ['#36A2EB', '#4BC0C0', '#FF6384']
			}]
		},
		options: {
			responsive: true,
			plugins: { title: { display: true, text: '구독자 상태 비율' } }
		}
	});

	// 3. 하단 Combo Chart (전년도 vs 이번년도 매출)
	comboChart = new Chart(document.getElementById('comboChart'), {
		type: 'bar',
		data: {
			labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			datasets: [
				{
					label: '전년도 매출',
					data: lastYearSales,
					borderColor: 'rgb(255, 99, 132)',
					backgroundColor: 'rgba(255, 99, 132, 0.5)',
					type: 'bar',
					yAxisID: 'y'
				},
				{
					label: '이번년도 매출',
					data: thisYearSales,
					borderColor: 'rgb(54, 162, 235)',
					backgroundColor: 'rgba(54, 162, 235, 0.5)',
					type: 'bar',
					yAxisID: 'y'
				},
				{
					label: 'Dataset3 (추후 정의)',
					data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
					borderColor: 'rgb(255, 205, 86)',
					backgroundColor: 'rgba(255, 205, 86, 0.5)',
					type: 'line',
					yAxisID: 'y'
				}
			]
		},
		options: {
			responsive: true,
			interaction: { mode: 'index', intersect: false },
			stacked: false,
			plugins: {
				title: { display: true, text: '전년도 vs 이번년도 매출' }
			},
			scales: {
				y: { type: 'linear', display: true, position: 'left' },
				x: { title: { display: true, text: '월' } }
			}
		}
	});
 
	document.getElementById('yearSelect').addEventListener('change', function() {
		const selectedYear = this.value;
		fetch(`/admin/payment/sales-data?year=${selectedYear}`)
			.then(response => response.json())
			.then(data => {
				// 차트 데이터 갱신
				comboChart.data.datasets[0].data = data.lastYearSales;
				comboChart.data.datasets[1].data = data.thisYearSales;
				comboChart.update();
			})
			.catch(error => console.error("데이터 로드 오류 :" , error));
	});


});


