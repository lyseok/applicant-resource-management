/**
 * 
 */
document.addEventListener('DOMContentLoaded', async ()=>{
	
		try{
				 const urlParams = new URLSearchParams(window.location.search);
  	 		const companyId = urlParams.get('companyId');
     		console.log("선택된 USER_ID:", companyId);

				const detailSalary = await axios.get(`/ajax/member/company_salary/detail/${companyId}`)
				const companyInfo = detailSalary.data[0];
				const detailSalaryList = detailSalary.data;
				console.log(companyInfo);
				document.getElementById('salaryCompanyName').textContent = companyInfo.comName;
				document.getElementById('salaryAvgGross').textContent = formatSalary(companyInfo.salaryAvgExclExec);
				renderSalaryChartTable(detailSalaryList);

				const similarResp = await axios.get(`/ajax/member/company_salary/similar/${companyInfo.industryType}`);
				console.log(similarResp.data);

				const similarSalary = similarResp.data;
				renderSimilarTable(similarSalary, companyId);
				


		}
		catch{

		}
		

	function renderSalaryChartTable(data){
			const allMins = data.map(d=>Number(d.salaryMin));
			const allMaxs = data.map(d=>Number(d.salaryMax));
			const globalMin = Math.min(...allMins);
			const globalMax = Math.max(...allMaxs);

			const tbody = document.querySelector('#salaryTable tbody')
			tbody.innerHTML = '';

			data.forEach(item => {
				const minVal = Number(item.salaryMin);
				const maxVal = Number(item.salaryMax);
				const midVal = Number(item.avgByRank);

				const startPct = ((minVal - globalMin) / (globalMax - globalMin)) * 100;
				const widthPct = ((maxVal - globalMin) / (globalMax - globalMin)) * 100 - startPct;
				const midPct = ((midVal - globalMin) / (globalMax - globalMin)) * 100;

				const tr = document.createElement('tr');
				tr.innerHTML = `
						<td>${item.codeName}</td>
						<td>${formatSalary(midVal)}</td>
						   <td class="chart-cell">
					 	<div class="chart-labels">
						   <!-- startPct: 구간 시작점 -->
						  <span class="salary-label min" style="left:${startPct}%">
						     최저 ${formatSalary(minVal)}
						   </span>
						  <!-- endPct: 구간 끝점(startPct+widthPct) -->
						   <span class="salary-label max" style="left:${startPct + widthPct}%">
						     최고 ${formatSalary(maxVal)}
						   </span>
						 </div>

						<div class="salary-bar">
							<div class="range-bar-inner" style="left:${startPct}%; width:${widthPct}%"></div>
						
							<i class="bx bxs-user person-icon" style="left:0%"></i>
						</div>
					</td>
    
				`;

				tbody.appendChild(tr);

				 // 애니메이션할 요소들 grab
    const barInner = tr.querySelector('.salary-bar-inner');
    const icon     = tr.querySelector('.person-icon');
    //const minLbl   = tr.querySelector('.salary-label.min');
    //const maxLbl   = tr.querySelector('.salary-label.max');

    // 다음 프레임에 목표값 세팅
    requestAnimationFrame(() => {
      barInner.style.width = `${midPct}%`;
      icon.style.left      = `${midPct}%`;
      //minLbl.style.left    = `${midPct}%`;
      //maxLbl.style.left    = `${midPct}%`;
    });
  });

	}


	function renderSimilarTable(data, companyId){
		const idx = data.findIndex(item => item.USER_ID === companyId);
		if(idx > -1){
			const [sel] = data.splice(idx, 1);
			data.unshift(sel);
		}

	

		const table = document.getElementById('similarTable');
		const thead = table.querySelector('thead');
		const tbody = table.querySelector('tbody');
		thead.innerHTML = '';
		tbody.innerHTML = '';

		const headerRow = document.createElement('tr');
		headerRow.innerHTML = `<th>기업명</th>`;
		data.forEach(com => {
			const isSel = com.USER_ID === companyId;
			headerRow.innerHTML += `
				<th class="${isSel ? 'selected' : ''}">
				
          ${com.COM_NAME}
				</th>`;
		});

		thead.appendChild(headerRow);
	

	  const rows = [
      { label: '직원수',               key: 'COM_MEM',              formatter: v => v?.toLocaleString() + '명'      },
      { label: '평균연봉(임원제외)', key: 'SALARYAVGEXCLEXEC',   formatter: formatSalary    },
      { label: '전체 평균연봉',        key: 'SALARYAVGEXEC',       formatter: formatSalary    }
    ];

		rows.forEach(rowInfo => {
			const tr = document.createElement('tr');
			tr.innerHTML = `<td>${rowInfo.label}</td>`;
			
			data.forEach(item => {
				const isSel = item.USER_ID === companyId;
				const raw = item[rowInfo.key];
				tr.innerHTML += `
					<td class="${isSel ? 'selected salary' : 'salary'}">
						${rowInfo.formatter(raw) || '-'}
					</td>
				`
			})
			tbody.appendChild(tr);
		})

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
})                                        