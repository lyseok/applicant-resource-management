/**
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
	        initialView: 'dayGridMonth',
	        locale: 'ko',
	        timeZone: 'local',
	        editable: true,  // 드래그 & 수정 가능
	        selectable: true, // 드래그로 새 일정 선택 가능
	        headerToolbar: {
	            left: 'prev,next today',
	            center: 'title',
	            right: 'dayGridMonth,timeGridWeek,timeGridDay'
	        },
	        eventSources: [
	            {
	                url: '/ajax/schedule/recruitAll',
	                method: 'GET',
	                color: '#4e73df',
	                editable: false  // 읽기 전용
	            },
	            {
	                url: '/ajax/schedule/custom',
	                method: 'GET',
	                color: '#f6c23e',
	                editable: true  // 사용자 일정은 수정 가능
	            }
	        ],

	        // 일정 등록
	        select: function(info) {
	            var title = prompt('일정 제목을 입력하세요:');
	            if (title) {
	                fetch('/ajax/schedule/custom', {
	                    method: 'POST',
	                    headers: { 'Content-Type': 'application/json' },
	                    body: JSON.stringify({
	                        title: title,
	                        start: info.startStr,
	                        end: info.endStr
	                    })
	                }).then(() => calendar.refetchEvents());
	            }
	        },

	        // 일정 클릭 시 수정/삭제
	        eventClick: function(info) {
	            if (info.event.source.url.includes('/custom')) {  // 개인 일정만 수정 가능
	                var choice = confirm('이 일정을 삭제하시겠습니까? (취소 시 수정)');
	                if (choice) {
	                    fetch('/ajax/schedule/custom/' + info.event.id, { method: 'DELETE' })
	                        .then(() => calendar.refetchEvents());
	                } else {
	                    var newTitle = prompt('새 일정 제목을 입력하세요:', info.event.title);
	                    if (newTitle) {
	                        fetch('/ajax/schedule/custom/' + info.event.id, {
	                            method: 'PUT',
	                            headers: { 'Content-Type': 'application/json' },
	                            body: JSON.stringify({ title: newTitle })
	                        }).then(() => calendar.refetchEvents());
	                    }
	                }
	            }
	        },

	        // 드래그로 날짜 변경
	        eventDrop: function(info) {
	            if (info.event.source.url.includes('/custom')) {
	                fetch('/ajax/schedule/custom/' + info.event.id, {
	                    method: 'PUT',
	                    headers: { 'Content-Type': 'application/json' },
	                    body: JSON.stringify({
	                        start: info.event.start.toISOString(),
	                        end: info.event.end ? info.event.end.toISOString() : null
	                    })
	                });
	            }
	        }
	    });

	    calendar.render();
	});