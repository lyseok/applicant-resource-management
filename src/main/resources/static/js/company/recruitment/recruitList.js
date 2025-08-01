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

    renderPagination(filteredData.length);
    renderPage(1);
}

function renderPage(page) {
    const listContainer = document.getElementById('recruitment-list');
    const itemsPerPage = 5;
    listContainer.innerHTML = '';
    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const paginatedItems = filteredData.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('div');
        row.className = 'd-flex align-items-center justify-content-between border rounded p-3 mb-3 bg-white shadow-sm';
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
            ? `${item.RECRUITMENTTITLE} <span class="badge bg-danger ms-2">마감</span>`
            : item.RECRUITMENTTITLE;

        row.innerHTML = `
            <div class="flex-grow-1">
                <h5 class="fw-bold mb-1">${titleHtml}</h5>
                <p class="text-muted mb-2">${truncateText(item.RECCONTENT, 5)}</p>
                <div class="small text-muted">
                    <span><strong>경력:</strong> ${item.yearCodeName || '-'}</span> |
                    <span><strong>직무:</strong> ${item.jobCodeName || '-'}</span> |
                    <span><strong>지역:</strong> ${item.cityCodeName} ${item.districtCodeName}</span> |
                    <span><strong>급여:</strong> ${item.SALARY || '-'}</span> |
                    <span><strong>등록일:</strong> ${item.RECRUITMENTSTARTDATE?.substring(0, 10) || '-'}</span> |
                    <span><strong>마감일:</strong> ${item.RECRUITMENTFINISHDATE?.substring(0, 10) || '-'}</span> |
                    <span><strong>지원자 수:</strong> ${item.APPLICANTCOUNT}</span>
                </div>
            </div>
        `;
        listContainer.appendChild(row);
    });
}

function renderPagination(totalItems) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';
    const itemsPerPage = 5;
    const totalPages = Math.ceil(totalItems / itemsPerPage);

    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement('button');
        btn.className = 'btn btn-sm btn-outline-secondary mx-1';
        btn.textContent = i;
        btn.addEventListener('click', () => renderPage(i));
        paginationContainer.appendChild(btn);
    }
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