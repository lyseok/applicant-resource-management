/**
 * 
 */
document.addEventListener('DOMContentLoaded', async ()=>{
	
		try{
		      const urlParams = new URLSearchParams(window.location.search);
  	 		  const companyId = urlParams.get('companyId');
     		   console.log("선택된 USER_ID:", companyId);

				const detailSalary = await axios.get(`/ajax/member/company_salary/detail/${companyId}`)
				console.log(detailSalary.data);
				const companyInfo = detailSalary.data[0];
				const detailSalaryList = detailSalary.data;
				renderRankChart(detailSalaryList); // 직급별 연봉 차트
				console.log(companyInfo);
				document.getElementById('salaryCompanyName').textContent = companyInfo.comName;
				document.getElementById('salaryAvgGross').textContent = formatSalary(companyInfo.salaryAvgExclExec);
			

				const similarResp = await axios.get(`/ajax/member/company_salary/similar/${companyInfo.industryType}`);
				console.log(similarResp.data);

				const similarSalary = similarResp.data;
				renderSimilarTable(similarSalary, companyId);
				


		}
		catch{

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



function renderRankChart(data) {
  const container = document.getElementById('salaryRankChart');
  container.innerHTML = '';

  // 1. 임원 제거
  const filteredData = data.filter(item =>
    !['이사','상무','전무','부사장','사장','임원'].includes(item.codeName)
  );

  // 2. 전체 스케일: 0 ~ 최고 연봉
  const globalMin = 0;
  const globalMax = Math.max(...filteredData.map(d => Number(d.salaryMax)));

  // 눈금선 렌더링
  renderScale(globalMin, globalMax);

  filteredData.forEach(item => {
    const min = Number(item.salaryMin);
    const max = Number(item.salaryMax);
    const avg = Number(item.avgByRank);

    const row = document.createElement('div');
    row.className = 'rank-row';

    const title = document.createElement('div');
    title.className = 'rank-title';
    title.textContent = item.codeName;

    // 막대 (고정 길이)
    const bar = document.createElement('div');
    bar.className = 'rank-bar';

    // 아이콘
    const icon = document.createElement('div');
    icon.className = 'rank-icon';
    icon.innerHTML = '<i class="bx bxs-user"></i>';
    icon.style.left = '0%';

    bar.appendChild(icon);
    row.appendChild(title);
    row.appendChild(bar);
    container.appendChild(row);

	const roundedMax = Math.ceil(globalMax / 1000) * 1000; // 눈금과 동일하게

	// 아이콘 이동 시 위치 계산도 roundedMax 기준
	setTimeout(() => {
		const percent = ((avg - globalMin) / (roundedMax - globalMin)) * 100;
		 icon.style.left = `calc(${percent}% - 10px)`; // 눈금과 동일 기준
	  
	  // 최소/최대 연봉 표시
	  setTimeout(() => {
	    const minLabel = document.createElement('span');
	    minLabel.textContent = `${min}만원`;
	    minLabel.style.position = 'absolute';
	    minLabel.style.right = '110%';
	    minLabel.style.top = '50%';
	    minLabel.style.transform = 'translateY(-50%)';
	    minLabel.style.fontSize = '0.8rem';
	    minLabel.style.color = '#555';

	    const maxLabel = document.createElement('span');
	    maxLabel.textContent = `${max}만원`;
	    maxLabel.style.position = 'absolute';
	    maxLabel.style.left = '110%';
	    maxLabel.style.top = '50%';
	    maxLabel.style.transform = 'translateY(-50%)';
	    maxLabel.style.fontSize = '0.8rem';
	    maxLabel.style.color = '#555';

	    icon.appendChild(minLabel);
	    icon.appendChild(maxLabel);
	  }, 1500);
	}, 300);
  });
}



function renderScale(globalMin, globalMax) {
  const scaleContainer = document.getElementById('salaryRankScale');
  scaleContainer.innerHTML = '';

  const roundedMax = Math.ceil(globalMax / 1000) * 1000;
  const step = Math.ceil((roundedMax - globalMin) / 5 / 1000) * 1000;

  for (let val = globalMin; val <= roundedMax; val += step) {
    // 0은 라벨과 눈금선 모두 제거
    if (val === globalMin) continue;

    const percent = ((val - globalMin) / (roundedMax - globalMin)) * 100;

    // 라벨
    const mark = document.createElement('div');
    mark.className = 'scale-mark';
    mark.style.left = `${percent}%`;
    mark.style.transform = 'translateX(-50%)';
    mark.textContent = val >= 10000 ? `${(val/10000).toFixed(1)}억` : `${val}만`;

    // 눈금선
    const line = document.createElement('div');
    line.className = 'scale-line';
    line.style.left = `${percent}%`;

    scaleContainer.appendChild(mark);
    scaleContainer.appendChild(line);
  }
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