document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('filterForm');
  const tableBody = document.querySelector('#logTable tbody');

  form.addEventListener('submit', async e => {
    e.preventDefault();
    const key = form.querySelector('select[name="key"]').value;
    const keyword = form.querySelector('input[name="keyword"]').value.trim();
    if (!keyword) return;

    try {
      const res = await fetch(`/company/log/selectlog?key=${encodeURIComponent(key)}&keyword=${encodeURIComponent(keyword)}`);
      if (!res.ok) throw new Error(res.statusText);
      const logs = await res.json();

      tableBody.innerHTML = '';

      if (logs.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">검색 결과가 없습니다.</td></tr>`;
        return;
      }

      logs.forEach((log, idx) => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${idx + 1}</td>
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
    return s ? s.replace(/[&<>"']/g, c =>
      ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;' })[c]
    ) : '';
  }

  function truncate(s, len) {
    return s.length > len ? s.slice(0, len) + '…' : s;
  }
});
