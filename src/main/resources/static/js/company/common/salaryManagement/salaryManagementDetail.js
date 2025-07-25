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
					const min = formatSalary(salary.salaryMin);
					const max = formatSalary(salary.salaryMax);

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


		// 포맷 함수
		   function formatSalary(salary) {
		       salary = Number(salary);
		       if (isNaN(salary) || salary === 0) return '면접 후 결정';
		       if (salary < 10000) {
		           return `${salary}만원`;
		       }
		       const eok = Math.floor(salary / 10000);
		       const man = salary % 10000;
		       return man > 0 ? `${eok}억 ${man}만원` : `${eok}억원`;
		   }
	
	
	
});
