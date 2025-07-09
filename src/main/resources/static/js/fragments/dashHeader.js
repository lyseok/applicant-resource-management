document.addEventListener('DOMContentLoaded', function() {
    // 버튼과 레이어 변수 저장
    const alarmBtn = document.getElementById('myAlarmBlt');
    const alarmLayer = document.querySelector('.alarm_layer_content');
    const memberBtn = document.querySelector('.btn_member');
    const memberLayer = document.querySelector('.layer_member');

    // 알림 버튼 클릭 이벤트
    alarmBtn.addEventListener('click', function(event) {
        event.stopPropagation();
        const isOpen = alarmLayer.style.display === 'block';
        // 멤버 레이어 무조건 닫기
        memberLayer.style.display = 'none';
        // 토글
        alarmLayer.style.display = isOpen ? 'none' : 'block';
    });

    // 멤버 버튼 클릭 이벤트
    memberBtn.addEventListener('click', function(event) {
        event.stopPropagation();
        const isOpen = memberLayer.style.display === 'block';
        // 알림 레이어 무조건 닫기
        alarmLayer.style.display = 'none';
        // 토글
        memberLayer.style.display = isOpen ? 'none' : 'block';
    });

    // 외부 클릭시 닫기 (버튼/레이어 아닌 부분 클릭시)
    document.addEventListener('click', function(event) {
        // 이벤트 발생 위치가 버튼/레이어 내부가 아니면 닫기
        if (!alarmBtn.contains(event.target) && !alarmLayer.contains(event.target)) {
            alarmLayer.style.display = 'none';
        }
        if (!memberBtn.contains(event.target) && !memberLayer.contains(event.target)) {
            memberLayer.style.display = 'none';
        }
    });
    
    const logoutBtnEl = document.querySelector('.logoutBtn');
    
    logoutBtnEl.addEventListener('click', () => {
      axios.post("/common/auth/revoke", {}, {
        withCredentials:true
      }).then(resp=>location.href="/");
    });
});
