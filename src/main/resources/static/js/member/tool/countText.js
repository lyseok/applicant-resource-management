document.addEventListener('DOMContentLoaded', function() {
    // HTML 요소들을 가져옵니다.
    const textarea = document.getElementById('input'); // 텍스트를 입력할 텍스트 영역
    const countWithSpaceSpan = document.getElementById('count-with-space'); // 공백 포함 글자 수를 표시할 곳
    const countWithoutSpaceSpan = document.getElementById('count-without-space'); // 공백 제외 글자 수를 표시할 곳

    // 텍스트 영역에 'input' 이벤트 리스너를 추가합니다.
    // 'input' 이벤트는 텍스트 영역의 내용이 변경될 때마다 발생합니다.
    textarea.addEventListener('input', function() {
        const text = textarea.value; // 현재 텍스트 영역에 있는 값(텍스트)을 가져옵니다.

        // 공백 포함 글자 수 계산
        const countWithSpace = text.length; // 문자열의 .length 속성은 공백을 포함한 전체 길이를 반환합니다.
        countWithSpaceSpan.textContent = countWithSpace; // 결과를 해당 <span>에 표시합니다.

        // 공백 제외 글자 수 계산
        // .replace(/\s/g, '')는 모든 공백 문자(스페이스, 탭, 줄 바꿈 등)를 제거합니다.
        const countWithoutSpace = text.replace(/\s/g, '').length;
        countWithoutSpaceSpan.textContent = countWithoutSpace; // 결과를 해당 <span>에 표시합니다.
    });
});