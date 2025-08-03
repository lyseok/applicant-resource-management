document.addEventListener('DOMContentLoaded', () => {
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
  const maxLabelEl = document.getElementById('max-label');
  const sortSel = document.getElementById('sort-select');  

  const SALARY_MAX = 10000;
  let minVal = 0;
  let maxVal = SALARY_MAX;

  // 서버로 보낼 파라미터
  let params = {
    page: 1,
    pageSize: 10,
    keyword: '',
    minSalary: 0,
    maxSalary: SALARY_MAX,
    sort: 'default'
  };

  // **데이터 로드**
  function fetchSalaryData() {
    axios.get('/ajax/member/company_salary', { params }).then(resp => {
      const data = resp.data.data;
      const totalCount = resp.data.totalCount;
      
      console.log("현재 페이지:", params.page);
      console.log("가져온 데이터:", data);
      
      renderList(data, totalCount);
      renderPager(Math.ceil(totalCount / params.pageSize), params.page);
      salaryTitleEl.textContent = `${totalCount}개 기업의 연봉이 등록되어 있습니다.`;
    }).catch(err => console.error(err));
  }
  fetchSalaryData(); // 초기 로드

  // **검색어 입력**
  searchEl.addEventListener('input', () => {
    params.keyword = searchEl.value.trim();
    params.page = 1;
    fetchSalaryData();
  });
  
  sortSel.addEventListener('change', () => {
	  params.sort = sortSel.value;
	  params.page = 1;
	  fetchSalaryData();
  });

  /*// **셀렉트 박스 변경**
  minSel.addEventListener('change', () => {
    minVal = Number(minSel.value);
    params.minSalary = minVal;
    params.page = 1;
    sliderUI();
    fetchSalaryData();
  });
  maxSel.addEventListener('change', () => {
    maxVal = Number(maxSel.value);
    params.maxSalary = maxVal;
    params.page = 1;
    sliderUI();
    fetchSalaryData();
  });*/

  // **슬라이더 드래그**
  sliderDraggable(minHandle, true);
  sliderDraggable(maxHandle, false);
  sliderUI();

  function sliderDraggable(handle, isMinHandle) {
    handle.addEventListener('mousedown', e => {
      e.preventDefault();
      const container = handle.parentElement;
      const rect = container.getBoundingClientRect();

      function onMove(ev) {
        let x = ev.clientX - rect.left;
        x = Math.max(0, Math.min(x, rect.width));
        const val = Math.round((x / rect.width) * SALARY_MAX);
        if (isMinHandle) minVal = Math.min(val, maxVal);
        else maxVal = Math.max(val, minVal);
        sliderUI();
      }

      document.addEventListener('mousemove', onMove);
      document.addEventListener('mouseup', () => {
        params.minSalary = minVal;
        params.maxSalary = maxVal;
        params.page = 1;
        fetchSalaryData();
        document.removeEventListener('mousemove', onMove);
      }, { once: true });
    });
  }

  function sliderUI() {
    const p1 = (minVal / SALARY_MAX) * 100;
    const p2 = (maxVal / SALARY_MAX) * 100;
    minHandle.style.left = `${p1}%`;
    maxHandle.style.left = `${p2}%`;
    sliderRangeEl.style.left = `${p1}%`;
    sliderRangeEl.style.width = `${p2 - p1}%`;
    minLabelEl.textContent = minVal === 0 ? '0만원' : `${minVal.toLocaleString()}만원`;
    maxLabelEl.textContent = maxVal === 0 ? '1억원↑' : `${maxVal.toLocaleString()}만원`;
    minSel.value = minVal;
    maxSel.value = maxVal;
  }


  function renderList(data, totalCount) {
    salaryListEL.innerHTML = '';
	  

		const reTotalCount = document.getElementById('result_total_count'); 
	  reTotalCount.innerHTML = `<p class="fs-14">총 <span class="fw-bold">${totalCount}</span>건</p>`;

	  if (data.length === 0) {
	    const li = document.createElement('li');
	    li.className = 'no-item';
	    li.textContent = '조건에 맞는 기업이 없습니다.';
	    salaryListEL.appendChild(li);
	    return;
	  }
    data.forEach(c => {
      const li = document.createElement('li');
      li.className = 'item';
      li.onclick = () => {
        location.href = `/member/company_salary/detail?companyId=${c.USER_ID}`;
      };
      li.innerHTML = `
        <div class="info">
          ${c.COM_LOGO ? `<img class="logo" src="${c.COM_LOGO}" alt="${c.COM_NAME}"/>` : `<div class="logo"></div>`}
          <div class="text">
            <div class="name">${c.COM_NAME}<span style="color:var(--violet70);font-size:.8rem;">(채용중)</span></div>
            <div class="meta">${c.INDU_NAME || ''} | 사원수 ${c.COM_MEM || 0}명</div>
          </div>
        </div>
        <div class="text-end"><small class="d-block">평균 연봉</small> ${formatSalary(c.SALARY_AVG)}</div>
      `;
      salaryListEL.appendChild(li);
    });
  }

  function formatSalary(salary) {
    salary = Number(salary);
    if (isNaN(salary) || salary === 0) return '면접 후 결정';
    if (salary < 10000) return `<b class="fs-5">${salary}</b><small>만원</small>`;
    const eok = Math.floor(salary / 10000);
    const man = salary % 10000;
    return man > 0 ? `${eok}억 ${man}만원` : `${eok}억원`;
  }

  // **페이징 렌더링**
  function renderPager(totalPages, currentPage) {
    let html = '';
    for (let i = 1; i <= totalPages; i++) {
      if (i === currentPage) html += `<span class="BtnType SizeS active">${i}</span>`;
      else html += `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
    }
    if (currentPage < totalPages) html += `<button data-page="${currentPage + 1}" class="BtnType SizeS BtnNext">다음</button>`;
    document.querySelector('.PageBox').innerHTML = html;
  }

  // **페이지 클릭**
  document.querySelector('.PageBox').addEventListener('click', e => {
    if (e.target.classList.contains('page')) {
      params.page = Number(e.target.dataset.page);
      fetchSalaryData();
    } else if (e.target.classList.contains('BtnNext')) {
      params.page += 1;
      fetchSalaryData();
    }
  });
});
