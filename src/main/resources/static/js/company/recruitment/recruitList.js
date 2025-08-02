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
    filteredData.sort((a, b) => {
        const dateA = new Date(a.RECRUITMENTSTARTDATE);
        const dateB = new Date(b.RECRUITMENTSTARTDATE);
        return sortOrder === 'latest' ? dateB - dateA : dateA - dateB;
    });
    
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
        const row = document.createElement('div');
        row.className = 'border-bottom p-3 ';
        row.style.cursor = 'pointer';

        // 클릭 이벤트: FINISHYN 값에 따라 다른 URL로 이동
        if (item.FINISHYN === 'Y') {
            row.addEventListener('click', () => {
                window.location.href = `/company/applicant_record/${item.RECRUITMENTNO}`;
            });
        } else {
            row.addEventListener('click', () => {
                window.location.href = `/company/recruit_notice/${item.RECRUITMENTNO}`;
            });
        }

        // 제목 옆에 마감 딱지 표시
        const titleHtml = item.FINISHYN === 'Y' 
            ? `<span class="badge bg-violet07 me-2">공고마감</span> ${item.RECRUITMENTTITLE}`
            : item.RECRUITMENTTITLE;

        row.innerHTML = `
            <div class="flex-grow-1">
                <h5 class="d-flex align-items-center fw-bold mb-2 gap-2">${titleHtml} <span class="fs-14 fw-500">- 지원자 <strong class="fs-15 text-violet80">${item.APPLICANTCOUNT}</strong></span></h5>
                <!--<p class="text-muted mb-2">${truncateText(item.RECCONTENT, 5)}</p> -->
                <div class="d-flex small text-muted gap-3">
                    <span><strong>경력</strong> ${item.yearCodeName || '-'}</span>
                    <span><strong>직무</strong> ${item.jobCodeName || '-'}</span>
                    <span><strong>지역</strong> ${item.cityCodeName} ${item.districtCodeName}</span>
                    <span><strong>급여</strong> ${item.SALARY || '-'}만원</span>
                </div>
                    <span class="small text-muted gap-3"><strong>채용기간</strong> ${item.RECRUITMENTSTARTDATE?.substring(0, 10) || '-'} ~  ${item.RECRUITMENTFINISHDATE?.substring(0, 10) || '-'}</span>
            </div>
        `;
        listContainer.appendChild(row);
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