document.addEventListener('DOMContentLoaded', async () => {
    let similarSalary = [];
    let companyId = null;

    try {
        const urlParams = new URLSearchParams(window.location.search);
        companyId = urlParams.get('companyId');

        const detailSalary = await axios.get(`/ajax/member/company_salary/detail/${companyId}`);
        const companyInfo = detailSalary.data[0];
        const detailSalaryList = detailSalary.data;

        renderRankChart(detailSalaryList); 
        document.getElementById('salaryCompanyName').textContent = companyInfo.comName;
        document.getElementById('salaryAvgGross').textContent = formatSalary(companyInfo.salaryAvgExclExec);

        const similarResp = await axios.get(`/ajax/member/company_salary/similar/${companyInfo.industryType}`);
        similarSalary = similarResp.data;

        renderSimilarTable(similarSalary, companyId);
		
		const rank = await axios.get(`/ajax/member/company_salary/rank/${companyId}/${companyInfo.industryType}`);
		comRank = rank.data;
		console.log("rank", comRank);
		renderSimailarRank(comRank);
		
    } catch (e) { console.error(e); }

	
	
	
	function renderSimailarRank(comRank){
		document.getElementById('salaryCompanyIndu').textContent = comRank.INDU_NAME +  ' 분야 '
		document.getElementById('salaryRank').textContent = comRank.SALARY_RANK + '위 ';
	}
	

	function renderSimilarTable(data, companyId){
	  
	    const idx = data.findIndex(item => item.USER_ID === companyId);
	    let selectedCompany = null;
	    if(idx > -1){
	        [selectedCompany] = data.splice(idx, 1);
	    }

	   
	    const fixedTable = document.getElementById('fixedTable');
	    const fixedThead = fixedTable.querySelector('thead');
	    const fixedTbody = fixedTable.querySelector('tbody');
	    fixedThead.innerHTML = '';
	    fixedTbody.innerHTML = '';

	    const scrollTable = document.getElementById('scrollTable');
	    const scrollThead = scrollTable.querySelector('thead');
	    const scrollTbody = scrollTable.querySelector('tbody');
	    scrollThead.innerHTML = '';
	    scrollTbody.innerHTML = '';


	    const fixedHeaderRow = document.createElement('tr');
	    fixedHeaderRow.innerHTML = `<th>기업명</th>`;
	    fixedHeaderRow.innerHTML += `<th class="selected">${selectedCompany?.COM_NAME || ''}</th>`;
	    fixedThead.appendChild(fixedHeaderRow);

	
	    const scrollHeaderRow = document.createElement('tr');
	    data.forEach(com => {
	        scrollHeaderRow.innerHTML += `<th>${com.COM_NAME}</th>`;
	    });
	    scrollThead.appendChild(scrollHeaderRow);

	
	    const rows = [
	        { label: '평균연봉', key: 'SALARYAVGEXEC', formatter: formatSalary },
	        { label: '신입초봉', key: 'SALARYNEWHIRE', formatter: formatSalary },
	        { label: '매출액', key: 'SALES', formatter: v => v ? v : '-' },
	        { label: '사원수', key: 'COM_MEM', formatter: v => v?.toLocaleString() + '명' }
	    ];

	    rows.forEach(rowInfo => {
	        // --- 고정 영역 ---
	        const fixedRow = document.createElement('tr');
	        fixedRow.innerHTML = `<td>${rowInfo.label}</td>`;
	        fixedRow.innerHTML += `<td class="selected">${rowInfo.formatter(selectedCompany?.[rowInfo.key]) || '-'}</td>`;
	        fixedTbody.appendChild(fixedRow);

	        // --- 스크롤 영역 ---
	        const scrollRow = document.createElement('tr');
	        data.forEach(item => {
	            scrollRow.innerHTML += `<td>${rowInfo.formatter(item[rowInfo.key]) || '-'}</td>`;
	        });
	        scrollTbody.appendChild(scrollRow);
	    });
	}

	// 스크롤 버튼
	const tableWrapper = document.querySelector('.table-wrapper');
	const btnLeft = document.querySelector('.scroll-btn.left');
	const btnRight = document.querySelector('.scroll-btn.right');
	const cellWidth = 200; // 열 너비
	const visibleCount = 4; // 한 화면에 보이는 열 수

	btnLeft.addEventListener('click', () => {
	    tableWrapper.scrollBy({ left: -cellWidth, behavior: 'smooth' });
	});

	btnRight.addEventListener('click', () => {
	    tableWrapper.scrollBy({ left: cellWidth, behavior: 'smooth' });
	});


    function formatSalary(salary) {
        salary = Number(salary);
        if (isNaN(salary) || salary === 0) return '면접 후 결정';
        if (salary < 10000) return `${salary}만원`;
        const eok = Math.floor(salary / 10000);
        const man = salary % 10000;
        return man > 0 ? `${eok}억 ${man}만원` : `${eok}억원`;
    }


    function renderRankChart(data) {
        const container = document.getElementById('salaryRankChart');
        container.innerHTML = '';

        const filteredData = data.filter(item =>
            !['이사','상무','전무','부사장','사장','임원'].includes(item.codeName)
        );

        const globalMin = 0;
        const globalMax = Math.max(...filteredData.map(d => Number(d.salaryMax)));
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

            const bar = document.createElement('div');
            bar.className = 'rank-bar';

            const icon = document.createElement('div');
            icon.className = 'rank-icon';
            icon.innerHTML = '<i class="bx bxs-user"></i>';
            icon.style.left = '0%';

            bar.appendChild(icon);
            row.appendChild(title);
            row.appendChild(bar);
            container.appendChild(row);

            const roundedMax = Math.ceil(globalMax / 1000) * 1000;

            setTimeout(() => {
                const percent = ((avg - globalMin) / (roundedMax - globalMin)) * 100;
                icon.style.left = `calc(${percent}% - 10px)`;

                setTimeout(() => {
                    const minLabel = document.createElement('span');
                    minLabel.textContent = `${min}`;
                    minLabel.style.position = 'absolute';
                    minLabel.style.right = '110%';
                    minLabel.style.top = '50%';
                    minLabel.style.transform = 'translateY(-50%)';
                    minLabel.style.fontSize = '1rem';
                    minLabel.style.color = '#555';

                    const maxLabel = document.createElement('span');
                    maxLabel.textContent = `${max}`;
                    maxLabel.style.position = 'absolute';
                    maxLabel.style.left = '110%';
                    maxLabel.style.top = '50%';
                    maxLabel.style.transform = 'translateY(-50%)';
                    maxLabel.style.fontSize = '1rem';
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
            if (val === globalMin) continue;

            const percent = ((val - globalMin) / (roundedMax - globalMin)) * 100;

            const mark = document.createElement('div');
            mark.className = 'scale-mark';
            mark.style.left = `${percent}%`;
            mark.style.transform = 'translateX(-50%)';
            mark.textContent = val >= 10000 ? `${(val/10000).toFixed(1)}억` : `${val}만`;

            const line = document.createElement('div');
            line.className = 'scale-line';
            line.style.left = `${percent}%`;

            scaleContainer.appendChild(mark);
            scaleContainer.appendChild(line);
        }
    }
	
	// 선택기업 열 위치 계산
	function updateStickyPosition() {
	    const firstCol = document.querySelector('.compare-table th:first-child');
	    if (!firstCol) return;

	    const firstColWidth = firstCol.offsetWidth; // 기업명 열 너비
	    document.querySelectorAll('.compare-table th.selected, .compare-table td.selected')
	        .forEach(cell => {
	            cell.style.left = firstColWidth + 'px';
	        });
	}

	// DOM 로드 & 리사이즈 시 실행
	window.addEventListener('resize', updateStickyPosition);
	document.addEventListener('DOMContentLoaded', updateStickyPosition);

});
