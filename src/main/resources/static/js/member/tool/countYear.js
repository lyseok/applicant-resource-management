document.addEventListener('DOMContentLoaded', () => {
	const startDateInput = document.getElementById('startDate');
	const workMonthsInput = document.getElementById('workMonths');

	startDateInput.addEventListener('change', () => {
		const startDate = new Date(startDateInput.value);
		const today = new Date();

		// 오늘 이후 날짜 선택 방지
		if (startDate > today) {
			alert('입사일은 오늘 이후일 수 없습니다.');
			startDateInput.value = '';
			workMonthsInput.value = '';
			return;
		}

		// 한 달 미만 입사
		const diffTime = today - startDate;
		const diffDays = diffTime / (1000 * 60 * 60 * 24);
		if (diffDays < 30) {
			alert('입사한지 1개월이 경과하지 않았습니다.');
			workMonthsInput.value = '';
			return;
		}

		// 개월 수 계산
		const startYear = startDate.getFullYear();
		const startMonth = startDate.getMonth();
		const todayYear = today.getFullYear();
		const todayMonth = today.getMonth();

		let months = (todayYear - startYear) * 12 + (todayMonth - startMonth);

		// 입사일 일이 오늘보다 크면 1개월 미만으로 간주하고 -1
		if (today.getDate() < startDate.getDate()) {
			months--;
		}

		workMonthsInput.value = months;
	});
});
