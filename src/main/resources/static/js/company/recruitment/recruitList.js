const searchInput = document.getElementById('search-title');
const filterSelect = document.getElementById('filter-period');
const sortSelect = document.getElementById('sort-date');

let recruitmentData = [];
let filteredData = [];

function truncateText(text, maxLength) {
    if (!text) return '공고 내용 없음';
    return text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
}

function filterAndSearchData() {
    const keyword = searchInput.value.trim().toLowerCase();
    const period = filterSelect.value;
    const sortOrder = sortSelect.value;
    const now = new Date();

		// 총 갯수
		const cnt = document.getElementById("list-count");
		cnt.innerText = recruitmentData.length;
		
		
    filteredData = recruitmentData.filter(item => {
        const titleMatch = item.RECRUITMENTTITLE?.toLowerCase().includes(keyword);
        const startDate = new Date(item.RECRUITMENTSTARTDATE);
        let dateMatch = true;

        if (period !== 'all') {
            const monthsAgo = new Date();
            monthsAgo.setMonth(monthsAgo.getMonth() - parseInt(period));
            dateMatch = startDate >= monthsAgo && startDate <= now;
        }

        return titleMatch && dateMatch;
    });

	    // 정렬
	if (sortOrder !== 'latest') {  // 최신순이면 재정렬 스킵
	    filteredData.sort((a, b) => {
	        const dateA = new Date(a.RECRUITMENTSTARTDATE);
	        const dateB = new Date(b.RECRUITMENTSTARTDATE);
	        return sortOrder === 'oldest' ? dateA - dateB : dateB - dateA;
	    });
	}
    
    // 필터/검색/정렬 시 무조건 1페이지로
    currentPage = 1;
    renderPagination();      // ← totalItems 제거
    renderPage();            // ← currentPage 그대로

}


let currentPage = 1;
const itemsPerPage = 5;  // 중복 제거 (전역으로 빼기)


function renderPage(page = currentPage) {
    currentPage = page;  // 현재 페이지 갱신

    const listContainer = document.getElementById('recruitment-list');
    listContainer.innerHTML = '';
    
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const paginatedItems = filteredData.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('ul');

				
        row.innerHTML = `						
						<li class="p-4 border-bottom d-flex justify-content-between recruit_list gap-5">
		        	<div class="d-flex flex-fill align-items-start">
			        	<div class="com_name_box viewat_box">
									${item.FINISHYN === "Y" ? `<div class="d-inline-block bg-violet03 fs-13 px-3">공고 마감</div>`: ""}
								</div>
								<div class="recruit_tit">
			          	<a class="d-block fs16 fw-bold m-0" href="/company/recruit_notice/${item.RECRUITMENTNO}"> ${item.RECRUITMENTTITLE}</a>     	
									<div class="d-flex align-items-center">
										<span class="fs-14 text-muted">${item.jobCodeName || '-'}</span>
									</div>
									<p class="fs-14 text-muted mt-2">${item.RECRUITMENTSTARTDATE?.substring(0, 10) || '-'} ~  ${item.RECRUITMENTFINISHDATE?.substring(0, 10) || '-'}</p>
								</div>
			          <div class="recruit_info">
			          	<div class="d-flex align-items-center">
			          		<span class="material-symbols-outlined">distance</span>
			          		<span class="">${item.cityCodeName} ${item.districtCodeName}</span>
			        		</div>
									<div class="d-flex align-items-center">
										<span class="material-symbols-outlined">money_bag</span>
										<span class="num_line">${item.SALARY || '-'}만원</span>
									</div> 
									<div class="d-flex align-items-center">
										<span class="material-symbols-outlined">business_center</span>
										<span class="num_line">${item.yearCodeName || '-'}</span>
									</div>
			          </div>
			        </div>
		
								<div class="d-flex flex-column align-items-center gap-1 fs-12 recruit_btn_wrap">
									<div class="d-flex align-items-center gap-1 mb-1">
										<span class="material-symbols-outlined">person</span>
										<span class="text-dark fs-14">지원자 <strong class="fs-15 text-violet80">${item.APPLICANTCOUNT}</strong></span>
									</div> 
								</div>
							</div>
						</li>
        `;
        listContainer.appendChild(row);
				
				// btn_wrap는 row 내부에서 찾기
				const btnWrap = row.querySelector(".recruit_btn_wrap");
				const recruitBtn = document.createElement("a");
				recruitBtn.className = `btn review-btn w140 justify-content-center fw-light fs-14 ${item.FINISHYN === 'Y' ? 'btn_violet' : 'btn_violet_line'}`;
				recruitBtn.textContent = item.FINISHYN === 'Y' ? '지원자 확인' : '공고 보기';
				recruitBtn.href = item.FINISHYN === 'Y'
		       ? `/company/applicant_record/${item.RECRUITMENTNO}`
		       : `/recruit_notice/${item.RECRUITMENTNO}`;
				btnWrap.appendChild(recruitBtn);
    });
}

function renderPagination() {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';
    const totalPages = Math.ceil(filteredData.length / itemsPerPage);

    // 이전 버튼
    const prev = document.createElement('a');
    prev.className = 'BtnType SizeS BtnPrev';
    prev.textContent = '이전';
    prev.href = '#';
    if (currentPage === 1) prev.classList.add('disabled');
    else prev.addEventListener('click', e => { e.preventDefault(); renderPage(currentPage - 1); renderPagination(); });
    paginationContainer.appendChild(prev);

    // 페이지 번호
    for (let i = 1; i <= totalPages; i++) {
        if (i === currentPage) {
            // 현재 페이지는 span
            const span = document.createElement('span');
            span.className = 'BtnType SizeS active';
            span.textContent = i;
            paginationContainer.appendChild(span);
        } else {
            // 다른 페이지는 a
            const a = document.createElement('a');
            a.href = '#';
            a.textContent = i;
            a.className = 'BtnType SizeS page';
            a.addEventListener('click', e => { 
                e.preventDefault();
                renderPage(i); 
                renderPagination(); 
            });
            paginationContainer.appendChild(a);
        }
    }


    // 다음 버튼
    const next = document.createElement('a');
    next.className = 'BtnType SizeS BtnNext';
    next.textContent = '다음';
    next.href = '#';
    if (currentPage === totalPages) next.classList.add('disabled');
    else next.addEventListener('click', e => { e.preventDefault(); renderPage(currentPage + 1); renderPagination(); });
    paginationContainer.appendChild(next);
}




fetch(`/ajax/recruit/list`)
    .then(response => response.json())
    .then(data => {
        recruitmentData = data;
        filterAndSearchData();
    })
    .catch(err => {
        document.getElementById('recruitment-list').innerHTML = `<div class="text-danger">채용공고를 불러오는 중 오류가 발생했습니다.</div>`;
        console.error(err);
    });

// 🔁 이벤트 연결
searchInput.addEventListener('input', filterAndSearchData);
filterSelect.addEventListener('change', filterAndSearchData);
sortSelect.addEventListener('change', filterAndSearchData);