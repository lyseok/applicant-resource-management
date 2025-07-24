/**
 * 
 */

 document.addEventListener('DOMContentLoaded', ()=>{

  const salaryListEL = document.getElementById('salary-list');
  const salaryTitleEl = document.getElementById('salary-title');
  const searchEl = document.getElementById('search-input');
  const autoEl = document.getElementById('autocomplete-list');
  const minSel = document.getElementById('min-select');
  const maxSel = document.getElementById('max-select');
  const minHandle = document.getElementById('min-handle');
  const maxHandle = document.getElementById('max-handle');
  const sliderRangeEl = document.getElementById('slider-range');
  const minLabelEl = document.getElementById('min-label');
  const maxLabelEl = document.getElementById('min-label');
  
  const SALARY_MAX = 10000; 
  let data = [];
  let filtered = [];
  let minVal = 0;
  let maxVal = SALARY_MAX;
   
   axios.get('/ajax/member/company_salary')
        .then(resp => {
          const salaryList = resp.data;
          console.log(salaryList);
		  
		  data = resp.data.map(s => ({
				...s,
				salaryAvg: Number(s.SALARY_AVG) || 0
		  }));
		  
		  applyFilter();
        }).catch(err => {
          console.error(err);
    })
	
	searchEl.addEventListener('input', () => {
		const searchValue = searchEl.value.trim().toLowerCase();
		autoEl.innerHTML = '';
		if(!searchValue) return;
		
		data.map(c => c.COM_NAME)
			.filter(name => name.toLowerCase().includes(searchValue))
			.slice(0,5)
			.forEach(name => {
				const li = document.createElement('li');
				li.textContent = name;
				lo.onMouseDown = () => {
					searchEl.value = name;
					autoEl.innerHTML = '';
					applyFilter();
				}
				autoEl.append(li);
			});
	});
	searchEl.addEventListener('blur', () => {
		setTimeout(()=> autoEl.innerHTML = '', 100);
	});
	
	minSel.addEventListener('change', () => {
		minVal = Number(minSel.value);
		sliderUI();
		applyFilter();
	});
	maxSel.addEventListener('change', () => {
		maxVal = Number(maxSel.value);
		sliderUI();
		applyFilter();
	});
	
	sliderDraggable(minHandle, true);
	sliderDraggable(maxHandle, false);
	
	sliderUI();
	
	function sliderDraggable(handle, isMinHandle){
		handle.addEventListener('mousedown', e => {
			e.preventDefault();
			const container = handle.parentElement;
			const rect = container.getBoundingClientRect();

			function onMove(ev){
				let x = ev.clientX - rect.left;
				x = Math.max(0, Math.min(x, rect.width));
				const val = Math.round((x / rect.width) * SALARY_MAX);

				if(isMinHandle) {
					minVal = Math.min(val, maxVal);
				}else{
					maxVal = Math.max(val, minVal);
				}

				sliderUI();
			}

			document.addEventListener('mousemove', onMove);
			document.addEventListener('mouseup', () => {
				document.removeEventListener('mousemove', onMove);
				applyFilter();
			}, {once: true});
		})
	}

	function sliderUI(){
		const p1 = (minVal / SALARY_MAX) * 100;
		const p2 = (maxVal / SALARY_MAX) * 100;

		minHandle.style.left = `${p1}%`;
		maxHandle.style.left = `${p2}%`;
		sliderRangeEl.style.left = `${p1}%`;
		sliderRangeEl.style.width = `${p2 - p1}%`;

		minLabelEl.textContent = minVal ===0 ? '0만원' : `${minVal.toLocaleString()}만원`;
		maxLabelEl.textContent = maxVal ===0  ? '1억원↑' : `${maxVal.toLocaleString()}만원`;

		minSel.value = minVal;
		maxSel.value = maxVal;
	}

	function applyFilter(){
		const filterAvg = searchEl.value.trim().toLowerCase();
		filtered = data.filter(c => {
			c.COM_NAME.toLowerCase().includes(filterAvg);
			c.avg >= minVal && c.avg <= maxVal
		});
		renderList();
	}

	function renderList(){
		salaryTitleEl.textContent = `${filtered.length}개 기업의 연봉이 등록되어 있습니다.`;
		salaryListEL.innerHTML = '';

		filtered.forEach(c => {
		const li = document.createElement('li');
		li.className = 'item';
		li.onclick = () => {
			location.href = `/member/company_salary/detail?companyId=${c.USER_ID}`;
		};
		li.innerHTML = `
			<div class="info">
			${c.COM_LOGO
				? `<img class="logo" src="${c.COM_LOGO}" alt="${c.COM_NAME}"/>`
				: `<div class="logo"></div>`}
			<div class="text">
				<div class="name">
				${c.COM_NAME}
				${c.IS_OPEN === 'Y'
					? `<span style="color:var(--violet70);font-size:.8rem;">(채용중)</span>`
					: ''}
				</div>
				<div class="meta">
				${c.INDU_NAME || ''} | 사원수 ${c.COM_MEM || 0}명
				</div>
			</div>
			</div>
			<div class="salary">${formatSalary(c.avg)}</div>
		`;
			salaryListEL.append(li);
		});
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