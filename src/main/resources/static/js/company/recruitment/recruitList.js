document.addEventListener('DOMContentLoaded', function () {
    const listContainer = document.getElementById('recruitment-list');
    const countEl = document.getElementById('recruitment-count');

    fetch(`/ajax/recruit/list`)
        .then(response => response.json())
        .then(data => {
            listContainer.innerHTML = '';

            data.forEach(item => {
                const row = document.createElement('div');
                row.className = 'd-flex align-items-center justify-content-between border rounded p-3 mb-3 bg-white shadow-sm';

				function truncateText(text, maxLength) {
					if (!text) return '공고 내용 없음';
					return text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
				}

                row.innerHTML = `
                    <div class="flex-grow-1">
                        <h5 class="fw-bold mb-1">${item.RECRUITMENTTITLE}</h5>
                        <p class="text-muted mb-2">${truncateText(item.RECCONTENT, 5) || '공고 내용 없음'}</p>
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
                    <div>
                        <a href="/company/recruit_notice/${item.RECRUITMENTNO}" class="btn btn-outline-primary btn-sm">상세보기</a>
                    </div>
                `;
                listContainer.appendChild(row);
            });
        })
        .catch(err => {
            listContainer.innerHTML = `<div class="text-danger">채용공고를 불러오는 중 오류가 발생했습니다.</div>`;
            console.error(err);
        });
});