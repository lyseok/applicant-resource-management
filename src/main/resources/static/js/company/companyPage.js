document.addEventListener("DOMContentLoaded", function() {
	const calendarEl = document.getElementById('calendar');
	const modal = new bootstrap.Modal(document.getElementById('eventViewModal'));

	// 날짜 포맷
	function formatDate(date) {
		if (!date) return '-';
		const d = new Date(date);
		return d.getFullYear() + '-' +
			String(d.getMonth() + 1).padStart(2, '0') + '-' +
			String(d.getDate()).padStart(2, '0') + ' ' +
			String(d.getHours()).padStart(2, '0') + ':' +
			String(d.getMinutes()).padStart(2, '0');
	}

	// DB 문자열 또는 ISO 문자열을 -> YYYY-MM-DD HH:mm
	function cleanDateStr(dateInput) {
		if (!dateInput) return '-';

		// Date 객체면 포맷
		if (dateInput instanceof Date) {
			const y = dateInput.getFullYear();
			const m = String(dateInput.getMonth() + 1).padStart(2, '0');
			const d = String(dateInput.getDate()).padStart(2, '0');
			const hh = String(dateInput.getHours()).padStart(2, '0');
			const mm = String(dateInput.getMinutes()).padStart(2, '0');
			return `${y}-${m}-${d} ${hh}:${mm}`;
		}

		// 문자열이면: T / 공백 제거 후 분까지만
		let str = String(dateInput).trim();
		str = str.replace('T', ' ').split('+')[0]; // ISO → 공백 치환 후 타임존 제거
		const [date, time] = str.split(' ');
		if (!time) return date;
		const [hh, mm] = time.split(':');
		return `${date} ${hh}:${mm}`;
	}

	const calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		locale: 'ko',
		timeZone: 'local',
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridDay'
		},
		eventSources: [
			{ url: '/ajax/company/schedule/recruit', method: 'GET' } // 백엔드에서 채용/시험/면접 이벤트 리턴
		],
		eventClick: function(info) {
			const event = info.event;
			const extra = event.extendedProps.extra || {};
			const type = extra.type || '기타';
			const isClosed = extra.isClosed ? true : false;

			// 제목 + 유형 뱃지 + 마감뱃지
			let badgeColor = type === '공고' ? 'warning' : type === '시험' ? 'primary' : 'success';
			let closedBadge = isClosed ? ' <span class="badge bg-danger">마감됨</span>' : '';
			document.getElementById('modalTitle').innerHTML =
				`${event.title} <span class="badge bg-${badgeColor}">${type}</span>${closedBadge}`;

			if (type === '공고') {
				document.getElementById('modalDateInfo').innerText =
					`공고기간: ${cleanDateStr(extra.recruitStart)} ~ ${cleanDateStr(extra.recruitFinish)}`;
				document.getElementById('recruitDateBlock').style.display = 'block';
				document.getElementById('modalRecruitStart').innerText = cleanDateStr(extra.recruitStart);
				document.getElementById('modalRecruitFinish').innerText = cleanDateStr(extra.recruitFinish);
			} else if (type === '시험') {
				document.getElementById('modalDateInfo').innerText = `시험일: ${cleanDateStr(event.start)}`;
				document.getElementById('recruitDateBlock').style.display = 'none';
			} else if (type === '면접') {
				document.getElementById('modalDateInfo').innerText = `면접일: ${cleanDateStr(event.start)}`;
				document.getElementById('recruitDateBlock').style.display = 'none';
			}

			modal.show();
		}
	});

	calendar.render();
});