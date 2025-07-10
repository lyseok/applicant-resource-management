document.addEventListener('DOMContentLoaded', () => {
  const input = document.getElementById('textInput');
  const result = document.getElementById('spellResult');

  let debounceTimer = null;

  input.addEventListener('input', () => {
    clearTimeout(debounceTimer);
    
    debounceTimer = setTimeout(() => {
      const text = input.value.trim();
      if (!text) {
        result.innerHTML = '';
        return;
      }

      fetch('/spell/check', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ text })  // JSON key: "text"
      })
      .then(response => {
        if (!response.ok) throw new Error('서버 오류 발생');
        return response.json();
      })
      .then(data => {
        // 예상: data.correctedText 혹은 data.result
        result.innerHTML = data.correctedText || '<span style="color:red;">오류 응답</span>';
      })
      .catch(err => {
        console.error('맞춤법 요청 실패:', err);
        result.innerHTML = '<span style="color:red;">검사 실패</span>';
      });
    }, 400); // 0.4초 지연 후 요청
  });
});
