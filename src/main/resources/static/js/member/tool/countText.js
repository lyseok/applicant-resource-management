 document.addEventListener('DOMContentLoaded', function () {
    // 글자수 세기 기능
    const textarea = document.getElementById('input');
    const countWithSpaceSpan = document.getElementById('count-with-space');
    const countWithoutSpaceSpan = document.getElementById('count-without-space');

    if (textarea) {
      textarea.addEventListener('input', function () {
        const text = textarea.value;
        countWithSpaceSpan.textContent = text.length;
        countWithoutSpaceSpan.textContent = text.replace(/\s/g, '').length;
      });
    }

    // 메뉴 선택 및 페이지 이동
    window.selectTool = function (n) {
      // active 클래스 토글
      document.querySelectorAll('.tool-remote li')
        .forEach((li, i) => li.classList.toggle('active', i === n - 1));

      // 페이지 이동
      switch (n) {
        case 1:
          location.href = '/countText';
          break;
        case 2:
          location.href = '/Spelling';
          break;
        case 3:
          location.href = '/countyear';
          break;
        case 4:
          alert('아직 경로가 준비되지 않았습니다.');
          break;
      }
    };
  });