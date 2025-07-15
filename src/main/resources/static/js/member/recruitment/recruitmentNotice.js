/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
  const applyBtn = document.getElementById('applyBtn');
  const finishDateStr = applyBtn.dataset.finish;
  const finishDate = new Date(`${finishDateStr}T23:59:59`);
  const dDayEl = document.getElementById('dDayCounter');

  function updateDday() {
    const now = new Date();
    const diff = finishDate - now;

    if (diff <= 0) {
      dDayEl.textContent = '마감';
      return;
    }

    const days = Math.floor(diff / (1000 * 60 * 60 * 24));
    const hours = Math.floor((diff / (1000 * 60 * 60)) % 24);
    const minutes = Math.floor((diff / (1000 * 60)) % 60);
    const seconds = Math.floor((diff / 1000) % 60);

    dDayEl.textContent = `D-${days} ${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
  }

  updateDday();
  setInterval(updateDday, 1000);
});