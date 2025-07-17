/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const tableEl = document.getElementById('salaryTable');
	const tbodyEl = document.getElementById('salaryTableBody')
	const noDataText = document.getElementById('noDataText');
	const actionBtnBox = document.getElementById('actionBtnBox');

	axios.get('/ajax/company/salary_management')
		.then(resp => {
			const salaryList = resp.data;
			if (!salaryList || salaryList.length === 0) {
				noDataText.textContent = '등록된 연봉이 없습니다.';
				actionBtnBox.innerHTML = `<button class="btn btn btn_violet" onclick="location.href='/company/salary_management/form'">연봉 등록</button>`;
			} else {
				noDataText.classList.add('d-none');
				tableEl.classList.remove('d-none');

				salaryList.forEach(salary => {
					const tr = document.createElement('tr');
					const min = formatSmartSalary(salary.salaryMin);
					const max = formatSmartSalary(salary.salaryMax);

					tr.innerHTML = `
                        <td>${salary.codeName}</td>
                        <td>${min} </td>
                        <td>${max} </td>
                    `;
					tbodyEl.appendChild(tr);
				});
				actionBtnBox.innerHTML = `<button class="btn btn btn_violet" onclick="location.href='/company/salary_management/form'">연봉 수정</button>`;
			}
		}).catch(err => {
			console.error('연봉조회 실패: ', err);
			noDataText.textContent = '데이터를 불러오는 데 실패했습니다.';
		});


	function formatToManWon(value) {
		const num = parseInt(value, 10);
		if (isNaN(num)) return '-';
		const man = Math.floor(num / 10000);
		return `${man.toLocaleString()}만 원`;
	}


	function formatSmartSalary(value) {
		const num = parseInt(value, 10);
		if (isNaN(num)) return '-';

		const eok = Math.floor(num / 100000000);
		const man = Math.floor((num % 100000000) / 10000);

		if (eok > 0 && man > 0) return `${eok}억 ${man.toLocaleString()}만 원`;
		if (eok > 0) return `${eok}억 원`;
		return `${man.toLocaleString()}만 원`;
	}
});
