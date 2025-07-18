/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const formEl = document.getElementById('salaryForm');
	const tbodyEl = document.getElementById('salaryTableBody');

	axios.get('/ajax/code/cmncodegroup/RANK')
		.then(resp => {
			console.log(resp.data.cmnCodeList);
			const rankList = resp.data.cmnCodeList;


			rankList.forEach(rank => {
				const tr = document.createElement('tr');
				tr.innerHTML = `
                        <td>${rank.codeName}</td> 
                        <td><input type="number" placeholder="예: 4000 (단위: 만원)" class="form-control" name="minSalary" data-code="${rank.codeDetailNo}" /></td>
                        <td><input type="number" placeholder="예: 4000 (단위: 만원)" class="form-control" name="maxSalary" data-code="${rank.codeDetailNo}" /></td>
                     `;
				tbodyEl.appendChild(tr);
			});
			return axios.get('/ajax/company/salary_management');
		})
		.then(resp => {
			const salaryList = resp.data;

			salaryList.forEach(salary => {
				const minInput = document.querySelector(`input[name="minSalary"][data-code="${salary.codeDetailNo}"]`);
				const maxInput = document.querySelector(`input[name="maxSalary"][data-code="${salary.codeDetailNo}"]`);

				if (minInput && maxInput) {
					minInput.value = parseInt(salary.salaryMin, 10) / 10000;
					maxInput.value = parseInt(salary.salaryMax, 10) / 10000;
				}
			});
		})
		.catch(error => {
			console.error('연봉 데이터 로드 실패: ', error);
		});



	formEl.addEventListener('submit', (e) => {
		e.preventDefault();

		const rows = document.querySelectorAll('#salaryTableBody tr');
		const payload = [];

		rows.forEach(row => {
			const minInput = row.querySelector('input[name="minSalary"]');
			const maxInput = row.querySelector('input[name="maxSalary"]');
			const codeDetailNo = minInput.dataset.code;

			if (minInput.value && maxInput.value) {
				payload.push({
					codeDetailNo: codeDetailNo,
					salaryMin: parseInt(minInput.value, 10) * 10000,
					salaryMax: parseInt(maxInput.value, 10) * 10000
				});
			}
		});

		axios.post('/ajax/company/salary_management', payload)
			.then(resp => {
				alert('연봉 정보가 저장되었습니다.');
				location.href = '/company/salary_management'; // 메인으로 이동
			}).catch(err => {
				alert('저장실패');
				console.error(err);
			})
	})
	
});
