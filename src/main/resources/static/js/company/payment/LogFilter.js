document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('filterForm');
  if (!form) return;

  form.addEventListener('submit', async e => {
    e.preventDefault();

    // 폼 값 수집
    const key     = form.querySelector('select[name="key"]').value;
    const keyword = form.querySelector('input[name="keyword"]').value.trim();
    console.log("key", key);
    console.log("keyword", keyword);
    if (!keyword) return;

    try {
      const res = await fetch(`/company/log/selectlog?key=${encodeURIComponent(key)}&keyword=${encodeURIComponent(keyword)}`);
      if (!res.ok) throw new Error(res.statusText);
      const logs = await res.json();
	console.log("res :", res);
	console.log("logs : ", logs);
      // 테이블 바디를 동적으로 찾고, 존재하는지 확인
      const tableBody = document.querySelector('#logTable tbody');
      if (!tableBody) {
        console.warn('테이블 요소를 찾을 수 없습니다: #logTable tbody');
        return;
      }
      

      // 로드된 로그로 테이블 갱신
      tableBody.innerHTML = '';
      logs.forEach((log, idx) => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <th scope="row">${idx + 1}</th>
          <td>${log.usedAt || '<span class="text-muted">미사용</span>'}</td>
          <td>${log.usedCount}</td>
          <td>${escapeHtml(log.emailAddress)}</td>
          <td>${escapeHtml(log.subject)}</td>
          <td>${truncate(escapeHtml(log.messageBody), 15)}</td>
        `;
        tableBody.appendChild(tr);
      });
    } catch (err) {
      console.error('로그 로드 실패', err);
      alert('로그를 불러오는 중 오류가 발생했습니다.');
    }
  });

  function escapeHtml(s) {
    return s.replace(/[&<>"']/g, c =>
      ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;' })[c]
    );
  }

  function truncate(s, len) {
    return s.length > len ? s.slice(0, len) + '…' : s;
  }
});
