document.addEventListener('DOMContentLoaded', () => {
  // 모달 요소 및 필드 초기화
  const modalElement = document.getElementById('jobOfferModal');
  const modal = new bootstrap.Modal(modalElement);

  const emailInput = document.getElementById('emailInput');
  const positionSelect = document.getElementById('positionSelect'); // 직군
  const jobSelect = document.getElementById('jobSelect');           // 직무
  const offerContent = document.getElementById('offerContent');
  const submitButton = document.getElementById('submitOffer');

  // 직무 옵션들 캐싱 (기본 "선택" 제외)
  const allJobOptions = Array.from(jobSelect.querySelectorAll('option')).slice(1);

  // 🔁 직군 변경 시 해당 직무만 필터링
  positionSelect.addEventListener('change', () => {
    const selectedTopJobCode = positionSelect.value;

    // 직무 초기화
    jobSelect.innerHTML = '<option value="">선택</option>';

    // 선택된 직군에 맞는 직무만 다시 추가
    allJobOptions.forEach(option => {
      if (option.dataset.topJobCode === selectedTopJobCode) {
        jobSelect.appendChild(option);
      }
    });
  });

  // 👤 이직 제안 버튼 클릭 시: 모달 열고 이메일 세팅
  document.querySelectorAll('[data-role="clickable-card"]').forEach(card => {
    card.addEventListener('click', (e) => {
      e.preventDefault();
      e.stopPropagation();

      const userEmail = card.dataset.userEmail;
      const userId = card.dataset.userId;

      if (userEmail) {
        console.log("이직 제안 대상 이메일:", userEmail);
        emailInput.value = userEmail;
        positionSelect.selectedIndex = 0;
        jobSelect.innerHTML = '<option value="">선택</option>';
        offerContent.value = '';
        modal.show();
      } else {
        console.warn("❗ userEmail 데이터 없음 (userId:", userId, ")");
      }
    });
  });

  // 📤 제출 버튼 클릭 시
  submitButton.addEventListener('click', () => {
    const email = emailInput.value.trim();
    const position = positionSelect.value;
    const job = jobSelect.value;
    const content = offerContent.value.trim();
    
    submitButton.addEventListener('click', () => {
  const email = emailInput.value.trim();
  const position = positionSelect.options[positionSelect.selectedIndex].text;
  const job = jobSelect.options[jobSelect.selectedIndex].text;
  const content = offerContent.value.trim();

  if (!email) {
    alert('이메일을 입력해주세요.');
    return;
  }

  if (!position || !job || !content) {
    alert('모든 항목을 작성해주세요.');
    return;
  }

  // 🔄 서버 전송
  fetch('/talentpool/sendOfferMail', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({
      email,
      position,
      job,
      content,
    })
  })
    .then(res => res.text())
    .then(response => {
      if (response === 'success') {
        alert('이직 제안 메일이 성공적으로 전송되었습니다!');
        modal.hide();
      } else {
       showModal(response);
      }
    })
    .catch(err => {
      console.error('메일 전송 중 오류 발생:', err);
      alert('메일 전송 중 오류가 발생했습니다.');
    });
});

   

    // 서버 전송 로직 (AJAX 등) 이 부분에 삽입 가능
    console.log('📨 이직 제안 전송 데이터:', {
      email,
      position,
      job,
      content
    });

    modal.hide();
  });
});

function showModal(message) {
  document.getElementById('errorModalMessage').textContent = message;
  const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
  errorModal.show();
}