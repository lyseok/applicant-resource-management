document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById('calendar');
  let selectedEvent = null;

  const modal = new bootstrap.Modal(document.getElementById('eventViewModal'));

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko',
    timeZone: 'local',
    editable: true,
    selectable: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    eventSources: [
      {
        url: '/ajax/schedule/recruit',
        method: 'GET',
        color: '#4e73df',
        editable: false
      },
      {
        url: '/ajax/schedule/custom',
        method: 'GET',
        color: '#f6c23e',
        editable: true
      }
    ],
    select: function(info) {
      const title = prompt("일정 제목을 입력하세요:");
      if (title) {
        fetch('/ajax/schedule/custom', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            scheduleName: title,
            scheduleStartDate: info.startStr,
            scheduleEndDate: info.endStr
          })
        }).then(() => calendar.refetchEvents());
      }
    },
    eventClick: function(info) {
      selectedEvent = info.event;

      // 값 세팅
		document.getElementById('modalTitleInput').value = selectedEvent.title;
		document.getElementById('modalStartInput').value = selectedEvent.start.toISOString().slice(0, 16);
		document.getElementById('modalEndInput').value = selectedEvent.end ? selectedEvent.end.toISOString().slice(0, 16) : '';

      // 사용자 일정만 수정/삭제 버튼 보이게
      const customControls = document.getElementById('customControls');
      if (selectedEvent.source.url.includes('/custom')) {
        customControls.style.display = 'flex';
      } else {
        customControls.style.display = 'none';
      }

      modal.show();
    },
    eventDrop: function(info) {
      if (info.event.source.url.includes('/custom')) {
        fetch('/ajax/schedule/custom/update/' + info.event.id, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
			scheduleNo: info.event.id,
			scheduleName: info.event.title,
            scheduleStartDate: info.event.start.toISOString(),
            scheduleEndDate: info.event.end ? info.event.end.toISOString() : null
          })
        });
      }
    }
  });

  document.getElementById('editBtn').addEventListener('click', function () {
  const newTitle = document.getElementById('modalTitleInput').value;
  const newStart = document.getElementById('modalStartInput').value;
  const newEnd = document.getElementById('modalEndInput').value;

  if (newTitle && newStart) {
    fetch('/ajax/schedule/custom/update/' + selectedEvent.id, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        scheduleNo: selectedEvent.id,
        scheduleName: newTitle,
        scheduleStartDate: newStart,
        scheduleEndDate: newEnd || null
      })
    }).then(() => {
      modal.hide();
      calendar.refetchEvents();
    });
  } else {
    alert('제목과 시작 시간은 필수입니다!');
  }
});

  // 삭제 버튼
  document.getElementById('deleteBtn').addEventListener('click', function () {
    if (confirm("정말 삭제하시겠습니까?")) {
      fetch('/ajax/schedule/custom/' + selectedEvent.id, { method: 'DELETE' })
        .then(() => {
          modal.hide();
          calendar.refetchEvents();
        });
    }
  });

  calendar.render();
});