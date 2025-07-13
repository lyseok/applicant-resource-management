/**
 * 
 */
 document.addEventListener('DOMContentLoaded', function () {
    const userId = '기업아이디값'; // 동적으로 넣거나 서버에서 넘기세요
    const listContainer = document.getElementById('recruitment-list');
    const countEl = document.getElementById('recruitment-count');

    fetch(`/ajax/recruit/list/corp03`)
        .then(response => response.json())
        .then(data => {
            countEl.textContent = `총 ${data.length}건`;
            listContainer.innerHTML = '';

            data.forEach(item => {
                const card = document.createElement('div');
                card.className = 'col-md-6 col-lg-4';

                card.innerHTML = `
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="fw-bold mb-2">${item.RECRUITMENTTITLE}</h5>
                            <p class="text-muted mb-1">${item.RECCONTENT || '공고 내용 없음'}</p>

                            <ul class="list-unstyled small text-muted mb-3">
                                <li><strong>직무:</strong> ${item.jobCodeName || '-'}</li>
                                <li><strong>지역:</strong> ${item.cityCodeName} ${item.districtCodeName}</li>
                                <li><strong>급여:</strong> ${item.SALARY || '-'}</li>
                                <li><strong>등록일:</strong> ${item.STARTDATE?.substring(0, 10) || '-'}</li>
                                <li><strong>마감일:</strong> ${item.FINISHDATE?.substring(0, 10) || '-'}</li>
                                <li><strong>지원자 수:</strong> ${item.APPLICANTCOUNT}</li>
                            </ul>
                        </div>
                        <div class="card-footer text-end bg-white border-0">
                            <a href="/company/recruit_notice/${item.RECRUITMENTNO}" class="btn btn-outline-primary btn-sm">상세보기</a>
                        </div>
                    </div>
                `;
                listContainer.appendChild(card);
            });
        })
        .catch(err => {
            listContainer.innerHTML = `<div class="text-danger">채용공고를 불러오는 중 오류가 발생했습니다.</div>`;
            console.error(err);
        });
});